package com.recruitagency.recruitapi.service;

import com.recruitagency.recruitapi.dto.UserDto;
import com.recruitagency.recruitapi.model.User;

import java.util.List;

public interface UserService {
  List<UserDto> findAll();

  User create(UserDto userDto);

}
