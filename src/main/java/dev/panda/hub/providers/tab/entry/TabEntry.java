package dev.panda.hub.providers.tab.entry;

import dev.panda.hub.providers.tab.skin.Skin;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Accessors(chain = true)
@Getter
@RequiredArgsConstructor
public class TabEntry {

    private final int column;
    private final int row;

    private final String text;

    private int ping = 0;
    private final Skin skin;
}
