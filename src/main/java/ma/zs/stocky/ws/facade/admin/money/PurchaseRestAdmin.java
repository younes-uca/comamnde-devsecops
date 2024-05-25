package ma.zs.stocky.ws.facade.admin.money;
//Operation checked

import ma.zs.stocky.bean.core.money.Purchase;
import ma.zs.stocky.dao.criteria.core.money.PurchaseCriteria;
import ma.zs.stocky.service.facade.admin.money.PurchaseAdminService;
import ma.zs.stocky.ws.converter.money.PurchaseConverter;
import ma.zs.stocky.ws.dto.money.PurchaseDto;
import ma.zs.stocky.zynerator.util.PaginatedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/admin/purchase/")
public class PurchaseRestAdmin {


    
    @PostMapping("import-data")
    public ResponseEntity<List<PurchaseDto>> importData(@RequestBody List<PurchaseDto> dtos) {
        List<Purchase> items = converter.toItem(dtos);
        List<Purchase> imported = this.service.importData(items);
        List<PurchaseDto> result = converter.toDto(imported);
        return ResponseEntity.ok(result);
    }


    
    @GetMapping("")
    public ResponseEntity<List<PurchaseDto>> findAll() throws Exception {
        ResponseEntity<List<PurchaseDto>> res = null;
        List<Purchase> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<PurchaseDto> dtos = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    
    @GetMapping("optimized")
    public ResponseEntity<List<PurchaseDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<PurchaseDto>> res = null;
        List<Purchase> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<PurchaseDto> dtos = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    
    @GetMapping("id/{id}")
    public ResponseEntity<PurchaseDto> findById(@PathVariable Long id) {
        Purchase t = service.findById(id);
        if (t != null) {
            converter.init(true);
            PurchaseDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    
    @GetMapping("reference/{reference}")
    public ResponseEntity<PurchaseDto> findByReference(@PathVariable String reference) {
        Purchase t = service.findByReferenceEntity(new Purchase(reference));
        if (t != null) {
            converter.init(true);
            PurchaseDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    
    @PostMapping("")
    public ResponseEntity<PurchaseDto> save(@RequestBody PurchaseDto dto) throws Exception {
        if (dto != null) {
            converter.init(true);
            Purchase myT = converter.toItem(dto);
            Purchase t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            } else {
                PurchaseDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        } else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    
    @PutMapping("")
    public ResponseEntity<PurchaseDto> update(@RequestBody PurchaseDto dto) throws Exception {
        ResponseEntity<PurchaseDto> res;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Purchase t = service.findById(dto.getId());
            converter.copy(dto, t);
            Purchase updated = service.update(t);
            PurchaseDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    
    @PostMapping("multiple")
    public ResponseEntity<List<PurchaseDto>> delete(@RequestBody List<PurchaseDto> dtos) throws Exception {
        ResponseEntity<List<PurchaseDto>> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Purchase> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    
    @DeleteMapping("")
    public ResponseEntity<PurchaseDto> delete(@RequestBody PurchaseDto dto) throws Exception {
        ResponseEntity<PurchaseDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            Purchase t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws Exception {
        ResponseEntity<Long> res;
        HttpStatus status = HttpStatus.PRECONDITION_FAILED;
        if (id != null) {
            boolean resultDelete = service.deleteById(id);
            if (resultDelete) {
                status = HttpStatus.OK;
            }
        }
        res = new ResponseEntity<>(id, status);
        return res;
    }

    
    @DeleteMapping("multiple/id")
    public ResponseEntity<List<Long>> deleteByIdIn(@RequestBody List<Long> ids) throws Exception {
        ResponseEntity<List<Long>> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (ids != null) {
            service.deleteByIdIn(ids);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(ids, status);
        return res;
    }


    
    @GetMapping("client/id/{id}")
    public List<PurchaseDto> findByClientId(@PathVariable Long id) {
        return findDtos(service.findByClientId(id));
    }

    
    @DeleteMapping("client/id/{id}")
    public int deleteByClientId(@PathVariable Long id) {
        return service.deleteByClientId(id);
    }

    
    @GetMapping("purchaseState/code/{code}")
    public List<PurchaseDto> findByPurchaseStateCode(@PathVariable String code) {
        return findDtos(service.findByPurchaseStateCode(code));
    }

    
    @DeleteMapping("purchaseState/code/{code}")
    public int deleteByPurchaseStateCode(@PathVariable String code) {
        return service.deleteByPurchaseStateCode(code);
    }

    
    @GetMapping("detail/id/{id}")
    public ResponseEntity<PurchaseDto> findWithAssociatedLists(@PathVariable Long id) {
        Purchase loaded = service.findWithAssociatedLists(id);
        converter.init(true);
        PurchaseDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<PurchaseDto>> findByCriteria(@RequestBody PurchaseCriteria criteria) throws Exception {
        ResponseEntity<List<PurchaseDto>> res = null;
        List<Purchase> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<PurchaseDto> dtos = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody PurchaseCriteria criteria) throws Exception {
        List<Purchase> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initList(false);
        converter.initObject(true);
        List<PurchaseDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody PurchaseCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }

    public List<PurchaseDto> findDtos(List<Purchase> list) {
        converter.initList(false);
        converter.initObject(true);
        List<PurchaseDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<PurchaseDto> getDtoResponseEntity(PurchaseDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


    @Autowired
    private PurchaseAdminService service;
    @Autowired
    private PurchaseConverter converter;


}
