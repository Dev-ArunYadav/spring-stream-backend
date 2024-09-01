package com.stream.app.spring_stream_backend.implement;

import com.stream.app.spring_stream_backend.entity.Video;
import com.stream.app.spring_stream_backend.repository.VideoRepository;
import com.stream.app.spring_stream_backend.service.VideoService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class VideoImplement implements VideoService {

    @Autowired
    private VideoRepository videoRepository;

    @Value("${files.video}")
    String DIR;

    @PostConstruct
    public void init(){
        File file = new File(DIR);
        if(!file.exists()){
            file.mkdirs();
            System.out.println("Directory created"+file.getAbsolutePath());
        }else{
            System.out.println("Directory already exists"+file.getAbsolutePath());
        }
    }

    @Override
    public Video save(Video video, MultipartFile file) {
        try {
            // original file name
            String fileName = file.getOriginalFilename();
            String contentType = file.getContentType();
            InputStream inputStream = file.getInputStream();

            //create folder path
            String cleanDir = StringUtils.cleanPath(DIR);
            assert fileName != null;
            String cleanFileName = StringUtils.cleanPath(fileName);
            // folder path with file name
            Path path =Paths.get(cleanDir, cleanFileName);
            System.out.println(path);
            // copy file to folder path
            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
            // video metadata
            video.setContentType(contentType);
            video.setFilePath(path.toString());

            // save video metadata
            return videoRepository.save(video);

        }catch (IOException e){
            e.printStackTrace(System.out);
            return null;
        }
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

    @Override
    public Video getById(String id) {
        return videoRepository.findById(id).orElseThrow(()-> new RuntimeException("Video not found"));
    }


}
