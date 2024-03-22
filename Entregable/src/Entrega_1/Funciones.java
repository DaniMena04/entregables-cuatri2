package Entrega_1;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Funciones{
	
	public static Boolean numeroPrimo(Integer num) {
		// COMPROBAR QUE EL NUMERO SEA UN ENTERO POSITIVO
		if(num <= 0) {
			throw new IllegalArgumentException("El numero no puede cero ni negativo");
		}
		
		// VERFICAR SI DICHO NUMERO ES PRIMO
		for(int i = 2; i <= num/2; i++) {
			if(num % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	// METODO PARA REALIZAR LA FACTORIZACION
	
	public static Long numeroCombinatorio(Integer num1, Integer num2) {
	    if(num1 < num2 ) {
	        throw new IllegalArgumentException("El primer numero debe ser mayor que el segundo");
	    }
	    
	    Long uno = numFactorial(num1);
	    Long dos = numFactorial(num2);
	    Long tres = numFactorial(num1 - num2);
	    
	    
	    return (uno / (dos * tres));
	}

	private static Long numFactorial(Integer num) {
	    Long result = 1L;
	    for(int i = 1; i <= num; i++) {
	        result *= i;
	    }
	    return result;
	}
	
	
	
	private static Double res_Sumatoria(Integer num1, Integer num2, int i) {
		
		return (Math.pow(-1,i) * numeroCombinatorio(num2, i).doubleValue() * Math.pow((num2 - i),num1));
	}
        
	
	// METODO PARA REALIZAR LA FUNCION S(n,k)
	public static Double funcionS(Integer num1, Integer num2) {
		
		// COMPROBAR QUE EL PRIMER NUMERO SEA MAYOR AL SEGUNDO
		if(num1 < num2) {
			throw new IllegalArgumentException("El primer numero debe ser mayor que el segundo");
		}
		
		//
		Long den = numFactorial(num2);
		Double div = 1.0/den;
		Double res1 = 0.;
		
		// FUNCION DE LA SUMATORIA
		for(int i = 0; i <= num2; i++) {
			res1 += res_Sumatoria(num1,num2,i);
		}
		
		return (div * res1);
	}
	
	/*
	public static List<Integer> diferenciaConsecutiva(List<Integer> list){
		
		// COMPROBAR QUE EL PRIMER NUMERO SEA MAYOR AL SEGUNDO
			if(list.isEmpty()) {
				throw new IllegalArgumentException("La lista no debe estar vacia");
			}else {
				// SE CREA UNA NUEVA LISTA VACIA
				List<Integer> result = new ArrayList<>();
				
				// SE RECORE LA LISTA Y REALIZA LA RESTA ENTRE EL ELEMENTO i Y EL ELEMENTO ANTERIOR A ESTE
				for(int i = 1; i <= list.size(); i++) {
					int dif = list.get(i) - list.get(i - 1);
					result.add(dif);
				}
				
				return result;
			}
	}
	
	
	public static String cadenaMasLarga(List<String> list){
		if(list.isEmpty()) {
			throw new IllegalArgumentException("La lista no debe estar vacia");
		}else {
			String cadenaMASLarga = "";
			for(String cadena: list) {
				if(cadena.length() > cadenaMASLarga.length()) {
					cadenaMASLarga = cadena;
				}
			}
			
			return cadenaMASLarga;
		}
	}
	*/
	
	// ------------------ EJERCICIOS DE LA DEFENSA ------------------------------
	// A)
	public static Long funcion_P2(Integer n, Integer k) {
		
		
		// COMPROBAR QUE EL PRIMER NUMERO SEA MAYOR AL SEGUNDO
		if(n < k) {
			throw new IllegalArgumentException("El primer numero (n) debe ser mayor o igual que el segundo (k)");
		}
		Long resul = 1L;
		for(int i=0; i<=k-2; i++) {
			if(i < k+1) {
				resul *= (n - i);
			}else {
				break;
			}
		}
		return resul;
	}
	
	// B)
	public static Long funcion_C2(Integer n, Integer k) {
		// COMPROBAR QUE EL PRIMER NUMERO SEA MAYOR AL SEGUNDO
		if(n < k) {
			throw new IllegalArgumentException("El primer numero (n) debe ser mayor que el segundo (k)");
		}
		Integer sum = k + 1;
		return numeroCombinatorio(n,sum);
	}
	
	// C)
	public static Double funcionS2(Integer n, Integer k) {
		// COMPROBAR QUE EL PRIMER NUMERO SEA MAYOR AL SEGUNDO
		if(n < k) {
			throw new IllegalArgumentException("El primer numero (n) debe ser mayor o igual que el segundo (k)");
		}
		Integer sum = k+2;
		Long num = numFactorial(3);
		Long den = numFactorial(5);
		Double div = (num.doubleValue())/den;
		
		Double rest = 0.00;
		for(int i=0; i<=k; i++) {
			rest += sumatoria(n,k,i);
		}
		
		return (div * rest);
	}
	
	private static Double sumatoria(Integer n, Integer k, int i) {
		return (Math.pow(-1,i) * numeroCombinatorio(k,i).doubleValue() * Math.pow((k-i), n));
	}
		
}


