package ma.zs.stocky.dao.facade.core.catalog;

import ma.zs.stocky.bean.core.catalog.Product;
import ma.zs.stocky.zynerator.repository.AbstractRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductDao extends AbstractRepository<Product, Long> {
    Product findByCode(String code);

    int deleteByCode(String code);


    @Query("SELECT NEW Product(item.id,item.reference) FROM Product item")
    List<Product> findAllOptimized();

}
