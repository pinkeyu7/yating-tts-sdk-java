package yating_tts.constants;

public class VoicePitch {
    public static final Double Max = 1.5;
    public static final Double Min = 0.5;

    public static boolean validate(Double value) {
        return (value <= Max && value >= Min);
    }
}
