package Client;


public class User_Config {
    private String username;
    private String password;
    private String projectUrl;
    private String projectKey;

    public User_Config(String username, String password, String projectUrl, String projectKey){
        this.username = username;
        this.password = password;
        this.projectUrl = projectUrl;
        this.projectKey = projectKey;
    }
    public User_Config(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getProjectKey() {
        return projectKey;
    }

    public String getProjectUrl() {
        return projectUrl;
    }

}
