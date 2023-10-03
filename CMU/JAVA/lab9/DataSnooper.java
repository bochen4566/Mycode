package DataPackage;

import java.util.ArrayList;

public class DataSnooper {
    Data d = new Data();

    public void snoop() {
        System.out.println("Beginning DataPackage.Data");
        System.out.println(d.toString());

        // Problem 1
        System.out.println("Direct access");
        System.out.println("iValue = " + d.getiValue());
        System.out.println("sValue = " + d.getsValue());
        System.out.println("iList = ");
        for (int i=0; i < d.getiList().length; i++) {
            System.out.println(d.getiList()[i]);
        }

        // Problem 2
        System.out.println("Change the simple data");
        d.setiValue(-1);
        d.setsValue("Dog");
//        d.iList[0] = 0;
//        d.iList[1] = 0;
//        d.iList[2] = 0;
        int[] list= {0,0,0};
        d.setiList(list);
        System.out.println(d.toString());

        // Problem 3
        System.out.println("Change the array");
        int[] mylist = {-1, -2, -3, -4, -5};
        d.setiList(mylist);
        System.out.println(d.toString());
        mylist[1] = 1000;
        System.out.println(d.toString());

        // Problem 4
        System.out.println("Change the ArrayList");
        ArrayList<Integer> yourlist = new ArrayList<>();
        yourlist.add(1000);
        d.setaList(yourlist);
        System.out.println(d.toString());

        // Problem 5
        Data newD = new Data(d);
        newD.setsValue("Cat");
        newD.getContainedClass().setStr("Elephant");
        System.out.println(newD.toString());
        System.out.println(d.toString());

        DataPackage.ContainedClass x = new DataPackage.ContainedClass();
        x.setStr("Gopher");
        d.setContainedClass(x);
        System.out.println(d);
        System.out.println(newD);
    }
}
