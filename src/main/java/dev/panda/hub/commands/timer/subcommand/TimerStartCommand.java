package dev.panda.hub.commands.timer.subcommand;

import dev.panda.hub.utilities.ChatUtil;
import dev.panda.hub.utilities.command.BaseCommand;
import dev.panda.hub.utilities.command.Command;
import dev.panda.hub.utilities.command.CommandArgs;
import dev.panda.hub.PandaHub;
import dev.panda.hub.commons.timer.Timer;
import dev.panda.hub.commons.timer.TimerManager;
import dev.panda.hub.utilities.JavaUtil;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;

public class TimerStartCommand extends BaseCommand {

    private final TimerManager timerManager;

    public TimerStartCommand() {
        this.timerManager = PandaHub.get().getModuleService().getManagerModule().getTimerManager();
    }

    @Command(name = "timer.start", aliases = {"customt.start", "ctimer.start", "ct.start"}, permission = "pandahub.command.timer.start", inGameOnly = false)
    @Override
    public void onCommand(CommandArgs command) {
        CommandSender sender = command.getSender();
        String[] args = command.getArgs();

        if (args.length < 3) {
            ChatUtil.sendMessage(sender, "&cUsage: /" + command.getLabel() + " start <timer> <time> <prefix>");
            return;
        }

        String timerName = ChatUtil.capitalize(args[0]);
        Timer timer = timerManager.getTimer(timerName);

        if (timer != null) {
            ChatUtil.sendMessage(sender, "&cTimer '" + timer + "' already exist.");
            return;
        }

        long duration = JavaUtil.formatLong(args[1]);

        if (duration == -1L) {
            ChatUtil.sendMessage(sender, "&c" + args[1] + " is an invalid duration.");
            return;
        }

        if (duration < 1000L) {
            ChatUtil.sendMessage(sender, "&cTimer must last for at least 20 ticks.");
            return;
        }

        String prefix = StringUtils.join(args, ' ', 2, args.length);

        timerManager.createTimer(new Timer(timerName, System.currentTimeMillis(), System.currentTimeMillis() + duration, prefix));
        ChatUtil.sendMessage(sender, "&aCustom Timer '&f" + timerName + "&a' has been created.");
    }
}
