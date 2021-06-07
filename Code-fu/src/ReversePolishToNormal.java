import java.util.StringTokenizer;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author Andrej
 */
public class ReversePolishToNormal {
	
	String expr;
	
	public static void main(String []args)
	{
		System.out.println((new ReversePolishToNormal()).transform("3 2 6 - * 5 4 + - 7 -"));
	}
	
	public String transform(String expr) {
		this.expr = expr;
		return doIt(expr.length()-1).retStr;
	}
	
	retValue doIt(int pos)
	{
		
		retValue rt;
		
		if (Character.isDigit(expr.charAt(pos)))
		{
			rt = new retValue(expr.charAt(pos)+"", pos-2, ' ');
		}
		else
		{
			retValue rt1 = doIt(pos - 2);
			retValue rt2 = doIt(rt1.toWhere);
			if (expr.charAt(pos)=='*')
			{
				if (rt1.op=='-' || rt1.op=='+')
				{
					rt1.retStr = "("+rt1.retStr+")";
				}
				if (rt2.op=='-' || rt2.op=='+')
				{
					rt2.retStr = "("+rt2.retStr+")";
				}
			}
			if (expr.charAt(pos)=='-')
			{
				if (rt1.op=='-' || rt1.op=='+')
				{
					rt1.retStr = "("+rt1.retStr+")";
				}
			}
			rt = new retValue(rt2.retStr + expr.charAt(pos) + rt1.retStr,rt2.toWhere, expr.charAt(pos));
		}
		return rt;
	}
}

class retValue
{
	public String retStr;
	public int toWhere;
	public char op;
	
	public retValue(String retStr, int toWhere, char op) {
		super();
		this.retStr = retStr;
		this.toWhere = toWhere;
		this.op = op;
	}
	
	
}
