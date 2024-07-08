package com.github.david0.streamedheaderproblem;

import java.time.Duration;
import java.util.stream.IntStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping
public class FooController {
	public record Identifier(int i) {
	}


	@PostMapping("/foo")
	public Flux<Identifier> foo() throws Exception {

		return Flux.fromStream(IntStream.range(0, 1)
						.mapToObj(Identifier::new))	;
				//.zipWith(Flux.interval(Duration.ofMillis(5)), (item, time) -> item);
	}
}
