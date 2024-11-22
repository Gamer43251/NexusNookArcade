/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Games.Blackjack;

import java.awt.*;
import java.awt.event.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import nexusnookarcade.Display;
import nexusnookarcade.NexusNookArcade;
import nexusnookarcade.User;
/**
 *
 * @author Dreel
 */
public class BlackJack extends javax.swing.JPanel { // start of BlackJack class

    private class Card{ // start of Card class
        String value; // initialise value String
        String type; // initialise type String
        
        Card(String value, String type){ // start of card constructor
            this.value = value; // sets the value for card
            this.type = type; // sets the type for card
        } // end of card constructor
        
        public String toString(){ // start of toString method
            return value + "-" + type; // returns value + type
        } // end of toString method
        
        public int getValue(){ // start of getValue method
            if("AJQK".contains(value)){ // checks if value is A,J,Q or K
                if (value == "A"){ // checks if value is A
                    return 11; // returns 11 if value is A
                }
                return 10; // returns 10 if value is J,Q or K
            }
            return Integer.parseInt(value); // returns value as int if it is 2 - 10
        } // end of getValue method
        
        public boolean isAce(){ // start of isAce method
            return value == "A"; // returns true if value is A and false if not
        } // end of isAce method
        
        public String getImagePath(){ // start of getImagePath mehod
            return "/Resources/img/Game Assets/Blackjack Assets/Cards/" + toString() + ".png"; // returns pathfile
        } // end of getImagePath method
    } // end of Card class
    
    
    ArrayList<Card> deck; // initialises ArrayList of cards 
    Random random = new Random(); // initialises new random
            

    
    public BlackJack() { // start of Blackjack constructor
        initComponents(); // calls initComponents method
        startGame(); // calls startGame method
        gamePanel.repaint(); // repaints gamePanel
    }
    
    // Dealer
    Card hiddenCard; // initialises dealers hidden card
    ArrayList<Card> dealerHand; // initialises dealerHand
    int dealerSum; // initialises dealerSum
    int dealerAceCount; // initialises dealerAceCount
    
    // Player
    ArrayList<Card> playerHand; // initialises playerHand
    int playerSum; // initialises playerSum
    int playerAceCount; // initialises playerAceCount
    
    int cardWidth = 110; // initialises cardWidth
    int cardHeight = 154; // initialises cardHeight
    
    private class customPanel extends JPanel { // start of customPanel class
    @Override
        protected void paintComponent(Graphics g) { // start of paintComponent method
            super.paintComponent(g); // call paintComponent method

            try { // start of try block
                // Draw Hidden card
                ImageIcon hiddenCardIcon = new ImageIcon(getClass().getResource("/Resources/img/Game Assets/Blackjack Assets/Cards/Back.png")); // sets the hidden card to back png
                Image hiddenCardImg = hiddenCardIcon.getImage(); // converts hidden card to image
                if(!stayButton.isEnabled()){ // checks if stayButton has been pressed
                    hiddenCardImg = new ImageIcon(getClass().getResource(hiddenCard.getImagePath())).getImage(); // show hidden card
                }
                g.drawImage(hiddenCardImg, 40, 40, cardWidth, cardHeight, null); // draw hidden card

                // Draw Dealers hand
                for(int i = 0; i <dealerHand.size(); i++){ // loops through dealers hand
                    Card card = dealerHand.get(i); // gets dealers card at i
                    Image cardImg = new ImageIcon(getClass().getResource(card.getImagePath())).getImage(); // turns current card into image
                    g.drawImage(cardImg, cardWidth + 45 + (cardWidth + 5) * i, 40, cardWidth, cardHeight, null); // draws current card
                } //stop looping through dealer hand

                // Draw Players Hand
                for(int i = 0; i < playerHand.size(); i++){ // loops through plaers hand
                    Card card = playerHand.get(i); // gets players card at i
                    Image cardImg = new ImageIcon(getClass().getResource(card.getImagePath())).getImage(); // turns current card into image
                    g.drawImage(cardImg, 40 + (cardWidth + 5) * i, 340, cardWidth, cardHeight, null); // draws current card
                } // stop looping through player hand

                if(!stayButton.isEnabled()){ //if stay button has been pressed
                    dealerSum = reduceDealerAce(); // call reduceDealerAce method
                    playerSum = reducePlayerAce(); // call reducePlayerAce method

                    String message = ""; // initialises message String
                    if (playerSum > 21){ // Check if playerSum is over 21
                        message = "Dealer Wins"; // sets message to Dealer Wins
                    }
                    else if(dealerSum > 21){ // checks if dealerSum is over 21
                        message = "You Win"; // sets message to You Win
                    }
                    else if (playerSum == dealerSum){ // check for a tie
                        message = "You Tied"; // sets message to You Tied
                    }
                    else if (playerSum < dealerSum){ // checks if dealerSum is > playerSum
                        message = "You Lose"; // sets message to You Lose
                    }
                    else if (playerSum > dealerSum){ // check if playerSum is > dealerSum
                        message = "You Win"; // sets message to You Win
                    }
                    g.setFont(new Font("Arial", Font.PLAIN, 75)); // sets g font 
                    g.setColor(Color.white); // sets color of g
                    g.drawString(message,80,290); // draws message to graphic
                    bjPlayAgainBTN.setVisible(true); // display play again button
                }

            } catch (Exception e) {
                e.printStackTrace();
            }// end of try block
        } // end of paintComponenet method
    } // end of customPanel class
    
    public void startGame(){ // start of startGame method
        buildDeck(); // call build deck method
        shuffleDeck(); // call shuffleDeck method
        
       
        dealerHand = new ArrayList<Card>(); // initialise dealerHand
        dealerSum = 0; // initialise dealerSum
        dealerAceCount = 0; // initialise dealerAceCount
        
        hiddenCard = deck.remove(deck.size()-1); // initialise dealers hidden card
        dealerSum += hiddenCard.getValue(); // add hidden cards value to dealers sum
        dealerAceCount += hiddenCard.isAce() ? 1 : 0; // check if hidden card is ace and add to dealerAceCount
        
        Card card = deck.remove(deck.size()-1); // initialise new card from deck for dealer
        dealerSum += card.getValue(); // add cards value to dealerSum
        dealerAceCount += card.isAce() ? 1 : 0; // check if card is ace and add to dealerAceCount
        dealerHand.add(card); // add card to dealerHand
        
        // Player
        
        playerHand = new ArrayList<Card>(); // initialise playerHand
        playerSum = 0; // initialise playerSum
        playerAceCount = 0; // initialise playerAceCount
        
        for(int i = 0; i < 2; i++){
            card = deck.remove(deck.size() - 1); // initialise new card from deck
            playerSum += card.getValue(); // get card value and add to playerSum
            playerAceCount += card.isAce() ? 1 : 0; // check if card is ace and add to playerAceCount

            playerHand.add(card); // add card to playerHand
        }
    }// end of startGame method
    
    public void buildDeck(){ // start of buildDeck method
        deck = new ArrayList<Card>(); // initialise deck
        String[] values = {"A", "2", "3", "4","5", "6", "7", "8","9", "10", "J", "Q","K"}; // initialise array of values
        String[] types = {"E", "F", "W", "G"}; // initialise array of types
        
        for(int i = 0; i < types.length; i++){
            for(int j = 0; j< values.length; j++){
                Card card = new Card(values[j], types[i]); // initialise new card with value and type
                deck.add(card); // add card to deck
            }
        }
    } // end of buildDeck method
    
    public void shuffleDeck(){ // start of shuffleDeck
        for (int i = 0; i< deck.size(); i++){
            int j = random.nextInt(deck.size());
            Card currCard = deck.get(i); // take current card from deck
            Card randomCard = deck.get(j); // get random card from deck
            
            deck.set(i, randomCard); // set i to random card
            deck.set(j, currCard); // set j to currCard
        }
    } // end of shuffleDeck card
    
    public int reducePlayerAce(){ // start of reducePlayerAce method
        while(playerSum > 21 && playerAceCount > 0){
            playerSum -= 10; // minus 10 from playerSum
            playerAceCount -= 1; // minus 1 from playerAceCount
        }
        return playerSum; 
    } // end of reducePlayerAce
    
    public int reduceDealerAce(){ // start of reduceDealerAce
        while(dealerSum > 21 && dealerAceCount > 0){
            dealerSum -= 10; // minus 10 from dealerSum
            dealerAceCount -= 1; // minus 1 from dealerAceCount
        }
        return dealerSum;
    } // end of reduceDealerAce
    
    public void playAgain(){ // start of playAgain
        
        deck.clear(); // clear deck
        playerHand.clear(); // clear players hand
        dealerHand.clear(); // clear dealers hand
        
        playerSum = 0; // clear players sum
        playerAceCount = 0; // clear players ace count
        dealerSum = 0; // clear dealers sum
        dealerAceCount = 0; // clear dealers ace count
        
        stayButton.setEnabled(true); // enable stay button
        hitButton.setEnabled(true); // enable hit button
        bjPlayAgainBTN.setVisible(false); // enable bjPlayAgainBTN
        
        startGame(); // call startGame method
        repaint();//call repaint method
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonPanel = new javax.swing.JPanel();
        hitButton = new javax.swing.JButton();
        stayButton = new javax.swing.JButton();
        gamePanel = new customPanel();
        bjPlayAgainBTN = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(650, 650));
        setMinimumSize(new java.awt.Dimension(650, 650));
        setLayout(new java.awt.BorderLayout());

        buttonPanel.setBackground(new java.awt.Color(153, 0, 0));
        buttonPanel.setMinimumSize(new java.awt.Dimension(650, 100));
        buttonPanel.setPreferredSize(new java.awt.Dimension(650, 100));

        hitButton.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        hitButton.setForeground(new java.awt.Color(0, 51, 0));
        hitButton.setText("Hit");
        hitButton.setFocusable(false);
        hitButton.setMaximumSize(new java.awt.Dimension(300, 50));
        hitButton.setMinimumSize(new java.awt.Dimension(300, 50));
        hitButton.setPreferredSize(new java.awt.Dimension(300, 50));
        hitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hitButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(hitButton);

        stayButton.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        stayButton.setForeground(new java.awt.Color(0, 51, 0));
        stayButton.setText("Stay");
        stayButton.setFocusable(false);
        stayButton.setMaximumSize(new java.awt.Dimension(300, 50));
        stayButton.setMinimumSize(new java.awt.Dimension(300, 50));
        stayButton.setPreferredSize(new java.awt.Dimension(300, 50));
        stayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stayButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(stayButton);

        add(buttonPanel, java.awt.BorderLayout.PAGE_END);

        gamePanel.setBackground(new java.awt.Color(53, 101, 77));
        gamePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 0), 20));
        gamePanel.setMaximumSize(new java.awt.Dimension(650, 550));
        gamePanel.setMinimumSize(new java.awt.Dimension(650, 550));
        gamePanel.setPreferredSize(new java.awt.Dimension(650, 550));

        bjPlayAgainBTN.setVisible(false);
        bjPlayAgainBTN.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        bjPlayAgainBTN.setText("Play Again");
        bjPlayAgainBTN.setMaximumSize(new java.awt.Dimension(600, 50));
        bjPlayAgainBTN.setMinimumSize(new java.awt.Dimension(600, 50));
        bjPlayAgainBTN.setPreferredSize(new java.awt.Dimension(600, 50));
        bjPlayAgainBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bjPlayAgainBTNActionPerformed(evt);
            }
        });
        gamePanel.add(bjPlayAgainBTN);

        add(gamePanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void hitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hitButtonActionPerformed
        Card card = deck.remove(deck.size() - 1); // initialise new card from deck
        playerSum += card.getValue(); // add cards value to playerSum
        playerAceCount += card.isAce() ? 1 : 0; // check if card is ace and add to playerAceCount
        playerHand.add(card); // add card to playerHand
        gamePanel.repaint(); // call repaint method on gamePanel
        if (reducePlayerAce() > 21){
            hitButton.setEnabled(false); // disable hitButton
        }
    }//GEN-LAST:event_hitButtonActionPerformed

    private void stayButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stayButtonActionPerformed
        hitButton.setEnabled(false); // disable hitButton
        stayButton.setEnabled(false); // disable stayButton
        
        while(dealerSum < 17){
            Card card = deck.remove(deck.size() - 1); // initialise new card from deck
            dealerSum += card.getValue(); // add cards value to dealers sum
            dealerAceCount += card.isAce()? 1 : 0; // check if card is ace and add to dealerAceCount
            dealerHand.add(card); // add card to dealers hand
        }
        gamePanel.repaint(); // call repaint method on gamePanel
    }//GEN-LAST:event_stayButtonActionPerformed

    private void bjPlayAgainBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bjPlayAgainBTNActionPerformed
        playAgain(); // call play again button
    }//GEN-LAST:event_bjPlayAgainBTNActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bjPlayAgainBTN;
    private javax.swing.JPanel buttonPanel;
    public static javax.swing.JPanel gamePanel;
    private javax.swing.JButton hitButton;
    private javax.swing.JButton stayButton;
    // End of variables declaration//GEN-END:variables
} // end of BlackJack class
