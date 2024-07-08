package com.github.david0.streamedheaderproblem;

import com.github.david0.streamedheaderproblem.FooController.Identifier;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

class FooControllerTest {

	private final  WebClient webClient = WebClient.builder()
			.clientConnector(new ReactorClientHttpConnector())
			.baseUrl("http://localhost:8080").build();

	@Test
	void test(){
		for(int i=0;i<100000; i++) {
			System.out.println(i);
			webClient.post()
					.uri("/foo")
					.accept(MediaType.APPLICATION_NDJSON)
					.retrieve()
					.bodyToFlux(Identifier.class)
					.collectList().block();
		}
	}
}