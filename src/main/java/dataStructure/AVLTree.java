package dataStructure;

/**
 * @author: lam
 * @create: 2020/11/14
 *
 * Simple AVL Tree only contains method <b>add()</b> and <b>getPredecessor()</>
 **/
public class AVLTree {

    public Node root;
    protected int size;

    public Node insert(int element) {
        if (root == null) {
            root = createNode(element, null, null, null);
            size++;
            return root;
        }

        Node insertParentNode = null;
        Node searchTempNode = root;
        while (searchTempNode != null && searchTempNode.value != null) {
            insertParentNode = searchTempNode;
            if (element < searchTempNode.value) {
                searchTempNode = searchTempNode.left;
            } else {
                searchTempNode = searchTempNode.right;
            }
        }

        Node newNode = createNode(element, insertParentNode, null, null);
        if (insertParentNode.value > newNode.value) {
            insertParentNode.left = newNode;
        } else {
            insertParentNode.right = newNode;
        }

        size++;
        reBalance(newNode);
        return newNode;
    }

    public int getPredecessor(int element) {
        return getPredecessor(root, element);
    }

    private int getPredecessor(Node node, int element) {
        if (node == null) {
            return -1;
        }

        if (node.value > element) {
            if (node.left == null) {
                return getPredecessor(node, node.value);
            }
            return getPredecessor(node.left, element);
        } else if (node.value < element) {
            if (node.right == null) {
                return node.value;
            }
            int subValue = getPredecessor(node.right, element);
            return subValue == -1 ? node.value : subValue;
        } else {
            if (node.left != null) {
                node = node.left;
                while (node.right != null) {
                    node = node.right;
                }
                return node.value;
            }
            else {
                return -1;
            }
        }
    }

    public void printTreeInOrder() {
        printTreeInOrder(root);
    }

    private void printTreeInOrder(Node node) {
        if (node != null) {
            printTreeInOrder(node.left);
            if (node.value != null) {
                System.out.println(node.value);
            }
            printTreeInOrder(node.right);
        }
    }

    protected Node createNode(int value, Node parent, Node left, Node right) {
        return new Node(value, parent, left, right);
    }

    private void reBalance(Node node) {
        while (node != null) {
            Node parent = node.parent;

            int leftHeight = (node.left == null) ? -1 : (node.left).height;
            int rightHeight = (node.right == null) ? -1 : (node.right).height;
            int nodeBalance = rightHeight - leftHeight;
            if (nodeBalance == 2) {
                if (node.right.right != null) {
                    node = rotateLeft(node);
                } else {
                    node = doubleRotateRightLeft(node);
                }
                break;
            } else if (nodeBalance == -2) {
                if (node.left.left != null) {
                    node = rotateRight(node);
                } else {
                    node = doubleRotateLeftRight(node);
                }
                break;
            } else {
                updateHeight(node);
            }
            node = parent;
        }
    }

    private Node rotateLeft(Node node) {
        Node temp = node.right;
        temp.parent = node.parent;

        node.right = temp.left;
        if (node.right != null) {
            node.right.parent = node;
        }

        temp.left = node;
        node.parent = temp;

        if (temp.parent != null) {
            if (node == temp.parent.left) {
                temp.parent.left = temp;
            } else {
                temp.parent.right = temp;
            }
        } else {
            root = temp;
        }

        updateHeight(temp.left);
        updateHeight(temp);
        return temp;
    }

    private Node rotateRight(Node node) {
        Node temp = node.left;
        temp.parent = node.parent;

        node.left = temp.right;
        if (node.left != null) {
            node.left.parent = node;
        }

        temp.right = node;
        node.parent = temp;

        if (temp.parent != null) {
            if (node == temp.parent.left) {
                temp.parent.left = temp;
            } else {
                temp.parent.right = temp;
            }
        } else {
            root = temp;
        }

        updateHeight(temp.right);
        updateHeight(temp);
        return temp;
    }

    protected Node doubleRotateRightLeft(Node node) {
        node.right = rotateRight(node.right);
        return rotateLeft(node);
    }

    protected Node doubleRotateLeftRight(Node node) {
        node.left = rotateLeft(node.left);
        return rotateRight(node);
    }

    private static final void updateHeight(Node node) {
        int leftHeight = (node.left == null) ? -1 : (node.left).height;
        int rightHeight = (node.right == null) ? -1 : (node.right).height;
        node.height = 1 + Math.max(leftHeight, rightHeight);
    }

    protected static class Node {
        public int height;
        public Integer value;
        public Node parent;
        public Node left;
        public Node right;

        public Node(Integer value, Node parent, Node left, Node right) {
            super();
            this.value = value;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((value == null) ? 0 : value.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Node other = (Node) obj;
            if (value == null) {
                if (other.value != null)
                    return false;
            } else if (!value.equals(other.value))
                return false;
            return true;
        }

    }
}