import java.util.Scanner;
public class Q2 {
  public static void main(String [] args){
Scanner sc = new Scanner(System.in);
System.out.printf("please enter several infortmation we need:\n");
float p = sc.nextFloat();
float d =sc.nextFloat();
float r=sc.nextFloat();
float y=sc.nextFloat();
float m=(p-d)*(1+r*y/100)/(y*12);
System .out .printf("the monthly payment is :%.2f\n",m);
sc.close();




  }  
}
