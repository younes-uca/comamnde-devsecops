package ma.zs.stocky.bean.core.commun;

import java.util.Objects;







import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.stocky.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "purchase_state")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="purchase_state_seq",sequenceName="purchase_state_seq",allocationSize=1, initialValue = 1)
public class PurchaseState  extends BaseEntity     {

    private Long id;



    @Column(length = 500)
    private String libelle;

    @Column(length = 500)
    private String code;



    public PurchaseState(){
        super();
    }

    public PurchaseState(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public PurchaseState(String libelle){
        this.libelle = libelle ;
    }
    public PurchaseState(String libelle,String code){
        this.libelle=libelle;
        this.code=code;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="purchase_state_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }
    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }

    @Transient
    public String getLabel() {
        label = libelle;
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseState purchaseState = (PurchaseState) o;
        return id != null && id.equals(purchaseState.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

