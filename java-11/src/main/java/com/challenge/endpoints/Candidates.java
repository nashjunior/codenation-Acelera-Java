package com.challenge.endpoints;

import java.util.List;
import java.util.Optional;

import com.challenge.dto.CandidateDTO;
import com.challenge.entity.Candidate;
import com.challenge.mappers.CandidateMapper;
import com.challenge.service.impl.CandidateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/candidate")
@RequiredArgsConstructor
public class Candidates {
  @Autowired
  private CandidateService candidateService;

  @Autowired
  private CandidateMapper candidateMapper;

  @GetMapping("/{userId}/{companyId}/{accelerationId}")
  public ResponseEntity<CandidateDTO> findCandidate(@PathVariable("userId") Long userId,
      @PathVariable("companyId") Long companyId, @PathVariable("accelerationId") Long accelerationId) {
    Optional<Candidate> candidate = this.candidateService.findById(userId, companyId, accelerationId);
    return candidate.isEmpty() ? ResponseEntity.ok(new CandidateDTO())
        : ResponseEntity.ok(this.candidateMapper.map(candidate.get()));
  }

  @GetMapping
  public ResponseEntity<List<CandidateDTO>> findCandidatesByCompanyId(
      @RequestParam(required = true, name = "companyId") Long companyId) {

    return ResponseEntity.ok(this.candidateMapper.map(this.candidateService.findByCompanyId(companyId)));
  }
}