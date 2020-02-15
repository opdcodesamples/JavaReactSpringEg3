package com.osmand.spring.react.example.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.osmand.spring.react.example.domain.Quote;

public interface QuoteRepository extends ReactiveMongoRepository<Quote, String> {

}
