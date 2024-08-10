package com.stream.app.spring_stream_backend.service;

import com.stream.app.spring_stream_backend.entity.Video;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface VideoService {

    // Save Video
    Video save(Video video, MultipartFile file);
    // Get Video By Title
    Video getByTitle(String title);
    // Get All Videos
    List<Video> getAll();

    // Update Video
    Video update(Video video, MultipartFile file);
    // Delete Video
    void delete(Video video);
}
