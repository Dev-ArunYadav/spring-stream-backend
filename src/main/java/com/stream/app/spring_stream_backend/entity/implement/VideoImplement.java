package com.stream.app.spring_stream_backend.entity.implement;

import com.stream.app.spring_stream_backend.entity.Video;
import com.stream.app.spring_stream_backend.service.VideoService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Service
public class VideoImplement implements VideoService {

    @Override
    public Video save(Video video, MultipartFile file) {
        return null;
    }

    @Override
    public Video getByTitle(String title) {
        return null;
    }

    @Override
    public List<Video> getAll() {
        return List.of();
    }

    @Override
    public Video update(Video video, MultipartFile file) {
        return null;
    }

    @Override
    public void delete(Video video) {

    }
}
