package ma.zs.stocky.ws.converter.catalog;

import ma.zs.stocky.bean.core.catalog.Product;
import ma.zs.stocky.ws.dto.catalog.ProductDto;
import ma.zs.stocky.zynerator.converter.AbstractConverterHelper;
import ma.zs.stocky.zynerator.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductConverter {


    public ProductConverter() {
    }


    public Product toItem(ProductDto dto) {
        if (dto == null) {
            return null;
        } else {
            Product item = new Product();
            if (StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if (StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if (StringUtil.isNotEmpty(dto.getReference()))
                item.setReference(dto.getReference());


            return item;
        }
    }


    public ProductDto toDto(Product item) {
        if (item == null) {
            return null;
        } else {
            ProductDto dto = new ProductDto();
            if (StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if (StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if (StringUtil.isNotEmpty(item.getReference()))
                dto.setReference(item.getReference());


            return dto;
        }
    }


    public List<Product> toItem(List<ProductDto> dtos) {
        List<Product> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ProductDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ProductDto> toDto(List<Product> items) {
        List<ProductDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Product item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ProductDto dto, Product t) {
        BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Product> copy(List<ProductDto> dtos) {
        List<Product> result = new ArrayList<>();
        if (dtos != null) {
            for (ProductDto dto : dtos) {
                Product instance = new Product();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
