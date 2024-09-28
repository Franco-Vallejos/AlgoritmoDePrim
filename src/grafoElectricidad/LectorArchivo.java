package grafoElectricidad;

import java.io.BufferedReader;
import java.io.FileReader;

public class LectorArchivo {
	private String path;
	private int [][] matriz;
	private int cantNodos, cantCentrosElectricos;
	private int [] posCentrosElectricos;
	public LectorArchivo(String path) {
		this.path = path;
	}
	
	public void leerArchivo() {
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String linea;

			linea = br.readLine();
			String[] partes = linea.split(" ");
			cantNodos = Integer.parseInt(partes[0]);
			cantCentrosElectricos = Integer.parseInt(partes[1]);

			this.posCentrosElectricos = new int[cantCentrosElectricos];
			this.matriz = new int[cantNodos][cantNodos];
			linea = br.readLine();
			partes = linea.split(" ");
			for(int i=0; i<partes.length; i++) {
				this.posCentrosElectricos[i]=Integer.parseInt(partes[i])-1;
			}
			int j=0;
			while ((linea = br.readLine()) != null) {
				partes = linea.split(" ");
				for(int i=0; i<partes.length; i++) {
					this.matriz[j][i]=Integer.parseInt(partes[i]);
				}
				j++;
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int[][] getMatriz(){
		return matriz;
	}
	
	public int getCantNodos() {
		return cantNodos;
	}
	
	public int getCantCentrosElectricos() {
		return cantCentrosElectricos;
	}
	
	public int[] getPosCentrosElectricos() {
		return posCentrosElectricos;
	}	
}
