package instalacao;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import controlo.Funcionario;

/**
 * Esta classe implementa o comportamento comum de uma instalação 
 * Possui um nome, identificador único e um nível de acesso,
 * bem como uma lista dos funcionários presentes
 */
public abstract class InstalacaoDefault implements Instalacao {

	private int id;
	private String nome;
	private HashSet<Funcionario> presentes = new HashSet<Funcionario>();
	
	public InstalacaoDefault( int id, String  nome) {
		this.id = id;
		this.nome = nome;
	}

	@Override
	public int getId() {
		return id;
	}
	
	@Override
	public String getNome() {
		return nome;
	}
	
	@Override
	public void setNome(String nome) {
		if( nome == null || nome .isBlank() )
			throw new IllegalArgumentException("nome inválido!");
		this.nome = nome;
	}
	
	@Override
	public boolean podeEntrar( Funcionario f ) {
		if( f == null )
			return false;
		
		// se já lá está não pode entrar
		return f.getInstalacao() != this;
	}

	@Override
	public boolean entrar( Funcionario f ){
		if( !podeEntrar( f ) )
			return false;		
		if( f.getInstalacao() != null )
			f.getInstalacao().sair(f);
		presentes.add( f );
		f.setInstalacao( this );
		return true;
	}
		
	@Override
	public void sair( Funcionario f ){
		presentes.remove( f );
		f.setInstalacao( null );
	}
	
	@Override
	public boolean estaPresente( Funcionario f ){
		return getPresentes().contains( f );
	}
	
	@Override
	public Collection<Funcionario> getPresentes(){
		return Collections.unmodifiableCollection( presentes );
	}
	
	@Override
	public boolean equals( Instalacao i){
		return id == i.getId();
	}	
}
