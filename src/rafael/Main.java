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
        new Enhanced().init();
    }
}
