
public class RebelAllianceComm {
	
	public static void main(String[] args) {
		RebelAllianceComm r = new RebelAllianceComm();
		System.out.println(r.countFrequencies(7, 2, 16));
	}
	
	 public int countFrequencies(int A, int k, int m) {
		 int counter = 1;
		 long a = (long)A;
		long K = k;
		long M = m;
	   a = (a*K+M)%M;
		 boolean[] visited = new boolean[100001];
		 visited[A] = true;
		 while ( !visited[(int)a] ) {
			 visited[(int)a] = true;
			 a = (a*K+M)%M;
          
			 ++counter;
		 }
		 return counter;
	 }

}
