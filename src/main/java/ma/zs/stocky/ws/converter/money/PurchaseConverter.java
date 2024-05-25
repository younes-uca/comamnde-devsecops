package ma.zs.stocky.ws.converter.money;

import ma.zs.stocky.bean.core.commun.PurchaseState;
import ma.zs.stocky.bean.core.crm.Client;
import ma.zs.stocky.bean.core.money.Purchase;
import ma.zs.stocky.ws.converter.catalog.ProductConverter;
import ma.zs.stocky.ws.converter.commun.PurchaseStateConverter;
import ma.zs.stocky.ws.converter.crm.ClientConverter;
import ma.zs.stocky.ws.dto.money.PurchaseDto;
import ma.zs.stocky.zynerator.converter.AbstractConverterHelper;
import ma.zs.stocky.zynerator.util.DateUtil;
import ma.zs.stocky.zynerator.util.ListUtil;
import ma.zs.stocky.zynerator.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PurchaseConverter {

    @Autowired
    private PurchaseStateConverter purchaseStateConverter;
    @Autowired
    private ProductConverter productConverter;
    @Autowired
    private PurchaseItemConverter purchaseItemConverter;
    @Autowired
    private ClientConverter clientConverter;
    private boolean client;
    private boolean purchaseState;
    private boolean purchaseItems;

    public PurchaseConverter() {
        init(true);
    }


    public Purchase toItem(PurchaseDto dto) {
        if (dto == null) {
            return null;
        } else {
            Purchase item = new Purchase();
            if (StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if (StringUtil.isNotEmpty(dto.getReference()))
                item.setReference(dto.getReference());
            if (StringUtil.isNotEmpty(dto.getPurchaseDate()))
                item.setPurchaseDate(DateUtil.stringEnToDate(dto.getPurchaseDate()));
            if (StringUtil.isNotEmpty(dto.getImage()))
                item.setImage(dto.getImage());
            if (StringUtil.isNotEmpty(dto.getTotal()))
                item.setTotal(dto.getTotal());
            if (StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if (this.client && dto.getClient() != null)
                item.setClient(clientConverter.toItem(dto.getClient()));

            if (this.purchaseState && dto.getPurchaseState() != null)
                item.setPurchaseState(purchaseStateConverter.toItem(dto.getPurchaseState()));


            if (this.purchaseItems && ListUtil.isNotEmpty(dto.getPurchaseItems()))
                item.setPurchaseItems(purchaseItemConverter.toItem(dto.getPurchaseItems()));


            return item;
        }
    }


    public PurchaseDto toDto(Purchase item) {
        if (item == null) {
            return null;
        } else {
            PurchaseDto dto = new PurchaseDto();
            if (StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if (StringUtil.isNotEmpty(item.getReference()))
                dto.setReference(item.getReference());
            if (item.getPurchaseDate() != null)
                dto.setPurchaseDate(DateUtil.dateTimeToString(item.getPurchaseDate()));
            if (StringUtil.isNotEmpty(item.getImage()))
                dto.setImage(item.getImage());
            if (StringUtil.isNotEmpty(item.getTotal()))
                dto.setTotal(item.getTotal());
            if (StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if (this.client && item.getClient() != null) {
                dto.setClient(clientConverter.toDto(item.getClient()));

            }
            if (this.purchaseState && item.getPurchaseState() != null) {
                dto.setPurchaseState(purchaseStateConverter.toDto(item.getPurchaseState()));

            }
            if (this.purchaseItems && ListUtil.isNotEmpty(item.getPurchaseItems())) {
                purchaseItemConverter.init(true);
                purchaseItemConverter.setPurchase(false);
                dto.setPurchaseItems(purchaseItemConverter.toDto(item.getPurchaseItems()));
                purchaseItemConverter.setPurchase(true);

            }


            return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.purchaseItems = value;
    }

    public void initObject(boolean value) {
        this.client = value;
        this.purchaseState = value;
    }

    public List<Purchase> toItem(List<PurchaseDto> dtos) {
        List<Purchase> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (PurchaseDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<PurchaseDto> toDto(List<Purchase> items) {
        List<PurchaseDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Purchase item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(PurchaseDto dto, Purchase t) {
        BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (t.getClient() == null && dto.getClient() != null) {
            t.setClient(new Client());
        }
        if (t.getPurchaseState() == null && dto.getPurchaseState() != null) {
            t.setPurchaseState(new PurchaseState());
        }
        if (dto.getClient() != null)
            clientConverter.copy(dto.getClient(), t.getClient());
        if (dto.getPurchaseState() != null)
            purchaseStateConverter.copy(dto.getPurchaseState(), t.getPurchaseState());
        if (dto.getPurchaseItems() != null)
            t.setPurchaseItems(purchaseItemConverter.copy(dto.getPurchaseItems()));
    }

    public List<Purchase> copy(List<PurchaseDto> dtos) {
        List<Purchase> result = new ArrayList<>();
        if (dtos != null) {
            for (PurchaseDto dto : dtos) {
                Purchase instance = new Purchase();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public PurchaseStateConverter getPurchaseStateConverter() {
        return this.purchaseStateConverter;
    }

    public void setPurchaseStateConverter(PurchaseStateConverter purchaseStateConverter) {
        this.purchaseStateConverter = purchaseStateConverter;
    }

    public ProductConverter getProductConverter() {
        return this.productConverter;
    }

    public void setProductConverter(ProductConverter productConverter) {
        this.productConverter = productConverter;
    }

    public PurchaseItemConverter getPurchaseItemConverter() {
        return this.purchaseItemConverter;
    }

    public void setPurchaseItemConverter(PurchaseItemConverter purchaseItemConverter) {
        this.purchaseItemConverter = purchaseItemConverter;
    }

    public ClientConverter getClientConverter() {
        return this.clientConverter;
    }

    public void setClientConverter(ClientConverter clientConverter) {
        this.clientConverter = clientConverter;
    }

    public boolean isClient() {
        return this.client;
    }

    public void setClient(boolean client) {
        this.client = client;
    }

    public boolean isPurchaseState() {
        return this.purchaseState;
    }

    public void setPurchaseState(boolean purchaseState) {
        this.purchaseState = purchaseState;
    }

    public boolean isPurchaseItems() {
        return this.purchaseItems;
    }

    public void setPurchaseItems(boolean purchaseItems) {
        this.purchaseItems = purchaseItems;
    }
}
