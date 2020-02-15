package com.osmand.spring.react.example;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.osmand.spring.react.example.domain.Quote;
import com.osmand.spring.react.example.service.QuoteGeneratorService;
import com.osmand.spring.react.example.service.QuoteGeneratorServiceImpl;

import reactor.core.publisher.Flux;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringReactWebFluxApplicationTests {
	
	Logger logger = LoggerFactory.getLogger(SpringReactWebFluxApplicationTests.class);
	QuoteGeneratorService quoteGeneratorService = new QuoteGeneratorServiceImpl();
	
	//@Test
	public void contextLoads() {
	}
	
	
	//@Test
	public void fetchQuoteStream() throws Exception {
		logger.info("\n\n in fetchQuoteStream .......");
		Flux<Quote> quoteFlux = quoteGeneratorService.fetchQuotesStream(Duration.ofMillis(/* Duration after which each data item should be emitted*/10L));
		
		quoteFlux.take(2).subscribe(n -> System.out.println("this is output .............~~~~~~~~~~~~~~~~~: " + n));
		
		
	}
	
	@Test
	public void fetchQuoteStreamCountDown() throws Exception {
		logger.info("\n\n in fetchQuoteStreamCountDown .......");
		Flux<Quote> quoteFlux = quoteGeneratorService.fetchQuotesStream(Duration.ofMillis(10L));
		
		Consumer<Quote> println = n -> System.out.println("this is output .............~~~~~~~~~~~~~~~~~: " + n);
		Consumer<Throwable> errorHandler = e -> System.out.println("Error/ Excepiton occurred: " + e);
		CountDownLatch countDownLatch = new CountDownLatch(1);
		
		Runnable allDone = () -> countDownLatch.countDown();
		
		quoteFlux.take(1000).subscribe(println, errorHandler, allDone);
		
		countDownLatch.await();
		
	}
	
	
	
	

}
