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
public class MakeVP_Propulsion 
{
    public static void MakingVP_Propulsion()
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
        File VPVesselSpecifics = new File("src/StoredINFO/VesselParticulars/VP_Propulsion.csv");
        if (!VPVesselSpecifics.exists()) 
        {
            try 
            {
                VPVesselSpecifics.createNewFile();
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                
                FileWriter fileWriterLabel = new FileWriter(VPVesselSpecifics, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriterLabel);
                //writes to the CSV file
                bufferedWriter.write("Time Stamp" + "," + "Port MDE Make" + "," 
                        + "Port MDE Hoursepower" + "," 
                        + "Port MDE Install/Overhual Date" + "," 
                        + "STBD MDE Make" + "," 
                        + "STBD MDE Hoursepower" + "," 
                        + "STBD MDE Install/Overhual Date" + "," 
                        + "Misc MDE1 Make" + "," 
                        + "Misc MDE1 Hoursepower" + "," 
                        + "Misc MDE1 Install/Overhual Date" + ","
                        + "Misc MDE2 Make" + "," 
                        + "Misc MDE2 Hoursepower" + "," 
                        + "Misc MDE2 Install/Overhual Date" + ","
                        
                        + "Port RG Make" + "," 
                        + "Port RG Ratio" + "," 
                        + "Port RG Install/Overhual Date" + "," 
                        + "STBD RG Make" + "," 
                        + "STBD RG Ratio" + "," 
                        + "STBD RG Install/Overhual Date" + "," 
                        + "Misc RG1 Make" + "," 
                        + "Misc RG1 Ratio" + "," 
                        + "Misc RG1 Install/Overhual Date" + ","
                        + "Misc RG2 Make" + "," 
                        + "Misc RG2 Ratio" + "," 
                        + "Misc RG2 Install/Overhual Date" + ","
                        
                        + "Number of Shafts" + "," + "Drive Type" + "\n");
                
                bufferedWriter.close(); 
                System.out.println("File VP_Propulsion created");
                
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(VP_Details.class.getName()).log(Level.SEVERE,
                        null, ex);
            }
        }
        else
        {
            System.out.println("File VP_Propulsion already available");
        }
        
        
    }
}
