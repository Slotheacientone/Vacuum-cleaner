

import java.util.Random;

public class AgentProgram {

    public Action execute(Percept p) {// location, status
        if (p.getLocationState() == Environment.DIRTY) {
            System.out.println("SUCK");
            return Environment.SUCK_DIRT;
        } else {
            Random random = new Random();
            int i = random.nextInt(4);
            switch (i) {
                case 0:
                    System.out.println("right");
                    return Environment.MOVE_RIGHT;
                case 1:
                    System.out.println("left");
                    return Environment.MOVE_LEFT;
                case 2:
                    System.out.println("up");
                    return Environment.MOVE_UP;
                case 3:
                    System.out.println("down");
                    return Environment.MOVE_DOWN;
            }
        }
        return NoOpAction.NO_OP;

    }
}