
public class FibonaciBitsKulev {
	int comb[][][] = new int[2][40][40];
	 
    void fillComb() {
        int i,j,k;
 
        // initial values for string length 1
        comb[0][1][0] = 1;
        comb[0][1][1] = 0;
        comb[1][1][0] = 0;
        comb[1][1][1] = 1;
 
        for (i=2;i<40;i++) {
            // for each length
            for (j=0;j<=i;j++) {
                // for each number of ones we want to get
                comb[0][i][j] = comb[0][i-1][j]+comb[1][i-1][j];
                if (j > 0) {
                    comb[1][i][j] = comb[0][i-1][j-1]+comb[1][i-1][j-1];
                }
            }
        }
 
    }
 
    // calculates the number of binary strings which are less than or equal to num
    // and which have number of ones in their binary representation which is
    // fibonacci number
    int calculate(int num) {
        int i,j,k;
        int res = 0;
        boolean fibonacci[] = new boolean[40];
 
        fibonacci[1] = true;
        fibonacci[2] = true;
        fibonacci[3] = true;
        fibonacci[5] = true;
        fibonacci[8] = true;
        fibonacci[13] = true;
        fibonacci[21] = true;
        fibonacci[34] = true;
 
        String s = Integer.toBinaryString(num);
        int L = s.length();
 
        // number of "good" numbers with length less than length of num
        for (i=1;i<L;i++) {
            for (j=0;j<=i;j++) {
                if (fibonacci[j] == true) {
                    res += comb[1][i][j];
                }
            }
        }
 
        // now we calculate for the number of "good" numbers with
        // length equal to length of num
        int passed_ones = 1;
        int N;
       
        for (i=1;i<L;i++) {
            if (s.charAt(i) == '1') {
                N = L-i;
                for (j=0;j<=N;j++) {
                    if (fibonacci[passed_ones+j] == true) {
                        res += comb[0][N][j];
                    }
                }
                passed_ones++;
            }
        }
       
        k = Integer.bitCount(num);
        if (fibonacci[k] == true) {
            res++;
        }
 
        return res;
    }
 
    public int modernFibonacci(int S, int E) {
        int a,b;
        fillComb();
 
        if (S == 0) {
            a = 0;
        } else {
            a = calculate(S-1);
        }
       
        b = calculate(E);
       
        return b-a;
    }
 
}
