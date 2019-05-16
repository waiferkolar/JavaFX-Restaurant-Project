package coder.models;

public class User {
    private int id, role;
    private boolean enabled;
    private String username, password;

    public User(int id, int role, boolean enabled, String username, String password) {
        this.id = id;
        this.role = role;
        this.enabled = enabled;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", role=" + role +
                ", enabled=" + enabled +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
