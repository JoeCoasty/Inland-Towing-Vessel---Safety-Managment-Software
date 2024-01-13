/*
 * Inland Towing Vessel - Safety Management System is an open source software
 * designed to assist vessel operators in maintaining compliance with the 
 * administrative requirements found in 46 CFR - SubChapter M.  This software 
 * can be used and modified by anyone as a free service.  This software can not
 * be used as a paid for service.
 */ 
  
 /** <**********************Free Open Source Statement**********************>
  This software is licensed, not sold. Users are granted permission to use it
 * as a free service, but they cannot sell or distribute it for profit. 
 * Additionally, users have the right to modify the software for their own use, 
 * but any modified versions cannot be distributed or sold.
 */

/**
 * @author Joseph Schwierking
 */
package itv_sms2;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainMenu{
    
    
    public static void startMainMenu(BorderPane pane)
    {
        
              
       //************* This programming is for the title **********************
       //build hbox for title
       HBox title = new HBox();
       
       //add text for title
       Text textTitle = new Text("INLAND TOWING VESSEL - SAFETY MANAGEMENT SOFTWARE\n\t\t\t\t\t\tMAIN MENU");
       textTitle.setFont(Font.font("Times New Roman", FontWeight.BOLD,
               FontPosture.ITALIC, 30)); //sets the style of the text
              
       //add title text to HBox
       title.setAlignment(Pos.CENTER);//makes it so everything added to HBOX is centered
       title.getChildren().addAll(textTitle);//adds title to HBox
       
       //place hbox in top pane
       pane.setTop(title); //adds HBox to top pane of borderPane
       
       //******** This programming for the buttons to be selected ***********
       
       //build GridPane to display pictures into
       GridPane display = new GridPane();
       
       //build HBoxes to place titles under buttons
       HBox parTitleBt = new HBox();
       HBox TVRTitleBt = new HBox();
       HBox crewTitleBt = new HBox();
       HBox maintTitleBt = new HBox();
       HBox reportsTitleBt = new HBox();
       
       //HBoxes to space everything out
       HBox blank1 = new HBox();
       HBox blank2 = new HBox();
       
       //set size for blank HBox
       blank1.setMinSize(100,50);
       blank2.setMinSize(100,50);
       
       //Images for buttons
       Image Particulars = new Image("VesselParticularsButton.jpg", 220, 220, false, false);
       Image TVR = new Image("TVRButton.jpg",220,220,false,false);
       Image Crew = new Image("CrewButton.jpg", 220, 220, false, false);
       Image Maint = new Image("MaintenanceButton.jpg", 220, 220, false, false);
       Image Reports = new Image("ReportsButton.jpg", 220, 220, false, false);
       
       //make buttons for each menu item
       Button btParticulars = new Button();
       Button btTVR = new Button();
       Button btCrew = new Button();
       Button btMaint = new Button();
       Button btReports = new Button();
       
       //place images into buttons
       btParticulars.setGraphic(new ImageView(Particulars));
       btTVR.setGraphic(new ImageView(TVR));
       btCrew.setGraphic(new ImageView(Crew));
       btMaint.setGraphic(new ImageView(Maint));
       btReports.setGraphic(new ImageView(Reports));
       
                    
       //Buttons with pictures to grid pane
       display.setAlignment(Pos.CENTER);
       display.add (btParticulars, 0, 0);
       display.add(btTVR, 2, 0);
       display.add(btCrew, 4, 0);
       display.add(btMaint, 0, 2);
       display.add(btReports, 2, 2);
       display.add(blank1, 1, 1);
       display.add(blank2, 3, 1);
       
       //labels under buttons
       Label lblParticulars = new Label("Vessel Particulars");
       Label lblTVR = new Label("Towing Vessel Record");
       Label lblCrew = new Label ("Crew");
       Label lblMaint = new Label("Maintenance");
       Label lblReports = new Label("Reports");
       
       //Display labels under buttons
       parTitleBt.setAlignment(Pos.TOP_CENTER);//center HBox
       parTitleBt.getChildren().add(lblParticulars);//add label to HBox
       display.add(parTitleBt, 0, 1);//Add HBox to grid
       
       maintTitleBt.setAlignment(Pos.TOP_CENTER);
       maintTitleBt.getChildren().add(lblMaint);
       display.add(maintTitleBt, 0, 3);
       
       TVRTitleBt.setAlignment(Pos.TOP_CENTER);
       TVRTitleBt.getChildren().add(lblTVR);
       display.add(TVRTitleBt, 2, 1);
       
       reportsTitleBt.setAlignment(Pos.TOP_CENTER);
       reportsTitleBt.getChildren().add(lblReports);
       display.add(reportsTitleBt, 2, 3);
       
       crewTitleBt.setAlignment(Pos.TOP_CENTER);
       crewTitleBt.getChildren().add(lblCrew);
       display.add(crewTitleBt, 4, 1);
       
       
       //action events for button selection
       btParticulars.setOnAction(e -> 
        {
            System.out.println("The Vessel Particulars button was pushed");
            pane.getChildren().clear();//clear the screen for new class to start
            Main_VesselParticulars.Main_VesselParticularsPressed(pane);
        });
       
       btTVR.setOnAction(e -> 
        {
            System.out.println("The Towing Vessel Record button was pushed");
            pane.getChildren().clear();//clear the screen for new class to start
            Main_TVR.Main_TVRPressed(pane);
        });
       
       btCrew.setOnAction(e -> 
        {
            System.out.println("The Crew button was pushed");
            pane.getChildren().clear();//clear the screen for new class to start
            Main_Crew.Main_CrewPressed(pane);
        });
       
       btMaint.setOnAction(e -> 
        {
            System.out.println("The Maintenance button was pushed");
            pane.getChildren().clear();//clear the screen for new class to start
            Main_Maintenance.Main_MaintenancePressed(pane);
        });
       
       btReports.setOnAction(e -> 
        {
            System.out.println("The Reports button was pushed");
            pane.getChildren().clear();//clear the screen for new class to start
            Main_Reports.Main_ReportsPressed(pane);
        });
       
       //adds gridpane to center display pane
       pane.setCenter(display);
       
    }
    
    
}
