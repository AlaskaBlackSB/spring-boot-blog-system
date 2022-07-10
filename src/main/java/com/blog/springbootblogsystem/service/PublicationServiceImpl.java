package com.blog.springbootblogsystem.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.springbootblogsystem.dto.PublicationDTO;
import com.blog.springbootblogsystem.entity.Publication;
import com.blog.springbootblogsystem.exception.ResourceNotFoundException;
import com.blog.springbootblogsystem.repository.PublicationRepository;

@Service
public class PublicationServiceImpl implements PublicationService  {

    @Autowired
    private PublicationRepository publicationRepository;

    @Override
    public PublicationDTO create(PublicationDTO publicationDTO) {
        
        // Convert DOT to Entity
        Publication publication = mapDTOToEntity(publicationDTO);

        // Save Entity (Create Publication in DB)
        Publication newPublication = publicationRepository.save(publication);

        // Object DTO to return
        PublicationDTO responsePublicationDTO = mapEntityToDTO(newPublication);

        return responsePublicationDTO;
    }

    @Override
    public List<PublicationDTO> findAll() {
        
        List<Publication> publications = publicationRepository.findAll();

        // Return the list of publicationsDTO, convert each publication Entity to DTO
        return publications.stream().map( publication -> mapEntityToDTO(publication)).collect(Collectors.toList());
    }

    @Override
    public PublicationDTO findById(Long id) {
        
        // Search Publication by ID, if not exist, return Exception
        Publication publication = publicationRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Publication", "Id", id) );

        return mapEntityToDTO(publication);
    }

    
    @Override
    public PublicationDTO update(PublicationDTO publicationDTO, Long id) {
        
        // Search Publication by ID, if not exist, return Exception
        Publication publication = publicationRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Publication", "Id", id) );

        // Update fields
        publication.setTitle(publicationDTO.getTitle());
        publication.setDescription(publicationDTO.getDescription());
        publication.setContent(publicationDTO.getContent());

        // Update publication in the DB
        Publication publicationUpdated = publicationRepository.save(publication);

        // Return publicationDTO updated
        return mapEntityToDTO(publicationUpdated);
    }

    @Override
    public void delete(Long id) {
        
        // Search Publication by ID, if not exist, return Exception
        Publication publication = publicationRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Publication", "Id", id) );

        // Delete publication if exist
        publicationRepository.delete(publication);

    }
    
    
    /**
     * Method to convert Entity to DTO
     * @param publication To convert to DTO
     * @return PublicationDTO
     */
    private PublicationDTO mapEntityToDTO(Publication publication) {

        // Convert Entity to DTO
        PublicationDTO publicationDTO = new PublicationDTO();

        publicationDTO.setId(publication.getId());
        publicationDTO.setTitle(publication.getTitle());
        publicationDTO.setDescription(publication.getDescription());
        publicationDTO.setContent(publication.getContent());

        return publicationDTO;

    }
    
    /**
     * Method to convert DTO to Entity
     * @param publication To convert to Entity
     * @return Publication (Entity)
     */
    private Publication mapDTOToEntity(PublicationDTO publicationDTO) {

        // Convert DTO to Entity
        Publication publication = new Publication();

        publication.setId(publicationDTO.getId());
        publication.setTitle(publicationDTO.getTitle());
        publication.setDescription(publicationDTO.getDescription());
        publication.setContent(publicationDTO.getContent());

        return publication;

    }
        
}