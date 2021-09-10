package com.mybury.bucketlist.auth;

import static com.mybury.bucketlist.auth.TestInfoContants.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.modifyUris;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mybury.bucketlist.core.util.JwtUtils;
import com.mybury.bucketlist.core.v2.service.CategoryService;
import com.mybury.bucketlist.core.v2.vo.CategoryPriorityRequest;
import com.mybury.bucketlist.core.v2.vo.CategoryRequest;
import com.mybury.bucketlist.core.v2.vo.UserRequest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryControllerTest {
	
	@Autowired
	private CategoryService categoryService;
	
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
	public void testMy() throws Exception {
		String accessToken = jwtUtils.createAccessToken(TEST_USER_ID);
		
		UserRequest request = new UserRequest();
		request.setUserId(TEST_USER_ID);
		
		this.mockMvc.perform(get("/v2/category/my")
				.header("X-Auth-Token", accessToken)
				.characterEncoding("UTF-8")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request))
			)
			.andDo(print())
			.andExpect(status().isOk());
	}
	
	@Test
	public void testEditPriority() throws Exception {
		String accessToken = jwtUtils.createAccessToken(TEST_USER_ID);
		
		CategoryPriorityRequest request = new CategoryPriorityRequest();
		request.setUserId(TEST_USER_ID);
		
		List<String> categories = Arrays.asList("2c94b0817b33eca6017b33ecb14d0015", "2c94b0817b33eca6017b33ecb1620017");
		request.setCategories(categories);
		
		this.mockMvc.perform(put("/v2/category/edit_priority")
				.header("X-Auth-Token", accessToken)
				.characterEncoding("UTF-8")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request))
			)
			.andDo(print())
			.andExpect(status().isOk());
	}
	
	@Test
	public void testEditName() throws Exception {
		String accessToken = jwtUtils.createAccessToken(TEST_USER_ID);
		
		CategoryRequest request = new CategoryRequest(); 
		request.setUserId(TEST_USER_ID);
		request.setId(TEST_CATEGORY_ID);
		request.setName(TEST_CATEGORY_NAME);
		
		this.mockMvc.perform(put("/v2/category/edit_name")
				.header("X-Auth-Token", accessToken)
				.characterEncoding("UTF-8")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request))
				)
		.andDo(print())
		.andExpect(status().isOk());
	}
	
	@Test
	public void testDelete() throws Exception {
		String accessToken = jwtUtils.createAccessToken(TEST_USER_ID);
		
		CategoryRequest request = new CategoryRequest(); 
		request.setUserId(TEST_USER_ID);
		request.setName(TEST_CATEGORY_NAME);
		request.setId(categoryService.save(request));
		
		this.mockMvc.perform(delete("/v2/category/delete")
				.header("X-Auth-Token", accessToken)
				.characterEncoding("UTF-8")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request))
				)
		.andDo(print())
		.andExpect(status().isOk());
	}
	
	@Test
	public void testSave() throws Exception {
		String accessToken = jwtUtils.createAccessToken(TEST_USER_ID);
		
		CategoryRequest request = new CategoryRequest(); 
		request.setUserId(TEST_USER_ID);
		request.setName(TEST_CATEGORY_NAME);
		
		MvcResult result = this.mockMvc.perform(post("/v2/category/save")
				.header("X-Auth-Token", accessToken)
				.characterEncoding("UTF-8")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request))
				)
		.andDo(print())
		.andExpect(status().isOk())
		.andReturn();
		
		CategoryRequest response = objectMapper.readValue(result.getResponse().getContentAsString(), CategoryRequest.class);
		categoryService.delete(response.getId());
	}
}
