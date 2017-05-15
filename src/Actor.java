import java.util.List;
import java.util.Scanner;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import common.Serie;

public class Actor implements MessageListener {
	private ConnectionFactory connectionFactory;
	private Destination destination;
	static Scanner scan = new Scanner(System.in);

	public Actor() throws NamingException{
		this.connectionFactory = InitialContext.doLookup("jms/RemoteConnectionFactory");
		this.destination = InitialContext.doLookup("jms/topic/playTopic");
	
	}
	public static void main(String[] args) throws NamingException {
		menu2();
	}

	public static void menu1() {

		while (true) {
			System.out.print("\nBEM-VINDO! \n1-> Registo\n2-> Login\n3-> Sair\nEscolha a opção: ");
			String option = scan.nextLine();

			String email = "";
			String user = "";
			String pass = "";
			// REGISTO
			if (option.equals("1")) {

				System.out.print("\nEmail: ");
				email = scan.nextLine();
				System.out.print("Nome: ");
				user = scan.nextLine();
				System.out.print("Pass: ");
				pass = scan.nextLine();

			} else if (option.equals("2")) {

				System.out.print("Email: ");
				email = scan.nextLine();
				System.out.print("Nome: ");
				user = scan.nextLine();

			}else if(option.equals("3")){
				System.out.println("Bye Bye");
				break;
			}
			else {
				System.out.println("\nA opçcao está incorreta. Tente novamente!");
			}
		}
	}
	public static void menu2(){
		
		while (true){
			System.out.println("**********************MENU**************************");
			System.out.println("1->Listar todas as Séries");
			System.out.println("2->Informação sobre uma Série");
			System.out.println("3->Listar convites pendentes");
			System.out.println("4->Mandar mensagem a um actor");
			System.out.println("5-> Aceitar ou Rejeitar Convites");
			System.out.println("6-> Sair");
			System.out.print("Escolha a opção: ");
			String option = scan.nextLine();
			
			if(option.equals("1")){
				System.out.println(todosResearchers());
			}
			else if(option.equals("2")){
				
			}
			else if(option.equals("3")){
				
			}
			else if(option.equals("4")){
				
			}
			else if(option.equals("5")){
				
			}
			else if(option.equals("6")){
				System.out.println("Bye Bye");
				break;
			}
			else{
				System.out.println("\nA opçcao está incorreta. Tente novamente!");
			}
		}
	}

	public void send(Messages objeto, Destination mandar, String receber) {
		try (JMSContext context = connectionFactory.createContext("adriana", "Adriana1");) {
			JMSProducer messageProducer = context.createProducer();
			TextMessage msg = context.createTextMessage();
			Destination tmp = context.createTemporaryQueue();
			msg.setJMSReplyTo(tmp);
			msg.setText("Hello my friend!");
			messageProducer.send(destination, msg);
			JMSConsumer cons = context.createConsumer(tmp);
			String str = cons.receiveBody(String.class);
			System.out.println("I received the reply sent to the temporary queue: " + str);
			//JMSProducer messageProducer = context.createProducer();
			//messageProducer.send(destination, text);
		} catch (Exception re) {
			re.printStackTrace();
		}
	}
	@Override
	public void onMessage(Message arg0) {
		// TODO Auto-generated method stub

	}
	
	public static String todosResearchers() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPersistence");
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("SELECT s FROM Serie s");

		@SuppressWarnings("unchecked")
		List<Serie> listaREGISTOS = (List<Serie>) query.getResultList();

		String registos = "";
		if (listaREGISTOS.size() != 0) {
			registos = "\nLista de researchers armazenados:";
			for (int i = 0; i < listaREGISTOS.size(); i++) {
				int reg = i + 1;
				registos = registos + "\n" + reg + ". " + listaREGISTOS.get(i).toString();
			}
		} else {
			registos = "\nATENCAO: Nao existem Researchers no sistema!";
		}

		em.close();
		emf.close();

		return registos;
	}
}
