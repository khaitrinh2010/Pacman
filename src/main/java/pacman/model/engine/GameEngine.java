package pacman.model.engine;

import pacman.model.entity.Renderable;
import pacman.view.ui.Observer;
import pacman.view.ui.Subject;

import java.util.List;


/**
 * The base interface for interacting with the Pac-Man model
 */
public interface GameEngine extends Subject {

    /**
     * Gets the list of renderables in the game
     *
     * @return The list of renderables
     */
    List<Renderable> getRenderables();

    /**
     * Starts the game
     */
    void startGame();

    /**
     * Move the player up
     */
    void moveUp();

    /**
     * Move the player down
     */
    void moveDown();

    /**
     * Move the player left
     */
    void moveLeft();

    /**
     * Move the player right
     */
    void moveRight();

    /**
     * Instruct the model to progress forward in time by one increment.
     */
    void tick();

    void registerObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers();
    void updatePlayerLives(int lives);
    void updatePlayerScore(int score);
    void updateGameState(GameState state);
    int getPLayerScore();
    int getPlayerLives();
    GameState getGameState();
    void terminateGame();
    void transitionToNextLevel();
}
