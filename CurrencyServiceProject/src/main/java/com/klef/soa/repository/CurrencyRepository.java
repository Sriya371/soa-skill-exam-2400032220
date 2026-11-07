package com.klef.soa.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.klef.soa.entity.Currency;
import java.util.List;
@Repository 
public interface CurrencyRepository extends JpaRepository<Currency,Long> 
{
	List<Currency> findByName(String name);
	List<Currency> findByType(String type);
}