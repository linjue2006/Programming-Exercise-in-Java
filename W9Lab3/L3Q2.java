import java.util.Random;
public class L3Q2 {
    public static void main(String[]args){
    Random rand =new Random();
    int num1=rand.nextInt(6);
String result;
    switch(num1){

     case 0:
        result="zero";
        break;
    case 1:
    result="one";
    break;
    case 2:
    result="two";
    break;
    case 3:
    result="three";
    break;
    case 4:
    result="four"; 
    break; 
    case 5:
    result="five";
    break;
    default:
    result="Invalid number";
    break;
    }
    System.out.println("the number  "+num1+"is"+result);
  




}
}
