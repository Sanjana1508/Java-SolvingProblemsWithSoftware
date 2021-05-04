package Assignment2;

public class Module2Part2 {
    public int howMany(String stringa, String stringb){
        int startIndex = 0;
        int count = 0;
        while(true){
            int index = stringb.indexOf(stringa, startIndex);
            if(index == -1){
                break;
            }
            count++;
            startIndex = stringb.indexOf(stringa, startIndex) + stringa.length();
        }
        return count;
    }
    public void testHowMany(){
        System.out.println(howMany("GAA","ATGAACGAATTGAATC"));
        System.out.println(howMany("AA","ATAAAA"));
    }

    public static void main(String[] args) {
        Module2Part2 p = new Module2Part2();
        p.testHowMany();
    }
}
