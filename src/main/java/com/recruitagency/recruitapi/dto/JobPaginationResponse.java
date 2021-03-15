package com.recruitagency.recruitapi.dto;

import com.recruitagency.recruitapi.model.Job;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JobPaginationResponse {
  private List<Job> jobs;
  private int pages;
  private int currentPage;
  private int totalCount;
}
