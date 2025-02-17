USE AdventureWorksDW2017

-- Listar o total das vendas
SELECT SUM(fis.SalesAmount)
FROM FactInternetSales fis;

-- Listar o total de vendas, por ano
SELECT dd.CalendarYear, SUM(fis.SalesAmount)
FROM FactInternetSales fis INNER JOIN DimDate dd
	ON fis.OrderDateKey = dd.DateKey
GROUP BY dd.CalendarYear
