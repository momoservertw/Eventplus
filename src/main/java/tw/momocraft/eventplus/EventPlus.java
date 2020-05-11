package tw.momocraft.eventplus;

import org.bukkit.plugin.java.JavaPlugin;
import tw.momocraft.eventplus.handlers.ConfigHandler;
import tw.momocraft.eventplus.handlers.ServerHandler;

public class EventPlus extends JavaPlugin {
    private static tw.momocraft.eventplus.EventPlus instance;

    @Override
    public void onEnable() {
        instance = this;
        ConfigHandler.generateData(false);
        ConfigHandler.registerEvents();
        ServerHandler.sendConsoleMessage("&fhas been Enabled.");
    }

    @Override
    public void onDisable() {
        ServerHandler.sendConsoleMessage("&fhas been Disabled.");
    }

    public static tw.momocraft.eventplus.EventPlus getInstance() {
        return instance;
    }
}
