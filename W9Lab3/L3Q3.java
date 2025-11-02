import java.util.Scanner;

public class L3Q3 {
  public static void main(String[]args)
    {
Scanner input =new Scanner(System.in);
double num =input.nextDouble();
double commission;
if(num<=100)
commission=num*0.05;
else if (num<=500)
commission=num*0.075;
else if(num<=1000)
commission=num*0.10;
else
commission=num*0.125;
System.out.printf("the commission is %.2f\n",commission);
input.close();










    }
}
