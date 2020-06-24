package challenge;

public class CriptografiaCesariana implements Criptografia {

    @Override
    public String criptografar(String texto) {
        isValid(texto);
        
        return criptografarMinusculo(texto.toLowerCase(),true);

    }
    
    public String criptografarMinusculo(String texto,boolean criptografar) {
        for (int i = 0; i < texto.length(); i++) {
            int ascii = (int) texto.charAt(i);
            if (criptografar && isLowerLetter(ascii)) {
                if (ascii >= 120 && ascii<=122 ){
                    ascii -= 23;
                } else{
                    ascii += 3;
                }
                texto = texto.substring(0, i) + (char) ascii + texto.substring(i + 1);
            } else if(isLowerLetter(ascii)){
                if (ascii >= 97 && ascii<=99) {
                    ascii -= 23;
    
                } else{
                    ascii -= 3;
                }
                texto = texto.substring(0, i) + (char) ascii + texto.substring(i + 1);    
            }
            
        }
        return texto;
    }

    public Boolean isLowerLetter(int ascii){
        return ascii>96 && ascii<123;
    }

    @Override
    public String descriptografar(String texto) {
        isValid(texto);
        return criptografarMinusculo(texto.toLowerCase(), false);
    }

    static void isValid(String texto) {
        if (texto.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
    }
}
