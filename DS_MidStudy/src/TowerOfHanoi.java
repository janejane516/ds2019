public class TowerOfHanoi {
    public static void main(String[] args) {
        System.out.println("The moves are: ");
        Hanoi(3, 1, 2, 3);
    }
    public static void Hanoi(int n, int src, int tar, int aux) {
        if(n>0) {
            Hanoi(n-1, src, aux, tar);
            System.out.println("Move top disk from tower " + src + " to top of tower " + aux);
            Hanoi(n-1,aux, src, tar);
        }
        else return;
    }
}
