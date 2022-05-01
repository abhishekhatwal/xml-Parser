import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class readingxml
{

       public static void readxml() throws ParserConfigurationException, SAXException, IOException
        {
            Scanner s = new Scanner(System.in);
            System.out.println("for reading xml press 1 else press 0");
            int n = s.nextInt();

            for (int i = 0; i < n; i++) {
                DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
                DocumentBuilder b = f.newDocumentBuilder();


                File file = new File("D:\\untitled\\src\\parsr.xml");
                Document docu = b.parse(file);

                System.out.println("root node is  " + docu.getDocumentElement().getNodeName());

                System.out.println("============================");

                Node rootnode = docu.getDocumentElement();
                NodeList list = rootnode.getChildNodes();

                for (int j = 0; j < list.getLength(); j++) {

                    Node node = list.item(j);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        NodeList list1 = node.getChildNodes();
                        System.out.println(node.getNodeName());

                        for (int k = 0; k < list1.getLength(); k++) {
                            Node detail = list1.item(k);
                            if (detail.getNodeType() == Node.ELEMENT_NODE) {
                                Element detailElement = (Element) detail;
                                System.out.println(detailElement.getTagName() + "  :  " + detailElement.getTextContent());
                            }
                        }
                    }

                }
            }
        }

}
