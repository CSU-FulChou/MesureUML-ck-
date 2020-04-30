
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXParserHandler extends DefaultHandler{
    int classIndex = 0;//设置全局变量，用来记录是第几个类
    boolean isclass=false;
    boolean isattributes=false;
    boolean isparameter=false;
    boolean isoperation=false;


    boolean oino=false;
    int generalizationLV=0;
    int associationLV=0;
    
    String fclassid; // 父类id
    int Visibility=0;
    boolean isstatic=false;

    String value = null;
    String methodName = null;
    FClass classes = null;
    int NMI =0;
    private ArrayList<FClass> classList = new ArrayList<FClass>();//保存classes对象

    public ArrayList<FClass> getBookList() {
        return classList;
    }

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
        if (qName.equals("o:Class")) {// 找到一个类：
            if(attributes.getQName(0)=="Id"){
            classes = new FClass();
            classes.setId(attributes.getValue(0));
            isclass=true;
            classIndex++;// 类的数目加一；
            
            System.out.println("======================开始遍历第"+(classIndex)+"个类的内容=================");
        //    /* //❤已知节点的属性名时：比如已知id属性，根据属性名称获取属性值
        //     String value = attributes.getValue("id");
        //     System.out.print("book的属性值是："+value);*/

        //   //❤未知节点的属性名时，获取属性名和属性值
        //     int num=attributes.getLength();// 这个attivutes 是标签里面的属性；

        //     for(int i=0;i<num;i++){
            	
        //         System.out.print("该类的第"+(i+1)+"个属性名是："+attributes.getQName(i));
        //         System.out.println("---属性值是："+attributes.getValue(i));

        //         /*if (attributes.getQName(i).equals("Id")) {//往class对象中塞值
        //             classes.setId(attributes.getValue(i));
        //         }*/
        //      }

            }
            else if(generalizationLV==1&&attributes.getQName(0)=="Ref"){
            	
            	fclassid=attributes.getValue(0);// 父类id；
                generalizationLV=2;
                for(FClass fClass:classList){
                    if(fClass.getId().equals(fclassid)){
                        fClass.setNOC(fClass.getNOC()+1);
                    }
                }
            }
            else if(generalizationLV==2&&attributes.getQName(0)=="Ref"){
            	for(FClass fclass:classList){
            		if(fclass.getId().equals(attributes.getValue(0))){
            			fclass.setFid(fclassid);//set father class id
            			
            			fclass.setIssubclass(true);
            			/*System.out.println(attributes.getValue(0)+"extends"+fclassid);*/
            			fclassid=null;
            		}
            	}
            	generalizationLV=0;
            }
            else if(associationLV==1&&attributes.getQName(0)=="Ref"){
                associationLV=2;
                String assoClassFstId = attributes.getValue(0);
                for(FClass fclass:classList){
                    if(fclass.getId().equals(assoClassFstId)){
                        fclass.setCBO(fclass.getCBO()+1);
                    }
                }
            }
            else if(associationLV==2&&attributes.getQName(0)=="Ref"){
            	for(FClass fclass:classList){
            		if(fclass.getId().equals(attributes.getValue(0))){
                        fclass.setCBO(fclass.getCBO()+1);
            			fclass.setIvariables(fclass.getIvariables()+1);
            		}
            	}
            }
        }

        // 碰到了一个参数的标记：
        else if(qName.equals("o:Parameter")&&attributes.getQName(0)=="Id"){
        	// System.out.println(attributes.getValue(0)+"111111");
        	isparameter=true;
        }
        
    
        //
        else if(qName.equals("o:Generalization")&&attributes.getQName(0)=="Id"){
        	generalizationLV=1;
        }
        else if(qName.equals("o:Association")&&attributes.getQName(0)=="Id"){
            associationLV=1;
        }
        else if(qName.equals("o:Attribute")){
        	isattributes=true;
        }
        else if(qName.equals("o:Operation")){
        	if(isoperation)//也就是上个Operation 标签没有结束；
        		oino=true;
        	isoperation=true;
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
        if (qName.equals("o:Class")&&isclass&&!isparameter&&!isattributes) {
        	System.out.println("id:"+classes.getId());
        	System.out.println("公共实例方法："+classes.getPimethodsNum());
        	System.out.println("实例方法："+classes.getImethodsNum());
        	System.out.println("类方法："+classes.getCmethodsNum());
        	System.out.println("实例变量："+classes.getIvariables());
            System.out.println("类变量"+classes.getCvariables());
            System.out.println("WMC （加权方法数）是"+classes.getWMC());
            classList.add(classes);//在清空classrs对象之前先保存
            classes = null;//把classes清空，方便解析下一个classes节点
            isclass=false;
            System.out.println("======================结束遍历第"+classIndex+"个类的内容=================");
        }
        
        else if(qName.equals("o:Attribute")&&isclass){
            isattributes=false;
          
        	if(!isstatic){
            classes.setIvariables(classes.getIvariables()+1);
            }
        	else if(isstatic)
        	classes.setCvariables(classes.getCvariables()+1);
        }
        else if(qName.equals("o:Parameter")&&isclass){
            classes.setTotalParameter(classes.getTotalParameter()+1);
        	isparameter=false;
        }
        else if(qName.equals("a:Parameter.DataType")){

            if(classes.variableListOfMethod.containsKey(methodName)){
                classes.variableListOfMethod.get(methodName).add(value);
            }else{
                List list = new ArrayList<String>(); 
                list.add(value); 
                System.out.println("methodName:"+methodName);
                System.out.println("parameter.DataType:"+value);
                classes.variableListOfMethod.put(methodName,list) ;  
            }

        }
        else if(qName.equals("o:Operation")&&isclass){
            //给是否是 操作归零：
            isoperation=false;
            classes.setWMC(classes.getWMC()+1); //关闭了 Operation 标签；
           
        	if(!oino){  //不是operation标签中包含operation标签
        	if(Visibility==1){//可见性为private
        		classes.setPrivatemn(classes.getPrivatemn()+1);
        		    if(!isstatic)
                	classes.setImethodsNum(classes.getImethodsNum()+1);
                	else if(isstatic)
                	classes.setCmethodsNum(classes.getCmethodsNum()+1);
        	}
        	else{
        	if(Visibility==0&&!isstatic){//public且非static
        	classes.setPimethodsNum(classes.getPimethodsNum()+1);
        	classes.setImethodsNum(classes.getImethodsNum()+1);
        	}
        	else if(!isstatic)
        	classes.setImethodsNum(classes.getImethodsNum()+1);
        	else if(isstatic)
        	classes.setCmethodsNum(classes.getCmethodsNum()+1);
        	}
        	Visibility=0;
        	isstatic=false;
         }
        oino=false;	
            
        }
        else if(qName.equals("a:Operation.Visibility")){
        	if(value.equals("-"))
        	Visibility=1;
        	else Visibility=2;
        }
        else if(qName.equals("a:Static")){
        	isstatic=true;
        }
        else if(qName.equals("a:Name")&&isclass&&!isattributes&&!isoperation){
        	classes.setName(value);
            System.out.println("类名为"+classes.getName());
            
        }
        else if(qName.equals("a:Name")&&isclass&&!isattributes&&isoperation&&!isparameter){
            // 处理一下函数名
            methodName = value;
        }
        else if(qName.equals("a:Stereotype")&&isoperation){
        	if(value.equals("Override"))
        		classes.setNMO(classes.getNMO()+1);
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
        value = new String(ch, start, length);
        //value获取的是文本（开始和结束标签之间的文本）
        // System.out.println(value);//输出时会多出两个空格，是因为xml文件中空格与换行字符被看成为一个文本节点
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
    public void Adjust(){
    	for(FClass sclass:classList){
    		
    		if((sclass.getCmethodsNum()+sclass.getImethodsNum())!=0){
    		
    		sclass.setAPPM(((double)sclass.getTotalParameter()/((double)sclass.getCmethodsNum()+(double)sclass.getImethodsNum())));}
    		if(sclass.isIssubclass()){
    		sclass.setNMA(sclass.getCmethodsNum()+sclass.getImethodsNum());
            computeNMI(sclass.getId());
    		sclass.setNMI(NMI);
    		NMI=0;
            }
            
    		/*if(sclass.isIssubclass()){
    			sclass.setNMA(sclass.getCmethodsNum()+sclass.getImethodsNum());
    			for(FClass fclass:classList){
    				if(sclass.getFid().equals(fclass.getId())){
    					sclass.setNMI(fclass.getCmethodsNum()+fclass.getImethodsNum()-fclass.getPrivatemn());
    				}
    			}
    		}*/
    	}
    }
   
    public void computeNMI(String id){
    	for(FClass sclass:classList){
    		if(sclass.getId()==id&&sclass.isIssubclass()){		
    			for(FClass fclass:classList){
    				if(sclass.getFid().equals(fclass.getId())){
    					NMI=NMI+fclass.getCmethodsNum()+fclass.getImethodsNum()-fclass.getPrivatemn()-sclass.getNMO();
    					if(fclass.isIssubclass())
    					computeNMI(fclass.getId());
    					
    				}
    			}
    			
    		  }
    		}    		
    }
  
	public int computeDIT(FClass classesx){
        int DIT = 0;
        while(classesx.isIssubclass()){
            DIT++;
        for(FClass fclass:classList){
            if(fclass.getId().equals(classesx.getFid())){
                classesx = fclass;
                }
            }
        }
        return DIT;
	}
    
    public void showAll(){
    	System.out.println("解析内容为:");
    	for(FClass fclass:classList){
    		System.out.println("类名为"+fclass.getName());
    		//System.out.println("方法参数数为"+fclass.getTotalParameter());
    		//System.out.println("私有方法数为"+fclass.getPrivatemn());
    		//System.out.println("类方法数为"+fclass.getCmethodsNum());
    		//System.out.println("实例方法数为"+fclass.getImethodsNum());
    		System.out.println("父类id为"+fclass.getFid());
    		System.out.println("方法重写个数为"+fclass.getNMO());
    		//System.out.println("NMI为"+fclass.getNMI());
    		//System.out.println("NMA为"+fclass.getNMA());
            //System.out.println("APPM为"+fclass.getAPPM());
            System.out.println("类的WMC为 "+fclass.getWMC());
            System.out.println("类的RFC是 "+(fclass.getWMC()+fclass.getCBO()));
            System.out.println("这个类的DIT为"+computeDIT(fclass));
            System.out.println("NOC(number of subClass is "+fclass.getNOC());
            System.out.println("CBO is "+ fclass.getCBO());
            System.out.println("LCOM is "+fclass.getLCOM());
    	}
    }
}
