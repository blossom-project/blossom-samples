<#import "/spring.ftl" as spring>
<#import "/blossom/master/master.ftl" as master>
<#import "/blossom/utils/tabulation.ftl" as tabulation>
<#import "/blossom/utils/privilege.ftl" as privilege>
<#import "/blossom/utils/buttons.ftl" as button>


<@master.default currentUser=currentUser>
<div class="row wrapper border-bottom white-bg page-heading">
  <div class="col-sm-8">
    <h2><i class="fa fa-question"></i> ${sample_entity.id}</h2>
    <ol class="breadcrumb">
      <li>
        <a href="/blossom"><@spring.message "menu.home"/></a>
      </li>
      <li>
        <@spring.message "menu.modules"/>
      </li>
      <li>
        <a href="/blossom/modules/sample_entitys"><@spring.message "sample_entitys.title"/></a>
      </li>
      <li class="active">
        <strong><@spring.message "sample_entitys.sample_entity.title"/></strong>
      </li>
    </ol>
  </div>
  <div class="col-sm-4">
    <div class="title-action">
      <@privilege.has currentUser=currentUser privilege="modules:sample_entitys:delete">
       <@button.delete id=sample_entity.id?c uri='/blossom/modules/sample_entitys/'+sample_entity.id?c+'/_delete'/>
      </@privilege.has>
    </div>
  </div>
</div>


<div class="wrapper wrapper-content">
  <@tabulation.tabs
    id="sample_entityContent"
    currentUser=currentUser
    tabs=[
    {
    "isActive": true,
    "linkLabel": "panel.information",
    "view": "/blossom/modules/sample_entitys/${sample_entity.id?c}/_informations",
    "edit": "/blossom/modules/sample_entitys/${sample_entity.id?c}/_informations/_edit",
    "privilege":"modules:sample_entitys:read"
    }
    ]
/>
</div>
</@master.default>
