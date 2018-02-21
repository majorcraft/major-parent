package org.majorcraft.groups.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.bukkit.permissions.Permission;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@RequiredArgsConstructor
@Accessors(chain = true)
public class Group {

    private String prefix;
    private String suffix;
    private final String groupId;
    private Group inheritance;

    private Map<String, Boolean> permissions;


    public Group addPermission(String perm, boolean enabled){
        if(permissions == null){
            permissions = new HashMap<>();
        }
        permissions.put(perm, enabled);
        return this;
    }

    /**
     * Cycles through all inheritenced groups and collects all Permissions
     * @return All Permissions
     */
    @JsonIgnore
    public Map<String, Boolean> getAllPermissions(){

        Map<String, Boolean> ret = inheritance.getAllPermissions();
        ret.putAll(permissions);

        return ret;

    }

}
