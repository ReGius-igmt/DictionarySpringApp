package ru.regiuss.practice.dictionary.server;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.regiuss.practice.dictionary.server.dto.ResponseDto;

@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    public ResponseDto<String> handler(Exception e) {
        return new ResponseDto<>(e.getMessage(), false);
    }

}
