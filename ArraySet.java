//ArraySet that ignores nulls if users try to add

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ArraySet<T> implements Iterable<T> {

    private T[] items;
    private int size;
    public ArraySet() {
        items = (T[]) new Object[100];
        size = 0;

    }

    public Iterator<T> iterator(){
        return new ArraySetIterator();
    }

    private class ArraySetIterator implements Iterator<T>{
        private int pos;
        public ArraySetIterator(){
            pos = 0;
        }

        public boolean hasNext(){
            return (pos < size);
        }

        public T next(){
            T returnItem = items[pos];
            pos++;
            return returnItem;
        }
    }

    /* Returns true if this map contains a mapping for the specified key.
     */
    public boolean contains(T x) {
        for (int i = 0; i < size; i++){
            if (items[i].equals(x)){
                return true;
            }
        }
        return false;
    }

    /* Associates the specified value with the specified key in this map.
       Throws an IllegalArgumentException if the key is null. */
    public void add(T x) {
        if (x == null) {
            return;
        }

        if (contains(x)){
            return;
        }
        items[size] = x;
        size++;
    }

    /* Returns the number of key-value mappings in this map. */
    public int size() {
        return size;
    }

    @Override
    public String toString(){
        StringBuilder returnSB = new StringBuilder("{");
        for (int i = 0; i < size - 1; i ++){
            returnSB.append(items[i].toString());
            returnSB.append(", ");

        }
        returnSB.append(items[size - 1]);
        returnSB.append("}");
        return returnSB.toString();
    }

    @Override
    public boolean equals(Object o){
        if (this == o) {return true;}
        if (o instanceof ArraySet oas){
            if (oas.size != this.size){
                return false;
            }
            for (T x : this){
                if (!oas.contains(x)){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static <Und>ArraySet<Und> of(Und... stuff){
        ArraySet<Und> returnSet = new ArraySet<Und>();
        for (Und x : stuff){
            returnSet.add(x);
        }
        return returnSet;
    }

    public static void main(String[] args) {
        ArraySet<String> s = new ArraySet<>();
        s.add(null);
        s.add("horse");
        s.add("fish");
        s.add("house");
        s.add("fish");
        System.out.println(s.contains("horse"));
        System.out.println(s.size());


        ArraySet<Integer> aset = new ArraySet<>();
        aset.add(5);
        aset.add(23);
        aset.add(42);

     /*   Iterator<Integer> aseer = aset.iterator();
        while (aseer.hasNext()){
            int i = aseer.next();
            System.out.println(i);
        }*/

        System.out.println(aset);
        ArraySet<Integer> equalSet = new ArraySet<>();
        equalSet.add(5);
        equalSet.add(23);
        equalSet.add(41);
        System.out.println(aset.equals(equalSet));

        ArraySet<String> aSetOfStrings = ArraySet.of("Hi", "I'm", "here");
        System.out.println(aSetOfStrings);



    }

    /* Also to do:
    1. Make ArraySet implement the Iterable<T> interface.
    2. Implement a toString method.
    3. Implement an equals() method.
    */
}