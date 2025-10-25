import java.util.Scanner;
public class Q1{
    public static void main(String[] args){
        Scanner fahrenheit = new Scanner(System.in);
        float x,celsius;
        System.out.printf("please enter a fahrenheit value:\n");
        x = fahrenheit.nextFloat();
        celsius = (x - 32) * 5.0f / 9.0f;
        System.out.printf("The celsius value is: %.2f\n", celsius);
        fahrenheit.close();
    }
}