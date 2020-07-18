package com.cloud.lifo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LifoRepository extends JpaRepository<LifoEntity,Integer> {
	Optional<LifoEntity> findTop1ByOrderByIdDesc();
}
