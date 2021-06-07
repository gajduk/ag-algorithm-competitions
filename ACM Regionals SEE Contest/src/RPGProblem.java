

/**
 * 
 * @author Andrej Gajduk
 * 
 *  Problem 1:
 *	You're on a quest to find a blade. Your initial strength is inir_str
 *  On you quest you meet monsters with increasing strength.
 *  If your strength is larger then the monsters you win.
 *  When you win the monster drops potions.There are two kinds of potions. 
 *  (+1) and (x2). As you assume *  the +1 increases your strength by one 
 *  and the x2 double your strength. *  Given you current strength, the 
 *  monsters strength, and the potions which you get from defeating them 
 *  see if you can finish your quest. You don't have to fight the monsters
 *  in order, but you need to kill all of them.
 *  
 *  class RPGProblem
 *  
 *  method: boolean quest( int init_strength, int monsters[] , int potion1[] , int potion2[] )
 *  
 *  Problem 2:
 *  
 *  You want to make the most powerful spell. You have k spell points.
 *  Each spell has an initial damage which you get first time you upgrade it.
 *  Every next spell point invested gives you a flat out bonus.
 *  Some spells also promote other spells damage by a percentage.
 *  This percentage is increased linearly e.g. 5% than 10 % and so on. 
 *  How should you assign your spell points to get the most powerful spell.
 *  Or what is the most powerful spell damage.
 *  
 *  class RPGProblem
 *  
 *  method: int quest( String spells[] )
 *  
 *  format of spells: name,initial_damage,damage_increase, name,integer_percantage separated by ;
 */
public class RPGProblem {
	
	public static void main(String[] args) {
		RPGProblem r = new RPGProblem();
		int monsters[] = { 5,6,7,15,32,34};
		int potion1[] = { 1 , 1 , 1 , 0 , 1 , 0 };
		int potion2[] = { 1 , 0 , 0 , 1 , 0 , 0 };
		System.out.println(r.quest(5, monsters, potion1, potion2));
	}
	
	boolean quest( int init_strength, int monsters[] , int potion1[] , int potion2[] ) {
		int p1 = 0; int p2 = 0;
		int strength = init_strength;
		for ( int i = 0 ; i < monsters.length ; ++i ) {
			System.out.println(strength);
			if ( strength < monsters[i] ) {
				if ( strength<<p2 < monsters[i] ) return false;
				while ( strength  < monsters[i] ) { strength *= 2; --p2; }
			}
			strength += potion1[i];
			p2 += potion2[i]; 
		}
		return true;
		
	}

}
