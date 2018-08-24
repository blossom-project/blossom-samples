<#import "/spring.ftl" as spring>
<#import "/blossom/master/master.ftl" as master>
<#import "/blossom/utils/civility.ftl" as gender>
<#import "/blossom/utils/buttons.ftl" as buttons>


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
      <li>
        <a href="/blossom/modules/sample_entitys"><@spring.message "sample_entitys.title"/></a>
      </li>
      <li class="active">
        <strong><@spring.message "sample_entitys.sample_entity.label"/></strong>
      </li>
    </ol>
  </div>
</div>
<div class="wrapper wrapper-content">
  <form id="sampleEntityCreateForm" class="form form-horizontal" novalidate method="POST">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <div class="ibox">
      <div class="ibox-content">

          
        <@spring.bind "sampleEntityCreateForm.name"/>
        <div class="form-group <#if spring.status.error>has-error</#if>">
          <label class="col-sm-2 control-label">name</label>
          <div class="col-sm-10">
            <input type="text" name="name" class="form-control" value="<#if sampleEntityCreateForm.name??>${sampleEntityCreateForm.name}</#if>"
                   placeholder="name">
            <#list spring.status.errorMessages as error>
              <span class="help-block text-danger m-b-none">${error}</span>
            </#list>
          </div>
        </div>
          
          <@spring.bind "sampleEntityCreateForm.description"/>
          <div class="form-group">
              <label class="col-sm-2 control-label">description</label>
              <div class="col-sm-10">
                  <textarea class="form-control" name="description" rows="5"><#if sampleEntityCreateForm.description??>${sampleEntityCreateForm.description}</#if></textarea>
                  <#list spring.status.errorMessages as error>
                      <span class="help-block text-danger m-b-none">${error}</span>
                  </#list>
              </div>
          </div>
          

      </div>
      <div class="ibox-footer">
        <div class="text-right">
          <button type="submit" class="btn btn-primary"><i class="fa fa-save"></i> <@spring.message "save"/></button>
          <a href="/blossom/modules/sample_entitys" class="btn btn-default btn-sm"><i class="fa fa-remove"></i> <@spring.message "cancel"/></a>
        </div>
      </div>
    </div>
  </form>
</div>
</@master.default>
