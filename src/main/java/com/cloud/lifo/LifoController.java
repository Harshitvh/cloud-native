package com.cloud.lifo;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/lifo")
@ApiOperation(value="Lifo API's for push and pop operations")
public class LifoController {
	
	@Autowired
	LifoService lifoService;
	
	@ApiOperation(value="API Operation to push data to persistent store")
	@PostMapping("/push")
	public LifoEntity push(@RequestBody  @Valid LifoRequest lifoRequest)
	{
		return lifoService.push(lifoRequest);
	}
	
	@ApiOperation(value="API Operation to pop and delete data from persistent store")
	@DeleteMapping("/pop")
	public LifoEntity pop() throws NoDataFoundException
	{
		return lifoService.pop();
	}
}
