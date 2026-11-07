
package com.klef.soa.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import com.klef.soa.entity.Currency;
import com.klef.soa.repository.CurrencyRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RepositoryTest {

    @Autowired
    private CurrencyRepository currencyRepository;

    @BeforeEach
    public void setUp() {

        currencyRepository.deleteAll();

        Currency c1 = new Currency();
        c1.setName("Indian Rupee");
        c1.setType("Fiat");
        c1.setStatus(true);
        c1.setLocation("India");

        Currency c2 = new Currency();
        c2.setName("US Dollar");
        c2.setType("Fiat");
        c2.setStatus(true);
        c2.setLocation("USA");

        Currency c3 = new Currency();
        c3.setName("Euro");
        c3.setType("Fiat");
        c3.setStatus(true);
        c3.setLocation("Europe");

        Currency c4 = new Currency();
        c4.setName("Bitcoin");
        c4.setType("Crypto");
        c4.setStatus(true);
        c4.setLocation("Global");

        Currency c5 = new Currency();
        c5.setName("Japanese Yen");
        c5.setType("Fiat");
        c5.setStatus(true);
        c5.setLocation("Japan");

        currencyRepository.saveAll(List.of(c1, c2, c3, c4, c5));
        currencyRepository.flush();
    }

    // Test 1: Find all currencies
    @Test
    public void testFindAll() {
        List<Currency> result = currencyRepository.findAll();

        assertNotNull(result);
        assertEquals(5, result.size());
    }

    // Test 2: Find by name
    @Test
    public void testFindByName() {
        List<Currency> result =
                currencyRepository.findByName("Indian Rupee");

        assertFalse(result.isEmpty());
        assertEquals("Indian Rupee", result.get(0).getName());
    }

    // Test 3: Find by type
    @Test
    public void testFindByType() {
        List<Currency> result =
                currencyRepository.findByType("Crypto");

        assertFalse(result.isEmpty());
        assertEquals("Bitcoin", result.get(0).getName());
    }

    // Test 4: Find by ID
    @Test
    public void testFindById() {
        List<Currency> currencies =
                currencyRepository.findByName("US Dollar");

        assertFalse(currencies.isEmpty());

        long id = currencies.get(0).getCurrencyId();

        Optional<Currency> result =
                currencyRepository.findById(id);

        assertTrue(result.isPresent());
        assertEquals("US Dollar", result.get().getName());
    }

    // Test 5: Count currencies
    @Test
    public void testCount() {
        assertEquals(5, currencyRepository.count());
    }
}
