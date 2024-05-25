package ma.zs.stocky.ws.facade.admin.commun;

import io.swagger.v3.oas.annotations.Operation;
import ma.zs.stocky.bean.core.commun.PurchaseState;
import ma.zs.stocky.dao.criteria.core.commun.PurchaseStateCriteria;
import ma.zs.stocky.service.facade.admin.commun.PurchaseStateAdminService;
import ma.zs.stocky.ws.converter.commun.PurchaseStateConverter;
import ma.zs.stocky.ws.dto.commun.PurchaseStateDto;
import ma.zs.stocky.zynerator.util.PaginatedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/admin/purchaseState/")
public class PurchaseStateRestAdmin {


    @Operation(summary = "Import Data")
    @PostMapping("import-data")
    public ResponseEntity<List<PurchaseStateDto>> importData(@RequestBody List<PurchaseStateDto> dtos) {
        List<PurchaseState> items = converter.toItem(dtos);
        List<PurchaseState> imported = this.service.importData(items);
        List<PurchaseStateDto> result = converter.toDto(imported);
        return ResponseEntity.ok(result);
    }


    @Operation(summary = "Finds a list of all purchaseStates")
    @GetMapping("")
    public ResponseEntity<List<PurchaseStateDto>> findAll() throws Exception {
        ResponseEntity<List<PurchaseStateDto>> res = null;
        List<PurchaseState> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<PurchaseStateDto> dtos = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all purchaseStates")
    @GetMapping("optimized")
    public ResponseEntity<List<PurchaseStateDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<PurchaseStateDto>> res = null;
        List<PurchaseState> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<PurchaseStateDto> dtos = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a purchaseState by id")
    @GetMapping("id/{id}")
    public ResponseEntity<PurchaseStateDto> findById(@PathVariable Long id) {
        PurchaseState t = service.findById(id);
        if (t != null) {
            PurchaseStateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a purchaseState by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<PurchaseStateDto> findByLibelle(@PathVariable String libelle) {
        PurchaseState t = service.findByReferenceEntity(new PurchaseState(libelle));
        if (t != null) {
            PurchaseStateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  purchaseState")
    @PostMapping("")
    public ResponseEntity<PurchaseStateDto> save(@RequestBody PurchaseStateDto dto) throws Exception {
        if (dto != null) {
            PurchaseState myT = converter.toItem(dto);
            PurchaseState t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            } else {
                PurchaseStateDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        } else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  purchaseState")
    @PutMapping("")
    public ResponseEntity<PurchaseStateDto> update(@RequestBody PurchaseStateDto dto) throws Exception {
        ResponseEntity<PurchaseStateDto> res;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            PurchaseState t = service.findById(dto.getId());
            converter.copy(dto, t);
            PurchaseState updated = service.update(t);
            PurchaseStateDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of purchaseState")
    @PostMapping("multiple")
    public ResponseEntity<List<PurchaseStateDto>> delete(@RequestBody List<PurchaseStateDto> dtos) throws Exception {
        ResponseEntity<List<PurchaseStateDto>> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<PurchaseState> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified purchaseState")
    @DeleteMapping("")
    public ResponseEntity<PurchaseStateDto> delete(@RequestBody PurchaseStateDto dto) throws Exception {
        ResponseEntity<PurchaseStateDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            PurchaseState t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified purchaseState")
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

    @Operation(summary = "Delete multiple purchaseStates by ids")
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


    @Operation(summary = "Finds a purchaseState and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<PurchaseStateDto> findWithAssociatedLists(@PathVariable Long id) {
        PurchaseState loaded = service.findWithAssociatedLists(id);
        PurchaseStateDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds purchaseStates by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<PurchaseStateDto>> findByCriteria(@RequestBody PurchaseStateCriteria criteria) throws Exception {
        ResponseEntity<List<PurchaseStateDto>> res = null;
        List<PurchaseState> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<PurchaseStateDto> dtos = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated purchaseStates by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody PurchaseStateCriteria criteria) throws Exception {
        List<PurchaseState> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<PurchaseStateDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets purchaseState data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody PurchaseStateCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }

    public List<PurchaseStateDto> findDtos(List<PurchaseState> list) {
        List<PurchaseStateDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<PurchaseStateDto> getDtoResponseEntity(PurchaseStateDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


    @Autowired
    private PurchaseStateAdminService service;
    @Autowired
    private PurchaseStateConverter converter;


}
