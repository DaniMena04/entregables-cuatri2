package Test;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

import Entrega_2.DataFrame;
import Entrega_2.DataFrameImpl;
public class TestDataFrameImpl {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Prueba de parse:\n");
		DataFrameImpl d = DataFrameImpl.parse1("C:\\Users\\Usuario\\git\\entrega-02-JAVA\\Entregable\\src\\ficheros\\personas (1).csv");
		System.out.println(d);
		
		System.out.println("");
		
		System.out.println("Prueba de columNames:\n");
		System.out.println(d.columNames());
		
		System.out.println("");
		
		System.out.println("Prueba de columNumber:\n");
		System.out.println(d.columNumber());
		
		System.out.println("");
		
		System.out.println("Pruebas de colum:\n");
		System.out.println("Columna llamada 'Apellidos':\n");
		System.out.println(d.colum("Apellidos"));
		System.out.println("\nColumna de índice 2:\n");
		System.out.println(d.colum(2));
		System.out.println("\nColumna llamada 'Fecha_Nacimiento' pasando sus valores a LocalDate:\n");
		// System.out.println(LocalDate.class);
		System.out.println(d.colum("Fecha_Nacimiento", LocalDate.class));
		System.out.println("\nColumna de índice 2, pasando sus valores a LocalDate:\n");
		System.out.println(d.colum(2, LocalDate.class));
		
		System.out.println("");
		
		System.out.println("Pruebas de columAllDifferent:\n");
		System.out.println("¿Todos los elementos de la columna 'Nombre' son diferentes?");
		System.out.println(d.columAllDifferent("Nombre"));
		System.out.println("\n¿Todos los elementos de la columna 'Id' son diferentes?");
		System.out.println(d.columAllDifferent("Id"));
		
		System.out.println("");
		
		System.out.println("Pruebas de propertie:\n");
		System.out.println("Propertie de la fila 0 y la columna 'Fecha_Nacimiento':");
		System.out.println(d.propertie(d.row(0), "Fecha_Nacimiento"));
		System.out.println("\nPropertie de la fila 0 y la columna 'Fecha_Nacimiento', pasando el valor a LocalDate:");
		System.out.println(d.propertie(d.row(0), "Fecha_Nacimiento", LocalDate.class));
		
		System.out.println("");
		
		System.out.println("Pruebas de cell:\n");
		System.out.println("Celda de fila 72 y columna 'Nombre':");
		System.out.println(d.cell(72,"Nombre"));
		System.out.println("\nCelda de fila 72 y columna 1:");
		System.out.println(d.cell(72,1));
		System.out.println("\nCelda de fila en la que la columna 'Id' tiene el valor 73, y columna 'Nombre':");
		System.out.println(d.cell("73", "Id", "Nombre"));
		
		System.out.println("");
		
		System.out.println("Prueba de rowNumber:\n");
		System.out.println(d.rowNumber());
		
		System.out.println("");
		
		System.out.println("Pruebas de row:\n");
		System.out.println("Fila de índice 5:");
		System.out.println(d.row(4));
		System.out.println("\nFila en la que la columna 'Id' tiene el valor 6:");
		System.out.println(d.row("6","Id"));
		
		System.out.println("");
		
		System.out.println("Pruebas de head:\n");
		System.out.println("Diez primeras filas:\n");
		System.out.println(d.head(10));
		System.out.println("\nCinco primeras filas (número por defecto):\n");
		System.out.println(d.head());
		
		System.out.println("");
		
		System.out.println("Pruebas de tail:\n");
		System.out.println("Diez últimas filas:\n");
		System.out.println(d.tail(10));
		System.out.println("\nCinco últimas filas (número por defecto):\n");
		System.out.println(d.tail());
		
		System.out.println("");
		
		System.out.println("Prueba de slice:\n");
		System.out.println("Porción entre las filas 5 y 10:\n");
		System.out.println(d.slice(5,10));
		
		System.out.println("");
		
		System.out.println("Prueba de filter:\n");
		System.out.println("Filas cuya persona tenga una edad mayor que 30:\n");
		System.out.println(d.filter(lista -> Period.between(LocalDate.parse(lista.get(2),DateTimeFormatter.ofPattern("dd/MM/yyyy")),LocalDate.now()).getYears() > 30));
		
		System.out.println("");
		
		System.out.println("Prueba de addCalculatedColum:\n");
		System.out.println("Añadimos la columna 'Email', cuyos valores son los nombres de las personas seguidos de @email.com:\n");
		System.out.println(d.addCalculatedColum("Email", lista -> lista.get(1)+"@email.com"));
		
		System.out.println("");
		
		System.out.println("Prueba de removeColum:\n");
		System.out.println("Eliminamos la columna 'Nombre':\n");
		System.out.println(d.removeColum("Nombre"));
	}

}
