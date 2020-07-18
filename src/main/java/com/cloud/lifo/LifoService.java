package com.cloud.lifo;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LifoService {
	
	@Autowired
	LifoRepository lifoRepository;
	public LifoEntity push(LifoRequest request)
	{
		LifoEntity lifoEntity = LifoEntity.builder().data(request.getData()).createdTime(LocalDateTime.now().toString()).build();
		return lifoRepository.save(lifoEntity);
	}
	public LifoEntity pop() throws NoDataFoundException
	{
		Optional<LifoEntity> retrievedEntity = lifoRepository.findTop1ByOrderByIdDesc(); 
		if(retrievedEntity.isPresent())
		{
			lifoRepository.deleteById(retrievedEntity.get().getId());
			return retrievedEntity.get();
		}
		else
			throw new NoDataFoundException("No Data Found In The Persistent Store, Please push some data to pop");
	}
	

}
