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
 * This program is used to display the propulsion tab under the
 * vessel particulars menu.
 */
public class VP_PressureVessels 
{

    public static void VP_PressureVesselsPressed(BorderPane pane, GridPane forms) throws FileNotFoundException
    {
         /**checks to see if the path and the CSV file VP_Inspection exist.  If not,
         * this class Vessel1s it. */
        MakeVP_PressureVessels.MakingVP_PressureVessels();
        
        //initializing path for VP_Details.csv
        File VP_PressureVessels = new File
        ("src/StoredINFO/VesselParticulars/VP_PressureVessels.csv");
        
        //build HBox to place inside GridPane
        HBox[] hbox = new HBox[14];
        HBox[] hbox1_ = new HBox[14];
        HBox[] hbox2_ = new HBox[14];
        HBox[] hbox3_ = new HBox[14];
        HBox[] hbox4_ = new HBox[14];
        for(int i = 0; i<hbox.length; i++)
        {
            hbox[i] = new HBox();
            hbox1_[i] = new HBox();
            hbox2_[i] = new HBox();
            hbox3_[i] = new HBox();
            hbox4_[i] = new HBox();
            forms.add(hbox[i], 0, i);
            forms.add(hbox1_[i], 1, i);
            forms.add(hbox2_[i], 2, i);
            forms.add(hbox3_[i], 3, i);
            forms.add(hbox4_[i], 4, i);
            hbox[i].setPadding(new Insets(0,10,10,0));
            hbox1_[i].setPadding(new Insets(0,10,10,0));
            hbox2_[i].setPadding(new Insets(0,10,10,0));
            hbox3_[i].setPadding(new Insets(0,10,10,0));
            hbox4_[i].setPadding(new Insets(0,10,10,0));
            hbox[i].setAlignment(Pos.CENTER);
            hbox1_[i].setAlignment(Pos.CENTER);
            hbox2_[i].setAlignment(Pos.CENTER);
            hbox3_[i].setAlignment(Pos.CENTER);
            hbox4_[i].setAlignment(Pos.CENTER);
        }
        
        //Set max width of table colums to sqeeze everything in
        for(int i = 1; i<hbox.length-1; i++)
        {
            hbox1_[i].setMaxWidth(150);
            hbox2_[i].setMaxWidth(150);
            hbox3_[i].setMaxWidth(150);
            hbox4_[i].setMaxWidth(150);
            
        }
        HBox hbox99 = new HBox();//for save button
        forms.add(hbox99, 4, 99);
        hbox99.setPadding(new Insets(0,0,10,0));
        
        //build out labels for each user input
        Label title = new Label();
        //down the left side of table
        Label PV_Name = new Label("Pressure Vessel Name:  ");
        Label PV_Manufacturer = new Label("Manufacturer:");
        Label PV_ID = new Label("ID/Serial #:");
        Label PV_Location = new Label("Tank Location:");
        Label PV_Service = new Label("Type of Service:");
        Label PV_MAWP = new Label("MAWP:");
        Label PV_ReliefSetPoint = new Label("Relief Set Point:");
        Label PV_InspPrior = new Label("Prior to Last Inspected:");
        Label PV_InspLast = new Label("Last Inspected:");
        Label PV_InspNext = new Label("Next Inspection:");
        
        
         //across the top
        Label Vessel1 = new Label("Vessel #1");
        Label Vessel2 = new Label("Vessel #2");
        Label Vessel3 = new Label("Vessel #3");
        Label Vessel4 = new Label("Vessel #4");
        
        
        //build text fields
        TextField tfVSL1_Name = new TextField();
        TextField tfVSL2_Name = new TextField();
        TextField tfVSL3_Name = new TextField();
        TextField tfVSL4_Name = new TextField();
        
        TextField tfVSL1_Manufacturer = new TextField();
        TextField tfVSL2_Manufacturer = new TextField();
        TextField tfVSL3_Manufacturer = new TextField();
        TextField tfVSL4_Manufacturer = new TextField();
        
        TextField tfVSL1_ID = new TextField();
        TextField tfVSL2_ID = new TextField();
        TextField tfVSL3_ID = new TextField();
        TextField tfVSL4_ID = new TextField();
        
        TextField tfVSL1_Location = new TextField();
        TextField tfVSL2_Location = new TextField();
        TextField tfVSL3_Location = new TextField();
        TextField tfVSL4_Location = new TextField();
        
        TextField tfVSL1_MAWP = new TextField();
        TextField tfVSL2_MAWP = new TextField();
        TextField tfVSL3_MAWP = new TextField();
        TextField tfVSL4_MAWP = new TextField();
        
        TextField tfVSL1_Relief = new TextField();
        TextField tfVSL2_Relief = new TextField();
        TextField tfVSL3_Relief = new TextField();
        TextField tfVSL4_Relief = new TextField();
        
                
        //build datepickers for inspection dates
        DatePicker dpVSL1_PriorInsp = new DatePicker();
        DatePicker dpVSL2_PriorInsp = new DatePicker();
        DatePicker dpVSL3_PriorInsp = new DatePicker();
        DatePicker dpVSL4_PriorInsp = new DatePicker();
        
        DatePicker dpVSL1_LastInsp = new DatePicker();
        DatePicker dpVSL2_LastInsp = new DatePicker();
        DatePicker dpVSL3_LastInsp = new DatePicker();
        DatePicker dpVSL4_LastInsp = new DatePicker();
        
        DatePicker dpVSL1_NextInsp = new DatePicker();
        DatePicker dpVSL2_NextInsp = new DatePicker();
        DatePicker dpVSL3_NextInsp = new DatePicker();
        DatePicker dpVSL4_NextInsp = new DatePicker();
        
        //build choise box for type of service pressure vessel is used for
        ChoiceBox cbVSL1_Service = new ChoiceBox(FXCollections.observableArrayList
        ("", "Service Air", "Control Air", "Start Air", "Combination"));
        ChoiceBox cbVSL2_Service = new ChoiceBox(FXCollections.observableArrayList
        ("", "Service Air", "Control Air", "Start Air", "Combination"));
        ChoiceBox cbVSL3_Service = new ChoiceBox(FXCollections.observableArrayList
        ("", "Service Air", "Control Air", "Start Air", "Combination"));
        ChoiceBox cbVSL4_Service = new ChoiceBox(FXCollections.observableArrayList
        ("", "Service Air", "Control Air", "Start Air", "Combination"));
        //make choise boxes the same size as textfields
        cbVSL1_Service.setMinWidth(140);
        cbVSL2_Service.setMinWidth(140);
        cbVSL3_Service.setMinWidth(140);
        cbVSL4_Service.setMinWidth(140);
        
        //build save button
        Button btSave = new Button("Save");
        
        //move btSave button to the right of the page
        hbox99.setAlignment(Pos.BOTTOM_RIGHT);
        
        //place labels and textfields into hboxes
        hbox[0].getChildren().addAll();
        hbox[1].getChildren().addAll(title);
        hbox1_[1].getChildren().addAll(Vessel1);
        hbox2_[1].getChildren().addAll(Vessel2);
        hbox3_[1].getChildren().addAll(Vessel3);
        hbox4_[1].getChildren().addAll(Vessel4);
        
        hbox[2].getChildren().addAll(PV_Name);
        hbox1_[2].getChildren().addAll(tfVSL1_Name);
        hbox2_[2].getChildren().addAll(tfVSL2_Name);
        hbox3_[2].getChildren().addAll(tfVSL3_Name);
        hbox4_[2].getChildren().addAll(tfVSL4_Name);
        
        hbox[3].getChildren().addAll(PV_Manufacturer);
        hbox1_[3].getChildren().addAll(tfVSL1_Manufacturer);
        hbox2_[3].getChildren().addAll(tfVSL2_Manufacturer);
        hbox3_[3].getChildren().addAll(tfVSL3_Manufacturer);
        hbox4_[3].getChildren().addAll(tfVSL4_Manufacturer);
        
        hbox[4].getChildren().addAll(PV_ID);
        hbox1_[4].getChildren().addAll(tfVSL1_ID);
        hbox2_[4].getChildren().addAll(tfVSL2_ID);
        hbox3_[4].getChildren().addAll(tfVSL3_ID);
        hbox4_[4].getChildren().addAll(tfVSL4_ID);
        
        hbox[5].getChildren().addAll(PV_Location);
        hbox1_[5].getChildren().addAll(tfVSL1_Location);
        hbox2_[5].getChildren().addAll(tfVSL2_Location);
        hbox3_[5].getChildren().addAll(tfVSL3_Location);
        hbox4_[5].getChildren().addAll(tfVSL4_Location);
        
        hbox[6].getChildren().addAll(PV_Service);
        hbox1_[6].getChildren().addAll(cbVSL1_Service);
        hbox2_[6].getChildren().addAll(cbVSL2_Service);
        hbox3_[6].getChildren().addAll(cbVSL3_Service);
        hbox4_[6].getChildren().addAll(cbVSL4_Service);
        
        hbox[7].getChildren().addAll(PV_MAWP);
        hbox1_[7].getChildren().addAll(tfVSL1_MAWP);
        hbox2_[7].getChildren().addAll(tfVSL2_MAWP);
        hbox3_[7].getChildren().addAll(tfVSL3_MAWP);
        hbox4_[7].getChildren().addAll(tfVSL4_MAWP);
        
        hbox[8].getChildren().addAll(PV_ReliefSetPoint);
        hbox1_[8].getChildren().addAll(tfVSL1_Relief);
        hbox2_[8].getChildren().addAll(tfVSL2_Relief);
        hbox3_[8].getChildren().addAll(tfVSL3_Relief);
        hbox4_[8].getChildren().addAll(tfVSL4_Relief);
        
        hbox[9].getChildren().addAll(PV_InspPrior);
        hbox1_[9].getChildren().addAll(dpVSL1_PriorInsp);
        hbox2_[9].getChildren().addAll(dpVSL2_PriorInsp);
        hbox3_[9].getChildren().addAll(dpVSL3_PriorInsp);
        hbox4_[9].getChildren().addAll(dpVSL4_PriorInsp);
        
        hbox[10].getChildren().addAll(PV_InspLast);
        hbox1_[10].getChildren().addAll(dpVSL1_LastInsp);
        hbox2_[10].getChildren().addAll(dpVSL2_LastInsp);
        hbox3_[10].getChildren().addAll(dpVSL3_LastInsp);
        hbox4_[10].getChildren().addAll(dpVSL4_LastInsp);
        
        hbox[11].getChildren().addAll(PV_InspNext);
        hbox1_[11].getChildren().addAll(dpVSL1_NextInsp);
        hbox2_[11].getChildren().addAll(dpVSL2_NextInsp);
        hbox3_[11].getChildren().addAll(dpVSL3_NextInsp);
        hbox4_[11].getChildren().addAll(dpVSL4_NextInsp);
        
        
        hbox99.getChildren().addAll(btSave);
        
        //****************Reading from VP_VesselSpecifics CSV************************
       
         //creating an array from VPInspection seperating each string by commas
        File VPPressureVessels = new File
        ("src/StoredINFO/VesselParticulars/VP_PressureVessels.csv");
        String line = "";
        String cvsSplitBy = ",";
        List<String[]> rows = new ArrayList<>();
        
        FileReader fileReader = new FileReader(VPPressureVessels);
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
            tfVSL1_Name.setText(lastRow[1]);
            tfVSL1_Manufacturer.setText(lastRow[2]);
            tfVSL1_ID.setText(lastRow[3]);
            tfVSL1_Location.setText(lastRow[4]);
            cbVSL1_Service.setValue(lastRow[5]);
            tfVSL1_MAWP.setText(lastRow[6]);
            tfVSL1_Relief.setText(lastRow[7]);
            if(!lastRow[8].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[8], formatter);
                dpVSL1_PriorInsp.setValue(date);
            }
            if(!lastRow[9].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[9], formatter);
                dpVSL1_LastInsp.setValue(date);
            }
            if(!lastRow[10].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[10], formatter);
                dpVSL1_NextInsp.setValue(date);
            }
            
            tfVSL2_Name.setText(lastRow[11]);
            tfVSL2_Manufacturer.setText(lastRow[12]);
            tfVSL2_ID.setText(lastRow[13]);
            tfVSL2_Location.setText(lastRow[14]);
            cbVSL2_Service.setValue(lastRow[15]);
            tfVSL2_MAWP.setText(lastRow[16]);
            tfVSL2_Relief.setText(lastRow[17]);
            if(!lastRow[18].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[18], formatter);
                dpVSL2_PriorInsp.setValue(date);
            }
            if(!lastRow[19].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[19], formatter);
                dpVSL2_LastInsp.setValue(date);
            }
            if(!lastRow[20].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[20], formatter);
                dpVSL2_NextInsp.setValue(date);
            }
            
            tfVSL3_Name.setText(lastRow[21]);
            tfVSL3_Manufacturer.setText(lastRow[22]);
            tfVSL3_ID.setText(lastRow[23]);
            tfVSL3_Location.setText(lastRow[24]);
            cbVSL3_Service.setValue(lastRow[25]);
            tfVSL3_MAWP.setText(lastRow[26]);
            tfVSL3_Relief.setText(lastRow[27]);
            if(!lastRow[28].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[28], formatter);
                dpVSL3_PriorInsp.setValue(date);
            }
            if(!lastRow[29].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[29], formatter);
                dpVSL3_LastInsp.setValue(date);
            }
            if(!lastRow[30].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[30], formatter);
                dpVSL3_NextInsp.setValue(date);
            }
            
            tfVSL4_Name.setText(lastRow[31]);
            tfVSL4_Manufacturer.setText(lastRow[32]);
            tfVSL4_ID.setText(lastRow[33]);
            tfVSL4_Location.setText(lastRow[34]);
            cbVSL4_Service.setValue(lastRow[35]);
            tfVSL4_MAWP.setText(lastRow[36]);
            tfVSL4_Relief.setText(lastRow[37]);
            if(!lastRow[38].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[38], formatter);
                dpVSL4_PriorInsp.setValue(date);
            }
            if(!lastRow[39].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[39], formatter);
                dpVSL4_LastInsp.setValue(date);
            }
            if(!lastRow[40].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[40], formatter);
                dpVSL4_NextInsp.setValue(date);
            }
            
        }  
        
        //Action events for save button 
        btSave.setOnAction(e -> 
        {
            //creates a time stamp so we can track when changes are made
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            File VP_VesselSpecifics = new File
                ("src/StoredINFO/VesselParticulars/VP_PressureVessels.csv");
            try {
                FileWriter fileWriter = new FileWriter(VP_VesselSpecifics, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                //writes to the CSV file
                bufferedWriter.write(timestamp.toString()
                        + "," + tfVSL1_Name.getText()+ "," + tfVSL1_Manufacturer.getText()
                        + "," + tfVSL1_ID.getText() + "," + tfVSL1_Location.getText()
                        + "," + cbVSL1_Service.getValue()+ "," + tfVSL1_MAWP.getText()
                        + "," + tfVSL1_Relief.getText()+ "," + dpVSL1_PriorInsp.getValue()
                        + "," + dpVSL1_LastInsp.getValue()+ "," + dpVSL1_NextInsp.getValue()
                        
                        + "," + tfVSL2_Name.getText()+ "," + tfVSL2_Manufacturer.getText()
                        + "," + tfVSL2_ID.getText() + "," + tfVSL2_Location.getText()
                        + "," + cbVSL2_Service.getValue()+ "," + tfVSL2_MAWP.getText()
                        + "," + tfVSL2_Relief.getText()+ "," + dpVSL2_PriorInsp.getValue()
                        + "," + dpVSL2_LastInsp.getValue()+ "," + dpVSL2_NextInsp.getValue()
                        
                        + "," + tfVSL3_Name.getText()+ "," + tfVSL3_Manufacturer.getText()
                        + "," + tfVSL3_ID.getText() + "," + tfVSL3_Location.getText()
                        + "," + cbVSL3_Service.getValue()+ "," + tfVSL3_MAWP.getText()
                        + "," + tfVSL3_Relief.getText()+ "," + dpVSL3_PriorInsp.getValue()
                        + "," + dpVSL3_LastInsp.getValue()+ "," + dpVSL3_NextInsp.getValue()
                        
                        + "," + tfVSL4_Name.getText()+ "," + tfVSL4_Manufacturer.getText()
                        + "," + tfVSL4_ID.getText() + "," + tfVSL4_Location.getText()
                        + "," + cbVSL4_Service.getValue()+ "," + tfVSL4_MAWP.getText()
                        + "," + tfVSL4_Relief.getText()+ "," + dpVSL4_PriorInsp.getValue()
                        + "," + dpVSL4_LastInsp.getValue()+ "," + dpVSL4_NextInsp.getValue()
                                         
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
