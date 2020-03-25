package rafael;

public class Main {

    public static void main(String[] args) {
        /*
        var keys = new KeysGenerator("1010101010");
        var temp = new SDES();
        temp.encrypt("11110000", keys);
        temp.decrypt("01011101", keys);
        // 01011101
        */
        var esdes = new Enhanced("3-5-2-0-4-1","1010101010");
        String[] keys = {"0-1","0-1-2","3-0-2-1","1-4-3-0-2","4-3-2-5-1-0","0-4-2-3-1-6-5","2-4-3-7-0-5-1-6","1-3-7-4-0-5-6-2-8"};
        String[] texts = {"0123456789ABCDEFGHIJ", "ken es un perro muy bonito", "ahorita te contesto bien va deja termino mi draft",
                "Pantalones, no se me vaya a encender por accidente la camara", "Ya casi empieza tus clase verdad", "Que bello es el mundo con git",
                "si quieres vemos lo que falta y por la noche hacemos el video para que no nos agarren las prisas",
                "Under King Charles, an ardent forester, the wholesale destruction of timber was arrested, and new plantations met with success."};
        String[] sdesKesy = {"0001010111", "0001001110", "0001000001", "0000011011", "0000001110"};

        for(String i : keys) {
            for(String j : sdesKesy) {
                esdes = new Enhanced(i,j);
                for (String k : texts) {
                    System.out.println(k);
                    String crypty = esdes.encrypt(k);
                    System.out.println(crypty);
                    String decry = esdes.decrypt(crypty);
                    System.out.println(decry);
                    System.out.println("===========================");
                }
            }
        }
    }
}
