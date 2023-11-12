import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File file = new File("database.txt"); //създавам нов ОБЕКТ файл (не самия файл) и му задавам име
        if(!file.exists()){ //проверява ако файлът НЕ съществува
            try {
                file.createNewFile(); //създава самия файл, ако няма такъв
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        ArrayList<String> titles = new ArrayList<>(); //създавам обект от тип array, който ще пълня със стрингове
        Scanner reader = null; //създавам си четец
        try {
            reader = new Scanner(file);
            while (reader.hasNextLine()) { //докато има нови редове ги чете
                String data = reader.nextLine();
                titles.add(data); //добавя в арея
            }
        } catch (FileNotFoundException е) {
            //todo
        }
        System.out.println("You have " + titles.size() + " movies in your list.\nPlease choose an option:\n1. Add a movie\n2. Remove a movie\n3. See full list\n4. Exit");
        reader.close(); //затварям четеца
        Scanner input = new Scanner(System.in); //създавам обект от тип сканер
        int option = input.nextInt();
        if (option == 1) {
            System.out.println("Please type the movie you want to add: ");
            String title = getInput(); //gets input via method
            titles.add(title); // добавям стринга от сканера в арея
            BufferedWriter fr = null;
            try {
                fr = new BufferedWriter(new FileWriter(file));
            } catch (IOException e) {
                System.out.println("Open file failed");
            }
            for (String line :titles) {
                try {
                    fr.append(line);
                    fr.append("\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                fr.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thank you! I've added " + title + " to your list.");
        } else if (option == 2) {
            System.out.println("Please enter the movie you'd like to remove: ");
            String removedMovie = getInput();
            int remover = titles.indexOf(removedMovie);
            titles.remove(remover); //маха филма от арея, но не и от файла
            System.out.println("Thank you! I've removed " + removedMovie + " from your list.");
            FileWriter overwrite = null; //презаписвам файла с новия арей без филма, който сме махнали
            try {
                overwrite = new FileWriter(file);
            } catch (IOException e) {
                System.out.println("Open file failed");
            }
            for (String line :titles) {
                try {
                    overwrite.append(line);
                    overwrite.append("\n");
                } catch (IOException var14) {
                    throw new RuntimeException(var14);
                }
            }
            try {
                overwrite.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (option == 3) {
            System.out.println("Here's a list with all your movies: " + titles);
        } else if (option == 4) {
            System.out.println("Goodbye!");
        } else {
            System.out.println("Invalid option chosen.");
        }
    }


    public static String getInput() {
        Scanner userInput = new Scanner(System.in);
        return userInput.nextLine();
    }

}