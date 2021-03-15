package com.recruitagency.recruitapi.controller;

import com.recruitagency.recruitapi.dto.UserDto;
import com.recruitagency.recruitapi.model.User;
import com.recruitagency.recruitapi.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
  @Autowired
  private UserServiceImpl userService;

  @GetMapping
  public List<UserDto> getAll() {
    return userService.findAll();
  }

  @PostMapping
  public User createUser(@RequestBody UserDto user) {
    return userService.create(user);
  }

}
