package com.stream.app.spring_stream_backend.playloads;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomMessage {

    private String message;
    private Boolean success;


}
