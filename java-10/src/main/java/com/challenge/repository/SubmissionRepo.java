package com.challenge.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import com.challenge.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmissionRepo extends JpaRepository <Submission, Long> {
  @Query(" select max(s.score) from Submission s " +
  " join s.id.challenge cha " +
  " where cha.id = ?1 ")
  Optional<BigDecimal> findHigherScoreByChallengeId(Long challengeId);

  @Query(" select s from Submission s " +
    " join s.id.challenge cha " +
    " join cha.accelerations acc " +
    " where acc.id = ?2 and cha.id = ?1 ")
  List<Submission> findByChallengeIdAndAccelerationId(Long challengeId, Long accelerationId);
}