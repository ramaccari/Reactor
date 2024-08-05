package com.main;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.main.model.Persona;
import com.main.operator.creation.Creation;
import com.main.operator.filtration.Filtration;
import com.main.operator.transformation.Transformation;

import io.reactivex.Observable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class ReactorApplication implements CommandLineRunner {
	
	private static final Logger log = LoggerFactory.getLogger(ReactorApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ReactorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		reactor();
		rxjava2();
		mono();
		flux();
		fluxMono();
		
		Creation creation = new Creation();
		creation.range();
		creation.repeat();
		
		Transformation transformation = new Transformation();
		transformation.map();
		transformation.flatMap();
		transformation.groupBy();
		transformation.doOnNext();
		
		Filtration filtration = new Filtration();
		filtration.filter();
		filtration.distinct();
		filtration.take();
		filtration.takeLast();
		filtration.skip();
		filtration.skipLast();
	}
	
	public void reactor() {
		Mono.just(new Persona(Long.valueOf(5), "Mito", Integer.valueOf(29)))
			.doOnNext(p -> p.setEdad(Integer.valueOf(p.getEdad().intValue() + 1)))
			.subscribe(p -> log.info("[Reactor]: " + p));
	}
	
	public void rxjava2() {
		Observable.just(new Persona(Long.valueOf(1), "Roberto", Integer.valueOf(61)))
			.doOnNext(p -> p.setEdad(Integer.valueOf(p.getEdad().intValue() + 1)))
			.subscribe(p -> log.info("[RXJava2]: " + p));
	}
	
	public void mono() {
		Mono.just(new Persona(Long.valueOf(2), "Olga", 58))
			.subscribe(p -> log.info("[Mono]: " + p));
	}
	
	public void flux() {
		List<Persona> personas = List.of(
				new Persona(Long.valueOf(1), "Roberto", Integer.valueOf(61)),
				new Persona(Long.valueOf(2), "Olga", Integer.valueOf(58)),
				new Persona(Long.valueOf(3), "Ana Luz", Integer.valueOf(31)),
				new Persona(Long.valueOf(4), "Anabella", Integer.valueOf(27))
				);
		Flux.fromIterable(personas).subscribe(p -> log.info("[Flux]: " + p));
	}
	
	public void fluxMono() {
		List<Persona> personas = List.of(
				new Persona(Long.valueOf(1), "Roberto", Integer.valueOf(61)),
				new Persona(Long.valueOf(2), "Olga", Integer.valueOf(58)),
				new Persona(Long.valueOf(3), "Ana Luz", Integer.valueOf(31)),
				new Persona(Long.valueOf(4), "Anabella", Integer.valueOf(27))
				);

		Flux<Persona> fx = Flux.fromIterable(personas);
		fx.collectList().subscribe(lista -> log.info("[FluxToMono]: " + lista));
	}

}
