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
	
	
int classIndex = 0;//����ȫ�ֱ�����������¼�ǵڼ�����
	int ActorNumSAX=0;
	int DependencyNumSAX=0;
	int UseCaseNumSAX=0;
	int AssocNumSAX=0;
	int UC1SAX=0;//������
	int UC2SAX=0;//�����ߺ�����֮�������
	double UC3SAX=0;//��������
	double UC4SAX=0;//k1k2
	double NODSAX=0;//
    UCD ucd = new UCD();
    String [] ActorId= new String[100];
    int [] AssocNum= new int[100];




    /**
     * ������ʶ������ʼ
     */
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        System.out.println("SAX������ʼ");

    }
    
    /**
     * ������ʶ��������
     */
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        System.out.println("SAX��������");
    }
    
    /**
     * ��������xml�ļ��Ŀ�ʼ��ǩ
     * ����xmlԪ��
     */
    @Override
    public void startElement(String uri, String localName, String qName,Attributes attributes) throws SAXException {
        //����DefaultHandler���startElement����
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
     * ��������xml�ļ��Ľ�����ǩ
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        //����DefaultHandler���endElement����
        super.endElement(uri, localName, qName);
        //�ж��Ƿ����һ�����Ѿ���������
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
     * ��ȡ�ı�
     * ��дcharaters()����ʱ��
     * String(byte[] bytes,int offset,int length)�Ĺ��췽����������Ĵ���
     * ȥ������ʱ����ո�
     */
    @Override
    public void characters(char[] ch, int start, int length)throws SAXException {
        /**
         * ch ����ڵ��е��������ݣ���ÿ������һ����ǩ����characters����ʱ������chʵ�ʶ�������XML�ĵ�������
         * ���ÿ��ȥ����characters����ʱ���Ƕ����Ի�ȡ��ͬ�Ľڵ����ԣ���ʱ�ͱ�����start����ʼ�ڵ㣩��length�����ȣ�
         */
        super.characters(ch, start, length);
        /*String */String value = new String(ch, start, length);//value��ȡ�����ı�����ʼ�ͽ�����ǩ֮����ı���
//        System.out.println(value);//���ʱ���������ո�����Ϊxml�ļ��пո��뻻���ַ�������Ϊһ���ı��ڵ�
       /* if(!value.trim().equals("")){//���valueȥ���ո���ǿ��ַ���
            System.out.println("�ڵ�ֵ�ǣ�" + value);
        }*/
    }
    
    /**
     * qName��ȡ���ǽڵ�������ǩ��
     * value��ȡ�����ı�����ʼ�ͽ�����ǩ֮����ı���
     * ˼����qName��value�ֱ������������У���ν������������еĲ������ϵ�һ��
     * ������Ҫ��������������ͬһ�������������ó�ȫ�ֱ��������Ը���ֵΪnull��
     *     ���԰�characters()�����е�value����һ��ȫ�ֱ���
     * 
     * Ȼ����endElement()�����ж�book���������ֵ���ǵ�Ҫ��Book��������Ϊȫ�ֱ�������������
     */
    
    
    public void showAll(){
    		    System.out.println("��������"+ucd.getActorNum());
        		System.out.println("��������"+ucd.getUseCaseNum());
        		System.out.println("������"+ ucd.getDependencyNum());
        		System.out.println("��ϵ��"+ucd.getAssocNum());
        		System.out.println("UC1 "+ucd.getUC1());
        		System.out.println("UC2 "+ucd.getUC2());
        		System.out.println("UC3 "+ucd.getUC3());
        		System.out.println("UC4 "+ucd.getUC4());
        		System.out.println("NOD "+ucd.getNOD());
    }
}

