package br.com.fatec.catalogo.services;

import br.com.fatec.catalogo.models.CategoriaModel;
import br.com.fatec.catalogo.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public List<CategoriaModel> listarTodas() {
        return repository.findAll();
    }

    public CategoriaModel buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
    }

    public CategoriaModel salvar(CategoriaModel categoria) {
        return repository.save(categoria);
    }

    public CategoriaModel atualizar(Long id, CategoriaModel categoriaAtualizada) {
        CategoriaModel categoria = buscarPorId(id);
        categoria.setNome(categoriaAtualizada.getNome());
        return repository.save(categoria);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}

