/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Melanie
 */

public class Boat {
    //Attributs
    String name;
    int speed;
    int angle;
    boolean power;
    
    //Constructor
    public Boat(String name)
    {
        this.name = name;
        this.speed = 0;
        this.angle = 0;
        this.power = false;
    }
    
    //Methods
    
    /**
     * This method turns on the boat power > power = true
     */
    public void powerOn()
    {
        this.power = true;
        System.out.println(this.name + " is pointing in the direction of " + this.angle + "deg with the speed of " + this.speed + "mph.");
    }
    
    /**
     * This method turns off the boat power > power = false
     * All the boat data are resetted then > speed and angle = 0
     */
    public void powerOff()
    {
        this.power = false;
        System.out.println(this.name + " is powered OFF. Speed and angle are resetted to 0.");
        
        //resetting boat data
        this.speed = 0;
        this.angle = 0;
    }
    
    /**
     * This method increases the boat speed by the minimum speed increment 2 mph
     */
    public void speedUp()
    {
        if(this.power == true)
        {
            this.speed += 2;
            System.out.println(this.name + " is pointing in the direction of " + this.angle + "deg with the speed of " + this.speed + "mph.");
        }
        else
        {
            System.out.println(this.name + " is powered OFF. You cannot increase its speed.");
        }
    }
    
    /**
     * This method decreases the boat speed by the minimum speed increment 2 mph
     */
    public void slowDown()
    {
        if(this.power == true)
        {
            if(this.speed >= 2)
            {
                this.speed -= 2;
                System.out.println(this.name + " is pointing in the direction of " + this.angle + "deg with the speed of " + this.speed + "mph.");
            }
            else
            {
                this.speed = 0;
                System.out.println(this.name + " is stopped and pointing in the direction of " + this.angle + " deg.");
            }
        }
        else
        {
            System.out.println(this.name + " is powered OFF. You cannot decrease its speed.");
        }
    }
    
    /**
     * This method turns the boat to the left by the minimum left turn degree 5 deg
     */
    public void turnLeft()
    {
        if(this.power == true)
        {
            this.angle += 5;
            System.out.println(this.name + " is pointing in the direction of " + this.angle + "deg with the speed of " + this.speed + "mph.");
        }
        else
        {
            System.out.println(this.name + " is powered OFF. You cannot turn left.");
        }
    }
    
    /**
     * This method turns the boat to the right by the minimum right turn degree 5 deg
     */
    public void turnRight()
    {
        if(this.power == true)
        {
            this.angle -= 5;
            System.out.println(this.name + " is pointing in the direction of " + this.angle + "deg with the speed of " + this.speed + "mph.");
        }
        else
        {
            System.out.println(this.name + " is powered OFF. You cannot turn right.");
        }
    }
}
