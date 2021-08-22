//Nishaan Sewnath
//CSC2002S-2021 Assignment 1
//Sequential Program

import java.util.*;
import java.io.*;

public class MedianFilt{


	static long startTime = 0;


	private static void tick(){

		startTime = System.nanoTime();

	}

	private static float tock(){

		return (System.nanoTime()-startTime)/1000000.0f;

	}


	public static float[] getMedian(float[] arr, int filtS){

		float temp =0.0f;
		float num1 =0.0f;
		float num2 =0.0f;
		float num3 =0.0f;
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

	public static void main(String []args){

		String[] ffl;
		float[] result;
		String temp = "";

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


			FileReader fl = new FileReader(nInput[0]);
			BufferedReader br = new BufferedReader(fl);
			String s = "";
			FileWriter fw = new FileWriter(nInput[2]);
			BufferedWriter bw = new BufferedWriter(fw);

			while((s = br.readLine())!=null){

				
				temp  = temp + " " + s;



			}
			temp = temp.replace(",",".");

			ffl = temp.split(" ");

			int numE = Integer.parseInt(ffl[1]);

			
			float[] Arr1 = new float[numE];
			int var = 0;

			for(int i = 1; i < ((numE+1)*2); i+=2){

				if(i!=1){

				
					Arr1[i-(3+var)] = Float.parseFloat(ffl[i]);

					++var;	
					
				}

			}

			result = new float[Arr1.length];
			for(int a = 0; a<5; a++){
				tick();
				result = getMedian(Arr1,filtS);
				float time = tock();
				System.out.println("Run " + a + " took: "+ time+ " millisecond...");
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
