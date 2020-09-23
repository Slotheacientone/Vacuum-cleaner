

import javax.swing.*;
import java.util.Random;

public class TestSimpleReflexAgent {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        int[][] states = new int[10][10];
        Random random = new Random();
        double dirt = states.length * states[0].length * 0.2;
        for (int i = 0; i < dirt; i++) {
            int x = random.nextInt(states.length);
            int y = random.nextInt(states[0].length);
            if (x != 1 || y != 1) {
                states[x][y] = Environment.DIRTY;
            }
        }
        double wall = states.length * states[0].length * 0.1;
        for (int i = 0; i < wall; i++) {
            int x = random.nextInt(states.length);
            int y = random.nextInt(states[0].length);
            if (x != 1 || y != 1) {
                states[x][y] = Environment.OBSTACLE;
            }
        }
        Environment env = new Environment(states);
        Agent agent = new Agent(new AgentProgram());
        env.addAgent(agent, new int[]{1, 1});
        jFrame.add(env.getCurrentState());
        jFrame.setSize(480, 480);
        jFrame.setTitle("Vacuum Cleaner");
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        env.step(200);
    }

}
