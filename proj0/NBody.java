/** NBody is a class that will actually run your simulation. 
 * This class will have NO constructor. */
public class NBody {
    public static Double readRadius(String filePath) {
        /** it returns a double corresponding to the radius of the universe in that file. */
        In in = new In(filePath);
        /* Every time you call a read method from the In class,
         * it reads the next thing from the file, assuming it is
         * of the specified type. */
        int planetsNumber = in.readInt();
        double radiusUnivers = in.readDouble();
        return radiusUnivers;
    }

    public static Body[] readBodies(String filePath) {
    /** it returns an array of Bodys corresponding to the bodies in the file. */
        In in = new In(filePath);
        /* Every time you call a read method from the In class,
         * it reads the next thing from the file, assuming it is
         * of the specified type. */
        int planetsNumber = in.readInt();
        double radiusUnivers = in.readDouble();
        Body[] bodies = new Body[planetsNumber];
        for(int i = 0; i < planetsNumber && !in.isEmpty(); i += 1) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            bodies[i] = new Body(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
        }
        return bodies;
    }

    public static void main(String[] args) {
        /** collecte all needed inputs. */
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String fileName = args[2];
        double radiusUnivers = readRadius(fileName);
        Body[] bodies = readBodies(fileName);

        public static String imageToDraw = "./images/starfield.jpg";
        /** Enables double buffering.
          * A animation technique where all drawing takes place on the offscreen canvas.
          * Only when you call show() does your drawing get copied from the
          * offscreen canvas to the onscreen canvas, where it is displayed
          * in the standard drawing window. */
        StdDraw.enableDoubleBuffering();

        /* Sets up the universe area. */
        StdDraw.setScale(-2 * radiusUnivers, 2 * radiusUnivers);
        /* Clears the drawing window. */
        StdDraw.clear();
        /** draw the background. starfield.jpg */
        StdDraw.picture(0, 0, imageToDraw);

        /* Draw the planets. */
        for (Body b : bodies) {
            b.draw();
        }


        /* Shows the drawing to the screen, and waits 2000 milliseconds. */
        StdDraw.show();
        StdDraw.pause(2000);



        

        
        






    }
}