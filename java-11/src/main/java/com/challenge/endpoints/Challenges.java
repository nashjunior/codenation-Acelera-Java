package com.challenge.endpoints;

import java.util.List;
import java.util.Optional;

import com.challenge.entity.Challenge;
import com.challenge.service.impl.ChallengeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/challenge")
public class Challenges {
  @Autowired
  private ChallengeService challengeService;

  @GetMapping
  public ResponseEntity<List<Challenge>> findById(
      @RequestParam(required = true, name = "accelerationId") Long accelerationId,
      @RequestParam(required = true, name = "userId") Long userId) {
    List<Challenge> challenge = this.challengeService.findByAccelerationIdAndUserId(accelerationId, userId);
    return challenge.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(challenge);
  }
}