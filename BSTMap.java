package bstmap;

//preorder traversal -- root, left, right
//postorder traversal -- left, right, root
//inorder traversal -- left, root, right

import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K,V>{

    private Node root;

    private class Node{
        private K key;
        private V val;
        private int size;
        private Node left;
        private Node right;

        public Node(K key, V val){
            this.key = key;
            this.val = val;
            left = null;
            right = null;
            size = 1;
        }
    }

    public BSTMap(){
        root = null;
    }
    public void clear(){
        root = null;
    }

    public boolean containsKey(K key) {
        return containsKey(key, root);
    }

    private boolean containsKey(K key, Node n){
        if (n == null){
            return false;
        }
        if (key.equals(n.key)){
            return true;
        }
        int comp = key.compareTo(n.key);
        if (comp > 0){
            return containsKey(key, n.right);
        }else{
            return containsKey(key, n.left);
        }
    }

    public V get(K key){
        return get(key, root);
    }

    public V get(K key, Node n){
        if (n == null){
            return null;
        }
        int comp = key.compareTo(n.key);
        if (comp == 0){
            return n.val;
        }else if (comp > 0){
            return get(key, n.right);
        }else{
            return get(key, n.left);
        }
    }

    public int size(){
        return size(root);

    }

    public void put(K key, V value){
        root = insert(root, key, value);

    }

    private Node insert(Node n, K key, V value){
        if (n == null){
            return new Node(key, value);
        }
        int comp = key.compareTo(n.key);
        if (comp == 0){
            n.val = value;
        }else if (comp > 0){
            n.right = insert(n.right, key, value);
        }else{
            n.left = insert(n.left, key, value);
        }
        n.size = size(n.right) + size(n.left) + 1;
        return n;
    }

    private int size(Node n){
        if (n == null){
            return 0;
        }else{
            return n.size;
        }
    }

    public Set<K> keySet(){
        Set<K> returnSet = new HashSet<>();


        return returnSet;

    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException. */
    public V remove(K key){
        throw new UnsupportedOperationException();

    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 7. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    public V remove(K key, V value){
        throw new UnsupportedOperationException();

    }

    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }



}
