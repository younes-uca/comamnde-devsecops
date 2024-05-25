package ma.zs.stocky.ws.facade.admin.money;


import ma.zs.stocky.bean.core.money.PurchaseItem;
import ma.zs.stocky.dao.criteria.core.money.PurchaseItemCriteria;
import ma.zs.stocky.service.facade.admin.money.PurchaseItemAdminService;
import ma.zs.stocky.ws.converter.money.PurchaseItemConverter;
import ma.zs.stocky.ws.dto.money.PurchaseItemDto;
import ma.zs.stocky.zynerator.util.PaginatedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/admin/purchaseItem/")
public class PurchaseItemRestAdmin {


    
    @PostMapping("import-data")
    public ResponseEntity<List<PurchaseItemDto>> importData(@RequestBody List<PurchaseItemDto> dtos) {
        List<PurchaseItem> items = converter.toItem(dtos);
        List<PurchaseItem> imported = this.service.importData(items);
        List<PurchaseItemDto> result = converter.toDto(imported);
        return ResponseEntity.ok(result);
    }


    @GetMapping("")
    public ResponseEntity<List<PurchaseItemDto>> findAll() throws Exception {
        ResponseEntity<List<PurchaseItemDto>> res = null;
        List<PurchaseItem> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<PurchaseItemDto> dtos = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @GetMapping("id/{id}")
    public ResponseEntity<PurchaseItemDto> findById(@PathVariable Long id) {
        PurchaseItem t = service.findById(id);
        if (t != null) {
            converter.init(true);
            PurchaseItemDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @PostMapping("")
    public ResponseEntity<PurchaseItemDto> save(@RequestBody PurchaseItemDto dto) throws Exception {
        if (dto != null) {
            converter.init(true);
            PurchaseItem myT = converter.toItem(dto);
            PurchaseItem t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            } else {
                PurchaseItemDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        } else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("")
    public ResponseEntity<PurchaseItemDto> update(@RequestBody PurchaseItemDto dto) throws Exception {
        ResponseEntity<PurchaseItemDto> res;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            PurchaseItem t = service.findById(dto.getId());
            converter.copy(dto, t);
            PurchaseItem updated = service.update(t);
            PurchaseItemDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @PostMapping("multiple")
    public ResponseEntity<List<PurchaseItemDto>> delete(@RequestBody List<PurchaseItemDto> dtos) throws Exception {
        ResponseEntity<List<PurchaseItemDto>> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<PurchaseItem> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @DeleteMapping("")
    public ResponseEntity<PurchaseItemDto> delete(@RequestBody PurchaseItemDto dto) throws Exception {
        ResponseEntity<PurchaseItemDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            PurchaseItem t = converter.toItem(dto);
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


    @GetMapping("detail/id/{id}")
    public ResponseEntity<PurchaseItemDto> findWithAssociatedLists(@PathVariable Long id) {
        PurchaseItem loaded = service.findWithAssociatedLists(id);
        converter.init(true);
        PurchaseItemDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("find-by-criteria")
    public ResponseEntity<List<PurchaseItemDto>> findByCriteria(@RequestBody PurchaseItemCriteria criteria) throws Exception {
        ResponseEntity<List<PurchaseItemDto>> res = null;
        List<PurchaseItem> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<PurchaseItemDto> dtos = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody PurchaseItemCriteria criteria) throws Exception {
        List<PurchaseItem> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<PurchaseItemDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody PurchaseItemCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }

    public List<PurchaseItemDto> findDtos(List<PurchaseItem> list) {
        converter.initObject(true);
        List<PurchaseItemDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<PurchaseItemDto> getDtoResponseEntity(PurchaseItemDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


    @Autowired
    private PurchaseItemAdminService service;
    @Autowired
    private PurchaseItemConverter converter;


}
