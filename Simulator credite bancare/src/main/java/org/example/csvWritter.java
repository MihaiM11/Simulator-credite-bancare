package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class csvWritter {
    private FileWriter fileWriter;

    public csvWritter (FileWriter fileWriter){
        this.fileWriter=fileWriter;
    }

    public void wrietHeader() throws IOException {
        fileWriter.append("Month");
        fileWriter.append(",");
        fileWriter.append("Mortgage");
        fileWriter.append(",");
        fileWriter.append("Balcance");
        fileWriter.append(",");
        fileWriter.append("Interest");
        fileWriter.append(",");
        fileWriter.append("Paid Amount");
        fileWriter.append("\n");
    }

    public void writeRecord (int mounth,double Mortgage,double balance,double interest,double paidAmount) throws IOException {
        fileWriter.append(String.valueOf(mounth));
        fileWriter.append(",");
        fileWriter.append(String.valueOf(Mortgage));
        fileWriter.append(",");
        fileWriter.append(String.valueOf(balance));
        fileWriter.append(",");
        fileWriter.append(String.valueOf(interest));
        fileWriter.append(",");
        fileWriter.append(String.valueOf(paidAmount));
        fileWriter.append("\n");
    }

    public void closeFile() throws IOException {
        fileWriter.close();
    }
}
