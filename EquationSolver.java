public class EquationSolver {

    // Solve method: parses infix to postfix, then evaluates
    public double solve(String equation) {
        DSAQueue postfixQueue = parseInfixToPostfix(equation);
        return evaluatePostfix(postfixQueue);
    }

    // Converts infix equation to postfix
    private DSAQueue parseInfixToPostfix(String equation) {
        String[] tokens = equation.split(" ");
        DSAStack opStack = new DSAStack(50); // stack for operators
        ShufflingQueue postfixQueue = new ShufflingQueue(50); // queue for output

        for (String token : tokens) {
            char firstChar = token.charAt(0);

            if (Character.isDigit(firstChar)) {
                // Token is a number
                postfixQueue.enqueue(Double.valueOf(token));
            } else if (firstChar == '(') {
                opStack.push(token.charAt(0));
            } else if (firstChar == ')') {
                // Pop operators until '('
                while (!opStack.isEmpty() && (char) opStack.peek() != '(') {
                    postfixQueue.enqueue(opStack.pop());
                }
                if (!opStack.isEmpty() && (char) opStack.peek() == '(') {
                    opStack.pop(); // remove '('
                }
            } else {
                // Operator + - * /
                while (!opStack.isEmpty() && opStack.peek() instanceof Character
                        && precedenceOf((char) opStack.peek()) >= precedenceOf(firstChar)) {
                    postfixQueue.enqueue(opStack.pop());
                }
                opStack.push(firstChar);
            }
        }

        // Pop remaining operators
        while (!opStack.isEmpty()) {
            postfixQueue.enqueue(opStack.pop());
        }

        return postfixQueue;
    }

    // Evaluates a postfix queue
    private double evaluatePostfix(DSAQueue postfixQueue) {
        DSAStack operandStack = new DSAStack(50);

        while (!postfixQueue.isEmpty()) {
            Object token = postfixQueue.dequeue();

            if (token instanceof Double) {
                operandStack.push(token);
            } else if (token instanceof Character) {
                double op2 = (Double) operandStack.pop();
                double op1 = (Double) operandStack.pop();
                char operator = (Character) token;
                double result = executeOperation(operator, op1, op2);
                operandStack.push(result);
            }
        }

        return (Double) operandStack.pop();
    }

    // Helper to execute binary operation
    private double executeOperation(char op, double op1, double op2) {
        switch (op) {
            case '+': return op1 + op2;
            case '-': return op1 - op2;
            case '*': return op1 * op2;
            case '/': return op1 / op2;
            default: throw new IllegalArgumentException("Unknown operator: " + op);
        }
    }

    // Helper to get operator precedence
    private int precedenceOf(char op) {
        switch (op) {
            case '+':
            case '-': return 1;
            case '*':
            case '/': return 2;
            default: return 0;
        }
    }

    // Main method to test
    public static void main(String[] args) {
        EquationSolver solver = new EquationSolver();

        String equation1 = "2 + 3 * 4";
        String equation2 = "( 1 + 2 ) * 3";
        String equation3 = "10 / 2 - 1";

        System.out.println(equation1 + " = " + solver.solve(equation1));
        System.out.println(equation2 + " = " + solver.solve(equation2));
        System.out.println(equation3 + " = " + solver.solve(equation3));
    }
}