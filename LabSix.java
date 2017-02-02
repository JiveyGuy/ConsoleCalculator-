
// Test 1:
// Objective: verify operation with negative numbers
// Test input: -1 + -1
// Expected output: -2
//output: -2

// Test 2:
// Objective: verify that program will not divide by 0
// Test input: 3 / 0
// Expected output: You cannot use a zero to divide or mod. 
//output: You cannot use a zero to divide or mod. 

// Test 3:
// Objective: Check if HELP (uppercase) will still return help. 
// Test input: HELP
// Expected output: {Help dialog}
//output: {Help Dialog}

// Test 4:
// Objective: verify that program will not explode when 0 is used as numerator.
// Test input: 4 / 0
// Expected output: 0
//output: 0

// Test 5:
// Objective: Verify the power function will work
// Test input: 2 ^ 2
// Expected output: 4
//output: 4




import java.io.*; //Here we import the things we need
import java.lang.Math;
import java.util.Scanner;
public class LabSix
{
  private static int opMinus = 0;
  private static int opAdd = 0;
  private static int opDivide = 0;
  private static int opTimes = 0;
  private static int opPower = 0;
  private static int opMod = 0; 
  private static int opRoot = 0;
  private static int arrayCount = 0;
  private static int allOps = opMinus + opAdd + opDivide + opTimes + opPower + opMod;
  public static void main ( String[] args ) throws IOException
  {
    String input = "";
    String operator;
    String fileName;
    PrintWriter fileWriter;
    int counter = 0;
    double numberTwo;
    double numberOne = 0;
    double highest = 0;
    double lowest = 0;
    double total = 0;
    double answer = 0;
    double [] answerArray = {0};
    boolean caseOfZero;
    
    
    LabSix.prompt(4, counter, answer);
    input = LabSix.input();
    fileName = input;
    fileWriter = LabSix.fileWrite(fileName);
    while (LabSix.runLoop(input, counter))
    {
      counter++;
      LabSix.prompt(1, counter, answer);
      input = LabSix.input();
      if(LabSix.help(input))
        continue;
      if(LabSix.prevNumber(answerArray, input, counter ))
      {
        numberOne = answer;
      }
      else if(LabSix.hasIt(input))
      {
        numberOne = LabSix.parse(input);
      }
      else
        break;   
      LabSix.prompt(2, counter, answer);
      operator = LabSix.input();
      operator = LabSix.operator(operator);
      operator = LabSix.validOperator(operator);
      LabSix.prompt(3, counter, answer);
      input = LabSix.input();
      numberTwo = LabSix.parse(input);
      caseOfZero = LabSix.zeroCheck(numberTwo, operator);
      answer = LabSix.operation(operator,  numberOne, numberTwo, caseOfZero, fileWriter);
      answerArray = LabSix.array(answer);
      total = LabSix.total(answer, total, caseOfZero);
      highest = LabSix.statistics(answer, highest, lowest, counter, 1);
      lowest = LabSix.statistics(answer, highest, lowest, counter, 2); 
    }
    LabSix.endStats(total, highest, lowest, counter);
    fileWriter.close();
  }
  public static double[] array(double answer)
  {
    
    double [] initialArray = {};
    for (int index = 0; index < answer; index++)
    {
      initialArray = addOneIntToArray( initialArray , answer);
      ++LabSix.arrayCount;
    }
    return initialArray;
  }
  public static double[] addOneIntToArray( double[] initialArray , double length)
  {
    double[] newArray = new double[initialArray.length +1];
    for ( int index = 0; index < initialArray.length; index++)
    {
      newArray[index] = initialArray[index];
    }
    newArray[ newArray.length - 1] = length;
    return newArray;
  }
  public static boolean prevNumber(double[] answer, String input, int counter)
  {
    double prevNumber = answer[answer.length - 1];
    boolean oldNumber = false;
    if (input.equals("a" )|| input.equals("A" ) )
    {
      if(counter > 1)
      {
        oldNumber = true;
        System.out.println("You used the number " + prevNumber);
      }
    } 
    return oldNumber;
  }
  public static void prompt(int option, int counter, double answer)
  {
    if (option == 1)
    {
      if(counter > 1 && answer > 0)
        System.out.print("\n To exit press [enter]. For help type [help]. To use your last answer type [a]"
                           +"\n Type your first digit here: ");
      else  
        System.out.print("\n To exit press [enter]. For help type [help]."
                           +"\n Type your first digit here: ");
    } 
    if(option == 2)
      System.out.print("Type your operator here: ");
    if(option == 3)
      System.out.print("Type your second digit here: ");
    if(option == 4)
      System.out.print("What is the name of the file you want to use? ");
  }
  public static String input() 
  {
    String blank = "";
    Scanner keyboard = new Scanner(System.in);
    String result = keyboard.nextLine();
    if (LabSix.hasIt(result)) 
    {
      
      return result;
    }
    else
      return blank;
  }
  public static double parse(String number)
  {
    double result = Double.parseDouble(number);
    return result;
  }
  public static boolean runLoop(String input, int counter)
  {
    boolean loopIt = false;
    if(counter == 0)
      loopIt = true;
    if(LabSix.hasIt(input))
      loopIt = true;
    return loopIt;
  }
  public static boolean hasIt(String input)
  {
    boolean hasIt = true;
    if(input.trim().length() == 0)
      hasIt = false;
    return hasIt;
  }
  public static boolean hasRun(int count)
  {
    boolean isSecond = false;
    if(count >= 1)
      isSecond = true;
    return isSecond; 
  }
  public static PrintWriter fileWrite(String fileName) throws IOException
  {
    PrintWriter printwriter = new PrintWriter(new FileWriter(fileName,true));
    return printwriter;
  }
  public static String operator(String operator)
  {
    if ( operator.trim().length() > 1)
    {
      System.out.print("only one operator! \n");
      return operator;
    }
    if ( operator.trim().length() <= 0)
    {
      System.out.print("At least 1 operator! ");
      System.out.print("Type your operator here: ");
      operator = LabSix.input();
      operator = LabSix.operator(operator);
      return operator;
    }
    else
      return operator;
  }
  public static boolean help(String input)
  {
    final String KEY_WORD1 = "help";
    final String KEY_WORD2 = "exit";
    if (input.substring(0,Math.min(input.length(), KEY_WORD1.length())).equals(KEY_WORD1.substring(0,Math.min(input.length(), KEY_WORD1.length()))))
    { 
      System.out.println("To divide use /");
      System.out.println("To multiply use *");
      System.out.println("To add use +");
      System.out.println("To subtract use -");
      System.out.println("To power n by n use ^ ");
      System.out.println("To rrot n by n use ~");
      System.out.println("To exit type exit");
      System.out.println("All of your operations can be found in the log file you chose");
      System.out.println("To use yoru last answer use a in the first dialog");
      return true;  
    }
    else
    {
      System.out.println("Invalid Characters");
      return false;
    }
  }
  
  
  public static double operation(String operator, double numberOne, 
                                 double numberTwo, boolean caseOfZero, PrintWriter fileWriter) 
  {
    double answer = 0;
    
    PrintWriter printWriter = fileWriter;
    switch (operator.trim().toLowerCase().charAt(0) )
    {
      case '+': 
        answer = numberOne + numberTwo;
        printWriter.println(numberOne + " + " + numberTwo + " = " + answer);
        System.out.println(numberOne + " + " + numberTwo + " = " + answer);
        ++LabSix.opAdd;
        
        break;
      case '-':
        answer = numberOne - numberTwo;
        ++LabSix.opMinus;
        printWriter.println(numberOne + " - " + numberTwo + " = " + answer);
        System.out.println(numberOne + " - " + numberTwo + " = " + answer);
        break;
      case '*':
        answer = numberOne * numberTwo;
        ++LabSix.opTimes;
        printWriter.println(numberOne + " * " + numberTwo + " = " + answer);
        System.out.println(numberOne + " * " + numberTwo + " = " + answer);
        break;
      case '^':
        answer = Math.pow(numberOne, numberTwo);
        ++LabSix.opPower;
        printWriter.println(numberOne + " ^ " + numberTwo + " = " + answer);
        System.out.println(numberOne + " ^ " + numberTwo + " = " + answer);
        break;
      case '~':
        
        if (numberOne < 0){
        answer = -Math.pow(Math.abs(numberOne), (1 / numberTwo));
      }
        answer = Math.pow(numberOne, 1.0 / numberTwo);
        ++LabSix.opRoot;
        printWriter.println(numberOne + " ~ " + numberTwo + " = " + answer);
        System.out.println(numberOne + " ~ " + numberTwo + " = " + answer);
        break;
      case '/':
        //Here we check for that naughty 0.    
        
        if (caseOfZero)
      {
        answer = numberOne / numberTwo;
        printWriter.println(numberOne + " / " + numberTwo + " = " + answer);
        System.out.println(numberOne + " / " + numberTwo + " = " + answer);
        ++LabSix.opDivide;
      }
        break;
      case '%':
        
        if (caseOfZero)
      {
        answer = numberOne % numberTwo;
        printWriter.println(numberOne + " % " + numberTwo + " = " + answer);
        System.out.println(numberOne + " % " + numberTwo + " = " + answer);
        LabSix.opMod++;
      }
        
        break;
      default: System.out.println("Try using the operators  + - / % ^ or ~ for square root.") ;
      break;
    }
    return answer;
  }
  
  
  public static double total(double answer, double total, boolean zeroCheck)
  {
    if(zeroCheck)
    {
      total += answer;
      System.out.println("The running total is " + total);  
    }
    return total;
    
  }public static boolean zeroCheck(double numberTwo, String operator)
  {
    boolean zeroCheck = true;
    if (numberTwo == 0)
    {
      
      if (operator.equals("/") || operator.equals("%"))
      {
        zeroCheck = false;
        System.out.println("You cannot use a zero to divide or mod.");
      }
      
    }
    else
      zeroCheck = true;
    return zeroCheck;
  }
  
  public static double statistics(double answer, double counter, double highest, double lowest, int option)
  {
    double result = 0;   
    if(counter == 1)
    {
      result = answer;
    }
    else
    {
      if(option == 1)
      {
        result = highest;
        if(answer > highest)
        {
          result = answer;
        } 
      }
      if(option == 2)
      {
        result = lowest;
        if(answer < lowest)
        {
          result = answer;
        }
      }
    }
    return result;
  }
  public static String validOperator(String operator)
  {
    boolean isValid = false;
    switch (operator.trim().toLowerCase().charAt(0) )
    {
      case '+':
        isValid = true;
        break;
      case '-':
        isValid = true;
        break;
      case '*':
        isValid = true;
        break;
      case '^':
        isValid = true;
        break;
      case '~':
        isValid = true;
        break;
      case '/':
        isValid = true;
        break;
      case '%':
        isValid = true;
        break;
      default: 
        isValid = false;
        System.out.println("Invalid operator! \n Try using the operators  + - / % ^ or ~ for the nth root.") ;
        if (!(isValid))
        {
          System.out.print("Type your operator here: ");
          operator = LabSix.input();
          operator = LabSix.operator(operator);
          operator = LabSix.validOperator(operator);
        }
        break;  
    }
    return operator;
  }
  public static void endStats(double total, double highest, double lowest, int counter)
  {   
    if (counter > 1)
    {
      System.out.printf("You preformed ");
      if (LabSix.opAdd >= 1)
      {
        System.out.printf("%d additions, ", opAdd);
      }
      if (LabSix.opMinus >= 1)
      {
        System.out.printf(" %d subtractions, ", LabSix.opMinus);
        
      }
      if (LabSix.opTimes >= 1)
      {
        System.out.printf("%d multiplications, ", LabSix.opTimes);
      }
      if (LabSix.opDivide >= 1)
      {
        System.out.printf("%d divisons, ", LabSix.opDivide);
      }
      if (LabSix.opMod >= 1)
      {
        System.out.printf("%d mods, ", LabSix.opMod);
      }
      if (LabSix.opPower >= 1) 
      {
        System.out.printf("%d powers, ", LabSix.opPower);
      }
      //statistics
      System.out.println("in all you preformed " + counter + " operations.");
      System.out.println("The grand total is: " + total );
      System.out.println("The lowest number is: " + lowest);
      System.out.println("The highest number is: " + highest);  
    }
  }
}  