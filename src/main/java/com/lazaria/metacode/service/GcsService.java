package com.lazaria.metacode.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class GcsService {

    private final Storage storage;
    private final String bucketName;

    public GcsService(@Value("${GOOGLE_CLOUD_CREDENTIALS}") String credentials,
                      @Value("${GOOGLE_CLOUD_PROJECT_ID}") String projectId,
                      @Value("${GCS_BUCKET_NAME}") String bucketName) throws IOException {
        this.bucketName = bucketName;
        try {
            GoogleCredentials googleCredentials = GoogleCredentials.fromStream(new ByteArrayInputStream(System.getenv("GOOGLE_CLOUD_CREDENTIALS").getBytes()));
            this.storage = StorageOptions.newBuilder()
                    .setProjectId(projectId)
                    .setCredentials(googleCredentials)
                    .build()
                    .getService();
        } catch (IOException e) {
            throw new IOException("Failed to initialize Google Cloud Storage service: " + e.getMessage(), e);
        }
    }

    public List<String> getTopics() {
        Bucket bucket = storage.get(bucketName);
        return StreamSupport.stream(bucket.list(Storage.BlobListOption.prefix(""), Storage.BlobListOption.currentDirectory()).iterateAll().spliterator(), false)
                .map(Blob::getName)
                .filter(name -> name.endsWith("/"))
                .map(name -> name.replace("/", ""))
                .collect(Collectors.toList());
    }

    public List<String> getChapters(String topic) {
        Bucket bucket = storage.get(bucketName);
        String encodedTopic;
        try {
            encodedTopic = URLEncoder.encode(topic, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Failed to encode URL", e);
        }

        String prefix = encodedTopic + "/";
        return StreamSupport.stream(bucket.list(Storage.BlobListOption.prefix(prefix), Storage.BlobListOption.currentDirectory()).iterateAll().spliterator(), false)
                .map(Blob::getName)
                .filter(name -> name.startsWith(encodedTopic + "/") && name.endsWith("/"))
                .map(name -> name.substring(encodedTopic.length() + 1, name.length() - 1))
                .sorted((a, b) -> Integer.compare(getNumber(a), getNumber(b)))
                .collect(Collectors.toList());
    }

    public List<String> getLessons(String topic, String fullChapterName) {
        Bucket bucket = storage.get(bucketName);
        String prefix = topic + "/" + fullChapterName + "/";

        System.out.println("Listing blobs with prefix: " + prefix);

        // List all blobs under the given prefix
        List<Blob> blobs = StreamSupport.stream(
                        bucket.list(Storage.BlobListOption.prefix(prefix), Storage.BlobListOption.currentDirectory()).iterateAll().spliterator(), false)
                .collect(Collectors.toList());
        List<String> lessonFolders = blobs.stream()
                .map(Blob::getName)
                .filter(name -> name.endsWith("/") && !name.equals(prefix))
                .map(name -> name.substring(prefix.length(), name.length() - 1))
                .sorted((prevLesson, nextLesson)->Integer.compare(getNumber(prevLesson), getNumber(nextLesson)))
                .collect(Collectors.toList());

        System.out.println("Filtered lesson folders: " + lessonFolders);
        return lessonFolders;
    }



    public String getLessonContent(String topic, String chapter, String lesson) {
        Bucket bucket = storage.get(bucketName);
        String prefix = topic + "/" + chapter + "/" + lesson + "/";

        System.out.println("Fetching content from folder: " + prefix);

        // List all blobs under the given prefix
        List<Blob> blobs = StreamSupport.stream(
                        bucket.list(Storage.BlobListOption.prefix(prefix), Storage.BlobListOption.currentDirectory()).iterateAll().spliterator(), false)
                .collect(Collectors.toList());

        System.out.println("All blobs found: " + blobs.stream().map(Blob::getName).collect(Collectors.toList()));

        // Find the first HTML file in the folder
        Blob htmlBlob = blobs.stream()
                .filter(blob -> blob.getName().endsWith(".html"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No HTML file found in folder: " + prefix));

        // Log the name of the HTML file found
        System.out.println("HTML file found: " + htmlBlob.getName());

        // Get the content of the HTML file
        String content = new String(htmlBlob.getContent());

        // Adjust image paths in the HTML content
        String adjustedContent = adjustImagePaths(content, prefix);

        System.out.println("Fetched and adjusted content: " + adjustedContent);
        return adjustedContent;
    }

    private String adjustImagePaths(String htmlContent, String prefix) {
        // Find all <img src="..."> tags and adjust the paths to point to the correct location in Google Cloud Storage
        String adjustedContent = htmlContent.replaceAll("(<img\\s+[^>]*src=\")([^\"/]*)(\")", "$1" + "https://storage.googleapis.com/" + bucketName + "/" + prefix + "images/$2$3");
        return adjustedContent;
    }


    private int getNumber(String name) {
        try {
            return Integer.parseInt(name.split("\\.")[0]);
        } catch (NumberFormatException e) {
            return Integer.MAX_VALUE;
        }
    }
}
