package com.example.ProectPract.Repository;

import com.example.ProectPract.Entity.Information;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InformationRepository extends JpaRepository<Information, Long> {
    <Optional> Integer findByUserId(Long userId);
}
