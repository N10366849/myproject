//shape dimension class
public class ShapeDimensions {
    public String name;
    public int x;
    public int y;
    public int w;
    public int h;
    public String color;

    //empty constructor
    public ShapeDimensions()
    {

    }
    //constructor call
    public ShapeDimensions(String n1,int x1,int x2,int x3,int x4)
    {
        this.name=n1;
        this.x=x1;
        this.y=x2;
        this.w=x3;
        this.h=x4;
    }


    //public methods
    public void setName(String n)
    {
        this.name=n;
    }

    public void setX(int n)
    {
        this.x=n;
    }

    public void setY(int n)
    {
        this.y=n;
    }
    public void setW(int n)
    {
        this.w=n;
    }
    public void setH(int n)
    {
        this.h=n;
    }

    public String getColor()
    {
        return this.color;
    }


    public void setColor(String c)
    {
        this.color=c;
    }

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }
    public int getW()
    {
        return this.w;
    }
    public int getH()
    {
        return this.h;
    }

}
