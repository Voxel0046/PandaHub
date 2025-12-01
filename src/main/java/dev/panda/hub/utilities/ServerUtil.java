package dev.panda.hub.utilities;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import dev.panda.hub.services.impl.ConfigService;
import dev.panda.hub.PandaHub;
import lombok.experimental.UtilityClass;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@UtilityClass
public class ServerUtil {

    private final SimpleDateFormat timeDate = new SimpleDateFormat(ConfigService.TIME_DATE);
    private final SimpleDateFormat timeHour = new SimpleDateFormat(ConfigService.TIME_HOUR);

    public void sendBungeeServer(Player player, String server) {
        String playerName = player.getName();

        if (PandaHub.get().getModuleService().getManagerModule().getRankManager().getRank().getRealName(player) != null) {
            playerName = PandaHub.get().getModuleService().getManagerModule().getRankManager().getRank().getRealName(player);
        }

        try {
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("ConnectOther");
            out.writeUTF(playerName);
            out.writeUTF(server);

            player.sendPluginMessage(PandaHub.get(), "BungeeCord", out.toByteArray());
        }
        catch (Exception exception) {
            player.sendMessage(ChatUtil.translate("&cServer '" + server + "' not found."));
        }
    }

    public String getDate() {
        timeDate.setTimeZone(TimeZone.getTimeZone(ConfigService.TIME_ZONE));
        return timeDate.format(new Date());
    }

    public String getHour() {
        timeHour.setTimeZone(TimeZone.getTimeZone(ConfigService.TIME_ZONE));
        return timeHour.format(new Date());
    }
}
