<#import "/spring.ftl" as spring>
<#import "/blossom/utils/civility.ftl" as gender>
<#import "/blossom/utils/buttons.ftl" as buttons>
<#import "/blossom/utils/notification.ftl" as notification>
<#import "/blossom/utils/modal.ftl" as modal>

<form class="form form-horizontal" onsubmit="submit_entityinformations(this);return false;">

  <div class="ibox-content" >
    <div class="sk-spinner sk-spinner-wave">
      <div class="sk-rect1"></div>
      <div class="sk-rect2"></div>
      <div class="sk-rect3"></div>
      <div class="sk-rect4"></div>
      <div class="sk-rect5"></div>
    </div>

    <@spring.bind "sampleEntityUpdateForm"/>
    <#if spring.status.error>
      <p class="alert alert-danger">
        <#list spring.status.errorMessages as error>
        ${error}<#if !error?is_last><br/></#if>
        </#list>
      </p>
    </#if>

      
  <@spring.bind "sampleEntityUpdateForm.name"/>
      <div class="form-group <#if spring.status.error>has-error</#if>">
          <label class="col-sm-2 control-label">name</label>
          <div class="col-sm-10">
              <input type="text" name="name" class="form-control" value="<#if sampleEntityUpdateForm.name??>${sampleEntityUpdateForm.name}</#if>"
                     placeholder="name">
          <#list spring.status.errorMessages as error>
              <span class="help-block text-danger m-b-none">${error}</span>
          </#list>
          </div>
      </div>
      
  <@spring.bind "sampleEntityUpdateForm.description"/>
      <div class="form-group">
          <label class="col-sm-2 control-label">description</label>
          <div class="col-sm-10">
              <textarea class="form-control" name="description" rows="5"><#if sampleEntityUpdateForm.description??>${sampleEntityUpdateForm.description}</#if></textarea>
          <#list spring.status.errorMessages as error>
              <span class="help-block text-danger m-b-none">${error}</span>
          </#list>
          </div>
      </div>
      


  </div>

  <div class="ibox-footer">
    <div class="text-right">
      <button class="btn btn-default btn-view" type="button" onclick="cancel_entityinformations(this);">
      <@spring.message "cancel"/>
      </button>

      <button class="btn btn-primary btn-view" type="submit">
      <@spring.message "save"/>
      </button>
    </div>
  </div>
</form>

<script>
  var submit_entityinformations = function (button) {
    var targetSelector = '#'+$(button).closest(".tab-pane").attr('id');
    $(targetSelector + ' > .ibox-content').addClass("sk-loading");
    var edit = $(targetSelector).data("edit");
    var form = $(targetSelector + ' > form');

    $.post(edit, form.serialize())
    .done(function(data, textStatus, jqXHR){
      $(targetSelector).html(data);
      <@notification.success message="updated"/>
    })
    .fail(function( jqXHR, textStatus, errorThrown){
      $(targetSelector).html(jqXHR.responseText);
      <@notification.error message="errored"/>
    }).always(function(){
      $(targetSelector+ ' > .ibox-content').removeClass("sk-loading");
    });
  };

  var cancel_entityinformations = function (button) {
    var targetSelector = '#'+$(button).closest(".tab-pane").attr('id');
    $(targetSelector + ' > .ibox-content').addClass("sk-loading");
    var view = $(targetSelector).data("view");
    $.get(view).done(function (responseText, textStatus, jqXHR) {
      $(targetSelector).html(responseText);
      $(targetSelector).removeClass("sk-loading");
    });
  };
</script>
