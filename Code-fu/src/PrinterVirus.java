/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andrej
 */
public class PrinterVirus {
          public String getPrintedText(String text) {
              String first = new StringBuffer(text.substring(0,text.length()/3)).reverse().toString();
              String second = new StringBuffer(text.substring(text.length()/3,text.length()/3*2)).reverse().toString();
              String third = new StringBuffer(text.substring(text.length()/3*2)).reverse().toString();
              return first+second+third;
          }


}
