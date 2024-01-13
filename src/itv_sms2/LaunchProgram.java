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
 * File purpose:  This file launches the program and sets the stage for the 
 * entire JavaFX portion of the program.  This program then opens the MainMenu
 * file.
 */


package itv_sms2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LaunchProgram extends Application{
    
    public static void main(String[] args)
    {
        Application.launch(args);
    }
    
    @Override
    public void start(Stage primaryStage)
    {
        //build border pane to run throughout the program 
       BorderPane pane = new BorderPane();
       pane.setMinSize(1000, 700);
       
        //adds display pane to scene
       Scene scene = new Scene(pane);
       primaryStage.setResizable(true);
       primaryStage.setTitle("ITV-SMS");//titlebar edited 
       primaryStage.setScene(scene);
       primaryStage.show();
       
       MainMenu.startMainMenu(pane);
    }
    
}
