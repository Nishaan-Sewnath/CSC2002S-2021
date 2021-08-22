//Nishaan Sewnath
//CSC2002S-2021 Assignment 1 Part2
//Parallel Program

import java.util.concurrent.RecursiveTask;

public class retArr extends RecursiveTask<String>{
	

	private float[] arr;
	private float med=0;
	private int lo=0;
	private int hi=0;
	private int filtS=0;
	private int nMed =0;
	private static final int SEQUENTIAL_CUTOFF=500;

	private float[] result;
	private float[] coll1;
	private float[] nums;
	private float[] temp5;

	public int counter1 = 0;;
	public int counter2 = 0;
	private String ans ="";
	private String ans2 = "";

	public retArr(float[] arrN, int l, int h, int filt){

		arr = arrN;
		lo = l;
		hi = h;
		filtS = filt;
		nMed = (filtS-1)/2;

		nums = new float[filtS];
		coll1 = new float[hi-lo];

	}


	protected String compute(){

		if((hi-lo)<SEQUENTIAL_CUTOFF){
			

			for(int j = lo; j<(hi); j++){

				if(((j-nMed)>=0) && ((j+nMed)<arr.length)){
				counter2 = j;
				counter1 = 0;
				nums = popArr(nums, counter1, counter2);
				counter1 = 0;
				counter2 = counter1+1;
				nums = sortArray(nums, counter1, counter2);
				System.out.println("Median: "+nums[nMed]);
				return (""+nums[nMed]);
				}else{
					

					//System.out.println("Not in range: " + arr[j]);
					return (""+arr[j]);


				}
				


			}
			
			


		}else{

			retArr left = new retArr(result, lo, (hi+lo)/2, filtS);
			retArr right = new retArr(result, (hi+lo)/2, hi, filtS);

			
			left.fork();
			String rightAns = right.compute();
			String leftAns = left.join();

			//System.out.println("right: " + rightAns);
			//System.out.println("left: "+ leftAns);
			ans = ans + " " + leftAns + " " + rightAns;

			return ans;
		}

		return ans;
	}


	private float[] popArr(float[] nArr1, int c1, int c2){


		if(c2 == filtS){
			
			counter1 = 0;
			return nArr1;

		}else{

			nArr1[c1] = arr[c2-nMed];

			return popArr(nArr1, ++c1, ++c2);

		}

	}

	public String[] getAnswer(String nAns){

		String[] answer;
		ans = ans.substring(1);
		answer = ans.split(" ");

		for(int i = 0; i<answer.length; i++){

			System.out.println(answer[i]);


		}

		return answer;



	}

	private float[] sortArray(float[] nArr, int c1, int c2){
		
		float temp = 0.0f;
		float temp2 = 0.0f;
		float temp3 = 0.0f;

	

		if(c1 == nArr.length){

			return nArr;

		}else{

			temp = nArr[c1];
			temp2 = nArr[c2];
			if((temp>temp2)&& !(c2>=nArr.length)){

				temp3 = temp;
				nArr[c1] = temp2;
				nArr[c2] = temp3;
				return sortArray(nArr, c1, ++c2);


			}else{
				int tc = c1+2;

				return sortArray(nArr, ++c1, tc);

			}
			

		}
		

	}



}
