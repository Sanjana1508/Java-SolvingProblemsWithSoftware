package HonorsAssignment;

import edu.duke.DirectoryResource;
import edu.duke.ImageResource;
import edu.duke.Pixel;
import java.io.File;

public class ConvertToGrayScale {
    public void batchGrayScale(){
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            ImageResource inImage = new ImageResource(f);
            ImageResource outImage = makeGrayScale(inImage);
            outImage.setFileName("gray-"+f.getName());
            outImage.save();
            outImage.draw();
        }
    }
    public ImageResource makeGrayScale(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for(Pixel p : outImage.pixels()){
            Pixel inPixel = inImage.getPixel(p.getX(),p.getY());
            int avg = (inPixel.getRed() + inPixel.getGreen() + inPixel.getBlue())/3;
            p.setRed(avg);
            p.setGreen(avg);
            p.setBlue(avg);
        }
        return outImage;
    }
    public void testGray() {
        batchGrayScale();
    }

    public static void main(String[] args) {
        ConvertToGrayScale grayScale = new ConvertToGrayScale();
        grayScale.testGray();
    }
}
