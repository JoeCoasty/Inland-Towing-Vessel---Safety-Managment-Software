/* 
 * Inland Towing Vessel - Safety Management System is an open source software
 * designed to assist vessel operators in constructionaining compliance with the 
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
 * File Purpose: 
 */
package itv_sms2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

 
public class Main_TVR{
    
    public static void Main_TVRPressed(BorderPane pane) {
        
             
       //************* This programming is for the title **********************
       //build hbox for title
       HBox title = new HBox();
       HBox blank1 = new HBox();
       blank1.setMinHeight(50);
      
       //add text for title
       Text textTitle = new Text(" INLAND TOWING VESSEL - SAFETY MANAGEMENT SOFTWARE\n\t\t\t\t TOWING VESSEL RECORD");
       textTitle.setFont(Font.font("Times New Roman", FontWeight.BOLD,
               FontPosture.ITALIC, 30)); //sets the style of the text
              
       //add title text to HBox
       title.setAlignment(Pos.CENTER);//makes it so everything added to HBOX is centered
       title.getChildren().addAll(textTitle);//adds title to HBox
       
       //place hbox in top pane
       pane.setTop(title); //adds HBox to top pane of borderPane
     
        //GridPane menus = new GridPane();
        GridPane forms = new GridPane();
        
        //Create VBoxs
        VBox menuMain = new VBox();//Main menu selections
        
        //Add buttons for main menu
        Button main = new Button("Main Menu");
        Button details = new Button ("?");
        Button surveys = new Button ("?");
        Button specifics = new Button ("?");
        Button propulsion = new Button("?");
        Button generator = new Button("?");
        Button steering = new Button("?");
        Button thrust = new Button("?");
        Button tanks = new Button("?");
        Button pressureVessels = new Button("?");
        Button boiler = new Button("?");
        Button construction = new Button ("?");
        Button routes = new Button("?");
        Button fireFighting = new Button("?");
        Button lifeSaving = new Button("?");
        Button documents = new Button("?");
        
        //set grid pane in left pane so that two VBoxes cen be installed for menubuttons and menutree
        //Set width of menu box
        menuMain.setPrefWidth(200);
       
        //set width of buttons to match menu vbox
        main.setMinSize(menuMain.getPrefWidth(), 30);
        details.setMinSize(menuMain.getPrefWidth(), 30);
        surveys.setMinSize(menuMain.getPrefWidth(), 30);
        specifics.setMinSize(menuMain.getPrefWidth(), 30);
        propulsion.setMinSize(menuMain.getPrefWidth(), 30);
        generator.setMinSize(menuMain.getPrefWidth(), 30);
        steering.setMinSize(menuMain.getPrefWidth(), 30);
        thrust.setMinSize(menuMain.getPrefWidth(), 30);
        tanks.setMinSize(menuMain.getPrefWidth(), 30);
        pressureVessels.setMinSize(menuMain.getPrefWidth(), 30);
        boiler.setMinSize(menuMain.getPrefWidth(), 30);
        construction.setMinSize(menuMain.getPrefWidth(), 30);
        routes.setMinSize(menuMain.getPrefWidth(), 30);
        fireFighting.setMinSize(menuMain.getPrefWidth(), 30);
        lifeSaving.setMinSize(menuMain.getPrefWidth(), 30);
        documents.setMinSize(menuMain.getPrefWidth(), 30);
        
        //Centers all text inside buttons for menu
        menuMain.setAlignment(Pos.TOP_CENTER); 
        //Adds all of the buttons to vbox
        menuMain.getChildren().addAll(blank1, main, details, surveys, specifics, 
                propulsion, generator, steering, thrust, tanks, pressureVessels,
                boiler, construction, routes, fireFighting, lifeSaving, 
                documents); 
        
        pane.setLeft(menuMain);//drop main menu into left borderpane

        //Action events for all the buttons on the left of Vessel Particulars
        //Returns to the main menu when clicked
        main.setOnAction(e -> 
        {
            System.out.println("The Main Menu button in VP was pushed");
            pane.getChildren().clear();//clear the screen for new class to start
            MainMenu.startMainMenu(pane);
        });
        
/*        
        //opens details menuSub
        details.setOnAction(e -> 
        {
            System.out.println("The Details button in VP was pushed");
            forms.getChildren().clear();//clear the form for new form to start
           try {
               VP_Details.VP_DetailsPressed(pane, forms);
           } catch (IOException ex) {
               Logger.getLogger(VesselParticulars.class.getName()).log(Level.SEVERE, null, ex);
           }
                     
        });    
        
        //Opens Survey menu when clicked
        surveys.setOnAction(e -> 
        {
            System.out.println("The Survey button in VP was pushed");
            forms.getChildren().clear();//clear the form for new form to start
           try {
               VP_Inspection.VP_InspectionPressed(pane, forms);
           } catch (FileNotFoundException ex) {
               Logger.getLogger(VesselParticulars.class.getName()).log(Level.SEVERE, null, ex);
           }
            
        });
        
        //Opens specifics menu when clicked
        specifics.setOnAction(e -> 
        {
            System.out.println("The Specifics button in VP was pushed");
            forms.getChildren().clear();//clear the form for new form to start
           try 
           {
               VP_VesselSpecifics.VP_VesselSpecificsPressed(pane, forms);
           } 
           catch (FileNotFoundException ex) 
           {
               Logger.getLogger(VesselParticulars.class.getName()).log(Level.SEVERE, null, ex);
           }
                     
        });
                
        //Opens propulsion menu when clicked
        propulsion.setOnAction(e -> 
        {
            System.out.println("The Propulsion button in VP was pushed");
            forms.getChildren().clear();
            try 
           {
               VP_Propulsion.VP_PropulsionPressed(pane, forms);
           } 
           catch (FileNotFoundException ex) 
           {
               Logger.getLogger(VesselParticulars.class.getName()).log(Level.SEVERE, null, ex);
           }
        });
        
        //Opens generator menu when clicked
        generator.setOnAction(e -> 
        {
            System.out.println("The Generator button in VP was pushed");
            forms.getChildren().clear();
            try 
           {
               VP_Generator.VP_GeneratorPressed(pane, forms);
           } 
           catch (FileNotFoundException ex) 
           {
               Logger.getLogger(VesselParticulars.class.getName()).log(Level.SEVERE, null, ex);
           }
        });
        
        //Opens steering menu when clicked
        steering.setOnAction(e -> 
        {
            System.out.println("The Steering button in VP was pushed");
            forms.getChildren().clear();
        });
        
        //Opens thruster menu when clicked
        thrust.setOnAction(e -> 
        {
            System.out.println("The Thrusters button in VP was pushed");
            forms.getChildren().clear();
            try 
           {
               VP_Thrusters.VP_ThrustersPressed(pane, forms);
           } 
           catch (FileNotFoundException ex) 
           {
               Logger.getLogger(VesselParticulars.class.getName()).log(Level.SEVERE, null, ex);
           }
        });
        
        //Opens tanks menu when clicked
        tanks.setOnAction(e -> 
        {
            System.out.println("The Tanks button in VP was pushed");
            forms.getChildren().clear();
            
             try 
           {
               VP_Tanks.VP_TanksPressed(pane, forms);
           } 
           catch (FileNotFoundException ex) 
           {
               Logger.getLogger(VesselParticulars.class.getName()).log(Level.SEVERE, null, ex);
           }
        });
        
        //Opens pressure vessels menu when clicked
        pressureVessels.setOnAction(e -> 
        {
            System.out.println("The Pressure Vessels button in VP was pushed");
            forms.getChildren().clear();
            
             try 
           {
               VP_PressureVessels.VP_PressureVesselsPressed(pane, forms);
           } 
           catch (FileNotFoundException ex) 
           {
               Logger.getLogger(VesselParticulars.class.getName()).log(Level.SEVERE, null, ex);
           }
        });
        
        //Opens boier menu when clicked
        boiler.setOnAction(e -> 
        {
            System.out.println("The Boiler button in VP was pushed");
            forms.getChildren().clear();
              try 
           {
               VP_Boilers.VP_BoilersPressed(pane, forms);
           } 
           catch (FileNotFoundException ex) 
           {
               Logger.getLogger(VesselParticulars.class.getName()).log(Level.SEVERE, null, ex);
           }
        });
                
        //Opens construction menu when clicked
        construction.setOnAction(e -> 
        {
            System.out.println("The Construction button in VP was pushed");
            forms.getChildren().clear();
            try 
           {
               VP_Construction.VP_ConstructionPressed(pane, forms);
           } 
           catch (FileNotFoundException ex) 
           {
               Logger.getLogger(VesselParticulars.class.getName()).log(Level.SEVERE, null, ex);
           }
        });
        
        //Opens routes menu when clicked
        routes.setOnAction(e -> 
        {
            System.out.println("The Routes button in VP was pushed");
            forms.getChildren().clear();
        });
        
        //Opens fire fighting menu when clicked
        fireFighting.setOnAction(e -> 
        {
            System.out.println("The Fire Fighting button in VP was pushed");
            forms.getChildren().clear();
        });
        
        //Opens life saving menu when clicked
        lifeSaving.setOnAction(e -> 
        {
            System.out.println("The Life Saving button in VP was pushed");
            forms.getChildren().clear();
        });
        
        //Opens documents menu when clicked
        documents.setOnAction(e -> 
        {
            System.out.println("The Documents button in VP was pushed");
            forms.getChildren().clear();
        });
                     
        
        //drop forms gridpane into center pane
        forms.setAlignment(Pos.TOP_LEFT);
        forms.setPadding(new Insets(50, 10, 10, 10));
        pane.setCenter(forms);
       
 */      

        HBox hbox99 = new HBox();
        Image imUnderConstruction = new Image("UnderConstruction.jpg", 220, 220, false, false);
        Button btUnderConstruction = new Button(); 
        btUnderConstruction.setGraphic(new ImageView(imUnderConstruction));
        hbox99.getChildren().addAll(btUnderConstruction);
        forms.add(hbox99, 1, 1);
        hbox99.setPadding(new Insets(0,0,10,0));
        
        
    
    }
  
}
