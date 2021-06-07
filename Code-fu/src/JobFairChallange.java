
public class JobFairChallange {
	
	public static void main(String[] args) {
		JobFairChallange j = new JobFairChallange();
		System.out.println(j.xor("gajduk_andrej@yahoo.com"));
	}
	
	public int xor ( String email ) {
		int res = (int)email.charAt(0);
		for ( int i = 1 ; i < email.length() ; ++i ) {
			res = (res^((int)email.charAt(i)));
		}
		System.out.println(Integer.toBinaryString(res));
		return res;
	}

}
