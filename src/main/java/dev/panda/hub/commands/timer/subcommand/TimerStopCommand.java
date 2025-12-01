package dev.panda.hub.commands.timer.subcommand;

import dev.panda.hub.utilities.ChatUtil;
import dev.panda.hub.utilities.command.BaseCommand;
import dev.panda.hub.utilities.command.Command;
import dev.panda.hub.utilities.command.CommandArgs;
import dev.panda.hub.PandaHub;
import dev.panda.hub.commons.timer.Timer;
import dev.panda.hub.commons.timer.TimerManager;
import org.bukkit.command.CommandSender;

public class TimerStopCommand extends BaseCommand {

    private final TimerManager timerManager;

    public TimerStopCommand() {
        this.timerManager = PandaHub.get().getModuleService().getManagerModule().getTimerManager();
    }

    @Command(name = "timer.stop", aliases = {"customt.stop", "ctimer.stop", "ct.stop"}, permission = "pandahub.command.timer.stop", inGameOnly = false)
    @Override
    public void onCommand(CommandArgs command) {
        CommandSender sender = command.getSender();
        String[] args = command.getArgs();

        if (args.length < 1) {
            ChatUtil.sendMessage(sender, "&cUsage: /" + command.getLabel() + " stop <timer>");
            return;
        }

        String timerName = ChatUtil.capitalize(args[0]);
        Timer timer = timerManager.getTimer(timerName);

        if (timer == null) {
            ChatUtil.sendMessage(sender, "&cTimer '" + timerName + "' is not activated.");
            return;
        }

        timer.setRunning(false);
        ChatUtil.sendMessage(sender, "&cCustom Timer '&f" + timerName + "&c' has been stopped.");
    }
}
