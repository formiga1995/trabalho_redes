
import java.util.Scanner;

public class Hamming {
public void Hamming (){
     Scanner leia = new Scanner(System.in);
        System.out.print("Entre com o número de bits(m) para o cálculo do Hamming: ");
        int m = leia.nextInt();
        int r = 0;
        int [] Mensagem = new int [m];  //Mensagem Original
        for(int i = 0;i<m;i++){
            System.out.println("Entre com o bit "+(m-i)+":");
            Mensagem[m-i-1] = leia.nextInt();
        }
        System.out.print("Mensagem lida: ");
        for(int i=0;i<m;i++){
            System.out.print(Mensagem[m-i-1]);
        }
        System.out.println();
        while(true)  //Calculo do número de bits de paridade necessarios
		{
			if(m+r+1<=Math.pow(2,r))
			{
				break;
			}
			r++;
		}
		System.out.println("Numero de bits de paridade Necessários : "+r);
        
        int codMLength = m+r;
        int codM[] = new int [codMLength];  //Mensagem Codificada
        int j =  0, k = 0;
        for(int i=1; i<=codM.length;i++){   
            if(Math.pow(2,j)==i){          //olha se a posição é múltipla de 2 para colocar o bit de paridade
                codM[i-1] = 2;             //coloca 2 no lugar dos bits de paridade
                j++;
            }
            else {
                codM[k+j] = Mensagem[k++];  //
            }
        
        }
        
        
        for(int i=0 ; i < r ; i++) {
			// Coloca os bits de paridade em seus lugares		
			codM[((int) Math.pow(2, i))-1] = Paridade(codM, i);
		}
        
        
       
        
        System.out.print("Mensagem codificada: ");
        
        for(int i = codM.length-1;i>=0;i--){
            System.out.print(codM[i]);
            }
        System.out.println();
       
        
    }
    
    static int Paridade(int codM[], int b) {
		int bitP = 0;
		for(int i=0 ; i < codM.length ; i++) {
			if(codM[i] != 2) {
				int k = i+1;
				String s = Integer.toBinaryString(k);				
				int x = ((Integer.parseInt(s))/((int) Math.pow(10, b)))%10;
				if(x == 1) {
					if(codM[i] == 1) {
						bitP = (bitP+1)%2;
					}
				}
			}
		}
		return bitP;
	}
}
