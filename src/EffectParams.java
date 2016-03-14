import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanev_000 on 13.03.2016.
 */
public class EffectParams {
    private String name;
    private String targetParameter;
    private String action;
    private int effect;
    private int duration;

    public EffectParams(String name, String targetParameter, String action, int effect, int duration) {
        this.name = name;
        this.targetParameter = targetParameter;
        this.action = action;
        this.effect = effect;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public String getTargetParameter() {
        return targetParameter;
    }

    public String getAction() {
        return action;
    }

    public int getEffect() {
        return effect;
    }

    public int getDuration() {
        return duration;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTargetParameter(String targetParameter) {
        this.targetParameter = targetParameter;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setEffect(int effect) {
        this.effect = effect;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}


