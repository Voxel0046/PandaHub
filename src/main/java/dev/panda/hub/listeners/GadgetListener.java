package dev.panda.hub.listeners;

import dev.panda.hub.listeners.gadget.*;
import dev.panda.hub.PandaHub;
import dev.panda.hub.listeners.gadget.*;
import org.bukkit.event.Listener;

public class GadgetListener implements Listener {

	public GadgetListener(PandaHub plugin) {
		new BowTeleportGadgetListener(plugin);
		new RiderGadgetListener(plugin);
		new SnowmanGadgetListener(plugin);
		new FireworkGadgetListener(plugin);
		new GrapplingHookGadgetListener(plugin);
	}
}
