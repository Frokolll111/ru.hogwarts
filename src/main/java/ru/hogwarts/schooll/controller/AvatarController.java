package ru.hogwarts.schooll.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.schooll.model.Avatar;
import ru.hogwarts.schooll.service.AvatarService;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@RequestMapping("/avatar")
@RestController
public class AvatarController {

    private final AvatarService avatarService;
    private final static Logger logger = LoggerFactory.getLogger(AvatarController.class);

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @PostMapping(value = "/uploadAvatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadAvatar(@RequestParam long idStudent,
                                          @RequestParam MultipartFile file) {
        try {
            avatarService.uploadAvatar(idStudent, file);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(200).build();
    }

    @GetMapping(value = "/id/{id}/avatar/preview")
    public ResponseEntity<byte[]> downloadAvatar(@PathVariable long idStudent) {
        Avatar avatar = avatarService.findAvatar(idStudent);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setContentLength(avatar.getPreview().length);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getPreview());
    }


    @GetMapping(value = "/file/{idStudent}/avatar")
    public void downloadAvatarFile(@PathVariable long idStudent, HttpServletResponse response) throws IOException {
        Avatar avatar = avatarService.findAvatar(idStudent);
        try (var out = response.getOutputStream();
             var in = new FileInputStream(avatar.getFilePath())) {
            in.transferTo(out);
            response.setStatus(200);
            response.setContentType(avatar.getMediaType());
            response.setContentLength((int) avatar.getFileSize());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/avatar")
    public List<Avatar> getPage(@RequestParam int page, @RequestParam int size) {
        return avatarService.getAvatarPage(page, size);
    }
}
