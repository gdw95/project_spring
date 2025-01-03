//package com.project.controller;
//
//import java.util.List;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.project.dto.ResourceResponse;
//import com.project.entity.Resource;
//import com.project.service.DataService;
//
//import lombok.Getter;
//import lombok.Setter;
//
//@Getter @Setter
//@RestController
//@RequestMapping("/api/resources")
//public class ResourceController {
//// 클래스는 공공 API에서 데이터를 조회하여 반환하는 역할, DataService와 Resource 엔티티를 연결하는 중요한 부분
//	
//    private final DataService dataService;
//
//    public ResourceController(DataService dataService) {
//        this.dataService = dataService;
//    }
//    
//    @PostMapping
//    public ResponseEntity<ResourceResponse> getResources(@RequestBody ResourceRequest requestBody) {
//        int pageNo = requestBody.getPageNo();
//        int numOfRows = requestBody.getNumOfRows();
//
//        // 공공데이터 API에서 데이터 조회
//        List<Resource> resourceList = dataService.getResources(pageNo, numOfRows);
//        
//        // 가져온 데이터를 데이터베이스에 저장
//        //dataService.insertTestData(pageNo, numOfRows);
//
//        // ApiResponse에 데이터 설정
//        ResourceResponse apiResponse = new ResourceResponse();
//        apiResponse.setResultCode("OK");
//        apiResponse.setResultMsg("정상");
//        apiResponse.setResultCount(resourceList.size());  // 실제로 조회된 데이터 개수
//        apiResponse.setData(resourceList);  // 데이터 설정
//
//        return ResponseEntity.ok(apiResponse);
//    }
//
//    // RequestBody 클래스를 정의
//    @Getter @Setter
//    public static class ResourceRequest {
//        private int pageNo;
//        private int numOfRows;
//    }
//}
