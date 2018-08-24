package com.blossomproject.sample.generated;

import java.util.Map;
import java.util.Optional;
import com.blossomproject.core.common.dto.AbstractDTO;
import com.blossomproject.core.common.search.SearchEngineImpl;
import com.blossomproject.ui.stereotype.BlossomApiController;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@BlossomApiController
@RequestMapping("/modules/sample_entitys")
public class SampleEntityApiController {
    private final SampleEntityService service;
    private final SearchEngineImpl<SampleEntityDTO> searchEngine;

    public SampleEntityApiController(SampleEntityService service, SearchEngineImpl<SampleEntityDTO> searchEngine) {
        this.service = service;
        this.searchEngine = searchEngine;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('modules:sample_entitys:read')")
    public Page<SampleEntityDTO> list(
        @RequestParam(value = "q", required = false)
        String q,
        @PageableDefault(size = 25)
        Pageable pageable) {
        if (Strings.isNullOrEmpty(q)) {
            return this.service.getAll(pageable);
        } else {
            return this.searchEngine.search(q, pageable).getPage();
        }
    }

    @PostMapping
    @PreAuthorize("hasAuthority('modules:sample_entitys:create')")
    public ResponseEntity<SampleEntityDTO> create(
        @NotNull
        @Valid
        @RequestBody
        SampleEntityCreateForm createForm)
        throws Exception
    {
        Preconditions.checkArgument((createForm!= null));
        return new ResponseEntity<>(service.create(createForm), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('modules:sample_entitys:read')")
    public ResponseEntity<SampleEntityDTO> get(
        @PathVariable
        Long id) {
        Preconditions.checkArgument((id!= null));
        SampleEntityDTO entity = this.service.getOne(id);
        if (entity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(entity, HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('modules:sample_entitys:write')")
    public ResponseEntity<SampleEntityDTO> update(
        @PathVariable
        Long id,
        @Valid
        @RequestBody
        SampleEntityUpdateForm updateForm) {
        Preconditions.checkArgument((id!= null));
        SampleEntityDTO entity = this.service.getOne(id);
        if (entity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(service.update(id, updateForm), HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('modules:sample_entitys:delete')")
    public ResponseEntity<Map<Class<? extends AbstractDTO> , Long>> delete(
        @PathVariable
        Long id,
        @RequestParam(value = "force", required = false, defaultValue = "false")
        Boolean force) {
        SampleEntityDTO entity = this.service.getOne(id);
        if (entity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<Map<Class<? extends AbstractDTO> , Long>> result = this.service.delete(entity, force);
        if ((!result.isPresent())||result.get().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result.get(), HttpStatus.CONFLICT);
        }
    }
}
