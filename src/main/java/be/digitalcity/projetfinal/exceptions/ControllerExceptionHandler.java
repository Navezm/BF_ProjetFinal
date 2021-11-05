package be.digitalcity.projetfinal.exceptions;

import be.digitalcity.projetfinal.exceptions.models.ExceptionDTO;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice(basePackages = "be.digitalcity.projetfinal.controller")
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * Handling of the not found error (404)
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ExceptionDTO(ex.getMessage()));
    }

    /**
     * Handling of the MisMatchException
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionDTO(ex.getMessage()));
    }

    /**
     * Handling of the RequestMethodNotSupported
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionDTO(ex.getMessage()));
    }

    /**
     * Handling of the IllegalArgumentException
     * @param ex
     * @return
     */
    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ExceptionDTO> handle(IllegalArgumentException ex){
        return ResponseEntity.badRequest()
                .body( new ExceptionDTO(ex.getMessage()) );
    }

    /**
     * Handling of the DataIntegretyViolationException
     * @param ex
     * @return
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionDTO> handle(DataIntegrityViolationException ex){
        return ResponseEntity.badRequest()
                .body(new ExceptionDTO(ex.getMessage()));
    }
}
