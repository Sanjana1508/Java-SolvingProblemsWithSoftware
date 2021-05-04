package Assignment2;

public class Module2Part3 {
    public int countGenes(String dna){
    int count = 0;
    int startIndex = 0;
    while(true){
        String currGene = findGene(dna,startIndex);
        if(currGene.isEmpty()){
            break;
        }
        count++;
        startIndex = dna.indexOf(currGene, startIndex)+currGene.length();
    }
    return count;
    }
    public void printAllGenes(String dna){
        int startIndex = 0;
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
        while (stopIndex != -1){
            if((stopIndex - startIndex) % 3 == 0){
                return stopIndex;
            }
            else
                stopIndex = dna.indexOf(stopCodon,stopIndex+1);
        }
        return dna.length();
    }
    public void testCountGenes(){
        String dna1 = "ATGTAAGATGCCCTAGT";
        System.out.println(dna1);
        System.out.println(countGenes(dna1));
        printAllGenes(dna1);
    }

    public static void main(String[] args) {
        Module2Part3 p = new Module2Part3();
        p.testCountGenes();
    }
}
