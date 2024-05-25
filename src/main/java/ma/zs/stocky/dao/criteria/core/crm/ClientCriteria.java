package ma.zs.stocky.dao.criteria.core.crm;


import ma.zs.stocky.zynerator.criteria.BaseCriteria;

public class ClientCriteria extends BaseCriteria {

    private String fullName;
    private String fullNameLike;
    private String email;
    private String emailLike;


    public ClientCriteria() {
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullNameLike() {
        return this.fullNameLike;
    }

    public void setFullNameLike(String fullNameLike) {
        this.fullNameLike = fullNameLike;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailLike() {
        return this.emailLike;
    }

    public void setEmailLike(String emailLike) {
        this.emailLike = emailLike;
    }


}
