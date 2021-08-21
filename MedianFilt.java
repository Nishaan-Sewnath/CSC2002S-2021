//Nishaan Sewnath

import java.util.*;
import java.io.*;

public class MedianFilt{


	public static float[] getMedian(float[] arr, int filtS){

		float temp =0.0f;
		float num1 =0.0f;
		float num2 =0.0f;
		float num3 =0.0f;
		int med =0;
		float[] numArr = new float[filtS];
		float[] result = new float[arr.length];
		
		//System.out.println(""+arr.length);

		med = (filtS-1)/2;

		for(int k = 0; k<arr.length; k++){


			//System.out.println(arr[k]);
			if(((k-med)>=0) && ((k+med)<arr.length)){
			for(int z = 0; z<filtS; z++){

				
				

					for(int m = k-med; m<=k+med; m++){
						numArr[z] = arr[m];
						System.out.println("" + numArr[z]);
					}

				System.out.println(" ");

				Arrays.sort(numArr);
			}
				if(filtS==3){

					System.out.println(numArr[med]+"\n");
					result[k] = numArr[med];


				}else{

					int nMed = (filtS-1)/2;
					result[k] = numArr[nMed];



				}

			

				//Arrays.sort(numArr);
				//result[k] = numArr[med];

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

		System.out.println("Enter a file path and filter size: ");
		Scanner scn = new Scanner(System.in);

		String input = scn.nextLine();

		String[] nInput = input.split(" ");

		int filtS = Integer.parseInt(nInput[1]);

		if((filtS<3||filtS>21)||filtS%2==0){


			System.out.println("Please enter a filter size between 3 and 21");
			System.exit(0);


		}

		//System.out.println(nInput[1]);

		try{


			FileReader fl = new FileReader(nInput[0]);
			BufferedReader br = new BufferedReader(fl);
			String s = "";

			while((s = br.readLine())!=null){

				
				temp  = temp + " " + s;



			}
			temp = temp.replace(",",".");

			ffl = temp.split(" ");

			int numE = Integer.parseInt(ffl[1]);

			//System.out.println(ffl[1]);
			float[] Arr1 = new float[numE];
			int var = 0;

			for(int i = 1; i < ((numE+1)*2); i+=2){

				if(i!=1){

				
					Arr1[i-(3+var)] = Float.parseFloat(ffl[i]);

					//System.out.println(""+Arr1[i-1]);
					++var;	
					
				}

			}

			/*for(int n = 0; n<Arr1.length; n++){

				System.out.println(""+Arr1[n]);

			}*/

			result = getMedian(Arr1,filtS);

			for(int j = 0; j<result.length; j++){


				System.out.println(""+result[j]);

			}

			






		}catch(IOException e){


			System.out.println("File did not load!");


		}



	}



}
