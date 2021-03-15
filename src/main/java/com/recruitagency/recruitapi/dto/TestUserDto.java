package com.recruitagency.recruitapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.Collections;


@Getter
@Setter
public class TestUserDto {


  private String newPassword;

  //PasswordValidator
  private String confirmPassword;

  public TestUserDto(String newPassword, String confirmPassword) {
    this.newPassword = newPassword;
    this.confirmPassword = confirmPassword;
  }

  public TestUserDto() {
  }

  public ApiResult comparePassword (String newPassword,String confirmPassword){
     return (newPassword.equals(confirmPassword)) ?
         new ApiResult(true)
         :
         new ApiResult(false, Collections.singletonList(new ApiError("400", "Passwort wird benötigt")));

  }

  @Override
  public String toString() {
    return "TestUserDto{" +
        "newPassword='" + newPassword + '\'' +
        ", confirmPassword='" + confirmPassword + '\'' +
        '}';
  }
}
