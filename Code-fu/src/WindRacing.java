
public class WindRacing {
	
	public static void main(String[] args) {
		WindRacing w = new WindRacing();
		int p[] = {0,0,0};
		System.out.println(w.getSpeed(4, p, 400));
	}

	public String getSpeed(int n, int[] percentage, int t) {
		double sum = 100;
		for ( int i = 0 ; i < percentage.length ; ++i ) {
			sum += (percentage[i]+100.0);
		}
		System.out.println(sum);
		double init_v = (double)(n*n*100000)/(t*sum);
		return init_v+"";
	}

}
