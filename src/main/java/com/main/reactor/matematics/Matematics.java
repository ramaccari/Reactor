package com.main.reactor.matematics;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.main.model.Persona;
import com.main.reactor.conditionals.Conditional;

import reactor.core.publisher.Flux;

@Service
public class Matematics {

	private static final Logger log = LoggerFactory.getLogger(Conditional.class);
	
	private List<Persona> familiares = List.of(
			new Persona(Long.valueOf(1), "Roberto", Integer.valueOf(61)),
			new Persona(Long.valueOf(2), "Olga", Integer.valueOf(58)),
			new Persona(Long.valueOf(3), "Ana Luz", Integer.valueOf(31)),
			new Persona(Long.valueOf(4), "Anabella", Integer.valueOf(27))
			);
	
	public void average() {
		Flux.fromIterable(familiares)
			.collect(Collectors.averagingInt(Persona::getEdad))
			.subscribe(p -> log.info("[average()]: " + p));
	}

	public void count() {
		Flux.fromIterable(familiares)
			.count()
			.subscribe(p -> log.info("[count()]: " + p));
	}

	public void min() {
		Flux.fromIterable(familiares)
			.collect(Collectors.minBy(Comparator.comparing(Persona::getEdad)))
			.subscribe(p -> log.info("[min()]: " + p.get()));
	}

	public void max() {
		Flux.fromIterable(familiares)
			.collect(Collectors.maxBy(Comparator.comparing(Persona::getEdad)))
			.subscribe(p -> log.info("[max()]: " + p.get()));
	}

	public void sum() {
		Flux.fromIterable(familiares)
			.collect(Collectors.summingInt(Persona::getEdad))
			.subscribe(p -> log.info("[sum()]: " + p));
	}

	public void summarizing() {
		Flux.fromIterable(familiares)
			.collect(Collectors.summarizingInt(Persona::getEdad))
			.subscribe(p -> log.info("[summarizing()]: " + p));
	}

}
