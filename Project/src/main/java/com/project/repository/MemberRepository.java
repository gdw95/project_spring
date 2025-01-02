package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String>{

}
