package  ma.zs.stocky.dao.specification.core.commun;

import ma.zs.stocky.dao.criteria.core.commun.PurchaseStateCriteria;
import ma.zs.stocky.bean.core.commun.PurchaseState;
import ma.zs.stocky.zynerator.specification.AbstractSpecification;


public class PurchaseStateSpecification extends  AbstractSpecification<PurchaseStateCriteria, PurchaseState>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
    }

    public PurchaseStateSpecification(PurchaseStateCriteria criteria) {
        super(criteria);
    }

    public PurchaseStateSpecification(PurchaseStateCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
