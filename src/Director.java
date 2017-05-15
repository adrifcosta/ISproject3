import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class Director implements MessageListener{
	private ConnectionFactory connectionFactory;
	private Destination destination;
	
	@SuppressWarnings("static-access")
	public Director() throws NamingException {
		this.connectionFactory = InitialContext.doLookup("jms/RemoteConnectionFactory");
		this.destination = InitialContext.doLookup("jms/topic/playTopic");
		
	}
	private void MenuDir() throws JMSException, IOException {
		@SuppressWarnings("resource")
		Scanner scan= new Scanner(System.in);
		try (JMSContext context = connectionFactory.createContext("adriana", "Adriana1");) {
	
		
			JMSConsumer consumer = context.createConsumer(destination);
			consumer.setMessageListener(this);
			while (true){
				
				System.out.println("");
				System.out.println("");
				System.out.println("MENU DO DIRETOR");
				System.out.println("1- Adicionar uma nova série");
				System.out.println("2- Atualizar série já existente");
				System.out.println("3- Remover uma série");
				System.out.println("4- Listar todos os atores");
				System.out.println("5- Convidar todos os atores a participarem numa determinada série");
				System.out.println("6- Consultar atores que aceitaram participar numa dada série");
				System.out.println("7- Selecionar ator para uma determinada série");
				System.out.println("8- Convidar um ator a participar numa nova série");
				System.out.println("9- Sair!");
				System.out.println("");
				System.out.print("Escolhe a opção: ");
				
				String option=scan.nextLine();
				if (option.equals("1"))
				{
					//ADICIONAR UMA NOVA SÉRIE
				}
				else if(option.equals("2"))
				{
					//ATUALIZAR SERIE JA EXISTENTE
					
				}
				else if(option.equals("3"))
				{
					//REMOVER UMA SÉRIE
					
				}
				else if(option.equals("4"))
				{
					//LISTAR TODOS OS ATORES
					System.out.println(allActors());
					
				}
				else if(option.equals("5"))
				{
					//CONVIDAR TODOS OS ATORES A PARTICIPAREM NUMA DADA SERIE

				}
				else if(option.equals("6"))
				{
					//CONSULTAR ATORES QUE ACEITARAM PARTICIPAR NUMA DADA SÉRIE
				}
				else if(option.equals("7"))
				{
					//SELECIONAR ATOR PARA PARTICIPAR NUMA DADA SÉRIE
				}
				else if(option.equals("8"))
				{
					//CONVIDAR UM ATOR A PARTICIPAR NUMA NOVA SÉRIE
					
				}
				else if(option.equals("9"))
				{
					break;
				}
			}
		}
		}
	public static void main(String[] args) throws NamingException, JMSException, IOException {
		Director director=new Director();
		director.MenuDir();
	}
	
	
	// LISTAR TODOS OS ATORES
	public static List<String> allActors() {
		//EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPersistence");
		//EntityManager em = emf.createEntityManager();
		//String jpql = "SELECT c.name FROM Cast c";
		//TypedQuery<String> typedQuery = em.createQuery(jpql, String.class);
		//List<String> allActorsNames=typedQuery.getResultList();
		//return allActorsNames;
		return null;
	}
	
	@Override
	public void onMessage(Message arg0) {
		// TODO Auto-generated method stub
		
	}
}
