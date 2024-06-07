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
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class GcsService {

    private final Storage storage;
    private final String bucketName;

    public GcsService(@Value("${GCP_CREDENTIALS}") String credentials,
                      @Value("${GCP_PROJECT_ID}") String projectId,
                      @Value("${GCP_STORAGE_BUCKET}") String bucketName) throws IOException {
        this.bucketName = bucketName;
        byte[] decodedCredentials = Base64.getDecoder().decode(credentials);
        this.storage = StorageOptions.newBuilder()
                .setProjectId(projectId)
                .setCredentials(GoogleCredentials.fromStream(new ByteArrayInputStream(decodedCredentials)))
                .build()
                .getService();
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
                .sorted((a, b) ->
