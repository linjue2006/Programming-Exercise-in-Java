public class L8Q3 {
    public static void main(String[] args)
    {
        WeightCaculator wc =new WeightCaculator(19,182.0);
        wc.display();
    }
}
class WeightCaculator
{
   int age;
   double height ;
   double recommendeweight;
   public WeightCaculator(int age,double height)
   {
    this.age=age;
    this.height=height;
    this .recommendeweight=calculate();
   } 
   private double calculate()
   {
recommendeweight=(height-100+age/10)*0.9;
return recommendeweight;
   }
   public void display()
   {
    System.out.printf("As your age is %d and your height is %.2f ,yr recommeded weight is %.2f",age,height,recommendeweight);

   }
}
