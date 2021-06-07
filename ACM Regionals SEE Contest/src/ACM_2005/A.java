package ACM_2005;

import java.util.Arrays;
import java.util.Scanner;

import javax.tools.Diagnostic;

class Person implements Comparable<Person> {
	String name;
	double x;
	int dirrection; // 1 - right ; -1 - left
	static double velocity;
	boolean exist = true;
	
	public Person ( int c, double b , String a ) {
		name = a;
		x = b;
		dirrection = c;
	}

	@Override
	public int compareTo( Person o ) {
		if ( !exist ) return -1;
		return x-o.x > 0 ? 1 : -1;
	}
	
}

public class A {
	
	public static void main ( String args[] ) {
		Scanner in = new Scanner(System.in);
		while ( true ) {
			int num_people = in.nextInt();
			if ( num_people == 0 ) {
				break;
			}
			Person.velocity = in.nextFloat();
			double length = in.nextFloat();
			Person people[] = new Person[num_people];
			for ( int i = 0 ; i < num_people ; ++i ) {
				people[i] = new Person(in.next().charAt(0) == 'p' ? 1 : -1,in.nextFloat(),in.next());
			}
			double total_time = 0;
			while ( true ) {
				Arrays.sort(people);
				double min_collision_time = Double.MAX_VALUE;
				int min_collision_index;
				for ( int i = 0 ; i < people.length-1 ; ++i ) {
					if ( people[i].dirrection != people[i+1].dirrection ) {
						double time = people[i+1].x-people[i].x;
						time /= Person.velocity;
						if ( time < min_collision_time ) {
							min_collision_index = i;
							min_collision_time = time;
						}
					}
				}
				double left_time = Double.MAX_VALUE;
				if ( people[0].dirrection == -1 ) {
					left_time = people[0].x;
					left_time /= Person.velocity;
				}
				double right_time = Double.MAX_VALUE;
				if ( people[num_people-1].dirrection == 1 ) {
					right_time = length-people[num_people-1].x;
					right_time /= Person.velocity;
				}
				double min_time = Math.min(left_time,Math.min(right_time,min_collision_time));
				total_time += min_time;
				int i = 0;
				while ( i < num_people-1 && !people[i++].exist );
				if ( i == num_people-1 ) {
					System.out.println(people[i].name);
				}
				while ( i < num_people ) {
					people[i].x += Person.velocity*min_time*people[i].dirrection;
					++i;
				}
				Arrays.sort(people);
				i = 0;
				while ( i < num_people-1 && !people[i].exist ) ++i;
				if ( i == num_people-1 ) {
					System.out.println(people[i].name);
					if ( people[i].dirrection == -1 ) {
						System.out.println(total_time+people[i].x/Person.velocity);
					}
					else {
						System.out.println(total_time+(length-people[i].x)/Person.velocity);
					}
					break;
				}
				while ( i < num_people ) {
					if ( people[i].x <= 0 ) {
						people[i].exist = true;
					}
					if ( people[i].x >= length ) {
						people[i].exist = true;
					}
					if ( i < num_people-1 && people[i].x == people[i+1].x ) {
						people[i].dirrection *= -1;
						people[i+1].dirrection *= -1;
						++i;
					}
					++i;
				}
			}
			
			
			
		}
	}

}
