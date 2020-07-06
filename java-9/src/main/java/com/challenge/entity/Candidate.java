package com.challenge.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Candidate {
  
  @EmbeddedId
  private CandidateId candidateId;

  @Column
  @NotNull
  private Integer status;

  @CreatedDate
  @Column(name = "created_at")
  private Date createdAt;
}