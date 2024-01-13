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
 * This program is used to display the propulsion tab under the
 * vessel particulars menu.
 */
public class VP_Generator 
{

    public static void VP_GeneratorPressed(BorderPane pane, GridPane forms) throws FileNotFoundException
    {
         /**checks to see if the path and the CSV file VP_Inspection exist.  If not,
         * this class makes it. */
        MakeVP_Generator.MakingVP_Generator();
        
        //initializing path for VP_Details.csv
        File VP_Generator = new File
        ("src/StoredINFO/VesselParticulars/VP_Generator.csv");
        
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
        Label title = new Label();
        Label portGEN = new Label("Port Generator:  ");
        Label stbdGEN = new Label("STBD Generator:");
        Label miscGEN1 = new Label("Misc. Generator:");
        Label make = new Label("Make/Model");
        Label KW = new Label("KW");
        Label installed = new Label("Date Installed/Last Overhaul");
       
        //build text fields
        TextField tfPortGEN_Make = new TextField();
        TextField tfStbdGEN_Make = new TextField();
        TextField tfMiscGEN1_Make = new TextField();
        
        TextField tfPortGEN_KW = new TextField();
        TextField tfStbdGEN_KW = new TextField();
        TextField tfMiscGEN1_KW = new TextField();
        
        //build datepickers for installation dates
        DatePicker dpPortGEN_Install = new DatePicker();
        DatePicker dpStbdGEN_Install = new DatePicker();
        DatePicker dpMiscGEN1_Install = new DatePicker();
        
        //build save button
        Button btSave = new Button("Save");
        
        //move btSave button to the right of the page
        hbox99.setAlignment(Pos.BOTTOM_RIGHT);
        
        //place labels and textfields into hboxes
        hbox[0].getChildren().addAll();
        hbox[1].getChildren().addAll(title);
        hbox1_[1].getChildren().addAll(make);
        hbox2_[1].getChildren().addAll(KW);
        hbox3_[1].getChildren().addAll(installed); 
        hbox[2].getChildren().addAll(portGEN);
        hbox1_[2].getChildren().addAll(tfPortGEN_Make);
        hbox2_[2].getChildren().addAll(tfPortGEN_KW);
        hbox3_[2].getChildren().addAll(dpPortGEN_Install);
        hbox[3].getChildren().addAll(stbdGEN);
        hbox1_[3].getChildren().addAll(tfStbdGEN_Make);
        hbox2_[3].getChildren().addAll(tfStbdGEN_KW);
        hbox3_[3].getChildren().addAll(dpStbdGEN_Install);
        hbox[4].getChildren().addAll(miscGEN1);
        hbox1_[4].getChildren().addAll(tfMiscGEN1_Make);
        hbox2_[4].getChildren().addAll(tfMiscGEN1_KW);
        hbox3_[4].getChildren().addAll(dpMiscGEN1_Install);
        
        hbox99.getChildren().addAll(btSave);
        
        //****************Reading from VP_VesselSpecifics CSV************************
       
         //creating an array from VPInspection seperating each string by commas
        File VPGenerator = new File
        ("src/StoredINFO/VesselParticulars/VP_Generator.csv");
        String line = "";
        String cvsSplitBy = ",";
        List<String[]> rows = new ArrayList<>();
        
        FileReader fileReader = new FileReader(VPGenerator);
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
            tfPortGEN_Make.setText(lastRow[1]);
            tfPortGEN_KW.setText(lastRow[2]);
            if(!lastRow[3].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[3], formatter);
                dpPortGEN_Install.setValue(date);
            }
            
            tfStbdGEN_Make.setText(lastRow[4]);
            tfStbdGEN_KW.setText(lastRow[5]);
            if(!lastRow[6].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[6], formatter);
                dpStbdGEN_Install.setValue(date);
            }
            
            tfMiscGEN1_Make.setText(lastRow[7]);
            tfMiscGEN1_KW.setText(lastRow[8]);
            if(!lastRow[9].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[9], formatter);
                dpMiscGEN1_Install.setValue(date);
            }
            
        }  
        
        //Action events for save button 
        btSave.setOnAction(e -> 
        {
            //creates a time stamp so we can track when changes are made
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            File VP_VesselSpecifics = new File
                ("src/StoredINFO/VesselParticulars/VP_Generator.csv");
            try {
                FileWriter fileWriter = new FileWriter(VP_VesselSpecifics, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                //writes to the CSV file
                bufferedWriter.write(timestamp.toString()
                        + "," + tfPortGEN_Make.getText()+ "," + tfPortGEN_KW.getText()
                         + "," + dpPortGEN_Install.getValue() 
                        
                        + "," + tfStbdGEN_Make.getText()+ "," + tfStbdGEN_KW.getText()
                         + "," + dpStbdGEN_Install.getValue()
             
                        + "," + tfMiscGEN1_Make.getText()+ "," + tfMiscGEN1_KW.getText()
                         + "," + dpMiscGEN1_Install.getValue()
                        
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
