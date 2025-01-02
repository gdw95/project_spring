package com.project.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.project.entity.DetailsInfo;
import com.project.entity.Resource;

@RestController
@RequestMapping("/api")
public class DetailsInfoController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	private final String apiUrl = "https://www.eshare.go.kr/eshare-openapi/rsrc/class/list/ca7d733a83dc3d82b8998a10b6a52883";
	
	private final String DetailsInfoApiUrl = "https://DetailsInfoApiUrl.com/resource.details";
	
	@PostMapping("/detailsInfo")
	public ResponseEntity<?> getDetailsInfo(@RequestBody Map<String, Object> requestBody) {
		//첫 번째 api에서 데이터 조회
		List<Resource> resources = getResources(1, 100);
		
		//첫 번째 api 응답에서 rsrcNo 추출
		if (resources.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No resources found");
		}
		
		//첫 번째 api에서 받은 첫 번째 리소스의 rsrcNo추출
		String rsrcNo = resources.get(0).getRsrcNo();
		
		//두 번째 api에서 POST 요청 보내기
		DetailsInfo detailsInfo = getDetailsInfoFromSecondApi(rsrcNo);
	}

}
