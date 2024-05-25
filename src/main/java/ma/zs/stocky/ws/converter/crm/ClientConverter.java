package ma.zs.stocky.ws.converter.crm;

import ma.zs.stocky.bean.core.crm.Client;
import ma.zs.stocky.ws.dto.crm.ClientDto;
import ma.zs.stocky.zynerator.converter.AbstractConverterHelper;
import ma.zs.stocky.zynerator.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClientConverter {


    public ClientConverter() {
    }


    public Client toItem(ClientDto dto) {
        if (dto == null) {
            return null;
        } else {
            Client item = new Client();
            if (StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if (StringUtil.isNotEmpty(dto.getFullName()))
                item.setFullName(dto.getFullName());
            if (StringUtil.isNotEmpty(dto.getEmail()))
                item.setEmail(dto.getEmail());


            return item;
        }
    }


    public ClientDto toDto(Client item) {
        if (item == null) {
            return null;
        } else {
            ClientDto dto = new ClientDto();
            if (StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if (StringUtil.isNotEmpty(item.getFullName()))
                dto.setFullName(item.getFullName());
            if (StringUtil.isNotEmpty(item.getEmail()))
                dto.setEmail(item.getEmail());


            return dto;
        }
    }


    public List<Client> toItem(List<ClientDto> dtos) {
        List<Client> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ClientDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ClientDto> toDto(List<Client> items) {
        List<ClientDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Client item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ClientDto dto, Client t) {
        BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Client> copy(List<ClientDto> dtos) {
        List<Client> result = new ArrayList<>();
        if (dtos != null) {
            for (ClientDto dto : dtos) {
                Client instance = new Client();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
