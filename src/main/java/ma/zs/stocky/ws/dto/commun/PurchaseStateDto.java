package ma.zs.stocky.ws.dto.commun;

import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.stocky.zynerator.audit.Log;
import ma.zs.stocky.zynerator.dto.AuditBaseDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class PurchaseStateDto extends AuditBaseDto {

    private String libelle;
    private String code;


    public PurchaseStateDto() {
        super();
    }


    @Log
    public String getLibelle() {
        return this.libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Log
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}
