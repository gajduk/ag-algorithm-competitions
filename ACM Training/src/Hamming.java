import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


class Node {
	public int count;

	public static List<Node> merge ( List<Node> nodes ) {
		if ( nodes.size() <= 1 ) return nodes;
		Node zero_node = nodes.get(0).count>nodes.get(1).count?nodes.get(0):nodes.get(1);
		Node one_node = nodes.get(0).count<nodes.get(1).count?nodes.get(0):nodes.get(1);
		for ( int k = 2 ; k < nodes.size() ; ++k ) {
			if ( nodes.get(k).count < zero_node.count ) {
				if ( nodes.get(k).count < one_node.count ) {
					zero_node = one_node;
					one_node = nodes.get(k);
				}
				else {
					zero_node = nodes.get(k);
				}
			}
		}
		nodes.remove(zero_node);
		nodes.remove(one_node);
		nodes.add(new InnerNode(zero_node,one_node));
		return nodes;
	}
	
	public String getEncoding (char c ) {
		return "";
	}
	

}

class LeafNode extends Node {
	public char the_char;
	
	public LeafNode( char the_char , int count) {
		this.the_char = the_char;
		this.count = count;
	}
	
	
	public String getEncoding (char c ) {
		if ( the_char == c)
			return "";
		else
			return null;
	}

}

class InnerNode extends Node {
	
	public Node zero_child;
	public Node one_child;
	
	public InnerNode(Node zero_child, Node one_child) {
		super();
		this.zero_child = zero_child;
		this.one_child = one_child;
		this.count = zero_child.count+one_child.count;
	}
	
	public String getEncoding (char c ) {
		String from_zero = zero_child.getEncoding(c);
		if ( from_zero != null ) return "0"+from_zero;
		String from_one = one_child.getEncoding(c);
		if ( from_one != null ) return "1"+from_one;
		return null;
	}


}

public class Hamming {
	
	public static void main(String[] args) {
		String text = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		String encoding[] = encode(text);
		System.out.println(Arrays.toString(encoding));
		System.out.println(encoding[(int)'a']);
	}

	private static String[] encode(String text) {
		int count[] = new int[256];
		for ( char c : text.toCharArray() ) {
			++count[c];
		}
		List<Node> nodes = new ArrayList<Node>();
		for ( int k = 0 ; k < count.length ; ++k ) {
			nodes.add(new LeafNode((char)k,count[k]));
		}
		while ( Node.merge(nodes).size() > 1 );
		Node root = nodes.get(0);
		String[] result = new String[count.length];
		for ( int k = 0 ; k < count.length ; ++k ) {
			result[k] = root.getEncoding((char)k);
		}
		return result;
	}

}
