public class LAB5
{
public static void main(String[] args)
{
    Box<String> a=new Box<String>("Genrerics");
System.out.println(a.getContent());
}



}
class Box<T>
{
    private T content;
    public Box(T content)
    {
        this.content=content;
    }
 void setContent(T item)
{
    this.content=item;
    
}
T getContent()
{
    return content;
}



}