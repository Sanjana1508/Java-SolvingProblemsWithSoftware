package Assignment2;

public class Module1Part3 {
    public boolean twoOccurrences(String stringa, String stringb){
        int first = stringb.indexOf(stringa);
        if(first == -1){
            return false;
        }
        int second = stringb.indexOf(stringa,first+1);
        if(second == -1){
            return false;
        }
        return true;
    }
    public void testing(){
        System.out.println(twoOccurrences("by","A story by Abby Long"));
        System.out.println(twoOccurrences("a","banana"));
        System.out.println(twoOccurrences("atg","ctgtatgta"));
        System.out.println(twoOccurrences("aa","aaa"));

        System.out.println(lastPart("an","banana"));
        System.out.println(lastPart("zoo","forest"));
    }
    public String lastPart(String stringa, String stringb){
        int index = stringb.indexOf(stringa);
        if(index == -1){
            return stringb;
        }
        return stringb.substring(index+stringa.length());
    }
    public static void main(String[] args) {
        Module1Part3 p = new Module1Part3();
        p.testing();

    }
}
