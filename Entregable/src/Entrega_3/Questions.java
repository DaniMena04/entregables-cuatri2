package Entrega_3;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import us.lsi.ejemplos_b1_tipos.Persona;

public class Questions {

	//	Vencimiento de los prestamos de un cliente
	public static Set<LocalDate> vencimientoDePrestamosDeCliente(Banco banco,String dni) {
		// TODO
		// OBTENER LOS PRESTAMOS
		Set<Prestamo> prestamos = banco.prestamos().todos();
		
		// FILTRAR LOS PRESTAMOS POR EL DNI DEL CLIENTE
		Set<LocalDate> vencimientos = prestamos.stream()
				.filter(prestamo -> prestamo.dniCliente().equals(dni))
				.map(Prestamo::fechaVencimiento)
				.collect(Collectors.toSet());
		return vencimientos;
	}
	//	Persona con más prestamos
	public static Persona clienteConMasPrestamos(Banco banco) {
		// TODO
		// OBTENER LOS PRESTAMOS
		Map<String, Long> contadorDniClientes = banco.prestamos().todos().stream()
	            .collect(Collectors.groupingBy(Prestamo::dniCliente, Collectors.counting()));
		
		// ENCONTRAR EL DNI DE LA PERSONA
		String dniClienteConMasPrestamos = contadorDniClientes.entrySet().stream()
		            .max(Map.Entry.comparingByValue())
		            .map(Map.Entry::getKey)
		            .orElse(null);
		
		return banco.personas().personaDni(dniClienteConMasPrestamos).orElse(null);
	}
	//	Cantidad total de los crétditos gestionados por un empleado
	public static Double cantidadPrestamosEmpledado(Banco banco,String dni) {
		// TODO
		Set<Prestamo> prestamos = banco.prestamos().todos();
		
		double cantidadPRESTAMO = prestamos.stream()
				.filter(prestamo -> prestamo.dniEmpleado().equals(dni))
				.mapToDouble(Prestamo::cantidad).count();
		
		return cantidadPRESTAMO;
	}
	//	Empleado más longevo (CON MAS TIEMPO EN LA EMPRESA)
	public static Persona empleadoMasLongevo(Banco banco) {
		// TODO
		Set<Empleado> emp = banco.empleados().todos();
		
		Optional<Empleado> masLONGEVO = emp.stream()
				.min(Comparator.comparing(Empleado::fechaDeContrado));
		return masLONGEVO.map(Empleado::persona).orElse(null);
	}
	//	Interés mínimo, máximo y medio de los préstamos
	public static record Info(Double min, Double max, Double mean) {
		/*
		public String toString() {
			return String.format("(%.2f,%.2f,%.2f)",this.min(),this.max(),this.mean());
		}
		*/
	}
	
	public static  Info rangoDeIntereseDePrestamos(Banco banco) {
		// TODO
		
		//TODOS LOS PRESTAMOS
		Set<Prestamo> pres = banco.prestamos().todos();
		
		Set<Double> intereses = pres.stream()
				.map(Prestamo::interes)
				.collect(Collectors.toSet());
		Double min = intereses.stream().min(Double::compare).orElse(0.0);
		Double max = intereses.stream().max(Double::compare).orElse(0.0);
		Double mean = intereses.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
		
		return new Info(min,max,mean);
	}

	//	Número de préstamos por mes y año
	public static record Info2(Integer mes, Integer año) {
		public String toString() {
			return String.format("(%d,%d)",this.mes(),this.año());
		}
	}
	public static Map<Info2,Integer> numPrestamosPorMesAño(Banco banco) {
		// TODO
	    // Obtener todos los préstamos del banco
	    Set<Prestamo> prestamos = banco.prestamos().todos();

	    // Crear un mapa para mantener el recuento de préstamos por mes y año
	    Map<Info2, Integer> numPrestamosPorMesAño = new HashMap<>();

	    // Iterar sobre todos los préstamos
	    for (Prestamo prestamo : prestamos) {
	        // Extraer el mes y el año de la fecha del préstamo
	        Integer mes = prestamo.fechaComienzo().getMonthValue();
	        Integer año = prestamo.fechaComienzo().getYear();

	        // Crear una instancia de Info2 para representar el mes y el año
	        Info2 info = new Info2(mes, año);

	        // Incrementar el contador correspondiente en el mapa
	        numPrestamosPorMesAño.put(info, numPrestamosPorMesAño.getOrDefault(info, 0) + 1);
	    }

	    return numPrestamosPorMesAño;
	}
	
	public static void main(String[] args) {	
    	Banco ban = Banco.of();
    	
    	System.out.println("TEST DE QUESTIONS");
    	System.out.println("\n---------------------------\n");
    	System.out.println(vencimientoDePrestamosDeCliente(ban, "86202090E"));
    	
    	System.out.println(clienteConMasPrestamos(ban));
    	
    	System.out.println(cantidadPrestamosEmpledado(ban, "52184462S"));
    	
    	System.out.println(empleadoMasLongevo(ban));
    	
    	System.out.println(rangoDeIntereseDePrestamos(ban));
    	
    	//CREAMOS UN DICCIONARIO ORDENADO POR EL MES, Y A IGUALDAD DE MES, POR AÑO
    	SortedMap<Info2, Integer> sm = new TreeMap<>(Comparator.comparing(Info2::mes).thenComparing(Info2::año));
    	// INGRESAMOS EN EL DICCIONARIO ANTERIORMENTE
    	sm.putAll(Questions.numPrestamosPorMesAño(ban));
    	// IMPRIMIMOS EL DICCIONARIO
    	System.out.println(sm.firstKey() + ", " + sm.get(sm.lastKey()));
    	System.out.println(sm.lastKey() + ", " + sm.get(sm.lastKey()));
    }
	
	
}
