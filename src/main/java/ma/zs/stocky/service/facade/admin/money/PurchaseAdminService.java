package ma.zs.stocky.service.facade.admin.money;

import java.util.List;
import ma.zs.stocky.bean.core.money.Purchase;
import ma.zs.stocky.dao.criteria.core.money.PurchaseCriteria;
import ma.zs.stocky.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface PurchaseAdminService {



    List<Purchase> findByClientId(Long id);
    int deleteByClientId(Long id);
    long countByClientEmail(String email);
    List<Purchase> findByPurchaseStateCode(String code);
    int deleteByPurchaseStateCode(String code);
    long countByPurchaseStateCode(String code);




	Purchase create(Purchase t);

    Purchase update(Purchase t);

    List<Purchase> update(List<Purchase> ts,boolean createIfNotExist);

    Purchase findById(Long id);

    Purchase findOrSave(Purchase t);

    Purchase findByReferenceEntity(Purchase t);

    Purchase findWithAssociatedLists(Long id);

    List<Purchase> findAllOptimized();

    List<Purchase> findAll();

    List<Purchase> findByCriteria(PurchaseCriteria criteria);

    List<Purchase> findPaginatedByCriteria(PurchaseCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(PurchaseCriteria criteria);

    List<Purchase> delete(List<Purchase> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Purchase>> getToBeSavedAndToBeDeleted(List<Purchase> oldList, List<Purchase> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Purchase> importExcel(MultipartFile file);

    List<Purchase> importData(List<Purchase> items);

}
