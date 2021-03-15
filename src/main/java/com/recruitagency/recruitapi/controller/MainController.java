package com.recruitagency.recruitapi.controller;

import com.recruitagency.recruitapi.dto.ApiResult;
import com.recruitagency.recruitapi.dto.JobPaginationResponse;
import com.recruitagency.recruitapi.dto.TestUserDto;
import com.recruitagency.recruitapi.model.*;
import com.recruitagency.recruitapi.repository.*;
import com.recruitagency.recruitapi.service.impl.JobServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api")
public class MainController {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private RoleRepository roleRepository;
  @Autowired
  private JobRepository jobRepository;
  @Autowired
  private OrganizationRepository organizationRepository;
  @Autowired
  private EmployeeRepository employeeRepository;
  @Autowired
  private EmployeeJobRepository employeeJobRepository;
  @Autowired
  private JobServiceImpl jobService;

  @GetMapping("/test")
  public ApiResult getTest(@RequestBody TestUserDto test){
    return test.comparePassword(test.getNewPassword(), test.getConfirmPassword());
  }

  @GetMapping("/user")
  public String getUser() {
    User user1 = new User("ILya");
    User user2 = new User("Ruslan");
    Role roleAdmin = new Role("ADMIN");
    Role roleUser = new Role("USER");
    roleRepository.save(roleAdmin);
    roleRepository.save(roleUser);


    user1.setRoles(Collections.singletonList(roleAdmin));
    user2.setRoles(Collections.singletonList(roleUser));

    userRepository.save(user1);
    userRepository.save(user2);

    Organization organization = new Organization();
    organization.setUser(user2);

    Job devJob = new Job("Developer");
    Job uiJob = new Job("UI/UX");

    organization.setJobs(Arrays.asList(devJob, uiJob));

    organizationRepository.save(organization);


    devJob.setOrganization(organization);
    jobRepository.save(devJob);
    uiJob.setOrganization(organization);
    jobRepository.save(uiJob);




    /*create employee*/
    Employee empl = new Employee("Employee");

    EmployeeJob employeeJob1 = new EmployeeJob();

    employeeJob1.setEmployee(empl);
    /*set By default*/
    employeeJob1.setStatus("In review");
    employeeJob1.setJob(devJob);

    EmployeeJob employeeJob2 = new EmployeeJob();

    employeeJob2.setEmployee(empl);
    /*set By default*/
    employeeJob2.setStatus("In review");
    employeeJob2.setJob(uiJob);

    empl.setUser(user1);
    empl.setJobs(Arrays.asList(employeeJob1, employeeJob2));

    employeeRepository.save(empl);


    employeeJobRepository.save(employeeJob1);
    employeeJobRepository.save(employeeJob2);


    System.out.println(empl.getJobs());
    return "OK ";
  }

  @GetMapping("/jobs")
  public JobPaginationResponse searchJob(
//
      @RequestParam(required = false) String keyword,
      @RequestParam(required = false) String location,
      @RequestParam(required = false,defaultValue = "0") int currentIndex,
      @RequestParam(required = false) String city,
      @RequestParam(required = false) String country,
      @RequestParam(required = false) String organization) {


    return jobService.getJobs(keyword,location,city,country,organization,currentIndex);
  }

}
