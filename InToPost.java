import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class InToPost {
    public static void main(String[] args) {
        String expression = "4+6*(5-2)/3";
        List<String> infixExpressionList = toInfExpList(expression);
        System.out.println("инфиксное выражение:" + expression);
        String listString = String.join("", parSufExpList(infixExpressionList));
        System.out.println("Постфиксное выражение:" + listString);
        System.out.printf("Результат вычисления=%d", calculate(parSufExpList(infixExpressionList)));
    }


    public static List<String> parSufExpList(List<String> list) {
        Stack<String> s1 = new Stack<>();
        List<String> s2 = new ArrayList<>();
        for (String item : list) {
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();
            } else {
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;

    }


    public static List<String> toInfExpList(String s) {
        List<String> list = new ArrayList<>();
        int i = 0;
        StringBuilder str;
        char c;
        do {
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                list.add("" + c);
                i++;
            } else {
                str = new StringBuilder();
                while (i < s.length() && s.charAt(i) >= 48 && (c = s.charAt(i)) <= 57) {
                    str.append(c);
                    i++;
                }
                list.add(str.toString());
            }
        } while (i < s.length());
        return list;
    }


    public static int calculate(List<String> ls) {

        Stack<String> stack = new Stack<>();
        for (String item : ls) {
            if (item.matches("\\d+")) {
                stack.push(item);
            } else {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = switch (item) {
                    case "+" -> num1 + num2;
                    case "-" -> num1 - num2;
                    case "*" -> num1 * num2;
                    case "/" -> num1 / num2;
                    default -> throw new RuntimeException("Неправильный оператор");
                };
                stack.push(res + "");
            }
        }
        return Integer.parseInt(stack.pop());
    }
}

class Operation {
    private static final int ADD = 1;
    private static final int SUB = 1;
    private static final int MUL = 2;
    private static final int DIV = 2;


    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+" -> result = ADD;
            case "-" -> result = SUB;
            case "*" -> result = MUL;
            case "/" -> result = DIV;
            default -> {
            }
        }
        return result;
    }
}

