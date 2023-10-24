package com.interview.parshvaproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.interview.parshvaproject.entity.ParshvaProjectEntity;
@Repository
public interface ParshvaRepo extends JpaRepository<ParshvaProjectEntity,Integer> {

}
