package grafoElectricidad;

import java.util.Arrays;

public class PrimMST {
	private int [][] matrizAdyacencia;
	private int[] predecesor;
	private int numeroNodos;
	
	public PrimMST(int [][] matriz){
		matrizAdyacencia = matriz.clone();
		numeroNodos = matriz.length;
		predecesor = new int[numeroNodos];
		Arrays.fill(predecesor, -1);
	}
	
	public void makePrimMST(int nodoInicial) throws Exception {
		//key seria un peso auxiliar
		int[] key = new int [this.numeroNodos];
		boolean[] nodoVisitado = new boolean[this.numeroNodos];
		
		Arrays.fill(key, Integer.MAX_VALUE);
		
		key[nodoInicial]=0;
		ColaPrioridad<Nodo> pq = new ColaPrioridad<Nodo>(this.numeroNodos);
		pq.push(new Nodo(0, key[nodoInicial]));
		
		while(!pq.estaVacia()) {
	       Nodo nodo = pq.poll();
	       int u = nodo.vertice;
	
	       nodoVisitado[u] = true;
	       for (int v = 0; v < this.numeroNodos; v++) {
	            if (matrizAdyacencia[u][v] != 0 && !nodoVisitado[v]) {
	                if (matrizAdyacencia[u][v] < key[v]) {
	                    key[v] = matrizAdyacencia[u][v];
	                    predecesor[v] = u;
	                    pq.push(new Nodo(v, key[v]));
	                }
	            }
	        }
		}
    }
	
	public int[] getPredecesora() {
		return predecesor;
	}
	
	public int[][] getMST() {
		return matrizAdyacencia;
	}

    public void showGraph() {
        System.out.println("Arista \tPeso");
        for (int i = 1; i < matrizAdyacencia.length; i++) {
            System.out.println(predecesor[i] + " - " + i + "\t" + matrizAdyacencia[i][predecesor[i]]);
        }
    }
    
    public int getCosto() {
    	int costo=0;
    	for(int i=0; i<predecesor.length; i++) {
    		costo+=predecesor[i];
    	}
    	return costo;
    }
    
    public void establecerCostos(int vec[], int valor) {
    	for(int i=0; i<vec.length; i++) {
    		predecesor[vec[i]] = valor;
    	}
    }
    
    public void showMatriz() {
        for (int i = 0; i < matrizAdyacencia.length; i++) {
            for (int j = 0; j < matrizAdyacencia.length; j++) {
            	System.out.print(matrizAdyacencia[i][j]);
            }
            System.out.println();
        }
    }
}
