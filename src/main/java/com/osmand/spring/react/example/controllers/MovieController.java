package com.osmand.spring.react.example.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.osmand.spring.react.example.domain.Quote;
import com.osmand.spring.react.example.service.QuoteGeneratorService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/movies")
public class MovieController {
	
	private final QuoteGeneratorService movieService;
	
	public MovieController(QuoteGeneratorService movieService) {
		this.movieService = movieService;
	}
	
	
	
	@GetMapping(value = "/{id}")
	Mono<Quote> getMovieById(@PathVariable String id){
		return null;
	}
	
	@GetMapping
	Flux<Quote> getAllMovies(){
		return null;
	}

}
