package Assignment2;

public class Module1Part2 {
    public String findSimpleGene(String dna, String startCodon, String stopCodon){
        dna = dna.toUpperCase();
        String result = "";
        int startIndex = dna.indexOf(startCodon);
        if(startIndex == -1){
            return result;
        }
        int stopIndex = dna.indexOf(stopCodon,startIndex+3);
        if(stopIndex == -1){
            return result;
        }
        if((stopIndex - startIndex) % 3 == 0){
            result = dna.substring(startIndex, stopIndex+3);
        }
        return result;
    }
    public void testSimpleGene(){
        String dna1 = "CTGATCTAA";
        System.out.println(dna1);
        System.out.println("Gene Found : "+findSimpleGene(dna1,"ATG","TAA"));
        String dna2 = "TCATGCTAGT";
        System.out.println(dna2);
        System.out.println("Gene Found : "+findSimpleGene(dna2,"ATG","TGA"));
        String dna3 = "TCAGCAATTGC";
        System.out.println(dna3);
        System.out.println("Gene Found : "+findSimpleGene(dna3,"ATG","TAG"));
        String dna4 = "TCGATGCTATAA";
        System.out.println(dna4);
        System.out.println("Gene Found : "+findSimpleGene(dna4,"ATG","TAA"));
        String dna5 = "TGATGCTGCCAGTCTATAACGT";
        System.out.println(dna5);
        System.out.println("Gene Found : "+findSimpleGene(dna5,"AGC","TGC"));
    }
    public static void main(String[] args) {
        Module1Part1 p = new Module1Part1();
        p.testSimpleGene();
    }
}
