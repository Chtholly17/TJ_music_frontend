package com.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RuntimeFunction {
    public static void main(String[] args) {
        String s = "jlsb";
        try {
            ProcessBuilder pb = new ProcessBuilder("/usr/bin/python3", "test.py", s, "arg2");
            Process process = pb.start();

            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();

            int exitCode = process.waitFor();
            System.out.println("Python script exited with code: " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}