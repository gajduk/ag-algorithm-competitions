package Chapter1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream.GetField;
import java.util.Scanner;



/*
 1.6.6 Interpreter
PC/UVa IDs: 110106/10033, Popularity: B, Success rate: low Level: 2
A certain computer has ten registers and 1,000 words of RAM. Each register or
RAM location holds a three-digit integer between 0 and 999. Instructions are encoded
as three-digit integers and stored in RAM. The encodings are as follows:

100 means halt

2dn means set register d to n (between 0 and 9)

3dn means add n to register d

4dn means multiply register d by n

5ds means set register d to the value of register s

6ds means add the value of register s to register d

7ds means multiply register d by the value of register s

8da means set register d to the value in RAM whose address is in register a

9sa means set the value in RAM whose address is in register a to that of register s

0ds means goto the location in register d unless register s contains 0

All registers initially contain 000. The initial content of the RAM is read from stan-
dard input. The first instruction to be executed is at RAM address 0. All results are
reduced modulo 1,000.

Input
The input begins with a single positive integer on a line by itself indicating the number
of cases, each described as below. This is followed by a blank line, and there will be a
blank line between each two consecutive inputs.
Each input case consists of up to 1,000 three-digit unsigned integers, representing the
contents of consecutive RAM locations starting at 0. Unspecified RAM locations are
initialized to 000.

Output
The output of each test case is a single integer: the number of instructions executed
up to and including the halt instruction. You may assume that the program does halt.
Separate the output of two consecutive cases by a blank line.
Sample Input
1

299
492
495
399
492
495
399
283
279
689
078
100
000
000
000

Sample Output
16
 */

/*
test case0:possible error
input:
2

221
235
100

245
294
100

output:
3
3

 */

public class Problem6 {
	//static int counter;
	/*
	public static void main ( String args[] ) {
		 //test();
		 
		 Scanner in = new Scanner(System.in);
		 int cases = new Integer(in.nextLine());
		 //System.out.println(cases);
		 in.nextLine();
		 for ( int i = 0 ; i < cases-1 ; ++i ) {
			 int length = 0;
			 int []commands = new int[1000];
			 for ( int j = 0 ; j < 1000 ; ++j ) {
				 commands[j] =0;
			 }
			 String temp = in.nextLine(); 
			 while ( ! temp.equalsIgnoreCase("") ) {
				 commands[length++] = new Integer(temp);
				 temp = in.nextLine(); 
			 }
			 
			// commands[length++] = new Integer(temp);
			 interpreter(commands,length);
			 System.out.println();
			 in.nextLine();
		 }
		 int length = 0;
		 int []commands = new int[1000];
		 for ( int j = 0 ; j < 1000 ; ++j ) {
			 commands[j] =0;
		 }
		 String temp = in.nextLine(); 
		 while ( ! temp.equalsIgnoreCase("") ) {
			 commands[length++] = new Integer(temp);
			 
			 temp = in.nextLine(); 
		 }
		 //commands[length++] = new Integer(temp);
		 //printCommands(commands);
		 interpreter(commands,length);
		 
	}
	*/
	
	static void printCommands( int t[] ) {
		for ( int i = 0 ; i < t.length ; ++i )
			System.out.println(t[i]);
	}
	
	
	/*
	static void test() {
		for ( int i = 0 ; i < 1000000 ; i++ ) {
			int commands_num = 1000;
			int commands[] = new int[commands_num];
			for ( int h = 0 ; h < commands_num-1 ; ++h ) {
				int operation = (int) (Math.random()*6)+2;
				int operand1 = (int) (Math.random()*9);
				int operand2 = (int) (Math.random()*9);
				commands[h] = operation*100+operand1*10+operand2;
			}
			commands[commands_num-1] = 100;
			interpreter(commands,commands_num);
		}
	}
	*/
	
	//static int[] registers = new int[10];
	
	/*
	public static void executeCommand( int command ) {
		if ( command / 100 == 2 ) {
            registers[(command/10)%10] = command%10;
        }
        if ( command / 100 == 3 ) {
            registers[(command/10)%10] += command%10;
            registers[(command/10)%10] %= 1000;
        }
        if ( command / 100 == 4 ) {
            registers[(command/10)%10] *= command%10;
            registers[(command/10)%10] %= 1000;
        }
        if ( command / 100 == 5 ) {
            registers[(command/10)%10] = registers[command%10];
        }
        if ( command / 100 == 6 ) {
            registers[(command/10)%10] += registers[command%10];
            registers[(command/10)%10] %= 1000;
        }
        if ( command / 100 == 7 ) {
            registers[(command/10)%10] *= registers[command]%10];
            registers[(command/10)%10] %= 1000;
        }
        if ( command / 100 == 8 ) {
            registers[(command/10)%10] = commands[registers[command%10]];
        }
        if ( command / 100 == 9 ) {
            commands[registers[(command/10)%10]] = registers[command%10];
        }
        current_command++;
        if ( commands[current_command] / 100 == 0 ) {
            if ( registers[commands[current_command]%10] != 0 ) {
                current_command = registers[(commands[current_command]/10)%10];
            }
        }
	}
	*/
	
	/*
	public static void interpreter ( int[] commands , int length ) {
        int current_command = 0;
        int counter = 0;
        int []registers = new int[10];
        
        for ( int i = 0 ; i < 10 ; ++i ) registers[i] = 0;
       
        while ( commands[current_command] != 100 ) {
            counter++;
      //      System.out.println("Executing command "+commands[current_command]+" with id number "+current_command);
            if ( commands[current_command] / 100 == 2 ) {
                registers[(commands[current_command]/10)%10] = commands[current_command]%10;
                current_command++;
                continue;
            }
            if ( commands[current_command] / 100 == 3 ) {
                registers[(commands[current_command]/10)%10] += commands[current_command]%10;
                registers[(commands[current_command]/10)%10] += 1000;
                registers[(commands[current_command]/10)%10] %= 1000;
                current_command++;
                continue;
            }
            if ( commands[current_command] / 100 == 4 ) {
                registers[(commands[current_command]/10)%10] *= commands[current_command]%10;
                registers[(commands[current_command]/10)%10] += 1000;
                registers[(commands[current_command]/10)%10] %= 1000;
                current_command++;
                continue;
            }
            if ( commands[current_command] / 100 == 5 ) {
                registers[(commands[current_command]/10)%10] = registers[commands[current_command]%10];
                current_command++;
                continue;
            }
            if ( commands[current_command] / 100 == 6 ) {
                registers[(commands[current_command]/10)%10] += registers[commands[current_command]%10];
                registers[(commands[current_command]/10)%10] += 1000;
                registers[(commands[current_command]/10)%10] %= 1000;
                current_command++;
                continue;
            }
            if ( commands[current_command] / 100 == 7 ) {
                registers[(commands[current_command]/10)%10] *= registers[commands[current_command]%10];
                registers[(commands[current_command]/10)%10] += 1000;
                registers[(commands[current_command]/10)%10] %= 1000;
                current_command++;
                continue;
            }
            if ( commands[current_command] / 100 == 8 ) {
                registers[(commands[current_command]/10)%10] = commands[registers[commands[current_command]%10]];
                current_command++;
                continue;
            }
            if ( commands[current_command] / 100 == 9 ) {
                commands[registers[commands[current_command]%10]] = registers[(commands[current_command]/10)%10];
                current_command++;
                continue;
            }
            if ( commands[current_command] / 100 == 0 ) {
                if ( registers[commands[current_command]%10] != 0 ) {
                    current_command = registers[(commands[current_command]/10)%10];
                }
                else {
                	current_command++;
                }
            }
            
            
            
         //   print_status(registers);
        }
        counter++;
       // System.out.println("done with this microprogram");
       // System.out.println();
        System.out.println(counter);
    }
	
	static void print_status( int registers[]) {
		 System.out.println("  Status of the registers: ");
	     for ( int i = 0 ; i < 10 ; i++ ) System.out.print("reg"+i+"="+registers[i]+"  ");
	     System.out.println();
	     System.out.println();
	}
	*/
	
	public static int instructions_executed;
	public static int[] registers = new int[10];
	public static int[] ram = new int[1000];
	public static int current_command;
	public static boolean handbrake;
	
	public static int getCommand ( int value ) {
		return value/100;
	}
	
	public static int getOperand1 ( int value ) {
		return value%100/10;
	}
	
	public static int getoperand2 ( int value ) {
			return value%10;
	}
	
	public static final int STOP = 1;
	public static final int SETI = 2;
	public static final int ADDI = 3;
	public static final int MULTI = 4;
	public static final int SET = 5;
	public static final int ADD = 6;
	public static final int MULT = 7;
	public static final int LOAD = 8;
	public static final int STORE = 9;
	public static final int GOTO = 0;
	
	public static void main ( String args[] ) {
		//test_file();
		test_judge();
	}
	
	public static void test_judge() {
		Scanner in = new Scanner(System.in);
    	int num_test_cases = new Integer(in.nextLine());
    	in.nextLine();
    	for ( int i = 0 ; i < num_test_cases ; ++i ) {
    		int counter = 0;
    		initRegisters();
    		initRAM();
    		if ( in.hasNext() ) {
    			String next_line = in.nextLine();
	    		while ( next_line.length() != 0  ) {
	    			ram[counter++] = new Integer(next_line);
	    			if ( ! in.hasNext() ) {
	    				break;
	    			}
	    			next_line = in.nextLine();
	    		}
    		}
    		interpreter();
    		System.out.println(instructions_executed);
    		if ( i != num_test_cases-1 ) {
    			System.out.println();
    		}
    	}
	}

	public static void test_file() {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter1 problem6.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	int num_test_cases = new Integer(in.nextLine());
    	in.nextLine();
    	for ( int i = 0 ; i < num_test_cases ; ++i ) {
    		int counter = 0;
    		initRegisters();
    		initRAM();
    		if ( in.hasNext() ) {
    			String next_line = in.nextLine();
	    		while ( next_line.length() != 0  ) {
	    			ram[counter++] = new Integer(next_line);
	    			if ( ! in.hasNext() ) {
	    				break;
	    			}
	    			next_line = in.nextLine();
	    		}
    		}
    		interpreter();
    		System.out.println(instructions_executed);
    		if ( i != num_test_cases-1 ) {
    			System.out.println();
    		}
    	}
	}
	
	public static void cleanRegisters () {
		for ( int i = 0 ; i < registers.length ; ++i ) {
			registers[i] = 0;
		}
	}

	public static void initRAM () {
		for ( int i = 0 ; i < ram.length ; ++i ) {
			ram[i] = 0;
		}
	}
	
	public static void initRegisters () {
		for ( int i = 0 ; i < registers.length ; ++i ) {
			registers[i] = 0;
		}
	}
	
	public static void print ( int a[] ) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(i+"["+a[i]+"]"+"  ");
		}
		System.out.println();
	}
	
	public static void interpreter ( ) {
		instructions_executed = 0;
		handbrake = false;
		current_command = 0;
		while ( ! handbrake ) {
			//System.out.println("Current command id = " + current_command + " current command = " + ram[current_command]);
			//print(registers);
			
			if ( getCommand(ram[current_command]) == ADDI ) {
				addi(getOperand1(ram[current_command]),getoperand2(ram[current_command]));
				continue;
			}
			if ( getCommand(ram[current_command]) == SETI ) {
				seti(getOperand1(ram[current_command]),getoperand2(ram[current_command]));
				continue;
			}
			if ( getCommand(ram[current_command]) == MULTI ) {
				multi(getOperand1(ram[current_command]),getoperand2(ram[current_command]));
				continue;
			}
			if ( getCommand(ram[current_command]) == STOP ) {
				if ( getOperand1(ram[current_command]) == 0 && getoperand2(ram[current_command]) == 0 ) {
					stop();
				}
				continue;
			}
			if ( getCommand(ram[current_command]) == SET ) {
				set(getOperand1(ram[current_command]),getoperand2(ram[current_command]));
				continue;
			}
			if ( getCommand(ram[current_command]) == ADD ) {
				add(getOperand1(ram[current_command]),getoperand2(ram[current_command]));
				continue;
			}
			if ( getCommand(ram[current_command]) == MULT ) {
				mult(getOperand1(ram[current_command]),getoperand2(ram[current_command]));
				continue;
			}
			if ( getCommand(ram[current_command]) == LOAD ) {
				load(getOperand1(ram[current_command]),getoperand2(ram[current_command]));
				continue;
			}
			if ( getCommand(ram[current_command]) == STORE ) {
				store(getOperand1(ram[current_command]),getoperand2(ram[current_command]));
				continue;
			}
			if ( getCommand(ram[current_command]) == GOTO ) {
				go_to(getOperand1(ram[current_command]),getoperand2(ram[current_command]));
				continue;
			}
		}
	}
	
	public static void stop () {
		++instructions_executed;
		handbrake = true;
	}
	
	public static void seti ( int operand1 , int operand2 ) {
		registers[operand1] = operand2;
		++current_command;
		++instructions_executed;
	}
	
	public static void addi ( int operand1 , int operand2 ) {
		registers[operand1] += operand2;
		registers[operand1] %= 1000;
		++current_command;
		++instructions_executed;
	}

	public static void multi ( int operand1 , int operand2 ) {
		registers[operand1] *= operand2;
		registers[operand1] %= 1000;
		++current_command;
		++instructions_executed;
	}

	public static void set ( int operand1 , int operand2 ) {
		registers[operand1] = registers[operand2];
		++current_command;
		++instructions_executed;
	}

	public static void add ( int operand1 , int operand2 ) {
		registers[operand1] += registers[operand2];
		registers[operand1] %= 1000;
		++current_command;
		++instructions_executed;
	}	

	public static void mult ( int operand1 , int operand2 ) {
		registers[operand1] *= registers[operand2];
		registers[operand1] %= 1000;
		++current_command;
		++instructions_executed;
	}

	public static void load ( int operand1 , int operand2 ) {
		registers[operand1] = ram[registers[operand2]];
		++current_command;
		++instructions_executed;
	}

	public static void store ( int operand1 , int operand2 ) {
		ram[registers[operand2]] = registers[operand1];
		++current_command;
		++instructions_executed;
	}
	
	public static void go_to ( int operand1 , int operand2 ) {
		if ( registers[operand2] != 0 ) {
			current_command = registers[operand1];
		}
		else {
			++current_command;
		}
		++instructions_executed;
	}

}
