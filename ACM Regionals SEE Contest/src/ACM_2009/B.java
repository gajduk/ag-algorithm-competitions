package ACM_2009;

import java.util.Scanner;

public class B {
	
	public static void main ( String args[] ) {
		Scanner in = new Scanner(System.in);
		int nodes[] = new int[10000];
		int nodes_count = 0;
		nodes[nodes_count++] = 0;
		nodes[nodes_count++] = 1;
		nodes[nodes_count++] = 2;
		nodes[nodes_count++] = 3;
		while ( in.hasNext() ) {
			String command = in.nextLine();
			command.trim();
			int index0 = Integer.parseInt(command.substring(0,command.indexOf(' ')));
			int index1 = Integer.parseInt(command.substring(command.lastIndexOf(' ')+1));
			command = command.substring(command.indexOf(' ')+1,command.lastIndexOf(' '));
//			System.out.println(index0+" "+command+" "+index1);
			
			for ( int i = command.length()-1 ; i >= 0 ; --i ) {
				if ( command.charAt(i) == 'f' ) {
					index1 += index1%2 == 0 ? 3 : 1;
				}
				if ( command.charAt(i) == 'b' ) {
					index1 -= 2;			
				}
				if ( command.charAt(i) == 'k' ) {
					System.out.println(index1);
				}
				if ( command.charAt(i) == '<' ) {
					nodes[nodes_count++] = index1;
				}
				if ( command.charAt(i) == '=' ) {
					System.out.println(index1==nodes[index0]?"=":"#");
				}
				
			}
		}
	}

}
