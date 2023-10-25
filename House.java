package code5_1001909995;
import  java.util.HashMap; 

public abstract class House 
{
    private String houseName; 
    HashMap<String, Integer>CandyList = new HashMap<>(); 
    public House(String houseName, HashMap<String, Integer>CandyList)
    {
        this.houseName = houseName; 
        this.CandyList = CandyList; 
        
    }
    
    public abstract String ringDoorbell(TrickOrTreater TOTobject ); 
     
}
