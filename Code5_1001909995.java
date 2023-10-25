/*
Name: Shah Arifur Rahman Rhyme
UTA ID: 1001909995
Coding Assignment 5
*/
package code5_1001909995;
import java.util.Scanner;
import java.io.File; 
import java.util.ArrayList;
import java.io.FileNotFoundException; 
import java.util.HashMap; 
import java.util.Random;
import java.util.concurrent.Executors; 
import java.util.concurrent. ExecutorService;

public class Code5_1001909995 
{   
    public static void CreateCandyList(String FILENAME, HashMap<String, Integer> mapCandyList) throws FileNotFoundException
    {
        File FH = new File (FILENAME);
        Scanner inFileRead = null;   
        String[] storeLine; 
        try
        {
            inFileRead = new Scanner(FH);
        }
        catch(Exception e)
        {
            System.out.printf("%s file name does not exist..exiting \n", FILENAME); 
            System.exit(0);
        }
        
         while(inFileRead.hasNextLine())
        {
            String candyName = inFileRead.nextLine(); 
            storeLine =  candyName.split("[|]");
            candyName = storeLine[0]; 
            int candyNumber = Integer.parseInt(storeLine[1]); 
            mapCandyList.put(candyName, candyNumber);  
        }  
         inFileRead.close();
    }
    
    public static String CreateHouses(String houseFILENAME , ArrayList<House> houseList , HashMap<String, Integer> mapCandyList) throws FileNotFoundException
    {
        Random rand = new Random(); 
     
        String HouseHeading = "         ";
        File FH = new File (houseFILENAME);
        Scanner inFile = null;   
        
        try
        {
            inFile = new Scanner(FH);
        }
        catch(Exception e)
        {
            System.out.printf("%s file name does not exist..exiting \n", houseFILENAME); 
            System.exit(0);
        }
        String readLine; 
        while(inFile.hasNextLine())
        {
            readLine = inFile.nextLine(); 
            HouseHeading = HouseHeading + readLine; 
            for (int i = 0; i < 11 - readLine.length(); i++)
            {
                HouseHeading += " "; 
            }
            int randNum = rand.nextInt(3); 
            if (randNum == 0)
            {
               houseList. add( new CandyHouse(readLine,mapCandyList));  
            }
            else
            {
                houseList. add (new ToothbrushHouse(readLine,mapCandyList)); 
            }
        }
        inFile.close(); 
        HouseHeading  = HouseHeading + "\n" + "\n" ;
        return  HouseHeading;    
    }
    
    public static  void CreateTOTs (String totFILENAME, ArrayList <TrickOrTreater> totList, ArrayList<House> houseList  ) throws  FileNotFoundException
    {
         
        File FH = new File (totFILENAME);
        Scanner inFileRead = null;   
        
        try
        {
            inFileRead = new Scanner(FH);
        }
        catch(Exception e)
        {
            System.out.printf("%s file name does not exist..exiting \n",  totFILENAME); 
            System.exit(0);
        }
        while(inFileRead.hasNextLine())
        {
            String readtotLine = inFileRead.nextLine(); 
            totList.add(new TrickOrTreater(readtotLine, houseList)); 
             
        }
        inFileRead.close();   
    }
    public static void main(String[] args) throws FileNotFoundException
    {
         //Scanner frog = new Scanner(System.in); 
        HashMap<String, Integer> mapCandyList = new HashMap<>();   //candy list
        ArrayList <House> houseList = new ArrayList<>();           //house list
        ArrayList <TrickOrTreater> totList = new ArrayList<>();    //TrickOrTreater list

        String candyFILENAME = args[0].substring(args[0].indexOf('=')+1); // getting the file name from command line
        String housesFILENAME = args[1].substring(args[1].indexOf('=')+1); 
        String totFILENAME = args[2].substring(args[2].indexOf('=')+1); 
       
        CreateCandyList(candyFILENAME, mapCandyList);
        String callHouseFuc  = CreateHouses(housesFILENAME, houseList, mapCandyList); 
        CreateTOTs(totFILENAME, totList, houseList);
        
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (TrickOrTreater it : totList)
        {
           executorService.execute(it); 
        }
        TextAreaFrame TAF = new TextAreaFrame();
        TAF.setVisible(true);
        String ScreenOutput = null; 
        while(TrickOrTreater.ImDone !=  totList.size())
        {
            ScreenOutput = String.format("%s",  callHouseFuc);
            for (TrickOrTreater it : totList)
            {
                 ScreenOutput += String.format("%s%s\n",it.getPath(), it.getName()); 
            }
            TAF.textArea.setText(ScreenOutput); 
        }
        executorService.shutdown();
        String BucketContents = "Candy!!" +"\n"+"\n";
        for (TrickOrTreater it : totList)
        {
            BucketContents = BucketContents + it.PrintBucket(); 
        }
        TAF. textArea.setText(BucketContents); 
    }
}
