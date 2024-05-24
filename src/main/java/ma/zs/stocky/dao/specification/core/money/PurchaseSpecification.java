package  ma.zs.stocky.dao.specification.core.money;

import ma.zs.stocky.dao.criteria.core.money.PurchaseCriteria;
import ma.zs.stocky.bean.core.money.Purchase;
import ma.zs.stocky.zynerator.specification.AbstractSpecification;


public class PurchaseSpecification extends  AbstractSpecification<PurchaseCriteria, Purchase>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("reference", criteria.getReference(),criteria.getReferenceLike());
        addPredicate("purchaseDate", criteria.getPurchaseDate(), criteria.getPurchaseDateFrom(), criteria.getPurchaseDateTo());
        addPredicate("image", criteria.getImage(),criteria.getImageLike());
        addPredicateBigDecimal("total", criteria.getTotal(), criteria.getTotalMin(), criteria.getTotalMax());
        addPredicateFk("client","id", criteria.getClient()==null?null:criteria.getClient().getId());
        addPredicateFk("client","id", criteria.getClients());
        addPredicateFk("client","email", criteria.getClient()==null?null:criteria.getClient().getEmail());
        addPredicateFk("purchaseState","id", criteria.getPurchaseState()==null?null:criteria.getPurchaseState().getId());
        addPredicateFk("purchaseState","id", criteria.getPurchaseStates());
        addPredicateFk("purchaseState","code", criteria.getPurchaseState()==null?null:criteria.getPurchaseState().getCode());
    }

    public PurchaseSpecification(PurchaseCriteria criteria) {
        super(criteria);
    }

    public PurchaseSpecification(PurchaseCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
