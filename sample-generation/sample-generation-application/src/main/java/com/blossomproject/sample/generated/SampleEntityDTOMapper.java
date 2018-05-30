package com.blossomproject.sample.generated;

import com.blossomproject.core.common.mapper.AbstractDTOMapper;

public class SampleEntityDTOMapper
    extends AbstractDTOMapper<SampleEntity, SampleEntityDTO>
{

    public SampleEntityDTO mapEntity(SampleEntity entity) {
        if (entity == null) {
            return null;
        }
        SampleEntityDTO dto = new SampleEntityDTO();
        mapEntityCommonFields(dto, entity);
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setBigDecimalField(entity.getBigDecimalField());
        dto.setBlobField(entity.getBlobField());
        dto.setBooleanField(entity.getBooleanField());
        dto.setDateField(entity.getDateField());
        dto.setEnumField(entity.getEnumField());
        dto.setIntegerField(entity.getIntegerField());
        dto.setLocalDateTimeField(entity.getLocalDateTimeField());
        dto.setLongField(entity.getLongField());
        dto.setStringField(entity.getStringField());
        return dto;
    }

    public SampleEntity mapDto(SampleEntityDTO dto) {
        if (dto == null) {
            return null;
        }
        SampleEntity entity = new SampleEntity();
        mapDtoCommonFields(entity, dto);
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setBigDecimalField(dto.getBigDecimalField());
        entity.setBlobField(dto.getBlobField());
        entity.setBooleanField(dto.getBooleanField());
        entity.setDateField(dto.getDateField());
        entity.setEnumField(dto.getEnumField());
        entity.setIntegerField(dto.getIntegerField());
        entity.setLocalDateTimeField(dto.getLocalDateTimeField());
        entity.setLongField(dto.getLongField());
        entity.setStringField(dto.getStringField());
        return entity;
    }
}
