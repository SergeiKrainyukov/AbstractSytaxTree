public class ANode {
    private final NodeType nodeType;
    private String value;

    public ANode(NodeType nodeType, String value) {
        this.nodeType = nodeType;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public NodeType getNodeType() {
        return nodeType;
    }
}
