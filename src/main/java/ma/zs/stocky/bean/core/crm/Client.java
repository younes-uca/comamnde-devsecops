package ma.zs.stocky.bean.core.crm;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import ma.zs.stocky.zynerator.bean.BaseEntity;

import java.util.Objects;

@Entity
@Table(name = "client")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name = "client_seq", sequenceName = "client_seq", allocationSize = 1, initialValue = 1)
public class Client extends BaseEntity {

    private Long id;


    @Column(length = 500)
    private String fullName;

    @Column(length = 500)
    private String email;


    public Client() {
        super();
    }

    public Client(Long id) {
        this.id = id;
    }

    public Client(Long id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public Client(String fullName) {
        this.fullName = fullName;
    }


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_seq")
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Transient
    public String getLabel() {
        label = fullName;
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id != null && id.equals(client.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

