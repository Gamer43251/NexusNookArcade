/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Games.TicTacToe;

import java.awt.Color;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import nexusnookarcade.Display;
import nexusnookarcade.NexusNookArcade;
import nexusnookarcade.User;

/**
 *
 * @author Dreel
 */
public class TicTacToeLogic { // start of TicTacToe class
    
    
    
    static String userHome = System.getProperty("user.home"); //Initialises a string linking to the users home
    static Path documentsPath = Paths.get(userHome, "Documents", "NexusNookArcade");//sets a path to where files will be stored
    static String url = "jdbc:sqlite:" + documentsPath.resolve("database.db").toString(); // creates url linking to database
    static User user = NexusNookArcade.getUser(); // get current user
    static boolean player1_turn = true; // initialise player1_turn
    static boolean playing = true; // initialise playing

    public static void checkBTN(JLabel BTN){ // start of checkBTN method
        if(BTN.getText().equals("") && playing){
            click(BTN); // call click Method on BTN
        }
    } // end of checkBTN method
        
    public static void click(JLabel BTN){ // start of clickBTN
        displayStats(); // call displayStats method
        if (player1_turn == true){
            player1_turn = false; // disable player1_turn
            BTN.setText("X"); // set the text of BTN to X
            BTN.setForeground(new Color(255,49,49)); // change labels text color
            TicTacToe.TurnLabel.setText("Guest's Turn"); // set Turnlabel to Guest's Turn
            if(!checkWin()){
               if (!checkTie()){
                Tied();  // call tied method
                }      
            }
        }
        else{
            player1_turn = true; // enable player1_turn
            BTN.setText("O"); // set the text of BTN to O
            BTN.setForeground(new Color(92,225,230)); // change labels text color
            TicTacToe.TurnLabel.setText("User's Turn"); // set Turnlabel to User's Turn
            if(!checkWin()){
               if (!checkTie()){
                Tied(); // call Tied method
                }      
            }
        }
        
    } // end of click method
    
    public static boolean checkWin(){ // start of checkWin method
        // Check X Wins
        if(
                TicTacToe.BTN1.getText().equals("X") &&
                TicTacToe.BTN2.getText().equals("X") &&
                TicTacToe.BTN3.getText().equals("X")){  
                xWins();
                return true;
        }
        if(
                TicTacToe.BTN4.getText().equals("X") &&
                TicTacToe.BTN5.getText().equals("X") &&
                TicTacToe.BTN6.getText().equals("X")){  
                xWins();
                return true;
        }
        if(
                TicTacToe.BTN7.getText().equals("X") &&
                TicTacToe.BTN8.getText().equals("X") &&
                TicTacToe.BTN9.getText().equals("X")){  
                xWins();
                return true;
        }
        if(
                TicTacToe.BTN1.getText().equals("X") &&
                TicTacToe.BTN4.getText().equals("X") &&
                TicTacToe.BTN7.getText().equals("X")){  
                xWins();
                return true;
        }
        if(
                TicTacToe.BTN2.getText().equals("X") &&
                TicTacToe.BTN5.getText().equals("X") &&
                TicTacToe.BTN8.getText().equals("X")){  
                xWins();
                return true;
        }
        if(
                TicTacToe.BTN3.getText().equals("X") &&
                TicTacToe.BTN6.getText().equals("X") &&
                TicTacToe.BTN9.getText().equals("X")){  
                xWins();
                return true;
        }
        if(
                TicTacToe.BTN1.getText().equals("X") &&
                TicTacToe.BTN5.getText().equals("X") &&
                TicTacToe.BTN9.getText().equals("X")){  
                xWins();
                return true;
        }
        if(
                TicTacToe.BTN3.getText().equals("X") &&
                TicTacToe.BTN5.getText().equals("X") &&
                TicTacToe.BTN7.getText().equals("X")){  
                xWins();
                return true;
        }
        
        //Check O Win
        if(
                TicTacToe.BTN1.getText().equals("O") &&
                TicTacToe.BTN2.getText().equals("O") &&
                TicTacToe.BTN3.getText().equals("O")){  
                oWins();
                return true;
        }
        if(
                TicTacToe.BTN4.getText().equals("O") &&
                TicTacToe.BTN5.getText().equals("O") &&
                TicTacToe.BTN6.getText().equals("O")){  
                oWins();
                return true;
        }
        if(
                TicTacToe.BTN7.getText().equals("O") &&
                TicTacToe.BTN8.getText().equals("O") &&
                TicTacToe.BTN9.getText().equals("O")){  
                oWins();
                return true;
        }
        if(
                TicTacToe.BTN1.getText().equals("O") &&
                TicTacToe.BTN4.getText().equals("O") &&
                TicTacToe.BTN7.getText().equals("O")){  
                oWins();
                return true;
        }
        if(
                TicTacToe.BTN2.getText().equals("O") &&
                TicTacToe.BTN5.getText().equals("O") &&
                TicTacToe.BTN8.getText().equals("O")){  
                oWins();
                return true;
        }
        if(
                TicTacToe.BTN3.getText().equals("O") &&
                TicTacToe.BTN6.getText().equals("O") &&
                TicTacToe.BTN9.getText().equals("O")){  
                oWins();
                return true;
        }
        if(
                TicTacToe.BTN1.getText().equals("O") &&
                TicTacToe.BTN5.getText().equals("O") &&
                TicTacToe.BTN9.getText().equals("O")){  
                oWins();
                return true;
        }
        if(
                TicTacToe.BTN3.getText().equals("O") &&
                TicTacToe.BTN5.getText().equals("O") &&
                TicTacToe.BTN7.getText().equals("O")){  
                oWins();
                return true;
        }
        return false;
    
    } // end of checkWin method

    public static void xWins(){ // start of xWins method
        TicTacToe.TurnLabel.setText("Player 1 Wins"); // set TurnLabel to Player 1 Wins
        increaseWins(); // call increaseWins method
        gameOver("Game Over You Won"); // call gameOver method
    } // end of xWins method

    private static void oWins() { // start of oWins
        TicTacToe.TurnLabel.setText("Guest Wins"); // set TurnLabel to Guest Wins
        increaseLoss(); // call increaseLoss method
        gameOver("Game Over You Lost"); //
    } // end of oWins method
    
    private static void Tied() { // start of Tied method
        TicTacToe.TurnLabel.setText("You Tied"); // set TurnLabel to You Tied
        gameOver("Game Over You Tied"); // call gameOver method
    } // end of Tied method

    private static boolean checkTie(){ // start of checkTie method
        // check if any buttons are clear
        if(TicTacToe.BTN1.getText().equals("") ||
           TicTacToe.BTN2.getText().equals("") ||
           TicTacToe.BTN3.getText().equals("") ||    
           TicTacToe.BTN4.getText().equals("") ||     
           TicTacToe.BTN5.getText().equals("") ||     
           TicTacToe.BTN6.getText().equals("") ||     
           TicTacToe.BTN7.getText().equals("") ||     
           TicTacToe.BTN8.getText().equals("") || 
           TicTacToe.BTN9.getText().equals("") 
                ){
            return true;
        }
        else{
            return false;
        }
    } // end of checkTie method
    
    private static void gameOver(String message){ // start of gameOver method
        playing = false; // disable playing
        TicTacToe.TTTGameOver.setVisible(true); // show Game over screen
        TicTacToe.GameOverLabel.setText(message); // set text of gameOverLabel
    } // end of gameOver method
    
    public static void playAgain(){ // start of playAgain method
        displayStats(); // call displayStats method
        // clear all buttons text
        TicTacToe.BTN1.setText("");
        TicTacToe.BTN2.setText("");
        TicTacToe.BTN3.setText(""); 
        TicTacToe.BTN4.setText(""); 
        TicTacToe.BTN5.setText(""); 
        TicTacToe.BTN6.setText("");
        TicTacToe.BTN7.setText("");    
        TicTacToe.BTN8.setText(""); 
        TicTacToe.BTN9.setText("");
        playing = true; // enable playing
        TicTacToe.TTTGameOver.setVisible(false); // hide gameover screen
        player1_turn = true; // enable player1_turn
    } // end of playAgain method
    
    public static void displayStats(){ // start of displayStats method
        Display.ticWinsLabel.setText("Wins: " + user.getTicWins()); // display players wins
        Display.ticLossesLabel.setText("Losses: " + user.getTicLoss());  // display players losses  
    } // end of displayStats method
    
    
    public static void increaseWins(){ // start of increaseWins method
        String sql = "UPDATE Users SET Tic_Tac_Toe_Wins = ? WHERE UUID = ?"; // sql statement to update users Tic tac toe wins
        int wins = user.getTicWins() + 1; // initialise wins
        user.upTWins(); // call upTWins method for user
        String userID = user.getID(); // initialise users userID
        try (Connection conn = DriverManager.getConnection(url);
            PreparedStatement pstmt = conn.prepareStatement(sql)) { // sql statement stored in prepared statement

            // Set the parameters before executing the update
            pstmt.setInt(1, wins); // set the value of 1st ?
            pstmt.setString(2, userID); // set the value of 2nd ? 

            // Execute the update statement and get the count of updated rows
            int updatedRows = pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            }
    } // end of increaseWins method
        
        public static void increaseLoss(){ // start of increaseLoss method
        String sql = "UPDATE Users SET Tic_Tac_Toe_LOSS = ? WHERE UUID = ?"; // sql statement to update users tic tac toe loss
        int loss = user.getTicWins() + 1; // initialise loss
        user.upTLoss(); // call upTLoss method for user
        String userID = user.getID(); // initialise users userID
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) { // store sql as prepared statement

            // Set the parameters before executing the update
            pstmt.setInt(1, loss); // stores the value of 1st ?
            pstmt.setString(2, userID); // stores the value of 2nd ?

            // Execute the update statement and get the count of updated rows
            int updatedRows = pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    } // end of increaseLoss method
       

} // end of TicTacToeLogic class
