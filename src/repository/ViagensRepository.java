package repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import model.Fly;

public class ViagensRepository {
	private List<Fly> flies;
	private File database;

	// Sobreescrever o arquivo
	private void saveFlies() {
		// buscando e recuperando arquivo.
		try (PrintWriter writer = new PrintWriter(new FileOutputStream(database, false))) {
			for (Fly fly : flies) {
				// formatando a linha do dado
				String data = fly.getId() + ";" + fly.getNome() + ";" + 
						fly.getInicioFly() + ";" + fly.getFimFly();
				// escreve uma linha e passa para a proxima
				writer.println(data);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo database nao encontrado! Deu ruim");
		}
	}

	public ViagensRepository() {
		this.database = new File("database-flies.txt");
		this.flies = new ArrayList<>();
		// carregar os dados
		loadFlies();
	}
	
	// Carregar 
	private void loadFlies() {
		try (Scanner sc = new Scanner(database)){
			while (sc.hasNextLine()) {
				String[] data = sc.nextLine().split(";");
				if(data.length >= 4) {
					// 0 -> id, 1 -> nome, 2 -> inicio, 3 -> Fim
					Fly fly = new Fly();
					fly.setId(Integer.parseInt(data[0]));
					fly.setNome(data[1]);
					fly.setInicioFly(data[2]);
					fly.setFimFly(data[3]);
					flies.add(fly);
				}
								
			}
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo nao encontrado, criando um novo!");
		}
	}

	// CRUD -> Create, Read, Update and Delete

	// Update - Atualizar
	public void updateFly(Fly updatedFly) {
		for (int i = 0; i < flies.size(); i++) {
			if(flies.get(i).getId() == updatedFly.getId()) {
				flies.set(i, updatedFly);
				saveFlies();
				break;
			}
		}
	}
	
	// buscar unico
	public Fly getFlyById(int id) {
		for(Fly fly : flies) {
			if(fly.getId() == id) {
				return fly;
			}
		}
		return null;
	}
	
	
	// Buscar todos
	public List<Fly> buscarTodos() {
		return new ArrayList<>(flies);
	}

	// Deletar Objeto Especifico
	public void deleteFly(int id) {
		// Percorrer todo o Array, caso seja igual ele vai remover
		flies.removeIf(fly -> fly.getId() == id);
		saveFlies();
	}

	// Criar Fly
	public void addFly(Fly fly) {
		// vai faltar o ID
		fly.setId(getNextId());
		flies.add(fly);
		// Sobrescrever o arquivo database
		saveFlies();
	}

	
	
	// Logica para pegar o proximo ID
	private int getNextId() {
		int maxId = 0;
		for (Fly fly : flies) {
			// Verifica se o numero é maior que o nosso numero maximo
			if (fly.getId() > maxId) {
				maxId = fly.getId();
			}
		}
		return maxId + 1;
	}

}
