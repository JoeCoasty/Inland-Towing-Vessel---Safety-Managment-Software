/*
 * Inland Towing Vessel - Safety Management Software is an open source software
 * designed to assist vessel operators in constructionaining compliance with the 
 * administrative requirements found in 46 CFR - SubChapter M.  This software 
 * can be used and modified by anyone as a free service.  This software can not
 * be used as a paid for service.
 */ 
  
 /** <********************* Free Open Source Statement *********************>
  This software is licensed, not sold. Users are granted permission to use it
 * as a free service, but they cannot sell or distribute it for profit. 
 * Additionally, users have the right to modify the software for their own use, 
 * but any modified versions cannot be distributed or sold.
 */ 
package itv_sms2; 

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * @author Joseph Schwierking
 * 
 * This program is used to display the propulsion tab under the
 * vessel particulars menu.
 */
public class VP_Thrusters 
{

    public static void VP_ThrustersPressed(BorderPane pane, GridPane forms) throws FileNotFoundException
    {
         /**checks to see if the path and the CSV file VP_Inspection exist.  If not,
         * this class makes it. */
        MakeVP_Thrusters.MakingVP_Thrusters();
        
        //initializing path for VP_Details.csv
        File VP_Thrusters = new File
        ("src/StoredINFO/VesselParticulars/VP_Thrusters.csv");
        
        //build HBox to place inside GridPane
        HBox[] hbox = new HBox[14];
        HBox[] hbox1_ = new HBox[14];
        HBox[] hbox2_ = new HBox[14];
        HBox[] hbox3_ = new HBox[14];
        for(int i = 0; i<hbox.length; i++)
        {
            hbox[i] = new HBox();
            hbox1_[i] = new HBox();
            hbox2_[i] = new HBox();
            hbox3_[i] = new HBox();
            forms.add(hbox[i], 0, i);
            forms.add(hbox1_[i], 1, i);
            forms.add(hbox2_[i], 2, i);
            forms.add(hbox3_[i], 3, i);
            hbox[i].setPadding(new Insets(0,10,10,0));
            hbox1_[i].setPadding(new Insets(0,10,10,0));
            hbox2_[i].setPadding(new Insets(0,10,10,0));
            hbox3_[i].setPadding(new Insets(0,10,10,0));
            hbox[i].setAlignment(Pos.CENTER);
            hbox1_[i].setAlignment(Pos.CENTER);
            hbox2_[i].setAlignment(Pos.CENTER);
            hbox3_[i].setAlignment(Pos.CENTER);
        }

        HBox hbox99 = new HBox();//for save button
        forms.add(hbox99, 3, 99);
        hbox99.setPadding(new Insets(0,0,10,0));
        
        //build out labels for each user input
              
        Label title = new Label("Thrusters");
        Label location = new Label("Location");
        Label drive = new Label("Drive Type");
        Label hoursePower = new Label("HorsePower/KW");
        
        //build text fields
        TextField tfLocation1 = new TextField("N/A");
        TextField tfLocation2 = new TextField("N/A");
        TextField tfLocation3 = new TextField("N/A");
        
        TextField tfDriveType1 = new TextField("N/A");
        TextField tfDriveType2 = new TextField("N/A");
        TextField tfDriveType3 = new TextField("N/A");
        
        TextField tfhoursePower1 = new TextField("N/A");
        TextField tfhoursePower2 = new TextField("N/A");
        TextField tfhoursePower3 = new TextField("N/A");
        
        //build save button
        Button btSave = new Button("Save");
        
        //move btSave button to the right of the page
        hbox99.setAlignment(Pos.BOTTOM_RIGHT);
        
        //place labels and textfields into hboxes
        hbox[0].getChildren().addAll(title);
        
        hbox1_[1].getChildren().addAll(location);
        hbox2_[1].getChildren().addAll(drive);
        hbox3_[1].getChildren().addAll(hoursePower); 
        
        hbox1_[2].getChildren().addAll(tfLocation1);
        hbox2_[2].getChildren().addAll(tfDriveType1);
        hbox3_[2].getChildren().addAll(tfhoursePower1);
        
        hbox1_[3].getChildren().addAll(tfLocation2);
        hbox2_[3].getChildren().addAll(tfDriveType2);
        hbox3_[3].getChildren().addAll(tfhoursePower2);
        
        hbox1_[4].getChildren().addAll(tfLocation3);
        hbox2_[4].getChildren().addAll(tfDriveType3);
        hbox3_[4].getChildren().addAll(tfhoursePower3);
        
        hbox99.getChildren().addAll(btSave);
        
        //****************Reading from VP_VesselSpecifics CSV************************
       
         //creating an array from VPInspection seperating each string by commas
        File VPThrusters = new File
        ("src/StoredINFO/VesselParticulars/VP_Thrusters.csv");
        String line = "";
        String cvsSplitBy = ",";
        List<String[]> rows = new ArrayList<>();
        
        FileReader fileReader = new FileReader(VPThrusters);
        BufferedReader br = new BufferedReader(fileReader);
        
        try 
        {
            while ((line = br.readLine()) != null) 
            {
                String[] row = line.split(cvsSplitBy);//split sting by comma
                rows.add(row);//not sure what this is
            }
          
        }
        catch(IOException e)
        {
            System.err.println("Error when trying file reader");
        }
        
        String[] lastRow = rows.get(rows.size() - 1);//takes you to the last row
        
               
        //Using data from array to fill in nodes
        
        DateTimeFormatter formatter = 
                        DateTimeFormatter.ofPattern("yyyy-MM-dd");
                
        if(lastRow[0].equals("Time Stamp"))//No one has saved anything before
        {
            System.out.println("No values ever saved in Vessel Specifics before");
            try 
            {
                br.close();
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(VP_Inspection.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        //*********************Pulling data from array*************************
        else
        {
                        
            //getting entered data from CSV
            tfLocation1.setText(lastRow[1]);
            tfDriveType1.setText(lastRow[2]);
            tfhoursePower1.setText(lastRow[3]);
            
            tfLocation2.setText(lastRow[4]);
            tfDriveType2.setText(lastRow[5]);
            tfhoursePower2.setText(lastRow[6]);
            
            tfLocation3.setText(lastRow[7]);
            tfDriveType3.setText(lastRow[8]);
            tfhoursePower3.setText(lastRow[9]);
        
        }  
        
        //Action events for save button 
        btSave.setOnAction(e -> 
        {
            //creates a time stamp so we can track when changes are made
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            File VP_VesselSpecifics = new File
                ("src/StoredINFO/VesselParticulars/VP_Thrusters.csv");
            try {
                FileWriter fileWriter = new FileWriter(VP_VesselSpecifics, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                //writes to the CSV file
                bufferedWriter.write(timestamp.toString()
                        + "," + tfLocation1.getText()+ "," + tfDriveType1.getText()
                         + "," + tfhoursePower1.getText() 
                        
                        + "," + tfLocation2.getText()+ "," + tfDriveType2.getText()
                         + "," + tfhoursePower2.getText()
             
                        + "," + tfLocation3.getText()+ "," + tfDriveType3.getText()
                         + "," + tfhoursePower3.getText()
                        
                        + "," + "\n");

                bufferedWriter.close(); 
                System.out.println("Saved to src/StoredINFO/VP_VesselSpecifics");
            } 
            catch (IOException ex) 
            {
                System.err.println("File did not write to "
                        + "src/StoredINFO/VP_VesselSpecifics");
            }
            
            
            
        });  
        
    }
    
}
