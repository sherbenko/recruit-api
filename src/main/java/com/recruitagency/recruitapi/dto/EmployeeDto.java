package com.recruitagency.recruitapi.dto;

import com.recruitagency.recruitapi.model.Employee;
import com.recruitagency.recruitapi.model.EmployeeJob;
import com.recruitagency.recruitapi.model.User;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.persistence.ElementCollection;
import java.util.Date;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDto {
  private Long id;
  private String imageUrl;
  private String firstName;
  private String lastName;
  private String street;
  private String city;

  private String phoneNumber;
  private String gender;
  /*skills*/
  private String nativeLanguage;
  private String nativeLanguageLevel;
  private String foreignLanguage;
  private String foreignLanguageLevel;

  private List<String> skills;
  /*employment preferences*/
  private String idealLocation;
  private String minCompensation;
  private String willingToTravel;
  private Date availabilityDate;
  private Boolean willingToRelocate;
  private List<String> employmentTypes;

  private User user;
  private List<EmployeeJob> jobs;
  public static EmployeeDto convertEntityToDto(Employee entity) {
    ModelMapper mapper = new ModelMapper();
    return mapper.map(entity, EmployeeDto.class);
  }

  public static Employee convertDtoToEntity(EmployeeDto dto) {
    ModelMapper mapper = new ModelMapper();
    return mapper.map(dto, Employee.class);
  }
}
