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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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
public class VP_Routes 
{

    public static void VP_RoutesPressed(BorderPane pane, GridPane forms) throws FileNotFoundException
    {
         /**checks to see if the path and the CSV file VP_Inspection exist.  If not,
         * this class makes it. */
        MakeVP_Routes.MakingVP_Routes();
        
        //initializing path for VP_Details.csv
        File VP_Routes = new File
        ("src/StoredINFO/VesselParticulars/VP_Routes.csv");
        
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
            hbox[i].setAlignment(Pos.CENTER_LEFT);
            hbox1_[i].setAlignment(Pos.CENTER_LEFT);
            hbox2_[i].setAlignment(Pos.CENTER);
            hbox3_[i].setAlignment(Pos.CENTER);
        }

        HBox hbox99 = new HBox();//for save button
        forms.add(hbox99, 3, 99);
        hbox99.setPadding(new Insets(0,0,10,0));
        
        //build out labels for each user input
              
        Label title = new Label("Route Discription");
        Label route = new Label("Route");
        Label routeDescription = new Label("Discription of route");
        Label hullInspection = new Label("Hull Inspection Interval");
        
        CheckBox cbRivers = new CheckBox("Rivers");
        CheckBox cbLBS = new CheckBox("Lakes, Bays & Sounds");
        CheckBox cbLtdCoastwise = new CheckBox("Limmited Coastwise");
        CheckBox cbCoastwise = new CheckBox("Coastwise");
        CheckBox cbGreatLakes = new CheckBox("Great Lakes");
        CheckBox cbOceans = new CheckBox("Ocean");
        
        CheckBox cbFreshWater = new CheckBox("Fresh Water (Once every 5 years.)   ");
        CheckBox cbSaltWater = new CheckBox("Salt Water(Twice every 5 years.)   ");
        
        TextArea taRivers = new TextArea("River, canal, or other similar body "
                + "of water designated by the cognizant OCMI.");
        TextArea taLBS = new TextArea("Lake other than the Great Lakes, a bay,"
                + " a sound, such other similar waters as may be \ndesignated by "
                + "the cognizant Coast Guard District Commander.");
        TextArea taLtdCoastwise = new TextArea("Not more than 20 nautical miles"
                + " from a harbor of safe refuge, as defined in this section.");
        TextArea taCoastwise = new TextArea("Not more than 20 nautical miles "
                + "offshore on any ocean, Gulf of Mexico, Caribbean Sea,\n Bering"
                + " Sea, Gulf of Alaska; or such other similar waters as may be"
                + " designated by a \nCoast Guard District Commander.");
        TextArea taGreatLakes = new TextArea("Route on the waters of any of the "
                + "Great Lakes and of the St. Lawrence River as far east \nas a "
                + "straight line drawn from Cap de Rosiers to West Point, "
                + "Anticosti Island, and west \nof a line along the 63rd meridian"
                + " from Anticosti Island to the north shore of the \nSt. Lawrence"
                +" River.");
        TextArea taOceans = new TextArea("More than 20 nautical miles offshore "
                + "on any of the following waters; any ocean, Gulf \nof Mexico, "
                + "Caribbean Sea, Bering Sea, Gulf of Alaska, or other similar "
                + "waters as may be \ndesignated by the cognizant Coast Guard "
                + "District Commander");
        taRivers.setMaxHeight(10);
        taLBS.setMaxHeight(110);
        taLtdCoastwise.setMaxHeight(50);
        taCoastwise.setMaxHeight(125);
        taGreatLakes.setMaxHeight(145);
        taOceans.setMaxHeight(125);
        
        taRivers.setEditable(false);
        taLBS.setEditable(false);
        taLtdCoastwise.setEditable(false);
        taCoastwise.setEditable(false);
        taGreatLakes.setEditable(false);
        taOceans.setEditable(false);
        //build save button
        Button btSave = new Button("Save");
        
        //move btSave button to the right of the page
        hbox99.setAlignment(Pos.BOTTOM_RIGHT);
        
        //place labels and textfields into hboxes
        //hbox[0].getChildren().addAll(title);
        
        hbox[1].getChildren().addAll(route);
        hbox2_[1].getChildren().addAll(routeDescription);
        
        hbox[2].getChildren().addAll(cbRivers);
        hbox2_[2].getChildren().addAll(taRivers);
        
        hbox[3].getChildren().addAll(cbLBS);
        hbox2_[3].getChildren().addAll(taLBS);
        
        hbox[4].getChildren().addAll(cbLtdCoastwise);
        hbox2_[4].getChildren().addAll(taLtdCoastwise);
        
        hbox[5].getChildren().addAll(cbCoastwise);
        hbox2_[5].getChildren().addAll(taCoastwise);
        
        hbox[6].getChildren().addAll(cbGreatLakes);
        hbox2_[6].getChildren().addAll(taGreatLakes);
        
        hbox[7].getChildren().addAll(cbOceans);
        hbox2_[7].getChildren().addAll(taOceans);
        
        hbox[8].getChildren().addAll(hullInspection);
        hbox2_[8].getChildren().addAll(cbFreshWater, cbSaltWater);
        
        hbox99.getChildren().addAll(btSave);
        
        //****************Reading from VP_VesselSpecifics CSV************************
       
         //creating an array from VPInspection seperating each string by commas
        File VPRoutes = new File
        ("src/StoredINFO/VesselParticulars/VP_Routes.csv");
        String line = "";
        String cvsSplitBy = ",";
        List<String[]> rows = new ArrayList<>();
        
        FileReader fileReader = new FileReader(VPRoutes);
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
                        
            if(lastRow[1].equals("true"))//If TSMS was selected
            {
                cbRivers.setSelected(true);
                cbLBS.setSelected(false);
                cbLtdCoastwise.setSelected(false);
                cbCoastwise.setSelected(false);
                cbGreatLakes.setSelected(false);
                cbOceans.setSelected(false);
            }
            else if(lastRow[2].equals("true"))//If TSMS was selected
            {
                cbRivers.setSelected(false);
                cbLBS.setSelected(true);
                cbLtdCoastwise.setSelected(false);
                cbCoastwise.setSelected(false);
                cbGreatLakes.setSelected(false);
                cbOceans.setSelected(false);
            }
            else if(lastRow[3].equals("true"))//If TSMS was selected
            {
                cbRivers.setSelected(false);
                cbLBS.setSelected(false);
                cbLtdCoastwise.setSelected(true);
                cbCoastwise.setSelected(false);
                cbGreatLakes.setSelected(false);
                cbOceans.setSelected(false);
            }
            else if(lastRow[4].equals("true"))//If TSMS was selected
            {
                cbRivers.setSelected(false);
                cbLBS.setSelected(false);
                cbLtdCoastwise.setSelected(false);
                cbCoastwise.setSelected(true);
                cbGreatLakes.setSelected(false);
                cbOceans.setSelected(false);
            }
            else if(lastRow[5].equals("true"))//If TSMS was selected
            {
                cbRivers.setSelected(false);
                cbLBS.setSelected(false);
                cbLtdCoastwise.setSelected(false);
                cbCoastwise.setSelected(false);
                cbGreatLakes.setSelected(true);
                cbOceans.setSelected(false);
            }
            else if(lastRow[6].equals("true"))//If TSMS was selected
            {
                cbRivers.setSelected(false);
                cbLBS.setSelected(false);
                cbLtdCoastwise.setSelected(false);
                cbCoastwise.setSelected(false);
                cbGreatLakes.setSelected(false);
                cbOceans.setSelected(true);
            }
            else
            {
                cbRivers.setSelected(false);
                cbLBS.setSelected(false);
                cbLtdCoastwise.setSelected(false);
                cbCoastwise.setSelected(false);
                cbGreatLakes.setSelected(false);
                cbOceans.setSelected(false);
            }
            
            if (lastRow[7].equals("true"))
            {
                cbFreshWater.setSelected(true);
                cbSaltWater.setSelected(false);
            }
            else if (lastRow[8].equals("true"))
            {
                cbFreshWater.setSelected(false);
                cbSaltWater.setSelected(true);
            }
            else
            {
                cbFreshWater.setSelected(false);
                cbSaltWater.setSelected(false);
            }
        
        }  
        
        //Action events for save button 
        cbRivers.setOnAction(e ->
        {    
                cbLBS.setSelected(false);
                cbLtdCoastwise.setSelected(false);
                cbCoastwise.setSelected(false);
                cbGreatLakes.setSelected(false);
                cbOceans.setSelected(false);
        });
        
        cbLBS.setOnAction(e ->
        {    
                cbRivers.setSelected(false);
                cbLtdCoastwise.setSelected(false);
                cbCoastwise.setSelected(false);
                cbGreatLakes.setSelected(false);
                cbOceans.setSelected(false);
        });
        
        cbLtdCoastwise.setOnAction(e ->
        {    
                cbRivers.setSelected(false);
                cbLBS.setSelected(false);
                cbCoastwise.setSelected(false);
                cbGreatLakes.setSelected(false);
                cbOceans.setSelected(false);
        });
        
        cbCoastwise.setOnAction(e ->
        {    
                cbRivers.setSelected(false);
                cbLBS.setSelected(false);
                cbLtdCoastwise.setSelected(false);
                cbGreatLakes.setSelected(false);
                cbOceans.setSelected(false);
        });
        
        cbGreatLakes.setOnAction(e ->
        {    
                cbRivers.setSelected(false);
                cbLBS.setSelected(false);
                cbLtdCoastwise.setSelected(false);
                cbCoastwise.setSelected(false);
                cbOceans.setSelected(false);
        });
        
        cbOceans.setOnAction(e ->
        {    
                cbRivers.setSelected(false);
                cbLBS.setSelected(false);
                cbLtdCoastwise.setSelected(false);
                cbCoastwise.setSelected(false);
                cbGreatLakes.setSelected(false);
        });
        
        cbFreshWater.setOnAction(e ->
        {    
                cbSaltWater.setSelected(false);
        });
        
        cbSaltWater.setOnAction(e ->
        {    
                cbFreshWater.setSelected(false);
        });
        
        btSave.setOnAction(e -> 
        {
            //creates a time stamp so we can track when changes are made
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            File VP_VesselSpecifics = new File
                ("src/StoredINFO/VesselParticulars/VP_Routes.csv");
            try {
                FileWriter fileWriter = new FileWriter(VP_VesselSpecifics, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                //writes to the CSV file
                bufferedWriter.write(timestamp.toString()
                        + "," + cbRivers.isSelected()
                        + "," + cbLBS.isSelected()
                        + "," + cbLtdCoastwise.isSelected() 
                        + "," + cbCoastwise.isSelected()
                        + "," + cbGreatLakes.isSelected()
                        + "," + cbOceans.isSelected()
                        
                        + "," + cbFreshWater.isSelected()
                        + "," + cbSaltWater.isSelected()
                        
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
