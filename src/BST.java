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
                if (size(node.left) > size(node.right)) {
                    node.key = RightMin(node.right);
                    node.right = delete(node.right, node.key);
                } else {
                    node.key = LeftMax(node.left);
                    node.left = delete(node.left, node.key);
                }
            }
        }
        node.size = size(node.right) + size(node.left) + 1;

        return node;
    }

    private K RightMin(Node node) {
        K min = node.key;
        while (node.left != null) {
            min = node.left.key;
            node = node.left;
        }

        return min;
    }

    private K LeftMax(Node node) {
        K max = node.key;
        while (node.right != null) {
            max = node.right.key;
            node = node.right;
        }

        return max;
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
