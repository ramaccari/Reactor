package com.main.reactor.combination;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.main.model.Persona;
import com.main.model.Venta;

import reactor.core.publisher.Flux;

@Service
public class Combination {

	private static final Logger log = LoggerFactory.getLogger(Combination.class);
	
	private List<Persona> familiares = List.of(
			new Persona(Long.valueOf(1), "Roberto", Integer.valueOf(61)),
			new Persona(Long.valueOf(2), "Olga", Integer.valueOf(58)),
			new Persona(Long.valueOf(3), "Ana Luz", Integer.valueOf(31)),
			new Persona(Long.valueOf(4), "Anabella", Integer.valueOf(27))
			);

	private List<Persona> amigos = List.of(
			new Persona(Long.valueOf(5), "Memo", Integer.valueOf(61)),
			new Persona(Long.valueOf(6), "Gustavo", Integer.valueOf(58)),
			new Persona(Long.valueOf(7), "Jorge", Integer.valueOf(59))
			);
	
	private List<Venta> ventas = List.of(
			new Venta(Long.valueOf(1), LocalDateTime.now()),
			new Venta(Long.valueOf(2), LocalDateTime.now().plusDays(1))
			);
			

	public void merge() {
		Flux<Persona> fxf = Flux.fromIterable(familiares);

		Flux<Persona> fxa = Flux.fromIterable(amigos);
		
		Flux<Venta> fxv = Flux.fromIterable(ventas);

		Flux.merge(fxf, fxa, fxv, fxf)
			.subscribe(p -> log.info("[merge()]: " + p));
	}
	
	public void mergeWith() {
		Flux<Persona> fxf = Flux.fromIterable(familiares);

		Flux<Persona> fxa = Flux.fromIterable(amigos);
		
		fxf.mergeWith(fxa)
			.subscribe(p -> log.info("[mergeWith()]: " + p));
	}
	
	public void zip() {
		Flux<Persona> fxf = Flux.fromIterable(familiares);

		Flux<Venta> fxv = Flux.fromIterable(ventas);
		
		Flux.zip(fxf, fxv, (p, v) -> String.format("Flux 1: %s Flux 2: %s", p, v))
			.subscribe(x -> log.info("[zip()]: " + x));
	}
	
	public void zipWith() {
		Flux<Persona> fxa = Flux.fromIterable(amigos);

		Flux<Venta> fxv = Flux.fromIterable(ventas);
		
		fxa.zipWith(fxv, (p, v) -> String.format("Flux 1: %s Flux 2: %s", p, v))
			.subscribe(x -> log.info("[zipWith()]: " + x));
	}

}
