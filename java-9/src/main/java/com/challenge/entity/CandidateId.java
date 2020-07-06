package com.challenge.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;


@Embeddable
public class CandidateId implements Serializable {
  @ManyToOne
  private User user;

  @ManyToOne
  private Acceleration acceleration;

  @ManyToOne
  private Company company;
  
}