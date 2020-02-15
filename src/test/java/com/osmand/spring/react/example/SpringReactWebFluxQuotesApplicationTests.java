package com.osmand.spring.react.example;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.CountDownLatch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.osmand.spring.react.example.domain.Quote;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringReactWebFluxQuotesApplicationTests {
	
	Logger logger = LoggerFactory.getLogger(SpringReactWebFluxQuotesApplicationTests.class);
	
	@Autowired
	private WebTestClient webTestClient;
	
	@Test
	public void contextLoads() {
	}
	
	
	@Test
	public void testFetchQuotes() throws Exception {
		
		logger.info("\n\n in testFetchQuotes .......");
		webTestClient
		.get()
		.uri("/quotes?size=20")
		.accept(MediaType.APPLICATION_JSON)
		.exchange()
		.expectStatus()
		.isOk()
		.expectHeader()
		.contentType(MediaType.APPLICATION_JSON)
		.expectBodyList(Quote.class)
		.hasSize(20)
		.consumeWith(allQuotes -> {
			assertThat(	allQuotes.getResponseBody())
						.allSatisfy(quote -> assertThat(quote.getPrice()).isPositive());
		});
		
	}
	
	@Test
	public void testStreamQuotes() throws Exception {
		
		logger.info("\n\n in testStreamQuotes .......");
		
		int size = 10;
		
		CountDownLatch countDownLatch = new CountDownLatch(size);
		
		webTestClient
		.get()
		.uri("/quotes?size=20")
		.accept(MediaType.APPLICATION_STREAM_JSON)
		.exchange()
		.returnResult(Quote.class)
		.getResponseBody()
		.take(size)
		.subscribe(quote -> {
			assertThat(	quote.getPrice()).isPositive();
			countDownLatch.countDown();
		});
		
		countDownLatch.await();
		
		logger.info("Test complete...");
		
	}
	
	
	
	

}
