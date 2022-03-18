
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.print.Paper;
import java.util.Random;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

public class RockPaperScissorsFrame extends JFrame
{
    private JPanel mainPanel;
    private JPanel optionSelectionPanel;
    private JPanel gameStatsPanel;
    private JPanel resultDisplayPanel;
    
    private JButton rockButton;
    private JButton paperButton;
    private JButton scissorButton;
    private JButton quitButton;
    
    private ImageIcon rockImageIcon;
    private ImageIcon paperImageIcon;
    private ImageIcon scissorImageIcon;
    
    private JLabel playerWinsLabel;
    private JLabel computerWinsLabel;
    private JLabel tiesLabel;
    
    private JTextField playerWinsCount;
    private JTextField computerWinsCount;
    private JTextField tiesCount;
    
    private JLabel emptyLabel1;
    private JLabel emptyLabel2;
    private JLabel emptyLabel3;
    
    private JTextArea resultDisplayArea;
    private JScrollPane resultDisScrollPane;
    
    private Random randomOptionSelction = new Random();
    
    private int playerWinCount;
    private int computerWinCount;
    private int tieCount;
    
    private enum ButtonID
    {
        ROCK,
        PAPER,
        SCISSOR,
        EMPTY;
    }
    
    public ButtonID ButtonClicked(int clickedIndex)
    {
        if(clickedIndex == 0) return ButtonID.ROCK;
        if(clickedIndex == 1) return ButtonID.PAPER;
        if(clickedIndex == 2) return ButtonID.SCISSOR;
        
        return ButtonID.EMPTY;
    }
    
    public RockPaperScissorsFrame()
    {
        setTitle("Rock Paper Scissors Game");
        
        playerWinCount = 0;
        computerWinCount = 0;
        tieCount = 0;
    }
    
    public void rockPaperScissorButtonClicked(int userSelectedButtonId)
    { 
        String resultDisplayString = "";
        
        int computerSelectedButtonId = randomOptionSelction.nextInt(3);
        
        if(userSelectedButtonId == computerSelectedButtonId) 
        {
            tiesCount.setText(String.valueOf(++tieCount));
            resultDisplayString = "Game Ties ";
        }
        else if((ButtonClicked(userSelectedButtonId) == ButtonID.ROCK) 
                && (ButtonClicked(computerSelectedButtonId) == ButtonID.PAPER))
        {
            computerWinsCount.setText(String.valueOf(++computerWinCount));
            resultDisplayString = "Paper covers Rock (Computer Wins) ";
        }
        else if((ButtonClicked(userSelectedButtonId) == ButtonID.PAPER) 
                && (ButtonClicked(computerSelectedButtonId) == ButtonID.ROCK))
        {
            playerWinsCount.setText(String.valueOf(++playerWinCount));
            resultDisplayString = "Paper covers Rock (Player Wins) ";
        }
        else if((ButtonClicked(userSelectedButtonId) == ButtonID.PAPER) 
                && (ButtonClicked(computerSelectedButtonId) == ButtonID.SCISSOR))
        {
            computerWinsCount.setText(String.valueOf(++computerWinCount));
            resultDisplayString = "Scissor cuts Paper (Computer Wins) ";
        }
        else if((ButtonClicked(userSelectedButtonId) == ButtonID.SCISSOR) 
                && (ButtonClicked(computerSelectedButtonId) == ButtonID.PAPER))
        {
            playerWinsCount.setText(String.valueOf(++playerWinCount));
            resultDisplayString = "Scissor cuts Paper (Player Wins) ";
        }
        else if((ButtonClicked(userSelectedButtonId) == ButtonID.SCISSOR) 
                && (ButtonClicked(computerSelectedButtonId) == ButtonID.ROCK))
        {
            computerWinsCount.setText(String.valueOf(++computerWinCount));
            resultDisplayString = "Rock breaks Scissor (Computer Wins) ";
        }
        else if((ButtonClicked(userSelectedButtonId) == ButtonID.ROCK) 
                && (ButtonClicked(computerSelectedButtonId) == ButtonID.SCISSOR))
        {
            playerWinsCount.setText(String.valueOf(++playerWinCount));
            resultDisplayString = "Rock breaks Scissor (Player Wins) ";
        }
        
        resultDisplayArea.append(resultDisplayString + "\n");
    }
    
    public void createAndDisplayPanels()
    {
        mainPanel = new JPanel();
        
        mainPanel.setLayout(new BorderLayout());
        
        createOptionSelectionPanel();
        createGameStatsPanel();
        createResultDisplayPanel();
        
        mainPanel.add(optionSelectionPanel, BorderLayout.NORTH);
        mainPanel.add(gameStatsPanel, BorderLayout.CENTER);
        mainPanel.add(resultDisplayPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    public void createOptionSelectionPanel()
    {
        optionSelectionPanel = new JPanel();

        Font quitButtonFont  = new Font(Font.MONOSPACED,  Font.BOLD, 60);
        
        GridLayout optionPanelLayout = new GridLayout(1, 4);
        optionPanelLayout.setHgap(5);
        optionSelectionPanel.setLayout(optionPanelLayout);
        
        rockImageIcon = new ImageIcon("src/RockImage.jpg");
        paperImageIcon = new ImageIcon("src/PaperImage.jpg");
        scissorImageIcon = new ImageIcon("src/ScissorImage.jpg");
        
        Border raisedBorder  = BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.RED, Color.GRAY, Color.WHITE, Color.LIGHT_GRAY);
        raisedBorder = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 7, rootPaneCheckingEnabled);
        rockButton = new JButton(rockImageIcon);
        rockButton.setBorder(raisedBorder);
        paperButton = new JButton(paperImageIcon);
        paperButton.setBorder(raisedBorder);
        scissorButton = new JButton(scissorImageIcon);
        scissorButton.setBorder(raisedBorder);
        quitButton = new JButton("Quit");
        
        quitButton.setFont(quitButtonFont);

        optionSelectionPanel.add(rockButton);
        optionSelectionPanel.add(paperButton);
        optionSelectionPanel.add(scissorButton);
        optionSelectionPanel.add(quitButton);
        
        rockButton.addActionListener(ae -> rockPaperScissorButtonClicked(0));
        paperButton.addActionListener(ae -> rockPaperScissorButtonClicked(1));
        scissorButton.addActionListener(ae -> rockPaperScissorButtonClicked(2));

        quitButton.addActionListener(ae -> {
            System.exit(0);
        });
        
        Border blackline = BorderFactory.createLineBorder(Color.BLUE, 5, rootPaneCheckingEnabled);
        optionSelectionPanel.setBorder(blackline);
    }
    
    public void createGameStatsPanel()
    {
        gameStatsPanel = new JPanel();
        
        GridLayout statsPanelLayout = new GridLayout(3, 3);
        
        gameStatsPanel.setLayout(statsPanelLayout);
        
        Font statsLabelFont  = new Font(Font.SANS_SERIF,  Font.BOLD, 24);
        Font statsFieldFont  = new Font(Font.SANS_SERIF,  Font.BOLD, 20);
        
        playerWinsLabel = new JLabel("Player Wins");
        playerWinsLabel.setFont(statsLabelFont);
        computerWinsLabel = new JLabel("Computer Wins");
        computerWinsLabel.setFont(statsLabelFont);
        tiesLabel = new JLabel("Ties");
        tiesLabel.setFont(statsLabelFont);
        
        playerWinsCount = new JTextField(5);
        playerWinsCount.setFont(statsFieldFont);
        playerWinsCount.setText("0");
        playerWinsCount.setEditable(false);
        computerWinsCount = new JTextField(5);
        computerWinsCount.setFont(statsFieldFont);
        computerWinsCount.setText("0");
        computerWinsCount.setEditable(false);
        tiesCount = new JTextField(5);
        tiesCount.setFont(statsFieldFont);
        tiesCount.setText("0");
        tiesCount.setEditable(false);
        
        emptyLabel1 = new JLabel();
        emptyLabel2 = new JLabel();
        emptyLabel3 = new JLabel();
        
        gameStatsPanel.add(playerWinsLabel);
        gameStatsPanel.add(playerWinsCount);
        gameStatsPanel.add(emptyLabel1);
        gameStatsPanel.add(computerWinsLabel);
        gameStatsPanel.add(computerWinsCount);
        gameStatsPanel.add(emptyLabel2);
        gameStatsPanel.add(tiesLabel);
        gameStatsPanel.add(tiesCount);
        gameStatsPanel.add(emptyLabel3);
     }
    
    public void createResultDisplayPanel()
    {
        resultDisplayPanel = new JPanel();
        
        Font textAreaFont  = new Font(Font.SERIF,  Font.BOLD, 22);
        resultDisplayArea = new JTextArea(5, 40);
        resultDisplayArea.setFont(textAreaFont);
        resultDisplayArea.setEditable(false);
        resultDisScrollPane = new JScrollPane(resultDisplayArea);
        resultDisScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        resultDisScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        resultDisplayPanel.add(resultDisScrollPane);
    }
}
