package aula04.tempo.copiadefensiva;

import java.util.Calendar;
import java.util.Objects;

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
	public static final int SEGS_POR_HORA = MINS_POR_HORA * SEGS_POR_MINUTO; // o compilador que faça a conta :-)
	public static final int HORAS_POR_DIA = 24;  
	public static final int SEGS_POR_DIA = HORAS_POR_DIA * SEGS_POR_HORA;  
	public static final int MINS_POR_DIA = HORAS_POR_DIA * MINS_POR_HORA;  
	
	
    public Hora( ){
		Calendar cal = Calendar.getInstance();
		horas = cal.get( Calendar.HOUR_OF_DAY );
		minutos = cal.get( Calendar.MINUTE );
		segundos = cal.get( Calendar.SECOND );    	
    }
    
    public Hora( int h, int m, int s){
    	setHoras( h );
    	setMinutos( m );
    	setSegundos( s );
    }
    
    public Hora( long nSegsMeiaNoite ){
        // verificar se nº de segundos não é inválido
    	if( nSegsMeiaNoite < 0 || nSegsMeiaNoite >= SEGS_POR_DIA )
        	throw new IllegalArgumentException( "segundos (" + nSegsMeiaNoite + ") tem de ser entre 0 e " + SEGS_POR_DIA ); 
        
        horas = (int)(nSegsMeiaNoite / SEGS_POR_HORA);
        minutos = (int)((nSegsMeiaNoite % SEGS_POR_HORA) / SEGS_POR_MINUTO);
        segundos = (int)((nSegsMeiaNoite % SEGS_POR_HORA) % SEGS_POR_MINUTO);
    }
       
    public Hora( String hStr ){
    	Objects.requireNonNull( hStr, "hStr não pode ser null.");

	    try {
			// ler a string no formato dia/mês/ano
			String str[] = hStr.split(":");	    
   
			// verificar se valores são válidos
			setHoras( Integer.parseInt( str[0] ) );
			setMinutos( Integer.parseInt( str[1] ) );
			setSegundos( Integer.parseInt( str[2] ) );
		} catch (Exception e) {
			throw new IllegalArgumentException(hStr + " não está no formato hh:mm:ss");
		}	        	
    }

    // métodos de acesso
    public int getHoras( ) {
    	return horas;
    }
    
    public int getMinutos( ) {
    	return minutos;
    }
    
    public int getSegundos( ) {
    	return segundos;
    }
    
    public void setHoras( int h ){
    	if( h < 0 || h > HORAS_POR_DIA )
    		throw new IllegalArgumentException( "Horas ("+h+") tem de estar entre 0 e HORAS_POR_DIA");
   		horas = h;    	
    }
    
    public void setMinutos( int m ){
    	if( m < 0 || m > MINS_POR_HORA )
    		throw new IllegalArgumentException( "minutos ("+m+") tem de estar entre 0 e MINS_POR_HORA");
    	minutos = m;    	
    }
    
    public void setSegundos( int s ){
    	if( s < 0 || s > SEGS_POR_MINUTO )
    		throw new IllegalArgumentException( "segundos ("+s+") tem de estar entre 0 e SEGS_POR_MINUTO");
    	segundos = s;    	
    }
    
    
    // métodos de implementação
    // retornam quantas vezes passaram de 24 horas (nº de dias)
    public int somaHoras( int numHoras ){
        // para garantir que o nº de horas está entre 0 e 23 aceita-se apenas o resto
        // da divisão por 24
        // exemplo: se Horas = 20 e se somar 10 fica = 30 dividindo por 24 dá
        //          30/24 = 1 e resto 6
        //          ficariam então as horas como sendo 6 da manhâ
       	int totalHoras = horas + numHoras;
        horas = totalHoras % HORAS_POR_DIA;
        return totalHoras / HORAS_POR_DIA;    	 	
    }
    
    public int somaMinutos( int numMinutos ){
        // somar os novos minutos ao já existentes
        minutos += numMinutos;
        // se a soma dos minutos superar os 59 tem-se de somar horas,
        // tantas quantas couberem dentro dos minutos somados
        int nDias = somaHoras( minutos/SEGS_POR_MINUTO );
        // garantir que minutos está entre 0 e 59
        minutos = minutos % SEGS_POR_MINUTO;
        return nDias;    	
    }
    
    
    public int somaSegundos( long numSegundos ){
        // procedimento semelhante aos minutos
        // usa-se seg porque segundos é int e perde-se resolução ao somar com long
        long seg = segundos + numSegundos;
        int nDias = somaMinutos( (int)(seg/SEGS_POR_MINUTO) );  // conversão long para int, para o compilador não se queixar
        segundos = (int)(seg%SEGS_POR_MINUTO);
        return nDias;     	
    }
    
    // método que retorna o nº de segundos desde a meia-noite desse dia
    public int toSegundos( ) {
    	return horas * SEGS_POR_HORA + minutos * SEGS_POR_MINUTO + segundos;
    }
    
    // método para comparar Horas    
    public int  compareTo( Hora outra){
        if( horas != outra.horas )
            return horas - outra.horas;
        if( minutos != outra.minutos )
            return minutos - outra.minutos;
        return segundos - outra.segundos;       	
    }
    
    public int getDiferencaSegs( Hora outra ){
    	return toSegundos() - outra.toSegundos();
    }
    
    // método para imprimir a hora em formato simples
    public String toString() {
    	return String.format("%02d:%02d:%02d", horas, minutos, segundos );
    }

	public Hora clone( ) {
		return new Hora( horas, minutos, segundos);
	}
}
