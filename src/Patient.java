public class Patient {
   String id;
   int diagnosis;
   double[] attributes;

   public Patient(String[] data) {
       this.id= data[0];
       this.diagnosis = "M".equals(data[1]) ? 1:0;
       this.attributes = new double[data.length - 2];
       for ( int i = 0; i< attributes.length; i++){
          attributes[i] = Double.parseDouble(data[i + 2]);
       }

   }
}


