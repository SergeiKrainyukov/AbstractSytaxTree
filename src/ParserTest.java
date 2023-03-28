import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ParserTest {

    @Test
    public void parseString1() {
        Parser parser = new Parser();
        String expression = "5*3+4";
        String expectedString = "((5*3)+4)";
        List<ANode> nodeList = parser.parseString(expression);
        StringBuilder resultStringBuilder = new StringBuilder();
        for (ANode aNode : nodeList) {
            resultStringBuilder.append(aNode.getValue());
        }
        String actualString = resultStringBuilder.toString();
        assertEquals(actualString, expectedString);
    }

    @Test
    public void parseString2() {
        Parser parser = new Parser();
        String expression = "5+3*4";
        String expectedString = "(5+(3*4))";
        List<ANode> nodeList = parser.parseString(expression);
        StringBuilder resultStringBuilder = new StringBuilder();
        for (ANode aNode : nodeList) {
            resultStringBuilder.append(aNode.getValue());
        }
        String actualString = resultStringBuilder.toString();
        assertEquals(actualString, expectedString);
    }

    @Test
    public void parseString3() {
        Parser parser = new Parser();
        String expression = "5+3+4/2";
        String expectedString = "(5+(3+(4/2)))";
        List<ANode> nodeList = parser.parseString(expression);
        StringBuilder resultStringBuilder = new StringBuilder();
        for (ANode aNode : nodeList) {
            resultStringBuilder.append(aNode.getValue());
        }
        String actualString = resultStringBuilder.toString();
        assertEquals(actualString, expectedString);
    }

    @Test
    public void parseString4() {
        Parser parser = new Parser();
        String expression = "3+5-5+3";
        String expectedString = "(3+((5-5)+3))";
        List<ANode> nodeList = parser.parseString(expression);
        StringBuilder resultStringBuilder = new StringBuilder();
        for (ANode aNode : nodeList) {
            resultStringBuilder.append(aNode.getValue());
        }
        String actualString = resultStringBuilder.toString();
        assertEquals(actualString, expectedString);
    }

    @Test
    public void parseString5() {
        Parser parser = new Parser();
        String expression = "3*5-5/3";
        String expectedString = "((3*5)-(5/3))";
        List<ANode> nodeList = parser.parseString(expression);
        StringBuilder resultStringBuilder = new StringBuilder();
        for (ANode aNode : nodeList) {
            resultStringBuilder.append(aNode.getValue());
        }
        String actualString = resultStringBuilder.toString();
        assertEquals(actualString, expectedString);
    }

    @Test
    public void treeTest() {
        Parser parser = new Parser();
        String expression = "3+1";
        String expectedString = "(3+1)";
        List<ANode> nodeList = parser.parseString(expression);
        StringBuilder resultStringBuilder = new StringBuilder();
        for (ANode aNode : nodeList) {
            resultStringBuilder.append(aNode.getValue());
        }
        String actualString = resultStringBuilder.toString();
        assertEquals(actualString, expectedString);
        SimpleTree<ANode> simpleTree = parser.calculateAST(nodeList);
        assertEquals(simpleTree.Count(), 3);
        assertEquals(simpleTree.Root.NodeValue.getValue(), "+");
        assertEquals(simpleTree.Root.Children.get(0).NodeValue.getValue(), "3");
        assertEquals(simpleTree.Root.Children.get(1).NodeValue.getValue(), "1");
    }

    @Test
    public void interpretTreeTest() {
        Parser parser = new Parser();
        String expression = "3+1";
        String expectedString = "(3+1)";
        List<ANode> nodeList = parser.parseString(expression);
        StringBuilder resultStringBuilder = new StringBuilder();
        for (ANode aNode : nodeList) {
            resultStringBuilder.append(aNode.getValue());
        }
        String actualString = resultStringBuilder.toString();
        assertEquals(actualString, expectedString);
        SimpleTree<ANode> simpleTree = parser.calculateAST(nodeList);
        assertEquals(simpleTree.Count(), 3);
        assertEquals(simpleTree.Root.NodeValue.getValue(), "+");
        assertEquals(simpleTree.Root.Children.get(0).NodeValue.getValue(), "3");
        assertEquals(simpleTree.Root.Children.get(1).NodeValue.getValue(), "1");
        parser.interpretTree();
        assertEquals(simpleTree.Root.NodeValue.getValue(), "4");
    }

    @Test
    public void interpretTreeTest2() {
        Parser parser = new Parser();
        String expression = "5+3+4/2";
        String expectedString = "(5+(3+(4/2)))";
        List<ANode> nodeList = parser.parseString(expression);
        StringBuilder resultStringBuilder = new StringBuilder();
        for (ANode aNode : nodeList) {
            resultStringBuilder.append(aNode.getValue());
        }
        String actualString = resultStringBuilder.toString();
        assertEquals(actualString, expectedString);
        SimpleTree<ANode> simpleTree = parser.calculateAST(nodeList);
        parser.interpretTree();
        assertEquals(simpleTree.Root.NodeValue.getValue(), "10");
    }

}