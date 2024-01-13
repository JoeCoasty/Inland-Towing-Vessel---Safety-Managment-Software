/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 
package itv_sms2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author josep
 */
public class MakeVP_Construction 
{
    public static void MakingVP_Construction()
    {
    //build a csv directory and file to store entered information
        //build directory
        Path path = Paths.get("src/StoredINFO/VesselParticulars");
        try 
        {
          Files.createDirectories(path);
          System.out.println("Directory created successfully!");
        }
        catch (Exception e) 
        {
          System.err.println("Error creating directory: " + e.getMessage());
        }
    
        //build file if not already there
        File VPConstruction = new File("src/StoredINFO/VesselParticulars/VP_Construction.csv");
        if (!VPConstruction.exists()) 
        {
            try 
            {
                VPConstruction.createNewFile();
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                
                FileWriter fileWriterLabel = new FileWriter(VPConstruction, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriterLabel);
                //writes to the CSV file
                bufferedWriter.write("Time Stamp" + "," 
                        + "Builder" + "," + "Construction Location" + "," 
                        + "Contract Date" + "," 
                        + "Keel Laid Date" + "," + "Delivery Date"
                        + "," + "Major Conversion/Modification Type" + "," 
                        + "Major Conversion/Modification Date" + "," 
                        + "Major Conversion/Modification Discription" + ","
                        
                        + "Doubler1 Size" + "," + "Doubler1 Location" + "," 
                        + "Doubler1 Installation Date" + "," 
                        + "Doubler1 Discription/Justification" + ","
                        
                        + "Doubler2 Size" + "," + "Doubler2 Location" + "," 
                        + "Doubler2 Installation Date" + "," 
                        + "Doubler2 Discription/Justification" + ","
                        
                        + "Bilge System Capacity" + "," + "# of Pumps" + "," 
                        + "Fixed System" + "," + "Inductor" + ","
                        
                        + "Ballast System Capacity" + "," + "Fixed Balast" + "," 
                        + "Balast Type" + "\n");
                
                bufferedWriter.close(); 
                System.out.println("File VP_Construction created");
                
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(VP_Details.class.getName()).log(Level.SEVERE,
                        null, ex);
            }
        }
        else
        {
            System.out.println("File VP_Construction already available");
        }
        
        
    }
}
