package com.blossomproject.sample.generated;

import com.blossomproject.core.common.service.CrudService;

public interface SampleEntityService
    extends CrudService<SampleEntityDTO>
{

    SampleEntityDTO create(SampleEntityCreateForm createForm);

    SampleEntityDTO update(Long id, SampleEntityUpdateForm updateForm);
}
