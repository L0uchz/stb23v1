package fr.univrouen.stb23v1.model;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import javax.xml.transform.stream.StreamSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestSTB {

    public String loadFileXML() {
        Resource resource = new DefaultResourceLoader().getResource("classpath:xml/test0.xml");
        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String content = br.readLine();
            while (content != null) {
                sb.append(content);
                content = br.readLine();
            }
            br.close();
            return new String(sb);
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
