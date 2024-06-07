package com.lazaria.metacode.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
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
            //GoogleCredentials googleCredentials = GoogleCredentials.fromStream(new ByteArrayInputStream(credentials.getBytes()));
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
                .map(name -> name.replace(topic + "/", "").replace("/", ""))
                .sorted((a, b) -> Integer.compare(getNumber(a), getNumber(b)))
                .collect(Collectors.toList());
    }

    public List<String> getLessons(String topic, String chapter) {
        Bucket bucket = storage.get(bucketName);
        String prefix = topic + "/" + chapter + "/";
        return StreamSupport.stream(bucket.list(Storage.BlobListOption.prefix(prefix), Storage.BlobListOption.currentDirectory()).iterateAll().spliterator(), false)
                .map(Blob::getName)
                .filter(name -> name.startsWith(prefix) && name.endsWith("/"))
                .map(name -> name.replace(prefix, "").replace("/", ""))
                .sorted((a, b) -> Integer.compare(getNumber(a), getNumber(b)))
                .collect(Collectors.toList());
    }

    public String getLessonContent(String topic, String chapter, String lesson) {
        Bucket bucket = storage.get(bucketName);
        Blob blob = bucket.get(topic + "/" + chapter + "/" + lesson + "/index.html");
        return new String(blob.getContent());
    }

    private int getNumber(String name) {
        try {
            return Integer.parseInt(name.split("\\.")[0]);
        } catch (NumberFormatException e) {
            return Integer.MAX_VALUE; // or some default value
        }
    }
}
