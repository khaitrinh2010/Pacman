package colouredballs.state;

import colouredballs.model.Ball;
import javafx.scene.paint.Paint;

public class RedState implements State {
    private Ball ball;
    public RedState(Ball ball){
        this.ball = ball;
    }
    @Override
    public void handleCollision() {
        ball.setColour(Paint.valueOf("BLUE"));
        ball.setState(ball.getBlueState());
    }
}
