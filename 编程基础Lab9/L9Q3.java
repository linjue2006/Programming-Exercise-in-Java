public class L9Q3 {
    
}
class Account
{
private Long accountNumber;
private Double balance;
public Account(Long id,Double bal)
{
    this.accountNumber=id;
    this .balance=bal;

}
public void deposit(Double amount)
{
    balance+=amount;

}
public void withdraw(Double amount)
{
if(amount<=balance)
    balance-=amount;
else
    balance=0.0;//直接取光

}
public void display()
{
System.out.println("account number:"+accountNumber+",balance:"+balance);

}
public Double getBalance()
{return balance;
}


}
class SavingsAccount extends Account
{
private Double interestRate;
public SavingsAccount(Long id ,Double bal ,Double rate)
{
    super(id,bal);
    this.interestRate=rate;

}
public void applyInterest()
{
deposit(interestRate*super.getBalance());
}


}
class CheckingAccount extends Account{
private Double overdraftLimit;
public CheckingAccount(Long id,Double bal,Double limit)
{
super(id, bal);
this .overdraftLimit=limit;


}
@Override
public void withdraw(Double amount)
{
    if(amount>super.getBalance()+overdraftLimit)
        System.out.println("Withdrawal exceeds overdraft limit.");
    else
      super.withdraw(amount);
}





}