package data_structure.binary_tree;

public class BST<Key extends Comparable<Key>,Value> {

    private Node root;

    private class Node {
        private Key key;            //键
        private Value val;        //值
        private Node left, right;
        private int N;              //以该结点为根的子树中的结点总数

        public Node(Key key, Value value,  int n) {
            this.key = key;
            this.val = value;
            this.N = n;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        } else {
            return node.N;
        }
    }


    public Value get(Key key) {
        return get(root, key);
    }


    private Value get(Node node, Key key) {
        // 在以node为根结点的子树中查找并返回key所对应的值
        // 如果找不到则返回null
        if (node == null) {
            return null;
        }
        int compareValue = key.compareTo(node.key);
        if (compareValue < 0) {
            return get(node.left, key);
        } else if (compareValue > 0) {
            return get(node.right, key);
        } else {
            return node.val;
        }
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node node,Key key, Value value) {
        // 如果key存在于node为根结点的子树中，则更新它的值
        // 否则将以key和value为键值对的新结点插入到该子树中

        if (node == null) {
            return new Node(key, value, 1);
        }
        int compareValue = key.compareTo(node.key);
        if (compareValue < 0) {
            node.left = put(node.left, key, value);
        } else if (compareValue > 0) {
            node.right = put(node.right, key, value);
        }
        else {
            node.val = value;
        }
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }


    public Key select(int k) {
        return select(root, k).key;
    }

    private Node select(Node node, int k) {
        // 返回排名为k（即树中正好有k个小于它的键）的结点
        if (node == null) {
            return null;
        }

        int t = size(node.left);
        if( t > k){
            return select(node.left, k);
        } else if (t < k) {
            return select(node.right, k - t - 1);
        } else {
            return node;
        }
    }

    public Key min() {
        return min(root).key;
    }

    private Node min(Node node) {

        if (node.left == null) {
            return node;
        }
        return min(node.left);
    }

    public int rank(Key key) {
        // 返回给定键的排名
        return rank(root, key);
    }

    private int rank(Node node,Key key) {
        // 返回以node为根结点的子树中小于 key 的键的数量
        if (node == null) {
            return 0;
        }
        int compareValue = key.compareTo(node.key);
        if (compareValue < 0) {
            return rank(node.left, key);
        } else if (compareValue > 0) {
            return 1 + size(node.left) + rank(node.right, key);
        } else {
            return size(node.left);
        }
    }

    public void deleteMin(){
        root = deleteMin(root);
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    private Node delete(Node node, Key key) {
        if (node == null) {
            return null;
        }

        int compareValue = key.compareTo(node.key);
        if (compareValue < 0) {
            node.left = delete(node.left, key);
        } else if (compareValue > 0) {
            node.right = delete(node.right, key);
        } else {
            if(node.left==null) return node.right;
            if (node.right == null) return node.left;
            Node t = node;
            node = min(t.right);
            node.right = deleteMin(t.right);
            node.left = t.left;
        }
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }
}
