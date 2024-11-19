package config;

public class DbConfig {

    public static  DbInfo getDbConfig(){
        DbInfo db=  new DbInfo();
        db.setUrl("jdbc:mysql://51.254.114.114:3307/farmacia");
        db.setPassword("password");
        db.setUser("root");
        return db;
    }
}
