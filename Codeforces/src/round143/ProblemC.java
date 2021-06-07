package round143;

import java.util.Scanner;

public class ProblemC {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int kx = in.nextInt();
		int ky = in.nextInt();
		int kz = in.nextInt();
		
		int x = in.nextInt();
		int y = in.nextInt();
		int z = in.nextInt();
		
		int a0 = in.nextInt();
		int a1 = in.nextInt();
		int a2 = in.nextInt();
		
		int a3 = in.nextInt();
		int a4 = in.nextInt();
		int a5 = in.nextInt();
		
		int sum = 0;
		//FROM BOTTOM
		if ( ky < 0 ) {
			sum += a0;
		}
		//FROM TOP
		if ( ky > y ) {
			sum += a1;
		}
		
		
		//TWO SIDES
		if ( kx < 0 && kz < 0 ) {
			sum += a2+a4;
		}
		//TWO SIDES
		if ( kx < 0 && kz > z ) {
			sum += a3+a4;
		}
		//TWO SIDES
		if ( kx > x && kz > z ) {
			sum += a3+a5;
		}
		//TWO SIDES
		if ( kx > x && kz < 0 ) {
			sum += a2+a5;
		}

		
		//one SIDE
		if ( (kx >= 0 && kx <= x) && kz < 0 ) {
			sum += a2;
		}
		//one SIDE
		if ( (kx >= 0 && kx <= x) && kz > z ) {
			sum += a3;
		}
		//one SIDE
		if ( kx > x && (kz >= 0 && kz <= z) ) {
			sum += a5;
		}
		//one SIDE
		if ( kx < 0 && (kz >= 0 && kz <= z) ) {
			sum += a4;
		}
		System.out.println(sum);
		
	}

}
