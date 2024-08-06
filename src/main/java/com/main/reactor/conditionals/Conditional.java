package com.main.reactor.conditionals;

import java.time.Duration;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.main.model.Persona;

import reactor.core.publisher.Flux;

@Service
public class Conditional {

	private static final Logger log = LoggerFactory.getLogger(Conditional.class);
	
	private List<Persona> familiares = List.of(
			new Persona(Long.valueOf(1), "Roberto", Integer.valueOf(61)),
			new Persona(Long.valueOf(2), "Olga", Integer.valueOf(58)),
			new Persona(Long.valueOf(3), "Ana Luz", Integer.valueOf(31)),
			new Persona(Long.valueOf(4), "Anabella", Integer.valueOf(27))
			);
	
	public void defaultIfEmpty() {
		Flux.empty()
			.defaultIfEmpty(new Persona(Long.valueOf(0), "Default", Integer.valueOf(0)))
			.subscribe(p -> log.info("[defaultIfEmpty()] Si no hay valores: " + p));
		
		Flux.fromIterable(familiares)
			.defaultIfEmpty(new Persona(Long.valueOf(0), "Default", Integer.valueOf(0)))
			.subscribe(p -> log.info("[defaultIfEmpty()] Cuando hay valores: " + p));
	}
	
	public void takeUntil() {
		Flux.fromIterable(familiares)
			.takeUntil(p -> p.getEdad() < 50)
			.subscribe(p -> log.info("[takeUntil()]: " + p));
	}

	public void timeOut() throws InterruptedException {
		Flux.fromIterable(familiares)
			.delayElements(Duration.ofSeconds(2))
			.timeout(Duration.ofSeconds(1))
			.onErrorReturn(new Persona(Long.valueOf(0), "TimeOut", Integer.valueOf(0))) // generamos un objeto por defecto
			.subscribe(p -> log.info("[timeOut()]: " + p));
		
		Thread.sleep(3000);
	}

}
