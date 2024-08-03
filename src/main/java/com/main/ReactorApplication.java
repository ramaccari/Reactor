package com.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.main.model.Persona;

import io.reactivex.Observable;
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
	}
	
	public void reactor() {
		Mono.just(new Persona(Long.valueOf(1), "Mito", Integer.valueOf(29)))
			.subscribe(p -> log.info("[Reactor] Persona: " + p));
	}
	
	public void rxjava2() {
		Observable.just(new Persona(Long.valueOf(1), "Mito", Integer.valueOf(29)))
			.subscribe(p -> log.info("[RXJava2] Persona: " + p));
	}

}
