package com.example.RedSet.Lattice;

import java.io.*;

public class CppCompiler {
    public static String compileAndRunFromFile(String code, String input) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("temp.cpp"));
        writer.write(code);
        writer.close();
        writer = new BufferedWriter(new FileWriter("inp.txt"));
        writer.write(input);
        writer.close();
        String codeFilePath = "temp.cpp";
        String inputFilePath = "inp.txt";
        try {
            ProcessBuilder compileProcessBuilder = new ProcessBuilder("g++", codeFilePath, "-o", "temp");
            compileProcessBuilder.redirectErrorStream(true);
            Process compileProcess = compileProcessBuilder.start();
            compileProcess.waitFor();

            ProcessBuilder executionProcessBuilder = new ProcessBuilder("./temp");
            executionProcessBuilder.redirectInput(new File(inputFilePath));
            executionProcessBuilder.redirectErrorStream(false);
            Process executionProcess = executionProcessBuilder.start();

            BufferedReader outputReader = new BufferedReader(new InputStreamReader(executionProcess.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = outputReader.readLine()) != null) {
                output.append(line).append("\n");
            }

            executionProcess.destroy();
            new File("temp").delete();

            return output.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "An error occurred. ";
        }
    }
}
