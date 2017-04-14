package gas.home.pricewatcher.util;

import gas.home.pricewatcher.model.Goods;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PageParser {
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0";
    private static final int TIMEOUT = 10 * 10000;

    private PageParser() {

    }

    public static void fillGoodsRoute(Goods goods) {
        try {
            Document doc = getDocument(goods.getUrl());
            Element cost = getElementByText(doc, goods.getCost());
            if (cost != null) {
                List<Integer> siblingIndexes = getSiblingIndexes(cost);
                goods.setCostElementIndexes(FormatConverter.getGson(siblingIndexes));
                List<GenericPair<String, String>> tagsClasses = getTagsClasses(cost);
                goods.setCostElementTagsAndClasses(FormatConverter.getGson(tagsClasses));
            } else
                System.out.println("\n*****************************************\n" + goods.getName() + " - " + goods.getDescription());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getCostByIndexChain(Goods goods) {
        try {
            Node node = getDocument(goods.getUrl());
            List<Integer> elementIndexes = FormatConverter.getFromGson(goods.getCostElementIndexes(), Integer.class);
            for (Integer elementIndex : elementIndexes) {
                if (node.childNodes().size() <= elementIndex) {
                    return "\n***************************************************************\n";
                }
                node = node.childNode(elementIndex);
            }
            return ((Element) node).text();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "\n***************************************************************\n";
    }

    public static String getCostByTagChain(Goods goods) {
        try {
            Document doc = getDocument(goods.getUrl());
            List<Element> elements = new ArrayList<>();
            elements.add(doc);
            List<GenericPair<String, String>> tags = FormatConverter.getFromGson(goods.getCostElementTagsAndClasses(), GenericPair.class);
            Element cost = recursiveCostByTagClass(doc, tags);
            return cost != null ? cost.text() : "\n*********************************************************************\n";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "\n**********************************************************\n";
    }

    private static Element recursiveCostByTagClass(Element element, List<GenericPair<String, String>> goods) {
        if (goods.isEmpty()) {
            return element;
        }
        String key = goods.get(0).getKey();
        String value = goods.get(0).getValue();
        List<Element> elements = element.getElementsByTag(key)
                .stream()
                .filter(el -> el.className().equals(value))
                .collect(Collectors.toList());
        if (elements.isEmpty()) {
            return null;
        }
        Element cost;
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

    private static List<GenericPair<String, String>> getTagsClasses(Element element) {
        List<GenericPair<String, String>> elementEntryList = element.parents().stream()
                .map(el -> new GenericPair<>(el.tagName(), el.className()))
                .collect(Collectors.toList());
        Collections.reverse(elementEntryList);
        elementEntryList.add(new GenericPair<>(element.tagName(), element.className()));
        return elementEntryList;
    }

    private static List<Integer> getSiblingIndexes(Node node) {
        List<Integer> siblingIndexes = new ArrayList<>();
        siblingIndexes.add(node.siblingIndex());
        Node parent = node.parentNode();
        while (parent != null) {
            siblingIndexes.add(parent.siblingIndex());
            parent = parent.parent();
        }
//        List<Integer> siblingIndexes = node.parents().stream().map(Element::elementSiblingIndex).collect(Collectors.toList());
//        List<Integer> siblingIndexes = node.parents().stream().map(Element::elementSiblingIndex).collect(Collectors.toList());
        siblingIndexes.remove(siblingIndexes.size() - 1);
        Collections.reverse(siblingIndexes);
//        siblingIndexes.add(node.elementSiblingIndex());
        return siblingIndexes;
    }
}

