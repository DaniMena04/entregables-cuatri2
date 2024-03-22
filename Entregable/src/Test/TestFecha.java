package Test;

import java.time.LocalDate;

import Entrega_1.Fecha;

public class TestFecha {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Crear una instancia de Fecha
        Fecha fecha = Fecha.of(2024, 3, 19); // Año, Mes, Día
        
        // Probar el método parse
        Fecha fechaParseada = Fecha.parse("2024-03-19");
        System.out.println("Fecha parseada: " + fechaParseada);

        // Probar el método sumarDIAS
        
        System.out.println("Fecha con días sumados: " + fecha.sumarDIAS(5));

        // Probar el método restarDIAS
        
        System.out.println("Fecha con días restados: " +  fecha.restarDIAS(5));
        
		
        // Probar el método nombreMes
        String nombreMes = fecha.nombreMES();
        System.out.println("Nombre del mes: " + nombreMes);

        // Probar el método diaSemana
        String diaSemana = fecha.diaSemana();
        System.out.println("Día de la semana: " + diaSemana);

        // Probar el método esAnioBisiesto
        boolean esBisiesto = Fecha.esAnioBisiesto(2024);
        System.out.println("¿El año 2024 es bisiesto?: " + esBisiesto);

        // Probar el método toString
        String fechaComoString = fecha.toString();
        System.out.println("Fecha como String: " + fechaComoString);

        // Probar el método diferenciaEnDias
        Integer diferenciaDias = fecha.diferenciaEnDias(Fecha.of(2024, 3, 24));
        System.out.println("Diferencia en días: " + diferenciaDias);
        
        
        
     // ------------------------------------------------
     	System.out.println("EJECUCION DE LOS EJERCICIOS DE LA DEFENSA");
     	System.out.println(fecha.restarDiasFechaDada(Fecha.of(2024, 3, 22), 50));

	}

}
