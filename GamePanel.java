import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.FontMetrics;
import java.awt.Font;


public class GamePanel extends JPanel implements ActionListener{

	static final int WIDTH = 600;
	static final int HEIGHT = 600;
	static final int GRID_BOX = 30;
	static final int GAME_UNITS = (WIDTH*HEIGHT)/(GRID_BOX*GRID_BOX);
	static final int DELAY = 75;
	final int x[] = new int[GAME_UNITS];
	final int y[] = new int[GAME_UNITS];
	int bodyParts = 1;
	int applesEaten;
	int appleX;
	int appleY;
	int appleA;
	int appleB;
	int appleC;
	int appleD;
	char direction = 'R';
	boolean running = false;
	Timer timer;
	Random random;
	
	GamePanel(){
		this.random = new Random();
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
		
	}
	public void startGame() {
		newApple();
		newApple2();
		running = true;
		timer = new Timer(DELAY,this);
		timer.start();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	public void draw(Graphics g) {
		
		if(running) {
			
			for(int i=0;i<HEIGHT/GRID_BOX;i++) {
				g.drawLine(i*GRID_BOX, 0, i*GRID_BOX, HEIGHT);
				g.drawLine(0, i*GRID_BOX, WIDTH, i*GRID_BOX);
			}
			
			g.setColor(Color.red);
			g.fillOval(appleX, appleY, GRID_BOX, GRID_BOX);
			g.setColor(Color.green);
			g.fillOval(appleA, appleB, GRID_BOX*2, GRID_BOX*2);
			g.setColor(Color.BLACK);
			g.fillOval(appleC, appleD, GRID_BOX/2, GRID_BOX/2);
			g.setColor(Color.BLACK);
			g.fillOval(appleC, appleD, GRID_BOX/2, GRID_BOX/2);
	
		
			for(int i = 0; i< bodyParts;i++) {
				g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
				g.fillRect(x[i], y[i], GRID_BOX, GRID_BOX);			
			}
			g.setColor(Color.red);
			g.setFont( new Font("Ink Free",Font.BOLD, 40));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Score: "+applesEaten, (WIDTH - metrics.stringWidth("Score: " + applesEaten))/2, g.getFont().getSize());
		}
		else {
			gameOver(g);
		}
		
	}
	public void newApple(){
		appleX = random.nextInt((int)(WIDTH/GRID_BOX))*GRID_BOX;
		appleY = random.nextInt((int)(HEIGHT/GRID_BOX))*GRID_BOX;
	}
	public void newApple2() {
		appleA = random.nextInt((int)(1000/GRID_BOX))*GRID_BOX;
		appleB = random.nextInt((int)(1000/GRID_BOX))*GRID_BOX;
	}

	public void newPosionApple() {
		appleC = random.nextInt((int)(300/GRID_BOX))*GRID_BOX;
		appleD = random.nextInt((int)(300/GRID_BOX))*GRID_BOX;
	}

	public void move(){
		for(int i = bodyParts; i > 0; i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		
		switch(direction) {
		case 'U':
			y[0] = y[0] - GRID_BOX;
			break;
		case 'D':
			y[0] = y[0] + GRID_BOX;
			break;
		case 'L':
			x[0] = x[0] - GRID_BOX;
			break;
		case 'R':
			x[0] = x[0] + GRID_BOX;
			break;
		}
		
	}
	public void checkApple() {
		if(x[0] == appleX && y[0] == appleY) {
			bodyParts++;
			applesEaten++;
			newApple();
			newApple2();
			newPosionApple();

		}
		if(x[0] == appleC && y[0] == appleD) {
			bodyParts= bodyParts-5;
			applesEaten= applesEaten-5;
			newApple();
			newApple2();
			newPosionApple();
		}
		if(x[0] == appleA && y[0] == appleB) {
			if (applesEaten != 0) {
			applesEaten+=applesEaten;
			}
			else {
				applesEaten += 1;
			}
			bodyParts  += 1;
			newApple2();
			newPosionApple();
		}
	}
	public void checkCollisions() {
		//checks if head collides with body
		for(int i = bodyParts;i>0;i--) {
			if((x[0] == x[i])&& (y[0] == y[i])) {
				running = false;
			}
		}
		//check if head touches left border
		if(x[0] < 0) {
			running = false;
		}
		//check if head touches right border
		if(x[0] > WIDTH) {
			running = false;
		}
		//check if head touches top border
		if(y[0] < 0) {
			running = false;
		}
		//check if head touches bottom border
		if(y[0] > HEIGHT) {
			running = false;
		}
		
		if(!running) {
			timer.stop();
		}
	}
	public void gameOver(Graphics g) {
		//Score
		g.setColor(Color.red);
		g.setFont( new Font("Ink Free",Font.BOLD, 40));
		FontMetrics metrics1 = getFontMetrics(g.getFont());
		g.drawString("Score: "+applesEaten, (WIDTH - metrics1.stringWidth("Score: "+applesEaten))/2, g.getFont().getSize());
		//Game Over text
		g.setColor(Color.red);
		g.setFont( new Font("Ink Free",Font.BOLD, 75));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Restart", (WIDTH - metrics2.stringWidth("Game Over"))/2, HEIGHT/2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(running) {
			move();
			checkApple();
			checkCollisions();
		}

		repaint();
	}

	
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(direction != 'R') {
					direction = 'L';
				}
				break;
			case KeyEvent.VK_R:
				startGame();

			case KeyEvent.VK_RIGHT:
				if(direction != 'L') {
					direction = 'R';
				}
				break;
			case KeyEvent.VK_UP:
				if(direction != 'D') {
					direction = 'U';
				}
				break;
			case KeyEvent.VK_DOWN:
				if(direction != 'U') {
					direction = 'D';
				}
				break;
			}
		}
	}
}