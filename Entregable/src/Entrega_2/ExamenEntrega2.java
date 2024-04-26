package Entrega_2;

import java.util.ArrayList;
import java.util.List;

public class ExamenEntrega2 {
	
	// EJERCICIO 1
	public DataFrame emptyDataFrame(DataFrame df) {
		List<String> cNAMES = df.columNames(); // OBTENER LOS NOMBRES DE LAS COLUMNAS DEL DATAFRAME PROPORCIONADO
		List<List<String>> rowsEMPTY = new ArrayList<>(); // UNA LISTA DE FILAS VACIAS
		return DataFrameImpl.of(cNAMES, rowsEMPTY);	
	}
	
	// EJERCICIO 2
	public DataFrame addDataFrame(DataFrame df1, DataFrame df2) {
		// VERFICAR QUE TENGAN LA MISMA ESTRUCTURA
		List<String> df1ColNAMES = df1.columNames();
		List<String> df2ColNAMES = df2.columNames();
		
		assert df1ColNAMES.equals(df2ColNAMES) : "Los DataFrames pasados como parametros NO poseen la misma estructura";
		
		// CREACION DE UNA NUEVA LISTA
		List<List<String>> newROWS = new ArrayList<>(df1.rows());
		newROWS.addAll(df2.rows());
		
		return DataFrameImpl.of(df1ColNAMES, newROWS);
	}
	
	// EJERCICIO 3
	public DataFrame removeColumnIndex(DataFrame df, int ci) {
		List<String> cNAMES = df.columNames();
		assert ci >= 0 && ci < cNAMES.size() : "El indice supera el rango de columnas que posee df";
		
		// CREA UNA NUEVA CABESERA ELIMINANDO LA COLUMNA DE INDICE ci
		List<String> newColumNAMES = new ArrayList<>(cNAMES);
		newColumNAMES.remove(ci);
		
		// CREAR UNA NUEVA LISTA DE FILAS
		
		List<List<String>> newROWS = new ArrayList<>();
		for(List<String> row : df.rows()) {
			List<String> upROW = new ArrayList<>(row);
			upROW.remove(ci);
			newROWS.add(upROW);
		}
		
		return DataFrameImpl.of(newColumNAMES, newROWS);
	}
	
	public List<DataFrame> divideDataFrame(DataFrame df, int ci){
		List<String> cNAMES = df.columNames();
		assert ci >= 0 && ci < cNAMES.size() : "EL INDICE ES INVALIDO";
		
		// PROSESO DE DIVICION
		List<String> primerasCOLUMNAS = new ArrayList<>(cNAMES.subList(0, ci+1));
		List<String> segundasCOLUMNAS = new ArrayList<>(cNAMES.subList(ci+1, cNAMES.size()));
		
		// CREAR LOS NUEVOS DATAFRAMES
		List<List<String>> primeraR = new ArrayList<>();
		List<List<String>> segundaR = new ArrayList<>();
		
		for(List<String> row : df.rows()) {
			primeraR.add(new ArrayList<>(row.subList(0, ci+1)));
			segundaR.add(new ArrayList<>(row.subList(ci+1, row.size())));
		}
		
		DataFrame primerDATA = DataFrameImpl.of(primerasCOLUMNAS, primeraR);
		DataFrame segundoDATA = DataFrameImpl.of(segundasCOLUMNAS, segundaR);
		
		// AGREGAR A LA LISTA Y DEVOLVER LA LISTA
		List<DataFrame> res = new ArrayList<>();
		res.add(primerDATA);
		res.add(segundoDATA);
		return res;
		
	}
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExamenEntrega2 ex2 = new ExamenEntrega2();
		System.out.println("Prueba de parse:\n");
		DataFrameImpl d = DataFrameImpl.parse1("C:\\Users\\Usuario\\git\\entrega-02-JAVA\\Entregable\\src\\ficheros\\personas (1).csv");
		DataFrameImpl d2 = DataFrameImpl.parse1("C:\\Users\\Usuario\\git\\entrega-02-JAVA\\Entregable\\src\\ficheros\\mascotas.csv");
		System.out.println(d);
		
		System.out.println("EJERCICIO 1 \n");
		System.out.println(ex2.emptyDataFrame(d));
		System.out.println("\nEJERCICIO 2 \n");
		System.out.println(ex2.addDataFrame(d, d2));
		System.out.println("\nEJERCICIO 3 \n");
		System.out.println(ex2.removeColumnIndex(d, 2));
		System.out.println("\nEJERCICIO 4 \n");
		System.out.println(ex2.divideDataFrame(d, 3));
		
		
		

	}

}
