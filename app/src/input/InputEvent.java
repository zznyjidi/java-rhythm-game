package input;

public class InputEvent {
    long timestamp;
    char key;
    boolean press;
    int track;
    int index;

    public InputEvent(long timestamp, char key, boolean press, int track, int index) {
        this.timestamp = timestamp;
        this.key = key;
        this.press = press;
        this.track = track;
        this.index = index;
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

    public int getTrack() {
        return track;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "{" +
                " timestamp='" + getTimestamp() + "'" +
                ", key='" + getKey() + "'" +
                ", press='" + isPress() + "'" +
                ", track='" + getTrack() + "'" +
                ", index='" + getIndex() + "'" +
                "}";
    }

}
