package os;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Semaphore;

//============================================================================
// Name        : newop.cpp
// Author      : Vhutali
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

public class Simulator 
{
    private static int numPeople;
    private static int numBranches;
    private static Person[] thePeople;
    private static Taxi theTaxi;
    public static void main(String[] args)    
    {
        try 
        {
        	//String file = "test.txt";
            FileInputStream fstream = new FileInputStream(args[0]);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            numPeople = new Integer(br.readLine());
            numBranches = new Integer(br.readLine());
            theTaxi = new Taxi();
            thePeople = new Person[numPeople];
           
            for(int i = 0; i < numPeople; i++)
            {
                thePeople[i] = new Person(i, args[0], theTaxi);
            }
            for(int i = 0; i < numPeople; i++)
            {
                thePeople[i].start();
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
