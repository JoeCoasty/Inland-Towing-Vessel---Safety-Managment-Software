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
public class MakeVP_Inspection 
{
    public static void MakingVP_Inspection()
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
        File VPInspection = new File("src/StoredINFO/VesselParticulars/VP_Inspection.csv");
        if (!VPInspection.exists()) 
        {
            try 
            {
                VPInspection.createNewFile();
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                
                FileWriter fileWriterLabel = new FileWriter(VPInspection, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriterLabel);
                //writes to the CSV file
                bufferedWriter.write("Time Stamp" + "," + "COI Issued" + "," 
                        + "COI Expires" + "," + "Frest Water" + "," 
                        + "Salt Water" + "," + "DD Prior"
                        + "," + "DD Last" + "," + "DD Next" + "," + "ISE Prior" 
                        + "," + "ISE Last" + "," + "ISE Next" + "," + "Internal" 
                        + "," + "External" + "," + "Survey Prior" + "," 
                        + "Survey Last" + "," + "Survey Next" + "," 
                        + "Mid Peroid Prior" + "," + "Mid Peroid Last" + "," 
                        + "Mid Peroid Next" + "," + "Management Audit Prior" 
                        + "," + "Management Audit Last" + "," + "Management Next" 
                        + "," + "Vessel Audit Prior" + "," + "Vessel Audit Last" 
                        + "," + "Vessel Audit Next" + "\n");
                
                bufferedWriter.close(); 
                System.out.println("File VP_Inspection created");
                
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(VP_Details.class.getName()).log(Level.SEVERE,
                        null, ex);
            }
        }
        else
        {
            System.out.println("File VP_Inspection already available");
        }
        
        
    }
}
