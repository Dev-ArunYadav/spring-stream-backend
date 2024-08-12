package com.stream.app.spring_stream_backend.controller;

import com.stream.app.spring_stream_backend.constants.Constants;
import com.stream.app.spring_stream_backend.entity.Video;
import com.stream.app.spring_stream_backend.playloads.CustomMessage;
import com.stream.app.spring_stream_backend.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

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
            @RequestParam("description") String description)
    {
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
}
