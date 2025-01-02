package com.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "resources")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 자동 증가 ID
    private Long id; // 새로운 고유 ID 필드 추가
    private String rsrcNo;  // 자원 번호
    private String rsrcNm;  // 자원 이름
    private String zip;     // 우편번호
    private String addr;    // 주소
    private String daddr;   // 상세 주소
    private double lot;     // 면적 (제공된 필드)
    private double lat;     // 위도
    private String instUrlAddr;  // 자원 URL
    private String imgFileUrlAddr;  // 이미지 파일 URL
}
