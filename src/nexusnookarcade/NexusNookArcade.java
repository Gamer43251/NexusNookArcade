/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package nexusnookarcade;


// Imports 
import com.ibatis.common.jdbc.ScriptRunner;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.HeadlessException;
import java.io.*;
import java.nio.file.*;
import java.sql.*;
import java.util.Random;
import javax.sound.sampled.*;
import javax.swing.JOptionPane;
/**
 *
 * @author Dreel
 */

//Start of class
public class NexusNookArcade {
    static String userHome = System.getProperty("user.home"); //Initialises a string linking to the users home
    static Path documentsPath = Paths.get(userHome, "Documents", "NexusNookArcade");//sets a path to where files will be stored
    static String url = "jdbc:sqlite:" + documentsPath.resolve("database.db").toString(); // creates url linking to database
    static boolean paused = false; // boolean to track whether audio is paused or played
    static Clip clip; // clip object to be used for audio
    public static User user; // initialises a user object
  
    public static void backgroundAudio() throws UnsupportedAudioFileException, IOException, LineUnavailableException{ // Start of method for background audio
        
        File file = new File("build/classes/Resources/audio/Synthwave.wav"); // sets the file to Synthwave 
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file); // gets the audio from the file
        clip = AudioSystem.getClip(); // sets the audio to a clip
        clip.open(audioStream); // opens the audio stream with the clip
        
        
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN); // creates a float control to manage audios volume
        float volume = 0.1f; // initialise variable to store volume
        gainControl.setValue(20f * (float) Math.log10(volume)); // sets the audio to volume
        
        clip.start(); // starts the clip
    }//end of background audio
    
    public static void pausePlay(){ // start of pausePlay method
        System.out.println(userHome);
        if (paused){ // checks if audio is paused
            clip.start(); // starts the audio if paused
            paused = false; // sets paused to false
        }
        else{ // if audio isnt paused
            clip.stop(); // pauses audio
            paused = true; // sets paused to true
        }
    } // end of pausePlay method
    
    public static Font newFont(String path) { // Method to create a new font, takes the path of the font file
        Font customFont = null; // Initializes customFont to null

        try { // Try block to handle font loading exceptions
            InputStream fontStream = NexusNookArcade.class.getResourceAsStream(path); // Retrieves font file as input stream
            customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream); // Creates a font from the input stream
        } catch (IOException | FontFormatException e) { // Catches IO and FontFormat exceptions
            e.printStackTrace(); // Prints stack trace for exception
        }

        if (customFont != null) { // Checks if customFont was successfully created
            return customFont.deriveFont(Font.PLAIN, 150); // Returns the custom font with specified size and style
        } else { // If customFont creation failed
            return new Font("Arial", Font.PLAIN, 16); // Returns a default font (Arial, plain, size 16)
        }
    } // end of newFont


    public static void setupFolder(){ // start of setupFolder
        try { // Try block to handle directory creation and file operations
            if (!Files.exists(documentsPath)) { // Checks if the directory specified by 'documentsPath' exists
                Files.createDirectories(documentsPath); // Creates the directory if it doesn't exist
                script(); // Calls the 'script' method
            }
        } catch (IOException e) { // Catches IO exceptions
            e.printStackTrace(); // Prints stack trace for exception
        }
    } // end of setupFolder


    public static void script(){ // Method to execute SQL script
        try { // Begin try block to handle potential exceptions
            DriverManager.registerDriver(new org.sqlite.JDBC()); // Register SQLite JDBC driver
            Connection conn = DriverManager.getConnection(url); // Establish connection to the database using URL
            ScriptRunner sr = new ScriptRunner(conn,false,false); // Instantiate ScriptRunner object with connection (No Username & Password)
            Reader reader = new BufferedReader(new FileReader("src/Resources/Database/UserTable.sql")); // Read SQL script file
            sr.runScript(reader); // Execute the SQL script
            conn.close(); // Close the database connection
        } catch (HeadlessException | IOException | SQLException e) { // Catch possible exceptions
            JOptionPane.showMessageDialog(null,(e.getMessage())); // Display error message in a popup if an exception occurs
        } // End try-catch block
    }// End script method

    
    public static void checkDB(){ // start of checkDB method
        if (!Files.exists(documentsPath)) { // checks if database doesnt exist at directory
            setupFolder(); // calls setupFolder method
        }
    } // End checkDB method
    
    public static void login(Display display){ // start of login method
        if(isValidLogin(Display.loginUsernameField.getText(), Display.loginPasswordField.getText())){ // checks if user exists
            display.cardLayout.show(display.Cards, "Home"); // swaps to home screen
            user = createUser(Display.loginUsernameField.getText(), Display.loginPasswordField.getText()); // stores user
            display.usernameLabel.setText(user.username); // displays username on label.
            accessGranted(); // calls accessGranted method
            
        }
        else{
            System.out.println("Login Failed"); // prints message
        }
    } // end of login method
    
    public static void logout(Display display){ // start of logout method
        display.cardLayout.show(display.Cards, "Login"); // brings user back to login screen
        noAccess(); // calls no access method
    } // end of logout method
    
    
    public static boolean isValidLogin(String username, String password) { // start of isValidLogin method
        
        String sql = "SELECT * FROM Users WHERE Username = ? AND Password = ?"; // SQL query to check for user with the given username and password

        try (Connection conn = DriverManager.getConnection(url); // try block to execute sql
             PreparedStatement pstmt = conn.prepareStatement(sql)) { // stores sql as a preparedstatement
            
            
            pstmt.setString(1, username); // set the value of 1st ?
            pstmt.setString(2, password); // set the value of 2nd ?

            ResultSet rs = pstmt.executeQuery(); // execute sql query

            
            return rs.next(); // If the query finds a row, the credentials are valid
        } catch (SQLException e) {
            System.out.println(e.getMessage()); // print error message
            return false; // return false
        } // end of try block
           
    } // end of isValidLogin method
    
    public static User createUser(String username, String password){ // start of createUser method
        String sql = "SELECT * FROM Users WHERE Username = ? AND Password = ?"; // SQL query to check for user with the given username and password

        try (Connection conn = DriverManager.getConnection(url); // try block to execute sql
             PreparedStatement pstmt = conn.prepareStatement(sql)) { // stores sql as a preparedstatement
            
            
            pstmt.setString(1, username); // set the value of 1st ?
            pstmt.setString(2, password); // set the value of 2nd ?

            ResultSet rs = pstmt.executeQuery(); // execute sql query
            
            String id = rs.getString("UUID"); // Stores id from db
            String Username = rs.getString("Username"); // Stores username from db
            String Password = rs.getString("Password"); // stores password from db
            int snake_HS = rs.getInt("Snake_HS"); // stores snake highscore from db
            int ttt_Wins = rs.getInt("Tic_Tac_Toe_Wins"); // stores tic tac toe wins from db
            int ttt_Loss = rs.getInt("Tic_Tac_Toe_LOSS"); // stores tic tac toe losses from db
            int blackjack_Wins = rs.getInt("Blackjack_WINS"); // stores blackjack wins from db
            int blackjack_Loss = rs.getInt("Blackjack_Loss"); // stores blackjack losses from db
            
            user = new User(id, Username, Password, snake_HS, ttt_Wins, ttt_Loss, blackjack_Wins, blackjack_Loss); // creates new user with importted stats
            return user; // returns new user
        }
        catch (SQLException e) {
            System.out.println(e.getMessage()); // prints error message
            
        } // end of try block
        
        return user; // returns empty user
    } // end of createUser method
    
    public static void createAccount(Display display){ // start of create account method
        if(display.createPasswordField.getText().equals(display.confirmPasswordField.getText())){ // checks if password and confirm password matches
           if(validUsername(display.createUsernameField.getText()) && validPassword(display.createPasswordField.getText())){ // checks that username and password are valid
               newUser(display.createUsernameField.getText(), display.createPasswordField.getText()); // creates new user
               display.cardLayout.show(display.Cards, "Login"); // displays login screen
            }
           else{
               System.out.println("Invalid Username or Password"); // prints message
           }
        }
        else{
            System.out.println("Passwords dont match"); // prints message
        }
    } // end of createAccount method
    
    
    public static void newUser(String username, String password){ // start of newUser method
        String sql = "INSERT INTO Users " + "VALUES (?, ?, ?, '0', '0', '0', '0', '0')"; // sql query to insert new user to database
        try (Connection conn = DriverManager.getConnection(url); // try block to execute sql
             PreparedStatement pstmt = conn.prepareStatement(sql)) { //prepared statement of sql
            
            pstmt.setString(1, newID()); // set the value of 1st ?
            pstmt.setString(2, username); // set the value of 2nd ?
            pstmt.setString(3, password); // set the value of 3rd ?
            
            pstmt.executeUpdate(); // execute sql
            
        }
        catch (SQLException e) {
            System.out.println(e.getMessage()); // print error message
            
        }
        
    } // end of newUser method
    
    public static String newID(){ // start of newID method
        String id = ""; // initialises id variable
        char[] charArray = { 
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'}; // initialises array with all valid chars for ID
        
        
        for(int i = 0; i < 10; i++){
            Random rand = new Random(); // initialises rand
            int randomIndex = rand.nextInt(charArray.length); // gets a random index from charArray
            
            id = id + charArray[randomIndex]; // adds current indexed char to ID
        }
        return id; // return ID
    }// end of newID method
    
    public static boolean validUsername(String user){ // start of validUsername method
        if (user.length() < 11){ // checks that length is < 11
            return true; // returns true
        }
        else{
            return false; // return false
        }
    } // end of validUsername method
    
    public static boolean validPassword(String pass){ // start of validPassword method
        if (pass.length() < 21){ // checks that length is < 21
            return true; // return true
        }
        else{
            return false; // return false
        }
    } // end of validPassword methos
    
    public static User getUser(){ //start of getUser method
        return user; // return user
    } // end of getUser method
    
    public static void noAccess(){ // start of noAccess method
        Display.usernameLabel.setVisible(false); // set usernameLabel to invisible
        Display.homeBTN.setVisible(false); // set home button to invisible
        Display.logoutBTN.setVisible(false); // set logout button to inkvisible
    } // end of noAccess method
    
    public static void accessGranted(){ // start of access granted method
        Display.usernameLabel.setVisible(true); // set username label to visible 
        Display.homeBTN.setVisible(true); // set home button to visible
        Display.logoutBTN.setVisible(true); // set logout button to visible
    } //end of accessGranted method
}
    

    

  

