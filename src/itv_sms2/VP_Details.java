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
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * @author Joseph Schwierking
 * 
 * This program is used to display the details tab under the vessel particulars 
 * menu.
 */
public class VP_Details 
{
    
    public static void VP_DetailsPressed(BorderPane pane, GridPane forms) throws IOException
    {
        /**checks to see if the path and the csv file VP_Details exist.  If not,
         * this class makes it. */
        MakeVP_Details.MakingVP_Deatils();
        
        //initializing path for VP_Details.csv
        File VPDetails = new File
        ("src/StoredINFO/VesselParticulars/VP_Details.csv");
        
        //****************Building the VP_Details GUI*************************
        
        //Button to btSave entered data
        Button btSave = new Button("Save");
        
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
            hbox[i].setAlignment(Pos.CENTER_RIGHT);
            hbox1_[i].setAlignment(Pos.CENTER_LEFT);
            hbox2_[i].setAlignment(Pos.CENTER_RIGHT);
            hbox3_[i].setAlignment(Pos.CENTER_LEFT);
        }

        HBox hbox99 = new HBox();//for save button
        forms.add(hbox99, 3, 99);
        hbox99.setPadding(new Insets(0,0,10,0));
        
        //build out labels for each text box
        Label userFees = new Label("  User Fees Last Paid:");
        Label option = new Label("   Regulatory Option: ");
        Label vesselName = new Label("   Vessel Name:");
        Label officialNumber = new Label("   Official Number:");
        Label callSign = new Label("   Call Sign:");
        Label hailingPort = new Label("   Hailing Port:");
        Label placeBuilt = new Label("   Place built:");
        Label MMSI = new Label("   MMSI #:");
        Label vesselPhone = new Label("   Vessel Phone #:");
        Label vesselEmail = new Label("   Vessel Email:");
        Label owner = new Label("   Owner:");
        Label ownerAddress = new Label("   Owner Address:");
        Label operator = new Label("   Operator:");
        Label operatorAddress = new Label("   Operator Address:");
        Label TPO = new Label("   TPO:");
        Label TPO_POC = new Label("   TPO POC:");
        Label TPO_Address = new Label("   TPO Email Address:");
        Label TPO_POC_Phone = new Label("   POC Phone #:");
        Label charterer = new Label("   Charterer:");
        Label specialConsiderations = new Label("   Special Considerations:");
        Label alternateArrangements = new Label("   Alternate Arrangement:");
                
        //build check boxes for inspection option
        CheckBox cbTSMS = new CheckBox("TSMS  ");
        CheckBox cbUSCG = new CheckBox("USCG  ");
                
        //build datepicker for user fees last paid
        DatePicker usrFees = new DatePicker();

        //build out text boxes for user to input info        
        TextField vslName = new TextField("Vessel Name");
        TextField offNumber = new TextField("Official Number");
        TextField cSign = new TextField("Call Sign");
        TextField hPort = new TextField("City");
        TextField plcBuilt = new TextField("City");
        TextField MMSI_Num = new TextField("MMSI Number");
        TextField vslPhone = new TextField("Vessel Phone");
        TextField vslEmail = new TextField("Vessel Email Address");
        TextField onr = new TextField("Owning Company");
        TextField onrAddress = new TextField("Owning Company Address");
        TextField opr = new TextField("Operating Company");
        TextField oprAddress = new TextField("Operating Company Address");
        TextField tpo = new TextField("Third Party Organization");
        TextField tpo_POC = new TextField("TPO Point of Contact");
        TextField tpo_Address = new TextField("TPO Address");
        TextField tpo_POC_Phone = new TextField("TPO Phone");
        TextField chrtr = new TextField("Charterer");
        TextField splConsiderations = new TextField("Special Considerations");
        TextField altArrangements = new TextField("Alternate Arrangements");
        
        onrAddress.setMinWidth(250);
        oprAddress.setMinWidth(250);
        tpo_Address.setMinWidth(250);
        chrtr.setMinWidth(200);
        splConsiderations.setMinWidth(200);       
        altArrangements.setMinWidth(200);
        
        
        //place labels and textfields into hboxes
        hbox[1].getChildren().add(option );
        hbox1_[1].getChildren().addAll(cbTSMS, cbUSCG);
        hbox2_[1].getChildren().add(userFees);
        hbox3_[1].getChildren().add(usrFees);        
       
        hbox[2].getChildren().add(vesselName); 
        hbox1_[2].getChildren().add(vslName);
        hbox2_[2].getChildren().add(officialNumber);
        hbox3_[2].getChildren().add(offNumber);
        
        hbox[3].getChildren().add(callSign); 
        hbox1_[3].getChildren().add(cSign);
        hbox2_[3].getChildren().add(MMSI);
        hbox3_[3].getChildren().add(MMSI_Num);
        
        hbox[4].getChildren().add(hailingPort); 
        hbox1_[4].getChildren().add(hPort);
        hbox2_[4].getChildren().add(placeBuilt);
        hbox3_[4].getChildren().add(plcBuilt);
        
        hbox[5].getChildren().add(vesselPhone); 
        hbox1_[5].getChildren().add(vslPhone);
        hbox2_[5].getChildren().add(vesselEmail);
        hbox3_[5].getChildren().add(vslEmail);
        
        hbox[6].getChildren().add(owner); 
        hbox1_[6].getChildren().add(onr);
        hbox2_[6].getChildren().add(ownerAddress);
        hbox3_[6].getChildren().add(onrAddress);
        
        hbox[7].getChildren().add(operator); 
        hbox1_[7].getChildren().add(opr);
        hbox2_[7].getChildren().add(operatorAddress);
        hbox3_[7].getChildren().add(oprAddress);
        
        hbox[8].getChildren().add(TPO); 
        hbox1_[8].getChildren().add(tpo);
        hbox2_[8].getChildren().add(TPO_POC);
        hbox3_[8].getChildren().add(tpo_POC);
        
        hbox[9].getChildren().add(TPO_POC_Phone); 
        hbox1_[9].getChildren().add(tpo_POC_Phone);
        hbox2_[9].getChildren().add(TPO_Address);
        hbox3_[9].getChildren().add(tpo_Address);
        
        hbox[10].getChildren().add(charterer); 
        hbox1_[10].getChildren().add(chrtr);
        
        hbox[11].getChildren().add(specialConsiderations); 
        hbox1_[11].getChildren().add(splConsiderations);
        
        hbox[12].getChildren().add(alternateArrangements); 
        hbox1_[12].getChildren().add(altArrangements);
        
         
        hbox99.getChildren().add(btSave);
        
        //move btSave button to the right of the page
        hbox99.setAlignment(Pos.BOTTOM_RIGHT);
        
        //*******************Reading from VP_Details***************************
        
        //creating an array from VP_Details seperating each string by commas
        String line = "";
        String cvsSplitBy = ",";
        List<String[]> rows = new ArrayList<>();
        
        FileReader fileReader = new FileReader(VPDetails);
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
        
        /* When the button Details is initially pressed, this IF statement will
        determine if someone has ever entered information into it by checking
        the Time Stamp colum.  If so, this will fill the textfield with the last
        saved information in the CSV file.  If not, this will preset all 
        textfields with the premade text.
        */
              
        if(lastRow[0].equals("Time Stamp"))/* If the first cell of the last row of the csv file says
            Time Stamp, this means no one has saved to the VP_Details yet. */
        {
            System.out.println("No values ever saved in Details before");
            br.close();
        }
        else//all the data prevously saved into VP_Details.csv will be displayed
        {
            //This is a loop that puts commas where the user had placed in text fields
            for(int i = 0; i<lastRow.length; i++)
            {
                lastRow[i] = lastRow[i].replace(";", ",");
            }
            //figuring out TSMS/CG Option check boxes
            if(lastRow[1].equals("true"))//If TSMS was selected
            {
                cbTSMS.setSelected(true);
                cbUSCG.setSelected(false);
                hbox2_[1].setVisible(false);
                hbox3_[1].setVisible(false);
            }
            else if(lastRow[2].equals("true"))
            {
                cbTSMS.setSelected(false);//If USCG was selected
                cbUSCG.setSelected(true);
                hbox2_[1].setVisible(true);
                hbox3_[1].setVisible(true);
                hbox[8].setVisible(false);//hide TPO POC
                hbox1_[8].setVisible(false);//hide TPO POC
                hbox2_[8].setVisible(false);//hide TPO POC
                hbox3_[8].setVisible(false);//hide TPO POC
                hbox[9].setVisible(false);//hide TPO POC
                hbox1_[9].setVisible(false);//hide TPO POC
                hbox2_[9].setVisible(false);//hide TPO POC
                hbox3_[9].setVisible(false);//hide TPO POC
                
            }
            else
            {
                cbTSMS.setSelected(false);//nothing selected(shouldn't happen)
                cbUSCG.setSelected(false);
                hbox2_[1].setVisible(true);
                hbox3_[1].setVisible(true);
            }
            
           //getting date from CSV
           if(!lastRow[3].equals("null"))//if TSMS option this will be null
           {
              DateTimeFormatter formatter = 
                    DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(lastRow[3], formatter);
                usrFees.setValue(date); 
           }
      
            //getting the rest of the entered data
            vslName.setText(lastRow[4]);
            offNumber.setText(lastRow[5]);
            cSign.setText(lastRow[6]);
            hPort.setText(lastRow[7]);
            plcBuilt.setText(lastRow[8]);
            MMSI_Num.setText(lastRow[9]);
            vslPhone.setText(lastRow[10]);
            vslEmail.setText(lastRow[11]);
            onr.setText(lastRow[12]);
            onrAddress.setText(lastRow[13]);
            opr.setText(lastRow[14]);
            oprAddress.setText(lastRow[15]);
            tpo.setText(lastRow[16]);
            tpo_POC.setText(lastRow[17]);
            tpo_POC_Phone.setText(lastRow[18]);
            tpo_Address.setText(lastRow[19]);
            chrtr.setText(lastRow[20]);
            splConsiderations.setText(lastRow[21]);
            altArrangements.setText(lastRow[22]);
            
            System.out.println("Values from previous save added to fields");
            
            br.close();
 
        }
        
        //***************Action events for checkboxes selected****************       
        
        //TSMS checkbox is selected
        cbTSMS.setOnAction(e -> 
         {
            System.out.println("TSMS Checked");

             if (cbTSMS.isSelected()==true) 
            {
                cbUSCG.setSelected(false);                
                hbox2_[1].setVisible(false);//hide user fees
                hbox3_[1].setVisible(false);//hide user fees tf
                hbox[8].setVisible(true);//unhide TPO 
                hbox1_[8].setVisible(true);//unhide TPO tf
                hbox2_[8].setVisible(true);//unhide TPO POC
                hbox3_[8].setVisible(true);//unhide TPO POC tf
                hbox[9].setVisible(true);//unhide TPO phone
                hbox1_[9].setVisible(true);//unhide TPO phone tf
                hbox2_[9].setVisible(true);//unhide TPO address
                hbox3_[9].setVisible(true);//unhide TPO address tf
            } 
        });
         
        //USCG checkbox is selected
         cbUSCG.setOnAction(e -> 
         {
            System.out.println("USCG Checked");

             if (cbUSCG.isSelected()==true) 
             {
                cbTSMS.setSelected(false);//unchecks TSMS
                hbox2_[1].setVisible(true);//unhide user fees 
                hbox3_[1].setVisible(true);//unhide user fees tf
                hbox[8].setVisible(false);//hide TPO
                hbox1_[8].setVisible(false);//hide TPO tf
                hbox2_[8].setVisible(false);//hide TPO POC
                hbox3_[8].setVisible(false);//hide TPO POC tf
                hbox[9].setVisible(false);//hide TPO phone 
                hbox1_[9].setVisible(false);//hide TPO phone tf
                hbox2_[9].setVisible(false);//hide TPO address 
                hbox3_[9].setVisible(false);//hide TPO address tf
             } 
                 });

        //Action events for save button 
        btSave.setOnAction(e -> 
        {
            //This stops the save if a regulatory option has not been selected
            if((cbTSMS.isSelected()==false)&&(cbUSCG.isSelected()==false))
            {
                Label saveErr = new Label("Can not save without selecting\n"
                        + "      a Regulatory Option.");
                //saveErr.setAlignment(Pos.CENTER);
                HBox err1 = new HBox(saveErr);
                err1.setAlignment(Pos.CENTER);
                
                Scene scene = new Scene(err1, 300, 100);
                Stage stage = new Stage();
                
                stage.setScene(scene);
                stage.setTitle("Save error"); 
                stage.show();              
            }
            else//This saves all entered data into the VP_Details file 
            {
                FileWriter fileWriter;
                try {
                    //creates a time stamp so we can track when changes are made
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());

                    fileWriter = new FileWriter(VPDetails, true);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    
                    //this wil remove potential commas from fields to prevent 
                    //incorrect seperation in the CSV file
                    hPort.setText(hPort.getText().replace(",", ";"));
                    plcBuilt.setText(plcBuilt.getText().replace(",", ";"));
                    onrAddress.setText(onrAddress.getText().replace(",", ";"));
                    oprAddress.setText(oprAddress.getText().replace(",", ";"));
                    tpo_Address.setText(tpo_Address.getText().replace(",", ";"));
                    splConsiderations.setText(splConsiderations.getText().replace(",", ";"));
                    altArrangements.setText(altArrangements.getText().replace(",", ";"));
                    
                    //writes to the CSV file
                    bufferedWriter.write(timestamp.toString() + "," 
                            + cbTSMS.isSelected() + "," + cbUSCG.isSelected() 
                            + "," + usrFees.getValue() + "," + vslName.getText() 
                            + "," + offNumber.getText() + "," + cSign.getText() 
                            + "," + hPort.getText() + "," + plcBuilt.getText() 
                            + "," + MMSI_Num.getText() + "," + vslPhone.getText() 
                            + "," + vslEmail.getText() 
                            + "," + onr.getText() + "," + onrAddress.getText() 
                            + "," + opr.getText() 
                            + "," + oprAddress.getText() + "," + tpo.getText() 
                            + "," + tpo_POC.getText() 
                            + "," + tpo_POC_Phone.getText() + "," 
                            +  tpo_Address.getText()
                            + "," + chrtr.getText() + "," 
                            + splConsiderations.getText() 
                            + "," + altArrangements.getText() + "\n");

                    bufferedWriter.close(); 
                    System.out.println("Saved to src/StoredINFO/VesselParticulars");
                    
                    //this will put the commas back in place
                    hPort.setText(hPort.getText().replace(";", ","));
                    plcBuilt.setText(plcBuilt.getText().replace(";", ","));
                    onrAddress.setText(onrAddress.getText().replace(";", ","));
                    oprAddress.setText(oprAddress.getText().replace(";", ","));
                    tpo_Address.setText(tpo_Address.getText().replace(";", ","));
                    splConsiderations.setText(splConsiderations.getText().replace(";", ","));
                    altArrangements.setText(altArrangements.getText().replace(";", ","));
                    
                } catch (IOException ex) {
                    System.err.println("File did not write to "
                            + "src/StoredINFO/VesselParticulars");
                    
                    
                }
            }
            
        });
        
        
        
      
    
    }
        
       
        
        
        
}
    

