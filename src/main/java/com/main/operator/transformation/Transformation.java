package com.main.operator.transformation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.main.model.Persona;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Transformation {

	public static final Logger log = LoggerFactory.getLogger(Transformation.class);
	
	public void map() {
		List<Persona> personas = List.of(
				new Persona(Long.valueOf(1), "Roberto", Integer.valueOf(61)),
				new Persona(Long.valueOf(2), "Olga", Integer.valueOf(58)),
				new Persona(Long.valueOf(3), "Ana Luz", Integer.valueOf(31)),
				new Persona(Long.valueOf(4), "Anabella", Integer.valueOf(27))
				);

		Flux.fromIterable(personas)
			.map(p -> {
				p.setEdad(Integer.valueOf(p.getEdad().intValue() + 10));
				return p; // Retorna el flujo transformado
			})
			.subscribe(p -> log.info("[map()]: " + p));
	}
	
	public void flatMap() {
		List<Persona> personas = List.of(
				new Persona(Long.valueOf(1), "Roberto", Integer.valueOf(61)),
				new Persona(Long.valueOf(2), "Olga", Integer.valueOf(58)),
				new Persona(Long.valueOf(3), "Ana Luz", Integer.valueOf(31)),
				new Persona(Long.valueOf(4), "Anabella", Integer.valueOf(27))
				);
		
		Flux.fromIterable(personas)
			.flatMap(p -> {
				p.setEdad(Integer.valueOf(p.getEdad().intValue() + 10));
				return Mono.just(p); // Retorna un nuevo flujo que hay que crear 
			})
			.subscribe(p -> log.info("[flatMap()]: " + p));
	}
	
	public void groupBy() {
		List<Persona> personas = List.of(
				new Persona(Long.valueOf(1), "Roberto", Integer.valueOf(61)),
				new Persona(Long.valueOf(2), "Olga", Integer.valueOf(58)),
				new Persona(Long.valueOf(3), "Ana Luz", Integer.valueOf(31)),
				new Persona(Long.valueOf(4), "Anabella", Integer.valueOf(27)),
				new Persona(Long.valueOf(5), "Franco", Integer.valueOf(31)),
				new Persona(Long.valueOf(6), "Bruno", Integer.valueOf(27))
				);
		
		Flux.fromIterable(personas)
			.groupBy(Persona::getEdad)
			.flatMap(edadFlux -> edadFlux.collectList())
			.subscribe(f -> log.info("[groupBy()]: " + f.toString()));
	}
	
	public void doOnNext() {
		List<Persona> personas = List.of(
				new Persona(Long.valueOf(1), "Roberto", Integer.valueOf(61)),
				new Persona(Long.valueOf(2), "Olga", Integer.valueOf(58)),
				new Persona(Long.valueOf(3), "Ana Luz", Integer.valueOf(31)),
				new Persona(Long.valueOf(4), "Anabella", Integer.valueOf(27))
				);

		Flux.fromIterable(personas)
			.doOnNext(p -> p.setEdad(p.getEdad() + 10))
			.subscribe(p -> log.info("[doOnNext()]: " + p));
	}
	
}
