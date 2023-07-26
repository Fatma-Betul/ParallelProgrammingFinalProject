package com.fatmabetul.registerdemo.parallelprogrammingfinalproject;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ParallelProgrammingFinal extends Thread{

    // Verilerin kaydolacağı dosyanın yolları belirtildi.
    public static final String PERSON_URL = "c:\\io\\turgutozaluniversitesi\\person.txt";
    public static final String SECRET_URL = "c:\\io\\turgutozaluniversitesi\\secret.txt";

    public String userDataInformation() {  // 1. Verilerin bilgileri alınıyor.
        Scanner kb = new Scanner(System.in);
        String username, password, email;
        System.out.println("Username giriniz: ");
        username = kb.nextLine();
        System.out.println("Password giriniz: ");
        password = kb.nextLine();
        System.out.println("email giriniz: ");
        email = kb.nextLine();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(username).append(password).append(email);
        return stringBuilder.toString();
    }
    public void fileIouserDataInformation() {    // 1. veriler person listesine ekleniyor.

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PERSON_URL, false))) {
            String userData = userDataInformation();
            bufferedWriter.write(userData);
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String userDataSecretInformation() {  // 2. veri alınıyor.

        Scanner kb = new Scanner(System.in);
        String secretInformation;
        System.out.println("secretInformation giriniz: ");
        secretInformation = kb.nextLine();
        return secretInformation;
    }

    public void fileIouserSecretInformation() {  // 2. veri secret adlı listeye ekleniyor.

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(SECRET_URL, false))) {
            String userData = userDataSecretInformation();
            bufferedWriter.write(userData);
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    
    public static void main(String[] args) throws InterruptedException { // Veriler belli sıraya göre işleniyor.
        // 1.Thread
        ParallelProgrammingFinal parallelService1=new ParallelProgrammingFinal();
        parallelService1.fileIouserDataInformation();// fileIouserDataInformation

        // 2.Thread
        ParallelProgrammingFinal parallelService2 = new ParallelProgrammingFinal();
        parallelService2.fileIouserSecretInformation();// fileIouserSecretInformation

        parallelService1.start();
        parallelService1.join();

        parallelService2.start();
        parallelService2.join();

        // Join ile 1. işlem bitmeden 2.işleme geçilemez.

    }

}