import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.*;
import org.apache.batik.anim.dom.SVGDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.dom.GenericDOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DOMImplementation;
import java.util.Scanner;
 public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("Enter how you wish to name the SVG file:");
        Scanner userInp = new Scanner(System.in);
        String fileName = userInp.nextLine();

        File svgFil = null;
        try{
            svgFil = new File(fileName + ".svg");
            if (svgFil.createNewFile()){
                System.out.println("File created: " + svgFil.getName());
            } else System.out.println("File already exists");
        } catch (IOException e){
            System.out.println("An error ocurred");
            e.printStackTrace();
        }

        DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();
        String svgNS = SVGDOMImplementation.SVG_NAMESPACE_URI;
        Document document = domImpl.createDocument(svgNS, "svg", null);

        SVGGraphics2D svgDoc = new SVGGraphics2D(document);
        Shape circle = new Ellipse2D.Double(0, 0, 50, 50);
        Shape square = new Rectangle2D.Double(0, 0, 50, 50);
        svgDoc.setPaint(Color.red);
        svgDoc.fill(circle);
        svgDoc.translate(60, 0);
        svgDoc.setPaint(Color.green);
        svgDoc.fill(circle);
        svgDoc.translate(60, 0);
        svgDoc.setPaint(Color.blue);
        svgDoc.fill(circle);
        svgDoc.translate(60, 0);
        svgDoc.setPaint(Color.MAGENTA);
        svgDoc.fill(square);
        svgDoc.setSVGCanvasSize(new Dimension(240, 50));

        boolean useCSS = true;

        try {
            FileWriter nuFil = new FileWriter(svgFil);
            svgDoc.stream(nuFil, useCSS);
        } catch(IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}