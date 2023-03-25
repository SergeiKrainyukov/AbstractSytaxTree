import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {

    public List<ANode> parseString(String expression) {
        List<ANode> tokens = parse(expression);
        List<ANode> newTokens = addBrackets(tokens);
        for (ANode token : newTokens) {
            System.out.print(token.getValue());
        }
        return newTokens;
    }

    // Создайте метод parse для разбора выражения:
    public List<ANode> parse(String expression) {
        List<ANode> tokens = new ArrayList<>();
        ANode currentToken = null;
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (Character.isDigit(c)) {
                if (currentToken == null) {
                    currentToken = new ANode("number", Character.toString(c));
                } else {
                    String currentTokenValue = currentToken.getValue();
                    String newTokenValue = currentTokenValue + c;
                    currentToken.setValue(newTokenValue);
                }
            } else if (c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')') {
                if (currentToken != null) {
                    tokens.add(currentToken);
                    currentToken = null;
                }
                tokens.add(new ANode("operator", Character.toString(c)));
            } else {
                throw new IllegalArgumentException("Invalid character: " + c);
            }
        }
        if (currentToken != null) {
            tokens.add(currentToken);
        }
        return tokens;
    }

    // Создайте метод addBrackets для добавления скобок к выражению:
    public List<ANode> addBrackets(List<ANode> tokens) {
        Integer lowestPriorityOperatorIndex = findLowestPriorityOperator(tokens);
        if (lowestPriorityOperatorIndex == null) {
            return tokens;
        }
        List<ANode> leftTokens = tokens.subList(0, lowestPriorityOperatorIndex);
        List<ANode> rightTokens = tokens.subList(lowestPriorityOperatorIndex + 1, tokens.size());
        List<ANode> newTokens = new ArrayList<>(wrapInBracketsIfNeeded(leftTokens));
        newTokens.add(tokens.get(lowestPriorityOperatorIndex));
        newTokens.addAll(addBrackets(rightTokens));
        return wrapInBracketsIfNeeded(newTokens);
    }

    //  Создайте метод wrapInBracketsIfNeeded для обертывания токенов в скобки при необходимости:
    public List<ANode> wrapInBracketsIfNeeded(List<ANode> tokens) {
        if (tokens.size() <= 1 || tokens.size() % 2 == 0) return tokens;
        List<ANode> newTokens = new ArrayList<>();
        newTokens.add(new ANode("open_paren", "("));
        newTokens.addAll(tokens);
        newTokens.add(new ANode("close_paren", ")"));
        return newTokens;
    }

    // Создайте метод findLowestPriorityOperator для поиска самого низкоприоритетного оператора:
    public Integer findLowestPriorityOperator(List<ANode> tokens) {
        List<String> operators = Arrays.asList("+", "-", "*", "/");
        Integer lowestPriorityOperator = null;
        int lowestPriority = Integer.MAX_VALUE;
        for (int i = 0; i < tokens.size(); i++) {
            ANode token = tokens.get(i);
            if (token.getTokenType().equals("operator") && operators.contains(token.getValue())) {
                int priority = operators.indexOf(token.getValue());
                if (priority < lowestPriority) {
                    lowestPriority = priority;
                    lowestPriorityOperator = i;
                }
            }
        }
        return lowestPriorityOperator;
    }
}

