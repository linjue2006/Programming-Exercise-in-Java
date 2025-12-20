import java.util.*;
public class LAB6 {
    public static void main(String[] args)
    {
List<Integer> intlist=new ArrayList<>(List.of(1,2,3));
System.out.println(findMaximum(intlist));
List<String> strlist=new ArrayList<>(List.of("apple","bannana","pear"));
System.out.println(findMaximum(strlist));







    }
    public static <T extends Comparable<T>> T findMaximum(List<T> list)
    {
        T max=list.get(0);
        for(T i:list)
        {
            if(i.compareTo(max)>0)
                max=i;
        }
        return max;
    }
}
