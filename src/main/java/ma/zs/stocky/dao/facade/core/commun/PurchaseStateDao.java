package ma.zs.stocky.dao.facade.core.commun;

import ma.zs.stocky.bean.core.commun.PurchaseState;
import ma.zs.stocky.zynerator.repository.AbstractRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PurchaseStateDao extends AbstractRepository<PurchaseState, Long> {
    PurchaseState findByCode(String code);

    int deleteByCode(String code);


    @Query("SELECT NEW PurchaseState(item.id,item.libelle) FROM PurchaseState item")
    List<PurchaseState> findAllOptimized();

}
