public class L8Q4 {
    public static void main(String[] args)
    {
Fraction f1=new Fraction(4,8);
f1.display();



    }
    
}
class Fraction
{
int n;
int d;
public Fraction(int n,int d)
{
this.n=n;
this.d=d;

}
public int cd(int n,int d)//for simplify such method ,we assume tha  numerator is smaller than denominator
{
  for (int i=n;i>=1;i--)
    {
        if (d%i==0&& n%i==0)
        {return i;
           

        }
    }
    return 1;//in case thar there is no common factor
}
public void display()
{
    System.out.printf("the common factor is %d\n",cd(n,d));
}





}
