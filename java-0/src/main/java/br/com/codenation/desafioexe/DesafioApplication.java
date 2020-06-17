package br.com.codenation.desafioexe;

import java.util.ArrayList;
import java.util.List;

public class DesafioApplication {
	public static List<Integer> fibonacci() {
		List<Integer> lista = new ArrayList<Integer>();
		lista.add(0);
		lista.add(1);
		lista.add(1);
		while(lista.get(lista.size()-1)<=350){
			lista.add(lista.get((lista.size()-1))+lista.get(lista.size()-2));
		}
		System.out.println(lista+"\n");
		
		return lista;
	}

	public static Boolean isFibonacci(Integer a) {
		return isPerfectSquare(5*a*a + 4) || isPerfectSquare(5*a*a - 4); 
	}
		

	static  boolean isPerfectSquare(int x) 
    { 
        int s = (int) Math.sqrt(x); 
        return (s*s == x); 
    } 

}