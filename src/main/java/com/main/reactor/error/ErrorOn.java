package com.main.reactor.error;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.main.model.Persona;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ErrorOn {

	private static final Logger log = LoggerFactory.getLogger(ErrorOn.class);
	
	private List<Persona> familiares = List.of(
			new Persona(Long.valueOf(1), "Roberto", Integer.valueOf(61)),
			new Persona(Long.valueOf(2), "Olga", Integer.valueOf(58)),
			new Persona(Long.valueOf(3), "Ana Luz", Integer.valueOf(31)),
			new Persona(Long.valueOf(4), "Anabella", Integer.valueOf(27))
			);

	public void retry() {
		Flux.fromIterable(familiares)
			.concatWith(Flux.error(new RuntimeException("EL ERROR SE LANZA AUNQUE REINTENTEMOS")))
			.retry(1)
			.doOnNext(x -> log.info("[retry()]: " + x))
			.subscribe();
	}
	
	public void onErrrorReturn() {
		Flux.fromIterable(familiares)
			.concatWith(Flux.error(new RuntimeException("EL ERROR SE MANEJA CON onErrorReturn")))
			.onErrorReturn(new Persona(Long.valueOf(0), "Default", Integer.valueOf(0))) // generamos un objeto por defecto
			.subscribe(x -> log.info("[omErrorReturn()]: " + x));
	}
	
	public void onErrorResume() {
		Flux.fromIterable(familiares)
			.concatWith(Flux.error(new RuntimeException("EL ERROR SE MANEJA CON onErrorResume")))
			.onErrorResume(e -> Mono.just(new Persona(Long.valueOf(0), "Default", Integer.valueOf(0)))) // generamos un flujo con un objeto popr defcto
			.subscribe(x -> log.info("[omErrorResume()]: " + x));
	}

	public void onErrorMap() {
		Flux.fromIterable(familiares)
			.concatWith(Flux.error(new RuntimeException("SE LANZA UN ERROR PERSONALIZADO CON onErrorMap")))
			.onErrorMap(e -> new InterruptedException(e.getMessage())) // lazamos una excepción personlaizada
			.subscribe(x -> log.info("[omErrorMap()]: " + x));
	}
}
