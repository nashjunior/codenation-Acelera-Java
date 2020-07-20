package com.challenge.endpoints;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.challenge.entity.Company;
import com.challenge.service.impl.CompanyService;

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
@RequestMapping("/company")
public class Companies {
  @Autowired
  private CompanyService companyService;

  private List<Company> companiesFounded;

  @GetMapping("/{id}")
  public ResponseEntity<Company> findCompany(@PathVariable("id") Long id) {
    Optional<Company> company = this.companyService.findById(id);
    return company.isPresent() ? ResponseEntity.ok(company.get()) : ResponseEntity.notFound().build();
  }

  @GetMapping
  public ResponseEntity<List<Company>> findAllCompanyByAccelerationIdOrUserId(
      @RequestParam(name = "accelerationId", required = false) Optional<Long> accelerationId,
      @RequestParam(name = "userId", required = false) Optional<Long> userId) {
    return accelerationId.map(aLong -> ResponseEntity.ok(this.companyService.findByAccelerationId(aLong)))
        .orElseGet(() -> userId.map(aLong -> ResponseEntity.ok(this.companyService.findByUserId(aLong)))
            .orElseGet(() -> ResponseEntity.ok(Collections.EMPTY_LIST)));
  }
}