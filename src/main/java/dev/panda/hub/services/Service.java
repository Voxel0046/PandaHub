package dev.panda.hub.services;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public abstract class Service {

    @Getter
    private static final List<Service> services = new ArrayList<>();

    public Service() {
        services.add(this);
    }

    public abstract void initialize();
}
