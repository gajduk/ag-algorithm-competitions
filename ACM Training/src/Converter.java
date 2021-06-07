import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;


public class Converter {
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream("primer_file.txt"));
		File f = new File("primer_file.arff");
		BufferedWriter out = new BufferedWriter(new FileWriter(f));
		
		out.write("@relation random-ime\n");
		out.write("\n");
		//prethodniot pat site atributi bea numeric
		int num_attr = 55;
		for ( int i = 0 ; i < num_attr ; ++i ) {
			out.write("@attribute attr"+i+" numeric\n");
		}
		out.write("@attribute class {");
		out.write("two-fingers,");
		out.write("whatever,");
		out.write("qwerty");
		out.write("}\n");
		out.write("\n");
		
		while ( in.hasNext() ) {
			String line = in.nextLine();
			//tuka mozesh da pustis nekoi regexi ako ti trebaat
			//primer da izbroish kolku atributi vkupno ima ako ne pisuva
			//System.out.println(line.split(",").length);
			
			
			out.write(line+"\n");
		}
		
		/**
		 so ovoj kod mozesh da gi videsh site klasi koi se dokolku te mrzi da gi baras racno
		 */
		/*
		HashSet<String> poss_vals = new HashSet<String>();
		Scanner in = new Scanner(new FileInputStream("primer_file.txt"));
		while ( in.hasNext() ) {
			String line = in.nextLine();
			int temp = line.lastIndexOf(',');
			if ( temp != -1 ) {
				String val = line.substring(temp+1);
				poss_vals.add(val);
			}
		}
		System.out.println(poss_vals.size());
		for ( String s : poss_vals ) {
			System.out.println(s);
		}
		*/
	}

}
