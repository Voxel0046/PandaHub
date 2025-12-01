package dev.panda.hub.commands.timer.subcommand;

import dev.panda.hub.utilities.ChatUtil;
import dev.panda.hub.utilities.command.BaseCommand;
import dev.panda.hub.utilities.command.Command;
import dev.panda.hub.utilities.command.CommandArgs;
import dev.panda.hub.PandaHub;
import dev.panda.hub.commons.timer.Timer;
import dev.panda.hub.commons.timer.TimerManager;
import org.bukkit.command.CommandSender;

public class TimerListCommand extends BaseCommand {

    private final TimerManager timerManager;

    public TimerListCommand() {
        this.timerManager = PandaHub.get().getModuleService().getManagerModule().getTimerManager();
    }

    @Command(name = "timer.list", aliases = {"customt.list", "ctimer.list", "ct.list"}, permission = "pandahub.command.timer.list", inGameOnly = false)
    @Override
    public void onCommand(CommandArgs command) {
        CommandSender sender = command.getSender();

        ChatUtil.sendMessage(sender, " ");
        ChatUtil.sendMessage(sender, " &9&lTimer List");

        if (timerManager.getTimers().isEmpty()) {
            ChatUtil.sendMessage(sender, " &9┃ &cThere are no timers.");
        } else {
            for (Timer timer : timerManager.getTimers().values()) {
                ChatUtil.sendMessage(sender, " &9┃ &f" + timer.getName());
            }
        }

        ChatUtil.sendMessage(sender, " ");
    }
}
