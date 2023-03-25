import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

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
        assertEquals(nodeList.size(), 9);
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
        assertEquals(nodeList.size(), 9);
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
        assertEquals(nodeList.size(), 13);
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
        assertEquals(nodeList.size(), 13);
        assertEquals(actualString, expectedString);
    }

}