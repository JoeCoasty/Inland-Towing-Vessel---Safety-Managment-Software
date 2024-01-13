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
public class VP_Boilers 
{

    public static void VP_BoilersPressed(BorderPane pane, GridPane forms) throws FileNotFoundException
    {
         /**checks to see if the path and the CSV file VP_Inspection exist.  If not,
         * this class Vessel1s it. */
        MakeVP_Boilers.MakingVP_Boilers();
        
        //initializing path for VP_Details.csv
        File VP_Boilers = new File
        ("src/StoredINFO/VesselParticulars/VP_Boilers.csv");
        
        //build HBox to place inside GridPane
        HBox[] hbox = new HBox[16];
        HBox[] hbox1_ = new HBox[16];
        HBox[] hbox2_ = new HBox[16];
        HBox[] hbox3_ = new HBox[16];
        HBox[] hbox4_ = new HBox[16];
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
        Label Boiler_Name = new Label("Boiler Name:  ");
        Label Boiler_Manufacturer = new Label("Manufacturer:");
        Label Boiler_Type = new Label("Boiler Type:");
        Label Boiler_Location = new Label("Boiler Location:");
        Label Boiler_Contract = new Label("Contract #:");
        Label Boiler_Service = new Label("Boiler Use:");
        Label Boiler_DesignPressure= new Label("Design Pressure:");
        Label Boiler_ReliefSetPoint = new Label("Safety Pressure Setting:");
        Label Boiler_SteamTemp = new Label("Steam Temperature:");
        Label Boiler_InspPrior = new Label("Prior to Last Inspected:");
        Label Boiler_InspLast = new Label("Last Inspected:");
        Label Boiler_InspNext = new Label("Next Inspection:");
        Label Boiler_HydroDate = new Label("Hydro date:");
        
         //across the top
        Label Boiler1 = new Label("Boiler #1");
        Label Boiler2 = new Label("Boiler #2");
        Label Boiler3 = new Label("Boiler #3");
        Label Boiler4 = new Label("Boiler #4");
        
        
        //build text fields
        TextField tbBoiler1_Name = new TextField();
        TextField tbBoiler2_Name = new TextField();
        TextField tbBoiler3_Name = new TextField();
        TextField tbBoiler4_Name = new TextField();
        
        TextField tbBoiler1_Manufacturer = new TextField();
        TextField tbBoiler2_Manufacturer = new TextField();
        TextField tbBoiler3_Manufacturer = new TextField();
        TextField tbBoiler4_Manufacturer = new TextField();
        
        TextField tbBoiler1_Type = new TextField();
        TextField tbBoiler2_Type = new TextField();
        TextField tbBoiler3_Type = new TextField();
        TextField tbBoiler4_Type = new TextField();
        
        TextField tbBoiler1_Location = new TextField();
        TextField tbBoiler2_Location = new TextField();
        TextField tbBoiler3_Location = new TextField();
        TextField tbBoiler4_Location = new TextField();
        
        TextField tbBoiler1_Contract = new TextField();
        TextField tbBoiler2_Contract = new TextField();
        TextField tbBoiler3_Contract = new TextField();
        TextField tbBoiler4_Contract = new TextField();
        
        TextField tbBoiler1_DesignPressure = new TextField();
        TextField tbBoiler2_DesignPressure = new TextField();
        TextField tbBoiler3_DesignPressure = new TextField();
        TextField tbBoiler4_DesignPressure = new TextField();
        
        TextField tbBoiler1_Relief = new TextField();
        TextField tbBoiler2_Relief = new TextField();
        TextField tbBoiler3_Relief = new TextField();
        TextField tbBoiler4_Relief = new TextField();
        
        TextField tbBoiler1_SteamTemp = new TextField();
        TextField tbBoiler2_SteamTemp = new TextField();
        TextField tbBoiler3_SteamTemp = new TextField();
        TextField tbBoiler4_SteamTemp = new TextField();
        
                
        //build datepickers for inspection dates
        DatePicker dpBoiler1_PriorInsp = new DatePicker();
        DatePicker dpBoiler2_PriorInsp = new DatePicker();
        DatePicker dpBoiler3_PriorInsp = new DatePicker();
        DatePicker dpBoiler4_PriorInsp = new DatePicker();
        
        DatePicker dpBoiler1_LastInsp = new DatePicker();
        DatePicker dpBoiler2_LastInsp = new DatePicker();
        DatePicker dpBoiler3_LastInsp = new DatePicker();
        DatePicker dpBoiler4_LastInsp = new DatePicker();
        
        DatePicker dpBoiler1_NextInsp = new DatePicker();
        DatePicker dpBoiler2_NextInsp = new DatePicker();
        DatePicker dpBoiler3_NextInsp = new DatePicker();
        DatePicker dpBoiler4_NextInsp = new DatePicker();
        
        DatePicker dpBoiler1_HydroInsp = new DatePicker();
        DatePicker dpBoiler2_HydroInsp = new DatePicker();
        DatePicker dpBoiler3_HydroInsp = new DatePicker();
        DatePicker dpBoiler4_HydroInsp = new DatePicker();
        
        //build choise box for type of service pressure vessel is used for
        ChoiceBox cbBoiler1_Service = new ChoiceBox(FXCollections.observableArrayList
        ("", "Propulsion", "Auxiliary", "Combination"));
        ChoiceBox cbBoiler2_Service = new ChoiceBox(FXCollections.observableArrayList
        ("", "Propulsion", "Auxiliary", "Combination"));
        ChoiceBox cbBoiler3_Service = new ChoiceBox(FXCollections.observableArrayList
        ("", "Propulsion", "Auxiliary", "Combination"));
        ChoiceBox cbBoiler4_Service = new ChoiceBox(FXCollections.observableArrayList
        ("", "Propulsion", "Auxiliary", "Combination"));
        //make choise boxes the same size as textfields
        cbBoiler1_Service.setMinWidth(140);
        cbBoiler2_Service.setMinWidth(140);
        cbBoiler3_Service.setMinWidth(140);
        cbBoiler4_Service.setMinWidth(140);
        
        ChoiceBox cbBoiler1_Type = new ChoiceBox(FXCollections.observableArrayList
        ("", "Fire Type", "Fluid Thermal Heater", "Hybrid", "Sectional Heater", 
                "Waste Heat", "Water Tube"));
        ChoiceBox cbBoiler2_Type = new ChoiceBox(FXCollections.observableArrayList
        ("", "Fire Type", "Fluid Thermal Heater", "Hybrid", "Sectional Heater", 
                "Waste Heat", "Water Tube"));
        ChoiceBox cbBoiler3_Type = new ChoiceBox(FXCollections.observableArrayList
        ("", "Fire Type", "Fluid Thermal Heater", "Hybrid", "Sectional Heater", 
                "Waste Heat", "Water Tube"));
        ChoiceBox cbBoiler4_Type = new ChoiceBox(FXCollections.observableArrayList
        ("", "Fire Type", "Fluid Thermal Heater", "Hybrid", "Sectional Heater", 
                "Waste Heat", "Water Tube"));
        //make choise boxes the same size as textfields
        cbBoiler1_Type.setMinWidth(140);
        cbBoiler2_Type.setMinWidth(140);
        cbBoiler3_Type.setMinWidth(140);
        cbBoiler4_Type.setMinWidth(140);
        
        //build save button
        Button btSave = new Button("Save");
        
        //move btSave button to the right of the page
        hbox99.setAlignment(Pos.BOTTOM_RIGHT);
        
        //place labels and textfields into hboxes
        hbox[0].getChildren().addAll();
        hbox[1].getChildren().addAll(title);
        hbox1_[1].getChildren().addAll(Boiler1);
        hbox2_[1].getChildren().addAll(Boiler2);
        hbox3_[1].getChildren().addAll(Boiler3);
        hbox4_[1].getChildren().addAll(Boiler4);
        
        hbox[2].getChildren().addAll(Boiler_Name);
        hbox1_[2].getChildren().addAll(tbBoiler1_Name);
        hbox2_[2].getChildren().addAll(tbBoiler2_Name);
        hbox3_[2].getChildren().addAll(tbBoiler3_Name);
        hbox4_[2].getChildren().addAll(tbBoiler4_Name);
        
        hbox[3].getChildren().addAll(Boiler_Manufacturer);
        hbox1_[3].getChildren().addAll(tbBoiler1_Manufacturer);
        hbox2_[3].getChildren().addAll(tbBoiler2_Manufacturer);
        hbox3_[3].getChildren().addAll(tbBoiler3_Manufacturer);
        hbox4_[3].getChildren().addAll(tbBoiler4_Manufacturer);
        
        hbox[4].getChildren().addAll(Boiler_Type);
        hbox1_[4].getChildren().addAll(cbBoiler1_Type);
        hbox2_[4].getChildren().addAll(cbBoiler2_Type);
        hbox3_[4].getChildren().addAll(cbBoiler3_Type);
        hbox4_[4].getChildren().addAll(cbBoiler4_Type);
        
        hbox[5].getChildren().addAll(Boiler_Location);
        hbox1_[5].getChildren().addAll(tbBoiler1_Location);
        hbox2_[5].getChildren().addAll(tbBoiler2_Location);
        hbox3_[5].getChildren().addAll(tbBoiler3_Location);
        hbox4_[5].getChildren().addAll(tbBoiler4_Location);
        
        hbox[6].getChildren().addAll(Boiler_Contract);
        hbox1_[6].getChildren().addAll(tbBoiler1_Contract);
        hbox2_[6].getChildren().addAll(tbBoiler2_Contract);
        hbox3_[6].getChildren().addAll(tbBoiler3_Contract);
        hbox4_[6].getChildren().addAll(tbBoiler4_Contract);
        
        hbox[7].getChildren().addAll(Boiler_Service);
        hbox1_[7].getChildren().addAll(cbBoiler1_Service);
        hbox2_[7].getChildren().addAll(cbBoiler2_Service);
        hbox3_[7].getChildren().addAll(cbBoiler3_Service);
        hbox4_[7].getChildren().addAll(cbBoiler4_Service);
        
        hbox[8].getChildren().addAll(Boiler_DesignPressure);
        hbox1_[8].getChildren().addAll(tbBoiler1_DesignPressure);
        hbox2_[8].getChildren().addAll(tbBoiler2_DesignPressure);
        hbox3_[8].getChildren().addAll(tbBoiler3_DesignPressure);
        hbox4_[8].getChildren().addAll(tbBoiler4_DesignPressure);
        
        hbox[9].getChildren().addAll(Boiler_ReliefSetPoint);
        hbox1_[9].getChildren().addAll(tbBoiler1_Relief);
        hbox2_[9].getChildren().addAll(tbBoiler2_Relief);
        hbox3_[9].getChildren().addAll(tbBoiler3_Relief);
        hbox4_[9].getChildren().addAll(tbBoiler4_Relief);
        
        hbox[10].getChildren().addAll(Boiler_SteamTemp);
        hbox1_[10].getChildren().addAll(tbBoiler1_SteamTemp);
        hbox2_[10].getChildren().addAll(tbBoiler2_SteamTemp);
        hbox3_[10].getChildren().addAll(tbBoiler3_SteamTemp);
        hbox4_[10].getChildren().addAll(tbBoiler4_SteamTemp);
        
        hbox[11].getChildren().addAll(Boiler_InspPrior);
        hbox1_[11].getChildren().addAll(dpBoiler1_PriorInsp);
        hbox2_[11].getChildren().addAll(dpBoiler2_PriorInsp);
        hbox3_[11].getChildren().addAll(dpBoiler3_PriorInsp);
        hbox4_[11].getChildren().addAll(dpBoiler4_PriorInsp);
        
        hbox[12].getChildren().addAll(Boiler_InspLast);
        hbox1_[12].getChildren().addAll(dpBoiler1_LastInsp);
        hbox2_[12].getChildren().addAll(dpBoiler2_LastInsp);
        hbox3_[12].getChildren().addAll(dpBoiler3_LastInsp);
        hbox4_[12].getChildren().addAll(dpBoiler4_LastInsp);
        
        hbox[13].getChildren().addAll(Boiler_InspNext);
        hbox1_[13].getChildren().addAll(dpBoiler1_NextInsp);
        hbox2_[13].getChildren().addAll(dpBoiler2_NextInsp);
        hbox3_[13].getChildren().addAll(dpBoiler3_NextInsp);
        hbox4_[13].getChildren().addAll(dpBoiler4_NextInsp);
        
        hbox[14].getChildren().addAll(Boiler_HydroDate);
        hbox1_[14].getChildren().addAll(dpBoiler1_HydroInsp);
        hbox2_[14].getChildren().addAll(dpBoiler2_HydroInsp);
        hbox3_[14].getChildren().addAll(dpBoiler3_HydroInsp);
        hbox4_[14].getChildren().addAll(dpBoiler4_HydroInsp);
        
        
        hbox99.getChildren().addAll(btSave);
        
        //****************Reading from VP_VesselSpecifics CSV************************
       
         //creating an array from VPInspection seperating each string by commas
        File VPBoilers = new File
        ("src/StoredINFO/VesselParticulars/VP_Boilers.csv");
        String line = "";
        String cvsSplitBy = ",";
        List<String[]> rows = new ArrayList<>();
        
        FileReader fileReader = new FileReader(VPBoilers);
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
            tbBoiler1_Name.setText(lastRow[1]);
            tbBoiler1_Manufacturer.setText(lastRow[2]);
            cbBoiler1_Type.setValue(lastRow[3]);
            tbBoiler1_Location.setText(lastRow[4]);
            tbBoiler1_Contract.setText(lastRow[5]);
            cbBoiler1_Service.setValue(lastRow[6]);
            tbBoiler1_DesignPressure.setText(lastRow[7]);
            tbBoiler1_Relief.setText(lastRow[8]);
            tbBoiler1_SteamTemp.setText(lastRow[9]);
            if(!lastRow[10].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[10], formatter);
                dpBoiler1_PriorInsp.setValue(date);
            }
            if(!lastRow[11].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[11], formatter);
                dpBoiler1_LastInsp.setValue(date);
            }
            if(!lastRow[12].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[12], formatter);
                dpBoiler1_NextInsp.setValue(date);
            }
            if(!lastRow[13].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[13], formatter);
                dpBoiler1_HydroInsp.setValue(date);
            }
            
            
            tbBoiler2_Name.setText(lastRow[14]);
            tbBoiler2_Manufacturer.setText(lastRow[15]);
            cbBoiler2_Type.setValue(lastRow[16]);
            tbBoiler2_Location.setText(lastRow[17]);
            tbBoiler2_Contract.setText(lastRow[18]);
            cbBoiler2_Service.setValue(lastRow[19]);
            tbBoiler2_DesignPressure.setText(lastRow[20]);
            tbBoiler2_Relief.setText(lastRow[21]);
            tbBoiler2_SteamTemp.setText(lastRow[22]);
            if(!lastRow[23].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[23], formatter);
                dpBoiler2_PriorInsp.setValue(date);
            }
            if(!lastRow[24].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[24], formatter);
                dpBoiler2_LastInsp.setValue(date);
            }
            if(!lastRow[25].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[25], formatter);
                dpBoiler2_NextInsp.setValue(date);
            }
            if(!lastRow[26].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[26], formatter);
                dpBoiler2_HydroInsp.setValue(date);
            }
            
            
            tbBoiler3_Name.setText(lastRow[27]);
            tbBoiler3_Manufacturer.setText(lastRow[28]);
            cbBoiler3_Type.setValue(lastRow[29]);
            tbBoiler3_Location.setText(lastRow[30]);
            tbBoiler3_Contract.setText(lastRow[31]);
            cbBoiler3_Service.setValue(lastRow[32]);
            tbBoiler3_DesignPressure.setText(lastRow[33]);
            tbBoiler3_Relief.setText(lastRow[34]);
            tbBoiler3_SteamTemp.setText(lastRow[35]);
            if(!lastRow[36].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[36], formatter);
                dpBoiler3_PriorInsp.setValue(date);
            }
            if(!lastRow[37].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[37], formatter);
                dpBoiler3_LastInsp.setValue(date);
            }
            if(!lastRow[38].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[38], formatter);
                dpBoiler3_NextInsp.setValue(date);
            }
            if(!lastRow[39].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[39], formatter);
                dpBoiler3_HydroInsp.setValue(date);
            }
            
            
            tbBoiler4_Name.setText(lastRow[40]);
            tbBoiler4_Manufacturer.setText(lastRow[41]);
            cbBoiler4_Type.setValue(lastRow[42]);
            tbBoiler4_Location.setText(lastRow[43]);
            tbBoiler4_Contract.setText(lastRow[44]);
            cbBoiler4_Service.setValue(lastRow[45]);
            tbBoiler4_DesignPressure.setText(lastRow[46]);
            tbBoiler4_Relief.setText(lastRow[47]);
            tbBoiler4_SteamTemp.setText(lastRow[48]);
            if(!lastRow[49].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[49], formatter);
                dpBoiler4_PriorInsp.setValue(date);
            }
            if(!lastRow[50].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[50], formatter);
                dpBoiler4_LastInsp.setValue(date);
            }
            if(!lastRow[51].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[51], formatter);
                dpBoiler4_NextInsp.setValue(date);
            }
            if(!lastRow[52].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[52], formatter);
                dpBoiler4_HydroInsp.setValue(date);
            }
            
           
            
        }  
        
        //Action events for save button 
        btSave.setOnAction(e -> 
        {
            //creates a time stamp so we can track when changes are made
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            File VP_VesselSpecifics = new File
                ("src/StoredINFO/VesselParticulars/VP_Boilers.csv");
            try {
                FileWriter fileWriter = new FileWriter(VP_VesselSpecifics, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                //writes to the CSV file
                bufferedWriter.write(timestamp.toString()
                        + "," + tbBoiler1_Name.getText()
                        + "," + tbBoiler1_Manufacturer.getText()
                        + "," + cbBoiler1_Type.getValue()
                        + "," + tbBoiler1_Location.getText()
                        + "," + tbBoiler1_Contract.getText()
                        + "," + cbBoiler1_Service.getValue()
                        + "," + tbBoiler1_DesignPressure.getText()
                        + "," + tbBoiler1_Relief.getText() 
                        + "," + tbBoiler1_SteamTemp.getText() 
                        + "," + dpBoiler1_PriorInsp.getValue()
                        + "," + dpBoiler1_LastInsp.getValue()
                        + "," + dpBoiler1_NextInsp.getValue()
                        + "," + dpBoiler1_HydroInsp.getValue()
                        
                        + "," + tbBoiler2_Name.getText()
                        + "," + tbBoiler2_Manufacturer.getText()
                        + "," + cbBoiler2_Type.getValue()
                        + "," + tbBoiler2_Location.getText()
                        + "," + tbBoiler2_Contract.getText()
                        + "," + cbBoiler2_Service.getValue()
                        + "," + tbBoiler2_DesignPressure.getText()
                        + "," + tbBoiler2_Relief.getText() 
                        + "," + tbBoiler2_SteamTemp.getText() 
                        + "," + dpBoiler2_PriorInsp.getValue()
                        + "," + dpBoiler2_LastInsp.getValue()
                        + "," + dpBoiler2_NextInsp.getValue()
                        + "," + dpBoiler2_HydroInsp.getValue()
                        
                        + "," + tbBoiler3_Name.getText()
                        + "," + tbBoiler3_Manufacturer.getText()
                        + "," + cbBoiler3_Type.getValue()
                        + "," + tbBoiler3_Location.getText()
                        + "," + tbBoiler3_Contract.getText()
                        + "," + cbBoiler3_Service.getValue()
                        + "," + tbBoiler3_DesignPressure.getText()
                        + "," + tbBoiler3_Relief.getText() 
                        + "," + tbBoiler3_SteamTemp.getText() 
                        + "," + dpBoiler3_PriorInsp.getValue()
                        + "," + dpBoiler3_LastInsp.getValue()
                        + "," + dpBoiler3_NextInsp.getValue()
                        + "," + dpBoiler3_HydroInsp.getValue()
                        
                        + "," + tbBoiler4_Name.getText()
                        + "," + tbBoiler4_Manufacturer.getText()
                        + "," + cbBoiler4_Type.getValue()
                        + "," + tbBoiler4_Location.getText()
                        + "," + tbBoiler4_Contract.getText()
                        + "," + cbBoiler4_Service.getValue()
                        + "," + tbBoiler4_DesignPressure.getText()
                        + "," + tbBoiler4_Relief.getText() 
                        + "," + tbBoiler4_SteamTemp.getText() 
                        + "," + dpBoiler4_PriorInsp.getValue()
                        + "," + dpBoiler4_LastInsp.getValue()
                        + "," + dpBoiler4_NextInsp.getValue()
                        + "," + dpBoiler4_HydroInsp.getValue()
                                         
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
