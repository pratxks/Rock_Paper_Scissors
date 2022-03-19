
import javax.swing.JFrame;

public class RockPaperScissorsRunner 
{
    public static void main(String[] args) {
        RockPaperScissorsFrame myRockPaperScissorFrame = new RockPaperScissorsFrame();
        
        myRockPaperScissorFrame.setSize(800, 650);
        myRockPaperScissorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myRockPaperScissorFrame.setLocationRelativeTo(null);
        myRockPaperScissorFrame.setResizable(false);
        myRockPaperScissorFrame.createAndDisplayPanels();
        
        myRockPaperScissorFrame.setVisible(true);
    }
}
