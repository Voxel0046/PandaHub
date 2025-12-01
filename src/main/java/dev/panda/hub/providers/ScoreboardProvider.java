package dev.panda.hub.providers;

import com.google.common.collect.Lists;
import dev.panda.hub.commons.timer.Timer;
import dev.panda.hub.commons.timer.TimerManager;
import dev.panda.hub.profile.Profile;
import dev.panda.hub.providers.scoreboard.ScoreboardAdapter;
import dev.panda.hub.providers.scoreboard.ScoreboardStyle;
import dev.panda.hub.services.impl.ScoreboardService;
import dev.panda.hub.utilities.ChatUtil;
import dev.panda.hub.PandaHub;
import dev.panda.hub.commons.queue.QueueManager;
import dev.panda.hub.hooks.PlaceholderAPIHook;
import dev.panda.hub.providers.scoreboard.ScoreboardAnimated;
import dev.panda.hub.utilities.JavaUtil;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

public class ScoreboardProvider implements ScoreboardAdapter {

	private final QueueManager queueManager;
	private final TimerManager timerManager;

	public ScoreboardProvider() {
		this.queueManager = PandaHub.get().getModuleService().getManagerModule().getQueueManager();
		this.timerManager = PandaHub.get().getModuleService().getManagerModule().getTimerManager();
	}

	@Override
	public String getTitle(Player player) {
		return ScoreboardAnimated.TITLE;
	}

	@Override
	public List<String> getLines(Player player) {
		List<String> lines = Lists.newArrayList();

		Profile profile = PandaHub.get().getModuleService().getManagerModule().getProfileManager().getProfile(player.getUniqueId());
		boolean inPvPMode = profile.isPvpMode();

		if (inPvPMode) {
			for (String pvpLine : ScoreboardService.PVP) {
				if (pvpLine.contains("%QUEUE%")) {
					if (queueManager.getQueue().isInQueue(player)) {
						for (String queueLine : ScoreboardService.QUEUE) {
							lines.add(queueLine
									.replace("%QUEUE_NAME%", queueManager.getQueue().getServer(player))
									.replace("%QUEUE_POSITION%", String.valueOf(queueManager.getQueue().getPosition(player)))
									.replace("%QUEUE_SIZE%", String.valueOf(queueManager.getQueue().getSize(player))));
						}
					}
					continue;
				}

				if (pvpLine.contains("%CUSTOM_TIMER%")) {
					for (Timer timer : timerManager.getTimers().values()) {
						for (String timerLine : ScoreboardService.CUSTOM_TIMER) {
							lines.add(timerLine
									.replace("%TIMER%", timer.getPrefix())
									.replace("%TIME%", JavaUtil.formatLongHour(timer.getRemaining())));
						}
					}
					continue;
				}

				if (pvpLine.contains("%FOOTER%")) {
					lines.add(ScoreboardAnimated.FOOTER);
					continue;
				}

				lines.add(ChatUtil.replaced(player, pvpLine));
			}
		} else {
			for (String scoreboardLine : ScoreboardService.MAIN) {
				if (scoreboardLine.contains("%QUEUE%")) {
					if (queueManager.getQueue().isInQueue(player)) {
						for (String queueLine : ScoreboardService.QUEUE) {
							lines.add(queueLine
									.replace("%QUEUE_NAME%", queueManager.getQueue().getServer(player))
									.replace("%QUEUE_POSITION%", String.valueOf(queueManager.getQueue().getPosition(player)))
									.replace("%QUEUE_SIZE%", String.valueOf(queueManager.getQueue().getSize(player))));
						}
					}
					continue;
				}

				if (scoreboardLine.contains("%CUSTOM_TIMER%")) {
					for (Timer timer : timerManager.getTimers().values()) {
						for (String timerLine : ScoreboardService.CUSTOM_TIMER) {
							lines.add(timerLine
									.replace("%TIMER%", timer.getPrefix())
									.replace("%TIME%", JavaUtil.formatLongHour(timer.getRemaining())));
						}
					}
					continue;
				}

				if (scoreboardLine.contains("%FOOTER%")) {
					lines.add(ScoreboardAnimated.FOOTER);
					continue;
				}

				lines.add(ChatUtil.replaced(player, scoreboardLine));
			}
		}

		return PlaceholderAPIHook.isPlaceholderAPI() ? PlaceholderAPI.setPlaceholders(player, lines) : lines;
	}


	@Override
	public ScoreboardStyle getBoardStyle(Player player) {
		return ScoreboardStyle.KOHI;
	}
}
