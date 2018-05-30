package com.blossomproject.sample.generated;

import com.blossomproject.core.common.dto.AbstractDTO;
import com.blossomproject.core.common.event.CreatedEvent;
import com.blossomproject.core.common.event.UpdatedEvent;
import com.blossomproject.core.common.service.AssociationServicePlugin;
import com.blossomproject.core.common.service.GenericCrudServiceImpl;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.plugin.core.PluginRegistry;

public class SampleEntityServiceImpl
    extends GenericCrudServiceImpl<SampleEntityDTO, SampleEntity>
    implements SampleEntityService
{

    public SampleEntityServiceImpl(SampleEntityDao dao, SampleEntityDTOMapper mapper, ApplicationEventPublisher publisher, PluginRegistry<AssociationServicePlugin, Class<? extends AbstractDTO>> associationRegistry) {
        super(dao, mapper, publisher, associationRegistry);
    }

    @Override
    public SampleEntityDTO create(SampleEntityCreateForm createForm) {
        SampleEntity toCreate = new SampleEntity();
        toCreate.setName(createForm.getName());
        toCreate.setDescription(createForm.getDescription());
        SampleEntity savedEntity = this.crudDao.create(toCreate);
        SampleEntityDTO savedDto = this.mapper.mapEntity(savedEntity);
        this.publisher.publishEvent(new CreatedEvent<SampleEntityDTO>(this, savedDto));
        return savedDto;
    }

    @Override
    public SampleEntityDTO update(Long id, SampleEntityUpdateForm updateForm) {
        SampleEntityDTO toUpdate = this.getOne(id);
        toUpdate.setName(updateForm.getName());
        toUpdate.setDescription(updateForm.getDescription());
        SampleEntityDTO savedDto = this.update(id, toUpdate);
        this.publisher.publishEvent(new UpdatedEvent<SampleEntityDTO>(this, savedDto));
        return savedDto;
    }
}
