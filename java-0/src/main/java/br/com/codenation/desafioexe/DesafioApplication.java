package br.com.codenation.desafioexe;

import java.util.ArrayList;
import java.util.List;

public class DesafioApplication {
	public static List<Integer> fibonacci() {
		List<Integer> lista = new ArrayList<Integer>();
		lista.add(0);
		lista.add(1);
		lista.add(1);
		
		while (lista.get(lista.size()-1) <= 350) {
			lista.add(lista.get(lista.size()-1)+lista.get(lista.size()-2));
		}
		return lista;
	}
	
	public static Boolean isFibonacci(Integer a) {
		//Segundo métdo de verificação para saber se é fibonacci
		/* int valor = 5*a*a + 4;
		int valor2= ;
		int verifica = (int)Math.sqrt(valor);
		int verifica2 = (int)Math.sqrt(valor2)
		return (verifica*verifica == valor) || (verifica2*verifica2 == (5*a*a - 4));  */
		
		return fibonacci().contains(a); 
	
	}

}