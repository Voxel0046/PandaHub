package dev.panda.hub.profile;

public interface IProfile {

    String getProfileSystem();

    void save(Profile profile, boolean destroy, boolean async);

    void load(Profile profile);
}
