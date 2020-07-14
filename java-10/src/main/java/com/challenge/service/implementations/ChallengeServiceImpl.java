package com.challenge.service.implementations;
import java.util.List;
import com.challenge.entity.Challenge;
import com.challenge.repository.ChallengeRepo;
import com.challenge.service.interfaces.ChallengeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ChallengeServiceImpl implements ChallengeServiceInterface {

  @Autowired
  private ChallengeRepo challengeRepo;

  @Override
  public Challenge save(Challenge object) {
    return challengeRepo.save(object);
  }

  @Override
  public List<Challenge> findByAccelerationIdAndUserId(Long accelerationId, Long userId) {
    return challengeRepo.findByAccelerationIdAndUserId(accelerationId, userId);
  }
}