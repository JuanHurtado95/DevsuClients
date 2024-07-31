package com.example.clientes.domain.report.service;

import com.example.clientes.domain.account.dto.AccountReportDTO;
import com.example.clientes.domain.motion.dto.MotionReportDTO;
import com.example.clientes.domain.report.dto.ClientReportDTO;
import com.example.clientes.domain.report.dto.ReportDTO;
import com.example.clientes.domain.report.dto.ReportRequestDTO;
import com.example.clientes.domain.report.dto.ReportResponseDTO;
import com.example.clientes.infraestructure.motion.repository.MotionRepository;
import com.example.clientes.infraestructure.report.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportService implements IReportService{

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private MotionRepository motionRepository;

    @Override
    public ReportResponseDTO report(ReportRequestDTO request) {

        ReportResponseDTO result = new ReportResponseDTO();

        result.setNombre(clientConsult(request.getClientId()));

        List<ReportDTO> accountResults = reportRepository.getReport(request.getClientId(), request.getStartDate(), request.getEndDate());

        Map<Long, List<ReportDTO>> groupedReportData = accountResults.stream()
                .collect(Collectors.groupingBy(ReportDTO::getNumeroCuenta));

        List<AccountReportDTO> accountReports = groupedReportData.entrySet().stream()
                .map(entry -> {
                    Long numeroCuenta = entry.getKey();
                    List<ReportDTO> accountMovements = entry.getValue();

                    // Obtener saldo y tipo de cuenta (asumimos que son iguales para todos los movimientos de la cuenta)
                    Double saldo = accountMovements.get(0).getSaldo();
                    String tipoCuenta = accountMovements.get(0).getTipoCuenta();

                    // Crear lista de MotionReportDTO con manejo de valores nulos
                    List<MotionReportDTO> movimientos = accountMovements.stream()
                            .filter(movement -> movement.getFecha() != null &&
                                    movement.getTipoMovimiento() != null &&
                                    movement.getValor() != null &&
                                    movement.getValor() != 0.0)
                            .map(movement -> new MotionReportDTO(
                                    movement.getFecha(),
                                    movement.getTipoMovimiento(),
                                    movement.getValor()))
                            .collect(Collectors.toList());

                    // Crear AccountReportDTO
                    return new AccountReportDTO(numeroCuenta, saldo, tipoCuenta, movimientos);
                })
                .collect(Collectors.toList());

        result.setCuentas(accountReports);
        return result;
    }

    private String clientConsult(Long clienteId) {
        String url = "http://localhost:8080/clientes/" + clienteId;
        ClientReportDTO clientReportDTO = restTemplate.getForObject(url, ClientReportDTO.class);
        if(clientReportDTO != null){
            return clientReportDTO.getNombre();
        }else{
            throw new RuntimeException("Client not found");
        }
    }
}
