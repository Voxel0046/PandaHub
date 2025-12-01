package dev.panda.hub.module;

import dev.panda.hub.PandaHub;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public abstract class Module {

    private int priority;

    @Getter private static final List<Module> modules = new ArrayList<>();

    public Module() {
        modules.add(this);
    }

    public abstract void onEnable(PandaHub plugin);
}
