package fr.univrouen.stb23v1.model;

import java.util.List;

import jakarta.xml.bind.annotation.*;

@XmlRootElement
@XmlType(name = "team")
@XmlAccessorType(XmlAccessType.NONE)
public class Team {

    @XmlElements({@XmlElement(name = "member", required = true)})
    private List<Member> member;

    public Team(){
    }

    @Override
    public String toString(){
        String s = "";
        for (int i = 0; i < member.size(); i++) {
            s += "Membre " + i + " :\n";
            s += member.get(i).toString() + "\n\n";
        }
        return s;
    }
}
