package com.recruitagency.recruitapi.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ApiResult {

  private List<ApiError> errors;

  private List<String> errorTextArray;

  private boolean isSuccess;

  public ApiResult(boolean success, List<ApiError> errorList) {
    this.isSuccess = success;
    this.errors = errorList == null ? new ArrayList<>() : errorList;
    this.errorTextArray = errors.stream().map(ApiError::getText).collect(Collectors.toList());
  }

  public ApiResult(boolean success) {
    this(success, null);
  }

  public static ApiResult sendSuccess() {
    return new ApiResult(true);
  }

  public static ApiResult sendError(List<ApiError> errorList) {
    return new ApiResult(false, errorList);
  }

  @Override
  public String toString() {
    return "ApiResult{" +
        "errors=" + errors +
        ", errorTextArray=" + errorTextArray +
        ", isSuccess=" + isSuccess +
        '}';
  }
}
