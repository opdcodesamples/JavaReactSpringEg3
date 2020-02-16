package com.osmand.spring.react.example.domain;

import java.math.BigDecimal;
import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;


@Data
@Document
public class Quote {
	
	@Id
	private String id;
	private String ticker;
	private BigDecimal price;
	private Instant instant;
	
}
