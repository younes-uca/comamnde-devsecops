package ma.zs.stocky.zynerator.security.ws.facade;


import ma.zs.stocky.zynerator.controller.AbstractController;
import ma.zs.stocky.zynerator.dto.FileTempDto;
import ma.zs.stocky.zynerator.security.bean.RoleUser;
import ma.zs.stocky.zynerator.security.dao.criteria.core.RoleUserCriteria;
import ma.zs.stocky.zynerator.security.service.facade.RoleUserService;
import ma.zs.stocky.zynerator.security.ws.converter.RoleUserConverter;
import ma.zs.stocky.zynerator.security.ws.dto.RoleUserDto;
import ma.zs.stocky.zynerator.util.PaginatedList;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/roleUser/")
public class RoleUserRest extends AbstractController<RoleUser, RoleUserDto, RoleUserCriteria, RoleUserService, RoleUserConverter> {


    
    @RequestMapping(value = "upload", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<FileTempDto> uploadFileAndGetChecksum(@RequestBody MultipartFile file) throws Exception {
        return super.uploadFileAndGetChecksum(file);
    }

    
    @RequestMapping(value = "upload-multiple", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<List<FileTempDto>> uploadMultipleFileAndGetChecksum(@RequestBody MultipartFile[] files) throws Exception {
        return super.uploadMultipleFileAndGetChecksum(files);
    }

    
    @GetMapping("")
    public ResponseEntity<List<RoleUserDto>> findAll() throws Exception {
        return super.findAll();
    }


    
    @GetMapping("id/{id}")
    public ResponseEntity<RoleUserDto> findById(@PathVariable Long id) {
        return super.findById(id);
    }


    
    @PostMapping("")
    public ResponseEntity<RoleUserDto> save(@RequestBody RoleUserDto dto) throws Exception {
        return super.save(dto);
    }

    
    @PutMapping("")
    public ResponseEntity<RoleUserDto> update(@RequestBody RoleUserDto dto) throws Exception {
        return super.update(dto);
    }

    
    @PostMapping("multiple")
    public ResponseEntity<List<RoleUserDto>> delete(@RequestBody List<RoleUserDto> listToDelete) throws Exception {
        return super.delete(listToDelete);
    }

    
    @DeleteMapping("")
    public ResponseEntity<RoleUserDto> delete(@RequestBody RoleUserDto dto) throws Exception {
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


    
    @GetMapping("role/id/{id}")
    public List<RoleUserDto> findByRoleId(@PathVariable Long id) {
        return findDtos(service.findByRoleId(id));
    }

    
    @DeleteMapping("role/id/{id}")
    public int deleteByRoleId(@PathVariable Long id) {
        return service.deleteByRoleId(id);
    }

    
    @GetMapping("user/id/{id}")
    public List<RoleUserDto> findByUserId(@PathVariable Long id) {
        return findDtos(service.findByUserId(id));
    }

    
    @DeleteMapping("user/id/{id}")
    public int deleteByUserId(@PathVariable Long id) {
        return service.deleteByUserId(id);
    }

    
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<RoleUserDto>> findByCriteria(@RequestBody RoleUserCriteria criteria) throws Exception {
        return super.findByCriteria(criteria);
    }

    
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody RoleUserCriteria criteria) throws Exception {
        return super.findPaginatedByCriteria(criteria);
    }

    
    @PostMapping("export")
    public ResponseEntity<InputStreamResource> export(@RequestBody RoleUserCriteria criteria) throws Exception {
        return super.export(criteria);
    }

    
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody RoleUserCriteria criteria) throws Exception {
        return super.getDataSize(criteria);
    }


    public RoleUserRest(RoleUserService service, RoleUserConverter converter) {
        super(service, converter);
    }


}
