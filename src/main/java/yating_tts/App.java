package yating_tts;

public class App {
    public static void main(String[] args) throws Exception {
        String ttsApiUrl = "TTS_ENDPOINT";
        String ttsApiKey = "PUT_YOUR_API_KEY_HERE";

        String inputText = "歡迎使用雅婷文字轉語音。";
        String inputType = InputType.Text;
        String voiceModel = VoiceModel.Female1;
        String audioEncoding = AudioEncoding.Linear16;
        String audioSampleRate = AudioSampleRate.SR16k;
        String fileName = "example";

        TtsClient client = new TtsClient(ttsApiUrl, ttsApiKey);

        try {
            client.Synthesize(inputText, inputType, voiceModel, audioEncoding, audioSampleRate, fileName);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
