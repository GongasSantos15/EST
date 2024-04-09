package securest.recurso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
//import securest.recurso.Funcionario;
import p2.tempo.Hora;
import p2.tempo.Horario;

public class Instalacao {

	public static final int NIVEL_MAXIMO = 5;
	public static final int NIVEL_MINIMO = 1;

	private int id, nivel;
	private String descricao;
	private Horario funcionamento;
	


	private ArrayList<Funcionario> presentes = new ArrayList<Funcionario>();
	private ArrayList<Funcionario> autorizados = new ArrayList<Funcionario>();


	public Instalacao(int id, String descricao, Horario funcionamento, int nivel) {
		this.id = id;
		setNivel(nivel);
		setFuncionamento(funcionamento);
		setDescricao(descricao);
	}
	
	private void addAutorizado(Funcionario f) {
		autorizados.add(f);
	}
	
	public boolean podeEntrar(Funcionario f, Hora h) {
		if(!funcionamento.estaDentro(h)) {
		return false;
		}
		
		return f.getNivel() >= nivel || autorizados.contains(h);
	}
	
	public boolean entrar(Funcionario f) {
		if(!podeEntrar(f, new Hora()))
			return false;
		
		presentes.add(f);
		return true;
	}


	public int getNivel() {
		return nivel;
	}


	public void setNivel(int nivel) {
		if(nivel >= NIVEL_MINIMO && nivel<=NIVEL_MAXIMO)
		this.nivel = nivel;
		
		else
			this.nivel = NIVEL_MAXIMO;
	}


	public Horario getFuncionamento() {
		return funcionamento;
	}


	public void setFuncionamento(Horario funcionamento) {
		this.funcionamento = funcionamento;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public int getId() {
		return id;
	}
	
	public List<Funcionario> getPresentes(){
		return Collections.unmodifiableList(presentes);
	}
	
	public List<Funcionario> getAutorizados(){
		return Collections.unmodifiableList(autorizados);
	}


	@Override
	public String toString() {
		return "[id=" + id + ", nivel=" + nivel + ", descricao=" + descricao + ", funcionamento="
				+ funcionamento + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Instalacao other = (Instalacao) obj;
		return id == other.id;
	}
	
	


	
	
	
	
	





}
