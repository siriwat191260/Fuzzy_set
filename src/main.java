public class main {
    public static void main(String[] args) {
        String fabric_type = "";
        for (int i=0;i<3;i++){
            if(i==0){
                fabric_type="Silk";
            }else if(i==1){
                fabric_type="Woolen";
            }else if(i==2){
                fabric_type="Cotton";
            }
            for (double j=1;j<=100;j++){
                for (double k=1;k<=30;k++){
                    fuzzy f = new fuzzy(fabric_type,j,k);
                }
            }
        }
    }
}
