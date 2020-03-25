package rafael;

public class ColumnarTransposition {
    public int[] keyResolution(String keyStr) {
        String[] temp = keyStr.split("-");
        int[] key = new int[temp.length];
        for(int i = 0; i<key.length; i++) {
            key[i] = Integer.parseInt(temp[i]);
        }
        return key;
    }

    public String encrypt(String text, String strKey) {
        int[] key = keyResolution(strKey);
        var m = key.length;
        var n = (text.length()%m == 0) ? text.length()/m : (text.length()/m) + 1;
        String[][] matrix = new String[n][m];
        String temp = "";

        var counter = 0;
        for(int i = 0; i<matrix.length; i++) {
            for(int j = 0; j<matrix[i].length; j++) {
                if(counter<text.length()) {
                    matrix[i][j] = "" + text.charAt(counter);
                    // System.out.print(matrix[i][j] + " ");
                    counter++;
                } else {
                    matrix[i][j] = " ";
                    // System.out.print(matrix[i][j] + " ");
                }
            }
            // System.out.println();
        }

        for(int i : key) {
            for(int j = 0; j<matrix.length; j++) {
                // System.out.print(j + " " + i + " | ");
                // System.out.print(matrix[j][i] + " ");
                temp = temp + matrix[j][i];
            }
            // System.out.println();
        }

        return temp;
    }

    public String decrypt(String text, String strKey) {
        int[] key = keyResolution(strKey);
        var m = key.length;
        var n = (text.length()%m == 0) ? text.length()/m : (text.length()/m) + 1;
        String[][] matrix = new String[n][m];
        String temp = "";

        var counter = 0;
        for(int i : key) {
            for(int j = 0; j<matrix.length; j++) {
                if(counter<text.length()) {
                    // System.out.print(j + " " + i + " | ");
                    matrix[j][i] = "" + text.charAt(counter);
                    // System.out.print(matrix[j][i] + " ");
                    counter++;
                } else {
                    matrix[j][i] = " ";

                }
            }
            // System.out.println();
        }

        for(int i = 0; i<matrix.length; i++) {
            for(int j = 0; j<matrix[i].length; j++) {
                temp = temp + matrix[i][j];
            }
        }

        return temp;
    }

    public static void main(String[] args) {
        ColumnarTransposition cl = new ColumnarTransposition();
        String text = "DIDYOUSEE";
        System.out.println(text);
        String encrypText = cl.encrypt(text,"3-1-2-0-4");
        System.out.println(encrypText);
        String decryptText = cl.decrypt(encrypText,"3-1-2-0-4");
        System.out.println(decryptText);
        // System.out.println(cl.encrypt("0123456789ABCDEFGHIJKLMNO", "3-1-2-0-4"));
    }
}
