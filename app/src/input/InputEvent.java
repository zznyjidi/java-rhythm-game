package input;

public class InputEvent {
    long timestamp;
    String key;
    boolean press;
    int track;
    int index;

    public InputEvent(long timestamp, String key, boolean press, int track, int index) {
        this.timestamp = timestamp;
        this.key = key;
        this.press = press;
        this.track = track;
        this.index = index;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getKey() {
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (timestamp ^ (timestamp >>> 32));
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        result = prime * result + (press ? 1231 : 1237);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        InputEvent other = (InputEvent) obj;
        if (timestamp != other.timestamp)
            return false;
        if (key == null) {
            if (other.key != null)
                return false;
        } else if (!key.equals(other.key))
            return false;
        if (press != other.press)
            return false;
        return true;
    }
}
