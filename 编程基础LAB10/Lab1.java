public class Lab1 {
public static void main(String[] args)
{
    Employee emp1=new ContractEmployee("ace",1000);
    Employee emp2=new TemporaryEmployee("bob",120);
emp1.display();
emp2.display();
}    
}
abstract class Employee
{
protected String name;
protected double salary;
abstract double calculateSalary();
public Employee(String name,double salary)
{
    this .name=name;
    this.salary=salary;

}
public void display()
{
    System.out.println("Name:"+name);
    System.out.println("Salary:"+calculateSalary());
}





}
class ContractEmployee extends Employee{
    int sales;
    public ContractEmployee(String n,int sales)
    {
        super(n,500);
        this.sales=sales;
    }
@Override
double calculateSalary()
{
return(salary+sales*0.5);
}

}
class TemporaryEmployee extends Employee{
int hoursWorked;
public TemporaryEmployee(String n,int hoursWorked)
{
    super(n,15);
    this .hoursWorked=hoursWorked;

}
@Override
double calculateSalary()
{
    return(salary*hoursWorked);

}

}