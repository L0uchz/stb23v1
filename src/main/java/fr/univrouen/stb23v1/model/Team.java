package fr.univrouen.stb23v1.model;

import java.util.List;

import jakarta.xml.bind.annotation.*;

@XmlRootElement
@XmlType(name = "team")
@XmlAccessorType(XmlAccessType.NONE)
public class Team {

    @XmlElements({@XmlElement(name = "member", required = true)})
    private List<Member> member;
}
