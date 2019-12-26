package com.joewitten.FileNameEditor;


import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        //Acquiring input
        System.out.println("Enter the path you want to edit files in");
        String input = scanner.nextLine();



        //Putting input in correct form, eg D:\JAVA_FOLDER\workspace\Home to D:\\JAVA_FOLDER\\workspace\\Home
        if (input.substring(input.length()-1).equals("\\")) {
            input = input.substring(0,input.length()-1);
        }
        input = input.replaceAll("\\\\", "\\\\\\\\" );


        //creates an array with each path
        File directory = new File(input);
        File[] folder = directory.listFiles();

        //Checks if a path has been entered
        if (folder == null) {
            System.out.println("The path you have entered is in the wrong format, please enter it in the format \"Drive:\\.....\\Directory\"");
            System.exit(0);
        }

        //Shows user the directory info
        System.out.println("----------------------------------------------------------------------------------------------------------");
        System.out.println("Here is a list of all of the files in this directory" + "\n" + Arrays.toString(folder));
        System.out.println("----------------------------------------------------------------------------------------------------------");

        //Takes user input of word remover
        System.out.println("What word would you like to get rid of?");
        String wordRemove = scanner.nextLine();

        //Checks if each path contains the word, then removes it
        for (int i = 0; i <folder.length; i++) {
            String temp = folder[i].toString();
            int index = temp.indexOf(wordRemove);
            if (index > 0) {
                File song = new File(temp);
                String end = temp.substring(index + wordRemove.length()).trim();
                String start =temp.substring(0,index).trim();

                //renames files
                boolean success = song.renameTo(new File(start + end));
                //Tells user if successful
                if (success) {
                    System.out.println("Rename successful!");
                }
                else {
                    System.out.println("Not successful!");
                }
            }
        }

    }
}
