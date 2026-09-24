/*
 * Problem Statement:
 * To write a Java program that collects student details (Name, Roll Number,
 * and Marks) and stores them in a text file using FileOutputStream.
 * Later, read the stored records using FileInputStream and display them
 * in a formatted manner.
 */

import java.io.*;
import java.util.*;

public class Assignment_10 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
              System.out.print("Enter Student Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Roll Number: ");
            int roll = sc.nextInt();

            System.out.print("Enter Marks: ");
            float marks = sc.nextFloat();

             String data = "Name: " + name
                    + "\nRoll Number: " + roll
                    + "\nMarks: " + marks + "\n";

             FileOutputStream fout =
                    new FileOutputStream("student.txt");

            byte[] b = data.getBytes();

            fout.write(b);
            fout.close();

            System.out.println(
                    "\nData written to file successfully."
            );

             FileInputStream fin =
                    new FileInputStream("student.txt");

            int i;

            System.out.println("\nStudent Record:");
            System.out.println("---------------------------");

            while ((i = fin.read()) != -1) {
                System.out.print((char) i);
            }

            fin.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());

        } finally {
            sc.close();
        }
    }
}
