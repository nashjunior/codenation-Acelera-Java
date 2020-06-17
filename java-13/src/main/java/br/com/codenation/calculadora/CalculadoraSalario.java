package br.com.codenation.calculadora;


public class CalculadoraSalario {

	public long calcularSalarioLiquido(double salarioBase) {
		//Use o Math.round apenas no final do método para arredondar o valor final.
		//Documentação do método: https://docs.oracle.com/javase/8/docs/api/java/lang/Math.html#round-double-
		if(salarioBase==0 || salarioBase<=0){
			return 0;
		}

		double teste = calcularInss(salarioBase);
		if(salarioBase<=1039){
			return 0;
		}
		if(salarioBase-teste<=3000){
			return Math.round(salarioBase-teste);
		}
		else if((salarioBase-teste)>3000 && (salarioBase-teste)<=6000 ){
			
			return  Math.round(salarioBase-((salarioBase-teste)*0.075)-teste);
		}

	return  Math.round(salarioBase-((salarioBase-teste)*0.15)-teste);

	}
	
	
	//Exemplo de método que pode ser criado para separar melhor as responsábilidades de seu algorítmo
	private double calcularInss(double salarioBase) {
		
		if(salarioBase<=1500){
			return  Math.round((salarioBase*0.08));
		}
		else if(salarioBase>1500 && salarioBase<=4000 ){
			
			return  Math.round(salarioBase*0.09);
		}
		return  Math.round(salarioBase*0.11);
}
}
/*Dúvidas ou Problemas?
Manda e-mail para o meajuda@codenation.dev que iremos te ajudar! 
*/