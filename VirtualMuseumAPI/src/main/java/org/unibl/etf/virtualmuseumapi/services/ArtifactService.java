package org.unibl.etf.virtualmuseumapi.services;

import lombok.AllArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.unibl.etf.virtualmuseumapi.exceptions.BadRequestException;
import org.unibl.etf.virtualmuseumapi.model.dto.ArtifactDTO;
import org.unibl.etf.virtualmuseumapi.model.dto.TourRequestDTO;
import org.unibl.etf.virtualmuseumapi.model.entities.ArtifactEntity;
import org.unibl.etf.virtualmuseumapi.model.entities.TransactionEntity;
import org.unibl.etf.virtualmuseumapi.repositories.ArtifactRepository;

import java.io.File;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
@AllArgsConstructor
public class ArtifactService {

    private final ArtifactRepository artifactRepository;
    private final TransactionService transactionService;

    public List<ArtifactDTO> getAllArtifactsByTourId(Integer tourId, TourRequestDTO tourRequestDTO) {
        TransactionEntity transactionEntity = transactionService.getTransactionEntityByTicketNumberAndTourIdAndUserId(tourRequestDTO.getTicketNumber(), tourId, tourRequestDTO.getUserId());

        if (transactionEntity == null || !transactionEntity.getIsSuccess())
            throw new BadRequestException("Ticket not valid.");

        List<ArtifactEntity> artifactEntities = artifactRepository.getAllByTourId(tourId);
        List<ArtifactDTO> artifactDTOs = new ArrayList<>();

        for (ArtifactEntity ae : artifactEntities) {
            if (!"ytube".equals(ae.getType())) {
                File file = new File(PATH_TO_ARTIFACTS + tourId + File.separator + ae.getUri());
                try {
                    byte[] fileContent = FileUtils.readFileToByteArray(file);
                    String encodedString = Base64.getEncoder().encodeToString(fileContent);
                    artifactDTOs.add(new ArtifactDTO(ae.getType(), encodedString, file.getName(), ""));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                String videoId = ae.getUri().split("v=")[1].split("&")[0];
                System.out.println("Video ID: " + videoId);
                artifactDTOs.add(new ArtifactDTO(ae.getType(), ae.getUri(), "External link", videoId));
            }
        }

        return artifactDTOs;
    }

    private static final String PATH_TO_ARTIFACTS = "C:\\Users\\njego\\Desktop\\IP\\projektni-zadatak-2022\\VirtualMuseumAdmin\\WebContent\\WEB-INF\\artifacts\\";
}