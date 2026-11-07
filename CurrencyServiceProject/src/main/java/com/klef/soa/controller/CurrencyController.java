package com.klef.soa.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.klef.soa.entity.Currency;
import com.klef.soa.service.CurrencyService;
@RestController
@RequestMapping("/currency")
public class CurrencyController
{
 @Autowired
 private CurrencyService service;
 @GetMapping("/")
 public String test()
 {
  return "SOA Programming & MicroServices";
 }
 // Add Currency
 @PostMapping("/add") 
 public ResponseEntity<Currency> addCurrency(@RequestBody Currency c) 
 { 
  Currency currency = service.addCurrency(c); 
  return ResponseEntity.status(201).body(currency); 
 }
 // Display All Currencies
 @GetMapping("/displayall")
 public ResponseEntity<List<Currency>> displayAllCurrencies()
 {
  List<Currency> currencies = service.displayAllCurrencies(); 
  return ResponseEntity.status(200).body(currencies);
 }
 // Display Currency By ID
 @GetMapping("/display")
 public ResponseEntity<?> displayCurrencyById(@RequestParam Long id)
 {
  Currency currency = service.displayCurrencyById(id);
  if(currency != null)
  {
   return ResponseEntity.status(200).body(currency);
  }
  else
  {
   return ResponseEntity.status(404).body("Currency ID Not Found");
  }
 }
 // Update Currency
 @PutMapping("/update")
 public ResponseEntity<?> updateCurrency(@RequestBody Currency currency)
 {
  Currency c = service.updateCurrency(currency);
  if(c != null)
  {
   return ResponseEntity.ok(c);
  }
  else
  {
   return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Currency ID Not Found");
  }
 }
 // Delete Currency By ID
 @DeleteMapping("/delete/{id}")
 public ResponseEntity<String> deleteCurrencyById(@PathVariable Long id)
 {
  String message = service.deleteCurrencyById(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
  
 }
 // Display Currencies By Name
 @GetMapping("/displaybyname/{name}")
 public ResponseEntity<List<Currency>> displayCurrenciesByName(@PathVariable String name)
 {
  List<Currency> currencies = service.displayCurrenciesByName(name);
  return ResponseEntity.status(200).body(currencies);
 }
 // Display Currencies By Type
 @GetMapping("/displaybytype/{type}")
 public ResponseEntity<List<Currency>> displayCurrenciesByType(@PathVariable String type)
 {
  List<Currency> currencies = service.displayCurrenciesByType(type);
  return ResponseEntity.status(200).body(currencies);
 }
 
 @GetMapping("/count")
 public ResponseEntity<String> displayCurrencyCount() 
 {
	Long count = service.displayCurrencyCount();
	String msg="Total Currencies="+count;
	return ResponseEntity.ok(msg);
 }
}
