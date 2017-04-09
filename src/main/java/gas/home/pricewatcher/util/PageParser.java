package gas.home.pricewatcher.util;

import gas.home.pricewatcher.model.Goods;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PageParser {
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0";
    private static final int TIMEOUT = 10 * 10000;

    public static class ElementEntry implements Serializable {
        private String tag;
        private String classs;


        public ElementEntry(String tagName, String className) {
            this.tag = tagName;
            this.classs = className;
        }

        @Override
        public String toString() {
            return "ElementEntry{" +
                    "tag='" + tag + '\'' +
                    ", classs='" + classs + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ElementEntry that = (ElementEntry) o;

            return (tag != null ? tag.equals(that.tag) : that.tag == null) && (classs != null ? classs.equals(that.classs) : that.classs == null);
        }

        @Override
        public int hashCode() {
            int result = tag != null ? tag.hashCode() : 0;
            result = 31 * result + (classs != null ? classs.hashCode() : 0);
            return result;
        }
    }


    public static Element getElementFromChain(Document doc, Goods goods) {

        Element element = doc;
        List<Integer> elementIndexes = goods.getCostElementIndexes();
        for (Integer elementIndex : elementIndexes) {
            element = element.child(elementIndex);
        }
        return element != doc ? element : null;
    }

    public static List<Integer> getElementsChainIndexes(Document doc, String text) {
        Optional<Element> optional = getElementEqualText(doc, text);
        return optional.map(PageParser::getSiblingIndexes).orElse(Collections.emptyList());
    }

    public static List<ElementEntry> getElementsChainTagsClasses(Document doc, String text) {
        Optional<Element> optional = getElementEqualText(doc, text);
        List<ElementEntry> elementEntryList = optional.isPresent() ? optional.get().parents().stream()
                .map(element -> new ElementEntry(element.tagName(), element.className()))
                .collect(Collectors.toList()) : Collections.emptyList();
        Collections.reverse(elementEntryList);
        if (optional.isPresent() && elementEntryList.size() > 0) {
            Element element = optional.get();
            elementEntryList.add(new ElementEntry(element.tagName(), element.className()));
        }
        return elementEntryList;
    }

    private static List<Integer> getSiblingIndexes(Element element) {
        List<Integer> siblingIndexes = element.parents().stream().map(Element::elementSiblingIndex).collect(Collectors.toList());
        Collections.reverse(siblingIndexes);
        siblingIndexes.add(element.elementSiblingIndex());
        return siblingIndexes;
    }

    private static Document getDocument(String url) throws IOException {
//        return Jsoup.connect(url).userAgent(USER_AGENT).referrer(REFERRER).get();
        return Jsoup.connect(url).userAgent(USER_AGENT).ignoreHttpErrors(true).timeout(TIMEOUT).get();
    }

    private static Optional<Element> getElementEqualText(Document doc, String text) {
        return doc.getElementsContainingOwnText(text)
                .stream()
                .filter(element -> element.text().equals(text))
                .findFirst();
    }

    private static Optional<Element> getElementByClass(Document doc, String htmlClass) {
        return doc.getElementsByClass(htmlClass).stream().findFirst();
    }


    public static void temp(Goods goods) {

        String url = goods.getUrl();
        String cost = goods.getCost();
        System.out.println(goods.getName() + " " + goods.getDescription());
        try {
            Document doc = getDocument(url);
            Optional<Element> oCost = getElementEqualText(doc, cost);

            List<Integer> costIndexes = getElementsChainIndexes(doc, cost);
            goods.setCostElementIndexes(costIndexes);

            Element costElement = getElementFromChain(doc, goods);

            System.out.println(costElement == null ? "NOT FOUND" : costElement.text());

            List<ElementEntry> elementsChainTagsClasses = getElementsChainTagsClasses(doc, cost);

            String fileName = goods.getName() + "-" + goods.getDescription() + ".txt";

            FileListConverter.saveToFile("tag", fileName , elementsChainTagsClasses);

            FileListConverter.saveToFile("index", fileName, costIndexes);

            FileListConverter.loadFromFile("tag", fileName).forEach(System.out::println);

            FileListConverter.loadFromFile("index", fileName).forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


//        List<Element> nameElementsChain = getElementsChain(doc, oName.get());
//        List<Element> costElementsChain = getElementsChain(doc, oCost.get());
//
//        Element namedElement = nameElementsChain.get(nameElementsChain.size() - 1);
//        for (int i = nameElementsChain.size() - 2; i >= 0; i--) {
//            Element element = nameElementsChain.get(i);
//            System.out.println("Element № " + i);
//            System.out.println("siblingIndex: " + element.siblingIndex());
//            if (StringUtils.isNotEmpty(element.tagName())) {
//                System.out.println("tagName: " + element.tagName());
//            }
//            if (StringUtils.isNotEmpty(element.className())) {
//                System.out.println("className: " + element.className());
//            }
//            if (StringUtils.isNotEmpty(element.nodeName())) {
//                System.out.println("ownText: " + element.ownText());
//            }
//            if (i != 0) {
//                System.out.println();
//            }
//        }
//            String className = oName.get().className();
//            String classCost = oCost.get().className();
//            System.out.println("\nClasses:");
//            System.out.println(className + "\n" + classCost);
//
//            Optional<Element> oElementName = getElementByClass(doc, className);
//            Optional<Element> oEementCost = getElementByClass(doc, classCost);
//            System.out.println("\nElements by classes:");
//            System.out.println(oElementName.get() + "\n" + oEementCost.get());
//
//            String getName = oElementName.get().text();
//            String getCost = oEementCost.get().text();
//            System.out.println("\nGet from Elements:");
//            System.out.println(getName + "\n" + getCost);
//
//    private static List<Element> getElementsChain(Document doc, Element element) {
//        List<Element> elementsChain = new ArrayList<>();
//        elementsChain.add(element);
//        elementsChain.addAll(element.parents());
//        return elementsChain;
//    }

