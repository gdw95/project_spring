package com.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.project.domain.Role;
import com.project.dto.ResourceResponse;
import com.project.entity.Board;
import com.project.entity.DetailsInfo;
import com.project.entity.Member;
import com.project.entity.Resource;
import com.project.repository.BoardRepository;
import com.project.repository.MemberRepository;
import com.project.repository.ResourceRepository;



//외부 API에서 받은 데이터를 Resource 엔티티로 변환하여 DB에 저장
// DB에 저장 후 insertTestData() 주석처리할 것


//@Component
public class InsertTestData implements ApplicationRunner{
	
	
	 private final String apiUrl = "https://www.eshare.go.kr/eshare-openapi/rsrc/list/010500/ca7d733a83dc3d82b8998a10b6a52883";  // 공공 API URL

	@Autowired
	private MemberRepository memRepo;
	@Autowired
	private BoardRepository boardRepo;
	@Autowired
    private ResourceRepository resourceRepository;
		    
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
	private PasswordEncoder encoder;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// 외부 API에서 데이터를 가져옴
    	List<Resource> resources = getResources(1, 100);
    	
        // 가져온 데이터를 데이터베이스에 저장
    	for (Resource resource : resources) {
    		resourceRepository.save(resource); // Resource 엔티티를 DB에 저장
    	}
    	
    	memberInit();
	}
	
	 // 외부 API에서 데이터 조회 (페이징 처리)
    private List<Resource> getResources(int pageNo, int numOfRows) {
        // API 요청 본문 (JSON 형식)
        String requestBody = "{ \"pageNo\" : " + pageNo + ", \"numOfRows\" : " + numOfRows + " }";

        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        // 요청 본문과 헤더를 HttpEntity로 설정
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        // API 호출 (Post 요청)
        ResponseEntity<ResourceResponse> response = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, ResourceResponse.class);

        // 응답에서 데이터 가져오기
        ResourceResponse apiResponse = response.getBody();
        if (apiResponse != null && apiResponse.getData() != null) {
            return apiResponse.getData();  // 데이터를 반환
        } else {
            return List.of();  // 빈 리스트 반환
        }
    }
   
	private void memberInit() throws Exception {
		Member user = Member.builder()
						.username("user")
						.password(encoder.encode("abcd"))
						.role(Role.ROLE_USER)
						.nickName("aa")
						.enabled(true)
						.build();
		memRepo.save(user);

		Member manager = Member.builder()
							.username("manager")
							.password(encoder.encode("abcd"))
							.role(Role.ROLE_MANAGER)
							.nickName("bb")
							.enabled(true)
							.build();
		memRepo.save(manager);
		
		for (int i = 1 ; i <= 10 ; i++) {
			boardRepo.save(Board.builder()
				.title("title" + i)
				.content("content" + i)
				.member(user)
				.build()
			);
		}
		for (int i = 100 ; i <= 105 ; i++) {
			boardRepo.save(Board.builder()
				.title("title" + i)
				.content("content" + i)
				.member(manager)
				.build()
			);
		}	
	}
	

}
