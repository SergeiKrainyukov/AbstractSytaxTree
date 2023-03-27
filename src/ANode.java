import java.util.Objects;

public class ANode {
    private NodeType nodeType;
    private String value;
    public ANode() {
    }
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

    public void setNodeType(NodeType nodeType) {
        this.nodeType = nodeType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ANode aNode = (ANode) o;

        if (nodeType != aNode.nodeType) return false;
        return Objects.equals(value, aNode.value);
    }

    @Override
    public int hashCode() {
        int result = nodeType != null ? nodeType.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
