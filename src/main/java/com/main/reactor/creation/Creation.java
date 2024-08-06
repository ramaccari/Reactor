package com.main.reactor.creation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.main.model.Persona;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class Creation {

	private static final Logger log = LoggerFactory.getLogger(Creation.class);
	
	private List<Persona> personas = List.of(
			new Persona(Long.valueOf(1), "Roberto", Integer.valueOf(61)),
			new Persona(Long.valueOf(2), "Olga", Integer.valueOf(58)),
			new Persona(Long.valueOf(3), "Ana Luz", Integer.valueOf(31)),
			new Persona(Long.valueOf(4), "Anabella", Integer.valueOf(27))
			);

	
	public void justFrom() {
		Mono.just(new Persona(Long.valueOf(1), "Mito", Integer.valueOf(29)));

		Flux.fromIterable(personas);
	}
	
	public void empty() {
		Mono.empty();
		Flux.empty();
	}
	
	public void range() {
		Flux.range(5, 5)
			.doOnNext(n -> log.info("[range(5, 5)] Número: " + n))
			.subscribe();
	}
	
	public void repeat() {
		Mono.just(new Persona(Long.valueOf(1), "Mito", Integer.valueOf(29)))
			.repeat(3)
			.subscribe(p -> log.info("[Mono repet(3)]: " + p));

		Flux.fromIterable(personas)
			.repeat(3)
			.subscribe(p -> log.info("[Flux repet(3)]: " + p));
		
	}
}
