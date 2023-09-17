package org.carbon.io.output;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;

public class FileBasedSimulationOutput implements SimulationOutput {
    @Override
    public void saveSimulationResult(String simulationResultSerialized) {
        try {
            String fileName = getFileName();
            writeToFile(fileName, simulationResultSerialized);
        } catch (IOException e) {
            System.out.println("Cannot write simulation output to file");
        }
    }

    private String getFileName() throws IOException {
        System.out.print("Which file should the output be written to ? (leave blank for default): ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return formatFileName(reader.readLine());
    }

    /**
     * Ensures the given fileName is a .txt file. Returns default "out.txt" if fileName is empty.
     */
    private String formatFileName(String fileName) {
        if (fileName.equals("")) {
            return "out.txt";
        }

        if (!fileName.endsWith(".txt")) {
            return fileName + ".txt";
        }

        return fileName;
    }

    private void writeToFile(String fileName, String simulationSerialized) throws IOException {
        File file = createFileIfNotExists(fileName);

        Files.writeString(file.toPath(), simulationSerialized);
        System.out.println("Result saved to " + file.toPath());
    }

    private File createFileIfNotExists(String fileName) throws IOException {
        File file = new File("./data/" + fileName);
        file.createNewFile(); // No need to check if the file already exists as this does nothing in that case

        return file;
    }

    @Override
    public void alertSimulationError(String errorMessage) {
        System.out.println("An error occurred during the simulation :");
        System.out.println(errorMessage);
    }
}
