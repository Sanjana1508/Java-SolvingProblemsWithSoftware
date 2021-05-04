package BabyNamesMiniProject;

import edu.duke.DirectoryResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import edu.duke.FileResource;
import java.io.File;
import java.nio.charset.StandardCharsets;

public class BabyNames {
    public void totalBirths(FileResource fr){
        int totalBirths = 0;
        int girlBirths = 0;
        int boyBirths = 0;
        for(CSVRecord record : fr.getCSVParser(false)){
            totalBirths += Integer.parseInt(record.get(2));
            if(record.get(1).equals("F"))
            girlBirths += Integer.parseInt(record.get(2));
            else
            boyBirths += Integer.parseInt(record.get(2));
        }
        System.out.println("Total Births = "+totalBirths);
        System.out.println("Girl Births = "+girlBirths);
        System.out.println("Boy Births = "+boyBirths);
    }
    public void testTotalBirths(){
        FileResource fr = new FileResource("src/BabyNamesMiniProject/us_babynames/us_babynames_test/example-small.csv");
        totalBirths(fr);
    }
    public int getRank(int year, String name, String gender){
        int rank = 0;
        FileResource fr =new FileResource("src/BabyNamesMiniProject/us_babynames/us_babynames_by_year/yob"+year+".csv");
        for(CSVRecord record : fr.getCSVParser(false)){
            if(record.get(1).equals(gender)){
                if(record.get(0).equals(name)) {
                    rank++;
                    return  rank;
                }
                rank++;
            }
        }
        return -1;
    }
    public void testGetRank(){
        System.out.println("Rank = "+getRank(2012,"Mason","M"));
    }
    public String getName(int year, int rank, String gender){
        String name ="NO NAME";
        int rankNum = 0;
        FileResource fr =new FileResource("src/BabyNamesMiniProject/us_babynames/us_babynames_by_year/yob"+year+".csv");
        for(CSVRecord record : fr.getCSVParser(false)){
            if(record.get(1).equals(gender)){
                rankNum++;
                if(rankNum == rank)
                    return record.get(0);
            }
        }
        return name;
    }
    public void testGetName(){
        System.out.println("Name with Rank "+1+" is "+getName(2012,1,"M"));
    }
    public String whatIsNameInYear(String name, int year, int newYear,String gender){
        int rank = getRank(year,name,gender);
        String newName = getName(newYear,rank,gender);
        return newName;
    }
    public void testWhatIsNameInYear(){
        String name = "Isabella";
        int year = 2012;
        int newYear = 2014;
        String gender = "F";
        System.out.println(name+" born in "+year+" would be "+whatIsNameInYear(name,year,newYear,gender)+" if she was born in "+newYear);
    }
    public int yearOfHighestRank(String name, String gender){
        int highestRank = Integer.MAX_VALUE;
        int resultYear = 0;
        DirectoryResource dr =new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser(false);
            int year = Integer.parseInt(f.getName().substring(3,7));
            int rank = getRank(year,name,gender);
            if(highestRank > rank) {
                highestRank = rank;
                resultYear = year;
            }
        }
        return resultYear;
    }
    public void testYearOfHighestRank(){
        String name = "Mason";
        String gender = "M";
        System.out.println(name+" is the highest ranked name in "+yearOfHighestRank(name,gender));
    }
    public double getAverageRank(String name, String gender){
        int rankSum = 0;
        int count = 0;
        DirectoryResource dr =new DirectoryResource();
        for(File f : dr.selectedFiles()) {
            count++;
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser(false);
            int year = Integer.parseInt(f.getName().substring(3,7));
            int rank = getRank(year,name,gender);
            if(rank != -1)
                rankSum +=rank;
        }
        return  rankSum/count;
    }
    public void testGetAverageRank(){
        String name = "Mason";
        String gender = "M";
        System.out.println("Average rank = "+getAverageRank(name,gender));
    }
    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        int rank = getRank(year,name,gender);
        int totalBirths = 0;
        int count = 0;
        FileResource fr = new FileResource("src/BabyNamesMiniProject/us_babynames/us_babynames_by_year/yob"+year+".csv");
        CSVParser parser = fr.getCSVParser(false);
        for(CSVRecord record : parser){
            if(record.get(1).equals(gender))
                count++;
            if(count < rank && count!=0)
                totalBirths += Integer.parseInt(record.get(2));
        }
        return totalBirths;
    }
    public void testGetTotalBirthsRankedHigher(){
        int year = 2012;
        String name ="Ethan";
        String gender = "M";
        System.out.println("Total Births Higher rank than "+name+ " is "+getTotalBirthsRankedHigher(year,name,gender));
    }
    public static void main(String[] args) {
        BabyNames bNames = new BabyNames();
        bNames.testTotalBirths();
        bNames.testGetRank();
        bNames.testGetName();
        bNames.testWhatIsNameInYear();
        bNames.testYearOfHighestRank();
        bNames.testGetAverageRank();
        bNames.testGetTotalBirthsRankedHigher();
    }
}
