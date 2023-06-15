public class Patient {
   String id;
   int diagnosis;
   double[] attributes;

   public Patient(String[] data) {
       this.id= data[0];
       this.diagnosis = "M".equals(data[1]) ? 1:0;
       this.attributes = new double[10];
       for ( int i = 2; i< 12; i++){
          attributes[i-2] = Double.parseDouble(data[i]);
       }

   }
}


