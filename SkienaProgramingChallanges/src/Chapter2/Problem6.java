package Chapter2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Problem6 {

	public static final int INFINITY = -1;
	public static ArrayList<String> authors = new ArrayList<String>();
	public static ArrayList<String> titles = new ArrayList<String>();
	public static ArrayList neighbours[] = new ArrayList[5000];
	public static int distance[];
	public static int n;

	public static void populateAuthors() {
		authors.add("Erdos, P.");
		neighbours[0] = new ArrayList<Integer>();
		for (Iterator iterator = titles.iterator(); iterator.hasNext();) {
			String title = (String) iterator.next();
			StringTokenizer tkr = new StringTokenizer(title.substring(0,
					title.indexOf(':')), ", ");
			ArrayList<Integer> co_authors = new ArrayList<Integer>();
			while (tkr.hasMoreTokens()) {
				String author = tkr.nextToken() + ", " + tkr.nextToken();
				author = author.trim();
				int index = authors.indexOf(author);
				if (index != -1) {
					co_authors.add(index);
				} else {
					co_authors.add(authors.size());
					authors.add(author);
					neighbours[authors.size() - 1] = new ArrayList<Integer>();
				}
			}
			for (int i = 0; i < co_authors.size(); ++i) {
				for (int j = i + 1; j < co_authors.size(); ++j) {
					neighbours[co_authors.get(i)].add(co_authors.get(j));
					neighbours[co_authors.get(j)].add(co_authors.get(i));
				}
			}
		}
	}

	public static void print(int array[]) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}

	public static void print(boolean matrix[][]) {
		for (int i = 0; i < matrix.length; i++) {
			for (int m = 0; m < matrix[i].length; m++) {
				System.out.print(matrix[i][m] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void traverseGraph() {

		distance = new int[authors.size()];

		ArrayList<Integer> queue = new ArrayList<Integer>();
		boolean is_visited[] = new boolean[authors.size()];

		for (int i = 0; i < distance.length; i++) {
			distance[i] = INFINITY;
		}
		is_visited[0] = true;
		queue.add(0);
		int level = 0;
		int border, front, rear;
		rear = 0;
		front = 1;
		border = front;
		while (!queue.isEmpty()) {
			int current_author = queue.remove(0);
			distance[current_author] = level;
			++rear;
			for ( Object o : neighbours[current_author] ) {
				Integer i = (Integer) o;
				if (!is_visited[i]) {
					is_visited[i] = true;
					queue.add(i);
					++front;
				}
			}
			if (rear == border) {
				++level;
				border = front;
			}
		}

	}

	public static void erdosNumbers(String s_titles[], String output_authors[]) {
		populateTitles(s_titles);
		populateAuthors();
		// print(graph);
		traverseGraph();
		printErdosNumbers(output_authors);
	}

	private static void populateTitles(String[] s_titles) {
		for (int i = 0; i < s_titles.length; i++) {
			titles.add(s_titles[i]);
		}

	}

	public static void printErdosNumbers(String output_authors[]) {
		for (int i = 0; i < output_authors.length; i++) {
			StringTokenizer tkr = new StringTokenizer(output_authors[i], ", ");
			output_authors[i] = tkr.nextToken() + ", " + tkr.nextToken();
			System.out.print(output_authors[i]);
			if (authors.contains(output_authors[i])) {
				int erdos_number = distance[authors.indexOf(output_authors[i])];
				if (erdos_number == INFINITY) {
					System.out.println(" infinity");
				} else {
					System.out.println(" " + erdos_number);
				}
			} else {
				System.out.println(" infinity");
			}
		}
	}

	public static void main(String args[]) {
		// test_file();
		test_judge();
	}

	public static void test_judge() {
		Scanner in = new Scanner(System.in);
		int num_test_cases = new Integer(in.nextLine());
		for (int h = 0; h < num_test_cases; ++h) {
			String s_numbers = in.nextLine();
			StringTokenizer numbers = new StringTokenizer(s_numbers, " ");
			int num_titles = new Integer(numbers.nextToken());
			int num_authors = new Integer(numbers.nextToken());
			String s_titles[] = new String[num_titles];
			String s_authors[] = new String[num_authors];
			for (int k = 0; k < num_titles; ++k) {
				s_titles[k] = in.nextLine();
			}
			for (int k = 0; k < num_authors; ++k) {
				s_authors[k] = in.nextLine();
			}
			System.out.println("Scenario " + (h + 1));
			cleanAllData();
			erdosNumbers(s_titles, s_authors);
		}
	}

	public static void cleanAllData() {
		authors = new ArrayList<String>();
		titles = new ArrayList<String>();
	}

	public static void test_file() {
		FileInputStream inputFile = null;
		try {
			inputFile = new FileInputStream(
					"C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter2 problem6.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace(System.err);
			return;
		}
		Scanner in = new Scanner(inputFile);
		int num_test_cases = new Integer(in.nextLine());
		for (int h = 0; h < num_test_cases; ++h) {
			String s_numbers = in.nextLine();
			StringTokenizer numbers = new StringTokenizer(s_numbers, " ");
			int num_titles = new Integer(numbers.nextToken());
			int num_authors = new Integer(numbers.nextToken());
			String s_titles[] = new String[num_titles];
			String s_authors[] = new String[num_authors];
			for (int k = 0; k < num_titles; ++k) {
				s_titles[k] = in.nextLine();
			}
			for (int k = 0; k < num_authors; ++k) {
				s_authors[k] = in.nextLine();
			}
			System.out.println("Scenario " + (h + 1));
			cleanAllData();
			erdosNumbers(s_titles, s_authors);
		}
	}

}