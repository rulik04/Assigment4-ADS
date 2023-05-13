import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BST<K extends Comparable<K>, V> implements Iterable<KeyAndValue<K, V>> {
    private Node root;

    private class Node {
        private K key;
        private V value;
        private int size;
        private Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            size = 1;
        }
    }

    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private Node put(Node node, K key, V value) {
        if (node == null) {
            return new Node(key, value);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, value);
        } else if (cmp > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }
        node.size = size(node.right) + size(node.left) + 1;

        return node;
    }

    public V get(K key) {
        return get(root, key);
    }

    private V get(Node node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return get(node.left, key);
        } else if (cmp > 0) {
            return get(node.right, key);
        }

        return node.value;
    }

    public void delete(K key) {
        root = delete(root, key);
    }

    private Node delete(Node node, K key) {
        if (node == null) {
            return node;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = delete(node.left, key);
        } else if (cmp > 0) {
            node.right = delete(node.right, key);
        } else if (cmp == 0) {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                Node newNode;
                if (size(node.left) > size(node.right)) {
                    newNode = RightMin(node.right);
                    node.key = newNode.key;
                    node.value = newNode.value;;
                    node.right = delete(node.right, node.key);
                } else {
                    newNode = LeftMax(node.left);
                    node.key = newNode.key;
                    node.value = newNode.value;
                    node.left = delete(node.left, node.key);
                }
            }
        }
        node.size = size(node.right) + size(node.left) + 1;

        return node;
    }

    private Node RightMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }

        return node;
    }

    private Node LeftMax(Node node) {
        while (node.right != null) {
            node = node.right;
        }

        return node;
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }

        return node.size;
    }

    public void inOrderREC() {
        inOrderREC(root);
    }

    private void inOrderREC(Node localRoot) {
        if (localRoot != null) {
            inOrderREC(localRoot.left);
            System.out.println("[" + localRoot.key + ", " + localRoot.value + "]");
            inOrderREC(localRoot.right);
        }
    }

    public Iterator<KeyAndValue<K, V>> iterator() {
        Queue<KeyAndValue<K, V>> queue = new LinkedList<>();
        inOrder(root, queue);
        return queue.iterator();
    }

    private void inOrder(Node node, Queue<KeyAndValue<K, V>> queue) {
        if (node == null) return;
        inOrder(node.left, queue);
        queue.add(new KeyAndValue<>(node.key, node.value));
        inOrder(node.right, queue);
    }

}
