package dev.panda.hub.profile;

import com.google.common.collect.Maps;
import dev.panda.hub.utilities.TaskUtil;
import lombok.Getter;

import java.util.Map;
import java.util.UUID;

@Getter
public class ProfileManager {

    private final Map<UUID, Profile> profiles;
    private final IProfile iProfile;

    public ProfileManager() {
        this.profiles = Maps.newHashMap();
        this.iProfile = new FlatFile();

        TaskUtil.runTaskTimer(() -> {
            for (Profile profile : profiles.values()) {
                if (profile != null) profile.save(false, true);
            }
        }, 300L, 300L);
    }

    public Profile getProfile(UUID uuid) {
        if (profiles.containsKey(uuid)) {
            return profiles.get(uuid);
        }
        return null;
    }

    public Profile createProfile(UUID uuid, String name) {
        Profile profile = new Profile(iProfile, uuid, name);
        profiles.put(uuid, profile);
        return profile;
    }

    public void save() {
        for (Profile profile : profiles.values()) {
            profile.save(false, false);
        }
    }
}
