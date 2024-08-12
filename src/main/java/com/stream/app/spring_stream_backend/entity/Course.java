package com.stream.app.spring_stream_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "yt_courses")
public class Course {
    @Id
    private String courseId;
    private String title;
    private String description;
    private String contentType;
    private String filePath;

//    @OneToMany(mappedBy = "course")
//    private List<Video> videos = new ArrayList<>();
}
