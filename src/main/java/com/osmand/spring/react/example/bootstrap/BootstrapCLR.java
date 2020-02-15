package com.osmand.spring.react.example.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.osmand.spring.react.example.client.StockQuoteClient;


@Component
public class BootstrapCLR implements CommandLineRunner {
	
	private final StockQuoteClient stockQuoteClient;
	
	public BootstrapCLR(StockQuoteClient stockQuoteClient) {
		this.stockQuoteClient = stockQuoteClient;
	}
	

	@Override
	public void run(String... args) throws Exception {
				
		

	}

}
