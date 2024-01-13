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
public class MakeVP_Boilers 
{
    public static void MakingVP_Boilers()
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
        File VPVesselSpecifics = new File("src/StoredINFO/VesselParticulars/VP_Boilers.csv");
        if (!VPVesselSpecifics.exists()) 
        {
            try 
            {
                VPVesselSpecifics.createNewFile();
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                
                FileWriter fileWriterLabel = new FileWriter(VPVesselSpecifics, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriterLabel);
                //writes to the CSV file
                for (int i = 1; i<5; i++)
                {
                bufferedWriter.write("Time Stamp" + ","
                        + "Vessel " + i + " Name" + "," 
                        + "Vessel " + i + " Manufacturer" + "," 
                        + "Vessel " + i + " Type" + "," 
                        + "Vessel " + i + " Location" + "," 
                        + "Vessel " + i + " Contract#" + ","
                        + "Vessel " + i + " Type of Service" + "," 
                        + "Vessel " + i + " Design Pressure" + ","
                        + "Vessel " + i + " Safety Pressure Setting" + "," 
                        + "Vessel " + i + " Steam Temperature" + "," 
                        + "Vessel " + i + " Inspection Prior to Last" + ","
                        + "Vessel " + i + " Inspection Last" + "," 
                        + "Vessel " + i + " Inspection Next" + ","
                        + "Vessel " + i + " Hydro date" + ",");
                }        
                
                        
                bufferedWriter.write("," + "\n");
                
                bufferedWriter.close(); 
                System.out.println("File VP_Boilers created");
                
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(VP_Details.class.getName()).log(Level.SEVERE,
                        null, ex);
            }
        }
        else
        {
            System.out.println("File VP_Boilers already available");
        }
        
        
    }
}
