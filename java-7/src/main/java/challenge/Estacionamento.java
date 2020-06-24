package challenge;

import java.util.ArrayList;
import java.util.List;

public class Estacionamento {
    private List <Carro> lista_carros = new ArrayList<>();

    public void estacionar(Carro carro) {
        int park=0;
        if(lista_carros.size()==0 || lista_carros.size()<10 && !lista_carros.contains(carro)){
            
            lista_carros.add(carro);
        } 
        else if (lista_carros.size()>=10){
            for(int i=0;i<lista_carros.size();i++){

                if(lista_carros.get(i).getMotorista().equals(carro.getMotorista())){
                    throw new EstacionamentoException("motorista nao pode usar dois carros ao mesmo tempo");
                }
                else if(lista_carros.get(i).getMotorista().getIdade()>55){
                    park++;
                }
                else {
                    park--;
                    lista_carros.set(i,carro);
                }
            }
            if(park>=lista_carros.size()){
                throw new EstacionamentoException("message");
            }
        }
    }


    public int carrosEstacionados() {
        return lista_carros.size();
    }

    public boolean carroEstacionado(Carro carro) {
        return lista_carros.contains(carro);
    }
}
