package com.educare.mindfulhome.exceptions;


import java.util.Date;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class ErrorDetails {

  private Date timeStamp;
  private String message;
  private String details;

  public ErrorDetails(Date timeStamp, String message, String details) {
    this.timeStamp = timeStamp;
    this.message = message;
    this.details = details;
  }
}
