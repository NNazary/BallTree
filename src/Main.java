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



        } catch (IOException | com.opencsv.exceptions.CsvException e) {
            e.printStackTrace();

        }
        Collections.shuffle(patients);
        List<Patient> fiftyPatients = patients.subList(0, 50);
        Collections.shuffle(patients);
        List<Patient> oneFiftyPatients = patients.subList(0, 150);
        Collections.shuffle(patients);
        List<Patient> twoFiftyPatients = patients.subList(0, 250);
        Collections.shuffle(patients);
        List<Patient> threeFiftyPatients = patients.subList(0, 350);
        Collections.shuffle(patients);
        List<Patient> fourFiftyPatients = patients.subList(0, 450);
        Collections.shuffle(patients);

//        for(Patient patient: patients) {
//            System.out.println("Patient ID: " + patient.id);
//            System.out.println("Patient Diagnosis: " + patient.diagnosis);
//            System.out.println("Patient Attributes: " + Arrays.toString(patient.attributes)); }

        }


}

