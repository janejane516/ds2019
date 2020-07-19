public class Lab03 {
    public static void main(String[] args) {
        MyLinkedList l = new MyLinkedList();
        System.out.println("isEmpty? " + l.isEmpty());
        System.out.println("size is " + l.size());
        System.out.println(l.remove(12));
        System.out.println("Index of 0: " + l.indexOf(0));
        l.add(5);
        l.add(2);
        l.add(4);
        l.add(10);
        l.add(7);
        l.add(4);
        l.print();
        System.out.println("size is " + l.size());
        System.out.println("Index of 2: " + l.indexOf(2));
        System.out.println("Index of 7: " + l.indexOf(7));
        System.out.println("Index of 10: " + l.indexOf(10));
        System.out.println(l.remove(4));
        System.out.println(l.remove(2));
        System.out.println(l.remove(2));
        l.print();
        System.out.println("isEmpty? " + l.isEmpty());
        System.out.println("size is " + l.size());
    }
}
