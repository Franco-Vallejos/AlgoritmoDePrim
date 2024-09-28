package grafoElectricidad;

public class Nodo implements Comparable<Nodo> {
    int vertice;
    int key;

    public Nodo(int vertex, int key) {
        this.vertice = vertex;
        this.key = key;
    }

    @Override
    public int compareTo(Nodo other) {
        return Integer.compare(this.key, other.key);
    }
   }