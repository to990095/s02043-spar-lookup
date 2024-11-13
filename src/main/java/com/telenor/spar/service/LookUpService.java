package com.telenor.spar.service;

//import lombok.extern.slf4j.Slf4j;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.telenor.spar.constants.Constants;
import com.telenor.spar.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;


//@Slf4j
@Service
public class LookUpService {

    @Value("${peppol.directory.search.url}")
    private String peppolDirectorySearchUrl; //="https://directory.peppol.eu/search/1.0/json";

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ObjectMapper objectMapper;

    //  @Autowired



    public LookUpResponse participantLookUp(String identifier) throws JsonProcessingException, NoSuchAlgorithmException {
        String searchUrl = peppolDirectorySearchUrl + Constants.PARTICIPANT +
                Constants.ACTOR_IDENTIFIER + "::" + identifier + Constants.BEAUTIFY;

        JsonNode response = restTemplate.getForObject(searchUrl, JsonNode.class);
        DirectorySearch directorySearch = objectMapper.treeToValue(response, DirectorySearch.class);

        return transformSearchResponse(identifier, directorySearch);
    }



    private LookUpResponse transformSearchResponse(String identifier,  DirectorySearch directorySearch) throws NoSuchAlgorithmException {

        ArrayList<DocumentTypes> documentTypesList = new ArrayList<>();
        LookUpResponse lookUpResponse = new LookUpResponse();

        ArrayList<Match> matchArrayList = directorySearch.getMatches();
        if(matchArrayList.size() ==0){
            System.out.println("Participant Id not found!!");
            return new LookUpResponse();
        }else{
            String url =  createSmdUrl(identifier);
            String participantId = matchArrayList.get(0).getParticipantID().getValue();
            String participantIdentifierScheme = matchArrayList.get(0).getParticipantID().getScheme();
            List<DocType> documentTypesArrayList = matchArrayList.get(0).getDocTypes();

            for(DocType doc : documentTypesArrayList){
                DocumentTypes documentTypes = new DocumentTypes();
                documentTypes.setDocumentType( doc.getValue());
                documentTypes.setDocumentIdentifier(doc.getScheme());
                documentTypes.setUrl(url);
                documentTypesList.add(documentTypes);
            }

            lookUpResponse.setDocumentTypes(documentTypesList);
            lookUpResponse.setParticipantId(participantId);
            lookUpResponse.setParticipantIdentifierScheme(participantIdentifierScheme);

            return lookUpResponse;
        }

    }

    private String createSmdUrl(String identifier) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(identifier.getBytes());
        byte[] digest = md.digest();
        String hex = DatatypeConverter.printHexBinary(digest).toLowerCase();

        return "http://b-" + (hex + "." + Constants.ACTOR_IDENTIFIER + "." + Constants.SML_DOMAIN
                +"/" + Constants.ACTOR_IDENTIFIER + "::" + identifier)
                .replace(":", "%3A").replace("#", "%23");
    }
}