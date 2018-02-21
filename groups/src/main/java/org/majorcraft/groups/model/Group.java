package org.majorcraft.groups.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonTypeId;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.majorcraft.groups.jackson.GroupNameSerializer;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@RequiredArgsConstructor
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonTypeInfo(visible = true, use = JsonTypeInfo.Id.CLASS)
public class Group {

//    @JsonTypeId
    private final String groupId;
    private String prefix;
    private String suffix;

    @JsonSerialize(using = GroupNameSerializer.class)
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
    @JsonIgnore
    public Map<String, Boolean> getAllPermissions(){

        Map<String, Boolean> ret = inheritance.getAllPermissions();
        ret.putAll(permissions);

        return ret;

    }

}
