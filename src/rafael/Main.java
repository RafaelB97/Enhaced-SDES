package rafael;

public class Main {

    public static void main(String[] args) {
	// write your code here
        // new KeysGenerator("1010000010");
        var keys = new KeysGenerator("1010101010");
        var temp = new SDES("11110000", keys);

    }
}
