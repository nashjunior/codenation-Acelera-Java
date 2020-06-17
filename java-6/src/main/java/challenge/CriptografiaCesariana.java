package challenge;

public class CriptografiaCesariana implements Criptografia {

    @Override
    public String criptografar(String texto) {
        String codificado2="";
        if(texto.length()<1){
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < texto.length(); i++) {
            int ascii = (int) texto.charAt(i);
            if (ascii > 64 && ascii < 90) {
                if (ascii == 90 || ascii == 89 || ascii == 88) {
                    ascii += 10;
                } else {
                    ascii += 35;
                }
                codificado2=codificado2.concat(Character.toString((char)ascii) );
            } else if (ascii > 96 && ascii < 123) {
                if (ascii == 121 || ascii == 122 || ascii == 120 ) {
                    ascii -= 23;
                } else {
                    ascii += 3;
                }
                codificado2= codificado2.concat(Character.toString((char)ascii) );
            } else{
                codificado2= codificado2.concat(Character.toString((char)ascii) );
            }
        }
        return codificado2;
        
        
    }

    @Override
    public String descriptografar(String texto) {
        String codificado2="";
        if(texto.length()<1){
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < texto.length(); i++) {
            int ascii = (int) texto.charAt(i);
            if (ascii > 64 && ascii < 90) {
                if (ascii == 65 || ascii == 66 || ascii == 67) {
                    ascii += 55;
                } else {
                    ascii += 29;
                }
                codificado2= codificado2.concat(Character.toString((char)ascii) );
            } else if (ascii > 96 && ascii < 123) {
                if (ascii == 97 || ascii == 98 || ascii == 99) {
                    ascii -= 23;
                } else {
                    ascii -= 3;
                }
                codificado2= codificado2.concat(Character.toString((char)ascii) );
            } else{
                codificado2= codificado2.concat(Character.toString((char)ascii) );
            }
        }
        return codificado2;
    }
}
