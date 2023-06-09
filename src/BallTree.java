import java.util.*;

public class BallTree {


    BallTreeNode root;
    public BallTreeNode buildTree(List<Patient> patients, int depth){
        if(patients.size() == 0){
            return null;
        }
        if (patients.size() == 1){
            return new BallTreeNode(patients.get(0));
        }

        //This to sort the patients based on the 10 attributes at the current depth
        int attributeIndex = depth % patients.get(0).attributes.length;
        patients.sort(Comparator.comparingDouble(patient-> patient.attributes[attributeIndex]));


        //this to split the patients into 2 groups and create the children
        int midIndex = patients.size() /2;
        BallTreeNode leftChild = buildTree(patients.subList(0, midIndex), depth+1 );
        BallTreeNode rightChild = buildTree(patients.subList(midIndex + 1, patients.size()), depth +1);

        //this is to create the root and set its children

        BallTreeNode root= new BallTreeNode(patients.get(midIndex));
        root.leftChild = leftChild;
        root.rightChild = rightChild;




        return root;
    }


    public List<Patient> searchTree(Patient queryPatient, int k) {
        //a priority queue for the k nearest neighbours
        //the comparator compare the patient based on their  distance to the given patient
        PriorityQueue<Patient> nearestNeighbours = new PriorityQueue<>(Comparator.comparingDouble(p-> euclideanDistance(p.attributes, queryPatient.attributes)));

        // priority queue for the nodes to visit and compare based on their distance of the patient to the ggiven patient
        PriorityQueue<BallTreeNode> nodesToVisit = new PriorityQueue<>(Comparator.comparingDouble(node -> euclideanDistance(node.patient.attributes, queryPatient.attributes)));


        nodesToVisit.add(root);

        while(!nodesToVisit.isEmpty() && (nearestNeighbours.size() < k || euclideanDistance(nodesToVisit.peek().patient.attributes, queryPatient.attributes) < euclideanDistance(Objects.requireNonNull(nearestNeighbours.peek()).attributes, queryPatient.attributes))) {
            BallTreeNode node = nodesToVisit.poll();

            assert node != null;
            nearestNeighbours.add(node.patient);
            if(nearestNeighbours.size() > k) {
                nearestNeighbours.poll();
            }
            if(node.leftChild != null){
                nodesToVisit.add(node.leftChild);
            }
            if(node.rightChild!= null) {
                nodesToVisit.add(node.rightChild);
            }




            }
        return new ArrayList<>(nearestNeighbours);
    }

    public int predict(Patient patient, int k) {

        List<Patient> neighbours= searchTree(patient, k);



        int malignantCount = 0;
        int benignCount = 0;

        for(Patient neighbour : neighbours) {
            if (neighbour.diagnosis == 1) {
                malignantCount++;
            }
            else {
                benignCount++;
            }
        }
        return malignantCount> benignCount ? 1:0;
    }
    private double euclideanDistance(double[] attributes1, double[] attributes2) {
        double sum = 0;
        for (int i = 0; i < attributes1.length; i++) {
            sum += Math.pow(attributes1[i] - attributes2[i], 2);
        }
        return Math.sqrt(sum);


    }}
