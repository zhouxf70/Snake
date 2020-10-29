package com.example.zhouxf.snake;

import org.junit.Test;

import static com.example.zhouxf.snake.Util.print;
import static com.example.zhouxf.snake.Util.println;
import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private Snake snake = new Snake(10, 10);

    Snake.Direction[] directions = new Snake.Direction[]
            {Snake.Direction.TOP, Snake.Direction.BOTTOM, Snake.Direction.LEFT, Snake.Direction.RIGHT};

    @Test
    public void test() {
        for (int i = 0; i < 100; i++) {
            int d = (int) (Math.random() * 4);
            print(directions[d]);
            boolean meet = snake.setDir(directions[d]);
            println(snake.listNode());
            if (meet) {
                break;
            }
        }
    }

    @Test
    public void foodTest() {
        for (int i = 0; i < 30; i++) {
            Snake.Node node = snake.newFood();
            println(node);
        }
    }

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void checkDirs() {
        checkDir(Snake.Direction.TOP);
//        checkDir(Snake.Direction.BOTTOM);
//        checkDir(Snake.Direction.LEFT);
//        checkDir(Snake.Direction.RIGHT);
    }

    public void checkDir(Snake.Direction direction) {
        snake.setDir(direction);
        boolean b1 = snake.checkDir(Snake.Direction.TOP);
        boolean b2 = snake.checkDir(Snake.Direction.BOTTOM);
        boolean b3 = snake.checkDir(Snake.Direction.LEFT);
        boolean b4 = snake.checkDir(Snake.Direction.RIGHT);
        println(b1);
        println(b2);
        println(b3);
        println(b4);
        println("------");
    }

    @Test
    public void checkForwards() {
        snake.setFood(5, 8);
        println(snake.listNode());
        println("--------------");
        checkForward(Snake.Direction.TOP);
        checkForward(Snake.Direction.BOTTOM);
        checkForward(Snake.Direction.LEFT);
        checkForward(Snake.Direction.RIGHT);
        checkForward(Snake.Direction.TOP);
        checkForward(Snake.Direction.RIGHT);
        checkForward(Snake.Direction.TOP);
        checkForward(Snake.Direction.TOP);
        checkForward(Snake.Direction.TOP);
        checkForward(Snake.Direction.TOP);
        checkForward(Snake.Direction.TOP);
        checkForward(Snake.Direction.TOP);
        checkForward(Snake.Direction.TOP);
        checkForward(Snake.Direction.TOP);
        checkForward(Snake.Direction.TOP);
    }

    public void checkForward(Snake.Direction direction) {
        println("是否相遇：" + snake.setDir(direction));
        println(snake.listNode());
    }

}