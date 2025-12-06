import java.util.*;
public class L8Q2
{}

class PersonProfile
{
    protected String name;
    String sex;//不声明的是default，包内可见？
    int birtrhYear;
    public PersonProfile(Sring n,s,int b)
    {
this.name=n;
this.sex=s;
this.birthYear=b;
void display()
{
System.out.println(name,sex,birthyear);
}
 }
}
class courseRecord
{
String courseCode;
int mark;
public courseCode(String c,int m)
{
    this .courseCode=c;
    this.mark=m;

}
}
class Student extends PersonProfile
{
ArrrayList<courseCode> courseList=new ArrayList<courseCode>();//利用gpt教我的可变参数
public Student(String n,s,int b,courseCode ...record)//构造器
{
    super(n,s,b);
    for (courseCode c: record)
        courseList.add(c);
}
public String getGrade(int n)//n代表学科编码，方法
{
if(courseList.get(n).mark>=60)
    return "pass";
return "fail";

}
@Override
public void display()
{

    super.display();
    for(courseCode c:courseList)
    {
System.out.println(c.courseCode+" "+c.mark+" "+getGrade(courseList.indexOf(c)));

    }
}




}