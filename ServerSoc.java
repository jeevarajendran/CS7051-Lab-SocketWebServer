/*
 * To change this license header, choose License Headers in Project Properties.
  * To change this template file, choose Tools | Templates
   * and open the template in the editor.
    */
    package serversoc;

    import java.io.*;
    import java.net.*;
    import java.util.concurrent.ExecutorService;
    import java.util.concurrent.Executors;

    /**
     *
      * @author jeevarajendran
       */
       public class ServerSoc implements Runnable {

           /**
	        * @param args the command line arguments
		     */
		         Socket clientSocket;
			     ServerSoc(Socket clientSocket)
			        {
					this.clientSocket = clientSocket;
			        }
				public static void main(String[] args) {
						         
				ServerSocket serSoc = null;
								   
				try
				{
				       System.out.println("Server running :\n");
				       serSoc = new ServerSocket(9999);
				}
				catch(Exception e)
				{
				        System.out.println(e + ": Exception in server");
				}
											           
				ExecutorService pool = Executors.newFixedThreadPool(2);
												           
				try
				{
				        while(true)
				        {
					        System.out.println("Waiting for a client to get connected :\n");
				                System.out.println("------------------------------------------\n");
				                Socket socket = serSoc.accept();
				                pool.execute(new Thread(new ServerSoc(socket)));
				        }
				}
				catch(Exception e)
				{
				        System.out.println(e + "Exception while waiting for the client");
				}
			}
			
			public void run()
			{
			        PrintStream output = null;
			        try
			        {
				        System.out.println("Connected to a client : \n ");
			                System.out.println("---------------------------- \n ");
			                BufferedReader bd = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			                output = new PrintStream(clientSocket.getOutputStream());
				        while(true)
				        {
				                String temp = bd.readLine();
				                System.out.println("Message from Client : "+ temp +" \n");
				                System.out.println("Enter your message for the client : ");
				                InputStreamReader rd = new InputStreamReader(System.in);
				                BufferedReader br = new BufferedReader(rd);
				                String messagetoclient = br.readLine();
				                output.println(messagetoclient);
				       }
				}
			        catch(Exception e)
			        {
			               System.out.println(e);
			        }
		       }
		}

