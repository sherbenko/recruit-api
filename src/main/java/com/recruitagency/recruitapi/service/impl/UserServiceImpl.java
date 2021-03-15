package com.recruitagency.recruitapi.service.impl;

import com.recruitagency.recruitapi.dto.UserDto;
import com.recruitagency.recruitapi.exception.NotFoundException;
import com.recruitagency.recruitapi.model.Role;
import com.recruitagency.recruitapi.model.User;
import com.recruitagency.recruitapi.repository.RoleRepository;
import com.recruitagency.recruitapi.repository.UserRepository;
import com.recruitagency.recruitapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private RoleRepository roleRepository;

  @Override
  public List<UserDto> findAll() {
    return userRepository.findAll()
        .stream()
        .map(UserDto::convertEntityToDto)
        .collect(Collectors.toList());
  }

  @Override
  public User create(UserDto userDto) {
    User user = UserDto.convertDtoToEntity(userDto);
    user.setRoles(Collections.singletonList(roleRepository.findById(userDto.getRoleId()).orElseThrow(() -> new NotFoundException("asd"))));
    return userRepository.save(user);
  }

}
