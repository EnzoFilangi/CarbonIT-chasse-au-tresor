package org.carbon.io.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileBasedSimulationInput implements SimulationInput {

    @Override
    public List<String> getInitializationCommands() {
        try {
            return Files.readAllLines(getFilePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Path getFilePath() {
        System.out.println("Please put your .txt file in the ./data folder");

        while (true) {
            System.out.print("Enter the file name: ");

            Path path = Path.of("data/", readInputFromConsole());
            if (path.toFile().exists()) {
                return path;
            } else {
                System.out.println("The file does not exist. Please enter a valid file name.");
            }
        }
    }

    private String readInputFromConsole() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return reader.readLine();
        } catch (IOException e) {
            System.err.println("Error reading input");
        }
        return "";
    }
}
