package com.osmand.spring.react.example.service;

import java.time.Duration;

import com.osmand.spring.react.example.domain.Quote;

import reactor.core.publisher.Flux;

public interface QuoteGeneratorService {
	
	Flux<Quote> fetchQuotesStream(Duration period);

}
