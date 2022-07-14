package com.rest.model;

/*
  @author u2cc
 */
import lombok.Data;

@Data
public class LoginDto {
    private String usernameOrEmail;
    private String password;
}
