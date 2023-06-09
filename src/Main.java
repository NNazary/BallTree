/*
Names


Start Date: 2023-June-07
 */

import com.opencsv.CSVReader;

import java.io.FileReader;
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

            int[] Ns = {50, 150, 250, 350, 450};
            int[] ks = {3, 5, 7};
            for (int N : Ns) {
                for (int k : ks) {
                    Collections.shuffle(patients);

                    List<Patient> NPatients = patients.subList(0, N);
                    List<Patient> testPatients = patients.subList(N, N + N / 4);

                    trainAndTest(NPatients, testPatients, k);
                }
            }


        } catch (IOException | com.opencsv.exceptions.CsvException e) {
            e.printStackTrace();

        }


    }

    public static void trainAndTest(List<Patient> trainingSet, List<Patient> testSet, int k) {
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


    }
}
