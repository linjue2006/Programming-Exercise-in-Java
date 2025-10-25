import java.util.Random;
public class Q5 {
    public static void main(String[] args){
Random n =new Random();
int numbers = n.nextInt(10001);
int sum=0;
for(int i=0, j=10;i<5;i++,j*=10){
sum+=numbers%j/(j/10);
}
System.out.printf("the numeber you entered and the sum of its each digit is :%d,%d\n",numbers,sum);





    }
    
}
