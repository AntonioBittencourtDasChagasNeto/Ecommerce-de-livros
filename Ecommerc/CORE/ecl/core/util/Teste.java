package ecl.core.util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ecl.dominio.Categoria;
import ecl.dominio.EntidadeDominio;
import ecl.dominio.Livro;
import jdk.nashorn.internal.runtime.Undefined;
public class Teste {
	public static void main(String[] args) throws IOException {
		  Livro liv = new Livro();
		  Livro liv2 = new Livro();
		  Livro liv3 = new Livro();
		  Categoria categoria = new Categoria();
		  Categoria categoria2 = new Categoria();
		  Categoria categoria3 = new Categoria();
		  List<Categoria> cat = new ArrayList<Categoria>();
		  List<Livro> livros = new ArrayList<Livro>();
		  List<Livro> livros2 = new ArrayList<Livro>();
		  List<Categoria> cat4 = new ArrayList<Categoria>();
		  List<Categoria> cat5 = new ArrayList<Categoria>();
		  List<Categoria> cat2 = new ArrayList<Categoria>(); 
		  List<Categoria> cat3 = new ArrayList<Categoria>();
		  boolean flag = false;
		  
		  
		  
		  
		  liv.setId(1);
		  categoria.setId(1);
		  categoria.setNome("Aventura");
		  
		  cat.add(categoria);
		  System.out.println("Primeiro Categoria  =  " + categoria.getNome()); 
		  categoria = new Categoria();
		  
		  categoria.setId(2);
		  categoria.setNome("Ação");
		  cat.add(categoria);
		  liv.setFlagAtivado(true);
		  liv.setIdcategoria(cat);
		  livros.add(liv);

		  liv2.setId(2);
		  categoria2.setId(1);
		  categoria2.setNome("Aventura");
		  System.out.println("Segundo Categoria  =  " + categoria2.getNome()); 
		  
		  
		  cat2.add(categoria2);
		  categoria2 = new Categoria();
		  categoria2.setId(2);
		  categoria2.setNome("Ação");
		  cat2.add(categoria2);
		  liv2.setIdcategoria(cat2);
		  liv2.setFlagAtivado(true);
		  livros.add(liv2);
		  
		  liv3.setId(1);
		  categoria3.setId(3);
		  categoria3.setNome("Terror");
		  liv3.setFlagAtivado(true);
		  cat3.add(categoria3);
		  liv3.setIdcategoria(cat3);
		  livros.add(liv3);
		  
		  System.out.println("TAMANHO DA LISTA = " + livros.size());
		  System.out.println("TAMANHO DA categoria = " + cat.size());
		  System.out.println("NOME DA lista categoria 1= " + livros.get(0).getIdcategoria().get(0).getNome());
		  System.out.println("NOME DA lista categoria 2= " + livros.get(0).getIdcategoria().get(1).getNome());
	        for(int z = 0 ; z < livros.size();z++)
	        {
	        	  System.out.println("Id = " + livros.get(z).getId());
	        	  for(int y = 0; y < livros.get(z).getIdcategoria().size(); y++)
	        	  {
	        		  System.out.println("Categoria  =  " + livros.get(z).getIdcategoria().get(y).getNome()); 
	        	  }
	        	  System.out.println("\n\n\n////////////////////////////////////////////////////"); 
	        }
		  int ajuda = livros.size();
	        // remove nove - ok
	        for (int i = 0; i < ajuda; i++) {
	        	cat4 = livros.get(i).getIdcategoria();
	        	flag = false;
	        	for(int j = 0; j < ajuda; j++)
	        	{
	        		 System.out.println("TAMANHO DA LISTA = " + livros.size());

	        		 if (i!=j && livros.get(i).getId() == livros.get(j).getId() && livros.get(j).getFlagAtivado() == true) {
	        			 
	        			   
	        			   for(Categoria ca : livros.get(j).getIdcategoria())
	        			   {
	        			     cat4.add(ca);
	        			   }
	        			   livros.get(i).setIdcategoria(cat4);
	        			   livros.get(j).setFlagAtivado(false);
	        			   livros.get(i).setFlagAtivado(false);
	        			  
	        			   
	        		 }
	 	            
	        		
	        	}
	        	 for(Livro livro : livros2)
  			   {
  				   if(livro.getId() == livros.get(i).getId())
  				   {
  					   flag = true;
  				   }
  			   }
  			   if(flag == false)
  			   {
  				   livros2.add(livros.get(i));
  			   }
	        }
	        
	        for(Livro li : livros)
	        {
	        	 for(Categoria ca : li.getIdcategoria())
  			   {
  			     ca.setFlgAtivado(true);
  			   }
	        }
	        for(Livro li : livros2)
	        {
	        	  System.out.println("Id = " + li.getId());
	        	  for(Categoria ca : li.getIdcategoria())
	        	  {
	        		  System.out.println("Categoria  =  " + ca.getNome()); 
	        	  }
	        	  System.out.println("\n\n\n////////////////////////////////////////////////////"); 
	        }




	    }
		}//main