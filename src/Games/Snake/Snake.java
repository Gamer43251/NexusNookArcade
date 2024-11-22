/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Games.Snake;

import java.awt.*;
import java.awt.event.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import nexusnookarcade.Display;
import nexusnookarcade.NexusNookArcade;
import nexusnookarcade.User;
/**
 *
 * @author Dreel
 */
public final class Snake extends javax.swing.JPanel implements ActionListener{ // start of Snake class
    
    private class Fruit{ // start of Fruit class
            
        int x_Pos = random.nextInt(); // initialise x_Pos for fruit
        int y_Pos = random.nextInt(); // initialise y_pos for fruit
        int value; // iinitialise value 
        Image img; // initialise img
        Color snakeColor; // initialise snakeColor
            
        public Fruit(int x_Pos, int y_Pos, int value, Image img, Color snakeColor){ // start of Fruit constructor
            this.x_Pos = x_Pos; // set fruits x_pos
            this.y_Pos = y_Pos; // set fruits y_pos
            this.value = value; // set fruits value
            this.img = img; // set fruits img
            this.snakeColor = snakeColor; // set fruits color for snake 
        } // end of Fruit constructor
        
        public String toString(){ // start of toString method
            return snakeColor.toString(); // return snakeColor
        } // end of toString method
        
        public void repositionFruit(){
            // Calculate the number of possible positions (steps) along each axis
            int maxX = (SCREEN_WIDTH / UNIT_SIZE) - 1;
            int maxY = (SCREEN_HEIGHT / UNIT_SIZE) - 1;

            // Generate a random position within the range and multiply by UNIT_SIZE for grid alignment
            this.x_Pos = random.nextInt(0, maxX + 1) * UNIT_SIZE;
            this.y_Pos = random.nextInt(0, maxY + 1) * UNIT_SIZE;
        }

    } // end of fruit class
    
    private class bodyPart { // start of bodyPart class
        int x_Pos; // initialise x pos for bodypart
        int y_Pos; // initialise y pos for bodypart
        Color Color; // initialise color for bodypart
        
        public bodyPart(int x_Pos, int y_Pos, Color Color){ // start of bodyPart constructor
            this.x_Pos = x_Pos; // set bodyparts x pos
            this.y_Pos = y_Pos; // set bodyparts y pos
            this.Color = Color; // set bodyparts color
            
        } // end of bodypart constructor
    } // end of bodyPart class
    
    static String userHome = System.getProperty("user.home"); //Initialises a string linking to the users home
    static Path documentsPath = Paths.get(userHome, "Documents", "NexusNookArcade");//sets a path to where files will be stored
    static String url = "jdbc:sqlite:" + documentsPath.resolve("database.db").toString(); // creates url linking to database
    static User user = NexusNookArcade.getUser(); // Store current logged in user
    static final int SCREEN_WIDTH = 650; // initiailize SCREEN_WIDTH for snake
    static final int SCREEN_HEIGHT = 650; // initialize SCREEN_HEIGHT for snake
    static int UNIT_SIZE = 25; // initialise UNIT_SIZE
    static final int DELAY = 100; // initialise DELAY
    ArrayList<bodyPart> snake = new ArrayList<bodyPart>(); // initialise arrayList of snakes bodypatrys
    char direction = 'D'; // initialise direction
    boolean running = false; // initialise running boolean
    boolean played = false; // initialise played boolean 
    boolean over = false; // initialise over boolean
    Timer timer; // initialise timer
    Random random = new Random(); //initiallise a random
    int score = 0; // initialise score
    ArrayList<Fruit>basket = new ArrayList<Fruit>(); // initialise ArrayList of fruit 
    Fruit fruit = new Fruit(50, 50, 1, new ImageIcon(getClass().getResource("/Resources/img/Game Assets/Snake Assets/Fruit/Apple.png")).getImage(), Color.green);; // initialise apple
    bodyPart body; // initialise body
    int prevX, prevY; // inigtialise prevX & prevY
    
    
        Image apple_image = new ImageIcon(getClass().getResource("/Resources/img/Game Assets/Snake Assets/Fruit/Apple.png")).getImage(); // initialise apple image
        Image grape_image = new ImageIcon(getClass().getResource("/Resources/img/Game Assets/Snake Assets/Fruit/Grape.png")).getImage(); // initialise grape image
        Image strawberry_image = new ImageIcon(getClass().getResource("/Resources/img/Game Assets/Snake Assets/Fruit/Strawberry.png")).getImage(); // initialise strawberry image
        Image orange_image = new ImageIcon(getClass().getResource("/Resources/img/Game Assets/Snake Assets/Fruit/Orange.png")).getImage(); // initialise orange image
        Image banana_image = new ImageIcon(getClass().getResource("/Resources/img/Game Assets/Snake Assets/Fruit/Banana.png")).getImage(); // iinitialise banana image
        Image peach_image = new ImageIcon(getClass().getResource("/Resources/img/Game Assets/Snake Assets/Fruit/Peach.png")).getImage(); // initialise peach image
        Image plum_image = new ImageIcon(getClass().getResource("/Resources/img/Game Assets/Snake Assets/Fruit/Plum.png")).getImage(); // initialise plum image
        Image raspberry_image = new ImageIcon(getClass().getResource("/Resources/img/Game Assets/Snake Assets/Fruit/Raspberry.png")).getImage(); // initialise raspberry image
        Image blueberry_image = new ImageIcon(getClass().getResource("/Resources/img/Game Assets/Snake Assets/Fruit/Blueberry.png")).getImage(); // initialise blueberry image
        
        Image headUp_image = new ImageIcon(getClass().getResource("/Resources/img/Game Assets/Snake Assets/Body/headUp.png")).getImage(); // initialise headUp image
        Image headLeft_image = new ImageIcon(getClass().getResource("/Resources/img/Game Assets/Snake Assets/Body/headLeft.png")).getImage(); // initialise headLeft image
        Image headDown_image = new ImageIcon(getClass().getResource("/Resources/img/Game Assets/Snake Assets/Body/headDown.png")).getImage(); // initialise headDown image
        Image headRight_image = new ImageIcon(getClass().getResource("/Resources/img/Game Assets/Snake Assets/Body/headRight.png")).getImage(); // initialise headRight image
        
        Fruit apple = new Fruit(0, 0, 1, apple_image, Color.green); // initialise apple
        Fruit grape = new Fruit(0, 0, 2, grape_image, new Color(128,0,128)); // initialise grape
        Fruit strawberry = new Fruit(0, 0, 4, strawberry_image, Color.red); // initialise strawberry
        Fruit orange = new Fruit(0, 0, 6, orange_image, Color.orange); // initialise orange
        Fruit banana = new Fruit(0, 0, 10, banana_image, Color.yellow); // initialise banana
        Fruit peach = new Fruit(0, 0, 15, peach_image, new Color(255,229,180)); // initialise peach
        Fruit plum = new Fruit(0, 0, 20, plum_image, new Color(255,0,255));// initialise plum
        Fruit raspberry = new Fruit(0, 0, 25, raspberry_image, Color.pink); // initialise raspberry
        Fruit blueberry = new Fruit(0, 0, 30, blueberry_image, Color.blue); // initialise blueberry
        
        boolean appleAdded = false; // initialise applea dded
        boolean grapeAdded = false; // initialise grape added
        boolean strawberryAdded = false; // initialise starwberry added
        boolean orangeAdded = false; // initialise orange added
        boolean bananaAdded = false; // initialise banana added
        boolean peachAdded = false; // initialise peach added
        boolean plumAdded = false; // initialise plum added
        boolean raspberryAdded = false; // initialise raspberry added
        boolean blueberryAdded = false; // initialise blueberry added
        
        Image headImage = headDown_image; // initialise head image
        

    
    public Snake() { // start of snake Constructor
        emptyBasket(); // call emptyBasket method
        fillBasket(); // call fillBasket method
        newApple(); // call newApple method
        initComponents(); // call initComponents method
        this.addKeyListener(new MyKeyAdapter()); // add KeyAdapter
        snake.add(new bodyPart(0,0,Color.green)); // add bodypart to snake 
    }// end of snake Constructor
    
    public void emptyBasket(){ // start of emptyBasket method
         grapeAdded = false; // disable grapeAdded
         strawberryAdded = false; // disable strawberryAdded
         orangeAdded = false; // disable orangeAdded
         bananaAdded = false; // disable bananaAdded
         peachAdded = false; // disable peachAdded
         plumAdded = false; // disable plumAdded
         raspberryAdded = false; // disable raspberryAdded
         blueberryAdded = false; // disable bleberryAdded
    } // end of emptyBasket method
    
    public void fillBasket(){ // start of fill basket method
        if(score >= 0){
            if(!appleAdded){
                basket.add(apple); // add apple
                appleAdded = true; // enable appleAdded
            }
            if(score >= 10 && !grapeAdded){
                basket.add(grape); // add grape
                grapeAdded = true; // enable grapeAdded
            }
            if(score >= 20 && !strawberryAdded){
                basket.add(strawberry); // add strawberry
                strawberryAdded = true; // enable starwberryAdded
            }
            if(score >= 30 && !orangeAdded){
                basket.add(orange); // add orange
                orangeAdded = true; // enable orangeAdded
            }
            if(score >= 50 && !bananaAdded){
                basket.add(banana); // add banana
                bananaAdded = true; // enable bananaAdded
            }
            if(score >= 75 && !peachAdded){
                basket.add(peach); // add peach
                peachAdded = true; // enable peachAdded
            }
            if(score >= 100 && !plumAdded){
                basket.add(plum); // add plum
                plumAdded = true; // enable plumAdded
            }
            if(score >= 125 && !raspberryAdded){
                basket.add(raspberry); // add raspberry
                raspberryAdded = true;// enable raspberryAdded
            }
            if(score >= 150 && !blueberryAdded){
                basket.add(blueberry); // add blueberry
                blueberryAdded = true; // enable blueberryAdded
            }
        }
    } // end of fillBasket method

    public void startGame() { // start of startGame method
        user = NexusNookArcade.getUser(); // call user from NexusNookArcade
        if (running == false){
            played = true; // enable player
            newApple(); // call newApple method
            running = true; //enable running

            timer = new Timer(DELAY, this); // initialise timer
            timer.start(); // start timer
            displayScore(); // call displayScore method
            getHighScore(); // call getHightScore method
        }
    } // end of startGame method

    public void paintComponent(Graphics g){ // start of paint component method
        super.paintComponent(g); // call paint componenets from super class
        draw(g); // call draw method
    } // end of paint componenets method
    
    public void draw(Graphics g){  // start of draw method

        if (running) {
        // start drawing checkered background
        for (int i = 0; i < SCREEN_WIDTH / UNIT_SIZE; i++) {
            for (int j = 0; j < SCREEN_HEIGHT / UNIT_SIZE; j++) {
                if ((i + j) % 2 == 0) {
                    g.setColor(new Color(173, 216, 230)); // Light blue color
                } else {
                    g.setColor(new Color(135, 206, 250)); // Sky blue color
                }
                g.fillRect(i * UNIT_SIZE, j * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
            }
        } // finish drawing checkered background

            
        g.drawImage(fruit.img, fruit.x_Pos, fruit.y_Pos, UNIT_SIZE, UNIT_SIZE, null); // draw fruit
            for(int i = 0; i < snake.size(); i++){
                if(i == 0){
                    g.drawImage(headImage, snake.get(i).x_Pos,snake.get(i).y_Pos,UNIT_SIZE,UNIT_SIZE, null); // draw snake head
                }
                else {
                    g.setColor(snake.get(i).Color);  // set color to snake body part color
                    g.fillRect(snake.get(i).x_Pos,snake.get(i).y_Pos,UNIT_SIZE,UNIT_SIZE); // fill rect at bodyparts x & y pos
                    
                }
            }
        }
        else if(!running && over){
            gameOver(g); // call gameOver method
        }
        else{
            startScreen(g); // call startScreen method
        }
    } // end of draw method
    
    public void newApple(){  // start of newApple method
        
        fillBasket(); // call fillBasket method
        int i = random.nextInt(basket.size()); // initialise i between 0 and size of basket
        fruit = basket.get(i); // get fruit from basket at i index
        fruit.repositionFruit(); // call respositionFruit method on fruit
    } // end of newApple method
    
    public void move(){ // start of move method
        checkCollisions(); // call checkCollisions method
        
        prevX = snake.get(0).x_Pos; // set prevX to snake heads x_Pos
        prevY = snake.get(0).y_Pos; // set prevY to snake heads y_Pos
        int tempX, tempY; // initialise tempX & tempY
        for(int i = 1; i < snake.size(); i++){
            // Swap positions
            tempX = snake.get(i).x_Pos; // set tempX to bodypart at index i x_Pos
            tempY = snake.get(i).y_Pos; // set tempY to bodypart at index i y_Pos
            snake.get(i).x_Pos = prevX; // set bodypart at index i x_pos to prevX
            snake.get(i).y_Pos = prevY; // set bodypart at index i y_pos to prevY
            prevX = tempX; // set prevX to tempX
            prevY = tempY; // set prevY to tempY
        }
            
        
        switch(direction){ 
        case 'U':
            snake.get(0).y_Pos -= UNIT_SIZE; // take UNIT_Size away from snake heads y_pos
            break;
        case 'D':
            snake.get(0).y_Pos += UNIT_SIZE; // add UNIT_SIZE to snake heads y_pos
            break;
        case 'L':
            snake.get(0).x_Pos -= UNIT_SIZE; // take UNIT_Size away from snake heads x_pos
            break;
        case 'R':
            snake.get(0).x_Pos += UNIT_SIZE; // add UNIT_SIZE to snake heads x_pos
            break;
        }
    } // end of move method
    
    public void checkApple(){ // start of checkApple method
    if((snake.get(0).x_Pos == fruit.x_Pos) && (snake.get(0).y_Pos == fruit.y_Pos)){

        // Add a new body part at the position where the last part was before moving
        // This is a simplified approach; consider tail's last direction for more accuracy
        snake.add(new bodyPart(prevX, prevY, fruit.snakeColor));
        score+= fruit.value;
        newApple(); // Reposition/respawn the fruit
        displayScore(); // call displayScore method
        }
    } // end of checkApple method
    
    public void checkCollisions(){// start of checkCollisions method
        // Checks if head collides with body
       bodyPart head = snake.get(0); // get head from snake arraylist
       for(int i = snake.size() - 1; i > 0; i--){
           body = snake.get(i); // get body part at index i
           if((head.x_Pos == body.x_Pos)&& (head.y_Pos == body.y_Pos)){
               running = false; // disable running
           }
       }
       
       //Check if head collides with left border
       if(head.x_Pos < 0){
           running = false; // disable running
           over = true; // enable over
           repaint(); // call reapint method
       }
       
       //Check if head collides with right border
       if(head.x_Pos > SCREEN_WIDTH - UNIT_SIZE){
           running = false; // disable running
           over = true; // enable over
           repaint(); // call repaint method
       }
       
       //Check if head collides with top border
       if(head.y_Pos < 0){
           running = false; // disable running
           over = true; // enable over
           repaint(); // call repaint method
       }
       
        //Check if head collides with bottom border
        if(head.y_Pos > SCREEN_HEIGHT - UNIT_SIZE){
           running = false; // disable running
           over = true; // enable over
           repaint(); // call repaint method
       }
       
            
    } // end of checkCollisions method
    
    public void gameOver(Graphics g){ // start of gameOver method
        if(score > user.getSnakeHigh()){
            updateHighScore(score); // call updateHighScore method passing score
            user.updateUser(); // call updateUser method on user
        }
        //Game Over Text
        g.setColor(Color.red);
        g.setFont(new Font("Comic Sans MS", 1, 48));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics.stringWidth("Game Over")) / 2 , SCREEN_HEIGHT / 2);
    } // end of gameOver method
    
    public void startScreen(Graphics g){ // start of startScreen method
        
        //draw start screen
        g.setColor(Color.white);
        g.setFont(new Font("Comic Sans MS", 1, 48));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Welcome to snake", (SCREEN_WIDTH - metrics.stringWidth("Welcome to Snake")) / 2 , SCREEN_HEIGHT / 2);
        g.setFont(new Font("Comic Sans MS", 1, 24));
        metrics = getFontMetrics(g.getFont());
        g.drawString("Press Space to start", (SCREEN_WIDTH - metrics.stringWidth("Press Space To Start")) / 2 , SCREEN_HEIGHT / 3);
    } // end of startScreen
    
    public void playAgain() { // start of playAgain method
        headImage = headDown_image; // reset headimage
        score = 0; // reset score
        emptyBasket(); // call emptyBasket method
        running = false; // disable running
        snake.clear(); // clear snake
        snake.add(new bodyPart(0,0,Color.green)); // add head to snake
        basket.clear(); // clear basket
        basket.add(apple); // add apple to basket
        direction = 'D'; // reset direction
        newApple(); // call newApplie method
        if (timer != null && timer.isRunning()) {
            timer.stop();  // Stop the timer if it is running
        }
        timer.stop(); // stop timer
        repaint();  // Redraw the panel with the reset values
        startGame(); // call start game method
    } // end of playAgain method
    
    @Override
    public void actionPerformed(ActionEvent e) { //start of actionPerformed method
        
        if(running){
            move(); // call move method
            checkApple(); // call checkApple method
            
        }
        repaint(); // call repaint method
       
    } // end of actionPerformed method
    
    public void displayScore(){ // start of displayScore method
        Display.SnakeScore.setText("Score \n" + score); // display score to label
    } // end of displayScore method
    
    public void getHighScore(){ // start of getHighScore
        int HS = user.getSnakeHigh(); // initialise HS as users snake high score
        Display.snakeHighScore.setText("Highcore \n" + HS); // display users high score to label
    } // end of getHighScore
    
    public void updateHighScore(int highScore){ // start of updateHighScore
        String sql = "UPDATE Users SET Snake_HS = ? WHERE UUID = ?"; // sql query to update users high score
        String userID = user.getID(); // initialise user ID
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) { // stores sql as prepared statement
            
            pstmt.setInt(1, highScore); // set the value of 1st ?
            pstmt.setString(2, userID); // set the value of 2nd ?

            // Execute the update statement and get the count of updated rows
            int updatedRows = pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            
        }
    } // end of updateHighScore

    
    public class MyKeyAdapter extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if (direction != 'R') {
                    direction = 'L'; // set direction to L 
                    headImage = headLeft_image; // change headImage
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (direction != 'L') {
                    direction = 'R'; // set direction to R
                    headImage = headRight_image; // change headImage
                }
                break;
            case KeyEvent.VK_UP:
                if (direction != 'D') {
                    direction = 'U'; // set direction to U
                    headImage = headUp_image; // change headImage
                }
                break;
            case KeyEvent.VK_DOWN:
                if (direction != 'U') {
                    direction = 'D'; // set direction to D
                    headImage = headDown_image; // update headImage
                }
                break;
            case KeyEvent.VK_A:
                if (direction != 'R') {
                    direction = 'L'; // set direction to L
                    headImage = headLeft_image; // update headImage
                }
                break;
            case KeyEvent.VK_D:
                if (direction != 'L') {
                    direction = 'R'; // set direction to R
                    headImage = headRight_image; // change headImage
                }
                break;
            case KeyEvent.VK_W:
                if (direction != 'D') {
                    direction = 'U'; // set direction to U
                    headImage = headUp_image; // change headImage
                }
                break;
            case KeyEvent.VK_S:
                if (direction != 'U') {
                    direction = 'D'; // set direction to D
                    headImage = headDown_image; // change headImage
                }
                break;
            case KeyEvent.VK_SPACE:
                if(!running){
                    if(!played){
                        startGame(); // call startGame method
                    }
                    else{
                      playAgain(); // call playAgain method
                    }
                }
                break;
        }
    }
    
}

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(0, 0, 0));
        setToolTipText("");
        setMaximumSize(new java.awt.Dimension(650, 650));
        setMinimumSize(new java.awt.Dimension(650, 650));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
} // end of snake Class
