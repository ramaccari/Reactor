package com.main.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Venta {
	
	private Long id;
	
	private LocalDateTime fecha;

	public Venta(Long id, LocalDateTime fecha) {
		super();
		this.id = id;
		this.fecha = fecha;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Venta other = (Venta) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Venta [id=" + id + ", fecha=" + fecha + "]";
	}

}
