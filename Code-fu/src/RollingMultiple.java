/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andrej
 */
public class RollingMultiple {
    public static int findOriginal(int lastDigit, int multiple) {
        int counter = 1;
        int last_digit = lastDigit;
        int carry = 0;
        String number = "";
        while ( counter <= 9 ) {
            number += new Integer(last_digit).toString();
            int temp1 = new Integer(new StringBuffer(number).reverse().toString());
            String a = new Integer(temp1).toString();
            String b = a.substring(a.length()-1)+a.substring(0,a.length()-1);
            if ( new Integer(a)*multiple == new Integer(b) ) return new Integer(a);
            int temp = last_digit * multiple;
            last_digit = ((temp) %10 + carry) % 10;
            carry = (temp/10 +(((temp) %10 + carry)/10) );
            counter++;
            System.out.println(a+"  "+b + "  " + carry+ "   " + last_digit+"   "+temp);
        }
        if ( counter > 9 ) return -1;
        return 0;
    }


}
