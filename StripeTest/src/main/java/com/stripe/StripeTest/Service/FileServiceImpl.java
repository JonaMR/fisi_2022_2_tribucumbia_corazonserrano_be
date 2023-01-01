package com.stripe.StripeTest.Service;

import com.stripe.StripeTest.Repository.GradoRepository;
import com.stripe.StripeTest.Repository.MatriculaRepository;
import com.stripe.StripeTest.Repository.PagoRepository;
import com.stripe.StripeTest.model.*;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class FileServiceImpl implements FileService{

    private final Path rootFolder = Paths.get("uploads");
    private final UserAlumnoService userAlumnoService;
    private final MatriculaRepository matriculaRepository;
    private final GradoRepository gradoRepository;
    private final PagoRepository pagoRepository;

    public FileServiceImpl(UserAlumnoService userAlumnoService, MatriculaRepository matriculaRepository,
                           GradoRepository gradoRepository, PagoRepository pagoRepository) {
        this.userAlumnoService = userAlumnoService;
        this.matriculaRepository = matriculaRepository;
        this.gradoRepository = gradoRepository;
        this.pagoRepository = pagoRepository;
    }

    @Override
    public void save(MultipartFile file, Integer idAlumno) throws Exception{
        //Validacion de nombre por aca ?? o por front
        Files.copy(file.getInputStream(),this.rootFolder.resolve(file.getOriginalFilename()));
        //Buscando alumno
        Alumno alumno = userAlumnoService.getById(idAlumno);
        Matricula optmatricula = matriculaRepository.findByIdAlumno(alumno);
        Aula aula = optmatricula.getIdAula();
        int grado = aula.getGrado();
        Optional<Grado> optgrado = gradoRepository.findById(grado);
        Double precio = optgrado.get().getPrecio();
        //Creacion de registro en tabla pago
        Pago pago = new Pago();
        pago.setFecha(LocalDate.now());
        pago.setMonto(precio);
        pago.setIdMatricula(optmatricula);
        pago.setArchivo(file.getOriginalFilename());
        pagoRepository.save(pago);
    }

    @Override
    public Resource load(String fileName) throws Exception{
        Path file = rootFolder.resolve(fileName);
        return new UrlResource(file.toUri());
    }

    @Override
    public void save(List<MultipartFile> files) throws Exception {
        for (MultipartFile file : files) {
            this.save(file,0);
        }
    }

    @Override
    public Stream<Path> loadAll() throws Exception {
        return Files.walk(rootFolder, 1).filter(path -> !path.equals(rootFolder)).map(rootFolder::relativize);
    }


}
