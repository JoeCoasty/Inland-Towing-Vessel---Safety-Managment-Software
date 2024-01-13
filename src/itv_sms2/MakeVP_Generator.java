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
public class MakeVP_Generator
{
    public static void MakingVP_Generator()
    {
        //build a csv directory and file to store entered information
        //build directory
        Path path = Paths.get("src/StoredINFO/VesselParticulars");
        try 
        {
          Files.createDirectories(path);
          System.out.println("Directory found successfully!");
        }
        catch (Exception e) 
        {
          System.err.println("Error creating directory: " + e.getMessage());
        }
    
        //build file if not already there
        File VPVesselSpecifics = new File("src/StoredINFO/VesselParticulars/VP_Generator.csv");
        if (!VPVesselSpecifics.exists()) 
        {
            try 
            {
                VPVesselSpecifics.createNewFile();
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                
                FileWriter fileWriterLabel = new FileWriter(VPVesselSpecifics, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriterLabel);
                //writes to the CSV file
                bufferedWriter.write("Time Stamp" + "," + "Port Generator Make" + "," 
                        + "Port Generator KW" + "," 
                        + "Port Generator Install/Overhual Date" + "," 
                        
                        + "STBD Generator Make" + "," 
                        + "STBD Generator KW" + "," 
                        + "STBD Generator Install/Overhual Date" + ","
                        
                        + "Misc Generator Make" + "," 
                        + "Misc Generator KW" + "," 
                        + "Misc Generator Install/Overhual Date" + ","
                        
                        +  "\n");
                
                bufferedWriter.close(); 
                System.out.println("File VP_Generator created");
                
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(VP_Details.class.getName()).log(Level.SEVERE,
                        null, ex);
            }
        }
        else
        {
            System.out.println("File VP_Generator already available");
        }
        
        
    }
}
