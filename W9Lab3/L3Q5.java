import java.util.Scanner;

public class L3Q5 {
    public static void main(String[]args)
    {
Scanner input =new Scanner(System.in);
double a =input.nextDouble();
double b =input.nextDouble();
double c =input.nextDouble();   
double d =input.nextDouble(); 
double e =input.nextDouble(); 
double f =input.nextDouble(); 
double x =0,y=0;
x=(e*f-b*d)/(a*d-b*c);
y=(a*f-e*c)/(a*d-b*c);
System.out.printf("the key to the equation are x= %.2f and y = %.2f\n",x,y);
input.close();





    }
    
}
