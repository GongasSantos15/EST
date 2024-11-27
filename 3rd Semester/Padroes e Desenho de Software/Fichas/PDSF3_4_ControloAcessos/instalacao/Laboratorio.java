package instalacao;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import controlo.Funcionario;

/**
 * Classe que representa um laboratório. Só podem entrar no laboratório 
 * funcionários que estejam presentes numa lista de acesso
 */
public class Laboratorio extends InstalacaoDefault {

	private HashSet<Funcionario> autorizados = new HashSet<Funcionario>();
	
	/** Para criar um laboratório é necessario indicar o seu identificador único e o nome
	 * @param id identificador único
	 * @param nome nome do laboratório
	 */
	public Laboratorio( int id, String nome ) {
		super( id, nome );
	}

	@Override
	public boolean podeEntrar(Funcionario f) {
		if( !super.podeEntrar(f) ) return false;
		return autorizados.contains( f );
	}
	
	/** adiciona um funcionário à lista de autorizados
	 * @param f funcionário a adicionar
	 */
	public void addAutorizado( Funcionario f ){
		autorizados.add( f );
	}

	/** remove um funcionario da lista de autorizados
	 * @param f funcionário a remover
	 */
	public void removeAutorizado( Funcionario f ){
		autorizados.remove( f );
	}
	
	/** retorna uma lista com os funcionários autorizados a entrar
	 * @return uma lista com os funcionários autorizados a entrar
	 */
	public Collection<Funcionario> getAutorizados(){
		return Collections.unmodifiableCollection( autorizados );
	}	
}
