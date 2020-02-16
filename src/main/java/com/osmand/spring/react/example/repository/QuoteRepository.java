package com.osmand.spring.react.example.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.stereotype.Repository;

import com.osmand.spring.react.example.domain.Quote;

import reactor.core.publisher.Flux;

@Repository
public interface QuoteRepository extends ReactiveMongoRepository<Quote, String> {
	
	@Tailable
	Flux<Quote> findWithTailableCursonBy();
}
