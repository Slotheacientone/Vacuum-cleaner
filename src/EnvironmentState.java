


import javax.swing.*;
import java.awt.*;

public class EnvironmentState extends JPanel {
    private int[][] states;
    private int[] agent;


    public EnvironmentState(int[][] states) {
        this.states = states;
        agent = new int[2];
    }

    public void setAgentLocation(int[] location) {
       agent=location;
        repaint();
    }

    public int[] getAgentLocation() {
       return agent;
    }

    public int getLocationState(int[] location) {
        return states[location[0]][location[1]];
    }

    public boolean checkClean() {
        boolean clean = true;
        for (int i = 0; i < states.length; i++) {
            for (int j = 0; j < states[0].length; j++) {
                if (states[i][j] == Environment.DIRTY) {
                    clean = false;
                }
            }
        }
        return clean;
    }

    public void setLocationState(int[] location, int state) {
        states[location[0]][location[1]] = state;
    }

    public int getNumberOfHorizontalSquares() {
        return states[0].length;
    }

    public int getNumberOfVerticalSquares() {
        return states.length;
    }

    public int squareHeight() {
        return getHeight() / states.length;
    }

    public int squareWidth() {
        return getWidth() / states[0].length;
    }

    public void drawing(Graphics g) {
        for (int i = 0; i < states.length; i++) {
            for (int j = 0; j < states[i].length; j++) {
                if (states[i][j] == Environment.DIRTY) {
                    drawDirtSquare(g, i * squareWidth(), j * squareHeight());
                } else if (states[i][j] == Environment.OBSTACLE) {
                    drawObstacleSquare(g, i * squareWidth(), j * squareHeight());
                } else if (states[i][j] == Environment.CLEAN) {
                    drawCleanSquare(g, i * squareWidth(), j * squareHeight());
                }
                    drawVacumSquare(g, agent[0] * squareWidth(), agent[1] * squareHeight());
            }

        }
    }

    public void drawDirtSquare(Graphics g, int x, int y) {
        g.setColor(Color.GRAY);
        g.fillRect(x, y, squareWidth(), squareHeight());

    }

    public void drawObstacleSquare(Graphics g, int x, int y) {
        g.setColor(Color.BLACK);
        g.fillRect(x, y, squareWidth(), squareHeight());

    }

    public void drawCleanSquare(Graphics g, int x, int y) {
        g.setColor(Color.BLACK);
        g.drawRect(x, y, squareWidth(), squareHeight());
    }

    public void drawVacumSquare(Graphics g, int x, int y) {
        g.setColor(Color.RED);
        g.fillRect(x, y, squareWidth(), squareHeight());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawing(g);
    }
}