package com.wen.rfspringboot.repository;

import com.wen.rfspringboot.pojo.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume, Integer> {
}