package ma.zs.stocky.service.impl.admin.commun;


import ma.zs.stocky.bean.core.commun.PurchaseState;
import ma.zs.stocky.dao.criteria.core.commun.PurchaseStateCriteria;
import ma.zs.stocky.dao.facade.core.commun.PurchaseStateDao;
import ma.zs.stocky.dao.specification.core.commun.PurchaseStateSpecification;
import ma.zs.stocky.service.facade.admin.commun.PurchaseStateAdminService;
import ma.zs.stocky.zynerator.exception.EntityNotFoundException;
import ma.zs.stocky.zynerator.util.ListUtil;
import ma.zs.stocky.zynerator.util.RefelexivityUtil;
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
public class PurchaseStateAdminServiceImpl implements PurchaseStateAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public PurchaseState update(PurchaseState t) {
        PurchaseState loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{PurchaseState.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public PurchaseState findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public PurchaseState findOrSave(PurchaseState t) {
        if (t != null) {
            PurchaseState result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<PurchaseState> findAll() {
        return dao.findAll();
    }

    public List<PurchaseState> findByCriteria(PurchaseStateCriteria criteria) {
        List<PurchaseState> content = null;
        if (criteria != null) {
            PurchaseStateSpecification mySpecification = constructSpecification(criteria);
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


    private PurchaseStateSpecification constructSpecification(PurchaseStateCriteria criteria) {
        PurchaseStateSpecification mySpecification = (PurchaseStateSpecification) RefelexivityUtil.constructObjectUsingOneParam(PurchaseStateSpecification.class, criteria);
        return mySpecification;
    }

    public List<PurchaseState> findPaginatedByCriteria(PurchaseStateCriteria criteria, int page, int pageSize, String order, String sortField) {
        PurchaseStateSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(PurchaseStateCriteria criteria) {
        PurchaseStateSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }


    public boolean deleteById(Long id) {
        boolean condition = deleteByIdCheckCondition(id);
        if (condition) {
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
    public int delete(PurchaseState t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<PurchaseState> delete(List<PurchaseState> list) {
        List<PurchaseState> result = new ArrayList();
        if (list != null) {
            for (PurchaseState t : list) {
                int count = delete(t);
                if (count == 0) {
                    result.add(t);
                }
            }
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public PurchaseState create(PurchaseState t) {
        PurchaseState loaded = findByReferenceEntity(t);
        PurchaseState saved;
        if (loaded == null) {
            saved = dao.save(t);
        } else {
            saved = null;
        }
        return saved;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<PurchaseState> create(List<PurchaseState> ts) {
        List<PurchaseState> result = new ArrayList<>();
        if (ts != null) {
            for (PurchaseState t : ts) {
                PurchaseState created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public PurchaseState findWithAssociatedLists(Long id) {
        PurchaseState result = dao.findById(id).orElse(null);
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<PurchaseState> update(List<PurchaseState> ts, boolean createIfNotExist) {
        List<PurchaseState> result = new ArrayList<>();
        if (ts != null) {
            for (PurchaseState t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    PurchaseState loadedItem = dao.findById(t.getId()).orElse(null);
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


    public PurchaseState findByReferenceEntity(PurchaseState t) {
        return t == null ? null : dao.findByCode(t.getCode());
    }


    public List<PurchaseState> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<PurchaseState>> getToBeSavedAndToBeDeleted(List<PurchaseState> oldList, List<PurchaseState> newList) {
        List<List<PurchaseState>> result = new ArrayList<>();
        List<PurchaseState> resultDelete = new ArrayList<>();
        List<PurchaseState> resultUpdateOrSave = new ArrayList<>();
        if (ListUtil.isEmpty(oldList) && ListUtil.isNotEmpty(newList)) {
            resultUpdateOrSave.addAll(newList);
        } else if (ListUtil.isEmpty(newList) && ListUtil.isNotEmpty(oldList)) {
            resultDelete.addAll(oldList);
        } else if (ListUtil.isNotEmpty(newList) && ListUtil.isNotEmpty(oldList)) {
            for (int i = 0; i < oldList.size(); i++) {
                PurchaseState myOld = oldList.get(i);
                PurchaseState t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                PurchaseState myNew = newList.get(i);
                PurchaseState t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
        }
        result.add(resultUpdateOrSave);
        result.add(resultDelete);
        return result;
    }


    public List<PurchaseState> importData(List<PurchaseState> items) {
        List<PurchaseState> list = new ArrayList<>();
        for (PurchaseState t : items) {
            PurchaseState founded = findByReferenceEntity(t);
            if (founded == null) {
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
    public List<PurchaseState> importExcel(MultipartFile file) {
        return null;
    }


    public PurchaseStateAdminServiceImpl(PurchaseStateDao dao) {
        this.dao = dao;
    }

    private PurchaseStateDao dao;


}
