package com.stream.app.spring_stream_backend.repository;

import com.stream.app.spring_stream_backend.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VideoRepository extends JpaRepository<Video, String> {
    Optional<Video> findByTitle(String title);

    // query method

    // native query

    // criteria api query

}
