package colouredballs.state;

import colouredballs.model.Ball;
import javafx.scene.paint.Paint;

public class BlackState implements State{
    private Ball ball;
    public BlackState(Ball ball){
        this.ball = ball;
    }
    @Override
    public void handleCollision() {
        ball.setColour(Paint.valueOf("RED"));
        ball.setState(ball.getRedSTate());
    }
}
