package com.challenge.endpoints;

import java.util.List;
import java.util.Optional;

import com.challenge.entity.Acceleration;
import com.challenge.service.impl.AccelerationService;

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
@RequestMapping("/acceleration")
public class Accelerations {
  @Autowired
  private AccelerationService accelerationService;

  private List<Acceleration> accelerations;

  @GetMapping("/{id}")
  public ResponseEntity<Acceleration> findAcceleration(@PathVariable("id") Long id) {
    Optional<Acceleration> acceleration = this.accelerationService.findById(id);
    return acceleration.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(acceleration.get());
  }

  @GetMapping
  public ResponseEntity<List<Acceleration>> findAccelerationByCompanyId(
      @RequestParam(required = true, name = "companyId") Long companyId) {
    this.accelerations = this.accelerationService.findByCompanyId(companyId);
    return this.accelerations.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(this.accelerations);
  }
}