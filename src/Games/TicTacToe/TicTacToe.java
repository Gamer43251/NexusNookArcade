/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Games.TicTacToe;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import nexusnookarcade.NexusNookArcade;

/**
 *
 * @author Dreel
 */
public class TicTacToe extends javax.swing.JPanel { // start of TicTacToe class

    /**
     * Creates new form TicTacToe
     */
    public TicTacToe() { // start of TicTacToe constructor
        try (InputStream fontStream = getClass().getClassLoader().getResourceAsStream("Resources/fonts/AUTOMANI.TTF")) {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream); // create custom font
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment(); 
            ge.registerFont(customFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        
        Font Automani = new Font("Automani", Font.PLAIN, 100);
        
        initComponents();
    } // end of TicTacToe constructor

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TTTGameOver = new javax.swing.JLayeredPane();
        GameOverLabel = new javax.swing.JLabel();
        TransparentBackground = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        TurnLabel = new javax.swing.JLabel();
        BTN1 = new javax.swing.JLabel();
        BTN2 = new javax.swing.JLabel();
        BTN3 = new javax.swing.JLabel();
        BTN4 = new javax.swing.JLabel();
        BTN5 = new javax.swing.JLabel();
        BTN6 = new javax.swing.JLabel();
        BTN7 = new javax.swing.JLabel();
        BTN8 = new javax.swing.JLabel();
        BTN9 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(573, 650));
        setMinimumSize(new java.awt.Dimension(573, 650));
        setPreferredSize(new java.awt.Dimension(573, 650));
        setLayout(new javax.swing.OverlayLayout(this));

        TTTGameOver.setVisible(false);

        GameOverLabel.setFont(new java.awt.Font("OCR A Extended", 1, 48)); // NOI18N
        GameOverLabel.setForeground(new java.awt.Color(255, 255, 255));
        GameOverLabel.setText("Game Over");

        TransparentBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/img/Game Assets/TTT Assets/GameOver.png"))); // NOI18N

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/img/Game Assets/TTT Assets/Play Again BTN.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        TTTGameOver.setLayer(GameOverLabel, javax.swing.JLayeredPane.DRAG_LAYER);
        TTTGameOver.setLayer(TransparentBackground, javax.swing.JLayeredPane.DEFAULT_LAYER);
        TTTGameOver.setLayer(jLabel1, javax.swing.JLayeredPane.DRAG_LAYER);

        javax.swing.GroupLayout TTTGameOverLayout = new javax.swing.GroupLayout(TTTGameOver);
        TTTGameOver.setLayout(TTTGameOverLayout);
        TTTGameOverLayout.setHorizontalGroup(
            TTTGameOverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TTTGameOverLayout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jLabel1)
                .addContainerGap(84, Short.MAX_VALUE))
            .addGroup(TTTGameOverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TTTGameOverLayout.createSequentialGroup()
                    .addContainerGap(147, Short.MAX_VALUE)
                    .addComponent(GameOverLabel)
                    .addContainerGap(147, Short.MAX_VALUE)))
            .addGroup(TTTGameOverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(TransparentBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 573, Short.MAX_VALUE))
        );
        TTTGameOverLayout.setVerticalGroup(
            TTTGameOverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TTTGameOverLayout.createSequentialGroup()
                .addContainerGap(460, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(130, 130, 130))
            .addGroup(TTTGameOverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TTTGameOverLayout.createSequentialGroup()
                    .addContainerGap(98, Short.MAX_VALUE)
                    .addComponent(GameOverLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(395, Short.MAX_VALUE)))
            .addGroup(TTTGameOverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(TransparentBackground, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(TTTGameOver);

        jPanel1.setMaximumSize(new java.awt.Dimension(573, 650));
        jPanel1.setMinimumSize(new java.awt.Dimension(573, 650));
        jPanel1.setPreferredSize(new java.awt.Dimension(573, 650));

        TurnLabel.setBackground(new java.awt.Color(233, 12, 12));
        TurnLabel.setFont(new java.awt.Font("OCR A Extended", 1, 48)); // NOI18N
        TurnLabel.setForeground(new java.awt.Color(0, 255, 255));
        TurnLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TurnLabel.setText("Users Turn");
        TurnLabel.setToolTipText("");
        TurnLabel.setOpaque(true);

        BTN1.setBackground(new java.awt.Color(0, 18, 255));
        BTN1.setFont(NexusNookArcade.newFont("/Resources/fonts/ka1.TTF"));
        BTN1.setForeground(new java.awt.Color(255, 49, 49));
        BTN1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BTN1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(233, 12, 12), 5));
        BTN1.setOpaque(true);
        BTN1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BTN1MouseClicked(evt);
            }
        });

        BTN2.setBackground(new java.awt.Color(0, 18, 255));
        BTN2.setFont(NexusNookArcade.newFont("/Resources/fonts/ka1.TTF"));
        BTN2.setForeground(new java.awt.Color(92, 225, 230));
        BTN2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BTN2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(233, 12, 12), 5));
        BTN2.setOpaque(true);
        BTN2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BTN2MouseClicked(evt);
            }
        });

        BTN3.setBackground(new java.awt.Color(0, 18, 255));
        BTN3.setFont(NexusNookArcade.newFont("/Resources/fonts/ka1.TTF"));
        BTN3.setForeground(new java.awt.Color(255, 49, 49));
        BTN3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BTN3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(233, 12, 12), 5));
        BTN3.setOpaque(true);
        BTN3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BTN3MouseClicked(evt);
            }
        });

        BTN4.setBackground(new java.awt.Color(0, 18, 255));
        BTN4.setFont(NexusNookArcade.newFont("/Resources/fonts/ka1.TTF"));
        BTN4.setForeground(new java.awt.Color(255, 49, 49));
        BTN4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BTN4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(233, 12, 12), 5));
        BTN4.setOpaque(true);
        BTN4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BTN4MouseClicked(evt);
            }
        });

        BTN5.setBackground(new java.awt.Color(0, 18, 255));
        BTN5.setFont(NexusNookArcade.newFont("/Resources/fonts/ka1.TTF"));
        BTN5.setForeground(new java.awt.Color(255, 49, 49));
        BTN5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BTN5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(233, 12, 12), 5));
        BTN5.setOpaque(true);
        BTN5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BTN5MouseClicked(evt);
            }
        });

        BTN6.setBackground(new java.awt.Color(0, 18, 255));
        BTN6.setFont(NexusNookArcade.newFont("/Resources/fonts/ka1.TTF"));
        BTN6.setForeground(new java.awt.Color(255, 49, 49));
        BTN6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BTN6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(233, 12, 12), 5));
        BTN6.setOpaque(true);
        BTN6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BTN6MouseClicked(evt);
            }
        });

        BTN7.setBackground(new java.awt.Color(0, 18, 255));
        BTN7.setFont(NexusNookArcade.newFont("/Resources/fonts/ka1.TTF"));
        BTN7.setForeground(new java.awt.Color(255, 49, 49));
        BTN7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BTN7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(233, 12, 12), 5));
        BTN7.setOpaque(true);
        BTN7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BTN7MouseClicked(evt);
            }
        });

        BTN8.setBackground(new java.awt.Color(0, 18, 255));
        BTN8.setFont(NexusNookArcade.newFont("/Resources/fonts/ka1.TTF"));
        BTN8.setForeground(new java.awt.Color(255, 49, 49));
        BTN8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BTN8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(233, 12, 12), 5));
        BTN8.setOpaque(true);
        BTN8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BTN8MouseClicked(evt);
            }
        });

        BTN9.setBackground(new java.awt.Color(0, 18, 255));
        BTN9.setFont(NexusNookArcade.newFont("/Resources/fonts/ka1.TTF"));
        BTN9.setForeground(new java.awt.Color(255, 49, 49));
        BTN9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BTN9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(233, 12, 12), 5));
        BTN9.setOpaque(true);
        BTN9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BTN9MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TurnLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BTN1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BTN4, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BTN2, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BTN5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BTN3, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BTN6, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(BTN7, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(BTN8, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(BTN9, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(TurnLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BTN1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BTN3, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BTN4, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BTN6, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(BTN2, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(BTN5, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BTN7, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTN9, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTN8, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents

    private void BTN1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN1MouseClicked
        TicTacToeLogic.checkBTN(BTN1); // call checkBTN method on BTN1
    }//GEN-LAST:event_BTN1MouseClicked

    private void BTN2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN2MouseClicked
        TicTacToeLogic.checkBTN(BTN2); // call checkBTN method on BTN2
    }//GEN-LAST:event_BTN2MouseClicked

    private void BTN3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN3MouseClicked
        TicTacToeLogic.checkBTN(BTN3); // call checkBTN method on BTN3
    }//GEN-LAST:event_BTN3MouseClicked

    private void BTN4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN4MouseClicked
        TicTacToeLogic.checkBTN(BTN4); // call checkBTN method on BTN4
    }//GEN-LAST:event_BTN4MouseClicked

    private void BTN5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN5MouseClicked
        TicTacToeLogic.checkBTN(BTN5); // call checkBTN method on BTN5
    }//GEN-LAST:event_BTN5MouseClicked

    private void BTN6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN6MouseClicked
        TicTacToeLogic.checkBTN(BTN6); // call checkBTN method on BTN6
    }//GEN-LAST:event_BTN6MouseClicked

    private void BTN7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN7MouseClicked
        TicTacToeLogic.checkBTN(BTN7); // call checkBTN method on BTN7
    }//GEN-LAST:event_BTN7MouseClicked

    private void BTN8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN8MouseClicked
        TicTacToeLogic.checkBTN(BTN8); // call checkBTN method on BTN8
    }//GEN-LAST:event_BTN8MouseClicked

    private void BTN9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN9MouseClicked
        TicTacToeLogic.checkBTN(BTN9); // call checkBTN method on BTN9
    }//GEN-LAST:event_BTN9MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        TicTacToeLogic.playAgain(); // call playAgain method
    }//GEN-LAST:event_jLabel1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel BTN1;
    public static javax.swing.JLabel BTN2;
    public static javax.swing.JLabel BTN3;
    public static javax.swing.JLabel BTN4;
    public static javax.swing.JLabel BTN5;
    public static javax.swing.JLabel BTN6;
    public static javax.swing.JLabel BTN7;
    public static javax.swing.JLabel BTN8;
    public static javax.swing.JLabel BTN9;
    public static javax.swing.JLabel GameOverLabel;
    public static javax.swing.JLayeredPane TTTGameOver;
    private javax.swing.JLabel TransparentBackground;
    public static javax.swing.JLabel TurnLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
} // end of TicTacToe class
