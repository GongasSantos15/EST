package instalacao;

import controlo.Funcionario;

/**
 * classe que representa uma sala. Só podem entrar na sala funcionários
 * com nível de acesso superior ou igual ao da própria sala 
 */
public class Sala extends InstalacaoDefault {

	private int nivelAcesso;
	
	/** Para criar uma sala é necessário indicar
	 * o seu identificador único, nome e nível de acesso.
	 * @param id identificador único
	 * @param nome nome da sala
	 * @param nivel nível de acesso mínimo para entrar na sala
	 */
	public Sala( int id, String nome, int nivel ) {
		super( id, nome );
		setNivelAcesso( nivel );
	}
	
	@Override
	public boolean podeEntrar( Funcionario f ) {
		if( !super.podeEntrar( f ) ) return false;
		return f.getNivel() >= nivelAcesso;
	}
	
	/** Define o nível de acesso. O nível de acesso deve ser entre 1 e NIVEL_ACESSO_MAX
	 * @param nivelAcesso nivel de acesso pretendido
	 */
	public void setNivelAcesso(int nivelAcesso) {
		if( nivelAcesso < Funcionario.NIVEL_ACESSO_MIN || nivelAcesso > Funcionario.NIVEL_ACESSO_MAX )
			throw new IllegalArgumentException( "nivel acesso inválido" );
		this.nivelAcesso = nivelAcesso;
	}
	
	/** retorna o nível de acesso necessário para entrar na sala
	 * @return o nível de acesso necessário para entrar na sala
	 */
	public int getNivelAcesso(){
		return nivelAcesso;
	}	
}
