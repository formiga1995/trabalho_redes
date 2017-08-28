
import java.util.Scanner;

/**
 *
 * @author Breno Henrique
 */
public class Main {

    private int escolha = 0;

    public static void main(String[] args) {

        Main main = new Main();;
        main.mostraMenu();

    }

    private void mostraMenu() {

        Scanner leia = new Scanner(System.in);

        System.out.println("||------------- MENU -----------------||");
        System.out.println("||                                    ||");
        System.out.println("||0 - Sair                            ||");
        System.out.println("||1 - CheckSum                        ||");
        System.out.println("||2 - CRC                             ||");
        System.out.println("||3 - Hamming                         ||");
        System.out.println("||4 - Paridade Dupla                  ||");
        System.out.println("||                                    ||");
        System.out.println("||------------------------------------||\n");

        System.out.print("Escolha o método a ser realizado: ");
        escolha = leia.nextInt();

        switch (escolha) {
            case 0:
                System.exit(0);
                break;
            case 1:
                CheckSum checksum = new CheckSum();
                checksum.comecaPrograma();
                break;
            case 2:
                break;

            case 3:
                Hamming hamming = new Hamming();
                hamming.Hamming();
                break;
            case 4:
                ParidadeDupla paridade = new ParidadeDupla();
                paridade.começaParidade();
                break;

        }
    }

}
