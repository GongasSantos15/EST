/* 1. Qual o n�mero de ocorr�ncias de precipita��o, por dia de semana, por tipo de chuva? */ 
SELECT 
    T.diaSemana,
	P.tipoChuva,
    COUNT(TF.id_precipitacao) AS NumeroOcorrencias
FROM 
	tabela_factos TF INNER JOIN dim_tempo T 
		ON TF.id_tempo = T.id_tempo INNER JOIN dim_precipitacao P
			ON TF.id_precipitacao = P.id_precipitacao
GROUP BY T.diaSemana, P.tipoChuva
ORDER BY T.diaSemana, P.tipoChuva;

/* 2. Qual a quantidade total, por tipo de pluviosidade, por m�s? */
SELECT 
    T.mes,
    P.tipoChuva,
    SUM(TF.quantidade) AS QuantidadeTotal
FROM 
    tabela_factos TF INNER JOIN dim_tempo T 
		ON TF.id_tempo = T.id_tempo INNER JOIN dim_precipitacao P 
			ON TF.id_precipitacao = P.id_precipitacao 
GROUP BY T.mes, P.tipoChuva
ORDER BY T.mes, P.tipoChuva;

/* 3. Qual o tempo m�dio de tempo dura��o e de quantidade, por tipo de pluviosidade? */
SELECT 
    P.tipoChuva,
    AVG(TF.duracao) AS DuracaoMedia,
    AVG(TF.quantidade) AS QuantidadeMedia
FROM 
    tabela_factos TF INNER JOIN dim_precipitacao P 
		ON P.id_precipitacao = TF.id_precipitacao
GROUP BY P.tipoChuva
ORDER BY P.tipoChuva;

/* 4. Quais os valores m�dios, por tipo de precipita��o, por distrito? */
SELECT 
    L.distrito,
    P.tipoChuva,
    AVG(TF.quantidade) AS QuantidadeMedia
FROM 
    tabela_factos TF INNER JOIN 
    dim_localizacao L 
		ON TF.id_localizacao = L.id_localizacao INNER JOIN dim_precipitacao P 
			ON TF.id_precipitacao = P.id_precipitacao
GROUP BY L.distrito, P.tipoChuva
ORDER BY L.distrito, P.tipoChuva;



