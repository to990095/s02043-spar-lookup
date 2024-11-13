package com.telenor.spar.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Data
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class LookUpResponse {
     String participantId;
     String participantIdentifierScheme;
     ArrayList<DocumentTypes> documentTypes;

    public String getParticipantId() {
        return participantId;
    }

    public void setParticipantId(String participantId) {
        this.participantId = participantId;
    }

    public ArrayList<DocumentTypes> getDocumentTypes() {
        return documentTypes;
    }

    public void setDocumentTypes(ArrayList<DocumentTypes> documentTypes) {
        this.documentTypes = documentTypes;
    }

    public String getParticipantIdentifierScheme() {
        return participantIdentifierScheme;
    }

    public void setParticipantIdentifierScheme(String participantIdentifierScheme) {
        this.participantIdentifierScheme = participantIdentifierScheme;
    }

    @Override
    public String toString() {
        return "LookUpResponse[participantId= " + participantId
                + " participantIdentifierScheme= " + participantIdentifierScheme
                + " documentTypes= " + documentTypes + "]";
    }
}

