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
public class MakeVP_Details 
{
    public static void MakingVP_Deatils()
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
        File VPDetails = new File("src/StoredINFO/VesselParticulars/VP_Details.csv");
        if (!VPDetails.exists()) 
        {
            try 
            {
                VPDetails.createNewFile();
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                
                FileWriter fileWriterLabel = new FileWriter(VPDetails, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriterLabel);
                //writes to the CSV file
                bufferedWriter.write("Time Stamp" + "," 
                        + "TSMS Selected" + "," + "USCG Selected" 
                        + "," + "User Fees Paid" + "," + "Vessel Name"
                        + "," + "Official Number" + "," + "Call Sign" 
                        + "," + "Home Port" + "," + "Place Built" 
                        + "," + "MMSI Number" + "," + "Vessel Phone Number" + "," + "Vessel Email" 
                        + "," + "Owner" + "," + "Owner Email" + "," + "Operator" 
                        + "," + "Operator Address" + "," + "TPO" + "," + "TPO POC" 
                        + "," + "TPO Address" + "," + "TPO POC Phone" 
                        + "," + "Charterer" + "," + "Special Considerations" 
                        + "," + "Alternate Arrangements" + "\n");
                
                bufferedWriter.close(); 
                System.out.println("File VP_Details created");
                
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(VP_Details.class.getName()).log(Level.SEVERE,
                        null, ex);
            }
        }
        else
        {
            System.out.println("File VP_Details already available");
        }
        
        //close csv file "VesselParticulars"
    }
}
