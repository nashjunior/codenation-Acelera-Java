package com.challenge.service.implementations;

import java.math.BigDecimal;
import java.util.List;
import com.challenge.entity.Submission;
import com.challenge.repository.SubmissionRepo;
import com.challenge.service.interfaces.SubmissionServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SubmissionInterfaceImpl implements SubmissionServiceInterface {

  @Autowired
  private SubmissionRepo submissionRepo;

  @Override
  public Submission save(Submission object) {
    return submissionRepo.save(object);
  }

  @Override
  public BigDecimal findHigherScoreByChallengeId(Long challengeId) {
    return submissionRepo.findHigherScoreByChallengeId(challengeId).orElse(BigDecimal.ZERO);
  }

  @Override
  public List<Submission> findByChallengeIdAndAccelerationId(Long challengeId, Long accelerationId) {
    return submissionRepo.findByChallengeIdAndAccelerationId(challengeId, accelerationId);
  }
}