package com.alura.controllers;


import com.alura.modelo.*;
import com.alura.modelo.curso.CursoRepository;
import com.alura.modelo.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private TopicosService topicosService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico , UriComponentsBuilder uriComponentsBuilder) {
        var autor = usuarioRepository.findById(datosRegistroTopico.autor()).get();
        var curso = cursoRepository.findById(datosRegistroTopico.curso()).get();
        Topico topico = topicoRepository.save(new Topico(datosRegistroTopico,autor,curso));
        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topico.getTitulo(),topico.getMensaje(),
                topico.getfechaCreacion(),topico.getAutor().getNombre(),topico.getCurso().getId());
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }
    @GetMapping
    public Page<DatosListaTopicos> listadoTopicos(@PageableDefault(size = 3) Pageable paginacion){
        return topicoRepository.findAll(paginacion).map(DatosListaTopicos::new);
        //return ResponseEntity.ok(medicoRepository.findByActivoTrue(paginacion).map(DatosListadoMedico::new));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico, @PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        topico.actualizarDatos(datosActualizarTopico);
        return ResponseEntity.ok(new DatosRespuestaTopico(topico.getTitulo(),topico.getMensaje(),
                topico.getfechaCreacion(),topico.getAutor().getNombre(),topico.getCurso().getId()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarMedico(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        topicoRepository.delete(topico);
        return ResponseEntity.noContent().build();
    }


}
