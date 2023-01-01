package com.stripe.StripeTest.controller;

import com.stripe.StripeTest.Repository.AlumnoRepository;
import com.stripe.StripeTest.Repository.MatriculaRepository;
import com.stripe.StripeTest.Repository.PagoRepository;
import com.stripe.StripeTest.Service.FileService;
import com.stripe.StripeTest.Service.UserAlumnoService;
import com.stripe.StripeTest.model.Alumno;
import com.stripe.StripeTest.model.Matricula;
import com.stripe.StripeTest.model.Pago;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/eduapp/pagoMatricula")
public class PagoMatriculaController {

    private final FileService fileService;

    private final MatriculaRepository matriculaRepository;

    private final AlumnoRepository alumnoRepository;

    private final UserAlumnoService userAlumnoService;
    private final PagoRepository pagoRepository;

    @Autowired
    public PagoMatriculaController(FileService fileService, MatriculaRepository matriculaRepository,
                                   AlumnoRepository alumnoRepository, UserAlumnoService userAlumnoService,
                                   PagoRepository pagoRepository){
        this.fileService = fileService;
        this.matriculaRepository = matriculaRepository;
        this.alumnoRepository = alumnoRepository;
        this.userAlumnoService = userAlumnoService;
        this.pagoRepository = pagoRepository;
    }

    @GetMapping("/validarMatriculaPendiente/{idAlumno}")
    public ResponseEntity<Boolean> validarMatriculaPendiente(@PathVariable Integer idAlumno){
        Alumno alumno = userAlumnoService.getById(idAlumno);
        Matricula matricula = matriculaRepository.findByIdAlumno(alumno);
        return ResponseEntity.ok(matricula.getEstado().equals("PENDIENTE"));
        //Verdadero: Si su matricula figura como pendiente (caso correcto)
        //Falso: Si su matricula figura como pagada o cancelada (caso incorrecto)
    }

    //Registrar voucher y generar registro de pago -
    //Input: Archivo de una imagen - Que cuyo nombre de archivo sea el apellidodelalumno
    @PostMapping("/subirArchivo/{idAlumno}")
    public ResponseEntity<String> uploadFile(@RequestParam("files") MultipartFile file,
                                             @PathVariable Integer idAlumno){
        try {
            fileService.save(file, idAlumno);
            return ResponseEntity.status(HttpStatus.OK).body("Archivos cargados correctamente ");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrio un error en el servidor");
        }
    }

    //Decargar las imagenes de los vouchers en base al id del pago - campo nombreArchivo
    @GetMapping("/archivo/{idPago}")
    public ResponseEntity<Resource> getFile(@PathVariable Integer idPago) throws Exception {
        Pago pago = pagoRepository.findById(idPago).get();
        Resource resource = fileService.load(pago.getArchivo());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    //Sin uso
    @GetMapping("/all")
    public ResponseEntity<List<File>> getAllFiles() throws Exception {
        List<File> files = fileService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder.fromMethodName(PagoMatriculaController.class, "getFile", path.getFileName().toString()).build().toString();

            return new File(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    //Luego de revisar los comprobantes (Lista de pagos - vouchers) este metodo cambia el estado de la matricula
    //a Pagado.
    @PutMapping("/aprobarMatricula/{idPago}")
    public ResponseEntity<Matricula> aprobarMatricula(@PathVariable Integer idPago){
        Optional<Pago> optPago = pagoRepository.findById(idPago);
        Pago pago = optPago.get();
        Matricula matricula = pago.getIdMatricula();
        matricula.setEstado("PAGADO");
        return ResponseEntity.ok(matriculaRepository.save(matricula));
    }

}
