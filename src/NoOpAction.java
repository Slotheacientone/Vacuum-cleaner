

public class NoOpAction extends Action {
    public final static NoOpAction NO_OP = new NoOpAction();

    @Override
    public boolean isNoOp() {
        return true;
    }
}
