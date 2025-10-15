package com.example.EmailRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.EmailEntity.EmailEntity;
@Repository
public interface EmailRepository extends JpaRepository<EmailEntity, Long> {
}
