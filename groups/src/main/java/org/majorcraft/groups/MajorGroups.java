package org.majorcraft.groups;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

import java.io.File;

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

    private void init(){
        instance = this;
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }


}
