package com.challenge.repository;

import java.util.List;
import com.challenge.entity.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChallengeRepo extends JpaRepository <Challenge, Long> {
  @Query("select distinct cha from Challenge cha " +
  " join cha.accelerations as acc " +
  " join acc.candidates can " +
  " join can.id.user user " +
  "where user.id = ?2 and acc.id = ?1")
  List<Challenge> findByAccelerationIdAndUserId(Long accelerationId, Long userId);
}