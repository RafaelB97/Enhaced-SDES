package rafael;

public class SDES {
    private String[][] s0 = {{"01", "00", "11", "10"},
                             {"11", "10", "01", "00"},
                             {"00", "10", "01", "11"},
                             {"11", "01", "11", "10"} };

    private String[][] s1 = {{"00", "01", "10", "11"},
                             {"10", "00", "01", "11"},
                             {"11", "00", "01", "01"},
                             {"10", "01", "00", "11"}};

    private IKeysGenerator keys = null;

    public SDES(IKeysGenerator keys) {
        this.keys = keys;
    }

    public String encrypt(String text) {
        String ip = IP(text);
        // System.out.println("IP: " + ip);
        int mid = ip.length() / 2;
        String f1 = function(ip.substring(0,mid), ip.substring(mid), keys.getK1());
        // System.out.println(f1);
        String f2 = function(f1.substring(mid), f1.substring(0, mid), keys.getK2());
        // System.out.println(f2);

        String inv = IPInv(f2);
        // System.out.println("IP Inv: " + inv);
        return inv;
    }

    public String decrypt(String text) {
        String ip = IP(text);
        // System.out.println("IP: " + ip);
        int mid = ip.length() / 2;
        String f1 = function(ip.substring(0,mid), ip.substring(mid), keys.getK2());
        // System.out.println(f1);
        String f2 = function(f1.substring(mid), f1.substring(0, mid), keys.getK1());
        // System.out.println(f2);

        String inv = IPInv(f2);
        // System.out.println("IP Inv: " + inv);
        return inv;
    }

    public String function(String text1, String text2, String key) {
        String ep = EP(text2);
        // System.out.println("EP: " + ep);
        String xor = XOR(ep, key);
        // System.out.println("XOR: " + xor);
        int mid = xor.length() / 2;
        String left = sBox(xor.substring(0,mid), true); //true => SBox0
        String right = sBox(xor.substring(mid), false); //false => SBox1
        // System.out.println("S0: " + left + " S1: " + right);
        String p4 = P4(left + right);
        // System.out.println("P4: " + p4);
        String xor2 = XOR(p4, text1);
        // System.out.println("XOR: " + xor2);
        return xor2 + text2;
    }

    public String IP(String text) {
        String temp[] = text.split("");
        return temp[1] + temp[5] + temp[2] + temp[0] + temp[3] + temp[7] + temp[4] + temp[6];
    }

    public String IPInv(String text) {
        String temp[] = text.split("");
        return temp[3] + temp[0] + temp[2] + temp[4] + temp[6] + temp[1] + temp[7] + temp[5];
    }

    public String EP(String text) {
        String temp[] = text.split("");
        return temp[3] + temp[0] + temp[1] + temp[2] + temp[1] + temp[2] + temp[3] + temp[0];
    }

    public String P4(String text) {
        String temp[] = text.split("");
        return temp[1] + temp[3] + temp[2] + temp[0];
    }

    public String sBox(String text, Boolean opc) {
        // String str = String.valueOf(text.charAt(0) + text.charAt(3));
        String StrRow = "" + text.charAt(0) + text.charAt(3);
        int row = Integer.parseInt(StrRow, 2);
        String StrColumn = "" + text.charAt(1) + text.charAt(2);
        int column = Integer.parseInt(StrColumn, 2);
        // System.out.println(row + " " + column);
        var i = (opc) ? s0[row][column] : s1[row][column];
        // System.out.println(i);
        return i;
    }

    public String XOR(String text1, String text2) {
        String temp = "";
        for(int i = 0; i < text1.length(); i++) {
            // System.out.println(text1.charAt(i) + " " + text2.charAt(i));
            temp = temp + (text1.charAt(i) ^ text2.charAt(i));
        }
        // System.out.println(temp);
        return temp;
    }
}
