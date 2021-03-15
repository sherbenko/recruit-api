package com.recruitagency.recruitapi.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "employees")
public class Employee {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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
  @ElementCollection
  private List<String> skills;
  /*employment preferences*/
  private String idealLocation;
  private String minCompensation;
  private String willingToTravel;
  private Date availabilityDate;
  private Boolean willingToRelocate;
  @ElementCollection
  private List<String> employmentTypes;


  @OneToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;

  @OneToMany(mappedBy = "employee")
  private List<EmployeeJob> jobs;

  public Employee(String firstName) {
    this.firstName = firstName;
  }
}
