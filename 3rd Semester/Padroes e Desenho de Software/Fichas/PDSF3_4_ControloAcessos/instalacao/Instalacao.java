package instalacao;

import java.util.Collection;

import controlo.Funcionario;

/**
 * Esta interface representa as instalações da companhia
 */
public interface Instalacao {

	/**
	 * devolve o identificador único 
	 * @return o identificador único 
	 */
	public int getId();
	
	/**
	 * indica se um funcionário pode entrar  
	 * @param f funcionário a querer entrar
	 * @return true se pode entrar, false se não pode
	 */
	public boolean podeEntrar( Funcionario f );
	
	/**
	 * Processa a entrada do funcionário, se este puder entrar
	 * @param f funcionário a querer entrar
	 * @return se entrou ou não
	 */
	public boolean entrar( Funcionario f );	
			
	/**
	 * processa a saída do funcionário f
	 * @param f funcionário que pretende sair
	 */
	public void sair( Funcionario f );
	
	/**
	 * indica se um dado funcionário está presente na instalação
	 * @param f o funcionário a verificar se está presente 
	 * @return se o funcionário está, ou não, presente na instalação
	 */
	public boolean estaPresente( Funcionario f ); 
	
	/**
	 * devolve uma lista com os funcionários presentes 
	 * @return uma lista com os funcionários presentes 
	 */
	public Collection<Funcionario> getPresentes();
	
	/**
	 * verifica se duas instalações têm o mesmo id, útil para pesquisas em coleções
	 * @param i instalação a verificar
	 * @return true se têm o mesmo id
	 */
	public boolean equals( Instalacao i);

	/** retorna o nome da instalação
	 * @return o nome da instalação
	 */
	public String getNome();

	/** define o nome a dar à instalação
	 * @param nome o nome a dar à instalação
	 */
	public void setNome(String nome);	
}
