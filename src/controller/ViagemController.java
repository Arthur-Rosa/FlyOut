package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Fly;
import repository.ViagensRepository;

public class ViagemController {
	// Cntrl + Espaço importar!
	@FXML
	private TableView<Fly> tableView;
	
	@FXML
	private TableColumn<Fly, String> cNome;
	
	@FXML
	private TableColumn<Fly, String> cPartida;
	
	@FXML
	private TableColumn<Fly, String> cDestino;
	
	@FXML
	private TextField nomeViagem;
	
	@FXML
	private TextField partida;
	
	@FXML
	private TextField destino;
	
	private ObservableList<Fly> data;
	
	private ViagensRepository repoViagem;
	
	@FXML
	public void initialize() {
		// instanciando com o valor default da celula ( cell )
		cNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		cPartida.setCellValueFactory(new PropertyValueFactory<>("inicioFly"));
		cDestino.setCellValueFactory(new PropertyValueFactory<>("fimFly"));
		// instanciando lista observable
		data = FXCollections.observableArrayList();
		
		// tableview aceita somente ObservableList
		tableView.setItems(data);
		repoViagem = new ViagensRepository();
		// Preencher lista
		carregarDadosSalvos();
	}
	
	public void carregarDadosSalvos() {
		try (BufferedReader br = new BufferedReader(new FileReader("database-flies.txt"))) {
			String line;
			// caso linha nao tenha registro, o while para!
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(";");
				// [0: id, 1: Nome, 2: Inicio, 3: Fim]
				if (parts.length >= 4) {
					Fly fly = new Fly();
					// preencher o objeto fly
					fly.setId(Integer.parseInt(parts[0]));
					fly.setNome(parts[1]);
					fly.setInicioFly(parts[2]);
					fly.setFimFly(parts[3]);
					// adicionar no observableList
					data.add(fly);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void cadastrar() {
		Fly fly = new Fly();
		fly.setNome(nomeViagem.getText());
		fly.setInicioFly(partida.getText());
		fly.setFimFly(destino.getText());
		repoViagem.addFly(fly);
		clearFields();
	}
	
	public void clearFields() {
		nomeViagem.clear();
		partida.clear();
		destino.clear();
	}
	
	public void limpar() {
		clearFields();
	}
}
