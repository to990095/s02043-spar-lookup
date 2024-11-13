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
public class Match {
    Participant participantID;
    ArrayList<DocType> docTypes;
  //  public ArrayList<Entity> entities;


    public Participant getParticipantID() {
        return participantID;
    }

    public void setParticipantID(Participant participantID) {
        this.participantID = participantID;
    }

    public ArrayList<DocType> getDocTypes() {
        return docTypes;
    }

    public void setDocTypes(ArrayList<DocType> docTypes) {
        this.docTypes = docTypes;
    }

    @Override
    public String toString() {
        return "Match[partipantID= " + participantID + " DocTypes= " + docTypes + "]";
    }
}
