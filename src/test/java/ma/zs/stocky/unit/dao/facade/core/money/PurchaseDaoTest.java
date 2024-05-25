package ma.zs.stocky.unit.dao.facade.core.money;

import ma.zs.stocky.bean.core.money.Purchase;
import ma.zs.stocky.dao.facade.core.money.PurchaseDao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.IntStream;
import java.time.LocalDateTime;

import ma.zs.stocky.bean.core.commun.PurchaseState ;
import ma.zs.stocky.bean.core.crm.Client ;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class PurchaseDaoTest {

@Autowired
    private PurchaseDao underTest;

    @Test
    void shouldFindByReference(){
        String reference = "reference-1";
        Purchase entity = new Purchase();
        entity.setReference(reference);
        underTest.save(entity);
        Purchase loaded = underTest.findByReference(reference);
        assertThat(loaded.getReference()).isEqualTo(reference);
    }

    @Test
    void shouldDeleteByReference() {
        String reference = "reference-1";
        Purchase entity = new Purchase();
        entity.setReference(reference);
        underTest.save(entity);

        int result = underTest.deleteByReference(reference);

        Purchase loaded = underTest.findByReference(reference);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(1);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        Purchase entity = new Purchase();
        entity.setId(id);
        underTest.save(entity);
        Purchase loaded = underTest.findById(id).get();
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Purchase entity = new Purchase();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Purchase loaded = underTest.findById(id).get();
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Purchase> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Purchase> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }
    @Test
    void shouldSave(){
        Purchase given = constructSample(1);
        Purchase saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private Purchase constructSample(int i) {
		Purchase given = new Purchase();
        given.setReference("reference-"+i);
        given.setPurchaseDate(LocalDateTime.now());
        given.setImage("image-"+i);
        given.setTotal(BigDecimal.TEN);
        given.setDescription("description-"+i);
        given.setClient(new Client(1L));
        given.setPurchaseState(new PurchaseState(1L));
        return given;
    }

}
