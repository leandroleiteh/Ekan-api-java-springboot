package com.ekan.dev.api.exceptionhandler;


import com.ekan.dev.common.exceptions.CouldNotDeleteResourceException;
import com.ekan.dev.common.exceptions.ResourceNotFoundException;
import com.ekan.dev.common.exceptions.ResourceWasNotCreatedException;
import com.ekan.dev.common.exceptions.ResourceWasNotUpdatedException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;


    //  método que lida com exception quando um argumento de método anotado com @Valid falha na validação.
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders
            headers, HttpStatusCode status, WebRequest request) {

        List<Field> fields = new ArrayList<>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String name = ((FieldError) error).getField();
            String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            fields.add(new Field(name, message));
        }

        Problem problem = new Problem(
                status.value(),
                OffsetDateTime.now(),
                "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente!",
                fields
        );

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    // método que lida com váriaveis de path ausentes ex:/users/{idUser}
    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex,
                                                               HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Problem problem = new Problem(
                status.value(),
                OffsetDateTime.now(),
                "Faltando variável de caminho obrigatória: " + ex.getVariableName(),
                null
        );

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @ExceptionHandler(ResourceWasNotCreatedException.class)
    public ResponseEntity<Object> handleResourceWasNotCreatedException(ResourceWasNotCreatedException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        Problem problem = new Problem(
                status.value(),
                OffsetDateTime.now(),
                ex.getMessage(),
                null
        );
        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        Problem problem = new Problem(
                status.value(),
                OffsetDateTime.now(),
                ex.getMessage(),
                null
        );
        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(ResourceWasNotUpdatedException.class)
    public ResponseEntity<Object> handleResourceWasNotUpdatedException(ResourceWasNotUpdatedException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        Problem problem = new Problem(
                status.value(),
                OffsetDateTime.now(),
                ex.getMessage(),
                null
        );
        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(CouldNotDeleteResourceException.class)
    public ResponseEntity<Object> handleCouldNotDeleteResourceException(CouldNotDeleteResourceException ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        Problem problem = new Problem(
                status.value(),
                OffsetDateTime.now(),
                ex.getMessage(),
                null
        );
        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }
}