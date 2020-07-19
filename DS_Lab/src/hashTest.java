public class hashTest {
    public static void main(String args[]) {
        MyHash h = new MyHash(17);
        h.add(34);
        h.add(0);
        h.add(33);
        h.add(6);
        h.add(23);
        h.add(7);
        h.add(28);
        h.add(12);
        h.add(29);
        h.add(11);
        h.add(30);
        h.add(45);
        System.out.println(h.toString());
        System.out.println(h.toString2());
        h.remove(0);
        System.out.println(h.toString());
        System.out.println(h.toString2());
        h.add(51);
        h.add(52);
        h.add(53);
        h.add(54);
        h.add(55);
        h.add(56);
        System.out.println(h.toString());
        System.out.println(h.toString2());
    }
}
