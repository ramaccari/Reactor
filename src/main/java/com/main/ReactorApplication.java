package com.main;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.main.model.Persona;
import com.main.reactor.combination.Combination;
import com.main.reactor.conditionals.Conditional;
import com.main.reactor.creation.Creation;
import com.main.reactor.error.ErrorOn;
import com.main.reactor.filtration.Filtration;
import com.main.reactor.matematics.Matematics;
import com.main.reactor.transformation.Transformation;

import io.reactivex.Observable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class ReactorApplication implements CommandLineRunner {
	
	private static final Logger log = LoggerFactory.getLogger(ReactorApplication.class);
	
	private List<Persona> personas = List.of(
			new Persona(Long.valueOf(1), "Roberto", Integer.valueOf(61)),
			new Persona(Long.valueOf(2), "Olga", Integer.valueOf(58)),
			new Persona(Long.valueOf(3), "Ana Luz", Integer.valueOf(31)),
			new Persona(Long.valueOf(4), "Anabella", Integer.valueOf(27))
			);

	
	@Autowired
	private Creation creation;
	
	@Autowired
	private Transformation transformation;
	
	@Autowired
	private Filtration filtration;
	
	@Autowired
	private Combination combination;
	
	@Autowired
	private ErrorOn errorOn;
	
	@Autowired
	private Conditional conditional;
	
	@Autowired
	private Matematics matematics;

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
		
		creation.range();
		creation.repeat();
		
		transformation.map();
		transformation.flatMap();
		transformation.groupBy();
		transformation.doOnNext();
		
		filtration.filter();
		filtration.distinct();
		filtration.take();
		filtration.takeLast();
		filtration.skip();
		filtration.skipLast();
		
		combination.merge();
		combination.mergeWith();
		combination.zip();
		combination.zipWith();
		
		// errorOn.retry(); 
		errorOn.onErrrorReturn();
		errorOn.onErrorResume();
		// errorOn.onErrorMap();
		
		conditional.defaultIfEmpty();
		conditional.takeUntil();
		conditional.timeOut();
		
		matematics.average();
		matematics.count();
		matematics.min();
		matematics.max();
		matematics.sum();
		matematics.summarizing();
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
		Flux.fromIterable(personas).subscribe(p -> log.info("[Flux]: " + p));
	}
	
	public void fluxMono() {
		Flux<Persona> fx = Flux.fromIterable(personas);
		fx.collectList().subscribe(lista -> log.info("[FluxToMono]: " + lista));
	}

}
