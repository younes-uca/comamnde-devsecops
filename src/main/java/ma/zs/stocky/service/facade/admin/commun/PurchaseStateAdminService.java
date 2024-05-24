package ma.zs.stocky.service.facade.admin.commun;

import java.util.List;
import ma.zs.stocky.bean.core.commun.PurchaseState;
import ma.zs.stocky.dao.criteria.core.commun.PurchaseStateCriteria;
import ma.zs.stocky.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface PurchaseStateAdminService {







	PurchaseState create(PurchaseState t);

    PurchaseState update(PurchaseState t);

    List<PurchaseState> update(List<PurchaseState> ts,boolean createIfNotExist);

    PurchaseState findById(Long id);

    PurchaseState findOrSave(PurchaseState t);

    PurchaseState findByReferenceEntity(PurchaseState t);

    PurchaseState findWithAssociatedLists(Long id);

    List<PurchaseState> findAllOptimized();

    List<PurchaseState> findAll();

    List<PurchaseState> findByCriteria(PurchaseStateCriteria criteria);

    List<PurchaseState> findPaginatedByCriteria(PurchaseStateCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(PurchaseStateCriteria criteria);

    List<PurchaseState> delete(List<PurchaseState> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<PurchaseState>> getToBeSavedAndToBeDeleted(List<PurchaseState> oldList, List<PurchaseState> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<PurchaseState> importExcel(MultipartFile file);

    List<PurchaseState> importData(List<PurchaseState> items);

}
