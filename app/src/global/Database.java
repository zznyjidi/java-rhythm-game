package global;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import chart.Chart;

public class Database {
    public static final int TRACK_COUNT = 3;

    public static final int JUDGEMENT_PERFECT_WINDOW = 30;
    public static final int JUDGEMENT_GREAT_WINDOW = 30;
    public static final int JUDGEMENT_EARLY_MISS_WINDOW = 40;
    public static final int JUDGEMENT_CRITICAL_WINDOW = 40;

    public static final int JUDGEMENT_GREAT_RANGE = JUDGEMENT_PERFECT_WINDOW + JUDGEMENT_GREAT_WINDOW;
    public static final int JUDGEMENT_LATE_RANGE = JUDGEMENT_GREAT_RANGE;
    public static final int JUDGEMENT_EARLY_RANGE = JUDGEMENT_LATE_RANGE + JUDGEMENT_EARLY_MISS_WINDOW;

    public static List<Map<Character, Integer>> keyMap = new ArrayList<>();

    static {
        keyMap.add(Map.of(
                'q', 0, 'w', 1, 'e', 2,
                'r', 3, 't', 4, 'y', 5,
                'u', 6, 'i', 7, 'o', 8,
                'p', 9));
        keyMap.add(Map.of(
                'a', 0, 's', 1, 'd', 2,
                'f', 3, 'g', 4, 'h', 5,
                'j', 6, 'k', 7, 'l', 8,
                ';', 9));
        keyMap.add(Map.of(
                'z', 0, 'x', 1, 'c', 2,
                'v', 3, 'b', 4, 'n', 5,
                'm', 6, ',', 7, '.', 8,
                '/', 9));
    }

    public static Chart currentChart;
}
