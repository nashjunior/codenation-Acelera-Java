package com.challenge.entity;

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
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @OneToMany(
        mappedBy = "user",
        cascade = CascadeType.PERSIST,
        fetch = FetchType.LAZY
    )
  private Set<Candidate> accelerations;

  @OneToMany(
        mappedBy = "user",
        cascade = CascadeType.PERSIST,
        fetch = FetchType.LAZY
    )
  private Set<Submission> submissions;

  @Column(name = "full_name",length = 100)
  @NotNull
  @Size(max = 100)
  private String fullName;

  @Column(name = "email", length = 100)
  @NotNull
  @Size(max = 100)
  @Email
  private String email;

  @Column(name = "nickname",length = 50)
  @NotNull
  @Size(max = 50)
  private String nickname;
  
  @Column(name = "password",length = 255)
  @NotNull
  @Size(max = 255)
  private String password;

  @CreatedDate
  @Column(name = "created_at")
  private Date createdAt;


}