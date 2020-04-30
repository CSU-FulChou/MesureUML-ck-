import java.util.ArrayList;

import javax.swing.JFrame;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;


public class SAXUCD extends DefaultHandler{
	
	
int classIndex = 0;//设置全局变量，用来记录是第几个类
	int ActorNumSAX=0;
	int DependencyNumSAX=0;
	int UseCaseNumSAX=0;
	int AssocNumSAX=0;
	int UC1SAX=0;//用例数
	int UC2SAX=0;//参与者和用例之间的总数
	double UC3SAX=0;//依赖次数
	double UC4SAX=0;//k1k2
	double NODSAX=0;//
    UCD ucd = new UCD();
    String [] ActorId= new String[100];
    int [] AssocNum= new int[100];




    /**
     * 用来标识解析开始
     */
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        System.out.println("SAX解析开始");

    }
    
    /**
     * 用来标识解析结束
     */
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        System.out.println("SAX解析结束");
    }
    
    /**
     * 用来遍历xml文件的开始标签
     * 解析xml元素
     */
    @Override
    public void startElement(String uri, String localName, String qName,Attributes attributes) throws SAXException {
        //调用DefaultHandler类的startElement方法
        super.startElement(uri, localName, qName, attributes);
        int i=0;
        if (qName.equals("packagedElement")) {
            if (attributes.getValue(0).equals("uml:Actor")) {
            	ActorNumSAX++;
            	ucd.setActorNum(ActorNumSAX);
            	
            
            }else if(attributes.getValue(0).equals("uml:Dependency")) {
            	DependencyNumSAX++;
        		ucd.setDependencyNum(DependencyNumSAX);
            }else if(attributes.getValue(0).equals("uml:UseCase")) {
            	UseCaseNumSAX++;
            	ucd.setUseCaseNum(UseCaseNumSAX);
            }else if(attributes.getValue(0).equals("uml:Association")) {
            	AssocNumSAX++;
            	ucd.setAssocNum(AssocNumSAX);
            }
        }
    }
            
   
    
    /**
     * 用来遍历xml文件的结束标签
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        //调用DefaultHandler类的endElement方法
        super.endElement(uri, localName, qName);
        //判断是否针对一个类已经遍历结束
        if (qName.equals("uml:Model")) {
        	UC1SAX=ActorNumSAX;
        	ucd.setUC1(UC1SAX);
        	UC2SAX=AssocNumSAX;
        	ucd.setUC2(UC2SAX);
        	UC3SAX=AssocNumSAX;
        	ucd.setUC3(UC3SAX);
        	
        	double k1=0.1;
        	double k2=0.1;
        	UC4SAX=k1*UC1SAX*UC1SAX+UC3SAX+k2*(AssocNumSAX-AssocNumSAX);
        	ucd.setUC4(UC4SAX);
        	
        	double ad=UseCaseNumSAX*(UseCaseNumSAX-1)/2;
        	NODSAX=(ad-DependencyNumSAX-AssocNumSAX)/ad;
        	ucd.setNOD(NODSAX);
        
        	
           
            }
        }
        
    
    /**
     * 获取文本
     * 重写charaters()方法时，
     * String(byte[] bytes,int offset,int length)的构造方法进行数组的传递
     * 去除解析时多余空格
     */
    @Override
    public void characters(char[] ch, int start, int length)throws SAXException {
        /**
         * ch 代表节点中的所有内容，即每次遇到一个标签调用characters方法时，数组ch实际都是整个XML文档的内容
         * 如何每次去调用characters方法时我们都可以获取不同的节点属性？这时就必须结合start（开始节点）和length（长度）
         */
        super.characters(ch, start, length);
        /*String */String value = new String(ch, start, length);//value获取的是文本（开始和结束标签之间的文本）
//        System.out.println(value);//输出时会多出两个空格，是因为xml文件中空格与换行字符被看成为一个文本节点
       /* if(!value.trim().equals("")){//如果value去掉空格后不是空字符串
            System.out.println("节点值是：" + value);
        }*/
    }
    
    /**
     * qName获取的是节点名（标签）
     * value获取的是文本（开始和结束标签之间的文本）
     * 思考：qName和value分别在两个方法中，如何将这两个方法中的参数整合到一起？
     * 分析：要在两个方法中用同一个变量，就设置成全局变量，可以赋初值为null。
     *     可以把characters()方法中的value作成一个全局变量
     * 
     * 然后在endElement()方法中对book对象进行塞值。记得要把Book对象设置为全局变量，变量共享
     */
    
    
    public void showAll(){
    		    System.out.println("参与者数"+ucd.getActorNum());
        		System.out.println("用例者数"+ucd.getUseCaseNum());
        		System.out.println("依赖数"+ ucd.getDependencyNum());
        		System.out.println("联系数"+ucd.getAssocNum());
        		System.out.println("UC1 "+ucd.getUC1());
        		System.out.println("UC2 "+ucd.getUC2());
        		System.out.println("UC3 "+ucd.getUC3());
        		System.out.println("UC4 "+ucd.getUC4());
        		System.out.println("NOD "+ucd.getNOD());
    }
}

