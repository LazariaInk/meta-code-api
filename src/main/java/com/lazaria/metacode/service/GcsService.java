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
        return StreamSupport.stream(bucket.list(Storage.BlobListOption.prefix(topic + "/"), Storage.BlobListOption.currentDirectory()).iterateAll().spliterator(), false)
                .map(Blob::getName)
                .filter(name -> name.startsWith(topic + "/") && name.endsWith("/"))
                .map(name -> name.substring(topic.length() + 1, name.length() - 1))
                .sorted((a, b) -> Integer.compare(getNumber(a), getNumber(b)))
                .collect(Collectors.toList());
    }

    public List<String> getLessons(String topic, String chapter) {
        Bucket bucket = storage.get(bucketName);
        String prefix = topic + "/" + chapter + "/";
        System.out.println("Listing blobs with prefix: " + prefix);

        // List all blobs under the given prefix
        List<Blob> blobs = StreamSupport.stream(
                        bucket.list(Storage.BlobListOption.prefix(prefix), Storage.BlobListOption.currentDirectory()).iterateAll().spliterator(), false)
                .collect(Collectors.toList());

        System.out.println("All blobs found: " + blobs.stream().map(Blob::getName).collect(Collectors.toList()));

        // Filter and return only HTML files
        List<String> lessonFiles = blobs.stream()
                .map(Blob::getName)
                .filter(name -> name.endsWith(".html"))
                .map(name -> name.substring(prefix.length()))
                .collect(Collectors.toList());

        System.out.println("Filtered lesson files: " + lessonFiles);
        return lessonFiles;
    }

    public String getLessonContent(String topic, String chapter, String lesson) {
        Bucket bucket = storage.get(bucketName);
        String filePath = topic + "/" + chapter + "/" + lesson;
        System.out.println("Fetching content from file path: " + filePath);
        Blob blob = bucket.get(filePath);
        if (blob != null && blob.exists()) {
            String content = new String(blob.getContent());
            System.out.println("Fetched content: " + content);
            return content;
        } else {
            throw new RuntimeException("No HTML file found at path: " + filePath);
        }
    }

    private int getNumber(String name) {
        try {
            return Integer.parseInt(name.split("\\.")[0]);
        } catch (NumberFormatException e) {
            return Integer.MAX_VALUE;
        }
    }
}
