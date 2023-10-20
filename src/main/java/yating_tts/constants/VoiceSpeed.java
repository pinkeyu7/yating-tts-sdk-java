package yating_tts.constants;

public class VoiceSpeed {
    public static final Double Max = 1.5;
    public static final Double Min = 0.5;

    public static boolean validate(Double value) {
        return (value <= Max && value >= Min);
    }
}
