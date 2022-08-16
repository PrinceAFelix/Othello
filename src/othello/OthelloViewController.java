package othello;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class OthelloViewController extends JFrame {
	
	
	private static final long serialVersionUID = 1L;
	
	Controller myController = new Controller();
	
	OthelloNetworkModalViewController networkDialog = new OthelloNetworkModalViewController(this);

	JLabel mainFrame = new JLabel();
	
	/**Represents the black color*/
	Color black = new Color(0, 0, 0);
	/**Represents the white color*/
	Color white = new Color(255, 255, 255);
	
	/**Represents the Side Letters*/
	private static final String letters = "ABCDEFGH";
	/**Represents the top and bottom numbers*/
	private static final String numbers = " 12345678 ";
	
//	String whitePiece = "white.png";
//	String blackPiece = "black.png";
	String checkMark = "checkmark.png";
	
	ImageIcon whitePlayer = new ImageIcon(getClass().getResource("white.png"));
	ImageIcon blackPlayer = new ImageIcon(getClass().getResource("black.png"));
	ImageIcon validMoves = new ImageIcon(getClass().getResource(checkMark));
	
	JLabel blackPiece = new JLabel(blackPlayer);
	JLabel whitePiece = new JLabel(whitePlayer);
	
	JButton submitBtn = new JButton();
	JCheckBox showMovesBtn = new JCheckBox("Show Valid Moves");
	
	int bPieceCount = 0;
	int wPieceCount = 0;
	
	int activeRow = 4;
	int activeCol = 3;
	
	int currentPlayer = 1;
	
	public JTextField chatField = new JTextField();
	
	public JTextArea displayedMessage = new JTextArea();
	
	JMenuBar menuBar;
	
	JMenu file, game, network, help, boardSubMenu, debugSubMenu;
	
	//File Menu
	JMenuItem newGame, load, save, exit, connect, disconnect;
	
	//Game Menu
	JRadioButton  normalGame, corner, side, oneCapture, twoCapture, emptyBoard, inner, upArrow;
	JMenuItem normal, canadian, randomColor;
	
	//Help Menu
	JMenuItem about;
	
	JButton moveBtn, topBtn, leftBtn, botBtn, rightBtn;
	
	JPanel gamePanel;
	JLabel[][] gameBoard;
	

	
	// BOARD COLOR
	Color userCustomColor = black;
	
	int[][] defaultBoard = new int[][]{
		{0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 2, 1, 0, 0, 0},
		{0, 0, 0, 1, 2, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0}};
		
	
	int mode = 0;
	
	
	public OthelloViewController() {
		
		//GAME MENU
		menuBar  = new JMenuBar();
		setJMenuBar(menuBar);
		
		file = new JMenu("File");
		game = new JMenu("Game");
			boardSubMenu = new JMenu("Board Colours");
			debugSubMenu = new JMenu("Debug Scenarios");
		network = new JMenu("Netwrok");
			
		help = new JMenu("Help");
		
		
		//File Menu
		file.add(newGame = new JMenuItem("New Game"));
		newGame.addActionListener(myController);
		file.add(load = new JMenuItem("Load"));
		load.setEnabled(false);
		file.add(save = new JMenuItem("Save"));
		save.setEnabled(false);
		file.add(exit = new JMenuItem("Exit"));
		exit.addActionListener(myController);
		
		network.add(connect = new JMenuItem("New Connection"));
		connect.addActionListener(myController);
		network.add(disconnect = new JMenuItem("Disconnect"));
		disconnect.setEnabled(false);
		disconnect.addActionListener(myController);
	
		
		//Game Menu
		ButtonGroup btnGroup = new ButtonGroup();
		
		btnGroup.add(normalGame = new JRadioButton("Normal Game"));
		normalGame.addActionListener(myController);
		
		btnGroup.add(corner = new JRadioButton("Cornert Test"));
		corner.addActionListener(myController);
		
		btnGroup.add(side = new JRadioButton("Side Test"));
		side.addActionListener(myController);
		
		btnGroup.add(oneCapture = new JRadioButton("1x Capture Test"));
		oneCapture.addActionListener(myController);
		
		btnGroup.add(twoCapture = new JRadioButton("2x Capture Test"));
		twoCapture.addActionListener(myController);
		
		btnGroup.add(emptyBoard = new JRadioButton("Empty Board"));
		emptyBoard.addActionListener(myController);
		
		btnGroup.add(inner = new JRadioButton("Inner Square Test"));
		inner.addActionListener(myController);
		
		btnGroup.add(upArrow = new JRadioButton("Up Arrow Orientation Test"));
		upArrow.addActionListener(myController);
		
		debugSubMenu.add(normalGame);
		debugSubMenu.add(corner);
		debugSubMenu.add(side);
		debugSubMenu.add(oneCapture);
		debugSubMenu.add(twoCapture);
		debugSubMenu.add(emptyBoard);
		debugSubMenu.add(inner);
		debugSubMenu.add(upArrow);
		
		boardSubMenu.add(normal = new JMenuItem("Normal"));
		normal.addActionListener(myController);
		boardSubMenu.add(canadian = new JMenuItem("Canadian"));
		boardSubMenu.add(randomColor = new JMenuItem("Random Board Colour"));
		
		
		
		game.add(boardSubMenu);
		game.add(debugSubMenu);
		
		
		
		
		//Help Menu (About)
		help.add(about = new JMenuItem("About"));
		about.addActionListener(myController);
		
		
		canadian.addActionListener(myController);
		normal.addActionListener(myController);
		randomColor.addActionListener(myController);
		
		game.addActionListener(myController);
		
		
		menuBar.add(file);
		menuBar.add(game);
		menuBar.add(network);
		menuBar.add(help);
		
		//MENU END 
	
		
		mainFrame.setLayout(new BorderLayout());
		mainFrame.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.GRAY)); 
		
		
		
		gamePanel = new JPanel();
		gamePanel.setPreferredSize(new Dimension(600, 620));
		gamePanel.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 5, Color.GRAY)); 
		
		
		JPanel chatPanel = new JPanel();
		JPanel movesPanel = new JPanel();
		
		
		
		/** BOARD **/
		
		gamePanel.setLayout(new BorderLayout());
	
		gameBoard = new JLabel[8][8];
		
		JPanel board = new JPanel();
		board.setLayout(new GridLayout(8,8)); 
		createBoard(board, gameBoard, defaultBoard);
		gameBoard[activeRow][activeCol].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GREEN));
		
		/** END BOARD **/
		
		/** MOVES **/
		
		movesPanel.setLayout(new BorderLayout());
		movesPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, Color.GRAY)); 
		movesPanel.setPreferredSize(new Dimension(470, 30));
		
		
		//Show Moves 
		JPanel showMoves = new JPanel();
		showMoves.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, Color.GRAY)); 
		showMoves.setPreferredSize(new Dimension(470, 30));
		showMoves.setLayout(new BorderLayout(0,0)); 

		showMovesBtn.addActionListener(myController);
		JPanel checkBoxPanel = new JPanel();
		checkBoxPanel.setLayout(new BorderLayout(0,0));
		checkBoxPanel.add(showMovesBtn);
		
		showMoves.add(checkBoxPanel, BorderLayout.SOUTH);
		
		//END Show Moves
		
		
		// Move Button
		
		JPanel movePanel = new JPanel();
		movePanel.setLayout(new BorderLayout());
		movePanel.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, Color.GRAY)); 
		movePanel.setPreferredSize(new Dimension(200, 250));
		
		

		
		//Set up move button
		moveBtn = new JButton("move");
		moveBtn.setFont(new Font(moveBtn.getFont().getName(), moveBtn.getFont().getStyle(), 9));
		buttons(moveBtn);
		
		//Create all 4 direction button
		topBtn = new JButton(new ImageIcon(getClass().getResource("uparrow.png"))); //Up button
		buttons(topBtn);

		leftBtn = new JButton(new ImageIcon(getClass().getResource("leftarrow.png"))); //Left button
		buttons(leftBtn);
		
		botBtn = new JButton(new ImageIcon(getClass().getResource("downarrow.png"))); //Down button
		buttons(botBtn);
		
		rightBtn = new JButton(new ImageIcon(getClass().getResource("rightarrow.png"))); //Right button
		buttons(rightBtn);
		
		JPanel buttonsWrapper = new JPanel();
		
		GridLayout layout = new GridLayout(3,3);
		layout.setHgap(2);
		layout.setVgap(2);
		
		buttonsWrapper.setLayout(layout);
		buttonsWrapper.setBorder(new EmptyBorder(5, 5, 5,0));

		//Add all buttons to the wrapper which is 3 by 3 Grid
		buttonsWrapper.add(new JLabel(""));
		buttonsWrapper.add(topBtn);
		buttonsWrapper.add(new JLabel(""));
		buttonsWrapper.add(leftBtn);
		buttonsWrapper.add(moveBtn);
		buttonsWrapper.add(rightBtn);
		buttonsWrapper.add(new JLabel(""));
		buttonsWrapper.add(botBtn);
		buttonsWrapper.add(new JLabel(""));
		
		
		movePanel.add(buttonsWrapper, BorderLayout.WEST);
		
		
		//Create a wrapper to gather the Player Piece Info
		JLabel pieceWrapper = new JLabel();
		pieceWrapper.setLayout(new GridLayout(2, 2)); //2 by 2 Grid Layout
		pieceWrapper.setPreferredSize(new Dimension(250,300));
		pieceWrapper.setBackground(new Color(175, 175, 255));

		//Create the Label and images for both player 1 and player 2
		JLabel p1 = new JLabel("Player 1 Pieces: ");

	
		JLabel p2 = new JLabel("Player 2 Pieces: ");
		whitePiece.setText(String.valueOf(wPieceCount));
		
		//add the player info to the player wrapper which is 2 by 2
		pieceWrapper.add(p1);
		pieceWrapper.add(blackPiece);
		pieceWrapper.add(p2);
		pieceWrapper.add(whitePiece);

		
		
		movePanel.add(pieceWrapper, BorderLayout.EAST);
		
		// END Move Button
		
		

		
		JPanel messages = new JPanel();
		messages.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.GRAY)); 
		messages.setPreferredSize(new Dimension(470, 400));
		messages.setLayout(new BorderLayout()); 
		messages.setBackground(new Color(175, 175, 255));
		
		
		displayedMessage.setBackground(new Color(175, 175, 255));
		
		setMessageText();
		setScoreText();
		
		messages.add(displayedMessage);
		
		displayedMessage.setFont(new Font("Calibri", Font.PLAIN, 12));
		displayedMessage.setBorder(new EmptyBorder(0, 5, 0, 0));
		
		
		displayedMessage.setWrapStyleWord(true);
		displayedMessage.setEditable(false);
		displayedMessage.setOpaque(true);
		

		JScrollPane sp = new JScrollPane(displayedMessage, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		
		messages.add(sp);
		
		movesPanel.add(showMoves, BorderLayout.NORTH);
		movesPanel.add(movePanel, BorderLayout.CENTER);
		movesPanel.add(messages, BorderLayout.SOUTH);
		
		
		/** END MOVES **/
		
		
		/** CHAT **/
		chatPanel.setLayout(new BorderLayout());
		chatPanel.setPreferredSize(new Dimension(1080, 30));
		
		
		chatField.setEditable(true);
		chatField.setHorizontalAlignment(JTextField.LEFT);
		
		submitBtn.setPreferredSize(new Dimension(50, 20));
		submitBtn.setBackground(Color.BLACK);
		submitBtn.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.WHITE));
		submitBtn.setText("Submit"); 
		submitBtn.setForeground(Color.RED);
		
		//Set up action listener for the submit button
		submitBtn.addActionListener(myController);
		
		chatPanel.add(chatField, BorderLayout.CENTER);
		chatPanel.add(submitBtn, BorderLayout.EAST);

		
		/** END CHAT **/
		
		
		gamePanel.add(board, BorderLayout.CENTER);
		notationPlacer(gamePanel);
		
		
		mainFrame.add(movesPanel, BorderLayout.EAST);
		
		mainFrame.add(gamePanel, BorderLayout.WEST);
		
		mainFrame.add(chatPanel, BorderLayout.SOUTH);
		
		add(mainFrame);
		
	}
	
	public void setScoreText() {
		
		blackPiece.setText(String.valueOf(bPieceCount));
		whitePiece.setText(String.valueOf(wPieceCount));
		
	}
	
	public void setMessageText() {
		displayedMessage.append("Player 1 initialized with " + bPieceCount + " pieces.\nPlayer 2 initialized with " + wPieceCount + " pieces.\n");
	}
	
	
	public void createBoard(JPanel panel, JLabel[][] chessBoard, int[][] mode) {
		
		for (int i = 0; i < mode.length ; i++) {
			for (int j = 0; j < mode.length; j++) {	
				chessBoard[i][j] = new JLabel();
				chessBoard[i][j].setOpaque(true);
				chessBoard[i][j].setPreferredSize(new Dimension(60, 60));
				
				if(defaultBoard[i][j] == 2) {
					wPieceCount++;
					chessBoard[i][j] = new JLabel(whitePlayer);
					chessBoard[i][j].setOpaque(true);
					chessBoard[i][j].setPreferredSize(new Dimension(60, 60));
				}else if(defaultBoard[i][j] == 1) {
					bPieceCount++;
					chessBoard[i][j] = new JLabel(blackPlayer);
					chessBoard[i][j].setOpaque(true);
					chessBoard[i][j].setPreferredSize(new Dimension(60, 60));
				}
				
				if(i % 2 == 0) {
					if(j %2 == 0) {
						chessBoard[i][j].setBackground(white);
					}else {
						chessBoard[i][j].setBackground(userCustomColor);
					}
				}else {
					if(j %2 != 0) {
						chessBoard[i][j].setBackground(white);
					}else {
						chessBoard[i][j].setBackground(userCustomColor);
						
					}
				}
				

				panel.add(chessBoard[i][j]);   
			}
		}
	}
	
	public void notationPlacer(JPanel panel) {
		
		JPanel sideLetter = new JPanel();
		sideLetter.setLayout(new GridLayout(8, 1));
		for(int i = 0; i < 8; i++) {
			JLabel letter = new JLabel(letters.substring(i, i+1), JLabel.CENTER);
			letter.setBackground(new Color(220, 220, 220));
			letter.setFont(new Font(letter.getFont().getName(), letter.getFont().getStyle(), 20));
			letter.setPreferredSize(new Dimension(60, 60));
			sideLetter.add(letter);
		}
		
	

		JPanel sideLetter1 = new JPanel();
		sideLetter1.setLayout(new GridLayout(8, 1));
		for(int i = 0; i < 8; i++) {
			JLabel letter = new JLabel(letters.substring(i, i+1), JLabel.CENTER);
			letter.setBackground(new Color(220, 220, 220));
			letter.setFont(new Font(letter.getFont().getName(), letter.getFont().getStyle(), 20));
			letter.setPreferredSize(new Dimension(60, 60));			
			sideLetter1.add(letter);
		}

		JPanel sideNumber = new JPanel();
		sideNumber.setLayout(new GridLayout(1, 8));

		for(int i = 0; i < 10; i++) {
			JLabel number = new JLabel(numbers.substring(i, i+1), JLabel.CENTER);
			number.setBackground(new Color(220, 220, 220));
			number.setFont(new Font(number.getFont().getName(), number.getFont().getStyle(), 20));
			number.setPreferredSize(new Dimension(60, 60));
			sideNumber.add(number);
		}
		
		JPanel sideNumber1 = new JPanel();
		sideNumber1.setLayout(new GridLayout(1, 10));
		for(int i = 0; i < 10; i++) {
			JLabel number = new JLabel(numbers.substring(i, i+1), JLabel.CENTER);
			number.setBackground(new Color(220, 220, 220));
			number.setFont(new Font(number.getFont().getName(), number.getFont().getStyle(), 20));
			number.setPreferredSize(new Dimension(60, 60));
			sideNumber1.add(number);
		}

		//Add side numbers to the north and south of west panel
		panel.add(sideNumber, BorderLayout.NORTH);
		panel.add(sideNumber1, BorderLayout.SOUTH);
		
		panel.add(sideLetter, BorderLayout.EAST);
		panel.add(sideLetter1, BorderLayout.WEST);
	}
	
	public JButton buttons(JButton myButton){
		
		//Apply settings for Button
		myButton.setBackground(Color.WHITE);
		myButton.setOpaque(true);
		myButton.setPreferredSize(new Dimension(35, 30));
		myButton.setMargin(new Insets(10, 10, 10, 10));
		myButton.setBorder(new EmptyBorder(5, 2, 2,2 ));
		myButton.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
		
		myButton.addActionListener(myController);
	
		return myButton;
	}
	
	public void placePiece(int player, JLabel[][] chessBoard, int row, int col) {
		
		if(player == 1) {
			chessBoard[row][col] = new JLabel(blackPlayer);
			chessBoard[row][col].setOpaque(true);
			chessBoard[row][col].setPreferredSize(new Dimension(60, 60));
		}else if(player == 2) {
			chessBoard[row][col] = new JLabel(whitePlayer);
			chessBoard[row][col].setOpaque(true);
			chessBoard[row][col].setPreferredSize(new Dimension(60, 60));
		}
	}
	
	



	class Controller implements ActionListener{
		
		OthelloModel model = new OthelloModel(OthelloViewController.this);
		
		int captureCount = 0;
		
		
		int count = 0;
		@Override
		public void actionPerformed(ActionEvent ae) {

			//Center the dialog box
			Point thisLocation = getLocation();
			Dimension parentSize = getSize();
			Dimension dialogSize = networkDialog.getSize();
			
			int offsetX =  (parentSize.width-dialogSize.width)/2+thisLocation.x;
			int offsetY = (parentSize.height-dialogSize.height)/2+thisLocation.y;
			
			
			
			//MOVE Button
			if(ae.getSource() == topBtn) {
				if(activeRow > 0) {
					activeRow -= 1;
					gameBoard[activeRow][activeCol].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GREEN));
				}
			}
			
			if(ae.getSource() == leftBtn) {
				if(activeCol > 0) {
					activeCol -= 1;
					gameBoard[activeRow][activeCol].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GREEN));
				}
			}
			
			if(ae.getSource() == botBtn) {
				if(activeRow < 7) {
					activeRow += 1;
					gameBoard[activeRow][activeCol].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GREEN));
				}
			}
			
			if(ae.getSource() == rightBtn) {
				if(activeCol < 7) {
					activeCol += 1;
					gameBoard[activeRow][activeCol].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GREEN));
				}
			}
			
			if(ae.getSource() == moveBtn) {
				
				if(currentPlayer == 1) {
					
					model.showGameValidMoves(currentPlayer, false);
					if(model.canMove(activeRow, activeCol, currentPlayer)) {
						captureCount = model.tryMove(activeRow, activeCol, currentPlayer);
						gameBoard[activeRow][activeCol].setIcon(blackPlayer);
						defaultBoard[activeRow][activeCol] = 1;
						currentPlayer = 2;
						showMovesBtn.setSelected(false);
					}else {
						gameBoard[activeRow][activeCol].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.RED));
					}
					bPieceCount = model.chipCount(1);
					
					
				}else if(currentPlayer == 2) {
					
					model.showGameValidMoves(currentPlayer, false);
					if(model.canMove(activeRow, activeCol, currentPlayer)) {
						captureCount = model.tryMove(activeRow, activeCol, currentPlayer);
						gameBoard[activeRow][activeCol].setIcon(whitePlayer);
						defaultBoard[activeRow][activeCol] = 2;
						currentPlayer = 1;
						showMovesBtn.setSelected(false);
					}else {
						gameBoard[activeRow][activeCol].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.RED));
					}
					wPieceCount = model.chipCount(2);
					
				}
				
				if(model.canMove(activeRow, activeCol, currentPlayer)) {
					displayedMessage.append("PLayer " + currentPlayer + " has captured " + captureCount + " piece.\n");
					captureCount = 0;
				}
				

				
				setScoreText();
				
				gameBoard[activeRow][activeCol].setHorizontalAlignment(JLabel.CENTER);
				
				
			}
			
			
			
			//END  MOVE
			if(ae.getSource() == normal) {
				userCustomColor = black;
			}
			
			if(ae.getSource() == canadian) {
				userCustomColor = new Color(255, 0, 0);
			}
			
			if(ae.getSource() == randomColor) {
				Random rand = new Random();
				userCustomColor = new Color(rand.nextInt(0, 255), rand.nextInt(0, 255), rand.nextInt(0, 255));
			}
			
	
			if(ae.getSource() == submitBtn && !chatField.getText().isEmpty()) {
				displayedMessage.append(chatField.getText().trim() + "\n");
				chatField.setText("");
				chatField.requestFocus();
			}
			
			if(ae.getSource() == showMovesBtn) {
				if(showMovesBtn.isSelected()) {
					model.showGameValidMoves(currentPlayer, true);
				}else {
					model.showGameValidMoves(currentPlayer, false);
				}
				
				
			}
			
			
			
			if(ae.getSource() == normalGame) {
				mode = 0;
			}else if(ae.getSource() == corner) {
				mode = 1;
			}else if(ae.getSource() == side) {
				mode = 2;
			}else if(ae.getSource() == oneCapture) {
				mode = 3;
			}else if(ae.getSource() == twoCapture) {
				mode = 4;
			}else if(ae.getSource() == emptyBoard) {
				mode = 5;
			}else if(ae.getSource() == inner) {
				mode = 6;
			}else if(ae.getSource() == upArrow) {
				mode = 7;
			}
			
			if(ae.getSource() == newGame) {
				currentPlayer = 1;
				
				bPieceCount = 0;
				wPieceCount = 0;
				
				model.prepareBoard(mode);
				
				model.setBoard();
				
				displayedMessage.setText("");
				
				setScoreText();
				setMessageText();
				
				SwingUtilities.updateComponentTreeUI(mainFrame);
			}
			
			//Network
			if(ae.getSource() == connect) { //If the user pressed new connectin
				networkDialog.setLocation(offsetX, offsetY);
				networkDialog.setVisible(true);
				
				//Condition for Pressed Connect and Cancel
				if(networkDialog.pressedConnect()) {
					displayedMessage.append("\n\nConnecting to " + networkDialog.getAddress() + "\nOn Port " + networkDialog.getPort() + "\nWith name: " + networkDialog.getName());
					
				}else {
					displayedMessage.append("\nCancel Pressed...");
				}
				disconnect.setEnabled(true);
			}
			else if(ae.getSource() == disconnect) { //If Disconnect is pressed
				displayedMessage.append("\nDisconnecting...");
			}
			
			
			if(ae.getSource() == about) {
				JOptionPane.showMessageDialog(null, "<html>Othello Game <br>by Prince Felix <br><br>June 2021</html>", "About", JOptionPane.INFORMATION_MESSAGE);
			}
			
			if(ae.getSource() == exit) {
				System.exit(0);
			}
			
			
			
			
			//Repaint COlor
			for(int i = 0; i < gameBoard.length; i++) {
				for(int j = 0; j < gameBoard.length; j++) {
					if(gameBoard[i][j].getBackground() != white) {
						gameBoard[i][j].setBackground(userCustomColor);
					}
					
					if((i+j) != (activeRow+activeCol)) {
						gameBoard[i][j].setBorder(null);
					}
					
				}
			}
			
			//Check for end game
			if(model.checkEndGame()) {
				displayedMessage.append("END OF GAME\nPLayer 1 finishes with " + bPieceCount + " pieces.\nPLayer 2 finishes with " + wPieceCount + " pieces.\n");
			}
			
			
			
		}
		
	}
}
