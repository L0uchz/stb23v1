package fr.univrouen.stb23v1.model;

import java.util.List;

import jakarta.xml.bind.annotation.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

@XmlRootElement
@XmlType(name = "member")
@XmlAccessorType(XmlAccessType.NONE)
public class Member {

    @Id
    @Field
    @XmlElements({@XmlElement (name = "person", required = true)})
    private Person person;

    @Field
    @XmlElement(required = true)
    private String mail;

    @Field
    @XmlElement(required = true)
    private List<String> function;

    public Member(Person person, List<String> function){
        super();
        this.person = person;
        this.function = function;
    }

    public Member(){
    }

    public Person getPerson() {
        return person;
    }

    public String getMail() {
        return mail;
    }

    public List<String> getFunction() {
        return function;
    }

    @Override
    public String toString(){
        String s = "Membre de l'équipe : " + person.toString() + "\nMail de contact : " + mail +"\n";
        for (int i = 0; i < function.size(); i++) {
            s += "- Fonction " + (i+1) + " :" + function.get(i).toString() + "\n";
        }
        return s;
    }
}
