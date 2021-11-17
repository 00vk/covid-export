package ru.spb.reshenie.covid.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.spb.reshenie.covid.entity.ExportStatusView;
import ru.spb.reshenie.covid.entity.ExportStatusViewDTO;
import ru.spb.reshenie.covid.service.CheckStatusService;
import ru.spb.reshenie.covid.service.ExportResultService;
import ru.spb.reshenie.covid.service.ExportStatusViewService;

import java.time.LocalDate;
import java.util.*;

/**
 * Created by vkondratiev on 04.11.2021
 */

@Controller
@RequestMapping("/")
public class WebViewController {

    private final ExportStatusViewService viewService;
    private final ExportResultService exportService;
    private final CheckStatusService statusService;

    public WebViewController(ExportStatusViewService viewService,
                             ExportResultService exportService,
                             CheckStatusService statusService) {
        this.viewService = viewService;
        this.exportService = exportService;
        this.statusService = statusService;
    }

    @RequestMapping
    public String redirectIndex() {
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String list(Model model) {
        Map<LocalDate, List<ExportStatusViewDTO>> statusesByDates = new TreeMap<>(Comparator.reverseOrder());
        List<ExportStatusView> lastTwoWeeks = viewService.findLastTwoWeeks();

        for (ExportStatusView statusView : lastTwoWeeks) {
            LocalDate readyDate = statusView.getReadyDate();
            statusesByDates.putIfAbsent(readyDate, new ArrayList<>());

            ExportStatusViewDTO status = new ExportStatusViewDTO(statusView);
            List<ExportStatusViewDTO> singleDateStatuses = statusesByDates.get(readyDate);
            singleDateStatuses.add(status);
        }
        for (List<ExportStatusViewDTO> singleDateStatuses : statusesByDates.values()) {
            singleDateStatuses.sort(Comparator.comparing(ExportStatusViewDTO::getPatientName));
        }
        model.addAttribute("datesAndStatuses", statusesByDates);
        return "index";
    }

    @GetMapping("/re-export")
    public String reExport(@RequestParam String order, Model model) {
        exportService.reexportResult(order);
        return list(model);
    }

    @GetMapping("/check-status")
    public String checkStatus(@RequestParam String order, Model model) {
        statusService.checkStatusesByOrderNumber(order);
        return list(model);
    }
}