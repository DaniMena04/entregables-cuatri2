package Entrega_1;

import java.time.LocalDate;

public record Fecha(Integer anio, Integer mes, Integer dia) {
	
	//METODOS DE FACTORIA
	public static Fecha of(Integer anio, Integer mes, Integer dia) {
		return new Fecha(anio,mes,dia);
	}
	
	public static Fecha parse(String str) {
		// FORMATO: "anio-mes-dia"
		String[] str2 = str.split("-");
		Integer anio = Integer.valueOf(str2[0]);
		Integer mes  = Integer.valueOf(str2[1]);
		Integer dia  = Integer.valueOf(str2[2]);
		return new Fecha(anio,mes,dia);
	}
	// METODOS PRIVADOS
	
	// ***** 1 ******
	 private static int al_Zeller(int anio, int mes, int dia) {
		 int h = 0;
	     if (mes < 3) {
	    	 anio -= 1;
	         mes += 12;
	     }

	     int K = anio % 100;
	     int J = anio / 100;
	     h = (dia + (13 * (mes + 1) / 5) + K + (K / 4) + (J / 4) - 2 * J) % 7;
	     return h;
	}
	 
	// ****** 2 *******
	 
	 public static Integer diasEnMes(Integer anio, Integer mes) {
		 assert mes >= 1 && mes <= 12 : "El mes es erroneo";
	        int[] mes31DIAS = {1, 3, 5, 7, 8, 10, 12};
	        int[] mes30DIAS = {4, 6, 9, 11};

	        if (mes == 2) {
	            if (anio % 4 == 0 && (anio % 100 != 0 || anio % 400 == 0)) {
	                return 29; // En el mes " + mes + " del año " + anio + " hay 29 días";
	            } else {
	                return 28; // En el mes " + mes + " del año " + anio + " hay 28 días";
	            }
	        } else if (contains(mes31DIAS, mes)) {
	            return 31; // En el mes " + mes + " del año " + anio + " hay 31 días";
	        } else if (contains(mes30DIAS, mes)) {
	            return 30; // En el mes " + mes + " del año " + anio + " hay 30 días";
	        } else {
	            return -1; // Mes no válido
	        }
	    }

    private static boolean contains(int[] array, int value) {
        for (int item : array) {
            if (item == value) {
                return true;
            }
        }
        return false;
    }
    
    // METODO EXTRA
    
    public static boolean esAnioBisiesto(Integer anio) {
        assert anio >= 0 : "El año no puede ser negativo";

        if (anio % 4 == 0) {
            if (anio % 100 != 0) {
                return true;
            } else if (anio % 400 == 0) {
                return true;
            }
        }
        return false;
    }
    
	// METODOS DERIVADOS
	public String nombreMES() {
		
		assert this.mes >= 1 && this.mes <= 12 : "El mes está fuera del rango válido (1-12).";
		String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", 
        		"Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        return meses[this.mes - 1];
        
    }
	
	public String diaSemana() {
		
		assert this.dia >= 1 && this.dia <= 31 : "El dia está fuera del rango válido (1-31).";
		String[] dias = {"Sabado", "Domingo", "Lunes","Martes","Miercoles","Jueves","Viernes"};
        return dias[this.al_Zeller(this.anio, this.mes, this.dia)];
	}
	
	public Fecha sumarDIAS(int diasEXTRA) {
		Integer dia_actual = this.dia;
		Integer mes_actual = this.mes;
		Integer anio_actual = this.anio;
		
		while (diasEXTRA > 0) {
			 Integer dias_mes_actual = diasEnMes(anio_actual, mes_actual);
			 Integer dias_restantes_mes = dias_mes_actual - dia_actual + 1;

			 if (diasEXTRA > dias_restantes_mes) {
				 diasEXTRA -= (dias_restantes_mes+1);
			     dia_actual = 1;
			     mes_actual += 1;

			     if (mes_actual > 12) {
			    	 mes_actual = 1;
			         anio_actual += 1;
			     }
			                
			 }else {
		    	 dia_actual += diasEXTRA;
			     diasEXTRA = 0;
			 }
		}
		return Fecha.of(anio_actual, mes_actual, dia_actual);
           
		
	}
	
	public Fecha restarDIAS(Integer diasMENOS) {
		Integer dia_actual = this.dia;
		Integer mes_actual = this.mes;
		Integer anio_actual = this.anio;
		
		while (diasMENOS > 0) {
			 if(diasMENOS >= dia_actual) {
				 diasMENOS -= dia_actual;
				 mes_actual -= 1;
				 
				 if(mes_actual < 1) {
					 mes_actual = 12;
					 anio_actual -= 1;
				 }
				 
				 dia_actual = diasEnMes(anio_actual, mes_actual);
			                
			 }else {
				 dia_actual -= diasMENOS;
				 diasMENOS = 0;
			 }
			                
		}
		
		return Fecha.of(anio_actual, mes_actual, dia_actual);
		
	}
	
	public Integer diferenciaEnDias(Fecha otraFecha) {
        LocalDate fechaActual = LocalDate.of(this.anio, this.mes, this.dia);
        LocalDate nuevaFecha = LocalDate.of(otraFecha.anio, otraFecha.mes, otraFecha.dia);

        if (fechaActual.equals(nuevaFecha)) {
            return 0;
        }

        // Calcula la diferencia en días
        int diferencia = Math.abs(fechaActual.until(nuevaFecha).getDays());
        return diferencia;
    }
	
	public String toString() {
		String dia_semana = this.diaSemana();
		Integer numDia = this.dia;
		String nomMES = this.nombreMES();
		Integer anio = this.anio;
		        
		return "[" + dia_semana + ", " + numDia + " de " + nomMES + " del " + anio + "]";
	}
}
