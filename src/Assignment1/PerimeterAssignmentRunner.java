import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        int count=0;
        for (Point p : s.getPoints()){
            count++;
        }
        return count;
    }

    public double getAverageLength(Shape s) {
        double perimeter = getPerimeter(s);
        int numberOfPoints = getNumPoints(s);
        double avgLength = perimeter/numberOfPoints;
        return avgLength;
    }

    public double getLargestSide(Shape s) {
        double largestSoFar=0.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()){
            double currDist = prevPt.distance(currPt);
            if(largestSoFar < currDist){
                largestSoFar = currDist;
            }
        }
        return largestSoFar;
    }

    public double getLargestX(Shape s) {
        double largestX=0.0;
        for (Point currPt : s.getPoints()){
            if (currPt.getX() > largestX){
                largestX = currPt.getX();
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        double largestSoFar=0.0;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape shape = new Shape(fr);
            double currPerimeter = getPerimeter(shape);
            if ( largestSoFar == 0.0){
                largestSoFar = currPerimeter;
            }
            else if (largestSoFar < currPerimeter){
                largestSoFar = currPerimeter;
            }
        }
        return largestSoFar;
    }

    public File getFileWithLargestPerimeter() {
        File temp = null;
        double largestSoFar=0.0;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape shape = new Shape(fr);
            double currPerimeter = getPerimeter(shape);
            if(largestSoFar == 0.0){
                largestSoFar = currPerimeter;
                temp = f;
            }
            if (largestSoFar < currPerimeter){
                largestSoFar = currPerimeter;
                temp = f;
            }
        }
        return temp;
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        int numberOfPoints = getNumPoints(s);
        System.out.println("Number Of Points = "+numberOfPoints);
        double avgLength = getAverageLength(s);
        System.out.println("Average Length = "+avgLength);
        double largestSide = getLargestSide(s);
        System.out.println("Largest Side = "+largestSide);
        double largestX = getLargestX(s);
        System.out.println("Largest X value = "+largestX);
    }
    
    public void testPerimeterMultipleFiles() {
        double largestPerimeter = getLargestPerimeterMultipleFiles();
        System.out.println("Largest Perimeter in Selected Files = "+largestPerimeter);
    }

    public void testFileWithLargestPerimeter() {
        File largestPerimeterfile = getFileWithLargestPerimeter();
        System.out.println("File with Largest Perimeter in Selected Files = "+largestPerimeterfile.getName());
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
        pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}
