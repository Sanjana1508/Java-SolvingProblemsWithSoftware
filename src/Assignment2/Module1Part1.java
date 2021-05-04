package Assignment2;

public class Module1Part1 {
    public String findSimpleGene(String dna){
        String result = "";
        int startCodon = dna.indexOf("ATG");
        if(startCodon== -1){
            return result;
        }
        int stopCodon = dna.indexOf("TAA",startCodon+3);
        if(stopCodon == -1){
            return result;
        }
        if((stopCodon - startCodon) % 3 == 0){
            result = dna.substring(startCodon, stopCodon+3);
        }
        return result;
    }
    public void testSimpleGene(){
        String dna1 = "CTGATCTAA";
        System.out.println(dna1);
        System.out.println("Gene Found : "+findSimpleGene(dna1));
        String dna2 = "TCATGCTAGT";
        System.out.println(dna2);
        System.out.println("Gene Found : "+findSimpleGene(dna2));
        String dna3 = "TCAGCAATTGC";
        System.out.println(dna3);
        System.out.println("Gene Found : "+findSimpleGene(dna3));
        String dna4 = "TCGATGCTATAA";
        System.out.println(dna4);
        System.out.println("Gene Found : "+findSimpleGene(dna4));
        String dna5 = "TGATGCTGCCAGTCTATAACGT";
        System.out.println(dna5);
        System.out.println("Gene Found : "+findSimpleGene(dna5));
    }

    public static void main(String[] args) {
        Module1Part1 p = new Module1Part1();
        p.testSimpleGene();
    }
}
