<#import "/spring.ftl" as spring>
<#import "/blossom/utils/civility.ftl" as civility>
<#import "/blossom/utils/buttons.ftl" as buttons>
<#import "/blossom/utils/tabulation.ftl" as tabulation>

<div class="ibox-content">
  <div class="sk-spinner sk-spinner-wave">
    <div class="sk-rect1"></div>
    <div class="sk-rect2"></div>
    <div class="sk-rect3"></div>
    <div class="sk-rect4"></div>
    <div class="sk-rect5"></div>
  </div>
  <form class="form form-horizontal">
      
    <div class="form-group">
      <label class="col-sm-2 control-label"><@spring.message "sample_entitys.sample_entity.properties.name"/></label>
      <div class="col-sm-10">
        <p class="form-control-static"><#if sample_entity.name??>${sample_entity.name}</#if></p>
      </div>
    </div>
      
  <@spring.bind "sample_entity.description"/>
      <div class="form-group">
          <label class="col-sm-2 control-label">description</label>
          <div class="col-sm-10">
              <textarea disabled class="form-control" name="description" rows="5"><#if sample_entity.description??>${sample_entity.description}</#if></textarea>
          <#list spring.status.errorMessages as error>
              <span class="help-block text-danger m-b-none">${error}</span>
          </#list>
          </div>
      </div>
      
    <div class="form-group">
      <label class="col-sm-2 control-label"><@spring.message "sample_entitys.sample_entity.properties.bigDecimalField"/></label>
      <div class="col-sm-10">
        <p class="form-control-static"><#if sample_entity.bigDecimalField??>${sample_entity.bigDecimalField}</#if></p>
      </div>
    </div>
      
    <div class="form-group">
      <label class="col-sm-2 control-label"><@spring.message "sample_entitys.sample_entity.properties.blobField"/></label>
      <div class="col-sm-10">
        <#--<p class="form-control-static"><#if sample_entity.blobField??>${sample_entity.blobField}</#if></p>-->
      </div>
    </div>
      
      <div class="form-group">
          <label class="col-sm-2 control-label">booleanField</label>
          <div class="col-sm-10">
          <#if sample_entity.booleanField??>
                      <@buttons.switch checked=sample_entity.booleanField disabled=true name="booleanField"/>
                  <#else>
            <@buttons.switch checked=false disabled=true name="booleanField"/>
          </#if>

          </div>
      </div>
      
    <div class="form-group">
      <label class="col-sm-2 control-label"><@spring.message "sample_entitys.sample_entity.properties.dateField"/></label>
      <div class="col-sm-10">
        <p class="form-control-static"><#if sample_entity.dateField??>${sample_entity.dateField?string("yyyy-MM-dd'T'HH:mm")}</#if></p>
      </div>
    </div>
      
      <div class="form-group">
          <label class="col-sm-2 control-label">enumField</label>
          <div class="col-sm-10">
              <select class="form-control" name="enumField" disabled>
                  
                    <option value="first" <#if "first"==sample_entity.enumField!"">selected</#if>>first</option>
                  
                    <option value="second" <#if "second"==sample_entity.enumField!"">selected</#if>>second</option>
                  
              </select>
          </div>
      </div>
      
    <div class="form-group">
      <label class="col-sm-2 control-label"><@spring.message "sample_entitys.sample_entity.properties.integerField"/></label>
      <div class="col-sm-10">
        <p class="form-control-static"><#if sample_entity.integerField??>${sample_entity.integerField}</#if></p>
      </div>
    </div>
      
    <div class="form-group">
      <label class="col-sm-2 control-label"><@spring.message "sample_entitys.sample_entity.properties.localDateTimeField"/></label>
      <div class="col-sm-10">
        <p class="form-control-static"><#if sample_entity.localDateTimeField??>${sample_entity.localDateTimeField?string("yyyy-MM-dd'T'HH:mm")}</#if></p>
      </div>
    </div>
      
    <div class="form-group">
      <label class="col-sm-2 control-label"><@spring.message "sample_entitys.sample_entity.properties.longField"/></label>
      <div class="col-sm-10">
        <p class="form-control-static"><#if sample_entity.longField??>${sample_entity.longField}</#if></p>
      </div>
    </div>
      
    <div class="form-group">
      <label class="col-sm-2 control-label"><@spring.message "sample_entitys.sample_entity.properties.stringField"/></label>
      <div class="col-sm-10">
        <p class="form-control-static"><#if sample_entity.stringField??>${sample_entity.stringField}</#if></p>
      </div>
    </div>
      
  </form>
</div>

<div class="ibox-footer">
  <div class="text-right">
    <button class="btn btn-primary btn-view" onclick="edit_entityinformations(this);">
    <@spring.message "edit"/>
    </button>
  </div>
</div>

<script>
  var edit_entityinformations = function (button) {
    var targetSelector = '#'+$(button).closest(".tab-pane").attr('id');
    $(targetSelector + ' > .ibox-content').addClass("sk-loading");
    var edit = $(targetSelector).data("edit");
    $.get(edit).done(function (responseText, textStatus, jqXHR) {
      if (jqXHR.status === 200) {
        $(targetSelector).html(responseText);
      }
      $(targetSelector).removeClass("sk-loading");
    });
  };
</script>
