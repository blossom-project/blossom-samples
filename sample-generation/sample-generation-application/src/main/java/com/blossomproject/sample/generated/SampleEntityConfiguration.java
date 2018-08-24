package com.blossomproject.sample.generated;

import java.util.function.Function;
import com.blossomproject.core.common.PluginConstants;
import com.blossomproject.core.common.dto.AbstractDTO;
import com.blossomproject.core.common.search.IndexationEngineConfiguration;
import com.blossomproject.core.common.search.IndexationEngineConfigurationImpl;
import com.blossomproject.core.common.search.IndexationEngineImpl;
import com.blossomproject.core.common.search.SearchEngineConfiguration;
import com.blossomproject.core.common.search.SearchEngineConfigurationImpl;
import com.blossomproject.core.common.search.SearchEngineImpl;
import com.blossomproject.core.common.search.SummaryDTO.SummaryDTOBuilder;
import com.blossomproject.core.common.service.AssociationServicePlugin;
import com.blossomproject.core.common.utils.privilege.Privilege;
import com.blossomproject.core.common.utils.privilege.SimplePrivilege;
import com.blossomproject.ui.menu.MenuItem;
import com.blossomproject.ui.menu.MenuItemBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.client.Client;
import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

@Configuration
@EnableJpaRepositories(basePackageClasses = SampleEntityRepository.class)
@EntityScan(basePackageClasses = com.blossomproject.sample.generated.SampleEntityConfiguration.class)
public class SampleEntityConfiguration {
    @Autowired
    @Qualifier(PluginConstants.PLUGIN_ASSOCIATION_SERVICE)
    private PluginRegistry<AssociationServicePlugin, Class<? extends AbstractDTO>> associationRegistry;

    @Bean
    public com.blossomproject.sample.generated.SampleEntityDTOMapper SampleEntityDTOMapper() {
        return new com.blossomproject.sample.generated.SampleEntityDTOMapper();
    }

    @Bean
    public com.blossomproject.sample.generated.SampleEntityDao SampleEntityDao(SampleEntityRepository repository) {
        return new SampleEntityDaoImpl(repository);
    }

    @Bean
    public com.blossomproject.sample.generated.SampleEntityService SampleEntityService(com.blossomproject.sample.generated.SampleEntityDao dao, com.blossomproject.sample.generated.SampleEntityDTOMapper mapper, ApplicationEventPublisher publisher) {
        return new SampleEntityServiceImpl(dao, mapper, publisher, associationRegistry);
    }

    @Bean
    public IndexationEngineConfiguration<SampleEntityDTO> sampleEntityIndexationEngineConfiguration(
        @Value("classpath:/elasticsearch/sample_entity.json")
        Resource resource) {
        Function<SampleEntityDTO, String> typeFunction = item -> "sample_entity";
        return new IndexationEngineConfigurationImpl<SampleEntityDTO>(SampleEntityDTO.class, resource, "sample_entitys", item -> "sample_entity", item -> SummaryDTOBuilder.create().id(item.getId()).type(typeFunction.apply(item)).name(item.getId().toString()).description("").uri(("/modules/sample_entitys/"+ item.getId())).build());
    }

    @Bean
    public IndexationEngineImpl<SampleEntityDTO> sampleEntityIndexationEngine(Client client, com.blossomproject.sample.generated.SampleEntityService service, BulkProcessor bulkProcessor, ObjectMapper objectMapper, IndexationEngineConfiguration<SampleEntityDTO> sampleEntityIndexationEngineConfiguration) {
        return new IndexationEngineImpl<SampleEntityDTO>(client, service, bulkProcessor, objectMapper, sampleEntityIndexationEngineConfiguration);
    }

    @Bean
    public SearchEngineConfiguration<SampleEntityDTO> sampleEntitySearchEngineConfiguration() {
        return new SearchEngineConfigurationImpl<SampleEntityDTO>("SampleEntity", "sample_entitys", SampleEntityDTO.class, Lists.newArrayList("dto.name", "dto.description"));
    }

    @Bean
    public SearchEngineImpl<SampleEntityDTO> sampleEntitySearchEngine(Client client, ObjectMapper objectMapper, SearchEngineConfiguration<SampleEntityDTO> sampleEntitySearchEngineConfiguration) {
        return new SearchEngineImpl<SampleEntityDTO>(client, objectMapper, sampleEntitySearchEngineConfiguration);
    }

    @Bean
    @Qualifier("sampleEntityIndexationFullJob")
    public JobDetailFactoryBean sampleEntityIndexationFullJob() {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(SampleEntityIndexationJob.class);
        factoryBean.setName("SampleEntity Indexation Job");
        factoryBean.setGroup("Indexation");
        factoryBean.setDescription("SampleEntity full indexation Job");
        factoryBean.setDurability(true);
        return factoryBean;
    }

    @Bean
    @Qualifier("sampleEntityScheduledIndexationTrigger")
    public SimpleTriggerFactoryBean sampleEntityScheduledIndexationTrigger(
        @Qualifier("sampleEntityIndexationFullJob")
        JobDetail articleIndexationFullJob) {
        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
        factoryBean.setJobDetail(articleIndexationFullJob);
        factoryBean.setName("SampleEntity re-indexation");
        factoryBean.setDescription("Periodic re-indexation of all articles of the application");
        factoryBean.setStartDelay(30000L);
        factoryBean.setRepeatInterval(3600000);
        factoryBean.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
        factoryBean.setMisfireInstruction(SimpleTrigger.MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_REMAINING_COUNT);
        return factoryBean;
    }

    @Bean
    @ConditionalOnMissingBean(name = "moduleMenuItem")
    @Order(3)
    public MenuItem moduleMenuItem(MenuItemBuilder builder) {
        return builder.key("modules").label("menu.modules").icon("fa fa-puzzle-piece").link("/blossom/modules").leaf(false).order(2147483646).build();
    }

    @Bean
    public Privilege SampleEntityReadPrivilegePlugin() {
        return new SimplePrivilege("modules", "sample_entitys", "read");
    }

    @Bean
    public Privilege SampleEntityWritePrivilegePlugin() {
        return new SimplePrivilege("modules", "sample_entitys", "write");
    }

    @Bean
    public Privilege SampleEntityCreatePrivilegePlugin() {
        return new SimplePrivilege("modules", "sample_entitys", "create");
    }

    @Bean
    public Privilege SampleEntityDeletePrivilegePlugin() {
        return new SimplePrivilege("modules", "sample_entitys", "delete");
    }

    @Bean
    public MenuItem SampleEntityMenuItem(MenuItemBuilder builder,
        @Qualifier("moduleMenuItem")
        MenuItem parentMenu) {
        return builder.key("sample_entitys").label("menu.sample_entity").icon("fa fa-question").link("/blossom/modules/sample_entitys").parent(parentMenu).privilege(SampleEntityReadPrivilegePlugin()).build();
    }

    @Bean
    @ConditionalOnMissingBean(com.blossomproject.sample.generated.SampleEntityController.class)
    public com.blossomproject.sample.generated.SampleEntityController SampleEntityController(com.blossomproject.sample.generated.SampleEntityService service, SearchEngineImpl<SampleEntityDTO> searchEngine) {
        return new com.blossomproject.sample.generated.SampleEntityController(service, searchEngine);
    }
}
