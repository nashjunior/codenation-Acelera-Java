package com.challenge.service.implementations;
import java.util.List;
import java.util.Optional;
import com.challenge.entity.Acceleration;
import com.challenge.repository.AccelerationRepo;
import com.challenge.service.interfaces.AccelerationServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccelerationServiceImpl implements AccelerationServiceInterface {
  @Autowired
  private AccelerationRepo accelerationRepo;

  @Override
  public Acceleration save(Acceleration object) {
    return accelerationRepo.save(object);
  }

  @Override
  public Optional<Acceleration> findById(Long id) {
    return accelerationRepo.findById(id);
  }

  @Override
  public List<Acceleration> findByCompanyId(Long companyId) {
    return accelerationRepo.findByCandidatesIdCompanyId(companyId);
  }
}

