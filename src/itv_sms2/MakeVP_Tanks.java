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
public class MakeVP_Tanks 
{
    public static void MakingVP_Tanks()
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
        File VPVesselSpecifics = new File("src/StoredINFO/VesselParticulars/VP_Tanks.csv");
        if (!VPVesselSpecifics.exists()) 
        {
            try 
            {
                VPVesselSpecifics.createNewFile();
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                
                FileWriter fileWriterLabel = new FileWriter(VPVesselSpecifics, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriterLabel);
                //writes to the CSV file
                bufferedWriter.write("Time Stamp" + "," + "Number of Fuel Tanks" + "," 
                        + "Fuel Capacity in Gallons" + "," 
                        + "Fuel Tank Location(s)" + "," 
                        + "Last Fuel Tank Inspection" + "," 
                        + "Number of Lube Oil Tanks" + "," 
                        + "Lube Oil Capacity in Gallons" + "," 
                        + "Lube Oil Tank Location(s)" + "," 
                        + "Last Lube Oil Tank Inspection" + ","
                        + "Number of Hydraulic Oil Tanks" + "," 
                        + "Hydraulic Oil Capacity in Gallons" + "," 
                        + "Hydraulic Oil Tank Location(s)" + "," 
                        + "Last Hydraulic Oil Tank Inspection" + ","
                        + "Number of Slop Tanks" + "," 
                        + "Slop Capacity in Gallons" + "," 
                        + "Slop Tank Location(s)" + "," 
                        + "Last Slop Tank Inspection" + ","
                        + "Number of Sewage Tanks" + "," 
                        + "Sewage Capacity in Gallons" + "," 
                        + "Sewage Tank Location(s)" + "," 
                        + "Last Sewage Tank Inspection" + ","
                        + "Number of Water Tanks" + "," 
                        + "Water Capacity in Gallons" + "," 
                        + "Water Tank Location(s)" + "," 
                        + "Last Water Tank Inspection" + ","
                        
                        + "Number of Shafts" + "," + "Drive Type" + "\n");
                
                bufferedWriter.close(); 
                System.out.println("File VP_Tanks created");
                
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(VP_Details.class.getName()).log(Level.SEVERE,
                        null, ex);
            }
        }
        else
        {
            System.out.println("File VP_Tanks already available");
        }
        
        
    }
}
