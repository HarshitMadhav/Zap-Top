import java.awt.Color;

import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import javax.swing.Timer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


public class zaptop extends JFrame{
	
	private JPanel panel;
	private JLabel[] block = new JLabel[16];
	private int[] board = new int[16];
	
	private int score = 0;
	private JLabel lblScore;
	private JLabel lblTimeLeft;
	private JLabel lblHighscore;
	private int timeLeft = 30;
	private int highscore = 0;
	private JButton btnStart;
	private Timer timer;

	
	private void loadHighscore(){
	    BufferedReader br = null;
	        String line = "";
	        try {
	            br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/highscore.txt"));
	            line = br.readLine();
	            br.close();
	        } catch (IOException e) {
	            line = "";
	        }

	        if(line != ""){
	            highscore = Integer.parseInt(line);
	            lblHighscore.setText("Highscore: " + highscore);
	        }
	    }

	private void saveHighscore(){
	    BufferedWriter bw = null;
	    try {
	        bw = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + "/highscoe.txt", false));
	        bw.write("" + highscore);
	        bw.flush();
	        bw.close();
	    } catch (IOException e) {
	        JOptionPane.showMessageDialog(this, e.getMessage(), "Error while saving highscore", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	
	private void pressedButton(int id) {
        int val = board[id];

        if(val==1){ 
            score++;
        }else{
            score--;
        }

        lblScore.setText("Score: " + score);

        clearBoard();

        genRandBoss();
    }

	private void initGUI() {
		setTitle("Zap Top");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 608, 720);
		
		
		JPanel contentPane = new JPanel();
		contentPane = new JPanel();
		contentPane.setBackground(new Color(249, 231, 159));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		
		JLabel lblTitle = new JLabel("Zap Top");
		lblTitle.setForeground(new Color(96, 96, 96));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblTitle.setBounds(0, 0, 602, 47);
		contentPane.add(lblTitle);
		
		
		JLabel lblTitle2 = new JLabel("Game by: Harshit and Akash");
		lblTitle2.setForeground(new Color(0,102,102));
		lblTitle2.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle2.setFont(new Font("Century Gothic", Font.BOLD, 13));
		lblTitle2.setBounds(210,650, 200, 27);
		contentPane.add(lblTitle2);
		
		JLabel lblTitle3 =  new JLabel ("Click 'Start' to begin !");
		lblTitle3.setForeground(new Color(0, 102, 102));
		lblTitle3.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle3.setFont(new Font("Century Gothic", Font.BOLD, 13));
		lblTitle3.setBounds(0, 0, 200, 60);
		contentPane.add(lblTitle3);
		
	
		panel = new JPanel();
		panel.setBackground(new Color(249, 231, 159));
		panel.setBounds(32, 105, 535, 546);
		panel.setLayout(null);
		panel.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                loadImage("/scope3.png").getImage(),
                new Point(0,0),"custom cursor1"));
		contentPane.add(panel);
		
		lblScore = new JLabel("Score: 0");
		lblScore.setHorizontalAlignment(SwingConstants.TRAILING);
		lblScore.setFont(new Font("Cambria", Font.BOLD, 14));
		lblScore.setForeground(new Color(102, 178, 255));
		lblScore.setBounds(423, 54, 144, 33);
		contentPane.add(lblScore);
		
		lblTimeLeft = new JLabel("30");
		lblTimeLeft.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimeLeft.setForeground(new Color(251,51,51));
		lblTimeLeft.setFont(new Font("Cambria Math", Font.BOLD, 24));
		lblTimeLeft.setBounds(232, 54, 144, 33);
		contentPane.add(lblTimeLeft);

		lblHighscore = new JLabel("Highscore: 0");
		lblHighscore.setHorizontalAlignment(SwingConstants.TRAILING);
		lblHighscore.setForeground(new Color(0,102 ,102));
		lblHighscore.setFont(new Font("Cambria", Font.BOLD, 14));
		lblHighscore.setBounds(433, 18, 134, 33);
		contentPane.add(lblHighscore);
		
		btnStart = new JButton("Start");
		btnStart.setBackground(new Color(249, 231, 159));
		btnStart.setBounds(32, 60, 110, 33);
		contentPane.add(btnStart);
		
		//code for randomization
		block[0] = new JLabel("0");
		block[0].setName("0");
		block[0].setBounds(0, 396, 132, 132);
		panel.add(block[0]);
		
		
		block[1] = new JLabel("1");
		block[1].setName("1");
		block[1].setBounds(132, 396, 132, 132);
		panel.add(block[1]);
		
		block[2] = new JLabel("2");
		block[2].setName("2");
		block[2].setBounds(264, 396, 132, 132);
		panel.add(block[2]);
		
		block[3] = new JLabel("3");
		block[3].setName("3");
		block[3].setBounds(396, 396, 132, 132);
		panel.add(block[3]);
		
		block[4] = new JLabel("4");
		block[4].setName("4");
		block[4].setBounds(0, 264, 132, 132);
		panel.add(block[4]);
		
		block[5] = new JLabel("5");
		block[5].setName("5");
		block[5].setBounds(132, 264, 132, 132);
		panel.add(block[5]);
		
		block[6] = new JLabel("6");
		block[6].setName("6");
		block[6].setBounds(264, 264, 132, 132);
		panel.add(block[6]);
		
		block[7] = new JLabel("7");
		block[7].setName("7");
		block[7].setBounds(396, 264, 132, 132);
		panel.add(block[7]);
		
		block[8] = new JLabel("8");
		block[8].setName("8");
		block[8].setBounds(0, 132, 132, 132);
		panel.add(block[8]);
		
		block[9] = new JLabel("9");
		block[9].setName("9");
		block[9].setBounds(132, 132, 132, 132);
		panel.add(block[9]);
		
		block[10] = new JLabel("10");
		block[10].setName("10");
		block[10].setBounds(264, 132, 132, 132);
		panel.add(block[10]);
		
		block[11] = new JLabel("11");
		block[11].setName("11");
		block[11].setBounds(396, 132, 132, 132);
		panel.add(block[11]);
		
		block[12] = new JLabel("12");
		block[12].setName("12");
		block[12].setBounds(0, 0, 132, 132);
		panel.add(block[12]);
		
		block[13] = new JLabel("13");
		block[13].setName("13");
		block[13].setBounds(132, 0, 132, 132);
		panel.add(block[13]);
		
		block[14] = new JLabel("14");
		block[14].setName("14");
		block[14].setBounds(264, 0, 132, 132);
		panel.add(block[14]);
		
		block[15] = new JLabel("15");
		block[15].setName("15");
		block[15].setBounds(396, 0, 132, 132);
		panel.add(block[15]);
		//code for randomization ends
		
		setContentPane(contentPane);
		
	}
	
	private void clearBoard() {
		for(int i = 0; i < 16; i++){
		block[i].setIcon(loadImage("/opendoor.jpg"));
		board[i] = 0;
		}
	}
	
	private void genRandBoss() {
        Random rnd = new Random(System.currentTimeMillis()); 
        int BossID = rnd.nextInt(16);
        board[BossID] = 1;
        block[BossID].setIcon(loadImageBoss("/angryBoss.png"));
	}


	private ImageIcon loadImage(String path) {
	Image image = new ImageIcon(this.getClass().getResource(path)).getImage();
	Image scaledImage = image.getScaledInstance(132, 132, java.awt.Image.SCALE_SMOOTH);
	return new ImageIcon(scaledImage);
	}
	
	
	private ImageIcon loadImageBoss(String path) {
		Image image = new ImageIcon(this.getClass().getResource(path)).getImage();
		Image scaledImage= image.getScaledInstance(132,132,java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(scaledImage);
	}

	public zaptop() {
	
		initGUI();
		clearBoard();
		initEvents();
		loadHighscore();
	}
	
	private void initEvents(){
	    for(int i = 0; i < block.length; i++){
	        block[i].addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent e){
	                JLabel lbl = (JLabel)e.getSource();
	                int id = Integer.parseInt(lbl.getName());
	                pressedButton(id);
	            }
	        });
	    }
	    
	    btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnStart.setEnabled(false);
                clearBoard();
                genRandBoss();
                timer.start();
            }
        });

        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(timeLeft == 0){
                    lblTimeLeft.setText("" + timeLeft);
                    timer.stop();
                    gameOver();
                }
                lblTimeLeft.setText("" + timeLeft);
                timeLeft--;
            }
        });
    }
	
	private void gameOver(){
		
	    btnStart.setEnabled(true);
	    if(score > highscore){
	        highscore = score;
	        lblHighscore.setText("Highscore: " + highscore);
	        JOptionPane.showMessageDialog(this, "Your final score is: " + score, "Congratulations! You beat the high score!", JOptionPane.INFORMATION_MESSAGE);
	    }else{
	    	if(score>=0){
	        JOptionPane.showMessageDialog(this, "Your final score is: " + score, "Game Over!", JOptionPane.INFORMATION_MESSAGE);
	    	}
	    	else{
	    		JOptionPane.showMessageDialog(this, "Don't Mess With Your Boss !!\n"
	    				+ "You have files to complete : "+ score*(-1), "Game Over!",JOptionPane.INFORMATION_MESSAGE);
	    		JOptionPane.showMessageDialog(this, "BOSS WINS !!\n You Are FIRED !!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
	    	   	}
	    }
	    
	    score = 0;
	    timeLeft = 30;
	    lblScore.setText("Score: 0");
	    lblTimeLeft.setText("30");

	    clearBoard();
	    
	    saveHighscore();
	}
	
	
	
	public static void main(String[] args){
		zaptop frame = new zaptop();
		frame.setVisible(true);
		}
	
}
