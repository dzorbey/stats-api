package nl.stats.camel.test;

import java.util.ArrayList;
import java.util.List;

import nl.stats.rest.service.MarkovService;

public class Markov {

	public static List<List<Double>> matrixInitilize(List<List<Double>> matrix, int size) {
        for(int i = 0; i < size; i++) {
        	matrix.add(new ArrayList<Double>());
            for(int j = 0; j < size; j++) {
            	matrix.get(i).add(0.0);
            }
        }
		return matrix;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
		
		List<List<Double>> matrix = new ArrayList<>();
		
		matrix = matrixInitilize(matrix, 4);
		

		
		matrix.get(0).set(0, 0.50);
		matrix.get(0).set(1, 0.05);
		matrix.get(0).set(2, 0.20);
		matrix.get(0).set(3, 0.25);
		
		matrix.get(1).set(0, 0.50);
		matrix.get(1).set(1, 0.00);
		matrix.get(1).set(2, 0.24);
		matrix.get(1).set(3, 0.26);
		
		matrix.get(2).set(0, 0.25);
		matrix.get(2).set(1, 0.25);
		matrix.get(2).set(2, 0.10);
		matrix.get(2).set(3, 0.40);
		
		matrix.get(3).set(0, 0.25);
		matrix.get(3).set(1, 0.15);
		matrix.get(3).set(2, 0.30);
		matrix.get(3).set(3, 0.30);
		
		
		
		System.out.println(matrix.get(1).get(3));
		
		
		
		MarkovService service = new MarkovService(4);
		
		service.checkSize();
		
		
		service.calculate(matrix, 4, 2);
		

		service.conditions();
		
	}
	

}
