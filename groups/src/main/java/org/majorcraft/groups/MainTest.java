package org.majorcraft.groups;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.majorcraft.groups.model.Group;

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

        Group moderator = new Group("player").setPrefix("Moderator [").setSuffix("]").setInheritance(player);

        Group admin = new Group("player").setPrefix("Admin [").setSuffix("]").setInheritance(moderator).addPermission("major.groups.classify.all", true);

        grList.add(player);
        grList.add(moderator);
        grList.add(admin);


        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        try {
            mapper.writeValue(new File("groups.yml"), new Group[]{player, moderator, admin});

            Group[] result = mapper.readValue(new File("groups.yml"), Group[].class);

            for (Group g : result) {
                System.out.println(g.getGroupId());
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}

