import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class ParserTest {

    @Test
    public void parseString1() {
        Parser parser = new Parser();
        List<ANode> nodeList = parser.parseString("5*3+4");
        assertEquals(nodeList.size(), 9);
    }

    @Test
    public void parseString2() {
        Parser parser = new Parser();
        List<ANode> nodeList = parser.parseString("5+3*4");
        assertEquals(nodeList.size(), 9);
    }

    @Test
    public void parseString3() {
        Parser parser = new Parser();
        List<ANode> nodeList = parser.parseString("5+3+4/2");
        assertEquals(nodeList.size(), 13);
    }

    @Test
    public void parseString4() {
        Parser parser = new Parser();
        List<ANode> nodeList = parser.parseString("3+5-5+3");
        assertEquals(nodeList.size(), 13);
    }

}