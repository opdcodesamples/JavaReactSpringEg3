package com.osmand.spring.react.example.bootstrap;

import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
import org.springframework.stereotype.Component;

import com.osmand.spring.react.example.client.StockQuoteClient;
import com.osmand.spring.react.example.domain.Quote;
import com.osmand.spring.react.example.repository.QuoteRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;


@Component
@Slf4j
// Can disable this as this will run forever on startup 
// We can enable it for demonstration
public class BootstrapCLR implements CommandLineRunner {
	
	private final StockQuoteClient stockQuoteClient;
	private final QuoteRepository quoteRepository;
	
	public BootstrapCLR(StockQuoteClient stockQuoteClient, QuoteRepository quoteRepository) {
		this.stockQuoteClient = stockQuoteClient;
		this.quoteRepository = quoteRepository;
	}
	

	@Override
	public void run(String... args) throws Exception {
				
		// fetching of quotes is not taken care by QuoteMonotorService, hence commenting below code
		// this is good for demonstration how quotes are fetched via client
		/*
		Flux<Quote> quotesFlux = stockQuoteClient.getQuoteStream();		
		quotesFlux.subscribe(System.out::println);
		*/
		
		Flux<Quote> quotesFlux = quoteRepository.findWithTailableCursonBy();
		Disposable disposable = quotesFlux.subscribe(quote -> {
			log.info("~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=>>>>> " + quote);
		});
		
		disposable.dispose();
		
		
		

	}

}
