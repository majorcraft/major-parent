package org.majorcraft.groups;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;
import org.majorcraft.groups.commands.ClassifyCommand;
import org.majorcraft.groups.commands.GroupCommand;
import org.majorcraft.groups.listener.GroupListener;

import java.io.File;

/**
 * Major Groups Main Class
 */
public class MajorGroups extends JavaPlugin {

    private static MajorGroups instance;

    public static MajorGroups getInstance() {
        return instance;
    }

    public MajorGroups() {
        init();
    }

    public MajorGroups(JavaPluginLoader loader, PluginDescriptionFile description, File dataFolder, File file) {
        super(loader, description, dataFolder, file);
        init();
    }

    private void init() {
        instance = this;
    }

    @Override
    public void onEnable() {
        super.onEnable();

        System.out.println("MajorGroups Enabled");

        registerListener();
        registerCommands();

    }

    @Override
    public void onDisable() {
        super.onDisable();

    }

    private void registerCommands() {
        getCommand("group").setExecutor(new GroupCommand());
        getCommand("classify").setExecutor(new ClassifyCommand());

    }

    private void registerListener() {
        getServer().getPluginManager().registerEvents(new GroupListener(), this);

    }


}
