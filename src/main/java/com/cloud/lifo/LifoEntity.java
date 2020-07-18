package com.cloud.lifo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="lifo")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LifoEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String createdTime;
	@NotNull
	private String data;

}
