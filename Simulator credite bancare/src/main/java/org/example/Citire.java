package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.management.MonitorInfo;
import java.nio.file.spi.FileSystemProvider;
import java.util.Scanner;

public class Citire {
    static final int MONTHS_IN_YEAR=12;
    static final int PERECENT=100;

    public static void citireDate() throws IOException {
        int amount;
        int period;
        double interestRate=0;
        Scanner scaner = new Scanner(System.in);

        csvWritter csvWritter;

        try {
            FileWriter writter= new FileWriter(fileProvider.getFile());
            csvWritter= new csvWritter(writter);
            csvWritter.wrietHeader();

        } catch (IOException e) {
            System.out.println("Some error occurred when initializing the csvWritter " + e.getMessage());
            return;
        }

        System.out.println("Please enter the amount: ");
        try {
             amount = Integer.parseInt(scaner.nextLine());
        }catch (NumberFormatException e){
            System.out.println("The amount is mandatory to be a numeric value");
            return;
        }

        System.out.println("Please enter the loan period in years: ");

        try {
             period= Integer.parseInt(scaner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("The period is manadatory to be a numeric value");
            return;
        }

        System.out.println("PLease the annual interest rate: ");
        try {
             interestRate=Double.parseDouble(scaner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("The interested rate is mandatory to be a numeric value");
        }


        double balance=amount;
        for(int month=1;month<=period*MONTHS_IN_YEAR;month++){

            double lastMonthBalance=balance;
            double monthlyMortgage=calculareCredit(amount,period,interestRate);
            double monthlyInterestRate=calculareDobandaLuna(lastMonthBalance,interestRate);
            double paidAmount=monthlyMortgage-monthlyInterestRate;


            balance=(lastMonthBalance-paidAmount)<0 ? 0 : (lastMonthBalance-paidAmount);
            
            try {
                csvWritter.writeRecord(month,monthlyMortgage,balance,monthlyInterestRate,paidAmount);
            } catch (IOException e) {
                System.out.println(" Error while writting the csv file:");
            }


        }
        try {
            csvWritter.closeFile();
        } catch (IOException e) {
            System.out.println(" Ceva gresit s-a intamplat in momentul inchiderii fisierului");
        }


    }

    public  static double calculareCredit(int amount, int period,double interestedRte){
        double momthlyRate=interestedRte/PERECENT/MONTHS_IN_YEAR;
        return (momthlyRate*amount)/(1-Math.pow(1+momthlyRate,(-period*MONTHS_IN_YEAR)));


    }

    public static double calculareDobandaLuna(double balance,double interesetdRate){
        double interestPerYear=balance*interesetdRate/PERECENT;
        return interestPerYear/MONTHS_IN_YEAR;

    }
}
