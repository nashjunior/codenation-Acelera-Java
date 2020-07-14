package com.challenge.repository;

import java.util.List;
import com.challenge.entity.Acceleration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccelerationRepo extends JpaRepository <Acceleration, Long>{
	List<Acceleration> findByCandidatesIdCompanyId(Long companyId);
}