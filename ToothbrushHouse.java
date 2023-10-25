package code5_1001909995;
import java.util.Random; 
import java.util.HashMap; 

public class ToothbrushHouse extends House
{
    public ToothbrushHouse(String houseName, HashMap<String, Integer>CandyList)
    {
        super(houseName, CandyList);
    } 
    @Override
    public synchronized String ringDoorbell(TrickOrTreater TOTobject)
    {
        String Candy = null; 
        TOTobject.addToPath("-"); 
        int sleepTime = 3000;  
        try 
        {
            Thread.sleep(sleepTime); 
        }
        catch(InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
        return "Toothbrush"; 
    }
    
}
