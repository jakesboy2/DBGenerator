package com.OklahomaInteractive;


import java.io.PrintWriter;
import java.util.Scanner;

/*
 * tool to generate queries for data.sql file in java spring
 * @author jacob.waldrip
 */
public class Main {

    public static void main(String[] args) {

        //objects initialization
        Scanner userInput = new Scanner(System.in);

        //variable initialization
        String fileName = "";
        String objectName = "";
        String numOfEntries = "";
        String[] options = {"Project", "User", "Inventory Items"};


        //keep asking for filename until a valid one is given
        do{

            //get the filename from the user
            System.out.print("Enter the file you want to save the data into: ");
            fileName = userInput.nextLine();

        }while(!validateFileName(fileName));



        try{

            //create a writer object with the file the user entered
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");

            //get the object we want to create from the user
            //display the array of options
            for(int i = 0; i < options.length; ++i){

                System.out.println((i+1) + ": " + options[i]);
            }
            System.out.println();


            //keep asking for object until a valid one is given
            do{

                //get the object they want to generate from the user
                System.out.print("Enter the object you want to create: ");
                objectName = userInput.nextLine();
                System.out.println();

            }while(!validateObjectSelection(objectName, options.length));


            //keep asking for number of entries until a valid one is given
            do{

                //get number of entries from the user
                System.out.print("Enter the number of entries you want to create: ");
                numOfEntries = userInput.nextLine();

            }while(!validateEntrySelection(numOfEntries));
            //convert the validated num of entries into an int
            int num = Integer.parseInt(numOfEntries);


            //call the proper method that the user wants to generate sql from
            switch(objectName){

                case "1":

                        generateProjects(num, writer);

                    break;


                case "2":

                        generateUsers(num, writer);

                    break;


                case "3":

                        generateInventoryItems(num, writer);

                    break;


                default:

                    //if this is reached, there was an issue with validating the users object name
                    System.out.print("There was an error with the object name you gave.");

                    break;
            }
        }catch(Exception ex){
            //if there was an error opening the file
            System.out.println("Trouble opening file... Initiating self destruct sequence in " +
                    "5.. 4.. 3.. 2.. 1..");
        }
    }


    //generate a given number of projects into a string
    public static void generateProjects(int num, PrintWriter writer){

        String entryActual = "";

        //create the string of the entry description we will be using every cycle
        String entryDesc = "insert into projects(projectId, description,shortName, startDate, enabled, suffix,financeAccountNumber,comments)";

        //create {num} entries and append them to the text file
        for(int i = 1; i <= num; ++i){

            //create a string of the entry actual we are going to be using this cycle
            entryActual = "values (" + i + ", 'Generated Entry','Generated', '2018-10-5', true, 187, 77896541, 'This entry was generated!');";

            writer.println(entryDesc);
            writer.println(entryActual);
        }

        //close file when we're done
        writer.close();
    }


    //generate a given number of projects into a string
    public static void generateUsers(int num, PrintWriter writer){

        String entryActual = "";
        String authoritiesEntry = "";

        //create the string of the entry description we will be using every cycle
        String entryDesc = "insert into users (username, password, firstName, lastName, enabled, attempts, role)";

        //create {num} entries and append them to the text file
        for(int i = 1; i <= num; ++i){

            //create a string of the entry actual we are going to be using this cycle
            entryActual = "values ('we" + i + "@uco.edu','$2a$10$RyY4bXtV3LKkDCutlUTYDOKd2AiJYZGp4Y7MPVdLzWzT1RX.JRZyG', 'Generated', 'Value', true, 0, 'ROLE_ADMIN');";

            authoritiesEntry = "insert into authorities (username, authority) values ('we" + i + "@uco.edu', 'ROLE_ADMIN');";

            writer.println(entryDesc);
            writer.println(entryActual);
            writer.println(authoritiesEntry);
        }

        //close file when we're done
        writer.close();
    }


    //generate a given number of projects into a string
    public static void generateInventoryItems(int num, PrintWriter writer){

        String entryActual = "";

        //create the string of the entry description we will be using every cycle
        String entryDesc = "insert into inventory_item(itemName, itemDescription, itemQuantity, itemManufacturer, itemModelNumber)";

        //create {num} entries and append them to the text file
        for(int i = 1; i <= num; ++i){

            //create a string of the entry actual we are going to be using this cycle
            entryActual = "values ('Generated Value #" + i + "', 'Important Stuff', 100, 'Oklahoma Interactive', '18D7F" + i+10 + "');";

            writer.println(entryDesc);
            writer.println(entryActual);
        }

        //close file when we're done
        writer.close();
    }


    //validate the file name
    public static boolean validateFileName(String fileName){

        //check if its at least 4 letters and the last 4 letters are .txt
        return (fileName.length() > 4 && (fileName.substring(fileName.length() - 4).equals(".txt")));
    }


    //validate the object select
    public static boolean validateObjectSelection(String objectSelection, int numOfOptions){

        //check that we have a number
        try
        {
            double d = Double.parseDouble(objectSelection);

            //check that we're in the field for options
            return (d <= numOfOptions && d > 0);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
    }

    //validate the number of entries you want
    public static boolean validateEntrySelection(String numOfEntries){

        try
        {
            double d = Double.parseDouble(numOfEntries);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }

        return true;
    }
}
