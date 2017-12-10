package com.ironman.ma.Algorithms.PandC;

import java.util.Scanner;

/**
 * Created by 127.0.0.1.ma on 20/08/17.
 */
public class PrintSpaceCombo {
    static void printCombo(String orignal, String carry, String str_rem) {
//        ArrayList<String> local_combo=new ArrayList<String>();
//        System.out.println("Carry [" + carry + "], StrRem [" + str_rem + "]");
        if (str_rem.length() == 1) {
            System.out.println(carry.length()+1+", "+carry + str_rem);
        } else {
                String newCarry=carry+str_rem.substring(0, 1);
                printCombo(orignal,
                        newCarry,
                        str_rem.substring(1));
                printCombo(orignal,
                        newCarry + " ",
                        str_rem.substring(1));


//            ArrayList<String> local_combo_derv=printCombo(str.substring(1));
//            for (String s:local_combo_derv){
//                local_combo.add(str.substring(0,1)+" "+s);
//                local_combo.add(str.substring(0,1)+s);
//            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String testcase = input.nextLine();
        printCombo(testcase, "", testcase);
        input.close();
    }
}
