/**
 *Class that filter's for the median number
 *@author Nishaan Sewnath
 * 
 */

import java.util.*;
import java.io.*;

public class MedianFilt{


	static long startTime = 0;
	
	/**
	 *
	 * starts the nano Timer
	 *
	 * */

	private static void tick(){

		startTime = System.nanoTime();

	}


	/**
	 *
	 *@return returns the time in milliseconds
	 *
	 *
	 * */

	private static float tock(){

		return (System.nanoTime()-startTime)/1000000.0f;

	}


	/**
	 *
	 *@param arr the array being filtered
	 *@param filts the filter size
	 *
	 * @return the filtered array
	 * */


	public static float[] getMedian(float[] arr, int filtS){

		int temp1 =0;
		int med =0;
		float[] numArr = new float[filtS];
		float[] result = new float[arr.length];
		
		

		med = (filtS-1)/2;

		for(int k = 0; k<arr.length; k++){ 

        	
			if(((k-med)>=0) && ((k+med)<arr.length)){
			for(int z = 0; z<filtS; z++){
				
				temp1 = z;					

				for(int m = k-med; m<=k+med; m++){
					
					if(temp1>=filtS){

						temp1 = 0;

					}	
						
					numArr[temp1] = arr[m];
					++temp1;
						
				}


				Arrays.sort(numArr);
				int nMed = (filtS-1)/2;
				result[k] = numArr[nMed];			

			}

			

			}else{

				
				result[k] = arr[k];



			}


		
		}


		return result;


	}


	/**
	 *
	 *main method that asks for the input, processes the textfile in question and produces a text file as an output
	 *
	 * */

	public static void main(String []args){

		float[] result;
		

		System.out.println("Enter a file path and filter size and output file name respectively: ");
		Scanner scn = new Scanner(System.in);

		String input = scn.nextLine();

		String[] nInput = input.split(" ");

		int filtS = Integer.parseInt(nInput[1]);

		if((filtS<3||filtS>21)||filtS%2==0){


			System.out.println("Please enter a filter size between 3 and 21");
			System.exit(0);


		}



		try{


			
         	File br = new File(nInput[0]);
         	Scanner n = new Scanner(br);
			String s = "";
			FileWriter fw = new FileWriter(nInput[2]);
			BufferedWriter bw = new BufferedWriter(fw);
         
         	int counter = 0;
			int nsize = Integer.parseInt(n.nextLine());

			float[] farr = new float[nsize];
			String[] tem;

			while(n.hasNextLine()){

				s = n.nextLine();
				s = s.replace(",", ".");
				tem = s.split(" ");
				farr[counter] = Float.parseFloat(tem[1]);

			}
         


			result = new float[farr.length];
			float[][] sum = new float[10][15];
			
			//below code gets multiple runs of the filter method  15 times, averages them 10 time and the prints the result 

			for(int y = 0; y<10; y++){
			for(int a = 0; a<15; a++){
				tick();
				result = getMedian(farr,filtS);
				float time = tock();
            
            
				sum[y][a] = time;
			
			}
         
         
			}
			
			float sumTime = 0.0f;

			for(int v = 0; v<10; v++){

			for(int b = 3; b<15; b++){

		
				sumTime = sumTime+sum[v][b];

			}

				System.out.println("Average time for run " + v + " is " + sumTime+" milliseconds...");
				sumTime = 0.0f;



			}
				

			for(int j = 0; j<result.length; j++){


				bw.write(""+result[j]+"\n");

			}

			bw.close();






		}catch(IOException e){


			System.out.println("File did not load!");


		}



	}



}
