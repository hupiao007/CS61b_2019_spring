/** Project 0 */
public class Body{

    /** instance variables */
    public double xxPos; //Its current x position
    public double yyPos; //Its current y position
    public double xxVel; //Its current velocity in the x direction
    public double yyVel; //Its current velocity in the y direction
    public double mass; //Its mass
    public String imgFileName; //The name of the file that corresponds to 
                //the image that depicts the body (for example, jupiter.gif)
    public Body(double xP, double yP, double xV,
              double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV; 
        yyVel = yV;
        mass = m; 
        imgFileName = img;
    }
    public Body(Body b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel; 
        yyVel = b.yyVel;
        mass = b.mass; 
        imgFileName = b.imgFileName;
    } //instaniate a copy of a body

    public double calcDistance(Body another) {
        /** calculate and return the distance between two bodies. */
        return Math.sqrt(Math.pow(this.xxPos - another.xxPos, 2) + 
            Math.pow(this.yyPos - another.yyPos, 2));
    }

    public double calcForceExertedBy(Body another) {
        /** calculate and return the force between two bodies. */
        double G = 6.67e-11;
        return G * this.mass * another.mass / Math.pow(this.calcDistance(another), 2);
    }

    public double calcForceExertedByX(Body another) {
        /** calculate and return on x axis. */
        return this.calcForceExertedBy(another) * (another.xxPos - this.xxPos)
        / this.calcDistance(another);
    }
    public double calcForceExertedByY(Body another) {
        /** calculate and return on y axis. */
        return this.calcForceExertedBy(another) * (another.yyPos - this.yyPos)
        / this.calcDistance(another);
    }

    public double calcNetForceExertedByX(Body[] bodies) {
        /** calculates the net X force exerted by all bodies in that 
        array upon the current Body. */
        double net_F_X = 0;
        for (Body another : bodies) {
            if (this.equals(another)) {
                continue;
            }
            net_F_X += this.calcForceExertedByX(another);           
        }
        return net_F_X;
    }
    public double calcNetForceExertedByY(Body[] bodies) {
        /** calculates the net Y force exerted by all bodies in that 
        array upon the current Body. */
        double net_F_Y = 0;
        for (Body another : bodies) {
            if (this.equals(another)) {
                continue;
            }
            net_F_Y += this.calcForceExertedByY(another);           
        }
        return net_F_Y;
    }

    public void update(double dt, double fX, double fY) {
        /** the force extered on this body changes in the body’s velocity and 
        osition in a small period of time dt. */
        this.xxVel += dt * (fX / this.mass);
        this.yyVel += dt * (fY / this.mass);
        this.xxPos += dt * this.xxVel;
        this.yyPos += dt * this.yyVel;
    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, this.imgFileName);
    }




}