/*
Names


Start Date: 2023-June-07
 */

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        List<Patient> patients = new ArrayList<>();
        try {
            CSVReader reader = new CSVReader(new FileReader("src\\data.csv"));
            List<String[]> lines = reader.readAll();

            for (int i = 1; i < lines.size(); i++) {
                patients.add(new Patient(lines.get(i)));
            }
            FileWriter writer = new FileWriter("output.csv");

            writer.append("N");
            writer.append(",");
            writer.append("k");
            writer.append(",");
            writer.append("RunningTime");
            writer.append(",");
            writer.append("Accuracy");
            writer.append("\n");


            int[] Ns = {50, 150, 250, 350, 450};
            int[] ks = {3, 5, 7};
            int runs = 5;
            for (int N : Ns) {
                for (int k : ks) {
                    double totalRunningTime = 0;
                    double totalAccuracy = 0;

                    for (int i = 0; i < runs; i++) {
                        Collections.shuffle(patients);

                        List<Patient> NPatients = patients.subList(0, N);
                        List<Patient> remainingPatients = new ArrayList<>(patients.subList(N, patients.size()));
                        Collections.shuffle(remainingPatients);

                        List<Patient> testPatients = remainingPatients.subList(0, N / 4);
                        long startTime = System.nanoTime();
                        double accuracy = trainAndTest(NPatients, testPatients, k);
                        long endTime = System.nanoTime();
                        double runningTime = (endTime - startTime) / 1e6;
                        totalRunningTime += runningTime;
                        totalAccuracy += accuracy;
                    }

                    double averageRunningTime = totalRunningTime / runs;
                    double averageAccuracy = totalAccuracy / runs;

                    System.out.println("Average running time for N=" + N + ", k=" + k + ": " + averageRunningTime + " nanoseconds");
                    System.out.println("Average accuracy for N=" + N + ", k=" + k + ": " + averageAccuracy + "%");

                    writer.append(Integer.toString(N));
                    writer.append(",");
                    writer.append(Integer.toString(k));
                    writer.append(",");
                    writer.append(String.format("%.4f", averageRunningTime));
                    writer.append(",");
                    writer.append(String.format("%.4f", averageAccuracy));
                    writer.append("\n");
                }
            }
            writer.flush();
            writer.close();


        } catch (IOException | com.opencsv.exceptions.CsvException e) {
            e.printStackTrace();

        }


    }

    public static double trainAndTest(List<Patient> trainingSet, List<Patient> testSet, int k) {
        BallTree ballTree = new BallTree();
        ballTree.root = ballTree.buildTree(trainingSet, 0);

        int correctPredictions = 0;
        for (Patient patient : testSet) {
            int prediction = ballTree.predict(patient, k);
            if (prediction == patient.diagnosis) {
                correctPredictions++;
            }
        }


        double accuracy = 100.0 * correctPredictions / testSet.size();
        System.out.println("N=" + trainingSet.size() + ", k=" + k + ": Accuracy = " + accuracy + "%");

        return accuracy;

    }
}
