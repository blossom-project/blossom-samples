package com.blossomproject.sample.generated;

import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import com.blossomproject.core.common.dto.AbstractDTO;
import com.blossomproject.core.common.search.SearchEngineImpl;
import com.blossomproject.ui.menu.OpenedMenu;
import com.blossomproject.ui.stereotype.BlossomController;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@BlossomController
@RequestMapping("/modules/sample_entitys")
@OpenedMenu("sample_entitys")
public class SampleEntityController {
    private final static Logger logger = LoggerFactory.getLogger(SampleEntityController.class);
    private final SampleEntityService service;
    private final SearchEngineImpl<SampleEntityDTO> searchEngine;

    public SampleEntityController(SampleEntityService service, SearchEngineImpl<SampleEntityDTO> searchEngine) {
        this.service = service;
        this.searchEngine = searchEngine;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('modules:sample_entitys:read')")
    public ModelAndView getsampleEntitysPage(
        @RequestParam(value = "q", required = false)
        String q,
        @PageableDefault(size = 25)
        Pageable pageable, Model model) {
        Page<SampleEntityDTO> items;
        if (Strings.isNullOrEmpty(q)) {
            items = this.service.getAll(pageable);
        } else {
            items = this.searchEngine.search(q, pageable).getPage();
        }
        model.addAttribute("items", items);
        model.addAttribute("q", q);
        return new ModelAndView("modules/sample_entitys/sample_entitys", model.asMap());
    }

    @GetMapping("/_create")
    @PreAuthorize("hasAuthority('modules:sample_entitys:create')")
    public ModelAndView getsampleEntityCreatePage(Model model, Locale locale) {
        SampleEntityCreateForm createForm = new SampleEntityCreateForm();
        return this.createView(createForm, model);
    }

    @PostMapping("/_create")
    @PreAuthorize("hasAuthority('modules:sample_entitys:create')")
    public ModelAndView handlesampleEntityCreateForm(
        @Valid
        @ModelAttribute("sampleEntityCreateForm")
        SampleEntityCreateForm createForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return this.createView(createForm, model);
        }
        try {
            SampleEntityDTO entity = this.service.create(createForm);
            return new ModelAndView(("redirect:../sample_entitys/"+ entity.getId()));
        } catch (final Exception e) {
            this.logger.error("Error on creating entity, already exists ", e);
            return this.createView(createForm, model);
        }
    }

    private ModelAndView createView(SampleEntityCreateForm createForm, Model model) {
        model.addAttribute("sampleEntityCreateForm", createForm);
        return new ModelAndView("modules/sample_entitys/create", model.asMap());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('modules:sample_entitys:read')")
    public ModelAndView getsampleEntity(
        @PathVariable
        Long id, Model model, HttpServletRequest request) {
        SampleEntityDTO entity = this.service.getOne(id);
        if (entity == null) {
            throw new NoSuchElementException(String.format("sampleEntity=%s not found", id));
        }
        model.addAttribute("sampleEntity", entity);
        return new ModelAndView("modules/sample_entitys/sample_entity", "sample_entity", entity);
    }

    @GetMapping("/{id}/_informations")
    @PreAuthorize("hasAuthority('modules:sample_entitys:read')")
    public ModelAndView getsampleEntityInformations(
        @PathVariable
        Long id, HttpServletRequest request) {
        SampleEntityDTO entity = this.service.getOne(id);
        if (entity == null) {
            throw new NoSuchElementException(String.format("sampleEntity=%s not found", id));
        }
        return this.viewInformationView(entity);
    }

    @GetMapping("/{id}/_informations/_edit")
    @PreAuthorize("hasAuthority('modules:sample_entitys:write')")
    public ModelAndView getsampleEntityInformationsForm(
        @PathVariable
        Long id) {
        SampleEntityDTO entity = this.service.getOne(id);
        if (entity == null) {
            throw new NoSuchElementException(String.format("sampleEntity=%s not found", id));
        }
        return this.updateInformationView(new SampleEntityUpdateForm(entity));
    }

    @PostMapping("/{id}/_informations/_edit")
    @PreAuthorize("hasAuthority('modules:sample_entitys:write')")
    public ModelAndView handlesampleEntityInformationsForm(
        @PathVariable
        Long id, Model model,
        @Valid
        @ModelAttribute("sampleEntityUpdateForm")
        SampleEntityUpdateForm updateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return this.updateInformationView(updateForm);
        }
        SampleEntityDTO entity = this.service.getOne(id);
        if (entity == null) {
            throw new NoSuchElementException(String.format("sampleEntity=%s not found", id));
        }
        SampleEntityDTO entityUpdated = this.service.update(id, updateForm);
        return this.viewInformationView(entityUpdated);
    }

    @PostMapping("/{id}/_delete")
    @PreAuthorize("hasAuthority('modules:sample_entitys:delete')")
    public ResponseEntity<Map<Class<? extends AbstractDTO> , Long>> deletesampleEntity(
        @PathVariable
        Long id,
        @RequestParam(value = "force", required = false, defaultValue = "false")
        Boolean force) {
        Optional<Map<Class<? extends AbstractDTO> , Long>> result = this.service.delete(this.service.getOne(id), force);
        if ((!result.isPresent())||result.get().isEmpty()) {
            return new ResponseEntity<>(Maps.newHashMap(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result.get(), HttpStatus.CONFLICT);
        }
    }

    private ModelAndView viewInformationView(SampleEntityDTO entity) {
        return new ModelAndView("modules/sample_entitys/sample_entityinformations", "sample_entity", entity);
    }

    private ModelAndView updateInformationView(SampleEntityUpdateForm entityUpdateForm) {
        return new ModelAndView("modules/sample_entitys/sample_entityinformations-edit", "sampleEntityUpdateForm", entityUpdateForm);
    }
}
