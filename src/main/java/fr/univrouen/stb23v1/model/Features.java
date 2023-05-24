package fr.univrouen.stb23v1.model;

import jakarta.xml.bind.annotation.*;

import java.util.List;
@XmlRootElement
@XmlType(name = "features")
@XmlAccessorType(XmlAccessType.NONE)
public class Features {
    @XmlElements({@XmlElement(name = "feature", required = true)})
    private List<Feature> feature;
}
