
import java.util.Scanner;

/**
 *
 * @author breno
 */
public class ParidadeDupla {

    Scanner leia;

    public void começaParidade() {

        leia = new Scanner(System.in);
        String cadeia_bits;

        System.out.print("Insira uma cadeia de bits: ");
        cadeia_bits = leia.next();
        String[] cadeias = new String[cadeia_bits.length() / 7];

        if (cadeia_bits.length() % 2 != 0) {
            System.out.print("Insira uma cadeia com número PAR de bits =D");
        } else {
            int x = 0;
            for (int i = 0; i < cadeias.length; i++) {
                cadeias[i] = cadeia_bits.substring(x, x + 7);
                x += 7;
            }
        }

        System.out.println("");
        verificaBits(cadeias);
    }

    private void verificaBits(String[] cadeias) {

        String[] new_cadeias = new String[cadeias.length + 1];
        String new_cadeia_bits = "";

        for (int i = 0; i < new_cadeias.length; i++) {
            int qtd_1 = 0;

            if (i < 4) {

                for (int j = 0; j < cadeias[i].length(); j++) {
                    if (cadeias[i].charAt(j) == '1') {
                        qtd_1++;
                    }

                    if (qtd_1 % 2 == 0) {
                        new_cadeias[i] = cadeias[i].concat("0");
                    } else {
                        new_cadeias[i] = cadeias[i].concat("1");
                    }
                }

                for (int j = 0; j < new_cadeias[i].length(); j++) {
                    if (j < new_cadeias[i].length() - 1) {
                        System.out.print(new_cadeias[i].charAt(j));
                    } else {
                        System.out.print("|" + new_cadeias[i].charAt(j) + "\n");
                    }
                }

            } else {

                new_cadeias[i] = "";

                for (int j = 0; j < 8; j++) {
                    int[] qtd_1_1 = new int[8];

                    for (int k = 0; k < cadeias.length; k++) {
                        if (new_cadeias[k].charAt(j) == '1') {
                            qtd_1_1[j]++;
                        }
                    }

                    if (qtd_1_1[j] % 2 != 0) {
                        new_cadeias[i] = new_cadeias[i].concat("1");
                    } else {
                        new_cadeias[i] = new_cadeias[i].concat("0");
                    }
                }
                System.out.println("---------");
                for (int j = 0; j < new_cadeias[i].length(); j++) {
                    if (j < new_cadeias[i].length() - 1) {
                        System.out.print(new_cadeias[i].charAt(j));
                    } else {
                        System.out.print("|" + new_cadeias[i].charAt(j) + "\n");
                    }
                }

            }
        }
        
        for (int i = 0; i < new_cadeias.length; i++) {
            new_cadeia_bits = new_cadeia_bits.concat(new_cadeias[i] + " ");
        }
        
        System.out.println("\n\n Nova cadeia com os bits de paridade: " + new_cadeia_bits);
        
        
    }
}
