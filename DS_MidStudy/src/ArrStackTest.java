public class ArrStackTest {
    public static void main(String[] args) {
        ArrayStack a = new ArrayStack(100);
        a.push(3);
        a.push(5);
        a.push(1);
        a.push(10);
        Object p = a.pop();
        System.out.println(p.toString());
    }
}
