package com.stripe.StripeTest.Service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public interface FileService {

    public void save(MultipartFile file, Integer idAlumno) throws Exception;

    public Resource load(String nameFile) throws Exception;

    public void save(List<MultipartFile> files) throws Exception;

    public Stream<Path> loadAll() throws Exception;
}
