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
	static String fArray(float[] arr, int filtS){
		
		retArr ret = new retArr(arr, 0, arr.length, filtS);
		return fjPool.commonPool().invoke(ret);


	}

	
	/**
	 *
	 *A recursive function that coverts the String array into a float array
	 *
	 *@param nums the String array being converted
	 *@param counter1 counter for the new array
	 *@param counter2 counter for the String array
	 *@param arr the new float array
	 *
	 *@return the new float array
	 *
	 * */

	public static float[] getArray(String[] nums, int counter1, int counter2, float[] arr){



		if(counter1 == (arr.length)){

			return arr;

		}else{

			if((counter2 != 0)&&(counter2%2==0)){
				arr[counter1] = Float.parseFloat(nums[counter2]);
				return getArray(nums, ++counter1, ++counter2, arr);
			}else{
				
				return getArray(nums, counter1, ++counter2, arr);
				
			}

		}	
		


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
		String[] nums;
		String hold = "";


		int filtS = 0;
		int size = 0;


		float[] result0;
		float[] result1;
		String result2="";




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

			FileReader fr = new FileReader(fileInName);
			FileWriter fw = new FileWriter(fileOutName);
			BufferedReader br = new BufferedReader(fr);
			BufferedWriter bw = new BufferedWriter(fw);
			
			String s = "";


			while((s=br.readLine()) != null){

				hold = hold + " " + s;

			}


			hold = hold.substring(1);
			hold = hold.replace(",",".");
			nums = hold.split(" ");
			size = Integer.parseInt(nums[0]);

			result0 = new float[size];
			float[] nm = new float[size];
			int tmp1 = 0;

			for(int m = 0; m<((size+1)*2)-1; m+=2){


				if((m-3-tmp1)>=size){

					break;

				}
				if((m!=0)){

				nm[m-(2+tmp1)] = Float.parseFloat(nums[m]);
				++tmp1;

				}
					

				
				
			}
	
			
			//the code below runs the parallel program 15 times, sotres them in an array and gets the the average of each of the 15 trials
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

			
			result2 = result2.substring(1);
			String[] arrStr = result2.split(" ");


			for(int i = 0; i<arrStr.length; i++){


				bw.write(arrStr[i] + "\n");


			}

			bw.close();
			
	

			

			




		}catch(IOException e){

			
			System.out.println("No File Was Loaded!");



		}





	}




}
