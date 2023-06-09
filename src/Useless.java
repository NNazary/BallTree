public class Useless {

    /*

     List<Patient> patients = new ArrayList<>();

        try {
            File file = new File("C:\\Users\\najmu\\Desktop\\COEN352\\Assignment3BallTree\\src\\PList.csv");
            Scanner scanner = new Scanner(file);

            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }


            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] columns = line.split(",");

                int id = Integer.parseInt(columns[0]);
                boolean diagnosis = columns[1].equals("M") ? true : false;
                double[] attributes = new double[10];

                for (int i = 0; i < 10; i++) {
                    attributes[i] = Double.parseDouble(columns[i + 2]);
                }
                patients.add(new Patient(id, diagnosis, attributes));


            }
            scanner.close();

        } catch (FileNotFoundException f) {
            System.out.println("An error occurred.");
            f.printStackTrace();
        }

        for (Patient patient : patients) {
            System.out.println(patients);
        }

Collections.shuffle(patients);
        List<Patient> fiftyPatientsTraining = patients.subList(0, 50);
        List<Patient> testSet= patients.subList(50, 50 +13);


        BallTree ballTree = new BallTree();
        ballTree.root = ballTree.buildTree(fiftyPatientsTraining, 0);

        int correctPrediction = 0;
        for(Patient testPatient: testSet) {
            int predictedDiagnosis = ballTree.predict(testPatient, 3);
            if (predictedDiagnosis == testPatient.diagnosis) {
                correctPrediction++;
            }
//        Collections.shuffle(patients);
//        List<Patient> oneFiftyPatients = patients.subList(0, 150);
//        Collections.shuffle(patients);
//        List<Patient> twoFiftyPatients = patients.subList(0, 250);
//        Collections.shuffle(patients);
//        List<Patient> threeFiftyPatients = patients.subList(0, 350);
//        Collections.shuffle(patients);
//        List<Patient> fourFiftyPatients = patients.subList(0, 450);
//        Collections.shuffle(patients);

//        for(Patient patient: patients) {
//            System.out.println("Patient ID: " + patient.id);
//            System.out.println("Patient Diagnosis: " + patient.diagnosis);
//            System.out.println("Patient Attributes: " + Arrays.toString(patient.attributes)); }










     */
}
