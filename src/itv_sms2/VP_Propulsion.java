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
public class VP_Propulsion 
{

    public static void VP_PropulsionPressed(BorderPane pane, GridPane forms) throws FileNotFoundException
    {
         /**checks to see if the path and the CSV file VP_Inspection exist.  If not,
         * this class makes it. */
        MakeVP_Propulsion.MakingVP_Propulsion();
        
        //initializing path for VP_Details.csv
        File VP_Propulsion = new File
        ("src/StoredINFO/VesselParticulars/VP_Propulsion.csv");
        
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
        Label portMDE = new Label("Port Main Engine:  ");
        Label stbdMDE = new Label("STBD Main Engine:");
        Label miscMDE1 = new Label("Misc. Main Engine 1:");
        Label miscMDE2 = new Label("Misc. Main Engine 2:");
        Label portRG = new Label("Port Redulction Gear:");
        Label stbdRD = new Label("STBD Reduction Gear:");
        Label miscRG1 = new Label("Misc. Reduction Gear 1:");
        Label miscRG2 = new Label("Misc. Reduction Gear 2:");
        Label numberShaft = new Label("Number of shafts:");
        Label driveType = new Label("Drive type:");
        Label make = new Label("Make/Model");
        Label make2 = new Label("Make/Model");
        Label hoursePower = new Label("Horsepower/KW");
        Label reduction = new Label("Reduction Ratio");
        Label installed = new Label("Date Installed/Last Overhaul");
        Label installed2 = new Label("Date Installed/Last Overhaul");
        Label shafts = new Label("Number of Shafts");
        Label drive = new Label("Drive Type");
        
        
        //build text fields
        TextField tfPortMDE_Make = new TextField();
        TextField tfStbdMDE_Make = new TextField();
        TextField tfMiscMDE1_Make = new TextField();
        TextField tfMiscMDE2_Make = new TextField();
        TextField tfPortRG_Make = new TextField();
        TextField tfStbdRG_Make = new TextField();
        TextField tfMiscRG1_Make = new TextField();
        TextField tfMiscRG2_Make = new TextField();
        TextField tfPortMDE_HP = new TextField();
        TextField tfStbdMDE_HP = new TextField();
        TextField tfMiscMDE1_HP = new TextField();
        TextField tfMiscMDE2_HP = new TextField();
        TextField tfPortRG_Ratio = new TextField();
        TextField tfStbdRG_Ratio = new TextField();
        TextField tfMiscRG1_Ratio = new TextField();
        TextField tfMiscRG2_Ratio = new TextField();
        TextField tfShafts = new TextField();
        
        //build datepickers for installation dates
        DatePicker dpPortMDE_Install = new DatePicker();
        DatePicker dpStbdMDE_Install = new DatePicker();
        DatePicker dpMiscMDE1_Install = new DatePicker();
        DatePicker dpMiscMDE2_Install = new DatePicker();
        DatePicker dpPortRG_Install = new DatePicker();
        DatePicker dpStbdRG_Install = new DatePicker();
        DatePicker dpMiscRG1_Install = new DatePicker();
        DatePicker dpMiscRG2_Install = new DatePicker();
        
        //build choise box for drive system type
        ChoiceBox cbDrive = new ChoiceBox(FXCollections.observableArrayList
        ("Direct Drive", "Reduction", "Diesel Electric", "Z Drive"));
        
        //build save button
        Button btSave = new Button("Save");
        
        //move btSave button to the right of the page
        hbox99.setAlignment(Pos.BOTTOM_RIGHT);
        
        //place labels and textfields into hboxes
        hbox[0].getChildren().addAll();
        hbox[1].getChildren().addAll(title);
        hbox1_[1].getChildren().addAll(make);
        hbox2_[1].getChildren().addAll(hoursePower);
        hbox3_[1].getChildren().addAll(installed); 
        hbox[2].getChildren().addAll(portMDE);
        hbox1_[2].getChildren().addAll(tfPortMDE_Make);
        hbox2_[2].getChildren().addAll(tfPortMDE_HP);
        hbox3_[2].getChildren().addAll(dpPortMDE_Install);
        hbox[3].getChildren().addAll(stbdMDE);
        hbox1_[3].getChildren().addAll(tfStbdMDE_Make);
        hbox2_[3].getChildren().addAll(tfStbdMDE_HP);
        hbox3_[3].getChildren().addAll(dpStbdMDE_Install);
        hbox[4].getChildren().addAll(miscMDE1);
        hbox1_[4].getChildren().addAll(tfMiscMDE1_Make);
        hbox2_[4].getChildren().addAll(tfMiscMDE1_HP);
        hbox3_[4].getChildren().addAll(dpMiscMDE1_Install);
        hbox[5].getChildren().addAll(miscMDE2);
        hbox1_[5].getChildren().addAll(tfMiscMDE2_Make);
        hbox2_[5].getChildren().addAll(tfMiscMDE2_HP);
        hbox3_[5].getChildren().addAll(dpMiscMDE2_Install);
        hbox[6].getChildren().addAll();
        hbox1_[6].getChildren().addAll(make2);
        hbox2_[6].getChildren().addAll(reduction);
        hbox3_[6].getChildren().addAll(installed2);
        hbox[7].getChildren().addAll(portRG);
        hbox1_[7].getChildren().addAll(tfPortRG_Make);
        hbox2_[7].getChildren().addAll(tfPortRG_Ratio);
        hbox3_[7].getChildren().addAll(dpPortRG_Install);
        hbox[8].getChildren().addAll(stbdRD);
        hbox1_[8].getChildren().addAll(tfStbdRG_Make);
        hbox2_[8].getChildren().addAll(tfStbdRG_Ratio);
        hbox3_[8].getChildren().addAll(dpStbdRG_Install);
        hbox[9].getChildren().addAll(miscRG1);
        hbox1_[9].getChildren().addAll(tfMiscRG1_Make);
        hbox2_[9].getChildren().addAll(tfMiscRG1_Ratio);
        hbox3_[9].getChildren().addAll(dpMiscRG1_Install);
        hbox[10].getChildren().addAll(miscRG2);
        hbox1_[10].getChildren().addAll(tfMiscRG2_Make);
        hbox2_[10].getChildren().addAll(tfMiscRG2_Ratio);
        hbox3_[10].getChildren().addAll(dpMiscRG2_Install);
        hbox[11].getChildren().addAll(shafts);
        hbox1_[11].getChildren().addAll(tfShafts);
        hbox[12].getChildren().addAll(drive);
        hbox1_[12].getChildren().addAll(cbDrive);
        hbox99.getChildren().addAll(btSave);
        
        //****************Reading from VP_VesselSpecifics CSV************************
       
         //creating an array from VPInspection seperating each string by commas
        File VPPropulsion = new File
        ("src/StoredINFO/VesselParticulars/VP_Propulsion.csv");
        String line = "";
        String cvsSplitBy = ",";
        List<String[]> rows = new ArrayList<>();
        
        FileReader fileReader = new FileReader(VPPropulsion);
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
            tfPortMDE_Make.setText(lastRow[1]);
            tfPortMDE_HP.setText(lastRow[2]);
            if(!lastRow[3].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[3], formatter);
                dpPortMDE_Install.setValue(date);
            }
            
            tfStbdMDE_Make.setText(lastRow[4]);
            tfStbdMDE_HP.setText(lastRow[5]);
            if(!lastRow[6].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[6], formatter);
                dpStbdMDE_Install.setValue(date);
            }
            
            tfMiscMDE1_Make.setText(lastRow[7]);
            tfMiscMDE1_HP.setText(lastRow[8]);
            if(!lastRow[9].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[9], formatter);
                dpMiscMDE1_Install.setValue(date);
            }
            
            tfMiscMDE2_Make.setText(lastRow[10]);
            tfMiscMDE2_HP.setText(lastRow[11]);
            if(!lastRow[12].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[12], formatter);
                dpMiscMDE2_Install.setValue(date);
            }
            
            tfPortRG_Make.setText(lastRow[13]);
            tfPortRG_Ratio.setText(lastRow[14]);
            if(!lastRow[15].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[15], formatter);
                dpPortRG_Install.setValue(date);
            }
            
            tfStbdRG_Make.setText(lastRow[16]);
            tfStbdRG_Ratio.setText(lastRow[17]);
            if(!lastRow[18].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[18], formatter);
                dpStbdRG_Install.setValue(date);
            }
            
            tfMiscRG1_Make.setText(lastRow[19]);
            tfMiscRG1_Ratio.setText(lastRow[20]);
            if(!lastRow[21].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[21], formatter);
                dpMiscRG1_Install.setValue(date);
            }
            
            tfMiscRG2_Make.setText(lastRow[22]);
            tfMiscRG2_Ratio.setText(lastRow[23]);
            if(!lastRow[24].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[24], formatter);
                dpMiscRG2_Install.setValue(date);
            }
            
            tfShafts.setText(lastRow[25]);
            cbDrive.setValue(lastRow[26]);
            
            
        }  
        
        //Action events for save button 
        btSave.setOnAction(e -> 
        {
            //creates a time stamp so we can track when changes are made
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            File VP_VesselSpecifics = new File
                ("src/StoredINFO/VesselParticulars/VP_Propulsion.csv");
            try {
                FileWriter fileWriter = new FileWriter(VP_VesselSpecifics, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                //writes to the CSV file
                bufferedWriter.write(timestamp.toString()
                        + "," + tfPortMDE_Make.getText()+ "," + tfPortMDE_HP.getText()
                         + "," + dpPortMDE_Install.getValue() 
                        
                        + "," + tfStbdMDE_Make.getText()+ "," + tfStbdMDE_HP.getText()
                         + "," + dpStbdMDE_Install.getValue()
                        
                        + "," + tfMiscMDE1_Make.getText()+ "," + tfMiscMDE1_HP.getText()
                         + "," + dpMiscMDE1_Install.getValue()
                        
                        + "," + tfMiscMDE2_Make.getText()+ "," + tfMiscMDE2_HP.getText()
                         + "," + dpMiscMDE2_Install.getValue()
                        
                         + "," + tfPortRG_Make.getText()+ "," + tfPortRG_Ratio.getText()
                         + "," + dpPortRG_Install.getValue() 
                        
                        + "," + tfStbdRG_Make.getText()+ "," + tfStbdRG_Ratio.getText()
                         + "," + dpStbdRG_Install.getValue()
                        
                        + "," + tfMiscRG1_Make.getText()+ "," + tfMiscRG1_Ratio.getText()
                         + "," + dpMiscRG1_Install.getValue()
                        
                        + "," + tfMiscRG2_Make.getText()+ "," + tfMiscRG2_Ratio.getText()
                         + "," + dpMiscRG2_Install.getValue()
                        
                        + "," + tfShafts.getText() + "," + cbDrive.getValue()
                        
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
