package com.challenge.endpoints;

import java.util.List;

import com.challenge.dto.SubmissionDTO;
import com.challenge.mappers.SubmissionMapper;
import com.challenge.service.impl.SubmissionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/submission")
public class Submissions {
  @Autowired
  private SubmissionService submissionService;

  @Autowired
  private SubmissionMapper submissionMapper;

  private List<SubmissionDTO> submissions;

  @GetMapping
  public ResponseEntity<List<SubmissionDTO>> findSubmission(
      @RequestParam(required = true, name = "challengeId") Long challengeId,
      @RequestParam(required = true, name = "accelerationId") Long accelerationId

  ) {
    this.submissions = this.submissionMapper
        .map(this.submissionService.findByChallengeIdAndAccelerationId(challengeId, accelerationId));
    return this.submissions.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(this.submissions);
  }
}