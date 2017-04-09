package gas.home.pricewatcher.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class FileListConverter {

    public static <T> void saveToFile(String parent, String fileName, List<T> list) {
        try {
            Path parentDir = Paths.get(parent);
            if (!Files.exists(parentDir)) {
                Files.createDirectory(parentDir);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Path path = Paths.get(parent + System.getProperty("file.separator") + fileName);
        try (FileOutputStream fos = new FileOutputStream(path.toFile())) {
            saveSerialize(list, fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        path = Paths.get(parent + System.getProperty("file.separator") + "GSON-" + fileName);
        try (FileOutputStream fos = new FileOutputStream(path.toFile())) {
            saveGson(list, fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        path = Paths.get(parent + System.getProperty("file.separator") + "XML-" +  fileName);
        try (FileOutputStream fos = new FileOutputStream(path.toFile())) {
            saveXml(list, fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> List<T> loadFromFile(String parent, String fileName) {
        Path path = Paths.get(parent + System.getProperty("file.separator") + fileName);
        try (FileInputStream fis = new FileInputStream(path.toFile())) {
            return loadSerialize(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  Collections.emptyList();
    }

    private static <T> void saveSerialize(List<T> list, FileOutputStream fos) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(list);
            byte[] bytes = bos.toByteArray();
            fos.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static <T> List<T> loadSerialize(FileInputStream fis) {
        try (ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (List<T>)ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    private static <T> void saveGson(List<T> list, FileOutputStream fos) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String sGson = gson.toJson(list);
            fos.write(sGson.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static <T> List<T> loadGson(FileInputStream fis) {
        List<T> tList = Collections.emptyList();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(fis))){
            Gson gson = new Gson();
            return gson.fromJson(reader, tList.getClass());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    private static <T> void saveXml(List<T> list, FileOutputStream fos) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
            String xml = xmlMapper.writeValueAsString(list);
            fos.write(xml.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static <T> List<T> loadXml(FileInputStream fis) {
        List<T> emptyList = Collections.emptyList();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(fis))){
            XmlMapper xmlMapper = new XmlMapper();
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            return xmlMapper.readValue(sb.toString(), emptyList.getClass());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return emptyList;
    }
}
