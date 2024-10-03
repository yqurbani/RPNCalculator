import java.util.Stack;
import java.util.Scanner;

public class PostFixEvaluator {
    private final static char ADD = '+';
    private final static char SUBTRACT = '-';
    private final static char MULTIPLY = '*';
    private final static char DIVIDE = '/';
    private final static char MODULUS = '%';
    private final static char UNARYMINUS = 'm';
    private final static char EXCHANGE = 'r';
    private final static char DUPLICATE = 'd';
    private final static char PRINT = 'p';
    private final static char PRINTREMOVETOP = 'n';
    private final static char PRINTALL = 'f';
    private final static char CLEAR = 'c';
    private final static char HELP = 'h';
    private final static char QUESTIONHELP = '?';

    private Stack<Integer> stack;

    /*
    FUNCTION NAME: PostFixEvaluator

    INPUT: None.

    OUTPUT: Creates a new integer stack.

    PRECONDITIONS: None.

    POSTCONDITIONS: Creates a new integer stack.

    CALLERS: Main class

    CALLEES: none.
    */
    public PostFixEvaluator() {
        stack = new Stack<Integer>();
    }

    /*
    FUNCTION NAME: evaluate

    INPUT: String expression.

    OUTPUT: will print, clear, or edit the stack depending on 
    the users input. will print error and has user try again until
    two valid opperands are entered.

    PRECONDITIONS: needs string (opperator symbol/letter), 
    two opperands.
    
    POSTCONDITIONS: performs opperations depending on opperands 
    and opperators involved.  

    CALLERS: Main class

    CALLEES: printHelp, requiresTwoOperands, 
    evaluateSingleOperator, isOpperator.
    */
    public int evaluate(String expr) {
        int op1, op2, result = 0;
        String token;
        Scanner parser = new Scanner(expr);
        while (parser.hasNext()) {
            token = parser.next();
            if (isOperator(token)) {
                if (stack.size() < 2 && requiresTwoOperands
                    (token.charAt(0))) {
                    System.out.println
                        ("Incorrect operands, try again.");
                    continue;
                }

                if (token.charAt(0) == UNARYMINUS) {
                    op2 = stack.pop();
                    result = op2 * -1;
                    stack.push(result);
                    System.out.println(result);
                } else if (token.charAt(0) == DUPLICATE) {
                    op2 = stack.peek();
                    stack.push(op2);
                    System.out.println(op2);
                } else if (token.charAt(0) == EXCHANGE) {
                    op2 = stack.pop();
                    op1 = stack.pop();
                    stack.push(op2);
                    stack.push(op1);
                    System.out.println(op1 + " " + op2);
                } else if (token.charAt(0) == PRINT) {
                    System.out.println(stack.peek());
                } else if (token.charAt(0) == PRINTREMOVETOP) {
                    System.out.println(stack.pop());
                } else if (token.charAt(0) == PRINTALL) {
                    for (int val : stack) {
                        System.out.print(val + " ");
                    }
                } else if (token.charAt(0) == CLEAR) {
                    stack.clear();
                } else if (token.charAt(0) == HELP 
                           || token.charAt(0) == QUESTIONHELP) {
                    printHelp();
                } else {
                    op2 = stack.pop();
                    op1 = stack.pop();
                    result = evaluateSingleOperator(token.charAt(0), op1, op2);
                    stack.push(result);
                    System.out.println(result);
                }
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        parser.close();
        if (stack.isEmpty()) {
          return 0; // Return 0 if the stack is empty
        } else {
          return stack.peek(); // Return the top element of the stack
        } 
    }

    /*
    FUNCTION NAME: isOpperator

    INPUT: String token (opperator).

    OUTPUT: None.

    PRECONDITIONS: needs string (opperator symbol/letter), 
    two opperands.

    POSTCONDITIONS: returns true if opperator based on 
    the corresponding token/symbol is valid. 
    this allows the opperations within the if statement in 
    evaluate method to commence.

    CALLERS: evaluate method 

    CALLEES: none
    */
    private boolean isOperator(String token) {
        return token.matches("[ + - * / % m r d p n f c h ?]");
    }

    /*
    FUNCTION NAME: requiresTwoOperands

    INPUT: opperation symbol/token.

    OUTPUT: returns true.

    PRECONDITIONS: needs opperation symbol

    POSTCONDITIONS: returns true if opperation one of the following.  

    CALLERS: evaluate method

    CALLEES: none
    */
    private boolean requiresTwoOperands(char operation) {
        return (operation == ADD || operation == SUBTRACT || operation == MULTIPLY ||
                operation == DIVIDE || operation == MODULUS || operation == EXCHANGE || operation == DUPLICATE || operation == PRINT || operation == PRINTREMOVETOP || operation == PRINTALL || operation == CLEAR || operation == UNARYMINUS || operation == HELP || operation == QUESTIONHELP);
    }

    /*
    FUNCTION NAME: evaluateSingleOperator

    INPUT: operation char from requiresTwoOperands, both opperands.

    OUTPUT: returns completed mathematical opperation. 
    throws exception if user inputs invalid opperator.

    PRECONDITIONS: needs opperation symbol/char and both opperands.

    POSTCONDITIONS: returns result of mathematical opperation 
    depending on which opperation symbol is used. 

    CALLERS: evaluate method

    CALLEES: none
    */
    private int evaluateSingleOperator(char operation, int op1, int op2) {
        switch (operation) {
            case ADD:
                return op1 + op2;
            case SUBTRACT:
                return op1 - op2;
            case MULTIPLY:
                return op1 * op2;
            case DIVIDE:
                return op1 / op2;
            case MODULUS:
                return op1 % op2;
            default:
                throw new UnsupportedOperationException("Unknown operator: " + operation);
        }
    }

    /*
    FUNCTION NAME: printHelp

    INPUT: none.

    OUTPUT: prints menu of opperations.

    PRECONDITIONS: none.

    POSTCONDITIONS: prints menu of opperations. 

    CALLERS: evaluate method and main class

    CALLEES: none
    */
    public void printHelp() {
        System.out.println("p print top");
        System.out.println("n print top and remove");
        System.out.println("d duplicate top");
        System.out.println("r exchange top two items");
        System.out.println("f print stack");
        System.out.println("c clear stack");
        System.out.println("+ add");
        System.out.println("- subtract");
        System.out.println("* multiply");
        System.out.println("/ divide");
        System.out.println("% modulus");
        System.out.println("m unary minus");
        System.out.println("q quit");
        System.out.println("h, ? help");
    }
}