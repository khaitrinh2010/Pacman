package pacman.view;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.util.Duration;
import pacman.model.engine.GameEngine;
import pacman.model.engine.GameState;
import pacman.model.entity.Renderable;
import pacman.model.entity.dynamic.player.Pacman;
import pacman.util.ImageFile;
import pacman.view.background.BackgroundDrawer;
import pacman.view.background.StandardBackgroundDrawer;
import pacman.view.entity.EntityView;
import pacman.view.entity.EntityViewImpl;
import pacman.view.keyboard.KeyboardInputHandler;
import pacman.view.ui.Observer;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Responsible for managing the Pac-Man Game View
 */
public class GameWindow implements Observer {

    public static final File FONT_FILE = new File("src/main/resources/maze/PressStart2P-Regular.ttf");
    private final Scene scene;
    private final Pane pane;
    private final GameEngine model;
    private final List<EntityView> entityViews;
    private Text scoreText;
    private Text stateText;
    private List<ImageView> lifeImages = new ArrayList<>();
    private KeyboardInputHandler keyboardInputHandler;

    public GameWindow(GameEngine model, int width, int height) {
        this.model = model;
        this.model.registerObserver(this);

        pane = new Pane();
        scene = new Scene(pane, width, height);

        entityViews = new ArrayList<>();

        keyboardInputHandler = new KeyboardInputHandler();
        scene.setOnKeyPressed(keyboardInputHandler::handlePressed);

        BackgroundDrawer backgroundDrawer = new StandardBackgroundDrawer();
        backgroundDrawer.draw(model, pane);

    }

    public Scene getScene() {
        return scene;
    }

    public void run() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(34),
                t -> this.draw()));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        model.startGame();
        Pacman pacman = model.getRenderables().stream()
                .filter(entity -> entity instanceof Pacman)
                .map(entity -> (Pacman) entity)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Pacman not found"));
        keyboardInputHandler.createController(pacman);
    }

    private void draw() {
        model.tick();

        List<Renderable> entities = model.getRenderables();

        for (EntityView entityView : entityViews) {
            entityView.markForDelete();
        }

        for (Renderable entity : entities) {
            boolean notFound = true;
            for (EntityView view : entityViews) {
                if (view.matchesEntity(entity)) {
                    notFound = false;
                    view.update();
                    break;
                }
            }
            if (notFound) {
                EntityView entityView = new EntityViewImpl(entity);
                entityViews.add(entityView);
                pane.getChildren().add(entityView.getNode());
            }
        }
        for (EntityView entityView : entityViews) {
            if (entityView.isMarkedForDelete()) {
                pane.getChildren().remove(entityView.getNode());
            }
        }
        entityViews.removeIf(EntityView::isMarkedForDelete);
    }

    @Override
    public void update() {
        updateStateUi();
        updateScoreUi();
        updateLivesUi();
    }

    private void updateScoreUi() {
        if (scoreText == null) {
            scoreText = new Text();
            scoreText.setFill(Paint.valueOf("WHITE"));
            scoreText.setX(20);
            scoreText.setY(40);
            scoreText.setFont(javafx.scene.text.Font.loadFont("file:" + FONT_FILE.getAbsolutePath(), 20));
            pane.getChildren().add(scoreText);
        }
        scoreText.setText(String.valueOf(model.getPLayerScore())); // Update the existing text instead of creating a new one
    }

    private void updateLivesUi() {
        pane.getChildren().removeAll(lifeImages);
        lifeImages.clear();
        for (int i = 0; i < model.getPlayerLives(); i++) {
            ImageView lifeImage = new ImageView(ImageFile.PACMAN_RIGHT);
            lifeImage.setX(10 + i * 30);
            lifeImage.setY(scene.getHeight() - 30);
            lifeImages.add(lifeImage);
            pane.getChildren().add(lifeImage);
        }
    }

    private void updateStateUi() {
        if (stateText == null) {
            stateText = new Text();
            stateText.setX(165);
            stateText.setY(340);
            stateText.setFont(javafx.scene.text.Font.loadFont("file:" + FONT_FILE.getAbsolutePath(), 20));
            pane.getChildren().add(stateText);
        }
        if (model.getGameState() == GameState.READY) {
            drawStateBasedOnContentAndColour("READY!", "WHITE");
            PauseTransition pause = new PauseTransition(Duration.seconds(3.4));
            pause.setOnFinished(e -> {
                stateText.setText("");
                model.updateGameState(GameState.RUNNING);
            });
            pause.play();
        } else if (model.getGameState() == GameState.GAME_OVER) {
            drawStateBasedOnContentAndColour("GAME OVER", "RED");
            PauseTransition pause = new PauseTransition(Duration.seconds(3.4));
            pause.setOnFinished(e -> {
                stateText.setText("");
                model.terminateGame();
            });
            pause.play();
        } else if (model.getGameState() == GameState.WIN) {
            drawStateBasedOnContentAndColour("YOU WIN!", "WHITE");
            PauseTransition pause = new PauseTransition(Duration.seconds(3.4));
            pause.setOnFinished(e -> {
                stateText.setText("");
                model.terminateGame();
            });
            pause.play();
        } else {
            stateText.setText("");
        }
    }
    private void drawStateBasedOnContentAndColour(String content, String colour) {
        stateText.setText(content);
        stateText.setFill(Paint.valueOf(colour));
    }
}
