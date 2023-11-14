package com.api.resources;

import java.awt.print.Pageable;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.domain.Product;
import com.api.repository.ProdutoRepository;
import com.api.services.ProdutoService;
import com.fasterxml.jackson.databind.ObjectMapper;

//@autor Jadson Feitosa #29

@RestController
@RequestMapping("/api/produto")
public class ProdutoResource implements ResourceBase<Product, Long>{
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ProdutoRepository produtoRepository;


//	Salvar Produto
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Product> save(@Valid @RequestBody Product pEntity, HttpServletResponse response) {
		Product produtoSalvo = null;
		try {
			produtoSalvo = produtoService.save(pEntity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
	}
	

//	Atualizar entidade 
	@PutMapping("/{pID}")
	public ResponseEntity<Product> update(@Valid @PathVariable Long pID, @RequestBody Product pEntity) {
		Product produtoSalvo = produtoService.update(pID, pEntity);
		return ResponseEntity.ok(produtoSalvo);
	}

//	Deletar produto
	@DeleteMapping("/{pID}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long pID) {
		produtoRepository.deleteById(pID);
	}

//	Filtro por ID
	@GetMapping("/{pID}")
	public ResponseEntity<Product> findById(@PathVariable Long pID) {
		return ResponseEntity.ok(produtoRepository.findById(pID).get());
	}
	
//	Filtro por ID
	@GetMapping("/busca/{ptitulo}")
	public ResponseEntity<List<Product>> findByTitulo(@PathVariable String ptitulo) {
		List<Product> lista = produtoRepository.findProdutoByNameContains(ptitulo);
		
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping("/deleteall")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAll( @RequestBody List<Product> pList) {
		produtoRepository.deleteAll(pList);
	}

	
	@GetMapping("/filter/{tipoFilter}&{dados}")
	public List<Product> findFilterProdutos(@PathVariable String tipoFilter, @PathVariable String dados){
		return produtoService.findFilterProdutos(tipoFilter, dados);
	}
	
	@GetMapping("/estabelecimento/{pID}")
	public List<Product> findByEstabelecimento(@PathVariable Long pID) {
		return produtoRepository.findByEstabelecimento(pID);
	}
	
//	@GetMapping("/categoria/{id}")
//	public List<Product> findProdutosByCategoria(@PathVariable Long id){
//		return produtoRepository.findProdutoByCategoria(id);
//	}
	
	public Page<Product> findAllPage(Product pFilter, Pageable pPage) {
		return null;
	}

	@GetMapping
	public List<Product> findAllList() {
		return produtoRepository.findAll();
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/send")
    public ResponseEntity<String> receiveData(String pessoaJson, MultipartFile foto) {

        ObjectMapper mapper = new ObjectMapper();
        Product pessoa = null;

        try {
            pessoa = mapper.readValue(pessoaJson, Product.class);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Não foi possível ler o json");
        }

        System.out.println(pessoa);
        System.out.println(foto.getOriginalFilename());
        return ResponseEntity.ok("Deu certo!");
    }

}
