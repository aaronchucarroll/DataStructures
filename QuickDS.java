public class QuickDS implements DisjointSet{
    private int[]id;

    public QuickDS(int size){
        id = new int[size];
        for (int i = 0; i < size; i++){
            id[i] = i;
        }
    }

    public void connect(int p, int q){
        int pId = id[p];
        int qId = id[q];
        for (int i = 0; i < id.length; i++){
            if (id[i] == pId){
                id[i] = qId;
            }
        }
    }

    public boolean isConnected(int p, int q){
        return (id[p] == id[q]);
    }

}
