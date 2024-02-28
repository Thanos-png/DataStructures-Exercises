import java.util.Scanner;
import java.util.NoSuchElementException;

public class PrefixToInfix {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a prefix expression: ");
        String prefixExpression = scanner.nextLine();

        try {
            String infixExpression = convertToInfix(prefixExpression);
            System.out.println("Infix expression: " + infixExpression);
        } catch (IllegalArgumentException | NoSuchElementException e) {
            System.err.println("Error: " + e.getMessage());
        }

        scanner.close();
    }

    public static String convertToInfix(String prefixExpression) {
        StringDoubleEndedQueueImpl<String> queue = new StringDoubleEndedQueueImpl<>();

        // Split the input expression into tokens
        char[] tokens = prefixExpression.toCharArray();

        for (int i = tokens.length - 1; i >= 0; i--) {
            char token = tokens[i];

            if (isOperator(token)) {
                if (queue.size() < 2) {
                    throw new IllegalArgumentException("Invalid prefix expression");
                }

                String operand1 = queue.removeFirst();
                String operand2 = queue.removeFirst();
                String infixSubExpression = "(" + operand1 + token + operand2 + ")";
                queue.addFirst(infixSubExpression);
            } else if (isOperand(token)) {
                queue.addFirst(Character.toString(token));
            } else {
                throw new IllegalArgumentException("Invalid character in the expression: " + token);
            }
        }

        if (queue.size() != 1) {
            throw new IllegalArgumentException("Invalid prefix expression");
        }

        return queue.getFirst();
    }

    private static boolean isOperator(char token) {
        return Character.toString(token).equals("+") || Character.toString(token).equals("-") || Character.toString(token).equals("*") || Character.toString(token).equals("/");
    }

    private static boolean isOperand(char token) {
        return Character.toString(token).matches("\\d");
    }
}
