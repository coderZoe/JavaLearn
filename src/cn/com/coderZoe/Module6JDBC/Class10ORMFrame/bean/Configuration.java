package cn.com.coderZoe.Module6JDBC.Class10ORMFrame.bean;

/**
 * @author yhs
 * @date 2020/5/5 11:52
 * @description 封装配置信息
 */
public class Configuration {
    private String driver;
    private String url;
    private String username;
    private String password;
    private String database;
    private String srcPath;
    private String poPackage;

    public Configuration() {
    }

    public Configuration(String driver, String url, String username, String password, String database, String srcPath, String poPackage) {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
        this.database = database;
        this.srcPath = srcPath;
        this.poPackage = poPackage;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getSrcPath() {
        return srcPath;
    }

    public void setSrcPath(String srcPath) {
        this.srcPath = srcPath;
    }

    public String getPoPackage() {
        return poPackage;
    }

    public void setPoPackage(String poPackage) {
        this.poPackage = poPackage;
    }
}
