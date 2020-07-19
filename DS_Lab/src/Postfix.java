import java.util.*;
public class Postfix {
    public static void main(String[] args) {
        String s = "(2+8)*(3-8)/(4+2)";
        String result = infixToPostFix(s);
        int answer = calculate(result);
        System.out.println(answer);
    }

    public static int calculate(String expression) {
        int answer;
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if(precedence(c) > 0) {
                int b = stack.pop();
                int a = stack.pop();
                int k;
                switch (c) {
                    case '*':
                        k = a * b;
                        break;
                    case '/':
                        k = a / b;
                        break;
                    case '+':
                        k = a + b;
                        break;
                    case '-':
                        k = a - b;
                        break;
                    default:
                        k = 0;
                        break;
                }
                stack.push(k);
            }
            else {
                String c_tmp = Character.toString(c);
                stack.push(Integer.parseInt(c_tmp));
            }
        }
        answer = stack.pop();
        return answer;
    }

    public static String infixToPostFix(String expression){
        String result = "";
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < expression.length() ; i++) {
            char c = expression.charAt(i);

            if(precedence(c) > 0){
                while(!stack.isEmpty() && precedence(stack.peek())>=precedence(c)){
                    result += stack.pop();
                }
                stack.push(c);
            }
            else if(c==')') {
                char x = stack.pop();
                while(x!='('){
                    result += x;
                    x = stack.pop();
                }
            }
            else if(c=='('){
                stack.push(c);
            }
            else {
                result += c;
            }
        }
        while(!stack.empty()) {
            result += stack.pop();
        }
        return result;
    }


    public static int precedence(char op) {
        if(op == '*' || op == '/')
            return 2;
        if(op == '+' || op == '-')
            return 1;
        else
            return -1;
    }
}
