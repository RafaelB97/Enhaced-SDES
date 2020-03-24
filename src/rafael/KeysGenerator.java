package rafael;

public class KeysGenerator implements IKeysGenerator {
    private String k1;
    private String k2;

    public KeysGenerator(String key) {
        String temp = P10(key);
        System.out.println("P10: " + temp);
        int mid = temp.length() / 2;
        System.out.println("Split");
        System.out.println(temp.substring(0, mid));
        System.out.println(temp.substring(mid));
        String shift1[] = {LS1(temp.substring(0, mid)), LS1(temp.substring(mid))};
        System.out.println("Shift1");
        System.out.println(shift1[0]);
        System.out.println(shift1[1]);
        k1 = P8(shift1[0] + shift1[1]);
        System.out.println("Key1: " + k1);

        System.out.println("Shift2");
        String shift2[] = {LS2(shift1[0]), LS2(shift1[1])};
        System.out.println(shift2[0]);
        System.out.println(shift2[1]);

        k2 = P8(shift2[0] + shift2[1]);
        System.out.println("Key2: " + k2);
    }

    public String P10(String key) {
        String temp[] = key.split("");
        /*
        System.out.println(temp.length);

        for(String i : temp) {
            System.out.println(i);
        }
        */

        return temp[2] + temp[4] + temp[1] + temp[6] + temp[3] + temp[9] + temp[0] + temp[8] + temp[7] + temp[5];
    }

    public String LS1(String key) {
        String temp[] = key.split("");
        return key.substring(1) + temp[0];

    }

    public String LS2(String key) {
        String temp[] = key.split("");
        return key.substring(2) + temp[0] + temp[1];

    }

    public String P8(String key) {
        String temp[] = key.split("");
        return temp[5] + temp[2] + temp[6] + temp[3] + temp[7] + temp[4] + temp[9] + temp[8];
    }

    public String getK1() {
        return k1;
    }

    public String getK2() {
        return k2;
    }
}
