package tw.momocraft.eventplus;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import tw.momocraft.eventplus.handlers.ConfigHandler;
import tw.momocraft.eventplus.handlers.PermissionsHandler;
import tw.momocraft.eventplus.handlers.PurgeHandler;
import tw.momocraft.eventplus.handlers.ServerHandler;
import tw.momocraft.eventplus.utils.Language;


public class Commands implements CommandExecutor {

    public boolean onCommand(final CommandSender sender, Command c, String l, String[] args) {
        if (args.length == 0) {
            if (PermissionsHandler.hasPermission(sender, "eventplus.use")) {
                Language.dispatchMessage(sender, "");
                Language.sendLangMessage("Message.EventPlus.Commands.title", sender, false);
                if (PermissionsHandler.hasPermission(sender, "eventplus.command.version")) {
                    Language.dispatchMessage(sender, "&d&lEventPlus &e&lv" + EventPlus.getInstance().getDescription().getVersion() + "&8 - &fby Momocraft");
                }
                Language.sendLangMessage("Message.EventPlus.Commands.help", sender, false);
                Language.dispatchMessage(sender, "");
            } else {
                Language.sendLangMessage("Message.noPermission", sender);
            }
            return true;
        } else if (args.length == 1 && args[0].equalsIgnoreCase("help")) {
            if (PermissionsHandler.hasPermission(sender, "eventplus.use")) {
                Language.dispatchMessage(sender, "");
                Language.sendLangMessage("Message.EventPlus.Commands.title", sender, false);
                if (PermissionsHandler.hasPermission(sender, "eventplus.command.version")) {
                    Language.dispatchMessage(sender, "&d&lEventPlus &e&lv" + EventPlus.getInstance().getDescription().getVersion() + "&8 - &fby Momocraft");
                }
                Language.sendLangMessage("Message.EventPlus.Commands.help", sender, false);
                if (PermissionsHandler.hasPermission(sender, "eventplus.command.reload")) {
                    Language.sendLangMessage("Message.EventPlus.Commands.reload", sender, false);
                }
                if (PermissionsHandler.hasPermission(sender, "eventplus.command.clean")) {
                    Language.sendLangMessage("Message.EventPlus.Commands.clean", sender, false);
                }
                Language.dispatchMessage(sender, "");
            } else {
                Language.sendLangMessage("Message.noPermission", sender);
            }
            return true;
        } else if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            if (PermissionsHandler.hasPermission(sender, "eventplus.command.reload")) {
                // working: close purge.Auto-Clean schedule
                ConfigHandler.generateData(true);
                Language.sendLangMessage("Message.configReload", sender);
            } else {
                Language.sendLangMessage("Message.noPermission", sender);
            }
            return true;/*
        } else if (args.length == 1 && args[0].equalsIgnoreCase("version")) {
            if (PermissionsHandler.hasPermission(sender, "eventplus.command.version")) {
                Language.dispatchMessage(sender, "&d&lEventPlus &e&lv" + EventPlus.getInstance().getDescription().getVersion() + "&8 - &fby Momocraft");
                ConfigHandler.getUpdater().checkUpdates(sender);
            } else {
                Language.sendLangMessage("Message.noPermission", sender);
            }
            return true;*/
        } else if (args.length == 1 && args[0].equalsIgnoreCase("clean")) {
            if (PermissionsHandler.hasPermission(sender, "eventplus.command.clean")) {
                ServerHandler.sendConsoleMessage("&6Starting to clean the expired data...");
                PurgeHandler purgeHandler = new PurgeHandler();
                purgeHandler.startClean();
            } else {
                Language.sendLangMessage("Message.noPermission", sender);
            }
            return true;
        } else {
            Language.sendLangMessage("Message.unknownCommand", sender);
            return true;
        }
    }
}