package dev.panda.hub.services.impl;

import dev.panda.hub.PandaHub;
import dev.panda.hub.services.Service;
import dev.panda.hub.utilities.file.FileConfig;

import java.util.List;

public class QueueService extends Service {

    private final FileConfig queueConfig = PandaHub.get().getModuleService().getFileModule().getFile("queue");

    public static int QUEUE_DELAY;
    public static int QUEUE_POSITION_INTERVAL;
    public static List<String> QUEUE_SERVERS;

    public static String QUEUE_JOIN;
    public static String QUEUE_LEAVE;
    public static List<String> QUEUE_POSITION;
    public static String QUEUE_SEND;
    public static String QUEUE_NOT_IN;
    public static String QUEUE_PAUSED;
    public static String QUEUE_UNPAUSED;
    public static String QUEUE_NOT_FOUND;

    @Override
    public void initialize() {
        QUEUE_DELAY = queueConfig.getInt("QUEUE.DELAY");
        QUEUE_POSITION_INTERVAL = queueConfig.getInt("QUEUE.POSITION_INTERVAL");
        QUEUE_SERVERS = queueConfig.getStringList("QUEUE.SERVERS");

        QUEUE_JOIN = queueConfig.getString("QUEUE_JOIN");
        QUEUE_LEAVE = queueConfig.getString("QUEUE_LEAVE");
        QUEUE_POSITION = queueConfig.getStringList("QUEUE_POSITION");
        QUEUE_SEND = queueConfig.getString("QUEUE_SEND");
        QUEUE_NOT_IN = queueConfig.getString("QUEUE_NOT_IN");
        QUEUE_PAUSED = queueConfig.getString("QUEUE_PAUSED");
        QUEUE_UNPAUSED = queueConfig.getString("QUEUE_UNPAUSED");
        QUEUE_NOT_FOUND = queueConfig.getString("QUEUE_NOT_FOUND");
    }
}
