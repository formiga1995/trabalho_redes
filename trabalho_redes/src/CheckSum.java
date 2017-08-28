
import java.util.Scanner;

public class CheckSum {

    private static int[] sequencia = {1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 1, 1};  //SEQUENCIA QUE SERÁ ENVIADA
    private static int[] primeiroSeg = new int[sequencia.length / 2];   //PRIMEIRO SEGMENTO DA SEQUENCIA A SER ENVIADA
    private static int[] segundoSeg = new int[sequencia.length / 2];    //SEGUNDO SEGMENTO 
    private static int[] checkSum = new int[segundoSeg.length];      //CHECKSUM 
    private static int[] vaiUm = new int[segundoSeg.length];		   //USADO PARA A AUXILIAR A SOMA DE BIT'S
    private static int[] receptor = new int[segundoSeg.length];	   //VERIFICA SE A MENSAGEM RECEBIDA NO FINAL SERA UMA SEQUENCIA DE '0' OU '1';

    public void comecaPrograma() {
        Scanner leia = new Scanner(System.in);

        //PRIMEIRO É DIVIDIDO O BLOCO DE DADOS (TIPO INT [] 'SEQUENCIA')EM 'K' SEGMENTOS DE 'N' BITS
        //CRIANDO O PRIMEIRO SEGMENTO 'A'
        for (int i = 0; i < sequencia.length / 2; i++) {
            primeiroSeg[i] = sequencia[i];
        }

        //CRIANDO O SEGUNDO SEGMENTO 'B'
        int controle = 0;
        for (int i = sequencia.length / 2; i < sequencia.length; i++) {
            segundoSeg[controle] = sequencia[i];
            controle += 1;
        }

        //IMPRIMINDO OS SEGUIMENTOS 'A' E 'B' PARA CONFERIR
        System.out.println("Segmento 'A': \n");
        mostraSegmento(primeiroSeg);
        System.out.println("Segmento 'B': \n");
        mostraSegmento(segundoSeg);

        //CRIANDO O CHECK_SUM
        checkSum_Emissor(primeiroSeg, segundoSeg);

        System.out.println("CheckSum: \n");
        mostraSegmento(checkSum);

        //APLICANDO COMPLEMENTO DE 1 (ALTERANDO BIT 0 PARA 1 E VICE-VERSA)
        for (int i = 0; i < checkSum.length; i++) {
            if (checkSum[i] == 0) {
                checkSum[i] = 1;
            } else if (checkSum[i] == 1) {
                checkSum[i] = 0;
            }
        }

        System.out.println("CheckSum Apos Complemento de 1: \n");
        mostraSegmento(checkSum);

        //ZERANDO 'vaiUm' PARA SER USADO NA SOMA DOS BITS DO RECPTOR
        for (int i = 0; i < vaiUm.length; i++) {
            vaiUm[i] = 0;
        }

        //RECEPTOR - SOMANDO OS 3 SEGMENTOS
        msgReceptor(checkSum, primeiroSeg, segundoSeg);

        System.out.println("Receptor: ");
        mostraSegmento(receptor);

        //APLICANDO COMPLEMENTO DE 1
        for (int i = 0; i < receptor.length; i++) {
            if (receptor[i] == 0) {
                receptor[i] = 1;
            } else if (receptor[i] == 1) {
                receptor[i] = 0;
            }
        }

        System.out.println("Receptor Apos Complemento: ");
        mostraSegmento(receptor);

        //SE RESULTADO = 0 RECEBE A MENSAGEM, SE FOR = 1 NÃO RECEBE;
        System.out.println("\n" + verifica());
    }

    private void mostraSegmento(int[] segmento) {
        for (int i = 0; i < segmento.length; i++) {
            System.out.println(segmento[i]);
        }
    }

    private void checkSum_Emissor(int[] primeiroSeg, int[] segundoSeg) {
        for (int i = (segundoSeg.length) - 1; i >= 0; i--) {
            if (vaiUm[i] == 1) {
                if (((primeiroSeg[i] == 0) && (segundoSeg[i] == 1)) || ((primeiroSeg[i] == 1) && (segundoSeg[i] == 0))) {
                    checkSum[i] = 0;
                    vaiUm[i - 1] = 1;
                } else if ((primeiroSeg[i] == 1) && (segundoSeg[i] == 1)) {
                    checkSum[i] = 1;
                    vaiUm[i - 1] = 1;
                } else if ((primeiroSeg[i] == 0) && (segundoSeg[i] == 0)) {
                    checkSum[i] = 1;
                }

            } else if (((primeiroSeg[i] == 0) && (segundoSeg[i] == 1)) || ((primeiroSeg[i] == 1) && (segundoSeg[i] == 0))) {
                checkSum[i] = 1;
            } else if ((primeiroSeg[i] == 1) && (segundoSeg[i] == 1)) {
                checkSum[i] = 0;
                vaiUm[i - 1] = 1;
            }
        }
    }

    private void msgReceptor(int[] checkSum, int[] primeiroSeg, int[] segundoSeg) {
        for (int i = (receptor.length) - 1; i >= 0; i--) {
            if (vaiUm[i] == 1) {
                if (((primeiroSeg[i] == 0) && (segundoSeg[i] == 0) && (checkSum[i] == 1))
                        || ((primeiroSeg[i] == 0) && (segundoSeg[i] == 1) && (checkSum[i] == 0))
                        || ((primeiroSeg[i] == 1) && (segundoSeg[i] == 0) && (checkSum[i] == 0))) {
                    receptor[i] = 0;
                    vaiUm[i - 1] = 1;
                } else if (((primeiroSeg[i] == 0) && (segundoSeg[i] == 1) && (checkSum[i] == 1))
                        || ((primeiroSeg[i] == 1) && (segundoSeg[i] == 0) && (checkSum[i] == 1))
                        || ((primeiroSeg[i] == 1) && (segundoSeg[i] == 1) && (checkSum[i] == 0))) {
                    receptor[i] = 1;
                    vaiUm[i - 1] = 1;
                } else if ((primeiroSeg[i] == 0) && (segundoSeg[i] == 0) && (checkSum[i] == 0)) {
                    receptor[i] = 1;
                }

            } else if (((primeiroSeg[i] == 0) && (segundoSeg[i] == 0) && (checkSum[i] == 1))
                    || ((primeiroSeg[i] == 0) && (segundoSeg[i] == 1) && (checkSum[i] == 0))
                    || ((primeiroSeg[i] == 1) && (segundoSeg[i] == 0) && (checkSum[i] == 0))) {
                receptor[i] = 1;
            } else if (((primeiroSeg[i] == 0) && (segundoSeg[i] == 1) && (checkSum[i] == 1))
                    || ((primeiroSeg[i] == 1) && (segundoSeg[i] == 0) && (checkSum[i] == 1))
                    || ((primeiroSeg[i] == 1) && (segundoSeg[i] == 1) && (checkSum[i] == 0))) {
                receptor[i] = 0;
                vaiUm[i - 1] = 1;
            } else if (((primeiroSeg[i] == 1) && (segundoSeg[i] == 1) && (checkSum[i] == 1))) {
                receptor[i] = 1;
                vaiUm[i - 1] = 1;
            }
        }
    }

    private String verifica() {
        boolean b = true;
        String msg;

        for (int i = 0; i < receptor.length; i++) {
            if (receptor[i] == 1) {
                b = false;
            }
        }

        if (b == false) {
            msg = "Não Recebido!";
        } else {
            msg = "Recebido com sucesso!";
        }

        return (msg);
    }
}
