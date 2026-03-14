package judgment;

public class JudgeResult {
    static enum State {
        Perfect, Great, Miss, NotInRange
    }

    static enum Timing {
        Early, Critical, Late
    }

    long timeOffset;
    State state;
    Timing timing;

    public JudgeResult(long timeOffset, State state, Timing timing) {
        this.timeOffset = timeOffset;
        this.state = state;
        this.timing = timing;
    }

    public long getTimeOffset() {
        return timeOffset;
    }

    public State getState() {
        return state;
    }

    public Timing getTiming() {
        return timing;
    }

    @Override
    public String toString() {
        return "{" +
                " timeOffset='" + getTimeOffset() + "'" +
                ", state='" + getState() + "'" +
                ", timing='" + getTiming() + "'" +
                "}";
    }
}
