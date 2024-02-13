package dev.lwnd.member;

import dev.lwnd.other.Loggable;
import dev.lwnd.other.ObjectDescription;

/**
 * Represents a member in the library system.
 */
@Loggable
@ObjectDescription(description = "Represents a member in the library system.")
public class Member {
    private String username;
    private boolean hasAdminRights;

    /**
     * Constructs a Member object with the specified username.
     * The member does not have admin rights by default.
     *
     * @param username the username of the member
     */
    public Member(String username) {
        this.username = username;
        hasAdminRights = false;
    }

    /**
     * Constructs a Member object with the specified username and admin rights.
     *
     * @param username        the username of the member
     * @param hasAdminRights  true if the member has admin rights, false otherwise
     */
    public Member(String username, boolean hasAdminRights) {
        this.username = username;
        this.hasAdminRights = hasAdminRights;
    }

    /**
     * Returns the username of the member.
     *
     * @return the username of the member
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the member.
     * The username must not be null or empty.
     *
     * @param username the new username of the member
     */
    public void setUsername(String username) {
        if (username != null && !username.isEmpty()) {
            this.username = username;
        }
    }

    /**
     * Checks if the member has admin rights.
     *
     * @return true if the member has admin rights, false otherwise
     */
    public boolean hasAdminRights() {
        return hasAdminRights;
    }

    /**
     * Sets the admin rights of the member.
     *
     * @param hasAdminRights true if the member has admin rights, false otherwise
     */
    public void setAdminRights(boolean hasAdminRights) {
        this.hasAdminRights = hasAdminRights;
    }
}
