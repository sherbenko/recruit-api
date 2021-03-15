package com.recruitagency.recruitapi.service.impl;

import com.recruitagency.recruitapi.dto.JobPaginationResponse;
import com.recruitagency.recruitapi.model.Job;
import com.recruitagency.recruitapi.model.Organization;
import com.recruitagency.recruitapi.repository.JobRepository;
import com.recruitagency.recruitapi.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class JobServiceImpl  implements JobService {
  @Autowired
  private JobRepository jobRepository;
  @Autowired
  private EntityManager entityManager;

//  @Override
//  public List<Job> filterJob(String keyWord) {
//    return jobRepository.searchBy(keyWord);
//  }

  //  @Override
//  public List<Job> findByJobNameIgnoreCaseContainingAndCountryIgnoreCaseContaining(String keyword,String location) {
//    return jobRepository.findByJobNameContainingAndCountryContaining(keyword,location);
//  }
  public JobPaginationResponse getJobs(String keyword, String location, String city, String country, String organization,int currentIndex) {

    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Job> criteriaQuery = criteriaBuilder.createQuery(Job.class);
    Root<Job> root = criteriaQuery.from(Job.class);
  Join<Job, Organization> org = root.join("organization");
//  System.out.println(org);

//  Join<Job, Organization> org = root.join("organizationId");
//  System.out.println(org);
//  String firstName = personSearchRequestModel.getFirstName();
//  String lastName = personSearchRequestModel.getLastName();
//  LocalDate startRangeDateOfBirth = personSearchRequestModel.getStartRangeBirthDate();
//  LocalDate endRangeDateOfBirth = personSearchRequestModel.getEndRangeBirthDate();
//  Long mobile = personSearchRequestModel.getMobile();

    /*
     *  Adding search criteria's for query using CriteriaBuilder
     */
    List<Predicate> searchCriterias = new ArrayList<>();
    System.out.println(location);
    System.out.println(city);
    System.out.println(country);
    System.out.println(organization);
    System.out.println(keyword);
    /*include a in city and country
    *create get with concat method
    * */
  if( (keyword != "") && (keyword != null) ) {
    searchCriterias.add(criteriaBuilder.like(criteriaBuilder.lower(root.<String>get("jobName").as(String.class)), "%" + keyword.toLowerCase(Locale.ROOT) + "%"));
  }
    if ((location != "") && (location != null)) {

//      searchCriterias.add(criteriaBuilder.like(criteriaBuilder.lower(root.<String>get("country").as(String.class) ), "%" + location.toLowerCase(Locale.ROOT) + "%"));
//      searchCriterias.add( criteriaBuilder.like(criteriaBuilder.lower(root.<String>get("country").as(String.class)),keyword);
      Predicate predicateForGradeA
          = criteriaBuilder.like(root.get("country"), "%" + location.toLowerCase(Locale.ROOT) + "%");
      Predicate predicateForGradeB
          = criteriaBuilder.like(root.get("city"), "%" + location.toLowerCase(Locale.ROOT) + "%");
      searchCriterias.add(criteriaBuilder.or(predicateForGradeA, predicateForGradeB));

    }
//    if ((location != "") && (location != null)) {
//      searchCriterias.add(criteriaBuilder.like(criteriaBuilder.lower(root.<String>get("city").as(String.class)), "%" + location.toLowerCase(Locale.ROOT) + "%"));
//    }
    /*city organization country*/
    if ((city != "") && (city != null)) {
      searchCriterias.add(criteriaBuilder.like(criteriaBuilder.lower(root.<String>get("city").as(String.class)), "%" + city.toLowerCase(Locale.ROOT) + "%"));
    }
    if ((country != "") && (country != null)) {
      searchCriterias.add(criteriaBuilder.like(criteriaBuilder.lower(root.<String>get("country").as(String.class)), "%" + country.toLowerCase(Locale.ROOT) + "%"));
    }
    if ((organization != "") && (organization != null)) {
      searchCriterias.add(criteriaBuilder.like(criteriaBuilder.lower(org.<String>get("name").as(String.class)), "%" + organization.toLowerCase(Locale.ROOT) + "%"));
    }



      criteriaQuery.select(root).where(criteriaBuilder.and(searchCriterias.toArray(new Predicate[0])));

int maxRes = 3;
int page=0;

int res = currentIndex * maxRes;
List<Job> resultJobs = entityManager.createQuery(criteriaQuery).setFirstResult(res)
    .setMaxResults(maxRes).getResultList();
    int totalCount = entityManager.createQuery(criteriaQuery).getResultList().size();
    System.out.println("Total count length");
    System.out.println(resultJobs.size());
    int pages = entityManager.createQuery(criteriaQuery).getResultList().size()/maxRes;
    System.out.println(pages);

    return new JobPaginationResponse(resultJobs,pages,currentIndex,totalCount);

  }
}
