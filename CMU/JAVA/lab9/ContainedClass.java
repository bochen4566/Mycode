package DataPackage;

public class ContainedClass {
    private String str = "";

    public ContainedClass() {
    }

    public ContainedClass(String str) {
        this.str = str;
    }
    public ContainedClass(ContainedClass a){
        this.str = a.str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
