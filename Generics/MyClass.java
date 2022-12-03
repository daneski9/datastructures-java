package Generics;
import java.util.*;

class Hello<T>{  //can have mutliple types. Hello<T,G,H,...>  (useful for hashmap?)
    
    //now that we have T, we don't need multiple classes with the same code, just to have a different variable. We have generics.(ex. a class that works on doubles exclusively, or ints, etc)
    T genericType;
    Hello(T genericType){ //since we don't care/know of the variable being passed in. We will use generic.
     this.genericType = genericType;

    }
    void showType(){ //function that prints out type of variable
        System.out.println(genericType.getClass().getName());
    }
    
    
    
}
public class MyClass {
    public static void main(String[] args) {
        Hello<Integer> obj = new Hello<>(10);    //what gets passed in needs to be an OBJECT type, not primative(ex int, double) -> object type = Integer, Double
        Hello<Double> obj2 = new Hello<>(20.00); 
        obj.showType();
        obj2.showType(); 
        
        
        
        
        
    }
    
    
    
    
    
}
