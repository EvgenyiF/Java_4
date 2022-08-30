import java.util.Stack;

public class Solution1 {

    public static void main(String[] args) {
        String path = "/home/";
        System.out.println(simplifyPath(path));
    }


    public static String simplifyPath(String path) {

        if (path == null) return "/";
        Stack<String> stack = new Stack<>();
        for (String s : path.split("/")) {
            if (s.equals(".") || s.isEmpty()) continue;
            if (s.equals("..")) {
                if (stack.size() > 0) stack.pop();
                continue;
            }
            stack.push(s);
        }
        if (stack.isEmpty()) return "/";
        return "/" + String.join("/", stack);
    }
}
