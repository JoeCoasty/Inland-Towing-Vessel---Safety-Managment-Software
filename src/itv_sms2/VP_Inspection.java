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
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * @author Joseph Schwierking
 * 
 * This program is used to display the inspections tab under the 
 * vessel particulars menu.
 */
public class VP_Inspection 
{
     
    public static void VP_InspectionPressed(BorderPane pane, GridPane forms) throws FileNotFoundException
    {
        
        /**checks to see if the path and the CSV file VP_Inspection exist.  If not,
         * this class makes it. */
        MakeVP_Inspection.MakingVP_Inspection();
        
        //initializing path for VP_Details.csv
        File VPInspection = new File
        ("src/StoredINFO/VesselParticulars/VP_Inspection.csv");
        
        //build HBox to place inside GridPane
        HBox[] hbox = new HBox[14];
        for(int i = 0; i<hbox.length; i++)
        {
            hbox[i] = new HBox();
            forms.add(hbox[i], 0, i);
            hbox[i].setPadding(new Insets(0,0,10,0));
        }

        
        HBox hbox99 = new HBox();//for save button
        forms.add(hbox99, 0, 99);
               
        
        //build out labels for each user input
        Label COI_Dates = new Label("   COI Dates:  ");
        Label COI_Issued = new Label("   COI Issued:");
        Label COI_Expires = new Label("   COI Expires:");
        Label fresh_salt = new Label("   Fresh or salt water route:  ");
        Label dryDock = new Label("   Credit Dry Docks: ");
        Label dryDockPrior= new Label("   Prior before last:");
        Label dryDockLast= new Label("   Last:");
        Label dryDockNext = new Label("   Next:");
        Label internalStructureExam = new Label
        ("   Credit Internal Structural Exams:");
        Label internalStructureExam_Prior= new Label("   Prior before last:");
        Label internalStructureExam_Last = new Label("   Last:");
        Label internalStructureExam_Next = new Label("   Next:");
        Label survey = new Label("   Surveys:  ");
        Label surveyPrior= new Label("   Prior before last:");
        Label surveyLast= new Label("   Last:");
        Label surveyNext = new Label("   Next:");
        Label midPeroidAdt = new Label("   Mid Peroid Management Audits:  ");
        Label midPeroidAdtPrior= new Label("   Prior before last:");
        Label midPeroidAdtLast= new Label("   Last:");
        Label midPeroidAdtNext = new Label("   Next:");
        Label managementAdt = new Label("   Management Audits:  ");
        Label managementAdtPrior= new Label("   Prior before last:");
        Label managementAdtLast= new Label("   Last:");
        Label managementAdtNext = new Label("   Next:");
        Label vesselAdt = new Label("   Vessel Audits:  ");
        Label vesselAdtPrior= new Label("   Prior before last:");
        Label vesselAdtLast= new Label("   Last:");
        Label vesselAdtNext = new Label("   Next:");
        
                                
        //build check boxes for fresh or salt water
        CheckBox cbFresh = new CheckBox("Frest Water  ");
        CheckBox cbSalt = new CheckBox("Salt Water  ");
        
        //build check boxes for internal/external survey program
        CheckBox cbInternal = new CheckBox("Internal  ");
        CheckBox cbExternal = new CheckBox("External  ");
        
        //build datepickers for inspection dates
                
        DatePicker issueCOI = new DatePicker();
        DatePicker expireCOI = new DatePicker();
        
        DatePicker ddPrior = new DatePicker();
        DatePicker ddLast = new DatePicker();
        DatePicker ddNext = new DatePicker();
        
        DatePicker isePrior = new DatePicker();
        DatePicker iseLast = new DatePicker();
        DatePicker iseNext = new DatePicker();
        
        DatePicker survPrior = new DatePicker();
        DatePicker survLast = new DatePicker();
        DatePicker survNext = new DatePicker();
        
        DatePicker midPeroidPrior = new DatePicker();
        DatePicker midPeroidLast = new DatePicker();
        DatePicker midPeroidNext = new DatePicker();
        
        DatePicker mangtAdtPrior = new DatePicker();
        DatePicker mangtAdtLast = new DatePicker();
        DatePicker mangtAdtNext = new DatePicker();
        
        DatePicker vslAdtPrior = new DatePicker();
        DatePicker vslAdtLast = new DatePicker();
        DatePicker vslAdtNext = new DatePicker();
        
        //build save button
        Button btSave = new Button("Save");
        
        //move btSave button to the right of the page
        hbox99.setAlignment(Pos.BOTTOM_RIGHT);
        
        //place labels and textfields into hboxes
        hbox[0].getChildren().addAll(COI_Dates, COI_Issued, issueCOI, COI_Expires, expireCOI);
        hbox[1].getChildren().addAll(fresh_salt, cbFresh, cbSalt);
        hbox[2].getChildren().addAll(dryDock);
        hbox[3].getChildren().addAll(dryDockPrior, ddPrior, dryDockLast, ddLast, 
                dryDockNext, ddNext);
        hbox[4].getChildren().addAll(internalStructureExam);
        hbox[5].getChildren().addAll(internalStructureExam_Prior, isePrior, 
                internalStructureExam_Last, iseLast, internalStructureExam_Next,
                iseNext);
        
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
        if(lastRow[1].equals("true"))
        {
            hbox[6].getChildren().addAll(survey, cbInternal, cbExternal);
            hbox[8].getChildren().add(midPeroidAdt);
            hbox[9].getChildren().addAll(midPeroidAdtPrior, midPeroidPrior, 
                    midPeroidAdtLast, midPeroidLast, midPeroidAdtNext, 
                    midPeroidNext);
            hbox[10].getChildren().add(managementAdt);
            hbox[11].getChildren().addAll(managementAdtPrior, mangtAdtPrior, 
                    managementAdtLast, mangtAdtLast, managementAdtNext, 
                    mangtAdtNext);
            hbox[12].getChildren().add(vesselAdt);
            hbox[13].getChildren().addAll(vesselAdtPrior, vslAdtPrior, 
                    vesselAdtLast, vslAdtLast, vesselAdtNext, 
                    vslAdtNext);
        }
        else
        {
            hbox[6].getChildren().add(survey);
        }
        
        
        hbox[7].getChildren().addAll(surveyPrior, survPrior, surveyLast, 
                survLast, surveyNext, survNext);
               
        try 
        {    
            br.close();
        }
        catch (IOException ex) 
        {
            Logger.getLogger(VP_Inspection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        hbox99.getChildren().add(btSave);
        
                
        //****************Reading from VP_Inspection CSV************************
        
         //creating an array from VPInspection seperating each string by commas
        line = "";
        cvsSplitBy = ",";
        rows = new ArrayList<>();
        
        fileReader = new FileReader(VPInspection);
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
                Logger.getLogger(VP_Inspection.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        //*********************Pulling data from array*************************
        else
        {
             
            if(!lastRow[1].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[1], formatter);
                issueCOI.setValue(date);
            }
            
            if(!lastRow[2].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[2], formatter);
                expireCOI.setValue(date);
            }
            
            
            if(lastRow[3].equals("true"))//If fresh water was selected
            {
                cbFresh.setSelected(true);
            }
            else if(lastRow[4].equals("true"))
            {
                cbSalt.setSelected(true);
            }
            else
            {
                cbFresh.setSelected(false);
                cbSalt.setSelected(false);
            }
            
            if(!lastRow[5].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[5], formatter);
                ddPrior.setValue(date);
            }
            if(!lastRow[6].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[6], formatter);
                ddLast.setValue(date);
            }
            if(!lastRow[7].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[7], formatter);
                ddNext.setValue(date);
            }
            if(!lastRow[8].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[8], formatter);
                isePrior.setValue(date);
            }
            if(!lastRow[9].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[9], formatter);
                iseLast.setValue(date);
            }
            if(!lastRow[10].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[10], formatter);
                iseNext.setValue(date);
            }
            
            if(lastRow[11].equals("true"))//If fresh water was selected
            {
                cbInternal.setSelected(true);
            }
            else if(lastRow[12].equals("true"))
            {
                cbExternal.setSelected(true);
            }
            else
            {
                cbInternal.setSelected(false);
                cbExternal.setSelected(false);
            }
            if(!lastRow[13].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[13], formatter);
                survPrior.setValue(date);
            }
            if(!lastRow[14].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[14], formatter);
                survLast.setValue(date);
            }
            if(!lastRow[15].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[15], formatter);
                survNext.setValue(date);
            }
            if(!lastRow[16].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[16], formatter);
                midPeroidPrior.setValue(date);
            }
            if(!lastRow[17].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[17], formatter);
                midPeroidLast.setValue(date);
            }
            if(!lastRow[18].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[18], formatter);
                midPeroidNext.setValue(date);
            }
            if(!lastRow[19].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[19], formatter);
                mangtAdtPrior.setValue(date);
            }
            if(!lastRow[20].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[20], formatter);
                mangtAdtLast.setValue(date);
            }
            if(!lastRow[21].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[21], formatter);
                mangtAdtNext.setValue(date);
            }
            if(!lastRow[22].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[22], formatter);
                vslAdtPrior.setValue(date);
            }
            if(!lastRow[23].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[23], formatter);
                vslAdtLast.setValue(date);
            }
            if(!lastRow[24].equals("null"))
            {
                LocalDate date = LocalDate.parse(lastRow[24], formatter);
                vslAdtNext.setValue(date);
            }
            
            
        }
        
        //***************Action events for checkboxes selected****************       
        
        //Fresh water checkbox is selected
        cbFresh.setOnAction(e -> 
         {
            System.out.println("Fresh Checked");

             if (cbFresh.isSelected()==true) 
            {
                cbSalt.setSelected(false);//unchecks Salt 
            } 
        });
         
        //Salt checkbox is selected
         cbSalt.setOnAction(e -> 
         {
            System.out.println("Salt Checked");

             if (cbSalt.isSelected()==true) 
             {
                 cbFresh.setSelected(false);//unchecks fresh
                 
             } 
                 });
         //Internal checkbox is selected
        cbInternal.setOnAction(e -> 
         {
            System.out.println("Internal Checked");

             if (cbInternal.isSelected()==true) 
            {
                cbExternal.setSelected(false);//unchecks external 
            } 
        });
         
        //External checkbox is selected
         cbExternal.setOnAction(e -> 
         {
            System.out.println("External Checked");

             if (cbExternal.isSelected()==true) 
             {
                 cbInternal.setSelected(false);//unchecks Internal
                 
             } 
                 });

        //Action events for save button 
        btSave.setOnAction(e -> 
        {
            
            FileWriter fileWriter;
            try {
                //creates a time stamp so we can track when changes are made
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());

                fileWriter = new FileWriter(VPInspection, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                //writes to the CSV file
                bufferedWriter.write(timestamp.toString() + "," 
                        + issueCOI.getValue() + "," + expireCOI.getValue() 
                        + "," + cbFresh.isSelected() + "," + cbSalt.isSelected() 
                        + "," + ddPrior.getValue() + "," + ddLast.getValue() 
                        + "," + ddNext.getValue() + "," + isePrior.getValue()
                        + "," + iseLast.getValue() + "," + iseNext.getValue() 
                        + "," + cbInternal.isSelected() + "," 
                        + cbExternal.isSelected() + "," + survPrior.getValue() 
                        + "," + survLast.getValue() + "," + survNext.getValue()
                        + "," + midPeroidPrior.getValue() + "," 
                        + midPeroidLast.getValue() + "," 
                        + midPeroidNext.getValue() + "," 
                        + mangtAdtPrior.getValue() + "," 
                        + mangtAdtLast.getValue() + "," 
                        + mangtAdtNext.getValue() + "," 
                        + vslAdtPrior.getValue() + "," 
                        + vslAdtLast.getValue() + "," 
                        + vslAdtNext.getValue() + "," + "\n");

                bufferedWriter.close(); 
                System.out.println("Saved to src/StoredINFO/VesselParticulars");
            } catch (IOException ex) {
                System.err.println("File did not write to "
                        + "src/StoredINFO/VesselParticulars");
            }
            
            
        });
       
    }
    
}
