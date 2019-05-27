import java.util.ArrayList;

/*Classe que representa o Cromossomo, ou seja
 * um solu��o proposta dentro de uma popula��o.
 * Cromossomos podem ser tamb�m mencionados como
 * indiv�duos. */

public class Cromossomo implements Comparable {
	/*lista de n�s formam um cromossomo*/
	ArrayList<Node> nodes;
	/*fitness que nos d� o valor de adequa��o dessa solu��o*/
	int fitness;

	
	public Cromossomo() {
		nodes = new ArrayList<Node>();
	}
	
	/*Calculando o Fitness. Para esse problema, o fitness
	 * � a pr�pria dist�ncia percorrida entre os n�s de 
	 * um caminho.*/
	public void calcFitness() {
		/*array de inteiros que usamos para contar quantos
		elementos se repetem*/
		int[] count = new int[7];
		fitness = 0;
		
		//percorrendo do n� 0 at� o n-1
		for(int i = 0; i < this.nodes.size() - 1; i++) {
			/*calculando a dist�ncia, getWeigh retorna o peso
			entre dois n�s*/
			fitness += nodes.get(i).getWeightBtw(nodes.get(i+1));
			
			/*contando quantos n�s se repetem*/
			if(nodes.get(i).label.equals("A")) {
					count[0]++;
			}else if(nodes.get(i).label.equals("B")) {
				count[1]++;
			}else if(nodes.get(i).label.equals("C")) {
				count[2]++;
			}else if(nodes.get(i).label.equals("D")) {
				count[3]++;
			}else if(nodes.get(i).label.equals("E")) {
				count[4]++;
			}else if(nodes.get(i).label.equals("F")) {
				count[5]++;
			}else if(nodes.get(i).label.equals("G")) {
				count[6]++;
			}
		}
		
		//verificando se h� repeti��es, quando count[i] > 1
		for(int i = 0; i < count.length; i++) {
			if(count[i]>1)
				//Como queremos minimzar o custo, coloco o fitness com valor m�ximo 
				fitness = Integer.MAX_VALUE;
		}
	}

	/*M�todo que realiza o cruzamento entre dois cromossomos
	 * Dado c1 = [A,D,B,C] e c2 = [A, B, C, D]
	 * newc = [A,B,C,D]*/
	public static Cromossomo crossover(Cromossomo c1, Cromossomo c2) {
	    Cromossomo newc = new Cromossomo();
			
		for(int i = 0; i < c1.nodes.size(); i++)
			if(i%2 == 0)
				newc.nodes.add(i, c1.nodes.get(i));
			else
				newc.nodes.add(i, c2.nodes.get(i));
				
		return newc;
	} 
	
	public static Cromossomo mutation(Cromossomo c) {
		
	   int i = (int) Math.random() * c.nodes.size();
	   int j = (int) Math.random() * c.nodes.size();
	   Node n = c.nodes.get(i);
	   c.nodes.set(i, c.nodes.get(j));
	   c.nodes.set(j, n);
	   
	   return c;	
	}

	@Override
	public int compareTo(Object arg) {
		Cromossomo c = (Cromossomo) arg;
		if(this.fitness > c.fitness)
			return 1;
		else if(this.fitness > c.fitness)
			return -1;
		else
			return  0;
	}

	public String toString() {
		String s = "";
		for(int j = 0;  j< nodes.size(); j++)
			 s+="["+nodes.get(j)+"] ";
		
		s+=" - fitness "+fitness +"\n";
		
	
		return s;
	}
}
