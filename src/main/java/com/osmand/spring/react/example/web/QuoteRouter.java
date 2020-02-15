package com.osmand.spring.react.example.web;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class QuoteRouter {
	
	@Bean
	public RouterFunction<ServerResponse> route(QuoteHandler handler) {
		return RouterFunctions
				.route(GET("/quotes").and(accept(MediaType.APPLICATION_JSON)), handler::fetchQuotes)
				.andRoute(GET("/quotes").and(accept(MediaType.APPLICATION_STREAM_JSON)), handler::streamQuotes);
	}
}
