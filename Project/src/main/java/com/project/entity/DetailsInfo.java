package com.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "detailsInfo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetailsInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
}
