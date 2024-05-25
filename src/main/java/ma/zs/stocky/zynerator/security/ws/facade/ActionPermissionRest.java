package ma.zs.stocky.zynerator.security.ws.facade;


import ma.zs.stocky.zynerator.controller.AbstractController;
import ma.zs.stocky.zynerator.dto.FileTempDto;
import ma.zs.stocky.zynerator.security.bean.ActionPermission;
import ma.zs.stocky.zynerator.security.dao.criteria.core.ActionPermissionCriteria;
import ma.zs.stocky.zynerator.security.service.facade.ActionPermissionService;
import ma.zs.stocky.zynerator.security.ws.converter.ActionPermissionConverter;
import ma.zs.stocky.zynerator.security.ws.dto.ActionPermissionDto;
import ma.zs.stocky.zynerator.util.PaginatedList;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/actionPermission/")
public class ActionPermissionRest extends AbstractController<ActionPermission, ActionPermissionDto, ActionPermissionCriteria, ActionPermissionService, ActionPermissionConverter> {


    @RequestMapping(value = "upload", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<FileTempDto> uploadFileAndGetChecksum(@RequestBody MultipartFile file) throws Exception {
        return super.uploadFileAndGetChecksum(file);
    }

    @RequestMapping(value = "upload-multiple", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<List<FileTempDto>> uploadMultipleFileAndGetChecksum(@RequestBody MultipartFile[] files) throws Exception {
        return super.uploadMultipleFileAndGetChecksum(files);
    }

    @GetMapping("")
    public ResponseEntity<List<ActionPermissionDto>> findAll() throws Exception {
        return super.findAll();
    }

    @GetMapping("optimized")
    public ResponseEntity<List<ActionPermissionDto>> findAllOptimized() throws Exception {
        return super.findAllOptimized();
    }

    @GetMapping("id/{id}")
    public ResponseEntity<ActionPermissionDto> findById(@PathVariable Long id) {
        return super.findById(id);
    }

    @GetMapping("reference/{reference}")
    public ResponseEntity<ActionPermissionDto> findByReference(@PathVariable String reference) {
        return super.findByReferenceEntity(new ActionPermission(reference));
    }

    @PostMapping("")
    public ResponseEntity<ActionPermissionDto> save(@RequestBody ActionPermissionDto dto) throws Exception {
        return super.save(dto);
    }

    @PutMapping("")
    public ResponseEntity<ActionPermissionDto> update(@RequestBody ActionPermissionDto dto) throws Exception {
        return super.update(dto);
    }

    @PostMapping("multiple")
    public ResponseEntity<List<ActionPermissionDto>> delete(@RequestBody List<ActionPermissionDto> listToDelete) throws Exception {
        return super.delete(listToDelete);
    }

    @DeleteMapping("")
    public ResponseEntity<ActionPermissionDto> delete(@RequestBody ActionPermissionDto dto) throws Exception {
        return super.delete(dto);
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws Exception {
        return super.deleteById(id);
    }

    @DeleteMapping("multiple/id")
    public ResponseEntity<List<Long>> deleteByIdIn(@RequestBody List<Long> ids) throws Exception {
        return super.deleteByIdIn(ids);
    }


    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ActionPermissionDto>> findByCriteria(@RequestBody ActionPermissionCriteria criteria) throws Exception {
        return super.findByCriteria(criteria);
    }

    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ActionPermissionCriteria criteria) throws Exception {
        return super.findPaginatedByCriteria(criteria);
    }

    @PostMapping("export")
    public ResponseEntity<InputStreamResource> export(@RequestBody ActionPermissionCriteria criteria) throws Exception {
        return super.export(criteria);
    }

    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ActionPermissionCriteria criteria) throws Exception {
        return super.getDataSize(criteria);
    }


    public ActionPermissionRest(ActionPermissionService service, ActionPermissionConverter converter) {
        super(service, converter);
    }


}
