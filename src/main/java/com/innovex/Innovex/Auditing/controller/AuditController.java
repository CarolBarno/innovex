package com.innovex.Innovex.Auditing.controller;

import com.innovex.Innovex.Auditing.service.AuditService;
import com.innovex.Innovex.utilities.utility.PageDetails;
import com.innovex.Innovex.utilities.utility.Pager;
import com.innovex.Innovex.utilities.utility.ResponseUtlity;
import io.swagger.annotations.Api;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for getting the audit events.
 */
@Api
@RestController
@RequestMapping("/management/audits")
public class AuditController {

    private final AuditService auditService;

    public AuditController(AuditService auditService) {
        this.auditService = auditService;
    }

//    /**
//     * GET /audits : get a page of AuditEvents.
//     *
//     * @param pageable the pagination information
//     * @return the ResponseEntity with status 200 (OK) and the list of
//     * AuditEvents in body
//     */
//    @GetMapping("/")
//    public ResponseEntity<Pager<List<AuditEvent>>> getAll(Pageable pageable) {
//        Page<AuditEvent> list = auditService.findAll(pageable);
//        Pager<List<AuditEvent>> pagers = new Pager();
//        pagers.setCode("0");
//        pagers.setMessage("Success");
//        pagers.setContent(list.getContent());
//        PageDetails details = new PageDetails();
//        details.setPage(list.getNumber() + 1);
//        details.setPerPage(list.getSize());
//        details.setTotalElements(list.getTotalElements());
//        details.setTotalPage(list.getTotalPages());
//        details.setReportName("Vendors");
//        pagers.setPageDetails(details);
//
//        return ResponseEntity.ok(pagers);
//    }
    /**
     * GET /audits : get a page of AuditEvents between the fromDate and toDate.
     *
     * @param fromDate the start of the time period of AuditEvents to get
     * @param toDate the end of the time period of AuditEvents to get
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of
     * AuditEvents in body
     */
    @GetMapping
    public ResponseEntity<Pager<List<AuditEvent>>> getAll(
            @RequestParam(value = "fromDate", required = false) LocalDate fromDate,
            @RequestParam(value = "toDate", required = false) LocalDate toDate,
            Pageable pageable) {

        Page<AuditEvent> list;
        if (fromDate != null && toDate != null) {
            list = auditService.findByDates(
                    fromDate.atStartOfDay(ZoneId.systemDefault()).toInstant(),
                    toDate.atStartOfDay(ZoneId.systemDefault()).plusDays(1).toInstant(),
                    pageable);
        } else {
            list = auditService.findAll(pageable);
        }

        Pager<List<AuditEvent>> pagers = new Pager();
        pagers.setCode("0");
        pagers.setMessage("Success");
        pagers.setContent(list.getContent());
        PageDetails details = new PageDetails();
        details.setPage(list.getNumber() + 1);
        details.setPerPage(list.getSize());
        details.setTotalElements(list.getTotalElements());
        details.setTotalPage(list.getTotalPages());
        details.setReportName("Vendors");
        pagers.setPageDetails(details);

        return ResponseEntity.ok(pagers);

    }

    /**
     * GET /audits/:id : get an AuditEvent by id.
     *
     * @param id the id of the entity to get
     * @return the ResponseEntity with status 200 (OK) and the AuditEvent in
     * body, or status 404 (Not Found)
     */
    @GetMapping("/{id:.+}")
    public ResponseEntity<AuditEvent> get(@PathVariable Long id) {
        return ResponseUtlity.wrapOrNotFound(auditService.find(id));
    }
}
