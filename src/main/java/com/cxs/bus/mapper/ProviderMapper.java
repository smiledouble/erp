package com.cxs.bus.mapper;

import com.cxs.bus.domain.Provider;

import java.util.List;

public interface ProviderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Provider record);

    int insertSelective(Provider record);

    Provider selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Provider record);

    int updateByPrimaryKey(Provider record);

    List<Provider> queryAllProvider(Provider provider);
}