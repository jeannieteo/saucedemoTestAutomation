package saucedemoTestAutomation;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

       int mover;
       int number=0;
       String s = "MCMXCIV";
       for (mover =0; mover < s.length(); mover++ )   {
           if(s.charAt(mover) == 'M')   {
               number +=1000;
               System.out.println(number);
               System.out.println("\n mover --" + mover);
           }
           else if(s.charAt(mover) == 'D')    {
                        number +=500;
           }
           else if(s.charAt(mover) == 'C')     { //char C
                        if( mover+1 != s.length() )   { //not out of range
                            if((s.charAt(mover+1) == 'D') || (s.charAt(mover+1) == 'M')) { //check ahead by 1
                                number -=100;
                                System.out.println(number);
                                System.out.println("\n mover --" + mover);
                            }
                            else {
                                number += 100;
                            }
                        }else {
                            number += 100;
                        }
           }
           else if(s.charAt(mover) == 'L'){
                        number +=50;
           }
           else if(s.charAt(mover) == 'X'){
                        if(mover+1 != s.length())   {
                            if((s.charAt(mover+1) == 'L') || (s.charAt(mover+1) == 'C')) {
                                number -=10;
                                System.out.println(number);
                                System.out.println("\n mover --" + mover);

                            }else{number +=10;}
                        }
                        else{number +=10;}
           }
           else if(s.charAt(mover) == 'V'){
                        number +=5;
           }
           else if(s.charAt(mover) == 'I'){

               if(mover+1 != s.length())   {//check index not last one
                            if((s.charAt(mover+1) == 'V') || (s.charAt(mover+1) == 'X')) {
                                number -=1;
                                System.out.println(number);
                                System.out.println("\n mover --" + mover);
                            }else{     number +=1; }
               }
                   else{     number +=1; }//last one u add
           }

                }//loop
                System.out.println(number);
            }
        }
