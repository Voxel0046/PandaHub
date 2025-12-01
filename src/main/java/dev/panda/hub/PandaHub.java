package dev.panda.hub;

import dev.panda.hub.module.ModuleService;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter @Setter
public class PandaHub extends JavaPlugin {

	private final ModuleService moduleService = new ModuleService();

	@Override
	public void onEnable() {
		moduleService.start(this);
	}

	@Override
	public void onDisable() {
		moduleService.stop();
	}

	public static PandaHub get() {
		return getPlugin(PandaHub.class);
	}
}
