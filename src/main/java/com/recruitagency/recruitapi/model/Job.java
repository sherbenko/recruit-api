package com.recruitagency.recruitapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "jobs")
@Getter
@Setter
@NoArgsConstructor
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String jobName;

    private String description;

    private String country;

    private String city;


    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "organization_id", referencedColumnName = "id")
    private Organization organization;


    @OneToMany(mappedBy = "job")
    @JsonIgnore
    private List<EmployeeJob> jobs;


    public Job(String jobName) {
        this.jobName = jobName;
    }


}
