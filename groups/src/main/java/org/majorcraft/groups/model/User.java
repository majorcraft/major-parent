package org.majorcraft.groups.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
public class User {

    private final UUID userId;
    private final String latestName;

    @Setter
    private Group group;


    public boolean isOnline() {
        return Bukkit.getServer().getPlayer(userId).isOnline();
    }

    /**
     * Checks if a user can classify a user to a specific group
     * @param group the specific group
     * @return true if the Group appears in the inheritance chan of the user
     */
    public boolean canClassify(Group group) {

        Group refGroup = this.group.getInheritance();

        while(refGroup != null){

            if(refGroup.equals(group)){
                return true;
            }
            refGroup = refGroup.getInheritance();
        }
        return false;
    }


}
