package ma.zs.stocky.service.impl.admin.money;


import ma.zs.stocky.bean.core.money.Purchase;
import ma.zs.stocky.bean.core.money.PurchaseItem;
import ma.zs.stocky.dao.criteria.core.money.PurchaseCriteria;
import ma.zs.stocky.dao.facade.core.money.PurchaseDao;
import ma.zs.stocky.dao.specification.core.money.PurchaseSpecification;
import ma.zs.stocky.service.facade.admin.commun.PurchaseStateAdminService;
import ma.zs.stocky.service.facade.admin.crm.ClientAdminService;
import ma.zs.stocky.service.facade.admin.money.PurchaseAdminService;
import ma.zs.stocky.service.facade.admin.money.PurchaseItemAdminService;
import ma.zs.stocky.zynerator.exception.EntityNotFoundException;
import ma.zs.stocky.zynerator.util.ListUtil;
import ma.zs.stocky.zynerator.util.RefelexivityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseAdminServiceImpl implements PurchaseAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Purchase update(Purchase t) {
        Purchase loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Purchase.class.getSimpleName(), t.getId().toString()});
        } else {
            updateWithAssociatedLists(t);
            dao.save(t);
            return loadedItem;
        }
    }

    public Purchase findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Purchase findOrSave(Purchase t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Purchase result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Purchase> findAll() {
        return dao.findAll();
    }

    public List<Purchase> findByCriteria(PurchaseCriteria criteria) {
        List<Purchase> content = null;
        if (criteria != null) {
            PurchaseSpecification mySpecification = constructSpecification(criteria);
            if (criteria.isPeagable()) {
                Pageable pageable = PageRequest.of(0, criteria.getMaxResults());
                content = dao.findAll(mySpecification, pageable).getContent();
            } else {
                content = dao.findAll(mySpecification);
            }
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private PurchaseSpecification constructSpecification(PurchaseCriteria criteria) {
        PurchaseSpecification mySpecification = (PurchaseSpecification) RefelexivityUtil.constructObjectUsingOneParam(PurchaseSpecification.class, criteria);
        return mySpecification;
    }

    public List<Purchase> findPaginatedByCriteria(PurchaseCriteria criteria, int page, int pageSize, String order, String sortField) {
        PurchaseSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(PurchaseCriteria criteria) {
        PurchaseSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Purchase> findByClientId(Long id) {
        return dao.findByClientId(id);
    }

    public int deleteByClientId(Long id) {
        return dao.deleteByClientId(id);
    }

    public long countByClientEmail(String email) {
        return dao.countByClientEmail(email);
    }

    public List<Purchase> findByPurchaseStateCode(String code) {
        return dao.findByPurchaseStateCode(code);
    }

    public int deleteByPurchaseStateCode(String code) {
        return dao.deleteByPurchaseStateCode(code);
    }

    public long countByPurchaseStateCode(String code) {
        return dao.countByPurchaseStateCode(code);
    }

    public boolean deleteById(Long id) {
        boolean condition = deleteByIdCheckCondition(id);
        if (condition) {
            deleteAssociatedLists(id);
            dao.deleteById(id);
        }
        return condition;
    }

    public boolean deleteByIdCheckCondition(Long id) {
        return true;
    }

    public void deleteByIdIn(List<Long> ids) {
        //dao.deleteByIdIn(ids);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public int delete(Purchase t) {
        int result = 0;
        if (t != null) {
            deleteAssociatedLists(t.getId());
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }

    @Transactional
    public void deleteAssociatedLists(Long id) {
        purchaseItemService.deleteByPurchaseId(id);
    }


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Purchase> delete(List<Purchase> list) {
        List<Purchase> result = new ArrayList();
        if (list != null) {
            for (Purchase t : list) {
                int count = delete(t);
                if (count == 0) {
                    result.add(t);
                }
            }
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Purchase create(Purchase t) {
        Purchase loaded = findByReferenceEntity(t);
        Purchase saved;
        if (loaded == null) {
            saved = dao.save(t);
            if (t.getPurchaseItems() != null) {
                t.getPurchaseItems().forEach(element -> {
                    element.setPurchase(saved);
                    purchaseItemService.create(element);
                });
            }
        } else {
            saved = null;
        }
        return saved;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Purchase> create(List<Purchase> ts) {
        List<Purchase> result = new ArrayList<>();
        if (ts != null) {
            for (Purchase t : ts) {
                Purchase created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Purchase findWithAssociatedLists(Long id) {
        Purchase result = dao.findById(id).orElse(null);
        if (result != null && result.getId() != null) {
            result.setPurchaseItems(purchaseItemService.findByPurchaseId(id));
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Purchase> update(List<Purchase> ts, boolean createIfNotExist) {
        List<Purchase> result = new ArrayList<>();
        if (ts != null) {
            for (Purchase t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Purchase loadedItem = dao.findById(t.getId()).orElse(null);
                    if (createIfNotExist && (t.getId() == null || loadedItem == null)) {
                        dao.save(t);
                    } else if (t.getId() != null && loadedItem != null) {
                        dao.save(t);
                    } else {
                        result.add(t);
                    }
                }
            }
        }
        return result;
    }

    public void updateWithAssociatedLists(Purchase purchase) {
        if (purchase != null && purchase.getId() != null) {
            List<List<PurchaseItem>> resultPurchaseItems = purchaseItemService.getToBeSavedAndToBeDeleted(purchaseItemService.findByPurchaseId(purchase.getId()), purchase.getPurchaseItems());
            purchaseItemService.delete(resultPurchaseItems.get(1));
            ListUtil.emptyIfNull(resultPurchaseItems.get(0)).forEach(e -> e.setPurchase(purchase));
            purchaseItemService.update(resultPurchaseItems.get(0), true);
        }
    }


    public Purchase findByReferenceEntity(Purchase t) {
        return t == null ? null : dao.findByReference(t.getReference());
    }

    public void findOrSaveAssociatedObject(Purchase t) {
        if (t != null) {
            t.setClient(clientService.findOrSave(t.getClient()));
            t.setPurchaseState(purchaseStateService.findOrSave(t.getPurchaseState()));
        }
    }


    public List<Purchase> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Purchase>> getToBeSavedAndToBeDeleted(List<Purchase> oldList, List<Purchase> newList) {
        List<List<Purchase>> result = new ArrayList<>();
        List<Purchase> resultDelete = new ArrayList<>();
        List<Purchase> resultUpdateOrSave = new ArrayList<>();
        if (ListUtil.isEmpty(oldList) && ListUtil.isNotEmpty(newList)) {
            resultUpdateOrSave.addAll(newList);
        } else if (ListUtil.isEmpty(newList) && ListUtil.isNotEmpty(oldList)) {
            resultDelete.addAll(oldList);
        } else if (ListUtil.isNotEmpty(newList) && ListUtil.isNotEmpty(oldList)) {
            for (int i = 0; i < oldList.size(); i++) {
                Purchase myOld = oldList.get(i);
                Purchase t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Purchase myNew = newList.get(i);
                Purchase t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
        }
        result.add(resultUpdateOrSave);
        result.add(resultDelete);
        return result;
    }


    public List<Purchase> importData(List<Purchase> items) {
        List<Purchase> list = new ArrayList<>();
        for (Purchase t : items) {
            Purchase founded = findByReferenceEntity(t);
            if (founded == null) {
                findOrSaveAssociatedObject(t);
                dao.save(t);
            } else {
                list.add(founded);
            }
        }
        return list;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Purchase> importExcel(MultipartFile file) {
        return null;
    }


    public PurchaseAdminServiceImpl(PurchaseDao dao) {
        this.dao = dao;
    }

    @Autowired
    private PurchaseStateAdminService purchaseStateService;
    @Autowired
    private PurchaseItemAdminService purchaseItemService;
    @Autowired
    private ClientAdminService clientService;

    private PurchaseDao dao;


}
