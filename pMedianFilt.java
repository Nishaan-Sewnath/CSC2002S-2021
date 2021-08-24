/**
 *
 *main class that runs the parallelism class
 *@author Nishaan Sewnath
 *
 *
 * */

import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
import java.io.*;



public class pMedianFilt{

	static long startTime = 0;

	
	/**
	 *starts the timer
	 *
	 * */

	private static void tick(){

		startTime = System.nanoTime();

	}

	/**
	 *
	 *Stops the timer
	 *@return time in milliseconds
	 *
	 *
	 * */

	private static float tock(){

		return (System.nanoTime()-startTime)/1000000.0f;

	}

	static final ForkJoinPool fjPool = new ForkJoinPool();


	/**
	 *
	 *intvokes the retArr program using fjJoinPool
	 *@param arr the array being filtered
	 *@param filtS the filter Size
	 *@return the invoked program
	 *
	 * */
	static float[] fArray(float[] arr, int filtS){
		
		retArr ret = new retArr(arr, 0, arr.length, filtS);
		return fjPool.commonPool().invoke(ret);


	}



	/**
	 *
	 *The main method
	 *
	 *
	 * */




	public static void main(String [] args){

		String input = "";
		String[] nInput;
		String fileInName = "";
		String fileOutName = "";
		
		


		int filtS = 0;
		
		float [] result2;




		System.out.println("Enter an input file name, filter size and output file name respectively: ");
		Scanner scn = new Scanner(System.in);
		input = scn.nextLine();
		nInput = input.split(" ");
		fileInName = nInput[0];
		filtS = Integer.parseInt(nInput[1]);
		fileOutName = nInput[2];


		if((filtS<3)||(filtS>21)||(filtS%2==0)){


			System.out.println("Please enter an odd filter size number between 3 and 21!");
			System.exit(0);


		}

		try{

			
			FileWriter fw = new FileWriter(fileOutName);
         
         	File fl = new File(fileInName);
         	Scanner br = new Scanner(fl);
			
			BufferedWriter bw = new BufferedWriter(fw);
			
			int nsize = Integer.parseInt(br.nextLine());
         
         
		 	float[] nm = new float[nsize];
			result2 = new float[nsize];

         	int counter = 0;
			while(br.hasNextLine()){
            	String tempo = br.nextLine();
            	String[] arry = tempo.split(" ");
				nm[counter] = Float.parseFloat(arry[1].replace(",","."));

				
            	++counter;

			}
         
         

	
			
			//the code below runs the parallel program 15 times, stores them in an array and gets the the average of each of the 15 trials
			float[][] sum = new float[10][15];
         
         
        

			for(int k = 0; k<10; k++){


				for(int c = 0; c<15; c++){
					tick();

					result2 = fArray(nm, filtS);

					float time = tock();

					sum[k][c] = time;


				}

			}
			

			float sumTime = 0.0f;

			for(int b = 0; b<10; b++){

				for(int d = 3; d<15; d++){


					sumTime+=sum[b][d];
				}

				System.out.println("Average time of run " + b + " is " + sumTime+ " milliseconds...");

				sumTime = 0.0f;
			}

			
			


			for(int i = 0; i<result2.length; i++){


				bw.write(result2[i] + "\n");


			}

			bw.close();
			
	

			

			




		}catch(IOException e){

			
			System.out.println("No File Was Loaded!");



		}





	}




}
