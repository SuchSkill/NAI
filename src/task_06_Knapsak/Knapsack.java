package task_06_Knapsak;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Knapsack {
	
	private static int ITEMS = 30;
	private static int limit;
	private static int[] values = new int[ITEMS];
	private static int[] weights = new int[ITEMS];
	private static long start;
	
	public static void main(String[] args) {
		start = System.currentTimeMillis();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(new File("C:\\Users\\Eugene\\IdeaProjects\\NAI\\src\\task_06_Knapsak\\data\\15")));
			limit = Integer.parseInt(br.readLine().trim());
			int i=0;
			
			while(br.ready()){
				String line = br.readLine();
				if (!line.equals("")){
					String[] l = line.split(" ");
					values[i] = Integer.parseInt(l[0]);
					weights[i] = Integer.parseInt(l[1]);
				}
				i++;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		
		test();

		long elapsedTime = System.currentTimeMillis() - start;
		System.out.println("Time elapsed: "+elapsedTime/1000+"sec");
	}
	
	private static void test(){
		long vector = (long)Math.pow(2, values.length);
		long bestVector = 0;
		long bestWeight = 0;
		long bestValue = 0;
		
		while (vector>0){
			int value=0;
			int weight=0;
			for (int j=0; j<values.length; j++){
				if ((vector & (1L << j)) != 0)
				{
					value+=values[j];
					weight+=weights[j];
				}
			}
			if (weight<=limit && value>bestValue){
				System.out.println("new best "+Long.toBinaryString(vector));
				bestValue = value;
				bestWeight = weight;
				bestVector = vector;
			}
			vector--;
		}
		
		System.out.println("Best Vector: "+Long.toBinaryString(bestVector));
		System.out.println("Value: "+bestValue);
		System.out.println("Weight: "+bestWeight);
	}
}
