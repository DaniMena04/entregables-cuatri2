package Entrega_3;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Cuenta {
	
	// ATRIBUTOS
	private String iban;
	private String dni;
	private LocalDate fecha_creacion;
	private Double saldo;
	
	// CONSTRUCTOR PRIVADO
	private Cuenta(String iban, String dni, LocalDate fecha_creacion, Double saldo) {
		this.iban = iban;
		this.dni = dni;
		this.fecha_creacion = fecha_creacion;
		this.saldo = saldo;
	}
	
	// METODOS DE FACTORIA
	public static Cuenta of(String iban, String dni, LocalDate fecha_creacion, Double saldo) {
		return new Cuenta(iban,dni,fecha_creacion,saldo);
	}
	
	public static Cuenta parse(String file) {
		DateTimeFormatter fm = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String[] partes = file.split(",");
		String iban = partes[0];
		String dni = partes[1];
		LocalDate fecha_creacion = LocalDateTime.parse(partes[2], fm).toLocalDate();
		Double saldo = Double.parseDouble(partes[3]);
		
		return of(iban,dni,fecha_creacion,saldo);
	}
	
	// METODOS PROPIOS
	public void Ingresar(Double c) {
		setSaldo(Saldo()+c);
	}
	
	public void Retirar(Double c) {
		setSaldo(Saldo()-c);
	}
	
	// GETTER AND SETTERS
	public Double Saldo() {
		return saldo;
	}

	private void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public String Iban() {
		return iban;
	}

	public String Dni() {
		return dni;
	}

	public LocalDate Fecha_creacion() {
		return fecha_creacion;
	}
	
	// toString
	@Override
	public String toString() {
		return String.format("%s, %s", this.Iban(),this.Saldo());
	}
	
	
	
	
	
	

}
