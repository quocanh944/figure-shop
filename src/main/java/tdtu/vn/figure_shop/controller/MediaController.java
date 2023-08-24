package tdtu.vn.figure_shop.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import tdtu.vn.figure_shop.service.FireBaseService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/media")
public class MediaController {
    private final FireBaseService fireBaseService;

    @PostMapping(value = "upload-image", consumes = {
            "multipart/form-data"
    })
    @Operation(summary = "Upload a single File")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) throws Exception {
        return ResponseEntity.ok(fireBaseService.uploadFile(file));
    }
}
