package com.nocrud.service;

import com.nocrud.config.MappingConfiguration;
import com.nocrud.domain.UrlStore;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @Description:
 * @Author Yan XinYu
 **/
public class DiskService implements Service{

    private MappingConfiguration mappingConfiguration;

    public DiskService(MappingConfiguration mappingConfiguration){
        this.mappingConfiguration = mappingConfiguration;
    }

    @Override
    public void register(UrlStore urlStore) {

    }

    public static void main(String[] args) throws IOException {
        ClassPathResource resource = new ClassPathResource("/disk/recode.crud");

        URL url = ClassUtils.getDefaultClassLoader().getResource("/");
        System.out.println(url.getPath());

        Path path = Paths.get(System.getProperty("user.dir") + "/assets/file.txt");
        OpenOption[] openOptions = {StandardOpenOption.CREATE,
                StandardOpenOption.APPEND,StandardOpenOption.WRITE};
        FileChannel fileChannel = FileChannel.open(path, openOptions);//只读
//        fileChannel.write();

    }

}
