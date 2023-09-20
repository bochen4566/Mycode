public class recur {

    public static int computeFibonacci(int first, int last, int n){
        if(n == -1) return first;
        else if (n == 0) {return last;}
        else{
            return computeFibonacci(last, first + last, n -1);

        }
    }

    public static void main(String args[]){
       System.out.println(computeFibonacci(0,1,6));
    }
}
