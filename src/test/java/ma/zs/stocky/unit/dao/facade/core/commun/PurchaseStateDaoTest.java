package ma.zs.stocky.unit.dao.facade.core.commun;

import ma.zs.stocky.bean.core.commun.PurchaseState;
import ma.zs.stocky.dao.facade.core.commun.PurchaseStateDao;

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
public class PurchaseStateDaoTest {

@Autowired
    private PurchaseStateDao underTest;

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        PurchaseState entity = new PurchaseState();
        entity.setCode(code);
        underTest.save(entity);
        PurchaseState loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-1";
        PurchaseState entity = new PurchaseState();
        entity.setCode(code);
        underTest.save(entity);

        int result = underTest.deleteByCode(code);

        PurchaseState loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(1);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        PurchaseState entity = new PurchaseState();
        entity.setId(id);
        underTest.save(entity);
        PurchaseState loaded = underTest.findById(id).get();
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        PurchaseState entity = new PurchaseState();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        PurchaseState loaded = underTest.findById(id).get();
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<PurchaseState> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<PurchaseState> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }
    @Test
    void shouldSave(){
        PurchaseState given = constructSample(1);
        PurchaseState saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private PurchaseState constructSample(int i) {
		PurchaseState given = new PurchaseState();
        given.setLibelle("libelle-"+i);
        given.setCode("code-"+i);
        return given;
    }

}
