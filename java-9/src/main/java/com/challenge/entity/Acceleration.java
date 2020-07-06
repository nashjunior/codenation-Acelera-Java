package com.challenge.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Acceleration implements Serializable{
  /**
   *
   */
  private static final long serialVersionUID = 6393751797434769273L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @OneToMany(
        mappedBy = "acceleration",
        cascade = CascadeType.PERSIST,
        fetch = FetchType.LAZY
    )
  private Set<Candidate> accelerations;

  @Column(length = 100, nullable = false)
  @NotNull
  @Size(max = 100)
  private String name;

  @Column(length = 50, nullable = false)
  @NotNull
  @Size(max = 50)
  private String slug;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "challenge_id", referencedColumnName = "id")
  private Challenge challenge;

  @CreatedDate
  @Column(name = "created_at")
  private Date createdAt;
}