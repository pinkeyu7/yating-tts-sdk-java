package yating_tts;

import org.json.JSONObject;

import yating_tts.constants.AudioEncoding;
import yating_tts.constants.AudioSampleRate;
import yating_tts.constants.InputType;
import yating_tts.constants.VoiceModel;
import yating_tts.constants.VoiceSpeed;
import yating_tts.constants.VoicePitch;
import yating_tts.constants.VoiceEnergy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

public class TtsClient {
    static String ttsApiUrl;
    static String ttsApiKey;

    public TtsClient(String url, String key) {
        ttsApiUrl = url;
        ttsApiKey = key;
    }

    public void Synthesize(String inputText, String inputType,
            String voiceModel, Double voiceSpeed, Double voicePitch, Double voiceEnergy,
            String audioEncoding, String audioSampleRate,
            String fileName)
            throws Exception {

        try {
            // parameter validation
            validation(inputType, voiceModel, voiceSpeed, voicePitch, voiceEnergy, audioEncoding, audioSampleRate);

            // mapping http request body
            String bodyString = bodyGenerator(inputText, inputType, voiceModel, voiceSpeed, voicePitch, voiceEnergy,
                    audioEncoding, audioSampleRate);

            // send http post request
            JSONObject responseBody = sendHttpRequest(bodyString);

            // get audio content and decode
            String audioContent = responseBody.getString("audioContent");
            byte[] decodedBytes = Base64.getDecoder().decode(audioContent);

            // save to file
            saveToFile(fileName, audioEncoding, decodedBytes);
        } catch (Exception e) {
            throw e;
        }
    }

    private static void validation(String inputType, String voiceModel, Double voiceSpeed, Double voicePitch,
            Double voiceEnergy, String audioEncoding,
            String audioSampleRate) throws Exception {
        if (!InputType.validate(inputType)) {
            throw new Exception("inputType: " + inputType + " is not allowed.");
        }
        if (!VoiceModel.validate(voiceModel)) {
            throw new Exception("voiceModel: " + voiceModel + " is not allowed.");
        }
        if (!AudioEncoding.validate(audioEncoding)) {
            throw new Exception("audioEncoding: " + audioEncoding + " is not allowed.");
        }
        if (!AudioSampleRate.validate(voiceModel, audioSampleRate)) {
            throw new Exception("audioSampleRate: " + audioSampleRate + " is not allowed.");
        }

        if (!VoiceSpeed.validate(voiceSpeed)) {
            throw new Exception("voiceSpeed: " + voiceSpeed + " out of range.");
        }
        if (!VoicePitch.validate(voicePitch)) {
            throw new Exception("voicePitch: " + voicePitch + " out of range.");
        }
        if (!VoiceEnergy.validate(voiceEnergy)) {
            throw new Exception("voiceEnergy: " + voiceEnergy + " out of range.");
        }
    }

    private static String bodyGenerator(String inputText, String inputType, String voiceModel, Double voiceSpeed,
            Double voicePitch, Double voiceEnergy, String audioEncoding,
            String audioSampleRate) {
        return new JSONObject()
                .put("input", new JSONObject().put("text", inputText).put("type", inputType))
                .put("voice",
                        new JSONObject().put("model", voiceModel).put("speed", voiceSpeed).put("pitch", voicePitch)
                                .put("energy", voiceEnergy))
                .put("audioConfig", new JSONObject().put("encoding", audioEncoding).put("sampleRate", audioSampleRate))
                .toString();
    }

    private static JSONObject sendHttpRequest(String bodyString) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(ttsApiUrl))
                .header("Content-Type", "application/json")
                .header("key", ttsApiKey)
                .POST(HttpRequest.BodyPublishers.ofString(bodyString))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200 && response.statusCode() != 201) {
            throw new Exception("http request fallure");
        }

        return new JSONObject(response.body());
    }

    private static void saveToFile(String fileName, String audioEncoding, byte[] data) throws Exception {
        try {
            String fileExtension = "";

            switch (audioEncoding) {
                case AudioEncoding.Linear16:
                    fileExtension = ".wav";
                    break;
                case AudioEncoding.Mp3:
                    fileExtension = ".mp3";
                    break;
            }

            File file = new File(fileName + fileExtension);
            OutputStream os = new FileOutputStream(file);

            os.write(data);
            os.close();
        } catch (Exception e) {
            throw new Exception("save to file fallure");
        }
    }
}
