


public class Environment {
    public static final Action MOVE_LEFT = new DynamicAction("LEFT");
    public static final Action MOVE_RIGHT = new DynamicAction("RIGHT");
    public static final Action MOVE_UP = new DynamicAction("UP");
    public static final Action MOVE_DOWN = new DynamicAction("DOWN");

    public static final Action SUCK_DIRT = new DynamicAction("SUCK");
    public static final int OBSTACLE = 2;
    public static final int DIRTY = 1;
    public static final int CLEAN = 0;
    public static final int VACUUM = 3;
    private int performance = 0;


    private EnvironmentState envState;
    private Agent agent = null;

    public Environment(int[][] states) {
        envState = new EnvironmentState(states);
    }

    // add an agent into the enviroment
    public void addAgent(Agent agent, int[] location) {
        this.agent = agent;
        envState.setAgentLocation(location);
    }

    public EnvironmentState getCurrentState() {
        return this.envState;
    }

    // Update enviroment state when agent do an action
    public EnvironmentState executeAction(Action action) {
        boolean canDoAction = false;
        if (action.toString().equals("SUCK")) {
            envState.setLocationState(envState.getAgentLocation(), Environment.CLEAN);
            performance = performance + 500;
            System.out.println("Performance: " + performance);

        }
        int[] location = envState.getAgentLocation();
        if (action.toString().equals("RIGHT")) {
            if ((location[0] + 1) < envState.getNumberOfHorizontalSquares() && envState.getLocationState(new int[]{location[0] + 1, location[1]}) != Environment.OBSTACLE) {
                int[] currentLocation = new int[2];
                currentLocation[0] = location[0] + 1;
                currentLocation[1] = location[1];
                envState.setAgentLocation(currentLocation);
                canDoAction = true;
            }
            if (canDoAction) {
                performance = performance - 10;
            } else {
                performance = performance - 100;
            }
            System.out.println("Performance: " + performance);
        } else if (action.toString().equals("LEFT")) {
            if ((location[0] - 1) >= 0 && envState.getLocationState(new int[]{location[0] - 1, location[1]}) != Environment.OBSTACLE) {
                int[] currentLocation = new int[2];
                currentLocation[0] = location[0] - 1;
                currentLocation[1] = location[1];
                envState.setAgentLocation(currentLocation);
                canDoAction = true;
            }
            if (canDoAction) {
                performance = performance - 10;
            } else {
                performance = performance - 100;
            }
            System.out.println("Performance: " + performance);

        } else if (action.toString().equals("UP")) {
            if ((location[1] - 1) >= 0 && envState.getLocationState(new int[]{location[0], location[1] - 1}) != Environment.OBSTACLE) {
                int[] currentLocation = new int[2];
                currentLocation[0] = location[0];
                currentLocation[1] = location[1] - 1;
                envState.setAgentLocation(currentLocation);
                canDoAction = true;
            }
            if (canDoAction) {
                performance = performance - 10;
            } else {
                performance = performance - 100;
            }
            System.out.println("Performance: " + performance);
        } else if (action.toString().equals("DOWN")) {
            if ((location[1] + 1) < envState.getNumberOfVerticalSquares() && envState.getLocationState(new int[]{location[0], location[1] + 1}) != Environment.OBSTACLE) {
                int[] currentLocation = new int[2];
                currentLocation[0] = location[0];
                currentLocation[1] = location[1] + 1;
                envState.setAgentLocation(currentLocation);
                canDoAction = true;
            }
            if (canDoAction) {
                performance = performance - 10;
            } else {
                performance = performance - 100;
            }
            System.out.println("Performance: " + performance);
        }
        return envState;
    }

    public Percept getPerceptSeenBy() {
        Percept percept = new Percept(envState.getLocationState(envState.getAgentLocation()));
        return percept;
    }

    public void step() {

        Action anAction = agent.execute(getPerceptSeenBy());
        envState = executeAction(anAction);
        envState.repaint();
    }

    public void step(int n) {
        for (int i = 0; i < n; i++) {
            if (envState.checkClean()) {
                break;
            }
            step();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
