package dev.panda.hub.commons.timer;

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter @Setter
public class TimerManager {

    private Map<String, Timer> timers;

    public TimerManager() {
        this.timers = Maps.newHashMap();
    }

    public void createTimer(Timer timer) {
        timers.put(timer.getName(), timer);
    }

    public void deleteTimer(Timer timer) {
        timers.remove(timer.getName());
    }

    public Timer getTimer(String name) {
        return timers.get(name);
    }
}
