package com.recruitagency.recruitapi.controller;

import com.recruitagency.recruitapi.model.Job;
import com.recruitagency.recruitapi.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api")
public class JobController {

  @Autowired
  private JobRepository jobRepository;

  @GetMapping
  public List<Job> getAll() {
    return jobRepository.findAll();
  }
}

