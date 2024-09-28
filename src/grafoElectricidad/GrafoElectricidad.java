package grafoElectricidad;

import java.io.FileWriter;
import java.io.IOException;

public class GrafoElectricidad {

	public static void main(String[] args) throws Exception {
        String path = "test.txt";
        LectorArchivo rd = new LectorArchivo(path);
        
        rd.leerArchivo();
        
        PrimMST algoritmo = new PrimMST(rd.getMatriz());
        
        int cantNodos = rd.getCantNodos();
        int cantCentralesElec = rd.getCantCentrosElectricos();
        int[] posCentralesElec = rd.getPosCentrosElectricos();
        algoritmo.makePrimMST(posCentralesElec[0]);
        int[] predecesor = algoritmo.getPredecesora();
        boolean [] nodosElectrificados = new boolean[cantNodos];
        
        for(int i=0; i<cantCentralesElec; i++) {
        	nodosElectrificados[posCentralesElec[i]] = true;
        }
        int[][] matriz = new int[cantNodos - cantCentralesElec][2];
        int pos = 0;
        for(int i=0; i<cantNodos; i++) {
        	if(nodosElectrificados[i]) {
        		continue;
        	}
        	matriz[pos][0] = i+1;
        	matriz[pos][1] = predecesor[i]+1;
        	pos++;
        }        
        algoritmo.establecerCostos(posCentralesElec, 0);
        int costos=algoritmo.getCosto();
        guardarMatrizEnArchivo(matriz, costos, "salida.txt");
 	}

    public static void guardarMatrizEnArchivo(int[][] matriz, int costo, String archivo) {
        try (FileWriter writer = new FileWriter(archivo)) {
            writer.write(costo + "\n");
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[i].length; j++) {
                    writer.write(matriz[i][j] + " ");
                }
                writer.write("\n");
            }
            
            System.out.println("Matriz guardada exitosamente en " + archivo);
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
}
