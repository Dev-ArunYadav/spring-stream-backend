package com.stream.app.spring_stream_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "yt_videos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Video {
    @Id
    private String videoId;
    private String title;
    private String description;
    @NotBlank(message = "Content type is required.")
    private String contentType;
    private String filePath;

//    @ManyToOne
//    private Course course;

}
