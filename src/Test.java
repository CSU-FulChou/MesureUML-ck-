import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class Test {
	 public static void main(String[] args) {
	        //1.��ȡһ��SAXParserFactory��ʵ������
	        SAXParserFactory factory = SAXParserFactory.newInstance();
	        //2.ͨ��factory��newSAXParser()������ȡһ��SAXParser��Ķ���
	        try {

				SAXParser parser = factory.newSAXParser();
				 // ������ͼ���ࣺ SAXParserHandler
				 System.out.println("please input classs graph address:"); 

				Scanner input =new Scanner(System.in);
				String instr = input.nextLine(); 
				String instr2 = "test.xml";
	            SAXParserHandler handler2 = new SAXParserHandler();
	            parser.parse(instr2, handler2);
				// //����SAXParserHandler����
	            // Scanner scanner = new Scanner(System.in);  
	            // //E:\Ex1_2.xml
	            // System.out.println("plese input liu cheng tu address:"); 
	            // Scanner input1 =new Scanner(System.in);
	            // String instr1 = input1.nextLine(); 
	            // SAXUCD handler1=new SAXUCD();
	            // parser.parse(instr1, handler1);
				// handler1.showAll();
	            //E:\Test4.xml
	          


	            //handler2.Adjust();
	            handler2.showAll();
	        } catch (ParserConfigurationException e) {
	            e.printStackTrace();
	        } catch (SAXException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	 }
}
