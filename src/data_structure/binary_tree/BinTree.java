package data_structure.binary_tree;

public class BinTree<T extends Comparable<T>> {
    //  二叉查找树，又名二叉排序树，亦名二叉搜索树

    private Node root;

    private int size;

    private int height;

    class Node {
        T key;

        Node leftNode;

        Node rightNode;

        Node parentNode;

        Node(T key) {
            this(key, null, null,null);
        }

        Node(Node leftNode,Node rightNode,Node parentNode) {
            this(null, leftNode, rightNode,parentNode);
        }

        Node(T key,Node leftNode,Node rightNode,Node parentNode) {
            this.key = key;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
            this.parentNode = parentNode;
        }
    }


    /**
     *
     *
     * @param insertData 需要插入的数据
     */
    public void insert(T insertData) {
        if (root  == null) {
            root = new Node(insertData);
        } else {
            insertNode(insertData,root);
        }
    }

    /**
     *
     * @param insertData 需要插入的数据
     * @param node 比较节点
     */
    private void insertNode(T insertData,Node node){

        int cv = insertData.compareTo(node.key);

        if (cv == 0) {
            System.err.println("The key already exist.");
            return;
        } else if (cv > 0) { //大于等于节点值
            if (node.rightNode == null) {
                Node newNode = new Node(insertData, null, null, node);
                node.rightNode = newNode;
            } else {
                insertNode(insertData, node.rightNode);
            }
        } else {
            if (node.leftNode == null) {
                Node newNode = new Node(insertData, null, null, node);
                node.leftNode = newNode;
            } else {
                insertNode(insertData, node.leftNode);
            }
        }

    }

    public Node search(T key) {
        if (root  == null) {
            System.err.println("current BinTree is empty.");
        }
        return searchKey(key, root);
    }

    /**
     *
     * 递归查找
     * @param key
     * @param currentNode
     * @return
     */
    private Node searchKey(T key,Node currentNode) {
        if (currentNode == null) return null;

        int cv = key.compareTo(currentNode.key);

        if (cv == 0) {
            return currentNode;
        }
        else if( cv > 0){
            return searchKey(key, currentNode.rightNode);
        }
        else {
            return searchKey(key, currentNode.leftNode);
        }
    }

/*
    private Node delete(T key, Node node) {
        if (node == null) {
            return null;
        }

        int compareValue = key.compareTo(node.key);
        if (compareValue < 0) {
            return delete(key, node.leftNode);
        } else if (compareValue > 0) {
            return delete(key, node.rightNode);
        } else {
        }
    }*/

    /**
     *
     * 递归实现找以node为根节点的最小节点
     *
     * @return
     */
    private Node findMin(Node node){
        if (node == null) {
            return null;
        }
        if (node.leftNode == null) {
            return node;
        }
        return findMin(node.leftNode);
    }


    /**
     *
     * 循坏实现找以node为根节点的最大节点
     *
     * @param node
     * @return
     */
    private Node findMax(Node node) {

        if (node == null) {
            return null;
        }
        while (node.rightNode != null) {

            node = node.rightNode;
        }
        return node;

    }
    /**
     *
     * 获取该节点的前驱结点
     * 即，查找"二叉树中数据值小于该结点"的"最大结点"。
     *
     * @param node
     * @return
     */
    private Node getPrecursorNode(Node node) {
        // 如果node存在左孩子，则"node的前驱结点"为 "以其左孩子为根的子树的最大结点"。
        if (node.leftNode != null) {
            return findMin(node.leftNode);
        }

        // 如果node没有左孩子。则x有以下两种可能：
        // (01) node是"一个右孩子"，则"node的前驱结点"为 "它的父结点"。
        // (02) node是"一个左孩子"，则查找"node的最低的父结点，并且该父结点要具有右孩子"，找到的这个"最低的父结点"就是"node的前驱结点"。
        Node parentNode = node.parentNode;
        while ((parentNode != null) && node == parentNode.leftNode) {
            // node == parentNode.leftNode 为true,说明node是一个左孩子 ，这说明是情况（02）
            // 继续往上找
            // node == parentNode.leftNode 为false，这说明这是一个右孩子，说明该祖先节点（父节点）有右孩子，已找到前驱节点
            node = parentNode;
            parentNode = node.parentNode;
        }
        return parentNode;
    }


    /**
     *
     * 获取该节点的后继结点
     * 即，查找"二叉树中数据值大于该结点"的"最小结点"。
     *
     * @param node
     * @return
     */
    private Node getSuccessorNode(Node node){
        // 如果node存在右孩子，则"node的后继结点"为 "以其右孩子为根的子树的最小结点"。
        if (node.rightNode != null) {
            return findMax(node.rightNode);
        }

        // 如果node没有右孩子。则x有以下两种可能：
        // (01) node是"一个左孩子"，则"node的后继结点"为 "它的父结点"。
        // (02) node是"一个右孩子"，则查找"node的最低的父结点，并且该父结点要具有左孩子"，找到的这个"最低的父结点"就是"x的后继结点"。
        Node parentNode = node.parentNode;
        while ((parentNode != null) && node == parentNode.rightNode) {
            // node == parentNode.rightNode 为true,说明node是一个右孩子 ，这说明是情况（02）
            // 继续往上找
            // node == parentNode.rightNode 为false，这说明这是一个左孩子，说明该祖先节点（父节点）有左孩子 ，已找到后继节点
            node = parentNode;
            parentNode = node.parentNode;
        }
        return parentNode;
    }
}
