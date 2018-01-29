import java.io.FileInputStream;
import java.util.Properties;

public enum Configuration {
    instance;

    public ShortestPathAlgorithmType engineType = ShortestPathAlgorithmType.dijkstra;
    public String userDirectory = System.getProperty("user.dir");
    public String fileSeparator = System.getProperty("file.separator");
    public String pathToJar = userDirectory + fileSeparator + getShortestPathAlgorithmType() + fileSeparator + "jar" + fileSeparator + "ShortestPathAlgorithm.jar";

    public ShortestPathAlgorithmType getShortestPathAlgorithmType() {
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
    }
}