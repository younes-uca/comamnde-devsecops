package ma.zs.stocky.zynerator.security.ws.facade;


import ma.zs.stocky.zynerator.controller.AbstractController;
import ma.zs.stocky.zynerator.dto.FileTempDto;
import ma.zs.stocky.zynerator.security.bean.ModelPermissionUser;
import ma.zs.stocky.zynerator.security.dao.criteria.core.ModelPermissionUserCriteria;
import ma.zs.stocky.zynerator.security.service.facade.ModelPermissionUserService;
import ma.zs.stocky.zynerator.security.ws.converter.ModelPermissionUserConverter;
import ma.zs.stocky.zynerator.security.ws.dto.ModelPermissionUserDto;
import ma.zs.stocky.zynerator.util.PaginatedList;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/modelPermissionUser/")
public class ModelPermissionUserRest extends AbstractController<ModelPermissionUser, ModelPermissionUserDto, ModelPermissionUserCriteria, ModelPermissionUserService, ModelPermissionUserConverter> {


    @GetMapping("user/{username}")
    public List<ModelPermissionUserDto> findByUserUserName(@PathVariable String username) {
        return findDtos(service.findByUserUsername(username));
    }

    
    @RequestMapping(value = "upload", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<FileTempDto> uploadFileAndGetChecksum(@RequestBody MultipartFile file) throws Exception {
        return super.uploadFileAndGetChecksum(file);
    }

    
    @RequestMapping(value = "upload-multiple", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<List<FileTempDto>> uploadMultipleFileAndGetChecksum(@RequestBody MultipartFile[] files) throws Exception {
        return super.uploadMultipleFileAndGetChecksum(files);
    }

    
    @GetMapping("")
    public ResponseEntity<List<ModelPermissionUserDto>> findAll() throws Exception {
        return super.findAll();
    }

    
    @GetMapping("init-model-permission-user")
    public ResponseEntity<List<ModelPermissionUserDto>> initModelPermissionUser() {
        List<ModelPermissionUser> list = service.initModelPermissionUser();
        List<ModelPermissionUserDto> dtos = converter.toDto(list);
        HttpStatus status = dtos != null && !dtos.isEmpty() ? HttpStatus.OK : HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(dtos, status);
    }

    
    @GetMapping("init-security-model-permission-user")
    public ResponseEntity<List<ModelPermissionUserDto>> initSecurityModelPermissionUser() {
        List<ModelPermissionUser> list = service.initSecurityModelPermissionUser();
        List<ModelPermissionUserDto> dtos = converter.toDto(list);
        HttpStatus status = dtos != null && !dtos.isEmpty() ? HttpStatus.OK : HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(dtos, status);
    }


    
    @GetMapping("id/{id}")
    public ResponseEntity<ModelPermissionUserDto> findById(@PathVariable Long id) {
        return super.findById(id);
    }


    
    @PostMapping("")
    public ResponseEntity<ModelPermissionUserDto> save(@RequestBody ModelPermissionUserDto dto) throws Exception {
        return super.save(dto);
    }

    
    @PutMapping("")
    public ResponseEntity<ModelPermissionUserDto> update(@RequestBody ModelPermissionUserDto dto) throws Exception {
        return super.update(dto);
    }

    
    @PostMapping("multiple")
    public ResponseEntity<List<ModelPermissionUserDto>> delete(@RequestBody List<ModelPermissionUserDto> listToDelete) throws Exception {
        return super.delete(listToDelete);
    }

    
    @DeleteMapping("")
    public ResponseEntity<ModelPermissionUserDto> delete(@RequestBody ModelPermissionUserDto dto) throws Exception {
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


    
    @GetMapping("actionPermission/id/{id}")
    public List<ModelPermissionUserDto> findByActionPermissionId(@PathVariable Long id) {
        return findDtos(service.findByActionPermissionId(id));
    }

    
    @DeleteMapping("actionPermission/id/{id}")
    public int deleteByActionPermissionId(@PathVariable Long id) {
        return service.deleteByActionPermissionId(id);
    }

    
    @GetMapping("modelPermission/id/{id}")
    public List<ModelPermissionUserDto> findByModelPermissionId(@PathVariable Long id) {
        return findDtos(service.findByModelPermissionId(id));
    }

    
    @DeleteMapping("modelPermission/id/{id}")
    public int deleteByModelPermissionId(@PathVariable Long id) {
        return service.deleteByModelPermissionId(id);
    }

    
    @GetMapping("user/id/{id}")
    public List<ModelPermissionUserDto> findByUserId(@PathVariable Long id) {
        return findDtos(service.findByUserId(id));
    }

    
    @GetMapping("user/{username}/model/{modelReference}/action/{actionReference}")
    public Boolean findByUserUsernameAndModelPermissionReferenceAndActionPermissionReference(@PathVariable String username, @PathVariable String modelReference, @PathVariable String actionReference) {
        return service.findByUserUsernameAndModelPermissionReferenceAndActionPermissionReference(username, modelReference, actionReference);
    }

    
    @DeleteMapping("user/id/{id}")
    public int deleteByUserId(@PathVariable Long id) {
        return service.deleteByUserId(id);
    }

    
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ModelPermissionUserDto>> findByCriteria(@RequestBody ModelPermissionUserCriteria criteria) throws Exception {
        return super.findByCriteria(criteria);
    }

    
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ModelPermissionUserCriteria criteria) throws Exception {
        return super.findPaginatedByCriteria(criteria);
    }

    
    @PostMapping("export")
    public ResponseEntity<InputStreamResource> export(@RequestBody ModelPermissionUserCriteria criteria) throws Exception {
        return super.export(criteria);
    }

    
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ModelPermissionUserCriteria criteria) throws Exception {
        return super.getDataSize(criteria);
    }


    public ModelPermissionUserRest(ModelPermissionUserService service, ModelPermissionUserConverter converter) {
        super(service, converter);
    }


}
