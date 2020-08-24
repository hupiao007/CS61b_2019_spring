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
    } //instantiate a copy of a body

    /** calculate and return the distance between two bodies. */
    public double calcDistance(Body another) {
        return Math.sqrt(Math.pow(this.xxPos - another.xxPos, 2) +
            Math.pow(this.yyPos - another.yyPos, 2));
    }

    /** calculate and return the force between two bodies. */
    public double calcForceExertedBy(Body another) {
        double G = 6.67e-11;
        return G * this.mass * another.mass / Math.pow(this.calcDistance(another), 2);
    }

    /** calculate and return on x axis. */
    public double calcForceExertedByX(Body another) {
        return this.calcForceExertedBy(another) * (another.xxPos - this.xxPos)
        / this.calcDistance(another);
    }

    /** calculate and return on y axis. */
    public double calcForceExertedByY(Body another) {
        return this.calcForceExertedBy(another) * (another.yyPos - this.yyPos)
        / this.calcDistance(another);
    }

    /** calculates the net X force exerted by all bodies in that array upon the current Body. */
    public double calcNetForceExertedByX(Body[] bodies) {
        double net_F_X = 0;
        for (Body another : bodies) {
            if (this.equals(another)) {
                continue;
            }
            net_F_X += this.calcForceExertedByX(another);           
        }
        return net_F_X;
    }

    /** calculates the net Y force exerted by all bodies in that array upon the current Body. */
    public double calcNetForceExertedByY(Body[] bodies) {

        double net_F_Y = 0;
        for (Body another : bodies) {
            if (this.equals(another)) {
                continue;
            }
            net_F_Y += this.calcForceExertedByY(another);           
        }
        return net_F_Y;
    }

    /** the force exerted on this body changes in the body’s velocity and position in a small period of time dt.*/
    public void update(double dt, double fX, double fY) {
        this.xxVel += dt * (fX / this.mass);
        this.yyVel += dt * (fY / this.mass);
        this.xxPos += dt * this.xxVel;
        this.yyPos += dt * this.yyVel;
    }

    /** Draw the Body’s image at the Body’s position.*/
    public void draw() {
        String file_path = "images/" + this.imgFileName;
        StdDraw.picture(this.xxPos, this.yyPos, file_path);
    }




}
