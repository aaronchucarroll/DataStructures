package deque;

import java.util.Iterator;


public class LinkedListDeque<T> implements Iterable<T>, Deque<T>{
    private class Node {
        public T item;
        public Node next;
        public Node prev;

        public Node(T item) {
            this.item = item;
        }
        public Node(T item, Node n, Node p){
            this.item = item;
            next = n;
            prev = p;
        }
    }
    private Node head;
    private Node tail;
    private int size;

    public LinkedListDeque(){
        head = new Node(null, null, null);
        head.next = head;
        head.prev = head;
        tail = head;
        size = 0;
    }

    public LinkedListDeque(T x){
        head = new Node(x, null, null);
        tail = head;
        size = 1;
    }

    public Iterator<T> iterator(){return new LinkedListDequeIterator();}

    private class LinkedListDequeIterator implements Iterator<T>{
        private Node pos;
        public LinkedListDequeIterator(){
            pos = head;
        }
        public boolean hasNext(){
            return (pos != null);
        }
        public T next(){
            T returnItem = pos.item;
            pos = pos.next;
            return returnItem;
        }
    }

    public void addFirst(T item){
        if (size == 0){
            head = new Node(item, null, null);
            tail = head;
        }else if (size == 1){
            head = new Node(item, tail, null);
            tail.prev = head;
        } else{
            head = new Node(item, head, null);
            head.next.prev = head;
        }
        size++;
    }
    public void addLast(T item){
        if (size == 0){
            head = new Node(item, null, null);
            tail = head;
        } else if (size == 1){
            tail = new Node(item, null, head);
            head.next = tail;
        }else{
            tail = new Node(item, null, tail);
            tail.prev.next = tail;
        }
        size++;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        Node p = head;
        StringBuilder SB = new StringBuilder("{");
        while (p.next != null){
            SB.append(p.item + ", ");
            p = p.next;
        }
        SB.append(p.item + "}");
        System.out.println(SB.toString());
    }
    public T removeFirst(){
        T returnVal = head.item;
        if (size == 0){
            return null;
        }
        if (head.next == null){
            head = new Node(null, null, null);
            size--;
            return returnVal;
        }
        head = new Node(head.next.item, head.next.next, null);
        size--;
        return returnVal;
    }

    public T removeLast(){
        T returnVal = tail.item;
        if (size == 0){
            return returnVal;
        }
        if (tail.prev != null) {
            tail = new Node(tail.prev.item, null, tail.prev.prev);
            size--;
            return returnVal;
        }

        head = new Node(null, null, null);
        tail = head;
        size--;
        return returnVal;
    }

    public T get(int index){
        Node p = head;
        for (int i = 0; i < index; i++){
            p = p.next;
        }
        return p.item;
    }

    @Override
    public boolean equals(Object o){
        if (this == o){return true;}
        if (o instanceof Deque p){
            if (p.size() != this.size()){
                return false;
            }
            for (int i = 0; i < p.size(); i++){
                if (!(p.get(i).equals(this.get(i)))){
                    return false;
                }
            }
        }
        return true;
    }




}



