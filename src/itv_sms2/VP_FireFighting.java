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
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * @author Joseph Schwierking
 * 
 * This program is used to display the inspections tab under the 
 * vessel particulars menu.
 */
public class VP_FireFighting 
{
     
    public static void VP_FireFightingPressed(BorderPane pane, GridPane forms) throws FileNotFoundException
    {
        
        /**checks to see if the path and the CSV file VP_FireFighting exist.  If not,
         * this class makes it. */
        MakeVP_FireFighting.MakingVP_FireFighting();
        
        //initializing path for VP_Details.csv
        File VPFireFighting = new File
        ("src/StoredINFO/VesselParticulars/VP_FireFighting.csv");
        
        //build HBox to place inside GridPane
        HBox[] hbox = new HBox[17];
        Label[] hgap = new Label[17];
        for(int i = 0; i<hbox.length; i++)
        {
            hbox[i] = new HBox();
            hgap[i] = new Label("   ");
            forms.add(hbox[i], 0, i);
            hbox[i].setPadding(new Insets(0,0,10,0));
        }

        
        HBox hbox99 = new HBox();//for save button
        forms.add(hbox99, 0, 99);
               
        
        //build out labels for each user input
        Label FireFightingInfo = new Label("   FireFighting Information:  ");
        FireFightingInfo.setStyle("-fx-font-weight: bold");
        Label builder = new Label("   Builder:");
        Label constructionLocation = new Label("   FireFighting Location:");
        Label contractDate = new Label("   Contract Date:  ");
        Label keelLaidDate = new Label("   Keel Laid Date: ");
        Label deliveryDate= new Label("   Delivery Date:");
        Label majorConversion= new Label("   Major Conversion/Modification:");
        majorConversion.setStyle("-fx-font-weight: bold");
        Label majorConversionType= new Label("\t\t\t Type \t\t");
        Label majorConversionDate= new Label("\t\t\t Date Completed \t\t");
        Label majorConversionDiscription= new Label("\t\t\t\t Discription");
        Label doublers = new Label("   Doublers:  ");
        doublers.setStyle("-fx-font-weight: bold");
        Label doublerSize = new Label("    Doubler Size");
        Label doublerLocation = new Label("\t\t Doubler Location ");
        Label doublerInstallDate = new Label("\t\t Doubler Install Date \t\t");
        Label doublerJustification = new Label("\t\t\t Install Justification ");
        
        Label bilgeSystem = new Label("   Bilge System:  ");
        bilgeSystem.setStyle("-fx-font-weight: bold");
        Label bilgeCapacity = new Label("    System Capacity");
        Label numberPumps = new Label("    # of Pumps");
        
        Label balastSystem = new Label("   Balast System:  ");
        balastSystem.setStyle("-fx-font-weight: bold");
        Label balastCapacity = new Label("    System Capacity:");
        Label balastType = new Label("    System Type:");
        
        TextField tfBuilder = new TextField();
        TextField tfFireFightingLocation = new TextField();
        TextField tfMajorConversionDiscription = new TextField();
        tfMajorConversionDiscription.setMinWidth(300);
        
        TextField tfDoublerSize1 = new TextField();
        tfDoublerSize1.setMaxWidth(100);
        TextField tfDoublerLocation1 = new TextField();
        TextField tfDoublerJustification1 = new TextField();
        tfDoublerJustification1.setMinWidth(300);
        
        TextField tfDoublerSize2 = new TextField();
        tfDoublerSize2.setMaxWidth(100);
        TextField tfDoublerLocation2 = new TextField();
        TextField tfDoublerJustification2 = new TextField();
        tfDoublerJustification2.setMinWidth(300);
        
        TextField tfBilgeCapacity = new TextField();
        TextField tfNumberPumps = new TextField();
        
        TextField tfbalastCapacity = new TextField();
        
        
        
        //build datepickers for inspection dates
                
        DatePicker dpContractDate = new DatePicker();
        DatePicker dpKeelLaidDate = new DatePicker();
        DatePicker dpDeliveryDate = new DatePicker();
        dpContractDate.setMaxWidth(150);
        dpKeelLaidDate.setMaxWidth(150);
        dpDeliveryDate.setMaxWidth(150);        
        
        DatePicker dpMajorConversionDate = new DatePicker();
        
        DatePicker dpDoublerInstallDate1 = new DatePicker();
        DatePicker dpDoublerInstallDate2 = new DatePicker();
        
        //Choice box for major conversion system selection
        ChoiceBox cbMajorConversionType = new ChoiceBox(FXCollections.observableArrayList
        ("", "Dimensions/Carrying Capacity", "Vessel Type", "Prolong Life", "New Vessel"));
        ChoiceBox cbBalastType = new ChoiceBox(FXCollections.observableArrayList
        ("", "Segrigated Water", "Solid", "Water"));
        
        //check boxes for fixed bilge system and inductor system
        CheckBox cbFixedSystem = new CheckBox("Fixed System");
        CheckBox cbInductor = new CheckBox("Inductor");
        CheckBox cbFixedBalast = new CheckBox("Fixed System");
        
        //build save button
        Button btSave = new Button("Save");
        
        //move btSave button to the right of the page
        hbox99.setAlignment(Pos.TOP_RIGHT);
        
        //place labels and textfields into hboxes
        hbox[0].getChildren().add(FireFightingInfo);
        hbox[1].getChildren().addAll(builder, tfBuilder, constructionLocation, 
                tfFireFightingLocation);
        hbox[2].getChildren().addAll(contractDate, dpContractDate, keelLaidDate, 
                dpKeelLaidDate, deliveryDate, dpDeliveryDate);
        
        hbox[3].getChildren().add(hgap[0]);
        hbox[4].getChildren().addAll(majorConversion);
        hbox[5].getChildren().addAll(majorConversionType, majorConversionDate, 
                majorConversionDiscription);
        hbox[6].getChildren().addAll(cbMajorConversionType, hgap[1], 
                dpMajorConversionDate, hgap[2], 
                tfMajorConversionDiscription);
        
        hbox[7].getChildren().add(hgap[3]);
        hbox[8].getChildren().addAll(doublers);
        hbox[9].getChildren().addAll(doublerSize, doublerLocation, 
                doublerInstallDate, doublerJustification);
        hbox[10].getChildren().addAll(tfDoublerSize1, hgap[4], tfDoublerLocation1,
                hgap[5], dpDoublerInstallDate1, hgap[6], tfDoublerJustification1);
        hbox[11].getChildren().addAll(tfDoublerSize2, hgap[7], tfDoublerLocation2,
                hgap[8], dpDoublerInstallDate2, hgap[9], tfDoublerJustification2);
        hbox[12].getChildren().add(hgap[10]);
        hbox[13].getChildren().addAll(bilgeSystem);
        hbox[14].getChildren().addAll(bilgeCapacity, tfBilgeCapacity, 
                numberPumps, tfNumberPumps, hgap[11], cbFixedSystem, hgap[12], 
                cbInductor);
        hbox[15].getChildren().addAll(balastSystem);
        hbox[16].getChildren().addAll(balastCapacity,tfbalastCapacity, hgap[13],
                cbFixedBalast, hgap[14], balastType, cbBalastType);
        
        /**here we need to pull from the VP_Details file to determine what we
         * display for inspection type.  (CG annual, external survey, internal
         * survey) */
        
        //this initializes  
        File VPDetails = new File("src/StoredINFO/VesselParticulars/VP_Details.csv");
        String line = "";
        String cvsSplitBy = ",";
        List<String[]> rows = new ArrayList<>();
        
        FileReader fileReader = new FileReader(VPDetails);
        BufferedReader br = new BufferedReader(fileReader);
        
        //Makes an array of every entry 
        try 
        {
            while ((line = br.readLine()) != null) 
            {
                String[] row = line.split(cvsSplitBy);
                rows.add(row);
            }
            
        }
        catch(IOException e)
        {
            System.err.println("Error when trying file reader");
        }
        
         String[] lastRow = rows.get(rows.size() - 1);
        
        //this will only show up if the TSMS box is selected in VPDetails
       
               
        try 
        {    
            br.close();
        }
        catch (IOException ex) 
        {
            Logger.getLogger(VP_FireFighting.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        hbox99.getChildren().add(btSave);
        
                
        //****************Reading from VP_FireFighting CSV************************
        
         //creating an array from VPFireFighting seperating each string by commas
        line = "";
        cvsSplitBy = ",";
        rows = new ArrayList<>();
        
        fileReader = new FileReader(VPFireFighting);
        br = new BufferedReader(fileReader);
        
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
        
        lastRow = rows.get(rows.size() - 1);//takes you to the last row
        
               
        //Using data from array to fill in nodes
        
        DateTimeFormatter formatter = 
                        DateTimeFormatter.ofPattern("yyyy-MM-dd");
                
        if(lastRow[0].equals("Time Stamp"))//No one has saved anything before
        {
            System.out.println("No values ever saved in Details before");
            try 
            {
                br.close();
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(VP_FireFighting.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        //*********************Pulling data from array*************************
        else
        {
            
            tfBuilder.setText(lastRow[1]);
            tfFireFightingLocation.setText(lastRow[2]);
            if(!lastRow[3].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[3], formatter);
                dpContractDate.setValue(date);
            }
            if(!lastRow[4].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[4], formatter);
                dpKeelLaidDate.setValue(date);
            }
            if(!lastRow[5].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[5], formatter);
                dpDeliveryDate.setValue(date);
            }
            
            cbMajorConversionType.setValue(lastRow[6]);
            if(!lastRow[7].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[7], formatter);
                dpMajorConversionDate.setValue(date);
            }
            tfMajorConversionDiscription.setText(lastRow[8]);
            
            tfDoublerSize1.setText(lastRow[9]);
            tfDoublerLocation1.setText(lastRow[10]);
            if(!lastRow[11].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[11], formatter);
                dpDoublerInstallDate1.setValue(date);
            }
            tfDoublerJustification1.setText(lastRow[12]);
            
            tfDoublerSize2.setText(lastRow[13]);
            tfDoublerLocation2.setText(lastRow[14]);
            if(!lastRow[15].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[15], formatter);
                dpDoublerInstallDate2.setValue(date);
            }
            tfDoublerJustification2.setText(lastRow[16]);
            
            tfBilgeCapacity.setText(lastRow[17]);
            tfNumberPumps.setText(lastRow[18]);
            if(lastRow[19].equals("true"))//If fixed system is selected
            {
                cbFixedSystem.setSelected(true);
            }
            if(lastRow[20].equals("true"))//If inductor is selected
            {
                cbInductor.setSelected(true);
            }
            tfbalastCapacity.setText(lastRow[21]);
            if(lastRow[22].equals("true"))//If fixed balast is selected
            {
                cbFixedBalast.setSelected(true);
            }
            cbBalastType.setValue(lastRow[23]);
            
          
        }
            
        
        //***************Action events for save button selected****************       
        
        btSave.setOnAction(e -> 
        {
            
            FileWriter fileWriter;
            try {
                //creates a time stamp so we can track when changes are made
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());

                fileWriter = new FileWriter(VPFireFighting, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                //writes to the CSV file
                bufferedWriter.write(timestamp.toString() + "," 
                        + tfBuilder.getText() + "," + tfFireFightingLocation.getText() 
                        + "," + dpContractDate.getValue() 
                        + "," + dpKeelLaidDate.getValue() 
                        + "," + dpDeliveryDate.getValue() 
                        + "," + cbMajorConversionType.getValue() 
                        + "," + dpMajorConversionDate.getValue()
                        + "," + tfMajorConversionDiscription.getText()
                        + "," + tfDoublerSize1.getText()
                        + "," + tfDoublerLocation1.getText()
                        + "," + dpDoublerInstallDate1.getValue()
                        + "," + tfDoublerJustification1.getText()
                        + "," + tfDoublerSize2.getText()
                        + "," + tfDoublerLocation2.getText()
                        + "," + dpDoublerInstallDate2.getValue()
                        + "," + tfDoublerJustification2.getText()
                        
                        + "," + tfBilgeCapacity.getText()
                        + "," + tfNumberPumps.getText()
                        + "," + cbFixedSystem.isSelected()
                        + "," + cbInductor.isSelected()
                        
                        + "," + tfbalastCapacity.getText()
                        + "," + cbFixedBalast.isSelected()
                        + "," + cbBalastType.getValue()
                        
                        + "," + "\n");

                bufferedWriter.close(); 
                System.out.println("Saved to src/StoredINFO/VesselParticulars");
            } catch (IOException ex) {
                System.err.println("File did not write to "
                        + "src/StoredINFO/VesselParticulars");
            }
            
            
        });
       
    }
    
}
