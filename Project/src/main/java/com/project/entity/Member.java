package com.project.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.domain.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Member {
	@Id
	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	private String nickName;
	
	@Builder.Default
	@Enumerated(EnumType.STRING)
	private Role role = Role.ROLE_USER;
	
	@Builder.Default
	private Boolean enabled = true;

	@JsonIgnore
	@ToString.Exclude
	@Builder.Default
	@OneToMany(mappedBy = "member")
	private List<Board> boardList = new ArrayList<>();
	
	public Collection<GrantedAuthority> getAuthorities() {
		return AuthorityUtils.createAuthorityList(role.toString());
	}
}