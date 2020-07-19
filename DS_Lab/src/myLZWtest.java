public class myLZWtest {
    public static void main(String args[]) {

        String s = "abababbabaabbabbaabbaabbaa";
        int[] D = {0,2,1,4,5,3,7};
        myLZW L = new myLZW();
        System.out.println(L.encoding(s));
        System.out.println(L.decoding(D));

        String s2 = "abababbaabbabaaabbab";
        myLZW L2 = new myLZW();
        System.out.println(L2.encoding(s2));
    }
}
