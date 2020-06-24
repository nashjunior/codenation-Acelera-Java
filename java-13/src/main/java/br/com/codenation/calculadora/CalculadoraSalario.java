package br.com.codenation.calculadora;


public class CalculadoraSalario {

	public long calcularSalarioLiquido(double salarioBase) {
		
		if(salarioBase<=1039){
			return 0;
		}

		return calcularIRRF(salarioBase);

	}

	public long calcularIRRF(double salarioBase){
		double salarioBaseMenosINSS = calcularInss(salarioBase);
		
		if(salarioBase-salarioBaseMenosINSS<=3000){
			return Math.round(salarioBase-salarioBaseMenosINSS);
		}
		else if((salarioBase-salarioBaseMenosINSS)<=6000 ){
			
			return  Math.round(salarioBase-((salarioBase-salarioBaseMenosINSS)*0.075)-salarioBaseMenosINSS);
		}

		return  Math.round(salarioBase-((salarioBase-salarioBaseMenosINSS)*0.15)-salarioBaseMenosINSS);
	}
	
	
	//Exemplo de método que pode ser criado para separar melhor as responsábilidades de seu algorítmo
	private double calcularInss(double salarioBase) {
		
		if(salarioBase<=1500){
			return  Math.round((salarioBase*0.08));
		}
		else if(salarioBase>1500 && salarioBase<=4000 ){
			
			return  Math.round(salarioBase*0.09);
		}
		return  salarioBase*0.11;
	}
}
/*Dúvidas ou Problemas?
Manda e-mail para o meajuda@codenation.dev que iremos te ajudar! 
*/