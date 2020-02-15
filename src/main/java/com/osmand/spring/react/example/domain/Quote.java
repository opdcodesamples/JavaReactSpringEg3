package com.osmand.spring.react.example.domain;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.Instant;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Quote {
	
	private static final MathContext MATH_CONTEXT = new MathContext(2);
	private String ticker;
	private BigDecimal price;
	private Instant instant;
	
	public Quote(String ticker, BigDecimal price) {
		this.ticker = ticker;
		this.price = price;
	}
	
	public Quote(String ticker, Double price) {
		this.ticker = ticker;
		this.price = new BigDecimal(price, MATH_CONTEXT);
	}
}
