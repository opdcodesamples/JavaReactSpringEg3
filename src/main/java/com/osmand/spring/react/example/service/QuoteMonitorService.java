package com.osmand.spring.react.example.service;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import com.osmand.spring.react.example.client.StockQuoteClient;
import com.osmand.spring.react.example.domain.Quote;
import com.osmand.spring.react.example.repository.QuoteRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class QuoteMonitorService implements ApplicationListener<ContextRefreshedEvent> {
	
	private final StockQuoteClient stockQuoteClient;
	private final QuoteRepository quoteRepository;
	
	public QuoteMonitorService (StockQuoteClient stockQuoteClient, QuoteRepository quoteRepository) {
		this.stockQuoteClient = stockQuoteClient;
		this.quoteRepository = quoteRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		stockQuoteClient.getQuoteStream()
		.log("QuoteMonitorService")
		.subscribe(quote -> {
			
			// NOTE: --------> BLOCKing was not working and threw exceptions. 
			//Mono<Quote> savedQuote = quoteRepository.save(quote);			
			//System.out.println(" ...... savedQuote:  " + savedQuote.block().getId());
			
			// Got this Solution in Tutorial, posted by a student
			 Mono<Quote> savedQuote = quoteRepository.insert(quote);
             savedQuote.subscribe(result ->
                       log.info("I saved a quote! Id :: " + result.getId()));
			
		});
		
	}

}
