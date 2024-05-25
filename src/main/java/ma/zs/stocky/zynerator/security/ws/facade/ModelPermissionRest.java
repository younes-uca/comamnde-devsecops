package ma.zs.stocky.zynerator.security.ws.facade;


import ma.zs.stocky.zynerator.controller.AbstractController;
import ma.zs.stocky.zynerator.dto.FileTempDto;
import ma.zs.stocky.zynerator.security.bean.ModelPermission;
import ma.zs.stocky.zynerator.security.dao.criteria.core.ModelPermissionCriteria;
import ma.zs.stocky.zynerator.security.service.facade.ModelPermissionService;
import ma.zs.stocky.zynerator.security.ws.converter.ModelPermissionConverter;
import ma.zs.stocky.zynerator.security.ws.dto.ModelPermissionDto;
import ma.zs.stocky.zynerator.util.PaginatedList;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/modelPermission/")
public class ModelPermissionRest extends AbstractController<ModelPermission, ModelPermissionDto, ModelPermissionCriteria, ModelPermissionService, ModelPermissionConverter> {


    @RequestMapping(value = "upload", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<FileTempDto> uploadFileAndGetChecksum(@RequestBody MultipartFile file) throws Exception {
        return super.uploadFileAndGetChecksum(file);
    }

    @RequestMapping(value = "upload-multiple", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<List<FileTempDto>> uploadMultipleFileAndGetChecksum(@RequestBody MultipartFile[] files) throws Exception {
        return super.uploadMultipleFileAndGetChecksum(files);
    }

    @GetMapping("")
    public ResponseEntity<List<ModelPermissionDto>> findAll() throws Exception {
        return super.findAll();
    }

    @GetMapping("optimized")
    public ResponseEntity<List<ModelPermissionDto>> findAllOptimized() throws Exception {
        return super.findAllOptimized();
    }

    @GetMapping("id/{id}")
    public ResponseEntity<ModelPermissionDto> findById(@PathVariable Long id) {
        return super.findById(id);
    }

    @GetMapping("reference/{reference}")
    public ResponseEntity<ModelPermissionDto> findByReference(@PathVariable String reference) {
        return super.findByReferenceEntity(new ModelPermission(reference));
    }

    @PostMapping("")
    public ResponseEntity<ModelPermissionDto> save(@RequestBody ModelPermissionDto dto) throws Exception {
        return super.save(dto);
    }

    @PutMapping("")
    public ResponseEntity<ModelPermissionDto> update(@RequestBody ModelPermissionDto dto) throws Exception {
        return super.update(dto);
    }

    @PostMapping("multiple")
    public ResponseEntity<List<ModelPermissionDto>> delete(@RequestBody List<ModelPermissionDto> listToDelete) throws Exception {
        return super.delete(listToDelete);
    }

    @DeleteMapping("")
    public ResponseEntity<ModelPermissionDto> delete(@RequestBody ModelPermissionDto dto) throws Exception {
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
    public ResponseEntity<List<ModelPermissionDto>> findByCriteria(@RequestBody ModelPermissionCriteria criteria) throws Exception {
        return super.findByCriteria(criteria);
    }

    
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ModelPermissionCriteria criteria) throws Exception {
        return super.findPaginatedByCriteria(criteria);
    }

    
    @PostMapping("export")
    public ResponseEntity<InputStreamResource> export(@RequestBody ModelPermissionCriteria criteria) throws Exception {
        return super.export(criteria);
    }

    
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ModelPermissionCriteria criteria) throws Exception {
        return super.getDataSize(criteria);
    }


    public ModelPermissionRest(ModelPermissionService service, ModelPermissionConverter converter) {
        super(service, converter);
    }


}
