import java.util.Arrays;


public class FibonacciBits {
	int cache[] = {	3278467,3071813,3071813,2675293,3071813,2675293,2675293,2138633,3071813,2675293,2675293,2138633,2675293,2138633,2138633,1664258,3071812,2675294,2675293,2138633,2675293,2138633,2138633,1664258,2675293,2138633,2138633,1664258,2138633,1664258,1664258,1472967,3071808,2675297,2675293,2138634,2675292,2138634,2138633,1664258,2675292,2138634,2138633,1664258,2138633,1664258,1664258,1472967,2675291,2138635,2138633,1664258,2138633,1664258,1664258,1472967,2138633,1664258,1664258,1472967,1664258,1472967,1472967,1653884,3071798,2675302,2675293,2138638,2675288,2138638,2138633,1664259,2675287,2138638,2138633,1664259,2138632,1664259,1664258,1472967,2675286,2138639,2138633,1664259,2138632,1664259,1664258,1472967,2138632,1664259,1664258,1472967,1664258,1472967,1472967,1653884,2675282,2138642,2138633,1664260,2138631,1664260,1664258,1472967,2138631,1664260,1664258,1472967,1664258,1472967,1472967,1653884,2138630,1664261,1664258,1472967,1664258,1472967,1472967,1653884,1664258,1472967,1472967,1653884,1472967,1653884,1653884,2095876 };
		public static void main(String[] args) {

		      System.out.println(Integer.toBinaryString(2105646));
		FibonacciBits f = new FibonacciBits();
		System.out.print(f.modernFibonacci(484565653, 1009882653));

		
	}
	
	public int modernFibonacci(int S, int E) {
		int s_index = S/(Integer.MAX_VALUE/128)+1;
		int e_index = E/(Integer.MAX_VALUE/128);
		System.out.println(s_index+" "+e_index);
		int res = 0;
		for ( int i = s_index ; i < e_index ; ++i ) {
			res += cache[i];
		}
		res += modernFibonacciCached(S,s_index*(Integer.MAX_VALUE/128))+modernFibonacciCached(e_index*(Integer.MAX_VALUE/128),E);
		return res;
	}
	
	
	public int modernFibonacciCached(int S, int E) {
		      int cond = 2105646;
	          int counter = 0;//how many numbers are there so far that are ok
	          for ( int i = S ; i <= E ; i++ )  {
	              counter += (cond & 1<<numOnes(i))>0?1:0;
	          }
	          return counter;
	}
	
	private int numOnes(int i)
	{
	    i = i - ((i >> 1) & 0x55555555);
	    i = (i & 0x33333333) + ((i >> 2) & 0x33333333);
	    return (((i + (i >> 4)) & 0x0F0F0F0F) * 0x01010101) >> 24;
	}
	
}

