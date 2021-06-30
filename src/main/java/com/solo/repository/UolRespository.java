package com.solo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.solo.domain.model.Plantao;

@Repository
public interface UolRespository extends JpaRepository<Plantao, Long> {

	List<Plantao> findByOrderByDataPlantaoAsc();

}

