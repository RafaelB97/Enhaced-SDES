package rafael;

public class Enhanced {

    public String sdesFunction(String text, SDES sdes, Boolean flag) {
        String[] result = new String[text.length()];
        String res = "";
        for(int i = 0; i<text.length(); i++) {
            var binary = Integer.toBinaryString(text.charAt(i));
            var length = binary.length();
            var n = 8 - binary.length();
            // System.out.println(binary + " " + length + " " + n);
            binary = new String(new char[n]).replace("\0", "0") + binary;
            // System.out.println(binary);
            result[i] = (flag) ? sdes.encrypt(binary) : sdes.decrypt(binary);
            // System.out.println(result[i]);
        }

        for(String i : result) {
            // System.out.print(i + " ");
            res = res +  (char) Integer.parseInt(i, 2);
        }
        // System.out.println();
        System.out.println(res);
        return res;
    }

    public void init() {
        var text = "0123456789ABCDEFGHIJK";
        String temp = text;
        var columnarKey = "3-1-2-0-4";
        ColumnarTransposition cl = new ColumnarTransposition();
        ShiftRows sr = new ShiftRows();
        KeysGenerator keySDES = new KeysGenerator("1010101010");
        SDES sdes = new SDES(keySDES);

        /*
        var encry = sdesFunction("Hola! Esta es una prueba de encryptamiento", sdes, true); // true -> Encrypt
        var decry = sdesFunction(encry, sdes, false); // false -> Decrypt
         */

        for(int i = 0; i<3; i++) {
            temp = cl.encrypt(temp,columnarKey);
            System.out.println("Encrypt Columnar Transposition " + i + ": " + temp);
        }

        temp = sr.encrypt(temp);
        System.out.println("Shift Row Stage " + temp);

        // -------------------------------------------

        temp = sdesFunction(temp, sdes, true);
        temp = sdesFunction(temp, sdes, false);

        // -------------------------------------------

        temp = sr.decrypt(temp);
        System.out.println("Inverse Shift Row Stage " + temp);

        for(int i = 0; i<3; i++) {
            temp = cl.decrypt(temp,columnarKey);
            System.out.println("Decrypt Columnar Transposition " + i + ": " + temp);
        }
    }
}
