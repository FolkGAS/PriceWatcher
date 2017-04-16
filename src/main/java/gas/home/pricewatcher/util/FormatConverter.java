package gas.home.pricewatcher.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatConverter {

    private FormatConverter() {
    }

    static <T> String getGson(List<T> list) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "\n**************************************************************************\n";
    }

    static <T> List<T> getFromGson(String gson, Class clazz) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            TypeFactory factory = mapper.getTypeFactory();
            return mapper.readValue(gson, factory.constructCollectionType(ArrayList.class, clazz));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    public static String getDigital(String cost) {
        if (cost == null || "".equals(cost.trim())) {
            return "";
        }
        Pattern pat= Pattern.compile("[0-9]+(.[0-9]+)?");
        Matcher matcher=pat.matcher(cost.replace(" ", ""));
        if (matcher.find()) {
            return matcher.group();
        }
        return "";
    }

//    public static String stringFromFile(Path path) {
//        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
//            if (Files.exists(path) && Files.isRegularFile(path)) {
//                Files.copy(path, baos);
//            } else {
//                return "\n**********************************************************************\n";
//            }
//            return baos.toString();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return "\n********************************************************************************\n";
//    }
//
//    public static boolean stringToFile(String s, Path path) {
//        try (ByteArrayInputStream bais = new ByteArrayInputStream(s.getBytes())) {
//            if (Files.exists(path)) {
//                Files.delete(path);
//            }
//            return Files.copy(bais, path) > 0;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    public static List<Integer> getIndexFromFile(Goods goods) {
//        String fileName = goods.getName() + "-" + goods.getDescription() + ".txt";
//        return loadFromFile("index", fileName, Integer.class);
//    }
//
//    public static List<GenericPair<String, String>> getTagsFromFile(Goods goods) {
//        String fileName = goods.getName() + "-" + goods.getDescription() + ".txt";
//        return loadFromFile("tag", fileName, GenericPair.class);
//    }
//
//    private static <T> void saveToFile(String parent, String fileName, List<T> list) {
//        try {
//            Path parentDir = Paths.get(parent);
//            if (!Files.exists(parentDir)) {
//                Files.createDirectory(parentDir);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Path path = Paths.get(parent + System.getProperty("file.separator") + "GSON-" + fileName);
//        String gson = getGson(list);
//        stringToFile(gson, path);
//    }
//
//    private static <T> List<T> loadFromFile(String parent, String fileName, Class clazz) {
//        Path path = Paths.get(parent + System.getProperty("file.separator") + "GSON-" + fileName);
//        String gson = stringFromFile(path);
//        List<T> fromGson = getFromGson(gson, clazz);
//        return fromGson.isEmpty() ? Collections.emptyList() : fromGson;
//    }
}
