package Chapter12;

public class GridsUtility {

	public static void main(String args[]) {
		int grid[][] = new int[5][10];
		for (int i = 0; i < grid.length; ++i) {
			for (int j = 0; j < grid[i].length; ++j) {
				grid[i][j] = j + 1;
			}
		}
		rowMajorTraverse(grid);
		collumnMajorTraverse(grid);
		snakeOrderTraverse(grid);
		diagonalOrderTraverse(grid);
	}
	
	public static void diagonalOrderTraverse ( int grid[][] ) {
		int row = 0;
		int col = 0;
		int num_diagonals = grid.length+grid[0].length-1;
		int current_num_fields = 0;
		for ( int d = 0 ; d < num_diagonals ; ++d ) {
			row = Math.max(0,d-grid.length+1);
			col = Math.max(0,grid.length-d-1);
			current_num_fields = Math.min(grid.length-col,grid[0].length-row);
			for ( int i = 0 ; i < current_num_fields ; ++i ) {
				System.out.print(grid[col][row]+" ");
				++row;
				++col;
			}
		}
		System.out.println();
	}
	
	public static void snakeOrderTraverse(int grid[][]) {
		for (int i = 0; i < grid.length; ++i) {
			for ( int j = 0 ; j < grid[i].length ; ++j ) {
				System.out.print(grid[i][j+(grid[i].length-1-2*j)*(i%2)]+" ");
			}
		}
		System.out.println();
	}
	
	public static void collumnMajorTraverse(int grid[][]) {
		for (int i = 0; i < grid[0].length; ++i) {
			for (int j = 0; j < grid.length; ++j) {
				System.out.print(grid[j][i]+" ");
			}
		}
		System.out.println();
	}

	public static void rowMajorTraverse(int grid[][]) {
		for (int i = 0; i < grid.length; ++i) {
			for (int j = 0; j < grid[i].length; ++j) {
				System.out.print(grid[i][j]+" ");
			}
		}
		System.out.println();
	}

}
