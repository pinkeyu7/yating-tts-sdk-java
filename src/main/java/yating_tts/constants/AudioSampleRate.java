package yating_tts.constants;

import java.util.Arrays;
import java.util.List;

public class AudioSampleRate {
    public static final String SR16k = "16K";
    public static final String SR22k = "22K";

    static List<String> getList(String voiceModel) {
        String[] valueList = new String[] {};
        switch (voiceModel) {
            case VoiceModel.ZhEnFemale1:
            case VoiceModel.ZhEnFemale2:
            case VoiceModel.ZhEnMale1:
                valueList = new String[] { SR16k, SR22k };
                break;
            case VoiceModel.TaiFemale1:
            case VoiceModel.TaiFemale2:
            case VoiceModel.TaiMale1:
                valueList = new String[] { SR16k };
                break;
        }
        return Arrays.asList(valueList);
    }

    public static boolean validate(String voiceModel, String value) {
        List<String> list = getList(voiceModel);
        return list.contains(value);
    }
}
