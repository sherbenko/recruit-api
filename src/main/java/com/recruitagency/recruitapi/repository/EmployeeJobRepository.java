package com.recruitagency.recruitapi.repository;

import com.recruitagency.recruitapi.model.EmployeeJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeJobRepository  extends JpaRepository<EmployeeJob,Long> {
}
