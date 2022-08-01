/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameofpig;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.*;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import java.awt.event.*;import java.awt.*;import javax.swing.*;
import java.util.Arrays;
import java.util.Random;
import javax.swing.*;
/**
 * FXML Controller class
 *
 * @author vidsterbroyo
 */
public class gopController implements Initializable {

    String j;
    
    @FXML 
    private Text p1ts, p1rs, p2ts, p2rs;
        
    @FXML 
    private Button rollButton, holdButton;
   
            
    @FXML
    private Pane pane1, pane2, winning;
    

    @FXML 
    private Text gopMessage, gopMessage1;

    @FXML 
    private TextField gopWinIn;
 
    @FXML 
    private Button gopPAButton;
    
    
    Random rand = new Random();
    int roundScore;
    int p1;
    int p2;
    boolean gopPlayer = true;
    boolean one;
    int gopWin;

    
        
    @FXML
    private void home(ActionEvent event) throws Exception{
        //load FXML file
        Parent root = FXMLLoader.load(getClass().getResource("gopHome.fxml"));
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 854, 480));
        gopWin=20;
    }
    
    @FXML
    private void gopPage(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("gameOfPig.fxml"));
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 854, 480));
    }
    
    @FXML
    private void gopIns(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("gopInstructions.fxml"));
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 854, 480));
    }
    
    @FXML
    private void gopIn(ActionEvent event) throws Exception{
        gopWin = Integer.parseInt(gopWinIn.getText());
        winning.setVisible(false);
        holdButton.setVisible(true);
        rollButton.setVisible(true);
    }
    
    @FXML
    private void reset(ActionEvent event) throws Exception{
        roundScore = 0;
        p1 = 0;
        p2 = 0;
        gopPlayer = true;
        one = false;
        gopWin = 20;
        Parent root = FXMLLoader.load(getClass().getResource("gameOfPig.fxml"));
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 854, 480));
    }
    
    @FXML
    private void roll(){
        // dice roll
        int d1 = rand.nextInt(6)+1;   int d2 = rand.nextInt(6)+1;
        gopMessage1.setText("Numbers rolled: "+d1+" & "+d2);
        
        // check for a 1
        if (d1 == 1 || d2 == 1){
            gopMessage.setText("You rolled a 1!");
            
            roundScore=0;
            one=true;
            save();
            one=false;
        }
        
        // if not a 1
        else{
            // calc. total and display
            roundScore += d1+d2;
            if (gopPlayer){
                p1rs.setText(Integer.toString(roundScore));
            }
            else{
                p2rs.setText(Integer.toString(roundScore));
            }
            rollButton.setText("ROLL AGAIN");  
        }
        
    }
    
    @FXML
    private void save(){
        if (!one){
            gopMessage.setText("");
        }
        
        
        if (gopPlayer){
            // add round score to total
            p1+=roundScore;
            if (p1 >= gopWin) {
                gopMessage.setText("Player 1 Won!");
                pane2.setStyle("-fx-opacity: 1; -fx-background-color:  #d4fcb3;");
                pane1.setStyle("-fx-opacity: 1; -fx-background-color:  #e787ff;");
                rollButton.setVisible(false);
                holdButton.setVisible(false);
                gopPAButton.setVisible(true);
            }
            else{
                // change opacity
                pane1.setStyle("-fx-opacity: 0.5; -fx-background-color:  #e787ff;");  pane2.setStyle("-fx-opacity: 1; -fx-background-color:  #d4fcb3;");

                // display new player
                gopMessage.setText(gopMessage.getText()+" It's Player 2's turn!");
            }
        }
        else{
            // add round score to total
            p2+=roundScore;
            if (p2 >= gopWin) {
                gopMessage.setText("Player 2 Won!");
                pane2.setStyle("-fx-opacity: 1; -fx-background-color:  #d4fcb3;");
                pane1.setStyle("-fx-opacity: 1; -fx-background-color:  #e787ff;");
                rollButton.setVisible(false);
                holdButton.setVisible(false);
                gopPAButton.setVisible(true);
            }
            else{
                // change opacity
                pane2.setStyle("-fx-opacity: 0.5; -fx-background-color:  #d4fcb3;");  pane1.setStyle("-fx-opacity: 1; -fx-background-color:  #e787ff;");

                // display new player
                gopMessage.setText(gopMessage.getText()+" It's Player 2's turn!");
            }
        }
        
        roundScore=0;
        rollButton.setText("ROLL");  
        p1rs.setText("0");
        p2rs.setText("0");
        gopPlayer = !gopPlayer;

        p1ts.setText(Integer.toString(p1));
        p2ts.setText(Integer.toString(p2));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
