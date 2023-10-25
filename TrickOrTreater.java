package code5_1001909995;

import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Thread; 
import java.util.Random; 


public class TrickOrTreater implements Runnable
{
    public static int ImDone; 
    
    private String name; 
    private String path = "."; 
    private ArrayList<House> ListOfHouses = new ArrayList<>();
    private HashMap<String, Integer> Bucket = new HashMap<>(); 
    public TrickOrTreater(String name,ArrayList<House> ListOfHouses )
    {
        this.name = name; 
        this.ListOfHouses = ListOfHouses; 
    }
    public String getName()
    {
        return name; 
    }
    public String getPath()
    {
        return path; 
    }
    public void addToPath(String newString)
    {
        path = path + newString; 
    }
    private void Walk(int speed)
    {
        int loop = 0; 
        for (loop = 0; loop < 10 ; loop++ )
        {
            path = path +"."; 
            try 
            {
                Thread.sleep(speed);
                
            } catch (InterruptedException e) 
            {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    public String PrintBucket()
    {
        String Candy; 
        String BucketContents; 
        int CandyCount = 0 ; 
        BucketContents = String.format("%-10s - ", name);
        
        for (HashMap. Entry  iterateBucket : Bucket.entrySet())
        {
            Candy = (String)iterateBucket.getKey(); 
            CandyCount = (int)iterateBucket.getValue(); 
            BucketContents += String.format("%15s %d ", Candy, CandyCount);
        }
        BucketContents += "\n";
        return BucketContents;
    }
    
    @Override
    
    public void run()
    {
        Random randNum = new Random();
        int speed = 0; 
        int count = 0; 
        String Candy = null;
        
        for (House it : ListOfHouses)
        {
            speed = randNum.nextInt(1501) +1; 
            Walk(speed);
            Candy = it.ringDoorbell(this);
            
            if(Bucket.containsKey(Candy))
            {
                count = Bucket.get(Candy); 
                Bucket.put(Candy,++count); 
            }
            else
            {
                Bucket.put(Candy, 1); 
            }
        }
        synchronized (this)
        {
            ImDone++; 
        }    
    }   
}
