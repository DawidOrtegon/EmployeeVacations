package ev.data.EmployeeVacations.Login;

import java.io.Serializable;

public class LoginBean implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;

    // For the Manager
    private boolean isManager;

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


    // For the manager ONLY.
    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }
}