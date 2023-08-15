package tdtu.vn.figure_shop.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

import static tdtu.vn.figure_shop.common.Constants.*;

@Service
public class FireBaseService {
    public ResponseEntity<String> uploadFile(MultipartFile multipartFile) throws IOException {
        String objectName = generateFileName(multipartFile);
        Resource resource = new ClassPathResource(FB_SDK_JSON);

        File file = convertMultiPartToFile(multipartFile);
        Path filePath = file.toPath();

        Storage storage = StorageOptions.newBuilder()
                .setCredentials(GoogleCredentials.fromStream(resource.getInputStream()))
                .setProjectId(FB_PROJECT_ID)
                .build()
                .getService();
        BlobId blobId = BlobId.of(STORAGE_ID, objectName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setMetadata(Map.of("firebaseStorageDownloadTokens",FB_ACCESS_TOKEN)).setContentType(multipartFile.getContentType()).build();

        storage.create(blobInfo, Files.readAllBytes(filePath));
        System.out.println("Deleted file after up to Firebase: " + file.delete());

        return ResponseEntity.status(HttpStatus.CREATED).body(generateImageUrl(objectName));
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convertedFile);
        fos.write(file.getBytes());
        fos.close();
        return convertedFile;
    }

    private String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + "-" + Objects.requireNonNull(multiPart.getOriginalFilename()).replace(" ", "_");
    }

    private String generateImageUrl(String name) {
        return "https://" + FB_STORAGE_URL + name + "?alt=media&token=" + FB_ACCESS_TOKEN;
    }
}
