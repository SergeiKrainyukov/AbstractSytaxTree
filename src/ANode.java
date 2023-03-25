public class ANode {
    private final String tokenType;
    private String value;

    public ANode(String tokenType, String value) {
        this.tokenType = tokenType;
        this.value = value;
    }

    public String getTokenType() {
        return tokenType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
