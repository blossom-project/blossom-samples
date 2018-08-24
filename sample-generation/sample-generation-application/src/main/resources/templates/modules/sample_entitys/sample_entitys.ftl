<#import "/spring.ftl" as spring>
<#import "/blossom/master/master.ftl" as master>
<#import "/blossom/utils/table.ftl" as table>
<#import "/blossom/utils/privilege.ftl" as privilege>
<#import "/blossom/utils/table.ftl" as table>

<@master.default currentUser=currentUser>
<div class="row wrapper border-bottom white-bg page-heading">
  <div class="col-sm-8">
    <h2><i class="fa fa-question"></i> <@spring.message "sample_entitys.title"/></h2>
    <ol class="breadcrumb">
      <li>
        <a href="/blossom"><@spring.message "menu.home"/></a>
      </li>
      <li>
      <@spring.message "menu.modules"/>
      </li>
      <li class="active">
        <strong><@spring.message "sample_entitys.title"/></strong>
      </li>
    </ol>
  </div>
  <div class="col-sm-4">
    <div class="title-action">
      <@privilege.has currentUser=currentUser privilege="modules:sample_entitys:create">
        <a href="/blossom/modules/sample_entitys/_create" class="btn btn-primary"><i class="fa fa-plus"></i></a>
      </@privilege.has>
    </div>
  </div>
</div>

<div class="wrapper wrapper-content">

  <@table.pagetable
  page=items
  label='sample_entitys.label'
  iconPath='fa fa-question'
  columns= {
    "id": { "label":"id", "sortable":true, "link":"/blossom/modules/sample_entitys/{id}"},
  "modificationDate": {"label":"list.modification.date.head", "sortable":true, "type":"datetime"}
  }
  filters=[]
  searchable=true
  q=q
/>
</div>
</@master.default>
