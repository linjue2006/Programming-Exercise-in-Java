public class L8Q7 {
 public static void main(String[]args)
 {
money m1=new money(34.58);
m1.round();
System.out.printf("the rounded amount is %.2f",m1.display());

 }   
}
class money
{
int d10;
int d1;
int d01;
int d001;
public money(double amount)
{
this .d10=(int)(amount/10);
this.d1=(int)(amount%10);
this.d01=(int)(amount*10%10);
this.d001=(int)(amount*100%10);

}
public void round()
{
if(d001<3)
    d001=0;
else if (d001>=3&&d001<=7)
    d001=5;
else
{
if (d01==9)
{
if (d1==9)
   { d10+=1;
d1=0;
d01=0;
d001=0;}
else
{
d1+=1;
d01=0;d001=0;
}
d01+=1;d001=0;



}
else
    d01+=1;d001=0;

}






}
public double display()
{
    return (d10*10+d1+d01*0.1+d001*0.01);
}}