package io.rapidtogo.rapidtogo.exception;

import io.rapidtogo.rapidtogo.customer.review.exception.ReviewNotUpdatableException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Slf4j
public class ExceptionHandling {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<Void> handleEntityNotFoundException(EntityNotFoundException e) {

    log.error("EntityNotFoundException: {}", e.getMessage());

    return ResponseEntity.status(HttpStatus.NO_CONTENT)
        .header("X-Error-Message", "EntityNotFoundException: " + e.getMessage()).build();
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Void> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException e, WebRequest request) {

    List<String> errors = e.getAllErrors().stream()
        .map(DefaultMessageSourceResolvable::getDefaultMessage).toList();

    log.error("MethodArgumentNotValidException: {}", errors);

    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .header("X-Error-Message", "MethodArgumentNotValidException: " + errors).build();
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Void> handleException(Exception e) {

    log.error("Exception: {}", (Object) e.getStackTrace());

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .header("X-Error-Message", "Exception: " + e.getMessage()).build();
  }

  @ExceptionHandler(NullPointerException.class)
  public ResponseEntity<Void> handleNullPointerException(NullPointerException e) {

    log.error("NullPointerException: {}", (Object) e.getStackTrace());

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .header("X-Error-Message", "NullPointerException: " + e.getMessage()).build();
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<Void> handleIllegalArgumentException(IllegalArgumentException e) {

    log.error("IllegalArgumentException: {}", e.getMessage());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .header("X-Error-Message", "IllegalArgumentException: " + e.getMessage()).build();
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<Void> handleConstraintViolationException(ConstraintViolationException e) {

    String errorMessages = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
        .collect(Collectors.joining("; "));

    log.error("ConstraintViolationException: {}", errorMessages);

    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .header("X-Error-Message", "ConstraintViolationException: " + errorMessages).build();
  }

  @ExceptionHandler(UniqueNameException.class)
  public ResponseEntity<Void> handleUniqueNameException(UniqueNameException e) {

    log.error("UniqueNameException: {}", e.getMessage());

    return ResponseEntity.status(HttpStatus.CONFLICT)
        .header("X-Error-Message", "UniqueNameException: " + e.getMessage()).build();
  }

  @ExceptionHandler(ReviewNotUpdatableException.class)
  public ResponseEntity<Void> handleReviewNotUpdatableException(ReviewNotUpdatableException e) {

    log.error("ReviewNotUpdatableException: {}", e.getMessage());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .header("X-Error-Message", "ReviewNotUpdatableException: " + e.getMessage()).build();
  }
}