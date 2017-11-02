package os;
//============================================================================
//Name        : newop.cpp
//Author      : Vhutali
//Version     :
//Copyright   : Your copyright notice
//Description : Hello World in C++, Ansi-style
//============================================================================
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

public class Time {
 
 private int hours;
 private int minutes;
 
 Time()
 {
     hours = 9;
     minutes = 0;
 }
 /**
  * @return the hours
  */
 public int getHours() {
     return hours;
 }

 /**
  * @param hours the hours to set
  */
 public void setHours(int hours) {
     this.hours = hours;
 }

 /**
  * @return the minutes
  */
 public int getMinutes() {
     return minutes;
 }

 /**
  * @param minutes the minutes to set
  */
 public void setMinutes(int minutes) {
     if((this.getMinutes()+minutes) >= 60){
         this.hours++;
         this.minutes += minutes % 60;
     }
     else
     {
         this.minutes += minutes;
     }
 }
 
 public String getTime()
 {
     if(this.getHours() < 10)
     {
         if(this.getMinutes()< 10)
             return "0"+ this.getHours() +  ":"+"0"+this.getMinutes();
         else
             return "0"+ this.getHours() +  ":"+this.getMinutes();  
     }
     else
     {
         if(this.getMinutes()< 10)
             return this.getHours() +  ":"+"0"+this.getMinutes();
         else
             return this.getHours() +  ":"+this.getMinutes();
     }
 }
}

