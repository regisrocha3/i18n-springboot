package rrlabs.i18n.config.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import rrlabs.i18n.config.i18n.Translator;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ApiError> handleExceptionNotFound(Exception ex, WebRequest request) {
        final ApiError apiError = ApiError.builder()
                .code(HttpStatus.BAD_REQUEST.toString())
                .message(Translator.getMessage("error.not.found")).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        //Get all errors
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> Translator.getMessage(x.getDefaultMessage()))
                .collect(Collectors.toList());

        final ApiError apiError = ApiError.builder().messages(errors).code(HttpStatus.BAD_REQUEST.toString()).build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ApiError {
        private String message;
        private List<String> messages;
        private String code;
    }
}
