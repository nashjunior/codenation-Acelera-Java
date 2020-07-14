package com.challenge.repository;

import java.util.List;
import com.challenge.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
	List<User> findByCandidatesIdCompanyId(Long companyId);
	List<User> findByCandidatesIdAccelerationName(String name);
}