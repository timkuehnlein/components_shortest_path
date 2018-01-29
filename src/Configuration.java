import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

public enum Configuration {
    instance;

    public ShortestPathAlgorithmType engineType = ShortestPathAlgorithmType.dijkstra;
    public String userDirectory = System.getProperty("user.dir");
    public String fileSeparator = System.getProperty("file.separator");
    public String pathToJar(){
        return  userDirectory + fileSeparator + getShortestPathAlgorithmType() + fileSeparator + "jar" + fileSeparator + "ShortestPathAlgorithm.jar";
    }

    /*public ShortestPathAlgorithmType getShortestPathAlgorithmType() {
        try {
            Properties properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream(userDirectory + fileSeparator + "ShortestPathAlgorithmType.props");
            properties.load(fileInputStream);
            fileInputStream.close();
            if (properties.getProperty("ShortestPathAlgorithmType").equals("dijkstra"))
                return ShortestPathAlgorithmType.dijkstra;
            else if (properties.getProperty("ShortestPathAlgorithmType").equals("bellman_ford"))
                return ShortestPathAlgorithmType.bellman_ford;
            else
                return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }*/

    public Properties getProperty() {
        try {
            Properties properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream(userDirectory + fileSeparator + "correlation.props");
            properties.load(fileInputStream);
            fileInputStream.close();
            return properties;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public  void setProperty(String argS) {

        try {
            Properties props = new Properties();
            props.setProperty("correlationType", argS);
            File f = new File(userDirectory + fileSeparator + "correlation.props");
            OutputStream out = new FileOutputStream( f );
            props.store(out, "");
            out.close();
        }
        catch (Exception e ) {
            e.printStackTrace();
        }
    }

    public String getShortestPathAlgorithmType() {
        try {
            Properties properties = getProperty();
            if (properties.getProperty("ShortestPathAlgorithmType").equals("dijkstra"))
                //return ""+ CorrelationType.spearman;
                return "dijkstra";
            else if (properties.getProperty("ShortestPathAlgorithmType").equals("bellman_ford"))
                //return ""+ CorrelationType.pearson;
                return "bellman_ford";
            else
                return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}