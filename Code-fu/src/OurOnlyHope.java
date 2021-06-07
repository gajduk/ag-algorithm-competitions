import java.util.ArrayList;
import java.util.StringTokenizer;

class Path {
	public int min;
	public ArrayList<Integer> star_systems;
	public long length;
	
	public Path() {
		// TODO Auto-generated constructor stub
	}

	public Path(int max, long length , int start ) {
		super();
		this.min = max;
		this.star_systems = new ArrayList<Integer>();
		star_systems.add(start);
		this.length = length;
	}
	
	@Override
	public String toString() {
		return star_systems.toString();
	}
	
	
}

public class OurOnlyHope {
	
	public static void main(String[] args) {
		OurOnlyHope hope = new OurOnlyHope();
		int []transitTime = {95,27,54,78,99,99};
		String []connections = {"4;2;3","0;3;5;2;4","1;5;3","1;2;0","0;5;2","2;3;4;0"}; 
		hope.getDeathStarRoute(transitTime, connections, 2, 4);
	}
	
	ArrayList<Path> paths = new ArrayList<Path>();
	
	public String getDeathStarRoute(int[] transitTime, String[] connections, int alderaan, int yavin) {
		boolean edges[][] = new boolean[transitTime.length][transitTime.length];
		for ( int i = 0 ; i < connections.length ; ++i ) {
			StringTokenizer tkr = new StringTokenizer(connections[i],";");
			while ( tkr.hasMoreTokens() ) {
				int star = Integer.parseInt(tkr.nextToken());
				edges[i][star] = true;
			}
		}
		print(edges);
		boolean visited[] = new boolean[transitTime.length];
		int prev[] = new int[visited.length];
		explore(transitTime,edges,visited,prev,alderaan,yavin,0);
		System.out.println(paths);
	    return "";
	}
	
	private void print(boolean[][] d) {
		for ( int i = 0 ; i < d.length ; ++i ) {
			for ( int k = 0 ; k < d.length ; ++k ) {
				System.out.print(d[i][k]?"1":"0");
			}
			System.out.println();
		}
	}

	private void explore( int[] transitTime, boolean[][] edges, boolean[] visited, int[] prev, int current, int dest , int path_id ) {
		if ( dest == current ) return;
		if ( paths.size() >= path_id ) {
			paths.add(new Path(transitTime[current],transitTime[current],current));
		}
		Path path = paths.get(path_id);
		for ( int i = 0 ; i < edges.length ; ++i ) {
			if ( edges[current][i] && ! visited[i] && path.min <= transitTime[i] ) {
				Path new_path = path;
				new_path.star_systems.add(i);
				new_path.length += transitTime[i];
				new_path.min = transitTime[i];
				paths.add(new_path);
				explore(transitTime,edges,visited,prev,i,dest,paths.indexOf(new_path));
			}
		}
	}

}
