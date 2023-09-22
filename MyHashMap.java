package hashmap;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author Aaron Chu-Carroll
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private static int DEFAULT_SIZE = 16;
    private static double DEFAULT_MAXLOAD = 0.75;
    private double maxLoad;
    private int n; // number of items
    private int m; // number of buckets

    /** Constructors */
    public MyHashMap() {
        this(DEFAULT_SIZE, DEFAULT_MAXLOAD);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, DEFAULT_MAXLOAD);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        buckets = createTable(initialSize);
        this.maxLoad = maxLoad;
        m = initialSize;
        for (int i = 0; i < initialSize; i++){
            buckets[i] = createBucket();
        }
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    private Collection<Node>[] createTable(int tableSize) {
        Collection<Node>[] returnArr = (Collection<Node>[]) new Collection[tableSize];
        return returnArr;
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

    public int hash(K key){
        int hash = key.hashCode();
        int index = hash % m;
        index = Math.abs(index);
        return index;
    }

    public void resize(){
        Collection<Node>[] temp = buckets;
        m *= 2;
        buckets = createTable(m);
        for (int i = 0; i < buckets.length; i++){
            buckets[i] = createBucket();
        }
        for (Collection<Node> c : temp){
            for (Node n : c){
                put(n);
            }
        }
    }


    public void clear(){
        for (int i = 0; i < buckets.length; i++){
            buckets[i].clear();
        }
        n = 0;
    }

    public boolean containsKey(K key){
        if (buckets == null){
            return false;
        }
        int index = hash(key);
        for (Node n : buckets[index]){
            if (n.key.equals(key)){
                return true;
            }
        }
        return false;
    }

    public V get(K key){
        if (buckets == null){
            return null;
        }
        int index = hash(key);
        for (Node n : buckets[index]){
            if (n.key.equals(key)){
                return n.value;
            }
        }
        return null;
    }

    public int size(){
        return n;
    }

    public void put(K key, V value){
        if (((double) n / m) > maxLoad){
            resize();
        }
        if (this.containsKey(key)){
            int index = hash(key);
            for (Node n : buckets[index]){
                if (n.key.equals(key)){
                    n.value = value;
                    return;
                }
            }
        }
        Node t = new Node(key, value);
        int index = hash(key);
        buckets[index].add(t);
        n++;
    }

    //Only used for resizing
    private void put(Node n){
        int index = hash(n.key);
        buckets[index].add(n);
    }

    public Set<K> keySet(){
        throw new UnsupportedOperationException();
    }

    public V remove(K key){
        throw new UnsupportedOperationException();
    }

    public V remove(K key, V value){
        throw new UnsupportedOperationException();
    }

    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }



}
