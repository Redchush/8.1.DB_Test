package root.utils;

import java.util.ResourceBundle;
import java.util.Set;


public enum ResourceManager {
    DATABASE("root.properties.database"),
    QUERIES("root.properties.databaseFullQueries"),
    PARTS("root.properties.databaseQueriesParts");

    private ResourceBundle resourceBundle;

    private ResourceManager(String string){
        resourceBundle = ResourceBundle.getBundle(string);
    }

    public String getString(String key){
        return resourceBundle.getString(key);
    }

    public Set<String> keySet() {
        return resourceBundle.keySet();
    }
}
