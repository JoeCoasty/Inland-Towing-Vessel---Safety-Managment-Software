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
public class VP_Tanks 
{

    public static void VP_TanksPressed(BorderPane pane, GridPane forms) throws FileNotFoundException
    {
         /**checks to see if the path and the CSV file VP_Inspection exist.  If not,
         * this class fuels it. */
        MakeVP_Tanks.MakingVP_Tanks();
        
        //initializing path for VP_Details.csv
        File VP_Tanks = new File
        ("src/StoredINFO/VesselParticulars/VP_Tanks.csv");
        
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
        //down the left
        Label tankNumber = new Label("Number of Tanks:  ");
        Label tankCapacity = new Label("Tank Capacity in Gallons:");
        Label tankLocation = new Label("Tank Location:");
        Label tankInspected = new Label("Last Inspected:");
        Label tankNumber2 = new Label("Number of Tanks:  ");
        Label tankCapacity2 = new Label("Tank Capacity in Gallons:");
        Label tankLocation2 = new Label("Tank Location:");
        Label tankInspected2 = new Label("Last Inspected:");
        
         //across the top
        Label fuel = new Label("Fuel Oil(Diesel)");
        Label lubeOil = new Label("Lube Oil");
        Label hydraulicOil = new Label("Hydraulic Oil");
        Label slops = new Label("Slops");
        Label sewage = new Label("Sewage");
        Label water = new Label("Water");
        
        //build text fields
        TextField tfFuel_Number = new TextField();
        TextField tfLubeOil_Number = new TextField();
        TextField tfHydraulicOil_Number = new TextField();
        TextField tfSlops_Number = new TextField();
        TextField tfSewage_Number = new TextField();
        TextField tfWater_Number = new TextField();
        
        TextField tfFuel_Capacity = new TextField();
        TextField tfLubeOil_Capacity = new TextField();
        TextField tfHydraulicOil_Capacity = new TextField();
        TextField tfSlops_Capacity = new TextField();
        TextField tfSewage_Capacity = new TextField();
        TextField tfWater_Capacity = new TextField();
        
        TextField tfFuel_Location = new TextField();
        TextField tfLubeOil_Location = new TextField();
        TextField tfHydraulicOil_Location = new TextField();
        TextField tfSlops_Location = new TextField();
        TextField tfSewage_Location = new TextField();
        TextField tfWater_Location = new TextField();
        
        //build datepickers for inspection dates
        DatePicker dpFuel_Inspected = new DatePicker();
        DatePicker dpLubeOil_Inspected = new DatePicker();
        DatePicker dpHydraulicOil_Inspected = new DatePicker();
        DatePicker dpSlops_Inspected = new DatePicker();
        DatePicker dpSewage_Inspected = new DatePicker();
        DatePicker dpWater_Inspected = new DatePicker();
        
        //build save button
        Button btSave = new Button("Save");
        
        //move btSave button to the right of the page
        hbox99.setAlignment(Pos.BOTTOM_RIGHT);
        
        //place labels and textfields into hboxes
        hbox[0].getChildren().addAll();
        hbox[1].getChildren().addAll(title);
        hbox1_[1].getChildren().addAll(fuel);
        hbox2_[1].getChildren().addAll(lubeOil);
        hbox3_[1].getChildren().addAll(hydraulicOil); 
        hbox[2].getChildren().addAll(tankNumber);
        hbox1_[2].getChildren().addAll(tfFuel_Number);
        hbox2_[2].getChildren().addAll(tfLubeOil_Number);
        hbox3_[2].getChildren().addAll(tfHydraulicOil_Number);
        hbox[3].getChildren().addAll(tankCapacity);
        hbox1_[3].getChildren().addAll(tfFuel_Capacity);
        hbox2_[3].getChildren().addAll(tfLubeOil_Capacity);
        hbox3_[3].getChildren().addAll(tfHydraulicOil_Capacity);
        hbox[4].getChildren().addAll(tankLocation);
        hbox1_[4].getChildren().addAll(tfFuel_Location);
        hbox2_[4].getChildren().addAll(tfLubeOil_Location);
        hbox3_[4].getChildren().addAll(tfHydraulicOil_Location);
        hbox[5].getChildren().addAll(tankInspected);
        hbox1_[5].getChildren().addAll(dpFuel_Inspected);
        hbox2_[5].getChildren().addAll(dpLubeOil_Inspected);
        hbox3_[5].getChildren().addAll(dpHydraulicOil_Inspected);
        hbox[6].getChildren().addAll();
        hbox1_[6].getChildren().addAll(slops);
        hbox2_[6].getChildren().addAll(sewage);
        hbox3_[6].getChildren().addAll(water);
        hbox[7].getChildren().addAll(tankNumber2);
        hbox1_[7].getChildren().addAll(tfSlops_Number);
        hbox2_[7].getChildren().addAll(tfSewage_Number);
        hbox3_[7].getChildren().addAll(tfWater_Number);
        hbox[8].getChildren().addAll(tankCapacity2);
        hbox1_[8].getChildren().addAll(tfSlops_Capacity);
        hbox2_[8].getChildren().addAll(tfSewage_Capacity);
        hbox3_[8].getChildren().addAll(tfWater_Capacity);
        hbox[9].getChildren().addAll(tankLocation2);
        hbox1_[9].getChildren().addAll(tfSlops_Location);
        hbox2_[9].getChildren().addAll(tfSewage_Location);
        hbox3_[9].getChildren().addAll(tfWater_Location);
        hbox[10].getChildren().addAll(tankInspected2);
        hbox1_[10].getChildren().addAll(dpSlops_Inspected);
        hbox2_[10].getChildren().addAll(dpSewage_Inspected);
        hbox3_[10].getChildren().addAll(dpWater_Inspected);
        
        
        hbox99.getChildren().addAll(btSave);
        
        //****************Reading from VP_VesselSpecifics CSV************************
       
         //creating an array from VPInspection seperating each string by commas
        File VPTanks = new File
        ("src/StoredINFO/VesselParticulars/VP_Tanks.csv");
        String line = "";
        String cvsSplitBy = ",";
        List<String[]> rows = new ArrayList<>();
        
        FileReader fileReader = new FileReader(VPTanks);
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
            tfFuel_Number.setText(lastRow[1]);
            tfFuel_Capacity.setText(lastRow[2]);
            tfFuel_Location.setText(lastRow[3]);
            if(!lastRow[4].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[4], formatter);
                dpFuel_Inspected.setValue(date);
            }
            
            tfLubeOil_Number.setText(lastRow[5]);
            tfLubeOil_Capacity.setText(lastRow[6]);
            tfLubeOil_Location.setText(lastRow[7]);
            if(!lastRow[8].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[8], formatter);
                dpLubeOil_Inspected.setValue(date);
            }
            
            tfHydraulicOil_Number.setText(lastRow[9]);
            tfHydraulicOil_Capacity.setText(lastRow[10]);
            tfHydraulicOil_Location.setText(lastRow[11]);
            if(!lastRow[12].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[12], formatter);
                dpHydraulicOil_Inspected.setValue(date);
            }
            
            tfSlops_Number.setText(lastRow[13]);
            tfSlops_Capacity.setText(lastRow[14]);
            tfSlops_Location.setText(lastRow[15]);
            if(!lastRow[16].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[16], formatter);
                dpSlops_Inspected.setValue(date);
            }
            
            tfSewage_Number.setText(lastRow[17]);
            tfSewage_Capacity.setText(lastRow[18]);
            tfSewage_Location.setText(lastRow[19]);
            if(!lastRow[20].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[20], formatter);
                dpSewage_Inspected.setValue(date);
            }
            
            tfWater_Number.setText(lastRow[21]);
            tfWater_Capacity.setText(lastRow[22]);
            tfWater_Location.setText(lastRow[23]);
            if(!lastRow[24].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[24], formatter);
                dpWater_Inspected.setValue(date);
            }
                                  
            
        }  
        
        //Action events for save button 
        btSave.setOnAction(e -> 
        {
            //creates a time stamp so we can track when changes are made
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            File VP_VesselSpecifics = new File
                ("src/StoredINFO/VesselParticulars/VP_Tanks.csv");
            try {
                FileWriter fileWriter = new FileWriter(VP_VesselSpecifics, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                //writes to the CSV file
                bufferedWriter.write(timestamp.toString()
                        + "," + tfFuel_Number.getText()+ "," + tfFuel_Capacity.getText()
                        + "," + tfFuel_Location.getText() + "," + dpFuel_Inspected.getValue() 
                        
                        + "," + tfLubeOil_Number.getText()+ "," + tfLubeOil_Capacity.getText()
                        + "," + tfLubeOil_Location.getText() + "," + dpLubeOil_Inspected.getValue()
                        
                        + "," + tfHydraulicOil_Number.getText()+ "," + tfHydraulicOil_Capacity.getText()
                        + "," + tfHydraulicOil_Location.getText() + "," + dpHydraulicOil_Inspected.getValue()
                        
                        + "," + tfSlops_Number.getText()+ "," + tfSlops_Capacity.getText()
                        + "," + tfSlops_Location.getText() + "," + dpSlops_Inspected.getValue()
                        
                        + "," + tfSewage_Number.getText()+ "," + tfSewage_Capacity.getText()
                        + "," + tfSewage_Location.getText() + "," + dpSewage_Inspected.getValue() 
                        
                        + "," + tfWater_Number.getText()+ "," + tfWater_Capacity.getText()
                        + "," + tfWater_Location.getText() + "," + dpWater_Inspected.getValue()
                                          
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
