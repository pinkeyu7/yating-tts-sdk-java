package yating_tts.constants;

import java.util.Arrays;
import java.util.List;

public class VoiceModel {
    public static final String Female1 = "zh_en_female_1";
    public static final String Female2 = "zh_en_female_2";
    public static final String Male1 = "zh_en_male_1";

    static List<String> getList() {
        String[] valueList = new String[] { Female1, Female2, Male1 };
        return Arrays.asList(valueList);
    }

    public static boolean validate(String value) {
        List<String> list = getList();
        return list.contains(value);
    }
}
