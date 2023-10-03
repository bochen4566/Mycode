package DataPackage;

import java.util.ArrayList;
import java.util.Arrays;

public class Data {
    private int iValue = 10;

    public void setiValue(int iValue) {
        this.iValue = iValue;
    }

    public int getiValue() {
        return iValue;
    }

    private String sValue = "a string";
    private int[] iList = {12, 15, 20};
    private ArrayList<Integer> aList = new ArrayList<>();
    private ContainedClass containedClass = new ContainedClass();

    // add getters
    public String getsValue() {
        return sValue;
    }

    public ArrayList<Integer> getaList() {
        return aList;
    }

    public ContainedClass getContainedClass() {
        return containedClass;
    }

    public int[] getiList() {
        return iList;
    }




    public Data() {
        for (int i=0; i<iList.length; i++) {
            aList.add(iList[i]);
        }
        containedClass.setStr("a contained string");
    }

    public Data(Data d) {
        this.iValue = d.iValue;
        this.sValue = d.sValue;
        this.iList = d.iList;
        this.aList = d.aList;
        this.containedClass  = d.containedClass;
    }

    @Override
    public String toString() {
        return "DataPackage.Data{" +
                "iValue=" + iValue +
                ", sValue='" + sValue + "\'" +
                ", iList=" + Arrays.toString(iList) +
                ", aList=" + aList +
                ", containedClass = " + containedClass.getStr() +
                "}";
    }

    public void setsValue(String sValue) {
        this.sValue = sValue;
    }
    public void setiList(int[] iList) {
        this.iList = new int[iList.length];

        for(int i= 0; i< iList.length; i++){
            this.iList[i] = iList[i];
        }
    }
    public void setaList(ArrayList<Integer> aList) {
        this.aList = aList;
    }

    public void setContainedClass(ContainedClass containedClass) {
        this.containedClass = new ContainedClass(containedClass);
    }
}
