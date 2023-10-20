package yating_tts;

import yating_tts.constants.AudioEncoding;
import yating_tts.constants.AudioSampleRate;
import yating_tts.constants.InputType;
import yating_tts.constants.VoiceModel;

public class App {
    public static void main(String[] args) throws Exception {
        String ttsApiUrl = "TTS_ENDPOINT";
        String ttsApiKey = "PUT_YOUR_API_KEY_HERE";

        String inputText = "歡迎使用雅婷文字轉語音。";
        String inputType = InputType.Text;
        String voiceModel = VoiceModel.ZhEnFemale1;
        Double voiceSpeed = 1.0;
        Double voicePitch = 1.0;
        Double voiceEnergy = 1.0;
        String audioEncoding = AudioEncoding.Linear16;
        String audioSampleRate = AudioSampleRate.SR16k;
        String fileName = "example";

        TtsClient client = new TtsClient(ttsApiUrl, ttsApiKey);

        try {
            client.Synthesize(inputText, inputType, voiceModel, voiceSpeed, voicePitch, voiceEnergy, audioEncoding,
                    audioSampleRate,
                    fileName);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
