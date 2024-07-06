import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

/**

 * @author Jay
 */
public class BSTMap<K extends Comparable<K>, V extends Comparable<V>> implements Map61B<K, V> {

   private class Node {
        private K key;
        private V value;
        private Node left;
        private Node right;

        public Node(K key, V value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }


   }
   private Node root;
   private int size ;

   public BSTMap(K key, V value) {
       root = new Node(key, value, null, null);
       size = 1;
   }

   public BSTMap() {
       root = null;
       size = 0;
   }



    private int compareRoots(Node a, Node b) {
        return a.key.compareTo(b.key);
    }
/**
    public Node toLeafRight(Node other) {
        if (other.right == null) {
            return other;
        }
        other = other.right;
        return other;
    }

    public Node toLeafLeft(Node other) {
       if (other.left == null) {
           return other;
       }
       other = other.left;
       return  other;
    }

    private Node order(Node p, Node newNode) {

        while (p.left != null || p.right != null) {
            if (compareRoots(p,newNode) < 0) {
                p = toLeafRight(p);
                continue;
            }
            if (compareRoots(p,newNode) > 0) {
                p =toLeafLeft(p);
                continue;
            }
            if (compareRoots(p, newNode) == 0) {
                p.value = newNode.value;
                break;
            }
        }
        Node node = p;
        return node;
    }



    public void put(K key, V value) {
       if (root.value == null) {
           root.value = value;
           root.key = key;
           return;
       }
       Node p = root;
       Node newNode = new Node(key, value, null, null);
        if (p.left != null || p.right != null) {
            p = order(p, newNode);
        }

       if (compareRoots(p, newNode) < 0) {
           p.right = newNode;
           return;
       }
       if (compareRoots(p, newNode) > 0) {
           p.left = newNode;
           return;
       }
       if (compareRoots(p, newNode) == 0) {
           p.value = newNode.value;
           return;
       }





   }
 */
    private Node inter(Node current, Node newNode) {

        if(current == null) {

            size++;
            return newNode;
        }

        int cmp = compareRoots(current, newNode);
        if (cmp < 0) {
            current.right = inter(current.right, newNode);
        }else if(cmp > 0) {
            current.left = inter(current.left, newNode);
        }else {
            current.value = newNode.value;
        }
        return current;


    }


    @Override
    public void put(K key, V value) {
        Node newNode = new Node(key, value, null, null);
        root = inter(root, newNode);
    }



    private Node getSearch(Node p, K key) {
        if(p == null) {
            return null;
        }
        int cmp = key.compareTo(p.key);
        if (cmp < 0) {
            return getSearch(p.left, key);
        } else if (cmp > 0) {
            return getSearch(p.right, key);
        } else {
            return p;
        }

    }

    @Override
    public V get(K key) {
        Node node = getSearch(root, key);
        return node == null ? null : node.value;

    }

    @Override
    public boolean containsKey(K key) {
        return getSearch(root, key) != null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        addKeys(root, keys);
        return keys;
    }

    private void addKeys(Node node, Set<K> keys) {
        if (node == null) {
            return;
        }
        keys.add(node.key);
        addKeys(node.left, keys);
        addKeys(node.right, keys);
    }

    public V remove(K key) {
        Node p = getSearch(root, key);
        if (p == null) {
            return null;
        }
        V oldValue = p.value;
        root = remove(root, key);
        size--;
        return oldValue;
    }

    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = remove(node.left, key);
        } else if (cmp > 0) {
            node.right = remove(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            Node t = node;
            node = min(t.right);
            node.right = deleteMin(t.right);
            node.left = t.left;
        }
        return node;
    }

    private Node min(Node node) {
        if (node.left == null) {
            return node;
        } else {
            return min(node.left);
        }
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        return node;
    }

    @Override
    public Iterator<K> iterator() {
        return new BSTMapIterator();
    }

    private class BSTMapIterator implements Iterator<K> {
        private Stack<Node> stack = new Stack<>();

        public BSTMapIterator() {
            pushLeft(root);
        }

        private void pushLeft(Node node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public K next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            Node current = stack.pop();
            K key = current.key;
            if (current.right != null) {
                pushLeft(current.right);
            }
            return key;
        }
    }
}
