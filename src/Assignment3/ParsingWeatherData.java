package Assignment3;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import  java.io.File;

public class ParsingWeatherData {
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldestSoFar = null;
        for(CSVRecord record : parser){
           if(record.get("TemperatureF").equals("-9999")){
                continue;
            }
           else if(coldestSoFar == null){
                coldestSoFar= record;
            }
           else{
                double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                double currTemp = Double.parseDouble(record.get("TemperatureF"));
                if(coldestTemp > currTemp) {
                    coldestSoFar= record;
                }
            }
        }
        return  coldestSoFar;
    }
    public String fileWithColdestTemperature(){
        CSVRecord coldestSoFar = null;
        File coldestFile = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord current = coldestHourInFile(fr.getCSVParser());
            if(coldestSoFar == null){
                coldestSoFar= current;
                coldestFile = f;
            }
            else{
                double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                double currTemp = Double.parseDouble(current.get("TemperatureF"));
                if(coldestTemp > currTemp) {
                    coldestSoFar = current;
                    coldestFile = f;
                }
            }
        }
        return coldestFile.getName();
    }
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestHumidity =null;
        for(CSVRecord record : parser){
            if(!record.get("Humidity").equals("N/A")){
                if(lowestHumidity == null){
                    lowestHumidity = record;
                }
                else{
                    double humidSoFar = Double.parseDouble(lowestHumidity.get("Humidity"));
                    double currHumid = Double.parseDouble(record.get("Humidity"));
                    if(humidSoFar > currHumid){
                        lowestHumidity = record;
                    }
                }
            }
        }
        return lowestHumidity;
    }
    public CSVRecord lowestHumidityInManyFiles(){
        CSVRecord lowestHumidity = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord current = lowestHumidityInFile(fr.getCSVParser());
            if(lowestHumidity == null){
                lowestHumidity = current;
            }
            else{
                double lowestHumid = Double.parseDouble(lowestHumidity.get("Humidity"));
                double currHumid = Double.parseDouble(current.get("Humidity"));
                if(lowestHumid > currHumid){
                    lowestHumidity = current;
                }
            }
        }
        return lowestHumidity;
    }
    public double averageTemperatureInFile(CSVParser parser){
        double sum = 0.0;
        int count =0;
        for(CSVRecord record : parser){
            sum += Double.parseDouble(record.get("TemperatureF"));
            count++;
        }
        return sum/count;
    }
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avg = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is "+avg);
    }
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        double sum = 0.0;
        int count = 0;
        for(CSVRecord record : parser){
            if(Integer.parseInt(record.get("Humidity")) >= value) {
                sum += Double.parseDouble(record.get("TemperatureF"));
                count++;
            }
        }
        return sum/count;
    }
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avg = averageTemperatureWithHighHumidityInFile(parser, 80);
        if(avg == 0.0)
            System.out.println("No temperatures with that humidity");
        else
        System.out.println("Average Temp when high Humidity is "+avg);
    }
    public void testFileWithColdestTemperature(){
        String result = fileWithColdestTemperature();
        System.out.println("Coldest day was in file "+result);
        String year = result.substring(8,12);
        FileResource fr = new FileResource("src/Assignment3/"+year+"/"+result);
        CSVParser parser = fr.getCSVParser();
        System.out.println("Coldest Temperature on that day was "+coldestHourInFile(parser).get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were:");
        CSVParser parserToDisplay = fr.getCSVParser();
        for(CSVRecord record : parserToDisplay){
            System.out.println(record.get("DateUTC")+" "+record.get("TemperatureF"));
        }
    }
    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord result = coldestHourInFile(parser);
        System.out.println("Coldest Temperature = "+result.get("TemperatureF")+" at "+result.get("TimeEDT"));
    }
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was "+csv.get("Humidity")+" at "+csv.get("DateUTC"));
    }
    public void testLowestHumidityInManyFiles(){
        CSVRecord lowestHumidity = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was "+lowestHumidity.get("Humidity")+" at "+lowestHumidity.get("DateUTC"));
    }
    public static void main(String[] args) {
        ParsingWeatherData parseWeatherData = new ParsingWeatherData();
        parseWeatherData.testColdestHourInFile();
        parseWeatherData.testFileWithColdestTemperature();
        parseWeatherData.testLowestHumidityInFile();
        parseWeatherData.testLowestHumidityInManyFiles();
        parseWeatherData.testAverageTemperatureInFile();
        parseWeatherData.testAverageTemperatureWithHighHumidityInFile();
    }
}
