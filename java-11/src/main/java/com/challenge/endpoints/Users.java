package com.challenge.endpoints;

import java.util.List;
import java.util.Optional;

import com.challenge.entity.User;
import com.challenge.service.impl.UserService;

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
@RequestMapping("/user")
public class Users {
  @Autowired
  private UserService userService;

  @GetMapping("/{id}")
  public ResponseEntity<User> findUser(@PathVariable("id") Long id) {
    Optional<User> user = this.userService.findById(id);
    return user.isPresent() ? ResponseEntity.ok(user.get()) : ResponseEntity.notFound().build();
  }

  @GetMapping
  public ResponseEntity<List<User>> findUsersByAccelerationOrCompany(
      @RequestParam(required = false, name = "accelerationName") Optional<String> acceleration,
      @RequestParam(required = false, name = "companyId") Optional<Long> company) {
    List<User> users;

    if (acceleration.isEmpty() && company.isEmpty()) {
      return ResponseEntity.badRequest().build();
    } else if (acceleration.isPresent()) {
      users = this.userService.findByAccelerationName(acceleration.get());
      return users.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(users);
    }
    users = this.userService.findByCompanyId(company.get());
    return users.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(users);
  }

}