public class L9Q2 {
    
}
class Person
{
private String name;
private Long id;
public Person(String n, long i)
{
this.name=n;
this.id=i;

}
public String getName()
{
    return name;
}
public Long getId()
{return id;}
public void displayInfo()
{
System.out.println(name);
System.out.println(id);
}
}
class Employee extends Person
{
private String department;
public Employee(String n,long i,String d)
{

    super(n, i);
    this .department=d;

}
public String getDepartment()
{
    return department;

}
@Override
public void displayInfo()
{
    super.displayInfo();
    System.out.println(department);
}


}