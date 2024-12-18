/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Games.Connect4;

/**
 *
 * @author Dreel
 */
public class Connect4Tile extends javax.swing.JPanel {
    char Letter;
    char Number;
    String Position;
    int State; // 0 = Empty, 1 = Red, 2 = Yellow
    
    /**
     * Creates new form Connect4Tile
     */
    public Connect4Tile() {
        initComponents();
        this.State = 0;
    }
    
    public Connect4Tile(char Letter) {
        initComponents();
        this.Letter = Letter;
        this.State = 0;
    }

    public void setPosition(char Number, char Letter){
        this.Letter = Letter;
        
    }
    
    public void setRed(){
        Tile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/img/Game Assets/Connect4 Assets/folder/Red Tile.png")));
        this.State = 1;

    }
    
    public void setYellow(){
        Tile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/img/Game Assets/Connect4 Assets/folder/Yellow Tile.png")));
        this.State = 2;

    }
    
    public void setColor(String color){
        if(color == "Red"){
            Tile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/img/Game Assets/Connect4 Assets/folder/Red Tile.png")));
            this.State = 1;
        }
        else if(color == "Yellow"){
            Tile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/img/Game Assets/Connect4 Assets/folder/Yellow Tile.png")));
            this.State = 2;
        }
    }
    
    public int checkState(){
        return this.State;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Tile = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(100, 100));
        setMinimumSize(new java.awt.Dimension(100, 100));
        setPreferredSize(new java.awt.Dimension(100, 100));

        Tile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/img/Game Assets/Connect4 Assets/folder/Empty Tile.png"))); // NOI18N
        Tile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TileMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Tile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Tile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void TileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TileMouseClicked
        
    }//GEN-LAST:event_TileMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Tile;
    // End of variables declaration//GEN-END:variables
}
