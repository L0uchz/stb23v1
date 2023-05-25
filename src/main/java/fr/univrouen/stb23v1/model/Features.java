package fr.univrouen.stb23v1.model;

import jakarta.xml.bind.annotation.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
@XmlRootElement
@XmlType(name = "features")
@XmlAccessorType(XmlAccessType.NONE)
public class Features {

    @Id
    @Field
    @XmlElements({@XmlElement(name = "feature", required = true)})
    private List<Feature> feature;

    public Features(){
    }

    @Override
    public String toString(){
        String s = "";
        for (int i = 0; i < feature.size(); i++) {
            s += "Fonctionnalités " + i + " :\n";
            s += feature.get(i).toString() + "\n\n";
        }
        return s;
    }
}
