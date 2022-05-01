import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Xmlparser
{
    public static void main(String[] args) throws Exception
    {
        Scanner s = new Scanner(System.in);
        int value;

      do{
          System.out.println("enter 1: to read xml ,enter 2:to create xml, enter 3:to modify xml");
          int choose = s.nextInt();

          switch (choose)
          {
              case 1:
                  readxml();
                  break;
              case 2:
                  writexml();
                  break;
              case 3:
                  modifyxml();
                  break;
              default:
                  System.out.println("enter valid number");
                  break;
          }
          System.out.println();
          System.out.println("do you want to continue if yes press 1 if no press 0");
          value=s.nextInt();

      }while(value==1);

}


//fuction to read an xml file
        public static void readxml() throws ParserConfigurationException, IOException, SAXException
        {
            Scanner s = new Scanner(System.in);
            System.out.println("for reading xml press 1 else press 0");
            int n = s.nextInt();

            for (int i = 0; i < n; i++)
            {
                DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
                DocumentBuilder b = f.newDocumentBuilder();


                File file = new File("D:\\untitled\\src\\parsr.xml");
                Document docu = b.parse(file);

                System.out.println("root node is  " + docu.getDocumentElement().getNodeName());
                System.out.println("============================");

                Node rootnode =docu.getDocumentElement();
                NodeList list=rootnode.getChildNodes();

                for (int j = 0; j < list.getLength(); j++)
                {
                    Node node = list.item(j);
                    if (node.getNodeType() == Node.ELEMENT_NODE)
                    {
                      NodeList list1 = node.getChildNodes();
                      System.out.println(node.getNodeName());
                      for (int k = 0; k < list1.getLength(); k++)
                      {
                        Node detail = list1.item(k);

                        if (detail.getNodeType() == Node.ELEMENT_NODE)
                        {
                            Element detailElement = (Element) detail;
                            System.out.println(detailElement.getTagName() + "  :  " + detailElement.getTextContent());
                        }
                      }
                    }

                }
            }
        }



       //function to create an xml file

        public static void writexml() throws ParserConfigurationException, TransformerException
        {
            DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
            DocumentBuilder b = f.newDocumentBuilder();
            Document docu = b.newDocument();
            Scanner s = new Scanner(System.in);

            System.out.println("enter number of root element to be insert in xml");
            int m = s.nextInt();

            System.out.println("enter number of child element to be insert in xml");
            int n = s.nextInt();

            Node node=null;
           for(int i=0;i<m;i++) {
               System.out.println("enter " + (i + 1) + " st rootnode name");
               String root = s.next();

               System.out.println("enter " + (i + 1) + " st childnode name");
               String subroot = s.next();

               Element rootele = docu.createElement(root);
               Element childele = docu.createElement(subroot);

               for (int j = 0; j < n; j++) {
                   System.out.println("enter " + (j + 1) + " subchild node name");
                   String str = s.next();
                   System.out.println("enter data of " + (j + 1) + " subchild node");
                   String str1 = s.next();
                   Element subchild = docu.createElement(str);

                   Text t1 = docu.createTextNode(str1);

                   subchild.appendChild(t1);

                   childele.appendChild(subchild);

               }

               rootele.appendChild(childele);
               if (i == 0)
               {
                   docu.appendChild(rootele);
                   node=rootele;
               }
               if(i>0)
               {
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






       //function to modify xml
    public static void modifyxml() throws ParserConfigurationException, IOException, SAXException, TransformerException
    {

            DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
            DocumentBuilder b = f.newDocumentBuilder();


            File file = new File("D:\\untitled\\src\\xmlgenerated.xml");
            Document docu = b.parse(file);
            Scanner s = new Scanner(System.in);
            boolean word;
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
                System.out.println("do you want to continue if yes enter true else false");
                word = s.nextBoolean();
            } while (word);

            saveXMLFile(docu);
        }


       //method in modify xml to save modified xml file

        private static void saveXMLFile(Document doc)
            throws TransformerFactoryConfigurationError, TransformerException
        {

        TransformerFactory t = TransformerFactory.newInstance();
        Transformer trans= t.newTransformer();

        trans.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);

        StreamResult result = new StreamResult(new File("D:\\untitled\\src\\xmlgenerated.xml"));

        trans.transform(source, result);

        System.out.println("XML file updated successfully");
       }



      // method in modifyxml to add element in xml file

       private static void addElement(Document doc)
       {

        Scanner sc=new Scanner(System.in);
        System.out.println("enter the subchlid node name in which you want to add new element node");
        String subchild= sc.next();

        System.out.println("element node name which you want to add");
        String nodename=sc.next();

        System.out.println("enter data of node to be added");
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


       //method in modifyxml to delete element in xml file

          private static void deleteElement(Document doc)
        {
            Scanner sc=new Scanner(System.in);
            System.out.println("enter the subchlid node name in which you want to delete element ");
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


       // method in modifyxml to udate any childnode and its value in xml file

        private static void updateElementValue(Document doc)
        {
        Scanner sc=new Scanner(System.in);

        System.out.println("enter that subchlid node name in which you want to udate the element value ");
        String subchild= sc.next();

        System.out.println("element node name which you want to update");
        String nodename=sc.next();

        System.out.println("enter new data which replace previous one");
        String str=sc.next();

        NodeList list = doc.getElementsByTagName(subchild);

        Element ele ;
        for (int i = 0; i < list.getLength(); i++)
        {
            ele = (Element) list.item(i);
            Node name = ele.getElementsByTagName(nodename).item(0).getFirstChild();
            name.setNodeValue(str);
        }

        }

}
