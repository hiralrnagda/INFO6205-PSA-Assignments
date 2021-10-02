/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.randomwalk;

import java.util.Random;

public class RandomWalk {

    private int x = 0;
    private int y = 0;
 
    private final Random random = new Random();

    /**
     * Private method to move the current position, that's to say the drunkard moves
     *
     * @param dx the distance he moves in the x direction
     * @param dy the distance he moves in the y direction
     */
    private void move(int dx, int dy) {
	// TODO you need to implement this
	this.x += dx;
	this.y += dy;
		
    }

    /**
     * Perform a random walk of m steps
     *
     * @param m the number of steps the drunkard takes
     */
    private void randomWalk(int m) {
        // TO BE IMPLEMENTED
	for(int i = 0; i < m; i++)
		randomMove();  //for each step this method will calculate possible moves randomly

    }

    /**
     * Private method to generate a random move according to the rules of the situation.
     * That's to say, moves can be (+-1, 0) or (0, +-1).
     */
    private void randomMove() //method implementing random generation of x, y co-ordinates
    {
        boolean ns = random.nextBoolean();
        int step = random.nextBoolean() ? 1 : -1;
        move(ns ? step : 0, ns ? 0 : step);
    }

    /**
     * Method to compute the distance from the origin (the lamp-post where the drunkard starts) to his current position.
     *
     * @return the (Euclidean) distance from the origin to the current position.
     */
    public double distance() {
        // TO BE IMPLEMENTED
        //System.out.println("Drunk man's final position on the x-y plane will be: ");
	//System.out.println("Final co-ordinates of x and y : "+ this.x+" "+this.y);
	double distance = Math.sqrt(Math.pow(0-x, 2) + Math.pow(0-y, 2));  //euclidean's formula to calculate distance
        return distance;
    }

    /**
     * Perform multiple random walk experiments, returning the mean distance.
     *
     * @param m the number of steps for each experiment
     * @param n the number of experiments to run
     * @return the mean distance
     */
    public static double randomWalkMulti(int m, int n) {
        double totalDistance = 0;
        for (int i = 0; i < n; i++) {
            RandomWalk walk = new RandomWalk();
            walk.randomWalk(m);
            totalDistance = totalDistance + walk.distance();
	    System.out.println(Math.sqrt(totalDistance));
        }
        return totalDistance / n;
    }

    public static void main(String[] args) {
        if (args.length == 0)
            throw new RuntimeException("Syntax: RandomWalk steps [experiments]");
        int m = Integer.parseInt(args[0]);
        int n = 10;
        if (args.length > 1) n = Integer.parseInt(args[1]);
	System.out.println("For "+ m + "steps over 10 experiments\n");
        double meanDistance = randomWalkMulti(m, n);
        System.out.println("Mean Distance: " + meanDistance);
    }

}
