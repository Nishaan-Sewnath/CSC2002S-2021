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
			
			
			/*for(int i = lo; i<hi; i++){
				
				if(counter1<(hi-lo)){
					
					addToArr(coll1, arr[i], counter1);


				}else{
					
					counter1 =0;
					//addToArr(coll1, arr[i], counter1);

				}




			}
			counter1 = 0;*/

			for(int j = lo; j<(hi); j++){

				
				counter2 = j;
				if(!(count1<filtS)){

					
					counter1 = 0;

					ans2 = ans2 + " " + nums[nMed];
					//addToArr(nums, coll1[j], counter1);


				}else{

					//counter1 = 0;
					nums = popArr(nums, counter1, counter2);
					counter1 = 0;
					counter2 = 0;
					nums = sortArray(nums, counter1, counter2);

				}


			}
			
			


		}else{

			retArr left = new retArr(result, lo, (hi+lo)/2);
			retArr right = new retArr(result, (hi+lo)/2, hi);

			
			left.fork();
			String rightAns = right.compute();
			String leftAns = left.join();
			ans = ans + " " + leftAns + " " + rightAns;

			return ans
		}


	}

	private void addToArr(float[] nArr, float entry, int counter){


			nArr[counter] = entry;
			++counter1;
		


	}

	private float[] popArr(float[] nArr1, int c1, int c2){


		if(c2 == filtS){
			
			counter1 =0;
			return nArr1;

		}else{

			nArr1[c1] = arr[c2-nMed];

			return popArray(nArr1, ++c1, ++c2);

		}

	}

	public String[] getAnswer(){

		String[] answer;
		answer = ans.split(" ");

		return answer;



	}

	private float[] sortArray(float[] nArr, int c1. int c2){
		
		float temp = 0.0f;
		float temp2 = 0.0f;
		float temp3 = 0.0f;

		if(c1 == nArr.length){

			return nArr;

		}else{

			temp = nArr[c1];
			temp2 = nArr[c2]
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
