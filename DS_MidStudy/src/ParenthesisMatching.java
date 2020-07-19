import java.util.*;
public class ParenthesisMatching {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        Scanner console = new Scanner(System.in);
        String exp = console.next();
        Parenthesis(exp);
    }

    public static void Parenthesis(String exp) {
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<exp.length(); i++) {
            if(exp.charAt(i) == '(')
                stack.push(i);
            else if(exp.charAt(i) == ')') {
                if(stack.empty()) {
                    System.out.println("No parenthesis Matching at " + i);
                }
                else {
                    System.out.print(stack.pop());
                    System.out.println(" " + i);
                }
            }
        }
        while(!stack.empty()) {
            System.out.println("No parenthesis Matching at " + stack.pop());
        }
    }
}
