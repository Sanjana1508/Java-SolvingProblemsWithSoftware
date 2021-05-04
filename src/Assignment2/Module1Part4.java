package Assignment2;

import edu.duke.URLResource;

public class Module1Part4 {
    public void findWebLinks(){
        URLResource ur = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for(String word : ur.words()){
            String lower = word.toLowerCase();
            int index = lower.indexOf("youtube.com");
            if(index != -1){
                int open = word.lastIndexOf("\"", index);
                int close = word.indexOf("\"", index+1);
                String link = word.substring(open+1,close);
                System.out.println(link);
            }
        }
    }

    public static void main(String[] args) {
        Module1Part4 p = new Module1Part4();
        p.findWebLinks();
    }
}
