import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {

    private static final char PLUS = '+';
    private static final char MINUS = '-';
    private static final char MULTIPLY = '*';
    private static final char DIVIDE = '/';
    private static final char LEFT_BRACKET = '(';
    private static final char RIGHT_BRACKET = ')';

    private static final String INVALID_CHARACTER_MESSAGE = "Invalid character: ";

    public final SimpleTreeNode<ANode> root = new SimpleTreeNode<>(new ANode(), null);
    private final SimpleTree<ANode> simpleTree = new SimpleTree<>(root);

    public SimpleTree<ANode> calculateAST(List<ANode> tokenList) {
        return AST(tokenList, root);
    }

    private SimpleTree<ANode> AST(List<ANode> tokenList, SimpleTreeNode<ANode> node) {
        if (tokenList.size() == 0) return simpleTree;
        ANode token = tokenList.get(0);
        if (token.getNodeType().equals(NodeType.LEFT_BRACKET)) {
            SimpleTreeNode<ANode> newNode = new SimpleTreeNode<>(null, node);
            simpleTree.AddChild(node, newNode);
            List<ANode> newList = new ArrayList<>(tokenList);
            newList.remove(token);
            return AST(newList, newNode);
        }
        if (token.getNodeType().equals(NodeType.RIGHT_BRACKET)) {
            List<ANode> newList = new ArrayList<>(tokenList);
            newList.remove(token);
            return AST(newList, node.Parent);
        }
        if (token.getNodeType().equals(NodeType.DIGIT)) {
            node.NodeValue = token;
            List<ANode> newList = new ArrayList<>(tokenList);
            newList.remove(token);
            return AST(newList, node.Parent);
        }
        if (token.getNodeType().equals(NodeType.OPERATOR)) {
            node.NodeValue = token;
            SimpleTreeNode<ANode> newNode = new SimpleTreeNode<>(null, node);
            simpleTree.AddChild(node, newNode);
            List<ANode> newList = new ArrayList<>(tokenList);
            newList.remove(token);
            return AST(newList, newNode);
        }
        return simpleTree;
    }

    public List<ANode> parseString(String expression) {
        return addBrackets(parse(expression));
    }

    public List<ANode> parse(String expression) {
        List<ANode> tokens = new ArrayList<>();
        ANode currentToken = null;

        for (char c : expression.toCharArray()) {

            if (!isDigit(c) && !isOperation(c)) throw new IllegalArgumentException(INVALID_CHARACTER_MESSAGE + c);

            if (isDigit(c) && currentToken == null) {
                currentToken = new ANode(NodeType.DIGIT, Character.toString(c));
                continue;
            }
            if (isDigit(c) && currentToken != null) {
                String currentTokenValue = currentToken.getValue();
                String newTokenValue = currentTokenValue + c;
                currentToken.setValue(newTokenValue);
                continue;
            }
            if (currentToken != null) {
                tokens.add(currentToken);
                currentToken = null;
            }
            tokens.add(new ANode(NodeType.OPERATOR, Character.toString(c)));
        }
        if (currentToken != null) {
            tokens.add(currentToken);
        }
        return tokens;
    }

    public List<ANode> addBrackets(List<ANode> tokens) {
        Integer lowestPriorityOperatorIndex = findLowestPriorityOperatorIndex(tokens);
        if (lowestPriorityOperatorIndex == null) return tokens;
        List<ANode> leftTokens = tokens.subList(0, lowestPriorityOperatorIndex);
        List<ANode> rightTokens = tokens.subList(lowestPriorityOperatorIndex + 1, tokens.size());
        List<ANode> newTokens = new ArrayList<>(wrapInBracketsIfNeeded(leftTokens));
        newTokens.add(tokens.get(lowestPriorityOperatorIndex));
        newTokens.addAll(addBrackets(rightTokens));
        return wrapInBracketsIfNeeded(newTokens);
    }

    public List<ANode> wrapInBracketsIfNeeded(List<ANode> tokens) {
        if (tokens.size() <= 1) return tokens;
        List<ANode> newTokens = new ArrayList<>();
        newTokens.add(new ANode(NodeType.LEFT_BRACKET, String.valueOf(LEFT_BRACKET)));
        newTokens.addAll(tokens);
        newTokens.add(new ANode(NodeType.RIGHT_BRACKET, String.valueOf(RIGHT_BRACKET)));
        return newTokens;
    }

    public Integer findLowestPriorityOperatorIndex(List<ANode> tokens) {
        List<String> operators = Arrays.asList(String.valueOf(PLUS), String.valueOf(MINUS), String.valueOf(MULTIPLY), String.valueOf(DIVIDE));
        Integer lowestPriorityOperatorIndex = null;
        int lowestPriority = Integer.MAX_VALUE;
        for (int i = 0; i < tokens.size(); i++) {
            ANode token = tokens.get(i);
            if (!(token.getNodeType().equals(NodeType.OPERATOR) && operators.contains(token.getValue()))) continue;
            int priority = operators.indexOf(token.getValue());
            if (priority < lowestPriority) {
                lowestPriority = priority;
                lowestPriorityOperatorIndex = i;
            }
        }
        return lowestPriorityOperatorIndex;
    }

    private boolean isOperation(char c) {
        return c == PLUS || c == MINUS || c == MULTIPLY || c == DIVIDE || c == LEFT_BRACKET || c == RIGHT_BRACKET;
    }

    private boolean isDigit(char c) {
        return Character.isDigit(c);
    }
}

