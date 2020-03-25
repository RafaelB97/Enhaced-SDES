package rafael;

public class ColumnarTransposition {
    public String encrypt(String text) {
        String[][] matrix = new String[5][5];
        String temp = "";
        int[] key = {3, 1, 2, 0, 4};
        // int[] key = {1, 2, 0};

        var n = 0;
        for(int i = 0; i<matrix.length; i++) {
            for(int j = 0; j<matrix[i].length; j++) {
                matrix[i][j] = "" + text.charAt(n);
                System.out.print(matrix[i][j] + " ");
                n++;
            }
            System.out.println();
        }

        for(int i : key) {
            for(int j = 0; j<matrix.length; j++) {
                // System.out.print(j + " " + i + " | ");
                System.out.print(matrix[j][i] + " ");
                temp = temp + matrix[j][i];
            }
            System.out.println();
        }

        return temp;
    }

    public String decrypt(String text) {
        String[][] matrix = new String[5][5];
        String temp = "";
        int[] key = {3, 1, 2, 0, 4};

        var n = 0;
        for(int i : key) {
            for(int j = 0; j<matrix.length; j++) {
                // System.out.print(j + " " + i + " | ");
                matrix[j][i] = "" + text.charAt(n);
                System.out.print(matrix[j][i] + " ");
                n++;
            }
            System.out.println();
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
        // cl.encrypt("DIDYOUSEE");
        System.out.println(cl.encrypt("0123456789ABCDEFGHIJKLMNO"));
        System.out.println(cl.decrypt("38DIN16BGL27CHM05AFK49EJO"));
    }
}
