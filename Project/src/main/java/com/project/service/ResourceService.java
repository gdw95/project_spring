package com.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.project.dto.ResourceResponse;
import com.project.entity.Resource;

@Service
public class ResourceService {
	
	private final String apiUrl = "https://www.eshare.go.kr/eshare-openapi/rsrc/list/010500/ca7d733a83dc3d82b8998a10b6a52883";
	
	@Autowired
	private RestTemplate restTemplate;
	
	public ResourceResponse getApiData(int pageNo, int numOfRows) {
		//요청 본문 (body) 데이터 설정
		String requestBody = "{ \"pageNo\" : " + pageNo + ", \"numOfRows\" : " + numOfRows + " }";
		
		//헤더 설정
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		
		HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
		
		try {
            // Post 요청 보내기
            ResponseEntity<ResourceResponse> response = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, ResourceResponse.class);
            ResourceResponse apiResponse = response.getBody();
            
            if (apiResponse != null && apiResponse.getData() != null) {
                // 공공데이터 API에서 반환되는 데이터를 Resource 객체로 변환
                List<Resource> resourceList = new ArrayList<>();
                for (Resource resource : apiResponse.getData()) {
                    
                    resourceList.add(resource);
                }
                
                apiResponse.setData(resourceList);
            }
            
            return apiResponse;
        } catch (RestClientException e) {
            e.printStackTrace();
            return null;
		}
	}

}
