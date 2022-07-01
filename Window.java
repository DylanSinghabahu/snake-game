import javax.swing.JFrame;
public class Window extends JFrame {
    Window() {
        GamePanel panel = new GamePanel();
        JFrame frame = new JFrame(); 
        frame.add(panel); // or this.add(panel
        //Line 5-6 Shortcut --> this.add(new Panel());
        frame.setTitle("Dylan's Snake Game");
        frame.setVisible(true); //makes the frame visible
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //gives exit button
        frame.setResizable(false); //hides bits outside the game dimensions
        frame.pack();
        frame.setLocationRelativeTo(null); //Sets window in centre of screen
    }
}