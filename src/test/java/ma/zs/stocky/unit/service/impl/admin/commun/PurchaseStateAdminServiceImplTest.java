package ma.zs.stocky.unit.service.impl.admin.commun;

import ma.zs.stocky.bean.core.commun.PurchaseState;
import ma.zs.stocky.dao.facade.core.commun.PurchaseStateDao;
import ma.zs.stocky.service.impl.admin.commun.PurchaseStateAdminServiceImpl;

import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;



import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
class PurchaseStateAdminServiceImplTest {

    @Mock
    private PurchaseStateDao repository;
    private AutoCloseable autoCloseable;
    private PurchaseStateAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new PurchaseStateAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllPurchaseState() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSavePurchaseState() {
        // Given
        PurchaseState toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeletePurchaseState() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetPurchaseStateById() {
        // Given
        Long idToRetrieve = 1L; // Example PurchaseState ID to retrieve
        PurchaseState expected = new PurchaseState(); // You need to replace PurchaseState with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(Optional.of(expected));

        // When
        PurchaseState result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private PurchaseState constructSample(int i) {
		PurchaseState given = new PurchaseState();
        given.setLibelle("libelle-"+i);
        given.setCode("code-"+i);
        return given;
    }

}
