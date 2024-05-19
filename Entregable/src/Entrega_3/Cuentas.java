package Entrega_3;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import us.lsi.tools.File2;

public class Cuentas {
	
	// ATRIBUTOS
	private static Cuentas gestorDeCuentas = null;
	private Set<Cuenta> cuentas;
	private Map<String,Cuenta> cuentasIban;
	
	// CONSTRUCTOR
	private Cuentas(Set<Cuenta> cuentas) {
		super();
		this.cuentas = cuentas;
		this.cuentasIban = this.cuentas.stream().collect(Collectors.toMap(e->e.Iban(), e->e));
	}
	
	// METODOS DE FACTORIA
	public static Cuentas of() {
		if(Cuentas.gestorDeCuentas == null) {
			Cuentas.gestorDeCuentas = Cuentas.parse("C:\\Users\\Usuario\\git\\entrega-02-JAVA\\Entregable\\src\\fichero_banco\\cuentas.txt");
		}
		return Cuentas.gestorDeCuentas;
	}
	
	public static Cuentas parse(String file) {
		Set<Cuenta> cuentas = File2.streamDeFichero(file).map(ln->Cuenta.parse(ln)).collect(Collectors.toSet());
		Cuentas.gestorDeCuentas = new Cuentas(cuentas);
		return Cuentas.gestorDeCuentas;
	}
	
	// METODOS PROPIOS
	public Set<Cuenta> todos(){
		return this.cuentas;
	}
	
	public Optional<Cuenta> cuentaIban(String iban){
		return Optional.ofNullable(this.cuentasIban.getOrDefault(iban, null));
	}
	
	public Integer size() {
		return this.cuentas.size();
	}
	
	public Cuenta cuentaINDEX(Integer index) {
		return this.cuentas.stream().toList().get(index);
	}

	@Override
	public String toString() {
		return this.cuentas.stream().map(p -> p.toString()).collect(Collectors.joining("\n\t","Cuentas\n\t", ""));
	}
	
	
	public static void main(String[] args) {
		Cuentas cuentas = Cuentas.parse("C:\\Users\\Usuario\\git\\entrega-02-JAVA\\Entregable\\src\\fichero_banco\\cuentas.txt");
		System.out.println(cuentas);
		cuentas.cuentaIban("ES4986795203116686070502").get().Ingresar(150.20);
		System.out.println(cuentas.cuentaIban("ES4986795203116686070502").get());
		
		
	}
	

	
	

}
