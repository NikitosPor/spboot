package ru.otus.spboot.service;

import org.springframework.stereotype.Component;
import java.io.PrintStream;
import java.util.Scanner;

@Component
public class IOServiceStreams implements IOService {
    private final PrintStream output;
    private final Scanner input;

    public IOServiceStreams() {
        output = System.out;
        input = new Scanner(System.in);
    }

    @Override
    public void outputString(String s){
        output.println(s);
    }

    @Override
    public String readString(){
        return input.nextLine();
    }
}
