package ma.zs.stocky.zynerator.security.ws.facade;


import ma.zs.stocky.zynerator.controller.AbstractController;
import ma.zs.stocky.zynerator.dto.FileTempDto;
import ma.zs.stocky.zynerator.security.bean.Role;
import ma.zs.stocky.zynerator.security.dao.criteria.core.RoleCriteria;
import ma.zs.stocky.zynerator.security.service.facade.RoleService;
import ma.zs.stocky.zynerator.security.ws.converter.RoleConverter;
import ma.zs.stocky.zynerator.security.ws.dto.RoleDto;
import ma.zs.stocky.zynerator.util.PaginatedList;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/role/")
public class RoleRest extends AbstractController<Role, RoleDto, RoleCriteria, RoleService, RoleConverter> {


    
    @RequestMapping(value = "upload", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<FileTempDto> uploadFileAndGetChecksum(@RequestBody MultipartFile file) throws Exception {
        return super.uploadFileAndGetChecksum(file);
    }

    
    @RequestMapping(value = "upload-multiple", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<List<FileTempDto>> uploadMultipleFileAndGetChecksum(@RequestBody MultipartFile[] files) throws Exception {
        return super.uploadMultipleFileAndGetChecksum(files);
    }

    
    @GetMapping("")
    public ResponseEntity<List<RoleDto>> findAll() throws Exception {
        return super.findAll();
    }

    
    @GetMapping("optimized")
    public ResponseEntity<List<RoleDto>> findAllOptimized() throws Exception {
        return super.findAllOptimized();
    }

    
    @GetMapping("id/{id}")
    public ResponseEntity<RoleDto> findById(@PathVariable Long id) {
        return super.findById(id);
    }

    
    @GetMapping("authority/{authority}")
    public ResponseEntity<RoleDto> findByAuthority(@PathVariable String authority) {
        return super.findByReferenceEntity(new Role(authority));
    }

    
    @PostMapping("")
    public ResponseEntity<RoleDto> save(@RequestBody RoleDto dto) throws Exception {
        return super.save(dto);
    }

    
    @PutMapping("")
    public ResponseEntity<RoleDto> update(@RequestBody RoleDto dto) throws Exception {
        return super.update(dto);
    }

    
    @PostMapping("multiple")
    public ResponseEntity<List<RoleDto>> delete(@RequestBody List<RoleDto> listToDelete) throws Exception {
        return super.delete(listToDelete);
    }

    
    @DeleteMapping("")
    public ResponseEntity<RoleDto> delete(@RequestBody RoleDto dto) throws Exception {
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
    public ResponseEntity<List<RoleDto>> findByCriteria(@RequestBody RoleCriteria criteria) throws Exception {
        return super.findByCriteria(criteria);
    }

    
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody RoleCriteria criteria) throws Exception {
        return super.findPaginatedByCriteria(criteria);
    }

    
    @PostMapping("export")
    public ResponseEntity<InputStreamResource> export(@RequestBody RoleCriteria criteria) throws Exception {
        return super.export(criteria);
    }

    
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody RoleCriteria criteria) throws Exception {
        return super.getDataSize(criteria);
    }


    public RoleRest(RoleService service, RoleConverter converter) {
        super(service, converter);
    }


}
