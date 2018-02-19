package org.majorcraft.groups;

import lombok.Getter;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
