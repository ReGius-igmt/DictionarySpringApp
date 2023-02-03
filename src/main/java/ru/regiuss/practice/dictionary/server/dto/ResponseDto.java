package ru.regiuss.practice.dictionary.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDto<T> {
    private T data;
    private boolean success;

    public ResponseDto(T data) {
        this.data = data;
        this.success = true;
    }
}
