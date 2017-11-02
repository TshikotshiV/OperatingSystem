package os;

//============================================================================
//Name        : newop.cpp
//Author      : Vhutali
//Version     :
//Copyright   : Your copyright notice
//Description : Hello World in C++, Ansi-style
//============================================================================ 
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Taxi extends Thread
{
 private int currentBranch;
 private int currentPassengers;
 private boolean outBound;
 private ArrayList<Integer> destBranches = new ArrayList<>();
 int branchNumber;
 /**
  * @param destBranch the currentBranch to set
  */
 public Taxi()
 {
     currentBranch = 0;
     outBound = false;
 }
 public void goToBranch(int destBranch) 
 {
     destBranches.add(destBranch);
     
     this.driveOff();
 }
 public void driveOff()
 {
 	 
     while(!destBranches.isEmpty())
     {
     	//getBranchNumber();
     	//System.out.println(branchNumber);
         if(outBound)
         {
             while(currentPassengers > 0 && currentBranch <  branchNumber) //If it is less than num branches actually
             	
             {
                 if(destBranches.contains(currentBranch))
                 {
                     destBranches.remove((Integer)currentBranch);
                     System.out.println("branch "+this.currentBranch +": taxi arrive");
                 }
                 if(!destBranches.isEmpty())
                 {
                     if(Collections.max(destBranches) <= currentBranch)
                     {
                         outBound = false;
                         currentBranch--;
                         //break;
                     } 
                 }
                 else
                 {
                     break;
                 }
                 currentBranch++;
             }
             if(currentPassengers > 0 && currentBranch >= branchNumber)
             {
                 outBound = false;
             }
         }
         else
         {
             while(currentPassengers > 0 && currentBranch > 0)
             {
                 if(destBranches.contains(currentBranch))
                 {
                     destBranches.remove((Integer)currentBranch);
                     System.out.println("branch "+this.currentBranch +": taxi arrive");
                 }
                 
                 currentBranch--;
             }
             if(currentPassengers > 0 && currentBranch <= 0)
                 outBound = true;
         }
     }
 }
 public int getCurrentBranch() 
 {
     return currentBranch;       
 }
 
 void  personPickup()
 {
         System.out.println("branch "+currentBranch +": taxi depart");   
         currentPassengers++;
 }
 
}
