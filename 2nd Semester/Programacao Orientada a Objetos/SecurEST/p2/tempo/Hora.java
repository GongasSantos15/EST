package p2.tempo;
import java.util.Calendar;

/**
 * @author fsergio
 *
 */
public class Hora implements Cloneable {
	private int horas;
	private int minutos;
	private int segundos;
	
	// constantes para vários cálculos
	public static final int SEGS_POR_MINUTO = 60;
	public static final int MINS_POR_HORA = 60;
	public static final int SEGS_POR_HORA = SEGS_POR_MINUTO * MINS_POR_HORA;
	public static final int HORAS_POR_DIA = 24;
	public static final int MINS_POR_DIA = HORAS_POR_DIA * MINS_POR_HORA;
	public static final int SEGS_POR_DIA = HORAS_POR_DIA * SEGS_POR_HORA;
	
	/** Constroi uma Hora com a hora atual do sistema
	 */
    public Hora( ){
		Calendar cal = Calendar.getInstance();
		horas = cal.get( Calendar.HOUR_OF_DAY );
		minutos = cal.get( Calendar.MINUTE );
		segundos = cal.get( Calendar.SECOND );    	
    }

    /** Constroi uma hora com uma dada hora, minutos e segundos
     * @param h as horas
     * @param m os minutos
     * @param s os segundos
     */
    public Hora( int h, int m, int s){
    	setHoras( h );
    	setMinutos( m );
    	setSegundos( s );
    }
    
    /** Constroi uma hora a partir de uma quantidade de segundos
     * @param totalSegs total de segundos para converter para Hora
     */
    public Hora( long totalSegs ){
        // verificar se nº de segundos não é inválido
        if( totalSegs < 0 )
        	totalSegs = 0;
        if( totalSegs >= SEGS_POR_DIA )
        	totalSegs = SEGS_POR_DIA - 1; // SEGS_POR_DIA - 1 porque segundos começam em 0 
        
        // o (int) serve só para evitar mensagens de warning
        horas = (int)(totalSegs / SEGS_POR_HORA);
        minutos = (int)((totalSegs % SEGS_POR_HORA) / SEGS_POR_MINUTO);
        segundos = (int)((totalSegs % SEGS_POR_HORA) % SEGS_POR_MINUTO);
    }
       
    /** Constroi uma hora a partir de uma string no formato hh:mm:ss
     * @param hStr a string formatada
     */
    public Hora( String hStr ){
	    // ler a string no formato horas:minutos:segundos
		String str[] = hStr.split(":");	    
    
	    // verificar se valores são válidos
        setHoras( Integer.parseInt( str[0] ) );
	    setMinutos( Integer.parseInt( str[1] ) );
	    setSegundos( Integer.parseInt( str[2] ) );	        	
    }
    
    /** Soma uma quantidade de horas à hora. <br>
     * Se o total ultrapassar as horas de um dia, será feito o transporte para o dia seguinte.
     * O método retorna quantas vezes passou das 24 horas, isto é, quantos dias passaram.  
     * @param numHoras número de horas a somar
     * @return o número de dias que se passaram
     */
    public int somaHoras( int numHoras ){
        // para garantir que o nº de horas é entre 0 e 23 aceita-se apenas o resto
        // da divisão por 24
        // exemplo: se Horas = 20 e se somar 10 fica = 30 dividindo por 24 dá
        //          30/24 = 1 e resto 6
        //          ficariam então as horas como sendo 6 da manhã
       	int totalHoras = horas + numHoras;
        horas = totalHoras % 24;
        return totalHoras / 24;    	 	
    }
    
    /** Soma uma quantidade de minutos à hora. <br>
     * Se o total ultrapassar os minutos de uma hora, soma as horas respetivas.
     * O método retorna quantas vezes passou das 24 horas, isto é, quantos dias passaram. 
     * @param numMinutos número de minutos a somar
     * @return o número de dias que se passaram
     */
    public int somaMinutos( int numMinutos ){
        // somar os novos minutos ao já existentes
        minutos += numMinutos;
        // se a soma dos minutos superar os 59 tem-se de somar horas,
        // tantas quantas couberem dentro dos minutos somados
        int nDias = somaHoras( minutos/SEGS_POR_MINUTO );
        // garantir que minutos esta emtre 0 e 59
        minutos = minutos % SEGS_POR_MINUTO;
        return nDias;    	
    }
    
    /** Soma uma quantidade de segundos à hora. <br>
     * Se o total ultrapassar os segundos de um minuto, soma os minutos respetivos.
     * O método retorna quantas vezes passou das 24 horas, isto é, quantos dias passaram.
     * @param numSegundos número de segundos a somar
     * @return o número de dias que se passaram
     */
    public int somaSegundos( int numSegundos ){
        // procedimento semelhante aos minutos
        segundos += numSegundos;
        int nDias = somaMinutos( segundos/SEGS_POR_MINUTO ); 
        segundos = (int)(segundos%SEGS_POR_MINUTO);
        return nDias;     	
    }
    
    /** Converte a hora para segundos, isto é, retorna quantos segundos
     * decorreram desde a meia noite até esta hora
     * @return o total de segundos desde a meia noite
     */
    public int toSegundos( ) {
    	return horas * SEGS_POR_HORA + minutos * SEGS_POR_MINUTO + segundos;
    }

   /** Retorna as horas
     * @return as horas
     */
    public int getHoras( ) {
    	return horas;
    }
    
    /** Define as horas
     * @param h a nova hora
     */
    public void setHoras( int h ){
    	horas = h < 0? 0: h;
    	if( horas >= HORAS_POR_DIA )
    		horas = HORAS_POR_DIA - 1;    	
    }

    /** retorna os minutos
     * @return os minutos
     */
    public int getMinutos( ) {
    	return minutos;
    }

    /** define os minutos
     * @param m os novos minutos
     */
    public void setMinutos( int m ){
    	minutos = m < 0? 0: m;
    	if( minutos >=  MINS_POR_HORA )
    		minutos = MINS_POR_HORA - 1;    	
    }
    
    /** retorna os segundos
     * @return os segundos
     */
    public int getSegundos( ) {
    	return segundos;
    }
    
    /** define os segundos
     * @param s os novos segundos
     */
    public void setSegundos( int s ){
    	segundos = s < 0? 0: s;
    	if( segundos >= SEGS_POR_MINUTO )
    		segundos = SEGS_POR_MINUTO;    	
    }
    
    /** Compara a Hora com outra. <br>
     * @param outra a hora com que se compara
     * @return < 0 se a hora for menor que a outra<br>
     *         == 0 se a hora for igual à outra<br>
     *          > 0 se a hora for maior que a outra<br> 
     */
    public int  compareTo( Hora outra){
        if( horas != outra.horas )
            return horas - outra.horas;
        if( minutos != outra.minutos )
            return minutos - outra.minutos;
        return segundos - outra.segundos;       	
    }
    
    /** Retorna a diferença em segundos para outra hora
     * @param outra a hora para a qual ver a diferença em segundos
     * @return  a diferença em segundos para outra hora
     */
    public long getDiferencaSegs( Hora outra ){
        return toSegundos() - outra.toSegundos();    	
    }
    
    public String toString() {
    	return horas + ":" + minutos + ":" + segundos;
    }

	public Hora clone( ) {
		return new Hora( horas, minutos, segundos);
	}
}
