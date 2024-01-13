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
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
public class VP_Steering 
{

    public static void VP_SteeringPressed(BorderPane pane, GridPane forms) throws FileNotFoundException
    {
         /**checks to see if the path and the CSV file VP_Inspection exist.  If not,
         * this class makes it. */
        MakeVP_Steering.MakingVP_Steering();
        
        //initializing path for VP_Details.csv
        File VP_Steering = new File
        ("src/StoredINFO/VesselParticulars/VP_Steering.csv");
        
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
              
        Label title = new Label("Steering");
        Label classification = new Label("Classification");
        Label controlType = new Label("Control Type");
        Label rudderQuantity = new Label("Rudder Quantity");
        
        //build text fields
        ChoiceBox cbClassification1 = new ChoiceBox(FXCollections.observableArrayList
        ("", "Main", "Flanking", "Emergency"));
        ChoiceBox cbClassification2 = new ChoiceBox(FXCollections.observableArrayList
        ("", "Main", "Flanking", "Emergency"));
        ChoiceBox cbClassification3 = new ChoiceBox(FXCollections.observableArrayList
        ("", "Main", "Flanking", "Emergency"));
        
        ChoiceBox cbControlType1 = new ChoiceBox(FXCollections.observableArrayList
        ("", "Electric", "Hydraulic", "Mechanical", "NEC"));
        ChoiceBox cbControlType2 = new ChoiceBox(FXCollections.observableArrayList
        ("", "Electric", "Hydraulic", "Mechanical", "NEC"));
        ChoiceBox cbControlType3 = new ChoiceBox(FXCollections.observableArrayList
        ("", "Electric", "Hydraulic", "Mechanical", "NEC"));
        
        TextField tfrudderQuantity1 = new TextField("N/A");
        TextField tfrudderQuantity2 = new TextField("N/A");
        TextField tfrudderQuantity3 = new TextField("N/A");
        
        //build save button
        Button btSave = new Button("Save");
        
        //move btSave button to the right of the page
        hbox99.setAlignment(Pos.BOTTOM_RIGHT);
        
        //place labels and textfields into hboxes
        hbox[0].getChildren().addAll(title);
        
        hbox1_[1].getChildren().addAll(classification);
        hbox2_[1].getChildren().addAll(controlType);
        hbox3_[1].getChildren().addAll(rudderQuantity); 
        
        hbox1_[2].getChildren().addAll(cbClassification1);
        hbox2_[2].getChildren().addAll(cbControlType1);
        hbox3_[2].getChildren().addAll(tfrudderQuantity1);
        
        hbox1_[3].getChildren().addAll(cbClassification2);
        hbox2_[3].getChildren().addAll(cbControlType2);
        hbox3_[3].getChildren().addAll(tfrudderQuantity2);
        
        hbox1_[4].getChildren().addAll(cbClassification3);
        hbox2_[4].getChildren().addAll(cbControlType3);
        hbox3_[4].getChildren().addAll(tfrudderQuantity3);
        
        hbox99.getChildren().addAll(btSave);
        
        //****************Reading from VP_VesselSpecifics CSV************************
       
         //creating an array from VPInspection seperating each string by commas
        File VPSteering = new File
        ("src/StoredINFO/VesselParticulars/VP_Steering.csv");
        String line = "";
        String cvsSplitBy = ",";
        List<String[]> rows = new ArrayList<>();
        
        FileReader fileReader = new FileReader(VPSteering);
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
            cbClassification1.setValue(lastRow[1]);
            cbControlType1.setValue(lastRow[2]);
            tfrudderQuantity1.setText(lastRow[3]);
            
            cbClassification2.setValue(lastRow[4]);
            cbControlType2.setValue(lastRow[5]);
            tfrudderQuantity2.setText(lastRow[6]);
            
            cbClassification3.setValue(lastRow[7]);
            cbControlType3.setValue(lastRow[8]);
            tfrudderQuantity3.setText(lastRow[9]);
        
        }  
        
        //Action events for save button 
        btSave.setOnAction(e -> 
        {
            //creates a time stamp so we can track when changes are made
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            File VP_VesselSpecifics = new File
                ("src/StoredINFO/VesselParticulars/VP_Steering.csv");
            try {
                FileWriter fileWriter = new FileWriter(VP_VesselSpecifics, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                //writes to the CSV file
                bufferedWriter.write(timestamp.toString()
                        + "," + cbClassification1.getValue()+ "," + cbControlType1.getValue()
                         + "," + tfrudderQuantity1.getText() 
                        
                        + "," + cbClassification2.getValue()+ "," + cbControlType2.getValue()
                         + "," + tfrudderQuantity2.getText()
             
                        + "," + cbClassification3.getValue()+ "," + cbControlType3.getValue()
                         + "," + tfrudderQuantity3.getText()
                        
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
