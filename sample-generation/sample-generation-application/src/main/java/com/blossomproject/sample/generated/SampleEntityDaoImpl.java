package com.blossomproject.sample.generated;

import com.blossomproject.core.common.dao.GenericCrudDaoImpl;

public class SampleEntityDaoImpl
    extends GenericCrudDaoImpl<SampleEntity>
    implements SampleEntityDao
{

    public SampleEntityDaoImpl(SampleEntityRepository repository) {
        super(repository);
    }

    @Override
    public SampleEntity updateEntity(SampleEntity originalEntity, SampleEntity modifiedEntity) {
        originalEntity.setName(modifiedEntity.getName());
        originalEntity.setDescription(modifiedEntity.getDescription());
        return originalEntity;
    }
}
