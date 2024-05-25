package ma.zs.stocky.zynerator.security.ws.facade;


import ma.zs.stocky.zynerator.controller.AbstractController;
import ma.zs.stocky.zynerator.dto.FileTempDto;
import ma.zs.stocky.zynerator.security.bean.User;
import ma.zs.stocky.zynerator.security.dao.criteria.core.UserCriteria;
import ma.zs.stocky.zynerator.security.payload.request.ChangePasswordRequest;
import ma.zs.stocky.zynerator.security.service.facade.UserService;
import ma.zs.stocky.zynerator.security.ws.converter.UserConverter;
import ma.zs.stocky.zynerator.security.ws.dto.UserDto;
import ma.zs.stocky.zynerator.util.PaginatedList;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/user/")
public class UserRest extends AbstractController<User, UserDto, UserCriteria, UserService, UserConverter> {


    
    @RequestMapping(value = "upload", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<FileTempDto> uploadFileAndGetChecksum(@RequestBody MultipartFile file) throws Exception {
        return super.uploadFileAndGetChecksum(file);
    }

    
    @RequestMapping(value = "upload-multiple", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<List<FileTempDto>> uploadMultipleFileAndGetChecksum(@RequestBody MultipartFile[] files) throws Exception {
        return super.uploadMultipleFileAndGetChecksum(files);
    }

    
    @GetMapping("")
    public ResponseEntity<List<UserDto>> findAll() throws Exception {
        return super.findAll();
    }


    
    @GetMapping("optimized")
    public ResponseEntity<List<UserDto>> findAllOptimized() throws Exception {
        return super.findAllOptimized();
    }

    
    @GetMapping("email/{email}")
    public ResponseEntity<UserDto> findByEmail(@PathVariable String email) {
        return super.findByReferenceEntity(new User(email));
    }

    
    @PostMapping("")
    public ResponseEntity<UserDto> save(@RequestBody UserDto dto) throws Exception {
        ResponseEntity<UserDto> save = super.save(dto);
        return save;
    }

    
    @PutMapping("")
    public ResponseEntity<UserDto> update(@RequestBody UserDto dto) throws Exception {
        return super.update(dto);
    }

    
    @PostMapping("multiple")
    public ResponseEntity<List<UserDto>> delete(@RequestBody List<UserDto> listToDelete) throws Exception {
        return super.delete(listToDelete);
    }

    
    @DeleteMapping("")
    public ResponseEntity<UserDto> delete(@RequestBody UserDto dto) throws Exception {
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

    
    @GetMapping("user-name/{username}")
    public ResponseEntity<UserDto> findByUserName(@PathVariable String username) {
        User user = service.findByUsername(username);
        UserDto userDto = converter.toDto(user);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    
    @GetMapping("id/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id) {
        return super.findWithAssociatedLists(id);
    }

    
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<UserDto>> findByCriteria(@RequestBody UserCriteria criteria) throws Exception {
        return super.findByCriteria(criteria);
    }

    
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody UserCriteria criteria) throws Exception {
        return super.findPaginatedByCriteria(criteria);
    }

    
    @PostMapping("export")
    public ResponseEntity<InputStreamResource> export(@RequestBody UserCriteria criteria) throws Exception {
        return super.export(criteria);
    }

    
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody UserCriteria criteria) throws Exception {
        return super.getDataSize(criteria);
    }

    @GetMapping("/username/{username}")
    public User findByUsernameWithRoles(@PathVariable String username) {
        return service.findByUsernameWithRoles(username);
    }

    @DeleteMapping("/username/{username}")
    public int deleteByUsername(@PathVariable String username) {
        return service.deleteByUsername(username);
    }

    @PostMapping("/changePassword")
    public boolean changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        return service.changePassword(changePasswordRequest.getUsername(), changePasswordRequest.getPassword());
    }

    @GetMapping("/user/{username}")
    public User findByUsername(@PathVariable String username) {
        return service.findByUsername(username);
    }


    public UserRest(UserService service, UserConverter converter) {
        super(service, converter);
    }


}
