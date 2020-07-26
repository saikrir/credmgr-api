package rao.saikrishna.apps.credmgr.api.advice;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import rao.saikrishna.apps.credmgr.api.exceptions.CredentialManagerRecordNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, List<String>> validationException(MethodArgumentNotValidException ex) {

        Map<String, List<String>> map = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            List<String> errorMessages = null;
            if (!map.containsKey(fieldError.getField())) {
                map.put(fieldError.getField(), new ArrayList<>());
            }
            map.get(fieldError.getField()).add(fieldError.getCode());
        }

        return map;
    }

    @ExceptionHandler(value = {CredentialManagerRecordNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> validationException(CredentialManagerRecordNotFoundException credentialManagerRecordNotFoundException) {
        Map<String, String> map = new HashMap<>();
        map.put("error", credentialManagerRecordNotFoundException.getMessage());
        return map;
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> validationException(Exception genericException) {
        Map<String, String> map = new HashMap<>();
        map.put("error", genericException.getMessage());
        return map;
    }
}
