package com.mybury.bucketlist.auth;

import static com.mybury.bucketlist.auth.TestInfoContants.TEST_USER_ID;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.modifyUris;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mybury.bucketlist.core.util.JwtUtils;
import com.mybury.bucketlist.core.v2.vo.UserRequest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BucketlistControllerTest {
	
	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private ObjectMapper objectMapper;

	private MockMvc mockMvc;
	
	@Rule
	public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
	            .apply(documentationConfiguration(this.restDocumentation))
				.alwaysDo(document("{method-name}",
						preprocessRequest(
								modifyUris()
									.scheme("http")
									.host("www.my-bury.com").removePort(),
								prettyPrint()
						),
						preprocessResponse(prettyPrint())))
				.build();
	}
	
	@Test
	public void testDday() throws Exception {
		String accessToken = jwtUtils.createAccessToken(TEST_USER_ID);

		UserRequest request = new UserRequest();
		request.setUserId(TEST_USER_ID);
		
		this.mockMvc.perform(get("/v2/bucketlist/dday")
				.header("X-Auth-Token", accessToken)
				.characterEncoding("UTF-8")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request))
			)
			.andDo(print())
			.andExpect(status().isOk());
	}
}
