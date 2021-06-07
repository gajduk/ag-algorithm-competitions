/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Andrej
 */
public class ComputerClock {
      public String time(int seconds) {
          int hours = seconds/3600;
          int mins = (seconds%3600)/60;
          int sec = seconds%60;
          String t1 = "";
          if ( hours < 10 )
              t1 += "0" + new Integer(hours).toString();
          else
              t1 += new Integer(hours).toString();
          t1 += ":";
          if ( mins < 10 )
             t1 += "0" + new Integer(mins).toString();
          else
              t1 += new Integer(mins).toString();
          t1 += ":";
          if ( sec < 10 )
            t1 += "0" + new Integer(sec).toString();
          else
              t1 += new Integer(sec).toString();
          return t1;
      }


}
