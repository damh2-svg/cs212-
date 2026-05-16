package phase2;

public class BSTSet<K extends Comparable<? super K>> implements Set<K> {

    private class Node {
        K key;
        Node left, right;

        Node(K key) {
            this.key = key;
        }
    }
    private Node root;
    private int size;

    public BSTSet() {
        root = null;
        size = 0;
    }

     @Override
     public int size() {
        return size;
     }

     @Override
     public boolean empty() {
        return size == 0;
     }

     @Override
     public void clear() {
        root = null;
        size = 0;
     }

     @Override
     public boolean contains(K k) {
        Node current = root;
        while (current != null) {
            int cmp = k.compareTo(current.key);
            if (cmp == 0) 
                return true; 
            if (cmp < 0)
                current = current.left;
            else
                current = current.right;
        }
        return false;
     }

     @Override
     public int nbKeyComp(K k) {
        int count = 0;
        Node current = root;
        while (current != null) {
            count++;
            int cmp = k.compareTo(current.key);
            if (cmp == 0) 
                break; 
            if (cmp < 0)
                current = current.left;
            else
                current = current.right;
        }
        return count;
     }

     @Override
     public boolean insert(K k) {
       Node parent = null;
    Node current = root;

    while (current != null && k.compareTo(current.key) != 0) {
        parent = current;
        if (k.compareTo(current.key) < 0)
            current = current.left;
        else
            current = current.right;    
    }

    if (current != null) 
        return false;

    Node newNode = new Node(k);

    if (parent == null) {
        root = newNode;
    } else if (k.compareTo(parent.key) < 0) {
        parent.left = newNode;
    } else {
        parent.right = newNode;
    }

    size++;
    return true;
     }

     @Override
     public boolean remove(K k) {
        Node parent = null;
    Node current = root;
    
    while (current != null && k.compareTo(current.key) != 0) {
        parent = current;
        if (k.compareTo(current.key) < 0)
            current = current.left;
        else
            current = current.right;    
    }
    
    if (current == null) 
        return false;
        
    if (current.left != null && current.right != null) {
        Node target = current;
        parent = current;
        current = current.right;
        
        while (current.left != null) {
            parent = current;
            current = current.left;
        }
        
        target.key = current.key;
    }
    
    Node child = (current.left != null) ? current.left : current.right;
    
    if (parent == null) {
        root = child;
    } else if (current == parent.left) {
        parent.left = child;
    } else {
        parent.right = child;
    }
    
    size--;
    return true;
     }

     @Override
      public List<K> getKeys() {
        List<K> keys = new phase2.LinkedList<>();
        recGetKeys(root, keys);
        return keys;
    }

    private void recGetKeys(Node node, List<K> keys) {
        if (node == null) return;
        recGetKeys(node.left,  keys);
        keys.insert(node.key);
        recGetKeys(node.right, keys);
    }

}

