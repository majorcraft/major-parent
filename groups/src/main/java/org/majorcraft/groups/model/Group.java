package org.majorcraft.groups.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@RequiredArgsConstructor
@Accessors(chain = true)
@ToString
public class Group {

    private final String groupId;
    private String prefix;
    private String suffix;

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
     * Cycles through all inherited groups and collects all Permissions
     * @return All Permissions
     */
    public Map<String, Boolean> getAllPermissions(){

        Map<String, Boolean> ret = inheritance.getAllPermissions();
        ret.putAll(permissions);

        return ret;

    }

}
