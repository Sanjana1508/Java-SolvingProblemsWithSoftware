package Assignment2;

public class Module2Part1 {
    public void printAllGenes(String dna){
        int startIndex = 0;
        System.out.println("Printing Genes");
        while(true){
            String currGene = findGene(dna,startIndex);
            if(currGene.isEmpty()){
                System.out.println();
                break;
            }
            System.out.println(currGene);
            startIndex = dna.indexOf(currGene, startIndex)+currGene.length();
        }
    }
    public String findGene(String dna, int startIndex){
        String result = "";
        int startCodonIndex = dna.indexOf("ATG",startIndex);
        if(startIndex == -1){
            return result;
        }
        int taaIndex = findStopCodon(dna,startCodonIndex,"TAA");
        int tgaIndex = findStopCodon(dna,startCodonIndex,"TGA");
        int tagIndex = findStopCodon(dna,startCodonIndex,"TAG");
        int minIndex = Math.min(taaIndex,Math.min(tgaIndex,tagIndex));
        if(minIndex == dna.length()){
            return result;
        }
        result = dna.substring(startCodonIndex,minIndex+3);
        return result;
    }
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int stopIndex = dna.indexOf(stopCodon, startIndex+3);
        if(stopIndex != -1){
            if((stopIndex - startIndex) % 3 == 0){
                return stopIndex;
            }
        }
        return dna.length();
    }
    public void testPrintAllGenes(){
        String dna1="TACTGA";
        System.out.println(dna1);
        printAllGenes(dna1);
        String dna2="ATGTCGTGA";
        System.out.println(dna2);
        printAllGenes(dna2);
        String dna3="CCTATGCTGAAATAATAG";
        System.out.println(dna3);
        printAllGenes(dna3);
        String dna4="ATGATTCGG";
        System.out.println(dna4);
        printAllGenes(dna4);
    }
    public void testFindGene(){
        String dna1="TACTGA";
        System.out.println(dna1);
        System.out.println(findGene(dna1,1));
        String dna2="ATGTCGTGA";
        System.out.println(dna2);
        System.out.println(findGene(dna2,0));
        String dna3="CCTATGCTGAAATAATAG";
        System.out.println(dna3);
        System.out.println(findGene(dna3,3));
        String dna4="ATGATTCGG";
        System.out.println(dna4);
        System.out.println(findGene(dna4,0));
    }
    public void testFindStopCodon(){
        String dna1 = "ATGTSGTAA";
        System.out.println(dna1);
        System.out.println(findStopCodon(dna1,0,"TAA"));
        String dna2 = "TCAATGCCCTGAACC";
        System.out.println(dna2);
        System.out.println(findStopCodon(dna2,3,"TGA"));
        String dna3 = "ATGCAATAGCTT";
        System.out.println(dna3);
        System.out.println(findStopCodon(dna3,0,"TAG"));
        String dna4 = "ATGCTTAGTC";
        System.out.println(dna4);
        System.out.println(findStopCodon(dna4,0,"TAG"));
    }

    public static void main(String[] args) {
        Module2Part1 p = new Module2Part1();
        p.testFindStopCodon();
        p.testFindGene();
        p.testPrintAllGenes();
    }
}
