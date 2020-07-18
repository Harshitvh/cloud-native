package com.cloud.lifo;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@WebMvcTest(LifoController.class)
public class LifoControllerTest {
	
	@Autowired
	MockMvc mvc;
	
	@MockBean
	LifoService lifoService;
	
	/***
	 * Test case to validate when valid request payload is passed, 200 status is returned
	 * @throws Exception
	 */
	@Test
	public void test_pushEP() throws Exception
	{
		LifoRequest lifoRequest = LifoRequest.builder().data("data").build();
		given(lifoService.push(lifoRequest)).willReturn(LifoEntity.builder().data("data").build());
		mvc.perform(post("/lifo/push")
				.content("{\"data\":\"data\"}")
				.contentType("application/json"))
				.andExpect(status().isOk());
				
				
	}
	
	/***
	 * Test case to validate when an  invalid request payload is passed to push endpoint, 400 status is returned
	 * @throws Exception
	 */
	@Test
	public void test_pushEPWithInvalidRequest() throws Exception
	{
		mvc.perform(post("/lifo/push")
				.content("{\"data\":null}")
				.contentType("application/json"))
				.andExpect(status().isBadRequest());
				
				
	}
	
	/***
	 * Test case to validate the pop endpoint
	 * @throws Exception
	 */
	@Test
	public void test_popEP() throws Exception
	{
		mvc.perform(delete("/lifo/pop")
				.contentType("application/json"))
				.andExpect(status().isOk());
				
				
	}
	

}
