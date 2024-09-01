package com.stream.app.spring_stream_backend.controller;

import com.stream.app.spring_stream_backend.constants.Constants;
import com.stream.app.spring_stream_backend.entity.Video;
import com.stream.app.spring_stream_backend.playloads.CustomMessage;
import com.stream.app.spring_stream_backend.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/videos")
public class VideoController {

    @Autowired
    private VideoService videoService;

   /* public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }*/
    @PostMapping
    public ResponseEntity<CustomMessage> createVideo(
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("description") String description
    ){
        Video video = new Video();
        video.setTitle(title);
        video.setDescription(description);
        video.setVideoId(UUID.randomUUID().toString());
        Video saveVideo = videoService.save(video, file);

        if(saveVideo != null) {
            return ResponseEntity.ok(new CustomMessage(Constants.VIDEO_UPLOADED_SUCCESS, true));
        }
        else {
            return ResponseEntity.badRequest().body(new CustomMessage(Constants.VIDEO_NOT_UPLOADED, false));
        }
    }

    @GetMapping("/stream/{videoId}")
    public ResponseEntity<Resource> getVideoById(
            @PathVariable String videoId
    ){
        Video video = videoService.getById(videoId);
        String contentType = video.getContentType();
        String filePath = video.getFilePath();

        Resource resource = new FileSystemResource(filePath);

        if(contentType == null){
            contentType = "application/octet-stream";
        }

        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }
}
