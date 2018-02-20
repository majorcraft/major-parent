package org.majorcraft.groups.repo;

import org.majorcraft.groups.model.Group;

import java.util.List;
import java.util.UUID;

public interface GroupRepository {

    List<Group> getAllGroups();

    Group findGroup(String groupId);

    boolean addGroup(Group g);

    Group getDefaultGroup();

    boolean removeGroup(Group group);

}
