package by.delmark.calculation;

import java.util.Set;
import java.util.Stack;

public class Calculator {

    private static final Set<String> supportedSyntax = Set.of("+", "-", "/", "*", "^", "(", ")");
    private static final Set<String> primitiveOperators = Set.of("+", "-", "/", "*", "^");
    private static final Set<String> functionOperators = Set.of("sin", "cos", "tan", "ctg", "abs");

    private static int isPriority(String op) {
        return switch (op){
            case "+", "-" -> 0;
            case "*", "/" -> 1;
            case "^" -> 2;
            case "sin", "cos", "tan", "ctg", "abs" -> 3;
            default -> throw new IllegalArgumentException("no such op");
        };
    }

    private static boolean isOp(char op) {
        return primitiveOperators.contains(String.valueOf(op));
    }

    private static boolean isNotBracket(char op) {
        return op != '(' && op != ')';
    }

    private static void validateExpression(String expression) {
        Stack<Character> openBracketStack = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            char symbol = expression.charAt(i);

            boolean digitOrDot = Character.isDigit(symbol) || symbol == '.';
            if (!digitOrDot && !supportedSyntax.contains(String.valueOf(symbol)) && i + 3 < expression.length()) {
                String potentialFunction = expression.substring(i, i + 3);
                if (functionOperators.contains(potentialFunction)) {
                    i += 2;
                } else {
                    throw new IllegalArgumentException("Error: unrecognized functions/operators on expression");
                }
            }

            if (symbol == '(') {
                openBracketStack.push(symbol);
            }
            if (symbol == ')') {
                if (openBracketStack.isEmpty()) {
                    throw new IllegalArgumentException("Error: Incorrect bracket placement");
                }
                openBracketStack.pop();
            }
        }
    }


    public static double calculate(String expression) {
        if (expression == null || expression.isEmpty()) {
            throw new IllegalArgumentException("Expression can't be null or empty");
        }
        expression = expression.replace(" ", "");
        validateExpression(expression);
        return calculateExpression(expression);
    }

    private static double calculateExpression(String expression) {
        Stack<Double> numbers = new Stack<>();
        Stack<String> operators = new Stack<>();

        int prevOpIdx = -1;
        for (int i = 0; i < expression.length(); i++) {
            char currentChar = expression.charAt(i);

            // если последним элементом выражения будет число, кидаем его в стек
            if (i == expression.length() - 1 && isNotBracket(currentChar)) {
                parseAndPutNumberIntoStack(expression, prevOpIdx, i + 1, numbers);
                continue;
            }

            // если полученный нами символ не какой-либо допустимый синтаксический символ
            // калькулятора - это часть функции или число, скипаем
            String operator = String.valueOf(currentChar);
            if (!supportedSyntax.contains(operator)) {
                continue;
            }

            // записываем минус после скобки/другого оператора как '*-1' в стек
            if (currentChar == '-' && (i == 0 || ((isOp(expression.charAt(i - 1))) || expression.charAt(i - 1) == '('))) {
                numbers.push(-1.);
                operators.push("*");
                prevOpIdx = i;
                continue;
            }

            // открывающая скобка может стоять перед функцией, попытаемся ещё распарсить
            if (currentChar == '(' && (i - 3 >= 0)) {
                String potentialFunction = expression.substring(i - 3, i);
                if (functionOperators.contains(potentialFunction)) {
                    operator = potentialFunction;
                }
            }

            // записываем число от предыдущего оператора до текущего и закидываем в стек,
            // только если между предыдущим и текущим оператором есть пространство
            // (его может не быть между ведущими минусами или скобками)
            if (i != 0 && i - 1 != prevOpIdx && !functionOperators.contains(operator)) {
                parseAndPutNumberIntoStack(expression, prevOpIdx, i, numbers);
            }

            if (isOp(currentChar) || currentChar == '(') {
                if (currentChar != '(') {
                    // если возможно, пытаемся посчитать уже накопленные выражения в стеке
                    while (!operators.isEmpty()
                        && isNotBracket(operators.peek().charAt(0))
                        && isNextCalculationAllowed(operators.peek(), operator)
                    ) {
                        processTopOperation(numbers, operators);
                    }
                }

                operators.push(operator);
                if (functionOperators.contains(operator)) {
                    operators.push("(");
                }
            }

            // если текущий символ закрывающая скобка, считаем все что внутри неё
            if (currentChar == ')') {
                while (!operators.isEmpty() && !operators.peek().equals("(")) {
                    processTopOperation(numbers, operators);
                }
                operators.pop(); // убираем открывающую скобку
            }

            prevOpIdx = i;
        }

        while (!operators.isEmpty()) {
            processTopOperation(numbers, operators);
        }

        return numbers.pop();
    }

    private static void parseAndPutNumberIntoStack(String expression, int prevOpIdx, int i, Stack<Double> numbers) {
        String strNum = expression.substring(prevOpIdx + 1, i);
        double num = Float.parseFloat(strNum);
        numbers.push(num);
    }

    // мы должны запретить вычисление там, где предыдущий оператор ниже по приоритету чем текущий
    // пример: 3+3*6
    // т.е нельзя вычислять 3+3 (prevOp = +), когда следующий оператор * (currentOp = *),
    // если этого не делать мы получим 6*6, вместо вычисления 3+18;
    // разрешено проводить вычисления для одинаковых по приоритету операторов (3+3 и -) или если
    // предыдущий выше по приоритету (3*3 и +)
    private static boolean isNextCalculationAllowed(String prevOp, String currentOp) {
        return
            (isPriority(currentOp) == isPriority(prevOp)
                // исключение у возведения в степень, его нельзя считать динамически, только
                // справа-налево, т.к возведение в степени правоассоативно
                && !(currentOp.equals("^") && currentOp.equals(prevOp)))
            || isPriority(currentOp) < isPriority(prevOp);
    }

    private static void processTopOperation(Stack<Double> numbers, Stack<String> operators) {
        String op = operators.pop();

        // оператор размеров в 1 символ - примитив, более - функция
        double result;
        if (op.length() == 1) {
            double n1 = numbers.pop();
            double n2 = numbers.pop();
            result = switch (op) {
                case "+" -> n2 + n1;
                case "-" -> n2 - n1;
                case "*" -> n2 * n1;
                case "/" -> {
                    if (n1 == 0) {
                        throw new ArithmeticException("Error: Divide to zero");
                    }
                    yield n2 / n1;
                }
                case "^" -> Math.pow(n2, n1);
                default -> throw new IllegalArgumentException("incorrect op");
            };
        } else {
            double functionArg = numbers.pop();
            result = switch (op) {
                case "sin" -> Math.sin(functionArg);
                case "cos" -> Math.cos(functionArg);
                case "tan" -> Math.tan(functionArg);
                case "ctg" -> Math.pow(Math.tan(functionArg), -1);
                case "abs" -> functionArg < 0 ? -1 * functionArg : functionArg;
                default -> throw new IllegalArgumentException("incorrect op");
            };
        }
        numbers.push(result);
    }


    public static void main(String[] args) {
        System.out.println(calculate("2^3+4*sin(3.14/2)+2^-1*12/(5+1)"));
    }
}
