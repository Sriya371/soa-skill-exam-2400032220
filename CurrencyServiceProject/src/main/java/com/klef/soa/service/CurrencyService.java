package com.klef.soa.service;
import java.util.List;
import com.klef.soa.entity.Currency;
public interface CurrencyService 
{
	Currency addCurrency(Currency currency);
	List<Currency> displayAllCurrencies();
	Currency updateCurrency(Currency c);
	Currency displayCurrencyById(Long id);
	String deleteCurrencyById(Long id);
	List<Currency> displayCurrenciesByName(String name);
	List<Currency> displayCurrenciesByType(String type);
	Long displayCurrencyCount();
}
