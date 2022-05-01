import java.io.File;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;


public class modifyingxml
{
    public static void modifyxml() throws Exception {


        DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
        DocumentBuilder b = f.newDocumentBuilder();


        File file = new File("D:\\untitled\\src\\xmlgenerated.xml");
        Document docu = b.parse(file);
        Scanner s = new Scanner(System.in);
        int word;
        do {
            System.out.println("enter 1:to update xml , enter 2:delete element in xml , enter 3: add element in xml");
            int num = s.nextInt();

            switch (num) {
                case (1):
                    updateElementValue(docu);
                    break;
                case (2):
                    deleteElement(docu);
                    break;
                case (3):
                    addElement(docu);
                    break;
                default:
                    System.out.println("enter valid number");
                    break;
            }
            System.out.println("do you want to again udpate the xml if yes enter 1 if no enter 0");
            word = s.nextInt();
        } while (word==1);

        saveXMLFile(docu);
    }

    private static void saveXMLFile(Document doc)
            throws TransformerFactoryConfigurationError, TransformerException {

        TransformerFactory t = TransformerFactory.newInstance();
        Transformer trans= t.newTransformer();

        trans.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);

        StreamResult result = new StreamResult(new File("D:\\untitled\\src\\xmlgenerated.xml"));

        trans.transform(source, result);

        System.out.println("XML file updated successfully");
    }

    private static void addElement(Document doc) {
        Scanner sc=new Scanner(System.in);
        System.out.println("enter that subchlid node name in which you want to add new element node");
        String subchild= sc.next();

        System.out.println("enter element node name which you want to add");
        String nodename=sc.next();

        System.out.println("enter data of node to be add in xml");
        String str=sc.next();

        NodeList list = doc.getElementsByTagName(subchild);
        Element emp ;

        for (int i = 0; i < list.getLength(); i++)
        {
            emp = (Element) list.item(i);
            Element element = doc.createElement(nodename);
            element.appendChild(doc.createTextNode(str));
            emp.appendChild(element);
        }
    }


    private static void deleteElement(Document doc)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("enter that subchlid node name in which you want to delete element ");
        String subchild= sc.next();

        System.out.println("element node name which you want to delete");
        String nodename=sc.next();

        NodeList list = doc.getElementsByTagName(subchild);

        Element ele ;

        for (int i = 0; i < list.getLength(); i++) {
            ele = (Element) list.item(i);
            Node node = ele.getElementsByTagName(nodename).item(0);
            ele.removeChild(node);
        }

    }

    private static void updateElementValue(Document doc) {
        Scanner sc=new Scanner(System.in);

        System.out.println("enter that subchlid node name in which you want to udate the element value ");
        String subchild= sc.next();

        System.out.println("element node name which data you want to update");
        String nodename=sc.next();

        System.out.println("enter new data which replace the previous one ");
        String str=sc.next();

        NodeList list = doc.getElementsByTagName(subchild);

        Element ele ;
        for (int i = 0; i < list.getLength(); i++) {
            ele = (Element) list.item(i);
            Node name = ele.getElementsByTagName(nodename).item(0).getFirstChild();
            name.setNodeValue(str);
        }
    }


}