import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Scanner;

public class Writingxml
{

       public static void writexml() throws Exception
        {
            DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
            DocumentBuilder b = f.newDocumentBuilder();
            Document docu = b.newDocument();
            Scanner s = new Scanner(System.in);

            System.out.println("enter number of root element to be insert in xml");
            int m = s.nextInt();

            System.out.println("enter number of child element to be insert in xml");
            int n = s.nextInt();

            Node node = null;
            for (int i = 0; i < m; i++) {
                System.out.println("enter " + (i + 1) + "st rootnode name");
                String root = s.next();

                System.out.println("enter childnode name of "+root);
                String subroot = s.next();

                Element rootele = docu.createElement(root);
                Element childele = docu.createElement(subroot);

                for (int j = 0; j < n; j++) {
                    System.out.println("enter " + (j + 1) +" " +subroot +" subchildnode name");
                    String str = s.next();
                    System.out.println("enter data to be inserted in " + str + "  subchild node");
                    String str1 = s.next();
                    Element subchild = docu.createElement(str);

                    Text t1 = docu.createTextNode(str1);

                    subchild.appendChild(t1);

                    childele.appendChild(subchild);

                }
                rootele.appendChild(childele);
                if (i == 0) {
                    docu.appendChild(rootele);
                    node = rootele;
                }
                if (i > 0) {
                    node.appendChild(rootele);
                }

            }


            TransformerFactory t = TransformerFactory.newInstance();
            Transformer trans = t.newTransformer();

            trans.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(docu);


            StreamResult console = new StreamResult(System.out);
            StreamResult file = new StreamResult(new File("D:\\untitled\\src\\xmlgenerated.xml"));


            trans.transform(source, console);
            trans.transform(source, file);


            System.out.println("xml file generated");
        }




}
