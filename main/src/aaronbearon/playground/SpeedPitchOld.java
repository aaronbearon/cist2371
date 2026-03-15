package aaronbearon.playground;

import java.util.Scanner;

public class SpeedPitchOld {
    public static void main(String[] args) {
        double semitones = 0.0;
        double speed = 100.0;
        while (true) {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter semitones: ");
            double newSemitones = input.nextDouble();
            newSemitones = Math.round(newSemitones * 10000) / 10000.0;

            if (newSemitones <= -48.0) {
                newSemitones = -48.0;
            }

            if (newSemitones >= 48.0) {
                newSemitones = 48.0;
            }

            double semDif = ((double) (((int) (newSemitones * 10000)) - ((int) (semitones * 10000)))) / 10000.0;
            System.out.println("Semitones: " + newSemitones);
            double newSpeed = Math.round(Math.round(1000000000 * Math.pow(2, (semDif / 12.0))) / 10.0) / 1000000.0;
            speed = Math.round(Math.round((newSpeed * speed) * 100000) / 10.0) / 1000000.0;
            System.out.println("Speed percentage: " + speed);
            semitones = newSemitones;
        }
    }
}
