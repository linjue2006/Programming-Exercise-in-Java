public class L8Q6 {
 public static void main(String[] args)
 {
store s1 =new store("kfc",100);
store s2=new store("macdonalds",150);
s1.increase(50);
s1.display();
s2.display();




 }   
}
class store
{
private String name;
private int sell;
public static int ts=0;
public store (String name,int sell)
{
    this .name=name;
    this.sell=sell;
ts+=this.sell;
}
public void increase(int amount)
{
    this.sell+=amount;
    ts+=amount;
}
public void display()
{
    System.out.printf("this store %s has sold %d burgers\n",name,sell);
    System.out.printf("the total sales volumes id %d\n",ts);
}



}
