package gas.home.pricewatcher.util;

import gas.home.pricewatcher.model.Goods;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PageParser {
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0";
    private static final int TIMEOUT = 10 * 10000;

    public static void fillGoodsRoute(Goods goods) {
        try {
            Document doc = getDocument(goods.getUrl());
            Element cost = getElementByText(doc, goods.getCost());
            if (cost != null) {
                List<Integer> siblingIndexes = getSiblingIndexes(cost);
                goods.setCostElementIndexes(siblingIndexes);
                List<Goods.ElementEntry> tagsClasses = getTagsClasses(cost);
                goods.setCostElementTagsAndClasses(tagsClasses);
                String fileName = goods.getName() + "-" + goods.getDescription() + ".txt";
                FileListConverter.saveToFile("index", fileName, siblingIndexes);
                FileListConverter.saveToFile("tag", fileName, tagsClasses);
            }
            else System.out.println("*****************************************" +goods.getName() + " - " + goods.getDescription());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getCostByIndexChain(Goods goods) {
        try {
            Document doc = getDocument(goods.getUrl());
            Element element = doc;
            List<Integer> elementIndexes = goods.getCostElementIndexes();
            for (Integer elementIndex : elementIndexes) {
                if (element.childNodeSize() < elementIndex.intValue()) {
                    break;
                }
                element = element.child(elementIndex);
            }
            return element.text();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "***************************************************************";
    }

    public static String getCostByTagChain(Goods goods) {
        try {
            Document doc = getDocument(goods.getUrl());
            List<Element> elements = new ArrayList<>();
            elements.add(doc);
            List<Goods.ElementEntry> tags = goods.getCostElementTagsAndClasses();
            Element cost = recursiveCostByTagClass(doc, tags);
            return cost != null ? cost.text() : "*********************************************************************";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "**********************************************************";
    }

    private static Element recursiveCostByTagClass(Element element, List<Goods.ElementEntry> goods) {
        if (goods.isEmpty()) {
            return element;
        }
        String tag = goods.get(0).getTag();
        String clasz = goods.get(0).getClasz();
        List<Element> elements = element.getElementsByTag(tag)
                .stream()
                .filter(el -> el.className().equals(clasz))
                .collect(Collectors.toList());
        if (elements.isEmpty()) {
            return null;
        }
        Element cost = null;
        goods.remove(0);
        for (Element el : elements) {
            cost = recursiveCostByTagClass(el, goods);
            if (cost != null) {
                return cost;
            }
        }
        return null;
    }

    private static Document getDocument(String url) throws IOException {
        return Jsoup.connect(url).userAgent(USER_AGENT).ignoreHttpErrors(true).timeout(TIMEOUT).get();      // .referrer(REFERRER)
    }

    private static Element getElementByText(Element element, String text) {
        Optional<Element> optional = element.getElementsContainingOwnText(text)
                .stream()
                .filter(el -> el.text().equals(text))
                .findFirst();
        return optional.orElse(null);
    }

    private static List<Goods.ElementEntry> getTagsClasses(Element element) {
        List<Goods.ElementEntry> elementEntryList = element.parents().stream()
                .map(el -> new Goods.ElementEntry(el.tagName(), el.className()))
                .collect(Collectors.toList());
        Collections.reverse(elementEntryList);
        elementEntryList.add(new Goods.ElementEntry(element.tagName(), element.className()));
        return elementEntryList;
    }

    private static List<Integer> getSiblingIndexes(Element element) {
        List<Integer> siblingIndexes = element.parents().stream().map(Element::elementSiblingIndex).collect(Collectors.toList());
        Collections.reverse(siblingIndexes);
        siblingIndexes.add(element.elementSiblingIndex());
        return siblingIndexes;
    }
}

