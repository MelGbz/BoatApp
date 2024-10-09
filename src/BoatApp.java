
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Melanie
 */
public class BoatApp {
    
    static Map<String, Boat> boatMap = new HashMap<>();
    
    public static List<String> GetCommandOptions()
    {
        List<String> commandList = new ArrayList<>();
        
        commandList.add("power on");
        commandList.add("power off");
        commandList.add("speed up");
        commandList.add("slow down");
        commandList.add("turn left");
        commandList.add("turn right");
        
        return commandList;
    }
    
    /**
     * This method print each line of the BoatNames txt file.
     */
    public static void DisplayBoatsNames()
    {        
        String filePath = "BoatNames.txt";

        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            System.out.println("\nHere is the existing boat names list :");
            System.out.println("---------------------------------------");
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line); // Print each boat name
            }

            bufferedReader.close(); // Close the reader
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
    
    public static void GetBoatsNames()
    {        
        String filePath = "BoatNames.txt";

        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                boatMap.put(line, new Boat(line)); // Create boat for each boat name
            }

            bufferedReader.close(); // Close the reader
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
    
    /**
     * This method add the given boat name to the BoatNames txt file.
     * @param name : name of the boat to add
     */
    public static void AddBoatsNames(String name)
    {
        String filePath = "BoatNames.txt";
        String newBoatName = name;

        try {
            FileWriter fileWriter = new FileWriter(filePath, true); // true for appending to the file
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.newLine(); // Add a new line before writing the boat name
            bufferedWriter.write(newBoatName);
            
            boatMap.put(name, new Boat(name)); // Add a new boat to boatMap

            bufferedWriter.close(); // Close the writer
            System.out.println("\nNew boat name #" + name + "# added to the file.");
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }
    
    public static void UserCommand(String boatName, String command)
    {
        // ########## //
        // Check Boat name
        if (boatMap.containsKey(boatName))
        {
            // ########## //
            // Check command
            List<String> commandList = GetCommandOptions(); //get all the command in a list
            
            if (commandList.contains(command.toLowerCase()))
            {
                switch (command.toLowerCase())
                {
                    case "power on" -> {
                        boatMap.get(boatName).powerOn();
                    }
                    case "power off" -> {
                        boatMap.get(boatName).powerOff();
                    }
                    case "speed up" -> {
                        boatMap.get(boatName).speedUp();
                    }
                    case "slow down" -> {
                        boatMap.get(boatName).slowDown();
                    }
                    case "turn left" -> {
                        boatMap.get(boatName).turnLeft();
                    }
                    case "turn right" -> {
                        boatMap.get(boatName).turnRight();
                    }
                    default -> {
                        System.out.println("\nInvalid input. Please enter a valid command.");
                    }
                }
            }
            else
            {
                System.out.println("The written command #" + command + "# does not exits.");
                System.out.println("Please write a command among the following : " + commandList);
            }
        }
        else
        {
            System.out.println("\nThe written boat name #" + boatName + "# does not exits.");
            System.out.println("\nPlease update the boat list (2 in menu)");
            System.out.println("   OR write an existing boat name (1 in menu to see existing name)");
            System.out.println("   OR add the boat name you want (3 in menu).");
        }
    }
    
    public static void Menu()
    {
        System.out.println("#### WELCOME to BoatApp menu! #####");
        
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        
        while(!userInput.equalsIgnoreCase("Q"))
        {
            System.out.println("\n###################################");
            System.out.println("# What do you want to do ?        #");
            System.out.println("# 1 | Consult existing boat names #");
            System.out.println("# 2 | Update boat list            #");
            System.out.println("# 3 | Add a boat name             #");
            System.out.println("# 4 | Consult possible command    #");
            System.out.println("# 5 | Provide a command           #");
            System.out.println("# Q | Quit app                    #");
            System.out.println("###################################\n");

            try {
                System.out.println("Enter the selected menu :");
                userInput = scanner.nextLine();  // Read user input
                
                switch (userInput.toUpperCase())
                {
                    case "1" -> {
                        DisplayBoatsNames();
                        GetBoatsNames();
                    }
                    case "2" -> {
                        GetBoatsNames();
                        System.out.println("\nBoats list has been updated...");
                    }
                    case "3" -> {
                        System.out.println("\nWhat boat name do you want to add ? ");
                        System.out.println("------------------------------------");
                        System.out.print("Enter the new boat name : ");
                        String boatname = scanner.nextLine();
                        AddBoatsNames(boatname);
                        GetBoatsNames(); // Re-create & update the boatMap
                    }
                    case "4" -> {
                        System.out.println("\nHere is the list of possible command : ");
                        System.out.println("---------------------------------------");
                        
                        List<String> commandList = GetCommandOptions();
                        for (String c : commandList)
                        {
                            System.out.println(c);
                        }
                    }
                    case "5" -> {
                        System.out.println("\nProvide a command in the following format <Boat name>,<command> :");
                        System.out.println("-----------------------------------------------------------------");
                        String userCommand = scanner.nextLine();
                        
                        // SPLIT the userInput using the comma delimiter
                        String[] parts = userCommand.split(",");
                        
                        String boatName = "";
                        String command = "";

                        if (parts.length == 2) {
                            boatName = parts[0].trim(); // Extract the name without right or left space
                            command = parts[1].trim(); // Extract the command without right or left space

                            System.out.println("Boat Name: " + boatName);
                            System.out.println("Command: " + command);
                        } else {
                            System.out.println("Invalid input format");
                        }
                        
                        UserCommand(boatName, command);
                    }
                    case "Q" -> {
                        System.out.println("\n###################################");
                        System.out.println("###### ...Exiting BoatApp... ######");
                        System.out.println("############ GOODBYE! #############");
                        System.out.println("###################################\n");
                    }
                    default -> {
                        System.out.println("Invalid input. Please enter a valid menu number or 'Q' to quit the app");
                    }
                }
            } catch (Exception e) {
                System.out.println("Error : " + e.getMessage());
                System.out.println("Please enter a valid number.");
            }
        }
        scanner.close();
    }
    
    
    public static void main(String[] args)
    {
//        System.out.println("### TEST AddBoatsNames ###");
//        AddBoatsNames("Test BoatName");
//        
//        System.out.println("\n### TEST GetBoatsNames ###");
//        GetBoatsNames();
//        
//        System.out.println("\n### TEST createBoatNamesList ###");        
//        // Create a list of boat names from the file
//        List<String> boatNames = createBoatNamesList();
//        System.out.println(boatNames);
//        
//        System.out.println("\n### TEST Boat's Methods ###");
//        Boat boat1 = new Boat("Boat1");
//        boat1.powerOn();
//        boat1.speedUp();
//        boat1.turnLeft();
//        boat1.powerOff();
//        boat1.turnRight();
//        boat1.powerOn();
//        boat1.slowDown();
        
//        System.out.println("\n### TEST User's request ###");
        Menu();
    }
}
