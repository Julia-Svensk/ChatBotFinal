package Main;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

//Creates the server log which makes and writes to the log file
public class ServerLog {
    final String fileName = "ServerLog.txt";
    // Formatter for date and time strings
    final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss");
    File logFile;

    //Constructor that creates or finds the log file
    public ServerLog() {
        FindOrMakeFile();
    }

    //Method that searches for the old file or makes a new one if it doesn't exist
    private void FindOrMakeFile() {
        try {
            logFile = new File(fileName);
            if (logFile.createNewFile()) {
                System.out.println("New log file has been created");
            } else {
                System.out.println("The log file has been found");
            }
        } catch (IOException e) {
            System.out.println("Something went wrong when making the file. Make sure the name and path of the file is correct.");
        }
    }

    //Method that writes the given string to the log file along with the time it was logged
    public void WriteToFile(String message) {
        try {
            //Gets the date and time and formats it according to the dateFormatter
            Date date = new Date();
            String dateString = dateFormatter.format(date);

            //Opens the log file in append mode
            FileWriter fileWriter = new FileWriter(fileName, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter writer = new PrintWriter(bufferedWriter);

            //Writes the log message and closes the writer
            writer.println(dateString + ": " + message + "\n");
            writer.close();

            System.out.println("Successfully saved to file");
        } catch (Exception e) {
            System.out.println("Error when saving to file: " + e);
        }
    }
}
