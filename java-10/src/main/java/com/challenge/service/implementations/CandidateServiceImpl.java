package com.challenge.service.implementations;
import java.util.List;
import java.util.Optional;
import com.challenge.entity.Candidate;
import com.challenge.entity.CandidateId;
import com.challenge.repository.CandidateRepo;
import com.challenge.service.interfaces.CandidateServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CandidateServiceImpl implements CandidateServiceInterface {

  @Autowired
  private CandidateRepo candidateRepo;

  @Override
  public Candidate save(Candidate object) {
      return candidateRepo.save(object);
  }

  @Override
  public Optional<Candidate> findById(CandidateId id) {
      return candidateRepo.findById(id);
  }

  @Override
  public Optional<Candidate> findById(Long userId, Long companyId, Long accelerationId) {
      return candidateRepo.findByIdUserIdAndIdCompanyIdAndIdAccelerationId(userId, companyId, accelerationId);
  }

  @Override
  public List<Candidate> findByCompanyId(Long companyId) {
      return candidateRepo.findByIdCompanyId(companyId);
  }

  @Override
  public List<Candidate> findByAccelerationId(Long accelerationId) {
      return candidateRepo.findByIdAccelerationId(accelerationId);
  }
}