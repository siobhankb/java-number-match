/*
Name:           Siobhan Boylan
Date:           September 10, 2023
description:    a Java program that simulates a slot-machine-type number matching game.
                Program generates 3 random numbers; user earns points for 2-3 matching.
                Keeps track of user's points; starts fresh for new user
self grade:     95 points
justification:  Proper Naming:      I have followed all the naming conventions (5pts)
                Indentation:        The code is readable and indentation is provided for all parts (5pts)
                                        (I use 4-space indents to be extra clear) 
                Comments:           I have included comments throughout the code (5pts)
                Program Compiles:   My code compiles and contains all required parts (25pts)
                Program Runs*:      My code runs with 1 small error (first prompt is ignored)
                                        and generates the sample output (25pts)
                Requirements*:      Program follows all the provided requirements (25pts)
                                    I added 2 extra methods that help prevent gambling addiction, a problem my family has faced.
                Self grade:         I have given myself a score of 95 and explained why (5pts) 

Testimony:      I have written this program all by myself and have not copied any code from any resourses: Siobhan Boylan 

*/

import java.util.*;
public class MatchingGameBoylan
{
    public static void main(String[] args)
    {
        //create a Scanner object
        Scanner console = new Scanner(System.in);
        
        //create a Random object
        Random rando = new Random();
        
        //declare a string variable and assign "" to it. call this variable <answer>                 
        String answer = "-m-";
        
        //start game...  
        while (!answer.equalsIgnoreCase("q"))
        {
            //call the description method
            description();
            
            //prompt the user to enter their name, store their name in a variable 
            System.out.println("\nWhat is your name?  ");
            String playerName = console.nextLine();
            
            //display the hello message(refer to the output)
            System.out.println("\nHello " + playerName + "! Let's play!");
            
            //call the play method and pass the Random object that you created to it 
            play(rando, playerName);
            
            //display the end-player message
            System.out.print("Press 'Enter' to let another person play or type 'q/Q' to quit the program:  ");
            
            //store the user's input in the answer variable
            answer = console.nextLine();
        }
        
        //display a goodbye message
        System.out.print("\nThanks for playing Lucky Number Match! Goodbye!");
      
    }
    
    //-----------
    //-----------
    //  METHODS
    //-----------
    //-----------
    
    /* ///////////////////////////////////////////////////////////////////
         PLAY - main loop of game - gets #s, compares #s, assigns points
    */ ///////////////////////////////////////////////////////////////////
    
    public static void play(Random rand, String player)
    {
        //Scanner obj to get user input
        Scanner playerInput = new Scanner(System.in);
        
        //total variable to store points won
        int total = 0;
        
        //answer variable to store player's input
        String answer = "-";
        
        //int variables to store (and write-over) 3 random numbers
        int n1= 0, n2 = 0, n3 = 0;
        
        //declare int variable to keep track of how many times a player plays
        //use to avoid gambling addictions
        int totalPlays = 0;
        
        //prompt the user to get the max value(refer to the sample output and store it in a variable)
        System.out.print("Please choose the maximum value for your lucky numbers:  ");
        int max = playerInput.nextInt();
    
        //begin game with while loop
        while(!answer.equalsIgnoreCase("q"))
        {
           //increase play count
            totalPlays += 1;
            
            //call the method getRand(rand, max) three times and store the result in the variable n1, n2, n3       
            n1 = getRand(rand, max);
            n2 = getRand(rand, max);
            n3 = getRand(rand, max);
        
            //display the generated random numbers
            System.out.printf("Your lucky numbers: %d %d %d\n", n1, n2, n3);
            //determine points (if any)
            int match = match(n1,n2,n3);
            if (match == 2)
            {
                //add 200 to the total 
                total += 200;
                
                //tell player they won 200 points
                System.out.println(player + ", you got two matches. You won 200 points!"+
                                   "\nYou have " + total + " total points after playing " +
                                   totalPlays + " time(s).");
            }   
            else if(match == 3)
            {
                //add 500 to the total
                total += 500;
                
                //tell player they won 500 points
                System.out.println(player + ", you got THREE matches! Lucky you! You won 500 points!"+
                                   "\nYou have " + total + " total points after playing " +
                                   totalPlays + " time(s).");
            }    
            else
            {
                //display the proper message
                System.out.println("Bummer, " + player + ". No matches."+
                                   "\nYou have " + total + " total points after playing " +
                                   totalPlays + " time(s).\n"); 
            }
                  
            if (isAddicted(totalPlays) == true)
            {
                System.out.print("\n\tYou have played " + totalPlays + " times." 
                                 + addictMessage() +
                                 "\nWe suggest you quit for now and come back later :)\n");
            }
            
            System.out.println("\nPress enter to continue or press q/Q to quit  ");
            answer = playerInput.nextLine();
            
        }
        
        //end of play message
        System.out.println("\n" + player + ", after " + totalPlays + " rounds, your total winnings are: " + total);
        System.out.println("\n");
    }
    
    /* ///////////////////////////////////////////////////////////////
         getRand - gets a random number between 1 and user-input max
    */ ///////////////////////////////////////////////////////////////
    
    public static int getRand(Random rand, int max)
    {
        //generate a random number between 1 and max
        Random randy = new Random();
        int aRandomNum = randy.nextInt(max)+1;
        return aRandomNum;
    }
    
    /* ///////////////////////////////////////////////////////////////////
         MATCH - determines how many of the 3 int arguments are the same
    */ ///////////////////////////////////////////////////////////////////
    
    public static int match(int n1, int n2, int n3)
    {
        //test equivalence with conditional statements 
        if  (n1 == n2 && n1 == n3)
        {
            return 3;
        } else if (n1 == n2 || n1 == n3 || n2 == n3)
        {
            return 2;
        }  else 
        {
            return 1;
        }
    }
  
    /* //////////////////////////////////////////////////////////////////
         DESCRIPTION - displays beginning message to explain game rules
    */ //////////////////////////////////////////////////////////////////

    public static void description()
    {
        //display the description.
        for (int i = 0; i < 26; i++)
        {
            System.out.print("*");
        }
        System.out.print("\nWelcome to Lucky Number Match!" +
                         "\nRules:\n"+
                         "I will generate 3 random numbers." +
                         "\nAll numbers are positive. You choose the maximum value."+
                         "\nIf 2 numbers match, you'll win 200 points."+
                         "\nIf 3 numbers match, you'll win 500 points!\n");
        for (int i = 0; i < 26; i++)
        {
            System.out.print("*");
        }
        
    }
    
    /* /////////////////////////////////////////////////////////////////////////////
         isAddicted - determines if player has played 10 times since last reminder
    */ /////////////////////////////////////////////////////////////////////////////

    public static boolean isAddicted(int numberPlays)
    {
        // a player has played a multiple of 10 times, 
        // send "true" to prompt message
        if (numberPlays % 10 == 0) 
        {
            return true;
        }
        
        return false;
    }
    
    /* ////////////////////////////////////////////////////////////////////
         addictMessage - returns one of 5 messages to prompt user to quit
    */ ////////////////////////////////////////////////////////////////////

    public static String addictMessage()
    {
        // set up 5 messages in an array
        String[] message = {"\n\tLife is like a box of chocolates... but you'll never taste them if you keep playing this game.\n\n",
                            "\n\tYour mom says 'GO OUTSIDE AND PLAY!'\n\n",
                            "\n\tWe know this game is super fun, but there's more to life than this.\n\n",
                            "\n\tYou only live once. You'll never get this time back. Is this really how you want to spend it?\n\n",
                            "\n\tYou know the points are made up, right? And this whole thing is a mirage...\n\n"
                            };
        
        // get random number (0-4)
        Random rando = new Random();
        int sendMessageNum = rando.nextInt(5);
        
        // return one of the messages
        return message[sendMessageNum];
    }
}