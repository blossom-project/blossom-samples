package com.blossomproject.sample.generated;

import com.blossomproject.core.common.search.IndexationEngine;
import com.blossomproject.core.scheduler.IndexationJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class SampleEntityIndexationJob
    extends IndexationJob
{
    @Autowired
    @Qualifier("sampleEntityIndexationEngine")
    private IndexationEngine fileIndexationEngine;

    @Override
    protected IndexationEngine getIndexationEngine() {
        return fileIndexationEngine;
    }
}
