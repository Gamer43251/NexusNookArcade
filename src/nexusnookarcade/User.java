/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nexusnookarcade;

/**
 *
 * @author Dreel
 */
public class User {
    String userID; // initialize userID string
    String username; // initialize username String
    String password; // initialize password String
    int snake_HS; // initialize snake_HS int
    int tttWins; // initialize tttWins int
    int tttLoss; // initialize tttLoss int
    int blackjackWins; // initialize blackjackWins int
    int blackjackLoss; // initialize bkacjackLoss int

    public User(String userID, String username, String password, int snake_HS, int tttWins, int tttLoss, int blackjackWins, int blackjackLoss){ // start of user Constructor
        this.userID = userID; // set Users userID
        this.username = username; // set Users username
        this.password = password; // set Users password
        this.snake_HS = snake_HS; // set Users snake_HS
        this.tttWins = tttWins; // set Users tttWins
        this.tttLoss = tttLoss; // set Users tttLoss
        this.blackjackWins = blackjackWins; // set Users blackjackWins
        this.blackjackLoss = blackjackLoss; // set Users blackjackLoss
        
    } // end of User constructor
    
    public String getID(){ // start of getID method
        return this.userID; // return Users userID
    } // end of getID method
    
    public int getSnakeHigh(){ // start of getSnakeHigh method
        return this.snake_HS; // return Users snake_HS
    } // end of getSnakeHigh method
    
    public int getTicWins(){ // start of getTicWins method
        return this.tttWins; // return Users tttWins
    } // end of getTcWins method
    
    public int getTicLoss(){ // start of getTicLoss method
        return this.tttLoss; // return Users tttLoss
    } // end of getTicLoss method
    
    public void upTWins(){ // start of upTWins method
        this.tttWins ++; // increase Users tttWins by 1
    } // end of upTWins method
    
    public void upTLoss(){ // start of up TLoss method
        this.tttLoss ++; // increase Users tttLoss by 1
    }// end of upTWins method
    
    @Override
    public String toString(){ //start of toString method
        String str = this.userID + " " + this.username + " " + this.password + " " + this.snake_HS + " " + this.tttWins + " " + this.tttLoss + " " + this.blackjackWins + " " + this.blackjackLoss; // Stores all user stats as a string
        
        return str; // returns all user stats
    } // end of toString method
    
    public User updateUser(){ // start of updateUser method
        User user = NexusNookArcade.createUser(this.username,this.password); // imports updated user
        return user; // returns updated user
    } // end of updateUser method
}