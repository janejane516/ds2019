import java.util.Stack;

public class ParenthesisMatching {


    /**
     * output the matched parenthesis pairs in the string expr
     */
    public static void printMatchedPairs(String expr) {
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<expr.length(); i++) {
            if (expr.charAt(i) == '(') {
                stack.push(i);
            }
            else if (expr.charAt(i) == ')') {
                if(!stack.empty()) {
                    System.out.print(stack.pop());
                    System.out.println(" " + i);
                }
                else {
                    System.out.println("No match for right parenthesis at " + i);
                }
            }
        }
        while(!stack.empty()) {
            System.out.println("No match for left parenthesis at " + stack.pop());
        }
    }



}


