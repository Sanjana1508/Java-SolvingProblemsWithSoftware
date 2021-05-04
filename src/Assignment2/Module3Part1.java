package Assignment2;

import edu.duke.StorageResource;
import edu.duke.FileResource;
import java.lang.String;

public class Module3Part1 {
    public StorageResource getAllGenes(String dna){
        int startIndex = 0;
        StorageResource storage = new StorageResource();
        while(true){
            String currGene = findGene(dna,startIndex);
            if(currGene.isEmpty()){
                break;
            }
            storage.add(currGene);
            startIndex = dna.indexOf(currGene, startIndex)+currGene.length();
        }
        return storage;
    }
    public void printAllGenes(String dna){
        int startIndex = 0;
        while(true){
            String currGene = findGene(dna,startIndex);
            if(currGene.isEmpty()){
                break;
            }
            System.out.println(currGene);
            startIndex = dna.indexOf(currGene, startIndex)+currGene.length();
        }
    }
    public String findGene(String dna, int startIndex){
        String result = "";
        int startCodonIndex = dna.indexOf("ATG",startIndex);
        if(startCodonIndex == -1){
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
        while(stopIndex != -1){
            if((stopIndex - startIndex)%3 == 0){
                return stopIndex;
            }
            else
                stopIndex = dna.indexOf(stopCodon,stopIndex+1);
        }
        return dna.length();
    }
    public void testGetAllGenes(){
        String dna = "ATGTAAGATGCCCTAGT";
        System.out.println(dna);
        StorageResource storage = getAllGenes(dna);
        for(String gene : storage.data()){
            System.out.println(gene);
        }
    }
    public double countCTG(String dna){
        String upperDna = dna.toUpperCase();
        double cgCount = 0.0;
        for(char c : upperDna.toCharArray()){
            if(c == 'C' || c == 'G'){
                cgCount++;
            }
        }
        return cgCount/dna.length();
    }
    public void processGenes(StorageResource sr){
        int longerThan9 = 0;
        int greaterCG = 0;
        int longestGeneLength= 0;
        int longerThan60 = 0;
        System.out.println("Printing genes");
        for(String gene : sr.data()){
            if(gene.length() > 9) {
                longerThan9++;
            }
            System.out.println(gene);
        }
        System.out.println("Number of Genes longer than 9 characters is "+longerThan9);
        for(String gene : sr.data()){
            if(countCTG(gene) > 0.35){
                greaterCG++;
            }
        }
        System.out.println("Number of genes having C-G ratio greater than 0.35 is "+greaterCG);
        for(String gene : sr.data()){
            if(gene.length() > longestGeneLength)
                longestGeneLength = gene.length();
        }
        System.out.println("Length of Longest Gene = "+longestGeneLength);
        System.out.println("Printing Strings that are longer than 60 characters");
        for(String gene : sr.data()){
            if(gene.length() > 60)
                System.out.println(gene);
        }
    }
    public void testProcessGenes(){
        String dna1 = "ATGCTGGCTTGACTG";
        System.out.println(dna1);
        processGenes(getAllGenes(dna1));
        String dna2 = "ATGTAA";
        System.out.println(dna2);
        processGenes(getAllGenes(dna2));
        String dna3 = "CTGGTACCTG";
        System.out.println(dna3);
        processGenes(getAllGenes(dna3));
        String dna4 = "TCGAATTA";
        System.out.println(dna4);
        processGenes(getAllGenes(dna4));
        String dna5 = "ATGGTCCAGTGCTAGATGCATTAA";
        System.out.println(dna5);
        processGenes(getAllGenes(dna5));
    }
    public void testOnRealDNA(){
        FileResource fr = new FileResource("src/Assignment2/brca1line.fa");
        String dna = fr.asString().toUpperCase();
        processGenes(getAllGenes(dna));
    }
    public void testCGCount(){
        String dna = "ATGCCATAG";
        System.out.println(dna);
        System.out.println(countCTG(dna));
    }
    public static void main(String[] args) {
        Module3Part1 p = new Module3Part1();
        p.testGetAllGenes();
        p.testCGCount();
        p.testProcessGenes();
        p.testOnRealDNA();
    }
}
