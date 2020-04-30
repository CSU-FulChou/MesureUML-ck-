
public class UCD {
	private String id;
    private String name;
    private int ActorNum=0;//参与者数
	private int UseCaseNum=0;//用例者数
    private int DependencyNum=0;//依赖数
    private int AssocNum=0;//联系数
    private int UC1=0;//用例数
    private int UC2=0;//参与者和用例之间的总数
    private double UC3=0;//依赖次数
    private double UC4=0;//k1k2
    private double NOD=0;//
    
    
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getActorNum() {
		return ActorNum;
	}
	public void setActorNum(int actorNum) {
		ActorNum = actorNum;
	}
	public int getUseCaseNum() {
		return UseCaseNum;
	}
	public void setUseCaseNum(int useCaseNum) {
		UseCaseNum = useCaseNum;
	}
	public int getDependencyNum() {
		return DependencyNum;
	}
	public void setDependencyNum(int dependencyNum) {
		DependencyNum = dependencyNum;
	}
	public int getAssocNum() {
		return AssocNum;
	}
	public void setAssocNum(int assocNum) {
		AssocNum = assocNum;
	}
	public int getUC1() {
		return UC1;
	}
	public void setUC1(int uC1) {
		UC1 = uC1;
	}
	public int getUC2() {
		return UC2;
	}
	public void setUC2(int uC2) {
		UC2 = uC2;
	}
	public double getUC3() {
		return UC3;
	}
	public void setUC3(double uC3SAX) {
		UC3 = uC3SAX;
	}
	public double getUC4() {
		return UC4;
	}
	public void setUC4(double uC4) {
		UC4 = uC4;
	}
	public double getNOD() {
		return NOD;
	}
	public void setNOD(double nOD) {
		NOD = nOD;
	}
    
    
}
