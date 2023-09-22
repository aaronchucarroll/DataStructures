package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T>, Deque<T>{
    private T[] items;
    private int size;
    int nextFirst;
    int nextLast;

    public ArrayDeque(){
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 3;
        nextLast = 4;
    }
    public Iterator<T> iterator(){return new ArrayDequeIterator();}

    private class ArrayDequeIterator implements Iterator<T>{
        private int pos;
        public ArrayDequeIterator(){
            pos = nextFirst + 1;
        }
        public boolean hasNext(){
            return (pos < nextLast);
        }

        public T next(){
            T returnItem = items[pos];
            pos++;
            return returnItem;
        }




    }

    private void resize(int capacity){
        T[] a = (T[]) new Object[capacity];
        int index = (capacity - size)  / 2;
        System.arraycopy(items, nextFirst + 1, a,index, size);
        items = a;
        nextFirst = index - 1;
        nextLast = index + size;
    }

    private void sizeDown(){
        T[]a = (T[]) new Object[size * 2];
        int index = size / 2;
        System.arraycopy(items, nextFirst + 1, a, index, size);
        items = a;
        nextFirst = index - 1;
        nextLast = index + size;
    }



    public void addFirst(T item){
        if (nextFirst == 0){
            resize(size * 3);
        }
        items[nextFirst] = item;
        nextFirst--;
        size++;
    }
    public void addLast(T item){
        if (nextLast == items.length){
            resize(size * 3);
        }
        items[nextLast] = item;
        nextLast++;
        size++;
    }

    public int size(){
        return size;
    }


    public void printDeque(){
        StringBuilder SB = new StringBuilder("{");
        for (int i = nextFirst + 1; i < nextLast - 1; i++){
            SB.append(items[i] + ", ");
        }
        SB.append(items[nextLast - 1] + "}");
        System.out.println(SB.toString());
    }

    public T removeFirst(){
        if (size == 0){
            return null;
        }
        if ((((double)items.length / size) > 4) && size > 16){
            sizeDown();
        }
        T returnVal = items[nextFirst + 1];
        items[nextFirst + 1] = null;
        nextFirst++;
        size--;
        return returnVal;
    }

    public T removeLast(){
        if (size == 0){
            return null;
        }
        if ((((double)items.length / size) > 4) && size > 16){
            sizeDown();
        }
        T returnVal = items[nextLast - 1];
        items[nextLast - 1] = null;
        nextLast--;
        size--;
        return returnVal;
    }

    public T get(int index){
        return items[nextFirst + index + 1];
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
