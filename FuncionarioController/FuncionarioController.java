package FuncionarioController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    public List<Funcionario> getAllFuncionarios() {
        return funcionarioService.findAll();
    }

    @GetMapping("/{id}")
    public Funcionario getFuncionarioById(@PathVariable Long id) {
        return funcionarioService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado com o id: " + id));
    }

    @PostMapping
    public Funcionario createFuncionario(@RequestBody Funcionario funcionario) {
        return funcionarioService.save(funcionario);
    }

    @PutMapping("/{id}")
    public Funcionario updateFuncionario(@PathVariable Long id, @RequestBody Funcionario funcionarioAtualizado) {
        return funcionarioService.findById(id).map(funcionario -> {
            funcionario.setNome(funcionarioAtualizado.getNome());
            funcionario.setDepartamento(funcionarioAtualizado.getDepartamento());
            return funcionarioService.save(funcionario);
        }).orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado com o id: " + id));
    }

    @DeleteMapping("/{id}")
    public void deleteFuncionario(@PathVariable Long id) {
        funcionarioService.deleteById(id);
    }
}
