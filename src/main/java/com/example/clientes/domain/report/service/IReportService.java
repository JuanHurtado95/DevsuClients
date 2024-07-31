package com.example.clientes.domain.report.service;

import com.example.clientes.domain.report.dto.ReportRequestDTO;
import com.example.clientes.domain.report.dto.ReportResponseDTO;

public interface IReportService {

    public ReportResponseDTO report(ReportRequestDTO request);
}
