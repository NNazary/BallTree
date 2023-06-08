import java.util.Comparator;
import java.util.List;

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


    public List<Patient> searchTree(Patient patient, int k) {


        return null; // for now return null
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


}
