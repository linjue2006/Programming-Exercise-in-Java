import java.util.Scanner;

public class L1Q6 {
    public static void main(String[]args)
    {
System.out.printf("please enter the radius of a circle and a coordinate point(x,y):\n");
Scanner input =new Scanner(System.in);
double radius = input.nextDouble();
double x = input.nextDouble();
double y = input.nextDouble();
double distance =Math.sqrt(x*x + y*y);
if(distance<=radius)
System.out.printf("the point is in the circle\n");
else
System.out.printf("the point is not in the circle\n");
input.close();




    }
    
}
