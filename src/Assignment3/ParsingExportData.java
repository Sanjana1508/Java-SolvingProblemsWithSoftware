package Assignment3;

import org.apache.commons.csv.CSVParser;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVRecord;

public class ParsingExportData {
    public String  countryInfo(CSVParser parser, String country){
        String result = "NOT FOUND";
        for(CSVRecord record : parser){
            if(record.get("Country").equals(country)){
                result = country+" : "+record.get("Exports")+" : "+record.get("Value (dollars)");
            }
        }
        return result;
    }
    public void listExportersTwoProducts(CSVParser parser , String exportItem1, String exportItem2){
        for(CSVRecord record : parser){
            if(record.get("Exports").contains(exportItem1) && record.get("Exports").contains(exportItem2)){
                System.out.println(record.get("Country"));
            }
        }
    }
    public int numberOfExporters(CSVParser parser, String exportitem){
        int numberOfCountries = 0;
        for(CSVRecord record : parser){
            if(record.get("Exports").contains(exportitem)){
                numberOfCountries++;
            }
        }
        return  numberOfCountries;
    }
    public  void bigExporters(CSVParser parser, String amount){
        for(CSVRecord record : parser){
            if(record.get("Value (dollars)").length() > amount.length()){
                System.out.println(record.get("Country")+" "+record.get("Value (dollars)"));
            }
        }
    }
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println(countryInfo(parser,"Nauru"));
        listExportersTwoProducts(parser,"gold","diamonds");
        System.out.println(numberOfExporters(parser,"sugar"));
        bigExporters(parser, "$999,999,999,999");
    }
    public static void main(String[] args) {
        ParsingExportData parseData = new ParsingExportData();
        parseData.tester();
    }
}
