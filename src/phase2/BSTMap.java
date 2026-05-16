package phase2;


public class  BSTMap<K extends Comparable<? super K>, T>  implements Map<K,T> {
        
    private class Node {
        K key;
        T data;
        Node left;
        Node right;

        public Node(K key, T data) {
            this.key = key;
            this.data = data;
            this.left = null;
            this.right = null;
            
        }
    }
    private Node root;
    private int size;

    public BSTMap() {
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
        public boolean insert(K key, T data) {
            Node parent = null;
            Node current = root;
            while (current != null) {
                int cmp = key.compareTo(current.key);
                if (cmp == 0) 
                    return false; 
                parent = current;
                if (cmp < 0)
                    current = current.left;
                else
                    current = current.right;
            }
            Node newNode = new Node(key, data);
            if (parent == null) {
                root = newNode;
            } else if (key.compareTo(parent.key) < 0) {
                parent.left = newNode;
            } else 
                parent.right = newNode;
                size++;
                return true;  
            }

        @Override
        public T get(K key) {
            Node current = root;
            while (current != null) {
                int cmp = key.compareTo(current.key);
                if (cmp == 0)
                    return current.data; 
                if (cmp < 0)
                    current = current.left;
                else
                    current = current.right;    
            }
            //not found
            return null;
        }

        @Override
        public boolean update(K key, T e) {
            Node current = root;
            while (current != null) {
                int cmp = key.compareTo(current.key);
                if (cmp == 0) {
                    current.data = e; 
                    return true;
                }
                if (cmp < 0)
                    current = current.left;
                else
                    current = current.right;    
            }
            //not found
            return false;
        }

        @Override
        public boolean remove(K key) {
            Node parent = null;
            Node current = root;
            while (current != null && key.compareTo(current.key) != 0) {
                parent = current;
                if (key.compareTo(current.key) < 0)
                    current = current.left;
                else
                    current = current.right;    
            }
            if (current == null) 
                return false;
            //cases
            if (current.left != null && current.right != null) {
                Node target = current;
                parent = current;
                current = current.right;
                while (current.left != null) {
                    parent = current;
                    current = current.left;
            }
            target.key = current.key;
            target.data = current.data;
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
        public int nbKeyComp(K key) {
            int count = 0;
            Node current = root;
            while (current != null) {
                count++;
                int cmp = key.compareTo(current.key);
                if (cmp < 0)
                    current = current.left;
                else if (cmp > 0)
                    current = current.right;
                else
                    break;
            }
            return count;
        }

        @Override
        public List<K> getKeys() {
            List<K> keys = new LinkedList<>();
            recGetKeys(root, keys);
            return keys;
        }

        private void recGetKeys(Node node, List<K> keys) {
            if (node != null) {
                recGetKeys(node.left, keys);
                keys.insert(node.key);
                recGetKeys(node.right, keys);
            }
        }
}
