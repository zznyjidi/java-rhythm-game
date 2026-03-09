package input;

public class InputEvent {
    long timestamp;
    String event;

    public InputEvent(String event) {
        this.timestamp = System.nanoTime();
        this.event = event;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getEvent() {
        return event;
    }
}
