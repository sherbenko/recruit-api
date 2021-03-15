package com.recruitagency.recruitapi.repository;

import com.recruitagency.recruitapi.model.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job,Long>, JpaSpecificationExecutor<Job> {
//  @Query("SELECT j FROM Job j where LOWER(j.jobName)  member CONCAT('%',:keyWord,'%')"
//      + " or LOWER(j.description) like CONCAT('%',:keyWord,'%')")
//   List<Job> searchBy(@Param("keyWord") String keyWord);

  List<Job> findAllByJobNameContainsAndCountryContains(String keyWord, String location);

//  List<Job> findByJobNameIgnoreCaseContainingAndCityIgnoreCaseContaining(String keyWord);

  List<Job> findByCountryIgnoreCaseContaining(String keyWord);


  @Query("SELECT p FROM Job p WHERE lower(p.jobName) LIKE lower(concat('%', ?1,'%'))"
      + " OR p.description LIKE %?1%"
//      + " OR p.country LIKE %?2%"
//      + " OR p.city LIKE %?3%"
  )
  List<Job> searchByKeyword(String keyword);

  @Query("SELECT p FROM Job p WHERE lower(p.country) LIKE lower(concat('%', ?1,'%'))"
      + " OR p.city LIKE %?1%"
//      + " OR p.country LIKE %?2%"
//      + " OR p.city LIKE %?2%"
  )
  List<Job> searchByLocation(String location);

//  @Query("SELECT p FROM Job p WHERE lower(p.jobName) LIKE lower(concat('%', ?1,'%'))"
//      + " AND lower(p.jobName) LIKE lower(concat('%', ?1,'%'))"
//      + " or lower(p.country) LIKE lower(concat('%', ?2,'%'))"
//      + " AND lower(p.city) LIKE lower(concat('%', ?2,'%'))"
//  )
@Query(value = "SELECT * FROM Job s WHERE MATCH(s.jobName, s.description) AGAINST(?1) AND WHERE MATCH(s.country, s.city) AGAINST(?2)",
    nativeQuery = true)
  List<Job> searchByLocationAndKeyWord(String keywords, String location);

List<Job> findAllByCountryContainingAndCityContainingAndJobNameContaining(String location,String location1,String keyWord);


}