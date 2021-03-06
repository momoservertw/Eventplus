package tw.momocraft.eventplus.utils;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import tw.momocraft.eventplus.handlers.ServerHandler;

public class VaultAPI {
    private Economy econ = null;
    private boolean isEnabled = false;
    private Permission perms = null;

    public VaultAPI() {
        this.setVaultStatus(Bukkit.getServer().getPluginManager().getPlugin("Vault") != null);
    }

    private void enableFeatures() {
        if (tw.momocraft.eventplus.EventPlus.getInstance().getServer().getPluginManager().getPlugin("Vault") != null) {
            if (!this.setupEconomy()) {
                //ServerHandler.sendErrorMessage("&cCan not find the Economy plugin.");
            }
            if (!this.setupPermissions()) {
                ServerHandler.sendErrorMessage("&cCan not find the Permission plugin.");
                ServerHandler.sendErrorMessage("&cYou can only clean the data: Logs and Regions");
            }
        }
    }

    private boolean setupEconomy() {
        if (tw.momocraft.eventplus.EventPlus.getInstance().getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = tw.momocraft.eventplus.EventPlus.getInstance().getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        this.econ = rsp.getProvider();
        return true;
    }

    private boolean setupPermissions() {
        if (tw.momocraft.eventplus.EventPlus.getInstance().getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Permission> rsp = tw.momocraft.eventplus.EventPlus.getInstance().getServer().getServicesManager().getRegistration(Permission.class);
        if (rsp == null) {
            return false;
        }
        this.perms = rsp.getProvider();
        return true;
    }

    public boolean vaultEnabled() {
        return this.isEnabled;
    }

    private void setVaultStatus(boolean bool) {
        if (bool) {
            this.enableFeatures();
        }
        this.isEnabled = bool;
    }


    public Economy getEconomy() {
        return this.econ;
    }

    public Permission getPermissions() {
        return this.perms;
    }
}