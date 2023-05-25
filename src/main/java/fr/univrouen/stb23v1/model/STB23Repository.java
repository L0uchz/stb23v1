package fr.univrouen.stb23v1.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class STB23Repository {

    private STBRepository stb;

    @Autowired
    public STB23Repository(STBRepository repository){
        this.stb = repository;
    }

    public List<STB> getSTB(){
        return stb.findAll();
    }

    public STB getSTB(int id){
        for (STB stbf : stb.findAll()) {
            if (stbf.getId() == id) {
                return stbf;
            }
            }
        return null;
    }

    public int saveSTB(STB stb1) {
        List<STB> stbs = stb.findAll();
        for (STB stbf : stbs) {
            if (stbf.getTitle().equals(stb1.getTitle()) && stbf.getPublished().equals(stb1.getPublished())) {
                return -1;
            }
        }
        stb.save(stb1);
        return 0;
    }


    public int deleteSTB(int id) {
        for (STB stbf : stb.findAll()) {
            if (stbf.getId() == id) {
                stb.delete(stbf);
                return 0;
            }
        }
        return -1;
    }
}
