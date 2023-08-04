package yating_tts;

import java.util.Arrays;
import java.util.List;

public class AudioSampleRate {
    public static final String SR16k = "16K";
    public static final String SR22k = "22K";

    static List<String> getList() {
        String[] valueList = new String[] { SR16k, SR22k };
        return Arrays.asList(valueList);
    }

    public static boolean validate(String value) {
        List<String> list = getList();
        return list.contains(value);
    }
}
