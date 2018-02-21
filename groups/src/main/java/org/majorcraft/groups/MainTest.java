package org.majorcraft.groups;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.bukkit.permissions.Permission;
import org.majorcraft.groups.model.Group;
import org.majorcraft.groups.model.Groups;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainTest {


    public static void main(String[] args) {

        List<Group> grList = new ArrayList<>();

        Map<String, Boolean> permissions = new HashMap<>();

        permissions.put("major.groups.classify", true);
        permissions.put("major.groups.classify.all", false);

        Group player = new Group("player").setPrefix("Player [").setSuffix("]").setPermissions(permissions);

        Group moderator = new Group("moderator").setPrefix("Moderator [").setSuffix("]").setInheritance(player);

        Group admin = new Group("admin").setPrefix("Admin [").setSuffix("]").setInheritance(moderator).addPermission("major.groups.classify.all", true);

        grList.add(player);
        grList.add(moderator);
        grList.add(admin);

        Groups groups = new Groups();
        groups.setGroups(grList);


        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        try {
            mapper.writeValue(new File("groups.yml"), groups);


        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
