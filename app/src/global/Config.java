package global;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Config {
    public static final int TRACK_COUNT = 3;

    public static final int JUDGEMENT_PERFECT_WINDOW = 30;
    public static final int JUDGEMENT_GREAT_WINDOW = 30;
    public static final int JUDGEMENT_EARLY_MISS_WINDOW = 40;
    public static final int JUDGEMENT_CRITICAL_WINDOW = 40;

    public static final int JUDGEMENT_GREAT_RANGE = JUDGEMENT_PERFECT_WINDOW + JUDGEMENT_GREAT_WINDOW;
    public static final int JUDGEMENT_LATE_RANGE = JUDGEMENT_GREAT_RANGE;
    public static final int JUDGEMENT_EARLY_RANGE = JUDGEMENT_LATE_RANGE + JUDGEMENT_EARLY_MISS_WINDOW;

    public static List<Map<String, Integer>> keyMap = new ArrayList<>();

    static {
        keyMap.add(Map.of(
                "Q", 0, "W", 1, "E", 2,
                "R", 3, "T", 4, "Y", 5,
                "U", 6, "I", 7, "O", 8,
                "P", 9));
        keyMap.add(Map.of(
                "A", 0, "S", 1, "D", 2,
                "F", 3, "G", 4, "H", 5,
                "J", 6, "K", 7, "L", 8,
                "Semicolon", 9));
        keyMap.add(Map.of(
                "Z", 0, "X", 1, "C", 2,
                "V", 3, "B", 4, "N", 5,
                "M", 6, "Comma", 7, "Period", 8,
                "Slash", 9));
    }

    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
}
