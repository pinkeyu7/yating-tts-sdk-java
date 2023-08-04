package yating_tts;

import java.util.Arrays;
import java.util.List;

public class AudioEncoding {
    public static final String Mp3 = "MP3";
    public static final String Linear16 = "LINEAR16";

    static List<String> getList() {
        String[] valueList = new String[] { Mp3, Linear16 };
        return Arrays.asList(valueList);
    }

    public static boolean validate(String value) {
        List<String> list = getList();
        return list.contains(value);
    }
}
