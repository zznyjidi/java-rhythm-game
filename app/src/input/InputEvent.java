package input;

public class InputEvent {
    long timestamp;
    char key;
    boolean press;
    int trackNumber;
    int columnNumber;

    public InputEvent(char key, boolean press) {
        this.timestamp = System.nanoTime();
        this.key = key;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public char getKey() {
        return key;
    }

    public boolean isPress() {
        return press;
    }

    public int getTrackNumber() {
        return trackNumber;
    }

    public int getColumnNumber() {
        return columnNumber;
    }
}
