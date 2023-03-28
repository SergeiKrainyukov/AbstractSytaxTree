import java.util.ArrayList;
import java.util.List;

public class SimpleTreeNode<T> {
    public T NodeValue; // значение в узле
    public SimpleTreeNode<T> Parent; // родитель или null для корня
    public List<SimpleTreeNode<T>> Children; // список дочерних узлов или null

    public int interpretResult;
    public String translationResult;

    public SimpleTreeNode(T val, SimpleTreeNode<T> parent) {
        NodeValue = val;
        Parent = parent;
        Children = null;
    }
}

class SimpleTree<T> {
    public SimpleTreeNode<T> Root; // корень, может быть null

    public SimpleTree(SimpleTreeNode<T> root) {
        Root = root;
    }

    public void AddChild(SimpleTreeNode<T> ParentNode, SimpleTreeNode<T> NewChild) {
        if (ParentNode.Children == null) ParentNode.Children = new ArrayList<>();
        ParentNode.Children.add(NewChild);
    }

    public void DeleteNode(SimpleTreeNode<T> NodeToDelete) {
        if (Root == null) return;
        deleteNode(NodeToDelete, Root);
    }

    public List<SimpleTreeNode<T>> GetAllNodes() {
        List<SimpleTreeNode<T>> nodeList = new ArrayList<>();
        if (Root == null)
            return null;
        if (Root.Children == null) {
            nodeList.add(Root);
            return nodeList;
        }
        return getNodes(nodeList, Root);
    }

    public List<SimpleTreeNode<T>> FindNodesByValue(T val) {
        List<SimpleTreeNode<T>> nodeList = new ArrayList<>();
        return getNodesByValue(nodeList, Root, val);
    }

    public void MoveNode(SimpleTreeNode<T> OriginalNode, SimpleTreeNode<T> NewParent) {
        DeleteNode(OriginalNode);
        AddChild(NewParent, OriginalNode);
    }

    public int Count() {
        return getNodes(new ArrayList<>(), Root).size();
    }

    public int LeafCount() {
        return getLeafsCount(0, Root);
    }

    private List<SimpleTreeNode<T>> getNodes(List<SimpleTreeNode<T>> nodeList, SimpleTreeNode<T> currentNode) {
        nodeList.add(currentNode);
        if (currentNode.Children == null || currentNode.Children.size() == 0) {
            return nodeList;
        }
        for (SimpleTreeNode<T> node : currentNode.Children) {
            getNodes(nodeList, node);
        }
        return nodeList;
    }

    private List<SimpleTreeNode<T>> getNodesByValue(List<SimpleTreeNode<T>> nodeList, SimpleTreeNode<T> currentNode, T value) {
        if (currentNode.NodeValue == value) {
            nodeList.add(currentNode);
            return nodeList;
        }
        if (currentNode.Children == null || currentNode.Children.size() == 0) return nodeList;
        for (SimpleTreeNode<T> node : currentNode.Children) {
            getNodesByValue(nodeList, node, value);
        }
        return nodeList;
    }

    private void deleteNode(SimpleTreeNode<T> nodeToDelete, SimpleTreeNode<T> currentNode) {
        if (currentNode.Children == null || currentNode.Children.size() == 0) {
            return;
        }
        if (currentNode.Children.contains(nodeToDelete)) {
            currentNode.Children.remove(nodeToDelete);
            if (currentNode.Children.size() == 0) currentNode.Children = null;
            return;
        }
        for (SimpleTreeNode<T> node : currentNode.Children)
            deleteNode(nodeToDelete, node);
    }

    private int getLeafsCount(int count, SimpleTreeNode<T> currentNode) {
        if (currentNode.Children == null || currentNode.Children.size() == 0) {
            count += 1;
            return count;
        }
        for (SimpleTreeNode<T> node : currentNode.Children) {
            count = getLeafsCount(count, node);
        }
        return count;
    }
}
