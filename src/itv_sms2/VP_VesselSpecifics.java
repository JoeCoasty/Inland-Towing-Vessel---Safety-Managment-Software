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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * @author Joseph Schwierking
 * 
 * This program is used to display the vessel specifics tab under the
 * vessel particulars menu.
 */
public class VP_VesselSpecifics 
{

    public static void VP_VesselSpecificsPressed(BorderPane pane, GridPane forms) throws FileNotFoundException
    {
         /**checks to see if the path and the CSV file VP_Inspection exist.  If not,
         * this class makes it. */
        MakeVP_VesselSpecifics.MakingVP_VesselSpecifics();
        
        //initializing path for VP_Details.csv
        File VPVesselSpecifics = new File
        ("src/StoredINFO/VesselParticulars/VP_VesselSpecifics.csv");
        
        //build HBox to place inside GridPane
        HBox[] hbox = new HBox[14];
        HBox[] hbox1_ = new HBox[14];
        for(int i = 0; i<hbox.length; i++)
        {
            hbox[i] = new HBox();
            hbox1_[i] = new HBox();
            forms.add(hbox[i], 0, i);
            forms.add(hbox1_[i], 1, i);
            hbox[i].setPadding(new Insets(0,0,10,0));
            hbox1_[i].setPadding(new Insets(0,0,10,0));
        }

        HBox hbox99 = new HBox();//for save button
        forms.add(hbox99, 1, 99);
        hbox99.setPadding(new Insets(0,0,10,0));
        
        //build out labels for each user input
        Label constructionDates = new Label("Construction Dates:");
        Label keelLaid = new Label("   Keel Laid:  ");
        Label deliveryDate = new Label("   Delivery Date:");
        Label measurments = new Label("Measurments:");
        Label length = new Label("   Length:");
        Label beam = new Label("   Beam:  ");
        Label draft = new Label("   Draft: ");
        Label grossTons= new Label("   Gross Tons:");
        Label dwtTons= new Label("   Dwt Tons:");
        Label netTons= new Label("   Net Tons:");
        Label hullMaterial = new Label("   Hull Material:");
        
        //build text fields
        TextField tfLength = new TextField();
        TextField tfBeam = new TextField();
        TextField tfDraft = new TextField();
        TextField tfGrossTons = new TextField();
        TextField tfDwtTons = new TextField();
        TextField tfNetTons = new TextField();
        TextField tfHullMaterial = new TextField();
        
        //build datepickers for keel laid and delivery dates
        DatePicker dpKeelLaid = new DatePicker();
        DatePicker dpDeliveryDate = new DatePicker();
        
        //build save button
        Button btSave = new Button("Save");
        
        //move btSave button to the right of the page
        hbox99.setAlignment(Pos.BOTTOM_RIGHT);
        
        //place labels and textfields into hboxes
        hbox[0].getChildren().addAll(constructionDates);
        hbox[1].getChildren().addAll(keelLaid);
        hbox1_[1].getChildren().addAll(dpKeelLaid, deliveryDate, dpDeliveryDate);
        hbox[2].getChildren().addAll(measurments);
        hbox[3].getChildren().addAll(length);
        hbox1_[3].getChildren().addAll(tfLength);
        hbox[4].getChildren().addAll(beam);
        hbox1_[4].getChildren().addAll(tfBeam);
        hbox[5].getChildren().addAll(draft);
        hbox1_[5].getChildren().addAll(tfDraft);
        hbox[6].getChildren().addAll(grossTons);
        hbox1_[6].getChildren().addAll(tfGrossTons);
        hbox[7].getChildren().addAll(dwtTons);
        hbox1_[7].getChildren().addAll(tfDwtTons);
        hbox[8].getChildren().addAll(netTons);
        hbox1_[8].getChildren().addAll(tfNetTons);
        hbox[9].getChildren().addAll(hullMaterial);
        hbox1_[9].getChildren().addAll(tfHullMaterial);
        hbox99.getChildren().addAll(btSave);
        
        //****************Reading from VP_VesselSpecifics CSV************************
        
         //creating an array from VPInspection seperating each string by commas
        VPVesselSpecifics = new File
        ("src/StoredINFO/VesselParticulars/VP_VesselSpecifics.csv");
        String line = "";
        String cvsSplitBy = ",";
        List<String[]> rows = new ArrayList<>();
        
        FileReader fileReader = new FileReader(VPVesselSpecifics);
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
             
            if(!lastRow[1].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[1], formatter);
                dpKeelLaid.setValue(date);
            }
            
            if(!lastRow[2].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[2], formatter);
                dpDeliveryDate.setValue(date);
            }
            
            //getting the rest of the entered data
            tfLength.setText(lastRow[3]);
            tfBeam.setText(lastRow[4]);
            tfDraft.setText(lastRow[5]);
            tfGrossTons.setText(lastRow[6]);
            tfDwtTons.setText(lastRow[7]);
            tfNetTons.setText(lastRow[8]);
            tfHullMaterial.setText(lastRow[9]);
        }  
        
        //Action events for save button 
        btSave.setOnAction(e -> 
        {
            //creates a time stamp so we can track when changes are made
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            File VP_VesselSpecifics = new File
                ("src/StoredINFO/VesselParticulars/VP_VesselSpecifics.csv");
            try {
                FileWriter fileWriter = new FileWriter(VP_VesselSpecifics, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                //writes to the CSV file
                bufferedWriter.write(timestamp.toString() + "," 
                        + dpKeelLaid.getValue() + "," + dpDeliveryDate.getValue() 
                        + "," + tfLength.getText()+ "," + tfBeam.getText()
                        + "," + tfDraft.getText()+ "," + tfGrossTons.getText()
                        + "," + tfDwtTons.getText()+ "," + tfNetTons.getText()
                        + "," + tfHullMaterial.getText() + "\n");

                bufferedWriter.close(); 
                System.out.println("Saved to src/StoredINFO/VP_VesselSpecifics");
            } catch (IOException ex) {
                System.err.println("File did not write to "
                        + "src/StoredINFO/VP_VesselSpecifics");
            }
            
            
        });  
        
    }
    
}
