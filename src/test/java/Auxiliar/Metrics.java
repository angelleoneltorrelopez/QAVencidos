package Auxiliar;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Metrics{

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        String path = System.getProperty("user.dir")+"/test-output/testng-results.xml";
        File testNgResultXmlFile = new File(path);

        //Get Document Builder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        //Build Document
        Document document = null;
        try {
            document = builder.parse(testNgResultXmlFile);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Normalize the XML Structure;
        document.getDocumentElement().normalize();

        System.out.println("Total : " + document.getDocumentElement().getAttribute("total"));
        System.out.println("Passed : " + document.getDocumentElement().getAttribute("passed"));
        System.out.println("Failed : " + document.getDocumentElement().getAttribute("failed"));
        System.out.println("Skipped : " + document.getDocumentElement().getAttribute("skipped"));

        NodeList tMethods = document.getElementsByTagName("test-method");

        for (int temp = 0; temp < tMethods.getLength(); temp++){
            Node node = tMethods.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE){
                Element eElement = (Element) node;
                // Get parent element to capture suite name
                Element suiteElement = (Element) eElement.getParentNode();
                System.out.println("Suite: "    + suiteElement.getAttribute("name"));
                // Get test case name
                System.out.println("Name: "    + eElement.getAttribute("name"));
                // Get test case status
                System.out.println("Status: "    + eElement.getAttribute("status"));
                // Get test case duration
                System.out.println("Duration: "    + eElement.getAttribute("duration-ms"));
                // Get exception message if exist
                Node eNode = eElement.getElementsByTagName("exception").item(0);
                Element exceptionNode = (Element) eNode;
                if (exceptionNode != null) {
                    System.out.println("Error Message: "    + exceptionNode.getAttribute("class"));
                } else {
                    System.out.println("Error Message: ");
                }

            }
        }
    }
}