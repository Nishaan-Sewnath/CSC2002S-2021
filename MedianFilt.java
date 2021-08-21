import java.util.*;
import java.io.*;

public class MedianFilt{


	public static void main(String []args){

		String[] ffl;
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

		System.out.println(nInput[0]);

		try{


			FileReader fl = new FileReader(nInput[0]);
			BufferedReader br = new BufferedReader(fl);
			String s = "";

			while((s = br.readLine())!=null){

				
				temp  = temp + " " + s;



			}
			temp = temp.replace(",",".");

			ffl = temp.split(" ");

			int numE = (ffl.length);

			System.out.println(""+ numE);
			float[] Arr1 = new float[numE];

			for(int i = 1; i < numE; i+=2){


				Arr1[i-1] = Float.parseFloat(ffl[i]);

				System.out.println(""+Arr1[i-1]);


			}

			int leng = Arr1.length - filtS;

			






		}catch(IOException e){


			System.out.println("File did not load!");


		}



	}



}
