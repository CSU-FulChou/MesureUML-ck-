import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FClass {
	 private String id;
     private String name;
    private int pimethodsNum=0;// public instance methods
    private int privatemn=0;// prite methods num 
	private int imethodsNum=0;// instance methods num 
    private int prmethodsNum=0;// private methods num 
    private int ivariables=0;// instance variables num 
    private int cmethodsNum=0;// class methods num 
	private int cvariables=0;// class variables 
	private int WMC = 0; // weighted Methods per Class
	//private ArrayList<String> variablesName = new ArrayList<>();
	public Map<String,List>  variableListOfMethod = new HashMap<>();
	
    private int NMO=0;//�1�7�1�7�Մ1�7�1�7�1�7�1�7�1�7�1�7
    private int NMI=0;//�1�7�0�7�ل1�7�1�7�1�7�1�7�1�7
    private int NMA=0;//�1�7�1�7�1�7��1�7�1�7�1�7�1�7
    private double APPM=0;//�0�9�1�7�1�7�1�7�1�7�1�7�1�7�1�7�1�7
	private int totalParameter=0;//�1�7�1�7�1�7�1�7�1�7�1�7�1�7�1�7
	private int NOC = 0; // number of subClass;
	private int CBO = 0; // Coupling Between Objects;
	private int LCOM = 0;
    public int getTotalParameter() {
		return totalParameter;
	}
	public void setTotalParameter(int totalParameter) {
		this.totalParameter = totalParameter;
	}
	private boolean issubclass=false;// have subclass
	private String fid; // father id

	public int getLCOM(){
		// int i=0; //看有多少个key
		for (String key1 : variableListOfMethod.keySet()) {

			for(String key2:variableListOfMethod.keySet()){
				if(!key1.equals(key2)){
					if(haveSameString(variableListOfMethod.get(key1), variableListOfMethod.get(key2)));
					this.LCOM++;
				}
			}
			// System.out.println("Value = " + value);
			//  i++;
		}
		LCOM/=2;
	
		// System.out.println(i);

		return this.LCOM;
	}

	private boolean haveSameString(List<String> a, List<String>b){

		for(String str:b){
			if(a.contains(str)){
				return true;
			}
			
		}
		return false;
	}
	
    public int getPrivatemn() {
		return privatemn;
	}
	public void setPrivatemn(int privatemn) {
		this.privatemn = privatemn;
	}

	public int getWMC() {
		return WMC;
	}
	public void setWMC(int WMC) {
		this.WMC = WMC;
	}

    public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public boolean isIssubclass() {
		return issubclass;
	}
	public void setIssubclass(boolean issubclass) {
		this.issubclass = issubclass;
	}
	public String getSuperclass() {
		return superclass;
	}
	public void setSuperclass(String superclass) {
		this.superclass = superclass;
	}
	private String superclass=null;
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getNMO() {
		return NMO;
	}
	public void setNMO(int nMO) {
		NMO = nMO;
	}
	public int getNMI() {
		return NMI;
	}
	public void setNMI(int nMI) {
		NMI = nMI;
	}
	public int getNMA() {
		return NMA;
	}
	public void setNMA(int nMA) {
		NMA = nMA;
	}
	public double getAPPM() {
		return APPM;
	}
	public void setAPPM(double aPPM) {
		APPM = aPPM;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPimethodsNum() {
		return pimethodsNum;
	}
	public void setPimethodsNum(int pimethodsNum) {
		this.pimethodsNum = pimethodsNum;
	}
	public int getImethodsNum() {
		return imethodsNum;
	}
	public void setImethodsNum(int imethodsNum) {
		this.imethodsNum = imethodsNum;
	}
	public int getPrmethodsNum() {
		return prmethodsNum;
	}
	public void setPrmethodsNum(int prmethodsNum) {
		this.prmethodsNum = prmethodsNum;
	}
	public int getIvariables() {
		return ivariables;
	}
	public void setIvariables(int ivariables) {
		this.ivariables = ivariables;
	}
	public int getCmethodsNum() {
		return cmethodsNum;
	}
	public void setCmethodsNum(int cmethodsNum) {
		this.cmethodsNum = cmethodsNum;
	}
	public int getCvariablesNum() {
		return cvariablesNum;
	}
	public void setCvariablesNum(int cvariablesNum) {
		this.cvariablesNum = cvariablesNum;
	}
	public int getCvariables() {
		return cvariables;
	}
	public void setCvariables(int cvariables) {
		this.cvariables = cvariables;
	}
	
	public int getNOC() {
		return NOC;
	}
	public void setNOC(int NOC) {
		this.NOC = NOC;
	}
	public int getCBO(){
		return CBO;
	}
	public void setCBO(int CBO){
		this.CBO = CBO;
	}
	private int cvariablesNum;
    
}
