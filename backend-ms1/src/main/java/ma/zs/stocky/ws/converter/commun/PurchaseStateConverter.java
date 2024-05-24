package  ma.zs.stocky.ws.converter.commun;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.stocky.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zs.stocky.zynerator.util.StringUtil;
import ma.zs.stocky.zynerator.converter.AbstractConverter;
import ma.zs.stocky.zynerator.util.DateUtil;
import ma.zs.stocky.bean.core.commun.PurchaseState;
import ma.zs.stocky.ws.dto.commun.PurchaseStateDto;

@Component
public class PurchaseStateConverter {


    public  PurchaseStateConverter() {
    }


    public PurchaseState toItem(PurchaseStateDto dto) {
        if (dto == null) {
            return null;
        } else {
        PurchaseState item = new PurchaseState();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());



        return item;
        }
    }


    public PurchaseStateDto toDto(PurchaseState item) {
        if (item == null) {
            return null;
        } else {
            PurchaseStateDto dto = new PurchaseStateDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());


        return dto;
        }
    }


	
    public List<PurchaseState> toItem(List<PurchaseStateDto> dtos) {
        List<PurchaseState> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (PurchaseStateDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<PurchaseStateDto> toDto(List<PurchaseState> items) {
        List<PurchaseStateDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (PurchaseState item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(PurchaseStateDto dto, PurchaseState t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<PurchaseState> copy(List<PurchaseStateDto> dtos) {
        List<PurchaseState> result = new ArrayList<>();
        if (dtos != null) {
            for (PurchaseStateDto dto : dtos) {
                PurchaseState instance = new PurchaseState();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
