package teste;
import p2.tempo.Hora;
import p2.tempo.Horario;
import securest.recurso.Funcionario;
import securest.recurso.Instalacao;

public class testeInstalacao {
	
	public static void main(String[] args) {
		
		Instalacao i1 = new Instalacao (1, "sala 1", new Horario(), 2);
		System.out.println(i1);
		
		Funcionario f1 = new Funcionario(20, "Martim Martins", 5);
		System.out.println(f1);
		
		if(i1.entrar(f1))
			f1.entrar(i1);
		
		System.out.println(i1.podeEntrar(f1, new Hora()));
		
		System.out.println("presentes: " + i1.getPresentes());
		
		
		
		
		
		
		
		
	}

}
