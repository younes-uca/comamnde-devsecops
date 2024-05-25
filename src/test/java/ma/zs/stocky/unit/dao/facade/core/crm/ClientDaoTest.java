package ma.zs.stocky.unit.dao.facade.core.crm;

import ma.zs.stocky.bean.core.crm.Client;
import ma.zs.stocky.dao.facade.core.crm.ClientDao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.IntStream;
import java.time.LocalDateTime;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ClientDaoTest {

@Autowired
    private ClientDao underTest;

    @Test
    void shouldFindByEmail(){
        String email = "email-1";
        Client entity = new Client();
        entity.setEmail(email);
        underTest.save(entity);
        Client loaded = underTest.findByEmail(email);
        assertThat(loaded.getEmail()).isEqualTo(email);
    }

    @Test
    void shouldDeleteByEmail() {
        String email = "email-1";
        Client entity = new Client();
        entity.setEmail(email);
        underTest.save(entity);

        int result = underTest.deleteByEmail(email);

        Client loaded = underTest.findByEmail(email);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(1);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        Client entity = new Client();
        entity.setId(id);
        underTest.save(entity);
        Client loaded = underTest.findById(id).get();
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Client entity = new Client();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Client loaded = underTest.findById(id).get();
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Client> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Client> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }
    @Test
    void shouldSave(){
        Client given = constructSample(1);
        Client saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private Client constructSample(int i) {
		Client given = new Client();
        given.setFullName("fullName-"+i);
        given.setEmail("email-"+i);
        return given;
    }

}
