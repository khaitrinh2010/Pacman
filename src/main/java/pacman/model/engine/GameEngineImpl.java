package pacman.model.engine;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import pacman.model.entity.Renderable;
import pacman.model.level.Level;
import pacman.model.level.LevelImpl;
import pacman.model.maze.Maze;
import pacman.model.maze.MazeCreator;

import java.util.ArrayList;
import java.util.List;
import pacman.view.ui.Observer;

/**
 * Implementation of GameEngine - responsible for coordinating the Pac-Man model
 */
public class GameEngineImpl implements GameEngine {

    private Level currentLevel;
    private int numLevels;
    private int currentLevelNo;
    private Maze maze;
    private JSONArray levelConfigs;
    private List<Observer> observers = new ArrayList<>();
    private int score;
    private GameState gameState;

    private int lives;
    public GameEngineImpl(String configPath) {
        gameState = GameState.READY;
        this.currentLevelNo = 0;
        init(new GameConfigurationReader(configPath));
    }

    private void init(GameConfigurationReader gameConfigurationReader) {
        // Set up map
        String mapFile = gameConfigurationReader.getMapFile();
        MazeCreator mazeCreator = new MazeCreator(mapFile);
        this.maze = mazeCreator.createMaze();
        this.maze.setNumLives(gameConfigurationReader.getNumLives());

        // Get level configurations
        this.levelConfigs = gameConfigurationReader.getLevelConfigs();
        this.numLevels = levelConfigs.size();
        if (levelConfigs.isEmpty()) {
            System.exit(0);
        }
    }

    @Override
    public List<Renderable> getRenderables() {
        return this.currentLevel.getRenderables();
    }

    @Override
    public void moveUp() {
        currentLevel.moveUp();
    }

    @Override
    public void moveDown() {
        currentLevel.moveDown();
    }

    @Override
    public void moveLeft() {
        currentLevel.moveLeft();
    }

    @Override
    public void moveRight() {
        currentLevel.moveRight();
    }

    @Override
    public void startGame() {
        startLevel();
    }

    private void startLevel() {
        JSONObject levelConfig = (JSONObject) levelConfigs.get(currentLevelNo);
        // reset renderables to starting state
        maze.reset();
        this.currentLevel = new LevelImpl(levelConfig, maze, this);
        this.updateGameState(GameState.READY);
    }

    @Override
    public void tick() {
        currentLevel.tick();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
    public void updatePlayerLives(int lives) {
        this.lives = lives;
        notifyObservers();
    }
    @Override
    public void updatePlayerScore(int score) {
        this.score = score;
        notifyObservers();
    }
    @Override
    public void updateGameState(GameState state) {
        this.gameState = state;
        notifyObservers();
        if(state == GameState.GAME_OVER || state == GameState.WIN){
            currentLevel.clearGhostAndPacman();
        }
    }
    @Override
    public int getPLayerScore() {
        return score;
    }
    @Override
    public int getPlayerLives() {
        return this.currentLevel.getNumLives();
    }
    @Override
    public GameState getGameState() {
        return gameState;
    }
    @Override
    public void terminateGame() {
        System.exit(0);
    }
    @Override
    public void transitionToNextLevel() {
        currentLevelNo++;
        if (currentLevelNo >= numLevels) {
            if(currentLevel.isLevelFinished()){
                this.updateGameState(GameState.WIN);
            }
        }
        else {
            this.updateGameState(GameState.PAUSED);
            startLevel();
        }
    }

}

