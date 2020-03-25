package rafael;

public class Enhanced {
    private String columnarKey;
    private ColumnarTransposition cl;
    private ShiftRows sr;
    private SDES sdes;

    public Enhanced(String columnarKey, String sdesKey) {
        this.columnarKey = columnarKey;
        cl = new ColumnarTransposition();
        sr = new ShiftRows();
        IKeysGenerator keySDES = new KeysGenerator(sdesKey);
        sdes = new SDES(keySDES);
    }

    public String sdesText(String text, Boolean flag) {
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

    public String encrypt(String text) {
        String temp = text;

        for (int i = 0; i < 3; i++) {
            temp = cl.encrypt(temp,columnarKey);
            System.out.println("Encrypt Columnar Transposition " + i + ": " + temp);
        }

        temp = sr.encrypt(temp);
        System.out.println("Shift Row Stage " + temp);

        // -------------------------------------------

        temp = sdesText(temp, true);

        return temp;
    }

    public String decrypt(String text) {
        String temp = text;

        temp = sdesText(temp, false);

        // -------------------------------------------

        temp = sr.decrypt(temp);
        System.out.println("Inverse Shift Row Stage " + temp);

        for(int i = 0; i<3; i++) {
            temp = cl.decrypt(temp,columnarKey);
            System.out.println("Decrypt Columnar Transposition " + i + ": " + temp);
        }
        return temp;
    }

    public void setColumnarKey(String columnarKey) {
        this.columnarKey = columnarKey;
    }
}
