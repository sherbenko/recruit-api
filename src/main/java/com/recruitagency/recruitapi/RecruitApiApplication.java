package com.recruitagency.recruitapi;

import com.recruitagency.recruitapi.model.Role;
import com.recruitagency.recruitapi.model.User;
import com.recruitagency.recruitapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class RecruitApiApplication{

    public static void main(String[] args) {
        SpringApplication.run(RecruitApiApplication.class, args);

    }


}
