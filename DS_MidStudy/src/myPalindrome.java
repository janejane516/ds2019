import java.util.Scanner;

public class myPalindrome {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        String s = console.next();
        System.out.println(isPalindrome(s));
    }

    static boolean isPalindrome(String s) {
        int n = s.length();
        if(s.length()==0 || s.length()==1)
            return true;
        if(s.charAt(0) == s.charAt(s.length()-1))
            return isPalindrome(s.substring(1, s.length()-1));
        return false;
    }
}
