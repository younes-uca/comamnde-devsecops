package ma.zs.stocky.ws.dto.crm;

import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.stocky.zynerator.audit.Log;
import ma.zs.stocky.zynerator.dto.AuditBaseDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientDto extends AuditBaseDto {

    private String fullName;
    private String email;


    public ClientDto() {
        super();
    }


    @Log
    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Log
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
