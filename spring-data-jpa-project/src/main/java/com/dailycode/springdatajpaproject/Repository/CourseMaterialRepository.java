package com.dailycode.springdatajpaproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dailycode.springdatajpaproject.Entity.CourseMaterial;
@Repository
public interface CourseMaterialRepository extends JpaRepository<CourseMaterial, Long> {

}
