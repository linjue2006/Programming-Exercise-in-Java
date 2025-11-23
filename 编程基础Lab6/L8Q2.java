public class L8Q2 {
   public static void main(String[] args)
   {BankAccount ba=new BankAccount("ChatGPT",2023111L,5000L);
ba.mutate(50);
ba.display();





   } 
}
class BankAccount
{
//private String name;
//private long IC;
private long accountnumber;
public BankAccount(String name,long IC,long accountnumber)
{
//this .name=name;
//this.IC=IC;
this.accountnumber=accountnumber;

}
public void mutate(int amount)
{
    if (amount>0)
        accountnumber+=amount;
    else
        accountnumber-=amount;
}
public void display()
    {
System.out.printf("the money in your account is : %d\n",accountnumber);


    }




}
