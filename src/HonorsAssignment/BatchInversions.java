package HonorsAssignment;

import edu.duke.DirectoryResource;
import edu.duke.ImageResource;
import edu.duke.Pixel;

import java.io.File;

public class BatchInversions {
    public void selectAndConvert(){
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            ImageResource inImage = new ImageResource(f);
            ImageResource outImage = makeInversion(inImage);
            outImage.setFileName("inversion-"+f.getName());
            outImage.save();
            outImage.draw();
        }
    }
    public ImageResource makeInversion(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for(Pixel p : outImage.pixels()){
            Pixel inPixel = inImage.getPixel(p.getX(),p.getY());
            p.setRed(255 - inPixel.getRed() );
            p.setGreen(255 - inPixel.getGreen() );
            p.setBlue(255 - inPixel.getBlue() );
        }
        return outImage;
    }
    public void testGray() {
        selectAndConvert();
    }

    public static void main(String[] args) {
        BatchInversions invert= new BatchInversions();
        invert.selectAndConvert();
    }
}
