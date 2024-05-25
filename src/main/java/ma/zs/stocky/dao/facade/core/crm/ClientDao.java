package ma.zs.stocky.dao.facade.core.crm;

import ma.zs.stocky.bean.core.crm.Client;
import ma.zs.stocky.zynerator.repository.AbstractRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ClientDao extends AbstractRepository<Client, Long> {
    Client findByEmail(String email);

    int deleteByEmail(String email);


    @Query("SELECT NEW Client(item.id,item.fullName) FROM Client item")
    List<Client> findAllOptimized();

}
