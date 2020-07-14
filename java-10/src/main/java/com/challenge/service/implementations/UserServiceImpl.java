package com.challenge.service.implementations;

import java.util.List;
import java.util.Optional;
import com.challenge.entity.User;
import com.challenge.repository.UserRepo;
import com.challenge.service.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserServiceInterface {

  @Autowired
  private UserRepo userRepo;

  @Override
  public User save(User object) {
    return userRepo.save(object);
  }

  @Override
  public Optional<User> findById(Long userId) {
    return userRepo.findById(userId);
  }

  @Override
  public List<User> findByAccelerationName(String name) {
    return userRepo.findByCandidatesIdAccelerationName(name);
  }

  @Override
  public List<User> findByCompanyId(Long companyId) {
    return userRepo.findByCandidatesIdCompanyId(companyId);
  }
}