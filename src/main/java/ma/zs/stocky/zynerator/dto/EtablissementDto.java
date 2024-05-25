package ma.zs.stocky.zynerator.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.stocky.zynerator.audit.Log;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class EtablissementDto extends AuditBaseDto {

    private String libelle;
    private String code;

    public EtablissementDto() {
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
