package com.cloud.lifo;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LifoServiceTest {
	
	@InjectMocks
	LifoService lifoService;
	
	@Mock
	LifoRepository lifoRepository;
	
	/***
	 * Test case to validate when a valid data is passed as the request to the push method,
	 * lifoRepository.save method is called
	 */
	@Test
	public void test_push()
	{
		LifoRequest lifoRequest = LifoRequest.builder().data("data").build();
		lifoService.push(lifoRequest);
		Mockito.verify(lifoRepository,Mockito.times(1)).save(Mockito.any(LifoEntity.class));
	}
	
	/***
	 * Test case to validate when pop method is called, and if the db call returns a record, it is deleted and returned
	 * @throws NoDataFoundException
	 */
	@Test
	public void test_pop() throws NoDataFoundException
	{
		LifoEntity lifoEntity = LifoEntity.builder().createdTime(LocalDateTime.now().toString()).id(1).data("data").build();
		Mockito.when(lifoRepository.findTop1ByOrderByIdDesc()).thenReturn(Optional.of(lifoEntity));
		LifoEntity lifoEntityResp = lifoService.pop();
		Mockito.verify(lifoRepository,Mockito.times(1)).deleteById(Mockito.eq(1));
		assertEquals("data",lifoEntityResp.getData());
		assertEquals(1,lifoEntityResp.getId());

	}
	
	/***
	 * Test case to validate that a no data found exception is thrown, when pop method is called and the db calls returns no record
	 * @throws NoDataFoundException
	 */
	@Test(expected=NoDataFoundException.class)
	public void test_popWithNoDataFoundException() throws NoDataFoundException
	{
		Mockito.when(lifoRepository.findTop1ByOrderByIdDesc()).thenReturn(Optional.empty());
		lifoService.pop();
	}

}
