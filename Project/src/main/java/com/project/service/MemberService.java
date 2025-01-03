package com.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.entity.Member;
import com.project.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepo;
	private final PasswordEncoder encoder;

	public List<Member> getMembers() {
		return memberRepo.findAll();
	}

	public Member getMember(String username) {
		return memberRepo.findById(username).get();
	}
	
	// 사용자 확인 메소드
	public Member join(Member member) {
		Optional<Member> opt = memberRepo.findById(member.getUsername());
		if (opt.isPresent()) return null;
		member.setPassword(encoder.encode(member.getPassword())); // password 암호화
		return memberRepo.save(member);
	}
	
	
}