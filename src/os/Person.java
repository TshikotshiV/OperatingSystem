package os;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
public class Person extends Thread
{
    /*
    *   1. Call a method that calls a method from taxi to be picked up
    *   2. Wait on a semaphore in the Person method until the Taxi pickup method executes i.e until the person is picked up
    *   3. After waiting i.e when picked up, call another method in Taxi to indicate branch to travel to
    *   4. Wait on a semaphore in the Person method until the Taxi travel method executes i.e until the new destination is reached
    */
//============================================================================
// Name        : newop.cpp
// Author      : Vhutali
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================
    private Taxi theTaxi;
    private int personNumber;
    private int currentBranch;
    private String theFile;
    Semaphore semaphore = new Semaphore(1);
    int numberB;
    Time theTime = new Time();
    Person(int personNum, String fileName, Taxi taxi)
    {
        currentBranch = 0;
        personNumber = personNum;
        theFile = fileName;
        theTaxi = taxi;
    }
    
    void requestTaxi() throws InterruptedException
    {
	boolean permit = false;
	    try {
		permit = semaphore.tryAcquire(1, TimeUnit.SECONDS);
		if (permit) {
		    System.out.println("Semaphore acquired");
		    System.out.println(theTime.getTime()+ " branch "+currentBranch +": person " +personNumber+" hail");
                    sleep(17*1);
                    System.out.print(theTime.getTime()+ " ");
                    theTaxi.personPickup();

		} else {
		    System.out.println("Could not acquire semaphore");
		}
	    } catch (InterruptedException e) {
		throw new IllegalStateException(e);
	    } finally {
		if (permit) {
		    System.out.println("Semaphore released");
		    semaphore.release();
		}
	    }
	
    }
    void goToBranch(int destBranch) throws InterruptedException
    {
        
	boolean permit = false;
	    try {
		permit = semaphore.tryAcquire(1, TimeUnit.SECONDS);
		if (permit) {
		    System.out.println("Semaphore acquired");
		    System.out.println(theTime.getTime()+ " branch "+currentBranch +": person " +personNumber+" request "+destBranch);
        	    sleep(17*2);
                    theTaxi.goToBranch(destBranch);
		    
		} else {
		   System.out.println("Could not acquire semaphore");
		}
	    } catch (InterruptedException e) {
		throw new IllegalStateException(e);
	    } finally {
		if (permit) {
		    System.out.println("Semaphore released");
		    semaphore.release();
		}
	    }
    }
    public void run()
    {      
        try 
        {
            String strLine;
            int pNumber;
            FileInputStream fstream = new FileInputStream(theFile);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            br.readLine();
            numberB =  Integer.parseInt(br.readLine());
            theTaxi.branchNumber = numberB;
            
            while ((strLine = br.readLine()) != null)
            {
                pNumber = new Integer(strLine.substring(0, 1));

                if(pNumber == personNumber)
                {
                    strLine = strLine.substring(2, strLine.length());
                
                    String[] temp = strLine.split("\\), ");
                
                    for(int i = 0; i < temp.length; i++)
                    {
                        temp[i] = temp[i].replace(")", "");
                        this.requestTaxi();         
                        this.goToBranch(new Integer(temp[i].substring(1, temp[i].indexOf(","))));

                    }
                }
                else
                {}  
            } 
            in.close();
            br.close();
        } 
        catch (Exception e)
        {
            System.err.println("Error: " + e.toString());
        }
    }
}
