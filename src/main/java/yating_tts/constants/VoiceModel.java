package yating_tts.constants;

import java.util.Arrays;
import java.util.List;

public class VoiceModel {
    public static final String ZhEnFemale1 = "zh_en_female_1";
    public static final String ZhEnFemale2 = "zh_en_female_2";
    public static final String ZhEnMale1 = "zh_en_male_1";

    public static final String TaiFemale1 = "tai_female_1";
    public static final String TaiFemale2 = "tai_female_2";
    public static final String TaiMale1 = "tai_male_1";

    static List<String> getList() {
        String[] valueList = new String[] { ZhEnFemale1, ZhEnFemale2, ZhEnMale1, TaiFemale1, TaiFemale2, TaiMale1 };
        return Arrays.asList(valueList);
    }

    public static boolean validate(String value) {
        List<String> list = getList();
        return list.contains(value);
    }
}
