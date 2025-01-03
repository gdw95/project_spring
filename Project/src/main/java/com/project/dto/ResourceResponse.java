package com.project.dto;

import java.util.List;

import com.project.entity.Resource;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ResourceResponse {
    private String resultCode;
    private String resultMsg;
    private int resultCount;
    private List<Resource> data;  // data 필드는 여러 자원 정보이므로 List로 처리
}
