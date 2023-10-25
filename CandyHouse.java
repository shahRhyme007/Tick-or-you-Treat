package code5_1001909995;
import java.lang.Thread; 
import java.util.HashMap; 
import java.util.Random; 
 

public class CandyHouse extends House
{
    public CandyHouse(String houseName, HashMap<String, Integer>CandyList)
    {
        super(houseName, CandyList);
    } 
    @Override
    public synchronized String ringDoorbell(TrickOrTreater TOTobject)
    {
        String Candy = null; 
        TOTobject.addToPath("+"); 
        int sleepTime = 3000;  
        try 
        {
            Thread.sleep(sleepTime); 
        }
        catch(InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
        Random rand = new Random(); 
        int getRand = 0;  
        getRand = rand.nextInt(CandyList.size()-1)+1; 

        for (HashMap. Entry candyListVal : CandyList.entrySet())
        {
            String iteratorKey = null; 
            if ((int)candyListVal.getValue() == getRand)
            { 
                iteratorKey = (String)candyListVal.getKey(); 
                Candy = iteratorKey; 
            }
        }
        return Candy; 
    }
}


 