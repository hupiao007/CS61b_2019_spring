/** NBody is a class that will actually run your simulation. 
 * This class will have NO constructor. */
public class NBody {
    /** it returns a double corresponding to the radius of the universe in that file. */
    public static Double readRadius(String filePath) {
        In in = new In(filePath);
        /* Every time you call a read method from the In class,
         * it reads the next thing from the file, assuming it is of the specified type. */
        int planetsNumber = in.readInt();
        double radiusUnivers = in.readDouble();
        return radiusUnivers;
    }

    /** it returns an array of Bodys corresponding to the bodies in the file. */
    public static Body[] readBodies(String filePath) {
        In in = new In(filePath);
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

    private static int readPlanetNumber(String filePath) {
        In in = new In(filePath);
        int planetsNumber = in.readInt();
        return planetsNumber;
    }

    /** collect all needed inputs. */
    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String fileName = args[2];
        double radiusUnivers = readRadius(fileName);
        Body[] bodies = readBodies(fileName);
        int planetsNumber = readPlanetNumber((fileName));

        // Draw the background, star filed.
        StdDraw.setScale(-2*radiusUnivers, 2*radiusUnivers);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");

        // Draw each one of the bodies in the Bodys array you created.
        for (Body b : bodies) {
            b.draw();
        }
<<<<<<< HEAD
        /* Shows the drawing to the screen, and waits 2000 milliseconds. */
        StdDraw.show();
        StdDraw.pause(2000);



        

        
        





=======

        // Enables double buffering.
        StdDraw.enableDoubleBuffering();

        // Time variable, building up with dt until T.
        double tim = 0;
        //For each time through the loop, do the following:
        while (tim < T) {
            //Calculate the net x and y forces for each Body,
            // storing these in the xForces and yForces arrays respectively.
            double[] xForces = new double[planetsNumber];
            double[] yForces = new double[planetsNumber];
            for (int i = 0; i < planetsNumber; i += 1) {
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }
            //call update on each of the Bodies. Update each body’s position, velocity, and acceleration.
            for (int i = 0; i < planetsNumber; i += 1) {
                bodies[i].update(dt, xForces[i], yForces[i]);
            }
            // Draw the background, star filed and then all the bodies.
            StdDraw.setScale(-2*radiusUnivers, 2*radiusUnivers);
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Body b : bodies) {
                b.draw();
            }
            //Show the offscreen buffer and pause the animation.
            StdDraw.show();
            StdDraw.pause(10);
            // time increase
            tim += dt;
        }
        //Print the universe
        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radiusUnivers);
        for (int i = 0; i < bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                    bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
        }
>>>>>>> proj1a

    }
}