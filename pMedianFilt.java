//Nishaan Sewnath
//CSC2002S-2021 Assignment 1
//Parallel Program


import java.util.*;
import java.io.*;



public class pMedianFilt{

	static long startTime = 0;


	private static void tick(){

		startTime = System.nanoTime();

	}

	private static float tock(){

		return (System.nanoTime()-startTime)/1000000.0f;

	}

	static final ForkJoinPool fjPool = new ForJoinPool();

	static float[] fArray(float[] arr, int filtS){

		return fjPool.invoke(new retArr(arr, 0, arr.length, filtS));


	}


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




	public static void main(String [] args){

		String input = "";
		String[] nInput;
		String fileInName = "";
		String fileOutName = "";
		String[] nums;
		String hold = "";


		int filtS = 0;
		int size = 0;

		float[] result1;
		float[] result2;




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
			nums = hold.split(" ");
			size = Integer.parseInt(nums[0]);

			result = new float[size];

			System.out.println(""+"a "+ nums[1]);

			result1 = getArray(nums, 0, 0, result);
			result2 = fArray(result1, 0, result1.length, filtS);


			for(int i = 0; i<result2.length; i++){

				System.out.println(""+result2[i]);


			}
	

			

			




		}catch(IOException e){

			
			System.out.println("No File Was Loaded!");



		}





	}




}
