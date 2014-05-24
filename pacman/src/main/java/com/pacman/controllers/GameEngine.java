package com.pacman.controllers;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;

import javax.swing.Timer;

import com.pacman.ai.PathFinder;
import com.pacman.model.Direction;
import com.pacman.model.Map;
import com.pacman.model.MightyPacman;
import com.pacman.model.MightyPill;
import com.pacman.model.Monster;
import com.pacman.model.Pacman;
import com.pacman.model.Pill;
import com.pacman.model.StationaryObject;
import com.pacman.model.StrongMonster;
import com.pacman.model.SuperPacman;
import com.pacman.model.SuperPill;
import com.pacman.model.WeakMonster;
import com.pacman.pacmannetwork.ClientObject;
import com.pacman.pacmannetwork.PacManClient;
import com.pacman.pacmannetwork.PacmanServer;
import com.pacman.pacmannetwork.PacmanTransmission;
import com.pacman.utils.SerializationUtil;
import com.pacman.views.GameWindow;
import com.pacman.views.GameView;
import com.pacman.views.StatusBarView;
import com.pacman.views.fx.SoundPlayer;

/**
 * Pacman Game Engine- Manages the game logic
 * 
 * @author
 * @version 1.0
 */
public class GameEngine implements Runnable {
	/* Game constants */
	private static final int SPECIAL_STAGE_TIME = 10;

	private static final int GAME_STOP_TIME = 30;

	private static final int MONSTERS_DELAY = 3000;
	private static final int PACMAN_LIVES = 2;
	private static final int POINTS_EATING_PILL = 10;
	private static final int POINTS_EATING_SUPER_PILL = 50;
	private static final int POINTS_EATING_MONSTER = 200;
	private static final int MAX_CHEAT_USE = 2;

	// public ClientObject sendObject;

	// TODO setting who is running the game
	// Nikki
	boolean hostFlag;
	// JASON
	private static final int MAX_USERS = 3;

	// Jason
	/* Flag for client/server */
	private int _csflag = 0;

	/* Game timers */
	private Timer _gameTimer;
	private static final int FPS = 60;
	private Timer _specialStageTimer;
	/* Game map */

	// Jason
	private Map[] _levelMap = new Map[MAX_USERS];
	/* UI Components */
	private GameWindow _window;
	// Jason
	private GameView[] _gameView = new GameView[MAX_USERS];

	private StatusBarView[] _statusBarView = new StatusBarView[MAX_USERS];
	private Pacman[] _pacman = new Pacman[MAX_USERS];
	// Jason
	// private Pacman _pacman1;
	/* Path Finder- AI Manager */
	private PathFinder _ai;
	/* Game Object & information */
	private int _remainingLives;
	private List<Monster> _monsters = new ArrayList<Monster>();
	private List<Monster> _monsters_client2 = new ArrayList<Monster>();
	private List<Monster> _monsters_client3 = new ArrayList<Monster>();
	private int _points;
	private int _remainingPills;
	private String _cheat = ""; // if types OOP - enter to the special stage
	private int _cheatUse = 0;
	// Jason
	private int current_users;
	// Siyuan
	private int _gameCountDownTime = GAME_STOP_TIME;
	// Nikki to be used by client
	String topicSName = "hostReq";
	private String hostName = "host";
	String identity = "";

	/**
	 * Creates a new Game Engine
	 */
	public GameEngine(int users_num,boolean flag) {
		//Nkki
		this.hostFlag = flag;
		// Jason
		current_users = users_num;
		if (this.current_users > 1)
			this._csflag = 1;

		// initialize base UI Components
		_window = new GameWindow();

		// get host name
		// TODO get added host name from setttings
		String topicName = "";
		if (hostFlag) {
			// if this is host
			topicName = this.hostName + "Req";
		} else {
			topicName = this.hostName;
		}

		PacManClient.initializePacmanClient(topicName,
				new PacManClient.Callback() {

					public void onMessage(byte[] data) {
						// TODO Auto-generated method stub
						// Nikki added
						ClientObject recievedObj = (ClientObject) SerializationUtil
								.fromByteArrayToJava(data);

						
						//make a pacman from pacmantransmission
						changetopacman(recievedObj.pacman);

						if(recievedObj!=null && !hostFlag){
							System.out.println("Recieved Data!!!");
						}
						 if (hostFlag) {
							 PacmanServer.sendData(hostName,recievedObj);
						 }

					}
				});

		// Jason
		for (int i = 0; i < this.current_users; i++) {
			System.out.println(i);
			_levelMap[i] = Map.getFirstLevelMap();
			this._gameView[i] = new GameView(this._levelMap[i]);
			// Siyuan
			this._statusBarView[i] = new StatusBarView();
			_ai = new PathFinder(_levelMap[i], 100);
		}
		// Jason
		_window.showView(_gameView, _statusBarView, this.current_users);

		_window.setWindowInScreenCenter();
		_gameView[0].addKeyListener(new MovePacmanListener());
		_gameView[0].setFocusable(true);
		// Jason

		// initialize the path finder object with the current map
		// Jason

		// initialize the game timer
		_gameTimer = new Timer(1000 / FPS, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				System.out.println("test!!!!");
				updateGame();
			}
		});

		// initialize the special stage timer
		_specialStageTimer = new Timer(SPECIAL_STAGE_TIME * 1000,
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// stop the special stage, back to the normal pacman and
						// stop the special stage timer
						// for(int i =0; i<current_users; i++)
						// {
						_pacman[0] = new Pacman(_levelMap[0]);

						_gameView[0].setPacman(_pacman[0]);
						// }
						// Jason

						for (Monster m : _monsters) {
							m.setNormalMode();
						}
						_specialStageTimer.stop();
					}
				});
		// ToDo get identity from Settings
		initializeNewGame();
		gameRestart();
	}

	/*
	 * Initialize new pacman game
	 */
	public void initializeNewGame() {
		// stop any timer (if running)
		_gameTimer.stop();
		_specialStageTimer.stop();

		// Set game variables to their default values
		// and reset the map
		_remainingLives = PACMAN_LIVES;
		// jason
		for (int i = 0; i < this.current_users; i++) {
			_levelMap[i] = Map.getFirstLevelMap();
			_remainingPills = _levelMap[i].getTotalPills();
			_gameView[i].newGame(_levelMap[i]);

		}

		// Jason

		_cheatUse = MAX_CHEAT_USE;
		_cheat = "";

		// initialize monsters
		// Jason
		for (int _monsters_i = 0; _monsters_i < this.current_users; _monsters_i++) {
			if (_monsters.size() == 0) {
				for (int i = 0; i < 2; i++) {
					// Weak monsters
					WeakMonster w = new WeakMonster(_levelMap[_monsters_i]);
					_monsters.add(w);
				}

				for (int i = 0; i < 2; i++) {
					// Strong monsters
					StrongMonster s = new StrongMonster(_levelMap[_monsters_i]);
					_monsters.add(s);
				}
				// Jason
				_gameView[_monsters_i].setMonsters(_monsters);
				// TODO get monsters
				// _gameView[1].setMonsters(_monsters);
			} else {
				for (Monster m : _monsters) {
					m.initializeMonster();
				}
			}
		}

		// play new game sound
		SoundPlayer.playNewGameSound();

		// Siyuan game countdown time
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				_statusBarView[1].setTime(_gameCountDownTime--);
			}
		};
		java.util.Timer t = new java.util.Timer();
		// Starts after 1 second, perform the task again every 1 second.
		t.scheduleAtFixedRate(task, 0, 1000);

	}

	/*
	 * Restart game match
	 */
	public void gameRestart() {
		// initialize new pacman and set its position to the initial position

		//can output the random between 0 and 1
		java.util.Random r=new java.util.Random(10); 
		  for(int j=0;j<10;j++){ 
		    System.out.println(r.nextDouble()); 
		}

		for (int i = 0; i < this.current_users; i++) {
			_pacman[i] = new Pacman(_levelMap[0]);
			// Jason
			_gameView[i].setPacman(_pacman[i]);

			_pacman[i].setPosition(_levelMap[i].getPacmanInitialPosition().x,
					_levelMap[0].getPacmanInitialPosition().y);
			// _pacman1.setPosition(_levelMap[1].getPacmanInitialPosition().x,
			// _levelMap[1].getPacmanInitialPosition().y);
		}

		// TODO get pacman

		// set monsters position and release them in different delays
		int delay = MONSTERS_DELAY;
		for (Monster m : _monsters) {
			m.setNormalMode();
			m.setPosition(_levelMap[0].getCagePosition().x,
					_levelMap[0].getCagePosition().y);
			m.setReleaseTime(delay);
			delay += MONSTERS_DELAY;
		}

		// starts the game timer
		_gameTimer.start();

		java.util.Timer _stopgameTimer = new java.util.Timer();
		_stopgameTimer.schedule(new TimerTask() {
			public void run() {
				gameEnd(0);
				// initializeNewGame();
				//gameRestart();
			}
		}, 1000 * GAME_STOP_TIME);

	}

	/**
	 * End the game after timer stops
	 */
	public void gameEnd(int current_user) {

		_gameView[current_user].setGameEnd(_points, _remainingLives);
		_gameTimer.stop();
		SoundPlayer.playPacmanDieSound();

	}

	/**
	 * Timer tick- updating the game
	 */
	public void updateGame() {
		// move the pacman
		_pacman[0].move();

		// Jason
//		_pacman[1].setDirection(Direction.LEFT);

		_pacman[1].move();
		// TODO put sending data here

		PacmanTransmission pacmantobesent = new PacmanTransmission();
		pacmantobesent.setDirection(_pacman[0].getDirection());
		pacmantobesent.setPosition(_pacman[0].getPosition());

		ClientObject dataSend = new ClientObject(identity, pacmantobesent);
		byte[] clientData = SerializationUtil.fromJavaToByteArray(dataSend);
//		System.out.println(clientData.length);

		//

		 if (this.hostFlag)
			 PacmanServer.sendData(hostName, dataSend);
		
		 else
		
			 PacmanServer.sendData(topicSName, dataSend);

		// _pacman[2].setDirection(Direction.RIGHT);
		//
		// _pacman[2].move();

		// System.out.println("pacman1 can move");

		// TODO If uncomment the two sentences below, weird bug will occur...
		_statusBarView[1].setPoints(_points);
		_statusBarView[1].setLives(_remainingLives);

		// move the monsters
		for (Iterator<Monster> it = _monsters.iterator(); it.hasNext();) {
			// check if a monster requests a new path
			Monster m = it.next();

			if (m.requestNewPath()) {
				// the initial target of each monster is the pacman.
				// if the game is in the special stage (the monster is
				// "IN FEAR")
				// or based on the monsters's probability for getting a random
				// path
				// we choose a random target for the new path
				Point target = _pacman[0].getPosition();
				if (m.isInFear()
						|| Math.random() < m.getRandomPathProbability()) {
					target = new Point(
							(int) (Math.random() * _levelMap[0]
									.getGameDimension().width),
							(int) (Math.random() * _levelMap[0]
									.getGameDimension().height));
				}

				// find the path to the chosen target using AI Manager (A*
				// algorithm)
				m.setPath(_ai.findPath(m, m.getPosition(), target));
			}

			// move the monster
			m.move();

			// pacman collision with monster
			if (_pacman[0].getBounds().intersects(m.getBounds())) {
				// check if pacman can eat the monster using Visitor pattern
				if (_pacman[0].eats(m)) {
					// get the monster back to the cage
					m.setPosition(_levelMap[0].getCagePosition().x,
							_levelMap[0].getCagePosition().y);
					m.setReleaseTime(SPECIAL_STAGE_TIME * 1000);
					_points += POINTS_EATING_MONSTER;
					SoundPlayer.playEatMonsterSound();
				} else {
					// monster beats pacman.
					// stop the game.
					// pacman is die for 2 seconds, and start another match (or
					// game over if there is no remaining lives)
					_pacman[0].die();
					_gameTimer.stop();
					_specialStageTimer.stop();
					_remainingLives--;
					SoundPlayer.playPacmanDieSound();

					// delay 2 seconds and start another match
					try {
						Thread.sleep(2000);
						if (_remainingLives < 0) {
							// Game Over
							// initialize a new game
							initializeNewGame();
						}

						// start another match
						gameRestart();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}

		// pacman eating pills
		StationaryObject[][] map = _levelMap[0].getStationaryObjectsMap();
		StationaryObject collidableObject = map[_pacman[0].getPosition().y][_pacman[0]
				.getPosition().x];

		if (collidableObject != null
				&& collidableObject.isCollidableWith(_pacman[0])) {;
			map[_pacman[0].getPosition().y][_pacman[0].getPosition().x].collideWith(_pacman[0], this);
		}

		// determines if pacman ate all the pills
		if (_remainingPills == 0) {
			// FINISH GAME & WIN!
			_gameTimer.stop();
			initializeNewGame();
			gameRestart();
		}
	}
	//change pacmantransmission to the pacman
	public void changetopacman(PacmanTransmission pacman){
		
		Point position = pacman.getPosition();
		Direction direction = pacman.getDirection();
		
		_pacman[1].setPosition(position.x, position.y);
		_pacman[1].setDirection(direction);
		
		StationaryObject[][] map_1 = _levelMap[1].getStationaryObjectsMap();
		StationaryObject collidableObject = map_1[_pacman[1].getPosition().y][_pacman[1]
				.getPosition().x];
		updateGame();
		if (collidableObject != null
				&& collidableObject.isCollidableWith(_pacman[1])) {
			map_1[_pacman[1].getPosition().y][_pacman[1].getPosition().x]
					.collideWith(_pacman[1],this);
		}
		updateGame();
		
		
	}

	/**
	 * eat a pill (uses the visitor pattern)
	 * 
	 * @param pacman
	 *            pacman object
	 * @param pill
	 *            pill to be eaten
	 */
	public void eatPill(Pacman pacman, Pill pill) {
		pill.getParent().remove(pill);
		_levelMap[0].getStationaryObjectsMap()[pacman.getPosition().y][pacman
				.getPosition().x] = null;
		_points += POINTS_EATING_PILL;
		_remainingPills--;
	}

	/**
	 * Enter to the special stage (set the monster to be in fear, play music,
	 * change the pacman)
	 * 
	 * @param pacman
	 *            pacman to change for the special stage
	 */
	private void enterSpecialStage(Pacman pacman) {
		// set the current pacman to the special stage's pacman
		_pacman[0] = pacman;
		// Jason
		_gameView[0].setPacman(_pacman[0]);
		// _gameView1.setPacman(_pacman);

		// set monsters to be in fear from the current pacman
		for (Monster m : _monsters) {
			m.setPath(null);
			m.setNormalMode();
			if (_pacman[0] instanceof SuperPacman)
				m.fearFromSuperPacman();
			else if (_pacman[0] instanceof MightyPacman)
				m.fearFromMightyPacman();
		}

		SoundPlayer.playSpecialStageSound();

		_specialStageTimer.restart();
	}

	/**
	 * eat a super pill (uses the visitor pattern)
	 * 
	 * @param pacman
	 *            pacman object
	 * @param pill
	 *            SuperPill to be eaten
	 */
	public void eatSuperPill(Pacman pacman, SuperPill pill) {
		removeStationaryObjectFromBoard(pacman, pill);
		_points += POINTS_EATING_SUPER_PILL;
		_remainingPills--;
		SoundPlayer.playEatSuperPillSound();

		enterSpecialStage(new SuperPacman(_levelMap[0]));
	}

	/**
	 * eat a mighty pill (uses the visitor pattern)
	 * 
	 * @param pacman
	 *            pacman object
	 * @param pill
	 *            MightyPill to be eaten
	 */
	public void eatMightyPill(Pacman pacman, MightyPill pill) {
		removeStationaryObjectFromBoard(pacman, pill);
		_points += POINTS_EATING_SUPER_PILL;
		_remainingPills--;
		SoundPlayer.playEatSuperPillSound();

		enterSpecialStage(new MightyPacman(_levelMap[0]));
	}

	/**
	 * Remove stationary object (for example a pill after eating)
	 * 
	 * @param pacman
	 *            pacman object
	 * @param object
	 *            stationary object for removal
	 */
	private void removeStationaryObjectFromBoard(Pacman pacman,
			StationaryObject object) {
		object.getParent().remove(object);
		_levelMap[0].getStationaryObjectsMap()[pacman.getPosition().y][pacman
				.getPosition().x] = null;
	}

	@Override
	/**
	 * Run the application
	 */
	public void run() {
		_window.setVisible(true);
	}

	/**
	 * Change Pacman direction- Keyboard Listener
	 * 
	 * @author Lidan Hifi
	 * @version 1.0
	 */
	class MovePacmanListener extends KeyAdapter {
		/*
		 * Set pacman direction Space = stop the pacman
		 * 
		 * @see java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
		 */
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				_pacman[0].setDirection(Direction.UP);
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				_pacman[0].setDirection(Direction.DOWN);
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				_pacman[0].setDirection(Direction.RIGHT);
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				_pacman[0].setDirection(Direction.LEFT);
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				_pacman[0].setDirection(Direction.NONE);
			}

			/* Game Cheat */
			// if you type "OOP" during the game, you'll switch to the special
			// stage!
			// you can use this cheat only 2 times in each game
			if (_cheatUse > 0) {
				if (e.getKeyCode() == KeyEvent.VK_O) {
					_cheat += "O";
				} else if (e.getKeyCode() == KeyEvent.VK_P) {
					_cheat += "P";
				} else {
					_cheat = "";
				}

				if (_cheat.equals("OOP")) {
					enterSpecialStage(new SuperPacman(_levelMap[0]));
					_cheat = "";
					_cheatUse--;
				}
			}
		}
	}

	/**
	 * Exit Application Listener
	 * 
	 * @author Lidan Hifi
	 * @version 1.0
	 */
	class ExitApplicationListener extends WindowAdapter implements
			ActionListener, MouseListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}

		@Override
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			System.exit(0);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
	}
}
