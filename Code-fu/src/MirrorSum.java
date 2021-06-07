/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andrej
 */
public class MirrorSum {

      //ќе напраеме посебна функција која ги дава мирорите на бдоевите
      //и за секој број од 0 до сум ќе го најдеме мироро и ќе ги собереме да видеме дали го задоволуват услово
      public static int getCount(int sum) {
          int count = 0;//kje broe kolku broevi go zadovoluvat uslovo
          //da gi izmineme site broevi od 1 do sum
          for ( int i = 1 ; i <= sum ; i++ ) {
              if ( mirror(i)+i == sum ) count++;
          }
          return count;
      }

    private static int mirror(int i) {
        int temp =  new Integer(new StringBuffer(new Integer(i).toString()).reverse().toString());
        return temp;
    }


              


}
