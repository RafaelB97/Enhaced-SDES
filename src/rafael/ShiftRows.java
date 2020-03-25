package rafael;

public class ShiftRows {

    public String encrypt(String text) {
        String[] temp = stringToArray(text);
        for(int i = 0; i<temp.length; i++) {
            temp[i] = temp[i].substring(i) + temp[i].substring(0,i);
            // System.out.println(temp[i]);
        }

        // System.out.println(String.join("", temp));
        return String.join("", temp);
    }

    public String decrypt(String text) {
        String[] temp = stringToArray(text);
        for(int i = 0; i<temp.length; i++) {
            temp[i] = temp[i].substring(temp[i].length()-i) + temp[i].substring(0,temp[i].length()-i);
            // System.out.println(temp[i]);
        }
        // System.out.println(String.join("", temp));
        return String.join("", temp).trim();
    }

    public String[] stringToArray(String text) {
        // System.out.println(text);
        int wordLength = text.length();
        int columns = (int) Math.sqrt(text.length()) + 1;
        // int columns = 11;
        int rows = (wordLength % columns == 0) ? wordLength / columns : (wordLength / columns) + 1;
        // System.out.println("wordLength: " + wordLength);
        // System.out.println("columns: " + columns);
        // System.out.println("rows: " + rows);

        String[] temp = new String[rows];
        int j = 0;
        for(int i = 0; i<temp.length; i++) {
            if(j+columns<=wordLength) {
                temp[i] = text.substring(j, j+columns);
                // System.out.println(temp[i] + " " + j + " " + (j+columns));
                j = j + columns;
            }else {
                temp[i] = text.substring(j,wordLength);
                // System.out.println(temp[i].length());
                temp[i] = temp[i] + new String(new char[columns-temp[i].length()]).replace("\0", " ");
                // System.out.println(temp[i].length());
                // System.out.println(temp[i]);
            }
        }
        return temp;
    }

    public static void main(String[] args) {
        ShiftRows sw = new ShiftRows();
        // sw.encrypt(sw.stringToArray("hello world!"));
        String originalText = "Hola! Esta es una prueba de encryptamiento";
        System.out.println("Original Text: " + originalText);
        String encryptText = sw.encrypt(originalText);
        System.out.println("Encrypt Text: " + encryptText);
        String decryptText = sw.decrypt(encryptText);
        System.out.println("Decrypt Text: " + decryptText);

        System.out.println();

        originalText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit";
        System.out.println("Original Text: " + originalText);
        encryptText = sw.encrypt(originalText);
        System.out.println("Encrypt Text: " + encryptText);
        decryptText = sw.decrypt(encryptText);
        System.out.println("Decrypt Text: " + decryptText);
    }
}
