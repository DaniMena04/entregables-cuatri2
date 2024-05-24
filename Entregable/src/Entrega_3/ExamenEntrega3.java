package Entrega_3;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import us.lsi.ejemplos_b1_tipos.Persona;

public class ExamenEntrega3 {
	
	/*
	List<Empleado> empleadosEntreDiaMes (Banco banco, String ini, String fin){
		
	}
	*/
	
	
	public static Map<Character, List<Empleado>> empleadoConLetraDNI(Banco banco, Set<Character> letras){
		
		// Validar que el parámetro letras no sea nulo y que solo contenga caracteres alfabéticos
        if (letras == null) {
            throw new IllegalArgumentException("El parámetro letras no puede ser nulo.");
        }
		
        for (Character letra : letras) {
            if (!Character.isLetter(letra)) {
                throw new IllegalArgumentException("El parámetro letras solo puede contener caracteres alfabéticos.");
            }
        }
        
        // Obtener todos los empleados del banco
        Set<Empleado> empleados = banco.empleados().todos();

        // Crear un mapa para asociar listas de empleados con cada letra del DNI
        Map<Character, List<Empleado>> empleadosPorLetraDNI = new HashMap<>();
        
     // Iterar sobre los empleados y agruparlos por la letra de su DNI
        for (Empleado empleado : empleados) {
            String dni = empleado.dni();
            char letraDNI = dni.charAt(dni.length() - 1);
            if (letras.contains(letraDNI)) {
                empleadosPorLetraDNI.computeIfAbsent(letraDNI, k -> new ArrayList<>()).add(empleado);
            }
        }

        return empleadosPorLetraDNI;
        
	}
	
	public static Double clienteEdadMedia(Banco banco, Integer m) {
		
		// Validar que m no sea nulo y sea positivo
        if (m == null) {
            throw new IllegalArgumentException("El parámetro m no puede ser nulo.");
        }
        if (m <= 0) {
            throw new IllegalArgumentException("El parámetro m debe ser positivo.");
        }
        
     // Obtener el conjunto de todas las personas
        Set<Persona> personas = banco.personas().todos();

        // Filtrar las personas que son mayores o igual a m y calcular sus edades
        double sumEdad = 0;
        int count = 0;
        for (Persona persona : personas) {
            int edad = (LocalDateTime.now().getYear() - persona.fechaDeNacimiento().getYear());
            if (edad >= m) {
                sumEdad += edad;
                count++;
            }
        }

        // Calcular la edad media
        if (count == 0) {
            return 0.0; // Si no hay personas mayores o igual a m, devolver 0.0
        }
        return sumEdad / count;
    }
	
	public static Set<String> clientesConMenosPrestamos (Banco banco, int n){
	
		 // Validar que n no sea nulo y sea mayor que cero
        if (n <= 0) {
            throw new IllegalArgumentException("El parámetro n debe ser mayor que cero.");
        }
		
		// Obtener todos los préstamos del banco
        Set<Prestamo> prestamos = banco.prestamos().todos();

        // Contar la cantidad de préstamos por cliente (DNI)
        Map<String, Long> prestamosPorCliente = prestamos.stream()
                .collect(Collectors.groupingBy(Prestamo::dniCliente, Collectors.counting()));

        // Ordenar los clientes por la cantidad de préstamos en orden ascendente
        List<Map.Entry<String, Long>> clientesOrdenados = prestamosPorCliente.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toList());

        // Obtener los DNIs de los n clientes con menos préstamos
        Set<String> dnisConMenosPrestamos = clientesOrdenados.stream()
                .limit(n)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        return dnisConMenosPrestamos;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Banco ban = Banco.of();
		Set<Character> letras = new HashSet<>();
        letras.add('A');
        letras.add('B');
        letras.add('C');
        
		System.out.println("PRUEBA DE LA DEFENSA");
		System.out.println("LETRA DNI");
		System.out.println(empleadoConLetraDNI(ban,letras));
		System.out.println("EDAD MEDIA");
		System.out.println(clienteEdadMedia(ban, 20));
		System.out.println("MENOS PRESTAMOS");
		System.out.println(clientesConMenosPrestamos(ban, 5));
	}

}
