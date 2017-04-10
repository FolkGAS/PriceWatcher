package gas.home.pricewatcher.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import gas.home.pricewatcher.model.Goods;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileListConverter {

    public static List<Integer> getIndexFromFile(Goods goods) {
        String fileName = goods.getName() + "-" + goods.getDescription() + ".txt";
        return loadFromFile("index", fileName, Integer.class);
    }

    public static List<Goods.ElementEntry> getTagsFromFile(Goods goods) {
        String fileName = goods.getName() + "-" + goods.getDescription() + ".txt";
        return loadFromFile("tag", fileName, Goods.ElementEntry.class);
    }

    public static <T> void saveToFile(String parent, String fileName, List<T> list) {
        try {
            Path parentDir = Paths.get(parent);
            if (!Files.exists(parentDir)) {
                Files.createDirectory(parentDir);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Path path = Paths.get(parent + System.getProperty("file.separator") + "GSON-" + fileName);
            String gson = getGson(list);
            stringToFile(gson, path);
    }

    public static <T> List<T> loadFromFile(String parent, String fileName, Class clazz) {
        Path path = Paths.get(parent + System.getProperty("file.separator") + "GSON-" + fileName);
        String gson = stringFromFile(path);
        List<T> fromGson = getFromGson(gson, clazz);
        return fromGson.isEmpty() ? Collections.emptyList() : fromGson;
    }

    public static <T> String getGson(List<T> list) {
        try {
            ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "**************************************************************************";
    }

    public static <T> List<T> getFromGson(String gson, Class clazz) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            TypeFactory factory = mapper.getTypeFactory();
            return mapper.readValue(gson, factory.constructCollectionType(ArrayList.class, clazz));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    private static String stringFromFile(Path path) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            if (Files.exists(path) && Files.isRegularFile(path)) {
                Files.copy(path, baos);
            } else {
                return "**********************************************************************";
            }
            return baos.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "********************************************************************************";
    }

    private static boolean stringToFile(String s, Path path) {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(s.getBytes())) {
            return Files.copy(bais, path) > 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
