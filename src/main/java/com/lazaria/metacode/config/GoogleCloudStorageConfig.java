package com.lazaria.metacode.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Configuration
public class GoogleCloudStorageConfig {

    @Bean
    public Storage storage() throws IOException {
        String credentials = System.getenv("GOOGLE_CLOUD_CREDENTIALS");
        if (credentials == null) {
            throw new IllegalStateException("GOOGLE_CLOUD_CREDENTIALS not set");
        }

        return StorageOptions.newBuilder()
                .setCredentials(GoogleCredentials.fromStream(
                        new ByteArrayInputStream(credentials.getBytes(StandardCharsets.UTF_8))))
                .build()
                .getService();
    }
}
