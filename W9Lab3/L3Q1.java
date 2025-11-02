import java.util.Scanner;
public class L3Q1{
    public static void main(String[] args){
System.out.println("Please enrter two numbers and an operator:");
Scanner input=new Scanner(System.in);
double num1=input.nextDouble();
double num2=input.nextDouble();
char operator=input.next().charAt(0);
double result;
switch(operator){
    case '+':
    result=num1+num2;
    break;
    case '-':
    result=num1-num2;
    break;
    case'*':
    result=num1*num2;
    break;
    case'/':
    result=num1/num2;
    break;
    case'%':
    result=num1%num2;
    break;
    default:
    System.out.println("Invalid operator");
    input.close();
    return;
}
System.out.println("Result: "+result);




    }









}