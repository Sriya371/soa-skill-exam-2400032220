package com.klef.soa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.soa.entity.Currency;
import com.klef.soa.repository.CurrencyRepository;

@Service
public class CurrencyServiceImpl implements CurrencyService
{
	@Autowired
	private CurrencyRepository repo;
	@Override
	public Currency addCurrency(Currency currency)
	{
		return repo.save(currency);
	}

	@Override
	public List<Currency> displayAllCurrencies() {
		return repo.findAll();
	}

	@Override
	public Currency updateCurrency(Currency c)
	{
	Optional<Currency> optional =repo.findById(c.getCurrencyId());
	if(optional.isPresent())
	{
		Currency currency = optional.get();
		currency.setName(c.getName());
		currency.setType(c.getType());
		currency.setStatus(c.isStatus());
		currency.setLocation(c.getLocation());

		return repo.save(currency);

	}
	else {
		return null;
	}
	}

	@Override
	public Currency displayCurrencyById(Long id)
	{
		return repo.findById(id).orElse(null);
	}

	@Override
	public String deleteCurrencyById(Long id) {
		boolean status =repo.existsById(id);
		if(status)
		{
			repo.deleteById(id);
			return "Currency Deleted Successfully";
		}
		else
		{
			return "Currency ID Not Found";
		}
	}

	@Override
	public List<Currency> displayCurrenciesByName(String name)
	{
		return repo.findByName(name);
	}

	@Override
	public List<Currency> displayCurrenciesByType(String type)
	{
		return repo.findByType(type);
	}

	@Override
	public Long displayCurrencyCount()
	{
		return repo.count();
	}

}
