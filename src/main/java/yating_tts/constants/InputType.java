package yating_tts.constants;

import java.util.Arrays;
import java.util.List;

public class InputType {
    public static final String Text = "text";
    public static final String Ssml = "ssml";

    static List<String> getList() {
        String[] valueList = new String[] { Text, Ssml };
        return Arrays.asList(valueList);
    }

    public static boolean validate(String value) {
        List<String> list = getList();
        return list.contains(value);
    }
}
