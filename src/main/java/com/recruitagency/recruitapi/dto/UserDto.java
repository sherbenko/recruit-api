package com.recruitagency.recruitapi.dto;

import com.recruitagency.recruitapi.model.Role;
import com.recruitagency.recruitapi.model.User;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {
    private Long id;
    private String email;
    private List<Role> roles;
    private Long roleId;

    public UserDto(String email, Long roleId) {
        this.email = email;
        this.roleId = roleId;
    }

    public static UserDto convertEntityToDto(User entity) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(entity, UserDto.class);
    }

    public static User convertDtoToEntity(UserDto dto) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(dto, User.class);
    }
}
