import java.util.ArrayList;

public class fuzzy {
    String input1; // ประเภทผ้า
    Double input2; // ความสกปรก
    Double input3; // น้ำหนัก
    double[] member_type = {0, 0, 0};
    double[] member_dirt = {0, 0, 0};
    double[] member_mass = {0, 0, 0};
//    String[] type_of_fabric = {"Silk", "Woolen", "Cotton"}; // [Silk,Woolen,Cotton]
//    String[] degree_of_dirt = {"Low", "Medium", "High"}; // [Low,Medium,High]
    Double min;
    Double max_short;
    Double max_medium;
    Double max_long;
    //Mode
    ArrayList<Double> short_time = new ArrayList<>();
    ArrayList<Double> medium_time = new ArrayList<>();
    ArrayList<Double> long_time = new ArrayList<>();
    //Union
    ArrayList<Double> UnionMax = new ArrayList<>();
    //Output
    int output;

    public fuzzy(String a, Double b, Double c) {
        input1 = a;
        input2 = b;
        input3 = c;
        Fuzzifier();
        rule();
        Union(short_time,medium_time,long_time);
        Defuzzification(UnionMax);
    }


    public void Fuzzifier() {
        //Type
        switch (input1) {
            case "Silk" -> {
                member_type[0] = 1;
                member_type[1] = 0;
                member_type[2] = 0;
            }
            case "Woolen" -> {
                member_type[0] = 0;
                member_type[1] = 1;
                member_type[2] = 0;
            }
            case "Cotton" -> {
                member_type[0] = 0;
                member_type[1] = 0;
                member_type[2] = 1;
            }
        }
        // Dirt
        //Low dirt
        if (input2 >= 0 && input2 <= 40) {
            if (input2 <= 20) {
                member_dirt[0] = (input2) / 10;
            } else if (input2 <= 40) {
                member_dirt[0] = (40 - input2) / 10;
            }
        }
        //Medium dirt
        if (input2 >= 40 && input2 <= 70) {
            if (input2 <= 50) {
                member_dirt[1] = (50 - input2) / 10;
            } else if (input2 <= 60) {
                member_dirt[1] = (60 - input2) / 10;
            } else if (input2 <= 70) {
                member_dirt[1] = (70 - input2) / 10;
            }
            //High dirt
            if (input3 >= 70 && input3 <= 100) {
                if (input3 <= 80) {
                    member_dirt[2] = (80 - input3) / 10;
                } else if (input3 <= 90) {
                    member_dirt[2] = (90 - input3) / 10;
                } else if (input3 <= 100) {
                    member_dirt[2] = (100 - input3) / 10;
                }
            }

        }

        //Mass below 10 kg
        if (input3 >= 0 && input3 <= 10) {
            member_mass[0] = input3 / 10;
        }
        //Mass 10 to 20 kg
        if (input3 >= 10 && input3 <= 20) {
            member_mass[1] = (20 - input3) / 10;
        }
        //Mass 20 to 30 kg
        if (input3 >= 20 && input3 <= 30) {
            member_mass[2] = (30 - input3) / 10;
        }

    }

    public void rule() {
        // 27 rules
        // rule #1 input[Silk,Low,below 10 kg]
        if ((member_type[0] > 0) && (member_dirt[0] >= 0) && (member_mass[0] > 0)) {
            min = Math.min(member_dirt[0], member_mass[0]);
            short_time.add(min);
        }
        // rule #2 input[Silk,Low,10 kg to 20 kg]
        if ((member_type[0] > 0) && (member_dirt[0] > 0) && (member_mass[1] > 0)) {
            min = Math.min(member_dirt[0], member_mass[1]);
            short_time.add(min);
        }
        // rule #3 input[Silk,Low,above 20 kg]
        if ((member_type[0] > 0) && (member_dirt[0] > 0) && (member_mass[2] > 0)) {
            min = Math.min(member_dirt[0], member_mass[2]);
            short_time.add(min);
        }
        // rule #4 input[Silk,Medium,below 10 kg]
        if ((member_type[0] > 0) && (member_dirt[1] > 0) && (member_mass[0] > 0)) {
            min = (Math.min(member_dirt[1], member_mass[0]));
            short_time.add(min);
        }
        // rule #5 input[Silk,Medium,10 kg to 20 kg]
        if ((member_type[0] > 0) && (member_dirt[1] > 0) && (member_mass[1] > 0)) {
            min = (Math.min(member_dirt[1], member_mass[1]));
            medium_time.add(min);
        }
        // rule #6 input[Silk,Medium,above 20 kg]
        if ((member_type[0] > 0) && (member_dirt[1] > 0) && (member_mass[2] > 0)) {
            min = (Math.min(member_dirt[1], member_mass[2]));
            medium_time.add(min);
        }
        // rule #7 input[Silk,High,below 10 kg]
        if ((member_type[0] > 0) && (member_dirt[2] > 0) && (member_mass[0] > 0)) {
            min = (Math.min(member_dirt[2], member_mass[0]));
            short_time.add(min);
        }
        // rule #8 input[Silk,High,10 kg to 20 kg]
       if ((member_type[0] > 0) && (member_dirt[2] > 0) && (member_mass[1] > 0)) {
            min = (Math.min(member_dirt[2], member_mass[1]));
            medium_time.add(min);
        }
        // rule #9 input[Silk,High,above 20 kg]
        if ((member_type[0] > 0) && (member_dirt[2] > 0) && (member_mass[2] > 0)) {
            min = (Math.min(member_dirt[2], member_mass[2]));
            long_time.add(min);
        }
        // rule #10 input[Woolen,Low,below 10 kg]
        if ((member_type[1] > 0) && (member_dirt[0] > 0) && (member_mass[0] > 0)) {
            min = (Math.min(member_dirt[0], member_mass[0]));
            short_time.add(min);
        }
        // rule #11 input[Woolen,Low,10 kg to 20 kg]
        if ((member_type[1] > 0) && (member_dirt[0] > 0) && (member_mass[1] > 0)) {
            min = (Math.min(member_dirt[0], member_mass[1]));
            short_time.add(min);
        }
        // rule #12 input[Woolen,Low,above 20 kg]
        if ((member_type[1] > 0) && (member_dirt[0] > 0) && (member_mass[2] > 0)) {
            min = (Math.min(member_dirt[0], member_mass[2]));
            medium_time.add(min);
        }
        // rule #13 input[Woolen,Medium,below 10 kg]
        if ((member_type[1] > 0) && (member_dirt[1] > 0) && (member_mass[0] > 0)) {
            min = (Math.min(member_dirt[1], member_mass[0]));
            short_time.add(min);
        }
        // rule #14 input[Woolen,Medium,10 kg to 20 kg]
        if ((member_type[1] > 0) && (member_dirt[1] > 0) && (member_mass[1] > 0)) {
            min = (Math.min(member_dirt[1], member_mass[1]));
            medium_time.add(min);
        }
        // rule #15 input[Woolen,Medium,above 20 kg]
        if ((member_type[1] > 0) && (member_dirt[1] > 0) && (member_mass[2] > 0)) {
            min = (Math.min(member_dirt[1], member_mass[2]));
            medium_time.add(min);
        }
        // rule #16 input[Woolen,High,below 10 kg]
        if ((member_type[1] > 0) && (member_dirt[2] > 0) && (member_mass[0] > 0)) {
            min = (Math.min(member_dirt[2], member_mass[0]));
            medium_time.add(min);
        }
        // rule #17 input[Woolen,High,10 kg to 20 kg]
        if ((member_type[1] > 0) && (member_dirt[2] > 0) && (member_mass[1] > 0)) {
            min = (Math.min(member_dirt[2], member_mass[1]));
            medium_time.add(min);
        }
        // rule #18 input[Woolen,High,above 20 kg]
        if ((member_type[1] > 0) && (member_dirt[2] > 0) && (member_mass[2] > 0)) {
            min = (Math.min(member_dirt[2], member_mass[2]));
            long_time.add(min);
        }
        // rule #19 input[Cotton,Low,below 10 kg]
        if ((member_type[2] > 0) && (member_dirt[0] > 0) && (member_mass[0] > 0)) {
            min = (Math.min(member_dirt[0], member_mass[0]));
            short_time.add(min);
        }
        // rule #20 input[Cotton,Low,10 kg to 20 kg]
        if ((member_type[2] > 0) && (member_dirt[0] > 0) && (member_mass[1] > 0)) {
            min = (Math.min(member_dirt[0], member_mass[1]));
            short_time.add(min);
        }
        // rule #21 input[Cotton,Low,above 20 kg]
        if ((member_type[2] > 0) && (member_dirt[0] > 0) && (member_mass[2] > 0)) {
            min = (Math.min(member_dirt[0], member_mass[2]));
            medium_time.add(min);
        }
        // rule #22 input[Cotton,Medium,below 10 kg]
        if ((member_type[2] > 0) && (member_dirt[1] > 0) && (member_mass[0] > 0)) {
            min = (Math.min(member_dirt[1], member_mass[0]));
            short_time.add(min);
        }
        // rule #23 input[Cotton,Medium,10 kg to 15 kg]
        if ((member_type[2] > 0) && (member_dirt[1] > 0) && (member_mass[1] > 0)) {
            min = (Math.min(member_dirt[1], member_mass[1]));
            medium_time.add(min);
        }
        // rule #24 input[Cotton,Medium,above 15 kg]
        if ((member_type[2] > 0) && (member_dirt[1] > 0) && (member_mass[2] > 0)) {
            min = (Math.min(member_dirt[1], member_mass[2]));
            long_time.add(min);
        }
        // rule #25 input[Cotton,High,below 10 kg]
        if ((member_type[2] > 0) && (member_dirt[2] > 0) && (member_mass[0] > 0)) {
            min = (Math.min(member_dirt[2], member_mass[0]));
            medium_time.add(min);
        }
        // rule #26 input[Cotton,High,10 kg to 15 kg]
        if ((member_type[2] > 0) && (member_dirt[2] > 0) && (member_mass[1] > 0)) {
            min = (Math.min(member_dirt[2], member_mass[1]));
            medium_time.add(min);
        }
        // rule #27 input[Cotton,High,above 15 kg]
        if ((member_type[2] > 0) && (member_dirt[2] > 0) && (member_mass[2] > 0)) {
            min = (Math.min(member_dirt[2], member_mass[2]));
            long_time.add(min);
        }
    }

    public void Union(ArrayList a, ArrayList b, ArrayList c) {
        max_short = Max_list(a);
        max_medium = Max_list(b);
        max_long = Max_list(c);
        UnionMax.add(max_short);
        UnionMax.add(max_medium);
        UnionMax.add(max_long);

    }

    public void Defuzzification(ArrayList a) {
        output = index_washing_time(a);
        String defuz_output ="";
        if(output == 0){
            defuz_output = "90 minutes";
        }else if(output == 1){
            defuz_output = "120 minutes";
        }else if(output == 2){
            defuz_output = "180 minutes";
        }
        System.out.println(defuz_output);

    }

    public Double Max_list(ArrayList a) {
        double maximum = 0;
        for (int i=0;i<a.size();i++){
            if (maximum < (double)a.get(i)){
                maximum = (double)a.get(i);
            }
        }
        return maximum;
    }
    
    public int index_washing_time (ArrayList a){
        double maximum = 0;
        int temp = 0;
        for (int i=0;i<a.size();i++){
            if (maximum < (double)a.get(i)){
                maximum = (double)a.get(i);
                temp = i;
            }

        }
        return temp;
    }
}








        // Double mass_of_fabrics = 0.0; // [below 10 kg , 10 kg to 15 kg , above 15 kg ]
        // range of type fabric by 0 1 2 replace silk,woolen,cotton
        //int[] range_type_fabric = {0,1,2};
        // range of dirt 0-100 by low(0-40) medium(40-60) high(70-100)
        //int[] low_dirt = {0,10,17,19,27,34};
        //int[] medium_dirt = {41,43,48,52,55,59};
        //int[] high_dirt = {72,77,80,87,91,98};
        // range of mass by [below 10 kg ,10 kg to 15 kg ,above 15 kg]













