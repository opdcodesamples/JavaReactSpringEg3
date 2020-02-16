package com.osmand.spring.react.example.client;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.osmand.spring.react.example.domain.Quote;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Component
@ConfigurationProperties("app")
@Setter
@Slf4j
public class StockQuoteClient {
	
	private String host;
	private String port;
	private String path;
	
	public Flux<Quote> getQuoteStream() {
		
		String url = "http://" + host + ":" + port ;
		log.debug("Application URL set to: " + url);
		
		Flux<Quote> flux = 	WebClient
							.builder()
							.baseUrl(url)
							.build()
							.get()
							.uri(path)
							.accept(MediaType.APPLICATION_STREAM_JSON)
							.retrieve()
							.bodyToFlux(Quote.class);
		
		return flux;
	}

}
