/**
 *This class is the brains behind the parallel filtering program
 *
 *@author Nishaan Sewnath
 *
 * */

import java.util.concurrent.RecursiveTask;

public class retArr extends RecursiveTask<String>{
	

	private float[] arr;
	private float med=0;
	private int lo=0;
	private int hi=0;
	private int filtS=0;
	private int nMed =0;
	private static final int SEQUENTIAL_CUTOFF= 20;

	/*
	 *
	 *for sample input 5 sc = 10;
	 *for sample input 100 SC = 15;
	 *for sample input 1000 sc = 30;
	 *for sample input 10000 sc = 30;
	 *for sample input 100000 sc = 25;
	 *for sample input 1000000 sc = ;
	 *for sample input 10000000 sc = ;
	 *
	 */

	private float[] nums;
	private float[] temp5;


	public int counter1 = 0;;
	public int counter2 = 0;
	private String ans ="";
	private String ans2 = "";


	/**
	 *
	 *The constuctor for the program
	 *
	 *@param arrN the array being filtered
	 *@param l basically always atarts off as 0
	 *@param h originally the length of arrN
	 *@param filtS the filter size
	 *
	 *
	 * */


	public retArr(float[] arrN, int l, int h, int filt){

		arr = arrN;
		lo = l;
		hi = h;
		filtS = filt;
		nMed = (filtS-1)/2;

		nums = new float[filtS];

	}


	/**
	 *
	 *The part of the program that makes it a parallel program
	 *
	 *
	 *
	 * */


	protected String compute(){

		String var1 = "";
		

		if((hi-lo)<SEQUENTIAL_CUTOFF){
			

			for(int j = lo; j<(hi); j++){

				if(((j-nMed)>=0) && ((j+nMed)<arr.length)){
				counter2 = j;
				counter1 = 0;
				nums = popArr(nums, counter1, counter2, lo);
				nums = sortArray(nums, 0, 0);
				var1 = var1 + " " + nums[nMed];
				}else{
					
					if(j<arr.length){
						var1 = var1+" "+ arr[j];
					}

				}
				


			}
			
			return var1;


		}else{

			retArr left = new retArr(arr, lo, (hi+lo)/2, filtS);
			retArr right = new retArr(arr, (hi+lo)/2, hi, filtS);

			
			left.fork();
			String rightAns = right.compute();
			String leftAns = left.join();

			ans = ans + leftAns + rightAns;

			return ans;
		}

	
	}


	/**
	 *
	 *This is a recursive method that populates an array of size filts
	 *
	 *@param nArr1 the resulting array
	 *@param c1 the counter for the resulting array
	 *@param c2 the counter for the old array
	 *@param l the lo value
	 *
	 *@return the resulting array
	 *
	 *
	 * */


	private float[] popArr(float[] nArr1, int c1, int c2, int l){


		if(c1 == filtS){
			
			return nArr1;

		}else{

			nArr1[c1] = arr[c2-nMed];

			return popArr(nArr1, ++c1, ++c2, l);

		}

	}



	/**
	 *
	 *A recursive method that sorts the array 
	 *
	 *@param nArr the array beign sorted
	 *@param c1 the counter for the outer for loop
	 *@param c2 the counter for the inner for loop
	 *
	 *@return the sorted array
	 *
	 *
	 * */

	private float[] sortArray(float[] nArr, int c1, int c2){
		
		float temp = 0.0f;
		float temp2 = 0.0f;
		float temp3 = 0.0f;
	
		if(c1 == nArr.length){
			
			counter1 = 0;
			counter2 = 0;
			return nArr;

		}else{

			
			if((nArr[c1] > nArr[c2]) && (c2<nArr.length)){
				
				temp = nArr[c1];
				temp2 = nArr[c2];
				temp3 = temp;
				nArr[c1] = temp2;
				nArr[c2] = temp3;
				return sortArray(nArr, c1, ++c2);


			}else{
				
				return sortArray(nArr, ++c1, c2);

			}
			

		}
		

	}



}
