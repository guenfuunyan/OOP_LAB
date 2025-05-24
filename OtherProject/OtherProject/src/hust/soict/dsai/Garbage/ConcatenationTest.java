package hust.soict.dsai.Garbage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ConcatenationTest {
    public static void main(String[] args) {
        String filename = "large_file.txt"; // Thay bằng file thực tế của bạn
        
        try {
            byte[] inputBytes = Files.readAllBytes(Paths.get(filename));
            
            // Test với toán tử +
            long startTime = System.currentTimeMillis();
            String outputString = "";
            for (byte b : inputBytes) {
                outputString += (char)b;
            }
            long endTime = System.currentTimeMillis();
            System.out.println("Using + operator: " + (endTime - startTime) + "ms");
            
            // Test với StringBuffer
            startTime = System.currentTimeMillis();
            StringBuffer outputStringBuffer = new StringBuffer();
            for (byte b : inputBytes) {
                outputStringBuffer.append((char)b);
            }
            endTime = System.currentTimeMillis();
            System.out.println("Using StringBuffer: " + (endTime - startTime) + "ms");
            
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}