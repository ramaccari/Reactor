package com.main.operator.filtration;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.main.model.Persona;

import reactor.core.publisher.Flux;

public class Filtration {

	public static final Logger log = LoggerFactory.getLogger(Filtration.class);
	
	public void filter() {
		List<Persona> personas = List.of(
				new Persona(Long.valueOf(1), "Roberto", Integer.valueOf(61)),
				new Persona(Long.valueOf(2), "Olga", Integer.valueOf(58)),
				new Persona(Long.valueOf(3), "Ana Luz", Integer.valueOf(31)),
				new Persona(Long.valueOf(4), "Anabella", Integer.valueOf(27))
				);

		Flux.fromIterable(personas)
			.filter(p -> p.getEdad() >= 50)
			.subscribe(p -> log.info("[filter()]: " + p));
	}

	public void distinct() {
		List<Persona> personas = List.of(
				new Persona(Long.valueOf(1), "Roberto", Integer.valueOf(61)),
				new Persona(Long.valueOf(2), "Olga", Integer.valueOf(58)),
				new Persona(Long.valueOf(1), "Ana Luz", Integer.valueOf(31)), // id duplicado 
				new Persona(Long.valueOf(2), "Anabella", Integer.valueOf(27)) // id duplicado
				);

		Flux.fromIterable(personas)
			.distinct()
			.subscribe(p -> log.info("[distinct()]: " + p));
	}

	public void take() {
		List<Persona> personas = List.of(
				new Persona(Long.valueOf(1), "Roberto", Integer.valueOf(61)),
				new Persona(Long.valueOf(2), "Olga", Integer.valueOf(58)),
				new Persona(Long.valueOf(3), "Ana Luz", Integer.valueOf(31)),
				new Persona(Long.valueOf(4), "Anabella", Integer.valueOf(27))
				);

		Flux.fromIterable(personas)
			.take(3)
			.subscribe(p -> log.info("[take(3)]: " + p));
	}

	public void takeLast() {
		List<Persona> personas = List.of(
				new Persona(Long.valueOf(1), "Roberto", Integer.valueOf(61)),
				new Persona(Long.valueOf(2), "Olga", Integer.valueOf(58)),
				new Persona(Long.valueOf(3), "Ana Luz", Integer.valueOf(31)),
				new Persona(Long.valueOf(4), "Anabella", Integer.valueOf(27))
				);

		Flux.fromIterable(personas)
			.takeLast(1)
			.subscribe(p -> log.info("[takeLast(1)]: " + p));
	}

	public void skip() {
		List<Persona> personas = List.of(
				new Persona(Long.valueOf(1), "Roberto", Integer.valueOf(61)),
				new Persona(Long.valueOf(2), "Olga", Integer.valueOf(58)),
				new Persona(Long.valueOf(3), "Ana Luz", Integer.valueOf(31)),
				new Persona(Long.valueOf(4), "Anabella", Integer.valueOf(27))
				);

		Flux.fromIterable(personas)
			.skip(2)
			.subscribe(p -> log.info("[skip(2)]: " + p));
	}

	public void skipLast() {
		List<Persona> personas = List.of(
				new Persona(Long.valueOf(1), "Roberto", Integer.valueOf(61)),
				new Persona(Long.valueOf(2), "Olga", Integer.valueOf(58)),
				new Persona(Long.valueOf(3), "Ana Luz", Integer.valueOf(31)),
				new Persona(Long.valueOf(4), "Anabella", Integer.valueOf(27))
				);

		Flux.fromIterable(personas)
			.skipLast(1)
			.subscribe(p -> log.info("[skipLast(1)]: " + p));
	}

}
